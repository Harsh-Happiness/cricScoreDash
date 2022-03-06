package com.practice;

import com.practice.model.Match;
import com.practice.model.Team;
import com.practice.repository.Database;
import com.practice.service.MatchServiceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class  Main {

    public static void main(String[] args) {
        Database database = Database.getInstance();
        MatchServiceImpl matchService = new MatchServiceImpl();

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter num of players for each team : ");
        int numOfPlayers = sc.nextInt();

        System.out.println("Enter no. of overs : ");
        int numOfOvers = sc.nextInt();

        Team India = new Team("India");
        Team England = new Team("England");
        database.teamMap.put("India", India);
        database.teamMap.put("England", England);

        Match match1 = new Match("Artherton-Gavaskar-2022-1",India, England, numOfPlayers, numOfOvers);
        database.matchMap.put("Artherton-Gavaskar-2022-1", match1);

        List<String> playersList1 = new ArrayList<>();
        List<String> playersList2 = new ArrayList<>();
        for(int i = 1 ; i <= numOfPlayers; i++ ){
            playersList1.add(new String("P"+String.valueOf(i)));
            playersList2.add(new String("P"+String.valueOf(numOfPlayers + i)));
        }
        matchService.addPlayersInTeam(match1.getMatchName(), India, playersList1);
        matchService.addPlayersInTeam(match1.getMatchName(), England, playersList2);
        matchService.startMatch(match1.getMatchName(), India, England);

/*
--------------------------------------------------------------------------------------------------------------------------
 */
/*
        Team RSA = new Team("RSA");
        Team Australia = new Team("Australia");
        database.teamMap.put("RSA", RSA);
        database.teamMap.put("Australia", Australia);

        Match match2 = new Match("Pollock-Border-2022-2",RSA, Australia, numOfPlayers, numOfOvers);
        database.matchMap.put("Pollock-Border-2022-2", match2);

        matchService.addPlayersInTeam(match2.getMatchName(), RSA, playersList1);
        matchService.addPlayersInTeam(match2.getMatchName(), Australia, playersList2);
        matchService.startMatch(match2.getMatchName(), RSA, Australia);

 */

    }

}
