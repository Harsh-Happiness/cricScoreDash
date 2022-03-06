package com.practice.repository;

import com.practice.model.Match;
import com.practice.model.Team;

import java.util.HashMap;
import java.util.Map;

public class Storage {
    public static Map<String, Match> matchMap = new HashMap<>();
    public static Map<String, Team> teamMap = new HashMap<>();
}
