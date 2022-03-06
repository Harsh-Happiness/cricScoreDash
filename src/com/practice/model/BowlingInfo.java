package com.practice.model;

public class BowlingInfo {
    private String playerName;

    private int runsConceded;
    private int wicketTaken;
    private int dotBalls;

    private int maidenOvers;

    private double economy;
    private int oversBowled;

    //TODO : over can become object and composed here
    public BowlingInfo() {
    }

    public BowlingInfo(String playerName) {
        this.playerName = playerName;
        this.oversBowled = 0;
        this.runsConceded = 0;
        this.wicketTaken = 0;
        this.maidenOvers = 0;
        this.dotBalls = 0;
        this.economy = 0;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getOversBowled() {
        return oversBowled;
    }

    public void setOversBowled(int oversBowled) {
        this.oversBowled = oversBowled;
    }

    public int getRunsConceded() {
        return runsConceded;
    }

    public void setRunsConceded(int runsConceded) {
        this.runsConceded = runsConceded;
    }

    public int getWicketTaken() {
        return wicketTaken;
    }

    public void setWicketTaken(int wicketTaken) {
        this.wicketTaken = wicketTaken;
    }

    public int getMaidenOvers() {
        return maidenOvers;
    }

    public void setMaidenOvers(int maidenOvers) {
        this.maidenOvers = maidenOvers;
    }

    public int getDotBalls() {
        return dotBalls;
    }

    public void setDotBalls(int dotBalls) {
        this.dotBalls = dotBalls;
    }

    public double getEconomy() {
        return economy;
    }

    public void setEconomy(double economy) {
        this.economy = economy;
    }
}
