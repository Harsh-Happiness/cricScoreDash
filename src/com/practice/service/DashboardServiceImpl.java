package com.practice.service;

import com.practice.model.BattingInfo;
import com.practice.model.Player;
import com.practice.model.Team;
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

    private void displayBattingCard(Team currentTeam) {
        for(Player player : currentTeam.getPlayers()){
            BattingInfo battingInfo = player.getBattingInfo();
            String onStrike = currentTeam.getStrikeBatsman().getName().equals(player.getName()) ? "*\t" : "\t";
            System.out.println(battingInfo.getPlayerName() + onStrike + "\t |   " + battingInfo.getRunScored() + " \t\t |   " +
                    battingInfo.getNumOfFour() + " \t\t |   " + battingInfo.getNumOfSix() + " \t\t |   " + battingInfo.getBallsFaced()
                    + " \t\t |   " + battingInfo.getStrikeRate());
        }
    }

    private void displayFinalScore(Team currentTeam, int overs) {
        System.out.println("Total : " + currentTeam.getTeamScore() + " / " + currentTeam.getTeamWicket());
        System.out.println("Overs : "+ overs);
        System.out.println("Extras : " + currentTeam.getTeamExtras().toString());
    }

    private void battingHeader(Team currentTeam) {
        System.out.println("Score card for batting team : " + currentTeam.getTeamName() );
        System.out.println("PLAYER  | SCORE \t\t| 4s \t\t| 6s \t\t| BALLS \t\t| SR");
    }

    @Override
    public void showBowlingStats(Team currentTeam) {
        bowlingHeader(currentTeam);
    }

    private void bowlingHeader(Team currentTeam) {
        System.out.println("Score card for bowling team : " + currentTeam.getTeamName() );
        System.out.println("PLAYER \t | OVERS \t\t | MAIDEN \t\t | RUNS \t\t | WICKET \t\t | ECO");
    }

    @Override
    public void showFinalScore(Team currentTeam) {

    }

    @Override
    public void showMatchSummary(Team teamOne, Team teamTwo) {

    }


}
