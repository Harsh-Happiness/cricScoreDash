package com.practice.service;

import com.practice.model.Match;
import com.practice.model.Team;

public interface DashboardService {

    void showBattingStats(Team currentTeam, int overs);
    void showBowlingStats(Team currentTeam, int overs);

    void showFinalScore(Match match);

    void showMatchSummary(Team teamOne, Team teamTwo);
}
