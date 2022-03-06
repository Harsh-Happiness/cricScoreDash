package com.practice.model;

import java.util.Objects;

public class Player {
    private String name;
    private BattingInfo battingInfo;
    private BowlingInfo bowlingInfo;

    private  boolean isOnstrike;
    private boolean isOut;

    public Player(String name) {
        this.name = name;
    }

    public boolean isOnstrike() {
        return isOnstrike;
    }

    public void setOnstrike(boolean onstrike) {
        isOnstrike = onstrike;
    }

    public boolean isOut() {
        return isOut;
    }

    public void setOut(boolean out) {
        isOut = out;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BattingInfo getBattingInfo() {
        return battingInfo;
    }

    public void setBattingInfo(BattingInfo battingInfo) {
        this.battingInfo = battingInfo;
    }

    public BowlingInfo getBowlingInfo() {
        return bowlingInfo;
    }

    public void setBowlingInfo(BowlingInfo bowlingInfo) {
        this.bowlingInfo = bowlingInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return isOnstrike == player.isOnstrike && isOut == player.isOut && Objects.equals(name, player.name) && Objects.equals(battingInfo, player.battingInfo) && Objects.equals(bowlingInfo, player.bowlingInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, battingInfo, bowlingInfo, isOnstrike, isOut);
    }
}
