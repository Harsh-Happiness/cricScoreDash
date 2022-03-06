package com.practice.enums;

public enum Extra {
    WIDE("wd"), NO("nb"), BYE("bye"), LEG_BYE("legBye"),PENALTY("pen");

    private String extraCode;

    Extra(String extraCode) {
        this.extraCode = extraCode;
    }
}
