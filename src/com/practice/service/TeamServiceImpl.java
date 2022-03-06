package com.practice.service;

import com.practice.model.BowlingInfo;
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

    @Override
    public Team addNewPlayer(Team currentTeam, Player player){
        if(currentTeam.getPlayers().contains(player)){
            System.out.println("Player is already in the team.");
        } else {
            currentTeam.getPlayers().add(player);
            currentTeam.getActivePlayers().add(player);
        }

        return currentTeam;
    }

    @Override
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
    public void battingOrder(Team currentBattingTeam) {
        System.out.println("Batting order for team : " + currentBattingTeam.getTeamName());
        for(Player player : currentBattingTeam.getPlayers()){
            System.out.println(player.getName());
        }
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

    @Override
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

    @Override
    public void swapStrike(Team team){
        Player strikeBatsman = team.getStrikeBatsman();
        team.setStrikeBatsman(team.getNonStrikeBatsman());
        team.setNonStrikeBatsman(strikeBatsman);

    }

    @Override
    public boolean updateWicket(Team currentBattingTeam){
        currentBattingTeam.setTeamWicket(currentBattingTeam.getTeamWicket()+1);

        if(hasAvailablePlayers(currentBattingTeam)){
            Player updatedBatsmanScore = updatePlayerStats(0, true, currentBattingTeam);
            currentBattingTeam.setStrikeBatsman(updatedBatsmanScore);

            updateTeamScore(0, currentBattingTeam);
            updateFallenWicket(currentBattingTeam);

            return true;
        } else{
            System.out.println("Team is all out.");
            return false;
        }
    }

    @Override
    public void initializeNewBowler(Team team){
        int size = team.getPlayers().size();
        Player newBowler = new Player();

        do{
            newBowler = team.getPlayers().get((int) (Math.random() * size));
        } while(team.getCurrentBowler().equals(newBowler));


        team.setCurrentBowler(newBowler);
        database.saveUpdatedTeamStatus(team.getTeamName(), team);
    }

    @Override
    public void updateBowlerScore(String score, Team currentBowlingTeam) {
        Player currentBowler = currentBowlingTeam.getCurrentBowler();

        Player player = playerService.updatePlayerBowlingScore(score, currentBowlingTeam, currentBowler);
        currentBowlingTeam.setCurrentBowler(player);

        database.saveUpdatedTeamStatus(currentBowlingTeam.getTeamName(), currentBowlingTeam);
    }

    private Player updatePlayerStats(int run , boolean isWicket, Team team){
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
