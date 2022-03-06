package com.practice.model;

public class BattingInfo {
    private String playerName;
    private int runScored;
    private int ballsFaced;
    private int numOfSix;
    private int numOfFour;

    private double strikeRate;

    public BattingInfo(String playerName) {
        this.playerName = playerName;
        this.runScored = 0;
        this.ballsFaced = 0;
        this.numOfSix = 0;
        this.numOfFour = 0;
        this.strikeRate = 0;
    }

    public BattingInfo() {
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getRunScored() {
        return runScored;
    }

    public void setRunScored(int runScored) {
        this.runScored = runScored;
    }

    public int getBallsFaced() {
        return ballsFaced;
    }

    public void setBallsFaced(int ballsFaced) {
        this.ballsFaced = ballsFaced;
    }

    public int getNumOfSix() {
        return numOfSix;
    }

    public void setNumOfSix(int numOfSix) {
        this.numOfSix = numOfSix;
    }

    public int getNumOfFour() {
        return numOfFour;
    }

    public void setNumOfFour(int numOfFour) {
        this.numOfFour = numOfFour;
    }

    public double getStrikeRate() {
        return strikeRate;
    }

    public void setStrikeRate(double strikeRate) {
        this.strikeRate = strikeRate;
    }
}
