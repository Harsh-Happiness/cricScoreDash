package com.practice.service;

import com.practice.model.Player;
import com.practice.model.Team;

public interface PlayerService {

    Player updatePlayerBattingScore(int score, Team team, Player player);
    Player updatePlayerBowlingScore(String score, Team team, Player player);
}
