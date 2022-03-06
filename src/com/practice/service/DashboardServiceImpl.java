package com.practice.service;

import com.practice.model.Team;
import com.practice.repository.Database;

public class DashboardServiceImpl implements DashboardService{

    private Database database;
    public DashboardServiceImpl() {
    }

    public DashboardServiceImpl(Database database) {
        this.database = database;
    }

    @Override
    public void showBattingStats(Team currentTeam) {
        battingHeader();
    }

    @Override
    public void showBowlingStats(Team currentTeam) {
        bowlingHeader();
    }

    @Override
    public void showFinalScore(Team currentTeam) {

    }

    @Override
    public void showMatchSummary(Team teamOne, Team teamTwo) {

    }
}
