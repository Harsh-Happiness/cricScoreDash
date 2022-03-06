package com.practice.model;


public class Match {
    private String matchName;
    private Team teamOne;
    private Team teamTwo;

    private int numOfPlayerInATeam;
    private int numOfOverInMatch;

    private Team currentBattingTeam;
    private Team prevBattingTeam;

    private Team winner;

    public Match(String matchName, Team teamOne, Team teamTwo, int numOfPlayerInATeam, int numOfOverInMatch) {
        this.matchName = matchName;
        this.teamOne = teamOne;
        this.teamTwo = teamTwo;
        this.numOfPlayerInATeam = numOfPlayerInATeam;
        this.numOfOverInMatch = numOfOverInMatch;
    }

    public String getMatchName() {
        return matchName;
    }

    public void setMatchName(String matchName) {
        this.matchName = matchName;
    }

    public Team getTeamOne() {
        return teamOne;
    }

    public void setTeamOne(Team teamOne) {
        this.teamOne = teamOne;
    }

    public Team getTeamTwo() {
        return teamTwo;
    }

    public void setTeamTwo(Team teamTwo) {
        this.teamTwo = teamTwo;
    }

    public int getNumOfPlayerInATeam() {
        return numOfPlayerInATeam;
    }

    public void setNumOfPlayerInATeam(int numOfPlayerInATeam) {
        this.numOfPlayerInATeam = numOfPlayerInATeam;
    }

    public int getNumOfOverInMatch() {
        return numOfOverInMatch;
    }

    public void setNumOfOverInMatch(int numOfOverInMatch) {
        this.numOfOverInMatch = numOfOverInMatch;
    }

    public Team getCurrentBattingTeam() {
        return currentBattingTeam;
    }

    public void setCurrentBattingTeam(Team currentBattingTeam) {
        this.currentBattingTeam = currentBattingTeam;
    }

    public Team getPrevBattingTeam() {
        return prevBattingTeam;
    }

    public void setPrevBattingTeam(Team prevBattingTeam) {
        this.prevBattingTeam = prevBattingTeam;
    }

    public Team getWinner() {
        return winner;
    }

    public void setWinner(Team winner) {
        this.winner = winner;
    }
}

