package com.practice.service;

import com.practice.model.*;
import com.practice.repository.Database;

public class DashboardServiceImpl implements DashboardService{

    private Database database;
    public DashboardServiceImpl() {
         this.database = Database.getInstance();
    }

    @Override
    public void showBattingStats(Team currentTeam, int overs) {
        battingHeader(currentTeam);
        displayBattingCard(currentTeam);
        displayFinalScore(currentTeam, overs);
    }

    @Override
    public void showBowlingStats(Team currentBowlingTeam, int overs) {
        bowlingHeader(currentBowlingTeam);
        displayBowlingCard(currentBowlingTeam);
    }

    private void displayBowlingCard(Team currentBowlingTeam) {
        for(Player player : currentBowlingTeam.getPlayers()){
            BowlingInfo bowlingInfo = player.getBowlingInfo();
            if(bowlingInfo.getDotBalls() != 0 || bowlingInfo.getRunsConceded() != 0){
                System.out.println(player.getName() + "\t\t | " + bowlingInfo.getOversBowled() + "\t\t | " + bowlingInfo.getRunsConceded()
                + "\t\t | " + bowlingInfo.getWicketTaken() + "\t\t | " + bowlingInfo.getDotBalls() + "\t\t | " + bowlingInfo.getEconomy());
            }
        }
    }

    private void displayBattingCard(Team currentTeam) {
        for(Player player : currentTeam.getPlayers()){
            BattingInfo battingInfo = player.getBattingInfo();
            String onStrike =
                    currentTeam.getStrikeBatsman().getName().equals(player.getName()) ||
                    currentTeam.getNonStrikeBatsman().getName().equals(player.getName())
                            ? "*\t" : "\t";
            System.out.println(battingInfo.getPlayerName() + onStrike + "\t |   " + battingInfo.getRunScored() + " \t\t |   " +
                    battingInfo.getNumOfFour() + " \t\t |   " + battingInfo.getNumOfSix() + " \t\t |   " + battingInfo.getBallsFaced()
                    + " \t\t |   " + battingInfo.getStrikeRate());
        }
    }

    private void displayFinalScore(Team currentTeam, int overs) {
        System.out.println("Total : " + currentTeam.getTeamScore() + " / " + currentTeam.getTeamWicket());
        System.out.println("Overs : "+ overs);
        System.out.println("Extras : " + currentTeam.getTeamExtras().toString());
        System.out.println("\n\n");
    }

    private void battingHeader(Team currentTeam) {
        System.out.println("Score card for batting team : " + currentTeam.getTeamName() );
        System.out.println("PLAYER  | SCORE \t| 4s \t\t| 6s \t\t| BALLS \t\t| SR");
    }


    private void bowlingHeader(Team currentTeam) {
        System.out.println("Score card for bowling team : " + currentTeam.getTeamName() );
        System.out.println("PLAYER \t | OVERS \t | RUNS \t | WICKET \t | DOT_BALLS \t | ECO");
    }

    @Override
    public void showFinalScore(Match currentMatch) {
        //TODO : status of match
        Team currTeam = currentMatch.getCurrentBattingTeam();
        Team prevTeam = currentMatch.getPrevBattingTeam();

        if(currTeam.getTeamScore() == prevTeam.getTeamScore()){
            System.out.println("Match is drawn.");

        } else if(currTeam.getTeamScore() > prevTeam.getTeamScore()){
            currentMatch.setWinner(currTeam);

            int activeWickets = currTeam.getActivePlayers().size() + 2;
            System.out.println(currTeam.getTeamName() + " won the match by " + activeWickets + " wickets.");
        } else{
            currentMatch.setWinner(prevTeam);

            int scoreDiff = prevTeam.getTeamScore() - currTeam.getTeamScore();
            System.out.println(prevTeam.getTeamName() + " won the match by " + scoreDiff + " runs." );
        }

        database.saveUpdatedMatch(currentMatch.getMatchName(), currentMatch);
    }

    @Override
    public void showMatchSummary(Team teamOne, Team teamTwo) {

    }


}
