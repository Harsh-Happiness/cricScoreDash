package com.practice.service;

import com.practice.model.BattingInfo;
import com.practice.model.Player;
import com.practice.model.Team;
import com.practice.repository.Database;

public class PlayerServiceImpl implements PlayerService {
    private Database database;

    public PlayerServiceImpl() {
        database = Database.getInstance();
    }

    public Player updatePlayerBattingScore(int score, Team team, Player player){
        BattingInfo battingInfo = player.getBattingInfo();

        battingInfo.setBallsFaced(battingInfo.getBallsFaced()+1);
        battingInfo.setRunScored( battingInfo.getRunScored() + score);

        double newStrikeRate = updateStrikeRate(battingInfo.getRunScored(), battingInfo.getBallsFaced());
        battingInfo.setStrikeRate(newStrikeRate);

        if(score == 4) {
            battingInfo.setNumOfFour(battingInfo.getNumOfFour() + 1);
        } else if (score == 6){
            battingInfo.setNumOfSix(battingInfo.getNumOfSix() + 1);
        }

        // update player info
        player.setBattingInfo(battingInfo);

        return player;
    }

    private double updateStrikeRate(int runs, int balls){
        return (runs/balls) * 100.0;
    }
}
