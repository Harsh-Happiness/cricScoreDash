package com.practice.model;


import com.practice.enums.MatchStatus;

public class Match {
    private String matchName;
    private Team teamOne;
    private Team teamTwo;

    private int numOfPlayerInATeam;
    private int numOfOverInMatch;

    private Team currentBattingTeam;
    private Team prevBattingTeam;

    private Team currentBowlingTeam;
    private Team prevBowlingTeam;

    private Team winner;
    private Enum matchStaus;

    public Match(String matchName, Team teamOne, Team teamTwo, int numOfPlayerInATeam, int numOfOverInMatch) {
        this.matchName = matchName;
        this.teamOne = teamOne;
        this.teamTwo = teamTwo;
        this.numOfPlayerInATeam = numOfPlayerInATeam;
        this.numOfOverInMatch = numOfOverInMatch;
        this.matchStaus = MatchStatus.YET_TO_START;
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

    public Enum getMatchStaus() {
        return matchStaus;
    }

    public void setMatchStaus(Enum matchStaus) {
        this.matchStaus = matchStaus;
    }

    public Team getCurrentBowlingTeam() {
        return currentBowlingTeam;
    }

    public void setCurrentBowlingTeam(Team currentBowlingTeam) {
        this.currentBowlingTeam = currentBowlingTeam;
    }

    public Team getPrevBowlingTeam() {
        return prevBowlingTeam;
    }

    public void setPrevBowlingTeam(Team prevBowlingTeam) {
        this.prevBowlingTeam = prevBowlingTeam;
    }
}

