package com.practice.service;

import com.practice.model.Match;
import com.practice.model.Player;
import com.practice.model.Team;
import com.practice.repository.Database;

import java.util.List;
import java.util.Scanner;

public class MatchService {
    private Database database;
    private TeamService teamService;

    public MatchService( ) {
        this.database = Database.getInstance();
        this.teamService = new TeamService();
    }

    public void addPlayersInTeam(String match1, Team team, List<String> playersList){
        if(playersList.size() >= Database.matchMap.get(match1).getNumOfPlayerInATeam())
            System.out.println("Over limit of player in a team.");
        else{
            for(String player : playersList){
                teamService.addNewPlayer(team.getTeamName(), new Player(player));
            }
        }
    }

    public void startMatch(String matchName, Team currentBattingTeam){
        Match currentMatch = database.matchMap.get(matchName);

        currentMatch.setCurrentBattingTeam(currentBattingTeam);
        database.saveUpdatedMatch(matchName, currentMatch);

        startBatting(currentBattingTeam , currentMatch);
        switchTeam(currentBattingTeam, currentMatch);
        startBatting(currentBattingTeam, currentMatch);

        displayResult(matchName);
    }


    private void startBatting(Team currentBattingTeam, Match currentMatch){
        Scanner sc = new Scanner(System.in);
        teamService.initializeOpeners(currentBattingTeam);

        for(int i = 1 ; i <= currentMatch.getNumOfOverInMatch(); i++ ){
            System.out.println("Playing over # : "+ i);

            for(int j =0; j < 6; j++ ){
                String score = sc.nextLine();

                if(!updateScore(score, currentBattingTeam) || (currentMatch.getCurrentBattingTeam() != null
                        && currentMatch.getCurrentBattingTeam().getTeamScore() >= currentMatch.getPrevBattingTeam().getTeamScore())
                ){
                    //todo : dashboard;
                    return ;
                }
            }

            teamService.swapStrike(currentBattingTeam);
            //todo : dashboard
        }

    }

    private boolean updateScore(String score, Team currentBattingTeam){
        if(score.equals("W")){
           return teamService.updateWicket(currentBattingTeam);
        } else if( score.contains("wd") ){
            teamService.updateTeamScore(Integer.parseInt(score.substring(0,1)), currentBattingTeam);
        } else{
            teamService.updateScore(Integer.parseInt(score), currentBattingTeam);
        }

        return true;
    }

    private void switchTeam(Team currentBattingTeam, Match currentMatch){
        if(currentBattingTeam == currentMatch.getTeamOne()){
            currentMatch.setCurrentBattingTeam(currentMatch.getTeamTwo());
            currentMatch.setPrevBattingTeam(currentMatch.getTeamOne());
        } else{
            currentMatch.setCurrentBattingTeam(currentMatch.getTeamOne());
            currentMatch.setPrevBattingTeam(currentMatch.getTeamTwo());
        }

        database.saveUpdatedMatch(currentMatch.getMatchName(), currentMatch);
    }

    public void displayResult(String currentMatchName){
        Match currentMatch = database.getMatch(currentMatchName);
        //TODO : status of match
        Team currTeam = currentMatch.getCurrentBattingTeam();
        Team prevTeam = currentMatch.getPrevBattingTeam();

        if(currTeam.getTeamScore() >= prevTeam.getTeamScore()){
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
}
