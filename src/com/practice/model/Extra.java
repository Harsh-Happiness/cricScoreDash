package com.practice.model;

public class Extra {
    private int wideBall;
    private int noBall;
    private int bye;
    private int legBye;

    public Extra() {
        this.wideBall = 0;
        this.noBall = 0;
        this.bye = 0;
        this.legBye = 0;
    }

    public int getWideBall() {
        return wideBall;
    }

    public void setWideBall(int wideBall) {
        this.wideBall = wideBall;
    }

    public int getNoBall() {
        return noBall;
    }

    public void setNoBall(int noBall) {
        this.noBall = noBall;
    }

    public int getBye() {
        return bye;
    }

    public void setBye(int bye) {
        this.bye = bye;
    }

    public int getLegBye() {
        return legBye;
    }

    public void setLegBye(int legBye) {
        this.legBye = legBye;
    }

    @Override
    public String toString() {
        return "Extra{" +
                "wideBall=" + wideBall +
                ", noBall=" + noBall +
                ", bye=" + bye +
                ", legBye=" + legBye +
                '}';
    }
}
