package com.practice.enums;

public enum MatchStatus {
    YET_TO_START("Yet to start"), IN_PROGRESS("In progress"), FINISHED("Finished");

    private String matchStatusDef;

    MatchStatus(String matchStatusDef) {
        this.matchStatusDef = matchStatusDef;
    }
}

