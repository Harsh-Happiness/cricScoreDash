package com.practice.service;

import com.practice.model.Team;

import java.util.List;

public interface MatchService {

    void addPlayersInTeam(String match1, Team team, List<String> playersList);

    void startMatch(String matchName, Team currentBattingTeam, Team currentBowlingTeam);

    void displayResult(String currentMatchName);
}
