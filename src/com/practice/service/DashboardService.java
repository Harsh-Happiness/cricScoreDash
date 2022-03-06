package com.practice.service;

import com.practice.model.Team;

public interface DashboardService {

    void showBattingStats(Team currentTeam, int overs);
    void showBowlingStats(Team currentTeam);

    void showFinalScore(Team currentTeam);

    void showMatchSummary(Team teamOne, Team teamTwo);
}
