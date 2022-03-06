package com.practice.service;

import com.practice.model.Extra;
import com.practice.model.Player;
import com.practice.model.Team;
import com.practice.repository.Database;

public class TeamServiceImpl implements TeamService{
    private Database database;
    private PlayerServiceImpl playerService;

    public TeamServiceImpl() {
        database = Database.getInstance();
        playerService = new PlayerServiceImpl();
    }

    public Team addNewPlayer(Team currentTeam, Player player){
        if(currentTeam.getPlayers().contains(player)){
            System.out.println("Player is already in the team.");
        } else {
            currentTeam.getPlayers().add(player);
            currentTeam.getActivePlayers().add(player);
        }

        return currentTeam;
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

    @Override
    public void updateExtra(int runs, String extraType, Team currentBattingTeam) {
        Extra teamExtras = currentBattingTeam.getTeamExtras();

        switch (extraType){
            case "wd":
                teamExtras.setWideBall(teamExtras.getWideBall() + runs);
                break;
            case "nb":
                teamExtras.setNoBall(teamExtras.getNoBall() + runs);
                break;
            case "bye":
                teamExtras.setBye(teamExtras.getBye() + runs);
                break;
            default:
                break;
        }

        currentBattingTeam.setTeamExtras(teamExtras);
        database.saveUpdatedTeamStatus(currentBattingTeam.getTeamName(), currentBattingTeam);
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
