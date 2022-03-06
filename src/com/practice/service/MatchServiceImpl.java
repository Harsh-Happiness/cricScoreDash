package com.practice.service;

import com.practice.enums.MatchStatus;
import com.practice.model.Match;
import com.practice.model.Player;
import com.practice.model.Team;
import com.practice.repository.Database;

import java.util.List;
import java.util.Scanner;

public class MatchServiceImpl implements MatchService{
    private Database database;
    private TeamService teamService;
    private DashboardService dashboardService;

    public MatchServiceImpl( ) {
        this.database = Database.getInstance();
        this.teamService = new TeamServiceImpl();
        this.dashboardService = new DashboardServiceImpl();
    }

    public void addPlayersInTeam(String match1, Team team, List<String> playersList){
        if(playersList.size() > Database.matchMap.get(match1).getNumOfPlayerInATeam())
            System.out.println("Over limit of player in a team.");
        else{
            for(String player : playersList){
                team = teamService.addNewPlayer(team, new Player(player));
            }

            database.saveUpdatedTeamStatus(team.getTeamName(), team);
        }
    }

    public void startMatch(String matchName, Team currentBattingTeam){
        Match currentMatch = database.matchMap.get(matchName);
        currentMatch.setMatchStaus(MatchStatus.IN_PROGRESS);

        currentMatch.setCurrentBattingTeam(currentBattingTeam);
        database.saveUpdatedMatch(matchName, currentMatch);

        startBatting(currentMatch.getCurrentBattingTeam() , currentMatch);
        switchTeam(currentBattingTeam, currentMatch);
        startBatting(currentMatch.getCurrentBattingTeam(), currentMatch);

        displayResult(matchName);
    }


    private void startBatting(Team currentBattingTeam, Match currentMatch){
        System.out.println("---------------------------------------------------------------");
        System.out.println("Current Batting team : " + currentBattingTeam.getTeamName());
        Scanner sc = new Scanner(System.in);
        teamService.initializeOpeners(currentBattingTeam);

        for(int i = 1 ; i <= currentMatch.getNumOfOverInMatch(); i++ ){
            System.out.println("Playing Over # : "+ i);

            for(Integer j = 0; j < 6; j++ ){
                String score = sc.nextLine();

                if(!updateScore(score, currentBattingTeam) || (currentMatch.getPrevBattingTeam() != null
                        && currentMatch.getCurrentBattingTeam().getTeamScore() >= currentMatch.getPrevBattingTeam().getTeamScore())
                ){
                    dashboardService.showBattingStats(currentBattingTeam, i);
                    currentMatch.setMatchStaus(MatchStatus.FINISHED);
                    return ;
                }

                if(score.contains("wd") || score.contains("nb")){
                    j--;
                }
            }

            teamService.swapStrike(currentBattingTeam);
            dashboardService.showBattingStats(currentBattingTeam, i);
        }
        currentMatch.setMatchStaus(MatchStatus.FINISHED);
//        dashboardService.showBowlingStats();
    }

    private boolean updateScore(String score, Team currentBattingTeam){
        if(score.equals("W")){
           return teamService.updateWicket(currentBattingTeam);

        } else if( score.contains("wd") || score.contains("nb") ){
            int extraRuns = Integer.parseInt(score.substring(0,1));
            String extraType = score.substring(1);
            teamService.updateTeamScore(extraRuns, currentBattingTeam);
            teamService.updateExtra(extraRuns, extraType, currentBattingTeam);
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
