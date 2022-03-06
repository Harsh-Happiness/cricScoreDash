package com.practice.service;

import com.practice.model.BattingInfo;
import com.practice.model.BowlingInfo;
import com.practice.model.Player;
import com.practice.model.Team;
import com.practice.repository.Database;

public class PlayerServiceImpl implements PlayerService {
    private Database database;

    public PlayerServiceImpl() {
        database = Database.getInstance();
    }

    @Override
    public Player updatePlayerBattingScore(int score, Team team, Player player){
        BattingInfo battingInfo = player.getBattingInfo();

        battingInfo.setBallsFaced(battingInfo.getBallsFaced()+1);
        battingInfo.setRunScored( battingInfo.getRunScored() + score);

        double newStrikeRate = updateStrikeRate(battingInfo);
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

    private double updateStrikeRate(BattingInfo battingInfo) {
        double v = (battingInfo.getRunScored() * 1.0 / battingInfo.getBallsFaced() * 1.0) * 100.0;

        double v1 = Math.round(v * 100.0) / 100.0;
        return v1;
    }

    @Override
    public Player updatePlayerBowlingScore(String score, Team team, Player player){
        BowlingInfo bowlingInfo = player.getBowlingInfo();
        score = score.substring(0,1);

        switch (score){
            case "0":
                bowlingInfo.setDotBalls(bowlingInfo.getDotBalls() + 1);
                break;
            case "W":
                bowlingInfo.setWicketTaken(bowlingInfo.getWicketTaken() + 1); // TODO : against which player is wicket taken
                bowlingInfo.setDotBalls(bowlingInfo.getDotBalls() + 1);
                break;
            case "O":
                bowlingInfo.setOversBowled(bowlingInfo.getOversBowled() + 1);
                bowlingInfo.setEconomy(bowlingInfo.getRunsConceded()/bowlingInfo.getOversBowled());
                break;
            default:
                bowlingInfo.setRunsConceded(bowlingInfo.getRunsConceded() + Integer.parseInt(score));
                break;
        }
        player.setBowlingInfo(bowlingInfo);

        return player;
    }

}
