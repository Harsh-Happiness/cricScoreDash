package com.practice.service;

import com.practice.model.Player;
import com.practice.model.Team;
import com.practice.repository.Database;

public class TeamService {
    private Database database;
    private PlayerService playerService;

    public TeamService() {
        database = Database.getInstance();
        playerService = new PlayerService();
    }

    public void addNewPlayer(String teamName, Player player){
        Team currentTeam = database.teamMap.get(teamName);

        if(currentTeam.getPlayers().contains(player)){
            System.out.println("Player is already in the team.");
        } else {
            currentTeam.getPlayers().add(player);
            currentTeam.getActivePlayers().add(player);
        }
    }

    public void initializeOpeners(Team currentBattingTeam){

        if(currentBattingTeam.getActivePlayers().size() >= 2){
            currentBattingTeam.setStrikeBatsman(currentBattingTeam.getActivePlayers().poll());
            currentBattingTeam.setNonStrikeBatsman(currentBattingTeam.getActivePlayers().poll());
        } else{
            System.out.println("Minimum 2 players are required for opening");
        }

        database.saveUpdatedTeamStatus(currentBattingTeam.getTeamName(),currentBattingTeam);
    }

    public void updateScore(int score , Team team){
        Player updatePlayerStats = updatePlayerStats(score, false, team);
        team.setStrikeBatsman(updatePlayerStats);
        updateStrikeBatsman(score, team);

        team.setTeamScore(team.getTeamScore() + score);

    }

    private void updateStrikeBatsman(int score, Team team){
        if(score != 0 && score%2 != 0)
            swapStrike(team);
    }

    public void swapStrike(Team team){
        Player strikeBatsman = team.getStrikeBatsman();
        team.setStrikeBatsman(team.getNonStrikeBatsman());
        team.setNonStrikeBatsman(strikeBatsman);

    }

    public boolean updateWicket(Team team){
        team.setTeamWicket(team.getTeamWicket()+1);

        if(hasAvailablePlayers(team)){
            Player updatedBatsmanScore = updatePlayerStats(0, true,  team);
            team.setStrikeBatsman(updatedBatsmanScore);

            updateTeamScore(0, team);
            updateFallenWicket(team);

            return true;
        } else{
            System.out.println("Team is all out.");
            return false;
        }
    }

    private Player updatePlayerStats(int run ,boolean isWicket, Team team){
        Player strikeBatsman = team.getStrikeBatsman();

        if(isWicket) {
            strikeBatsman.setOut(true);
        }

        Player updatedBatsmanScore = playerService.updatePlayerBattingScore(run, team, strikeBatsman );

        return updatedBatsmanScore;
    }

    public void updateTeamScore(int run, Team team){
        team.setTeamScore(team.getTeamScore() + run);

        database.saveUpdatedTeamStatus(team.getTeamName(), team);
    }

    private void updateFallenWicket(Team team){
        Player nextStrikeBatsman = team.getActivePlayers().poll();
        team.setStrikeBatsman(nextStrikeBatsman);

        database.saveUpdatedTeamStatus(team.getTeamName(), team);
    }


    private boolean hasAvailablePlayers(Team team){
        if(team.getActivePlayers().size() > 0)
            return true;

        return false;
    }
}
