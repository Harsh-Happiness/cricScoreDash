package com.practice.service;

import com.practice.model.Player;
import com.practice.model.Team;

public interface TeamService {

    Team addNewPlayer(Team team, Player player);
    void initializeOpeners(Team currentBattingTeam);
    void battingOrder(Team currentBattingTeam);
    void updateScore(int score, Team team);
    void updateExtra(int runs, String extraType, Team currentBattingTeam);
    boolean updateWicket(Team currentBattingTeam);
    void swapStrike(Team team);
    void updateTeamScore(int parseInt, Team currentBattingTeam);

    void initializeNewBowler(Team team);
    void updateBowlerScore(String score, Team currentBowlingTeam);
}
