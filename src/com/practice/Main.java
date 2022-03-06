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
	// write your code here
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

        Match match1 = new Match("Border-Gavaskar-2022-1",India, England, numOfPlayers, numOfOvers);
        database.matchMap.put("Border-Gavaskar-2022-1", match1);

        List<String> playersList = new ArrayList<>();
        for(int i = 1 ; i <= numOfPlayers; i++ ){
            playersList.add(new String("P"+String.valueOf(i)));
        }
        matchService.addPlayersInTeam(match1.getMatchName(), India, playersList);
        matchService.addPlayersInTeam(match1.getMatchName(), England, playersList);
        matchService.startMatch(match1.getMatchName(), India);
    }
}
