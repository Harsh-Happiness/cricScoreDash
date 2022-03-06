package com.practice.repository;

import com.practice.model.Match;
import com.practice.model.Team;

import java.util.HashMap;
import java.util.Map;

public class Database {
    public static Map<String, Match> matchMap;
    public static Map<String, Team> teamMap;

    private static Database database;

    private Database() {
    }

    public static Database getInstance(){
        if(database == null) {
            database = new Database();
        }
        return database;
    }

    public Match getMatch(String matchName){
        return matchMap.get(matchName);
    }

    public void saveUpdatedMatch(String matchName, Match updatedMatch){
        matchMap.put(matchName,updatedMatch);
    }

    public void saveUpdatedTeamStatus(String teamName, Team updatedTeam){
        teamMap.put(teamName,updatedTeam);
    }
}
