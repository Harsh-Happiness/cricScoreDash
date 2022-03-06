package com.practice.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Team {
    private String teamName;
    private List<Player> players;
    private Queue<Player> activePlayers;

    private int teamScore;
    private int teamWicket;

    private Player strikeBatsman;
    private Player nonStrikeBatsman;

    private Player currentBowler;
    private Extra teamExtras;

    public Team(String teamName) {
        this.teamName = teamName;
        this.players = new ArrayList<>();
        this.activePlayers = new LinkedList<>();
        this.teamScore = 0;
        this.teamWicket = 0;

        this.strikeBatsman = new Player();
        this.nonStrikeBatsman = new Player();
        this.currentBowler = new Player();
        this.teamExtras = new Extra();
    }

//    public void addNewPlayer(Player player){
//        if(this.players.contains(player)){
//            System.out.println("Player is already in the team.");
//        } else {
//            this.players.add(player);
//            this.activePlayers.add(player);
//        }
//    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Queue<Player> getActivePlayers() {
        return activePlayers;
    }

    public void setActivePlayers(Queue<Player> activePlayers) {
        this.activePlayers = activePlayers;
    }

    public int getTeamScore() {
        return teamScore;
    }

    public void setTeamScore(int teamScore) {
        this.teamScore = teamScore;
    }

    public int getTeamWicket() {
        return teamWicket;
    }

    public void setTeamWicket(int teamWicket) {
        this.teamWicket = teamWicket;
    }

    public Player getStrikeBatsman() {
        return strikeBatsman;
    }

    public void setStrikeBatsman(Player strikeBatsman) {
        this.strikeBatsman = strikeBatsman;
    }

    public Player getNonStrikeBatsman() {
        return nonStrikeBatsman;
    }

    public void setNonStrikeBatsman(Player nonStrikeBatsman) {
        this.nonStrikeBatsman = nonStrikeBatsman;
    }

    public Extra getTeamExtras() {
        return teamExtras;
    }

    public void setTeamExtras(Extra teamExtras) {
        this.teamExtras = teamExtras;
    }

    public Player getCurrentBowler() {
        return currentBowler;
    }

    public void setCurrentBowler(Player currentBowler) {
        this.currentBowler = currentBowler;
    }
}
