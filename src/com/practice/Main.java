package com.practice;

import com.practice.model.Match;
import com.practice.model.Team;
import com.practice.repository.Database;
import com.practice.service.MatchService;

import java.util.Arrays;
import java.util.Scanner;

public class  Main {

    public static void main(String[] args) {
	// write your code here
        Database database = Database.getInstance();
        Scanner sc = new Scanner(System.in);
        int numOfPlayers = sc.nextInt();
        int numOfOvers = sc.nextInt();

        Team India = new Team("India");
        Team England = new Team("England");
        database.teamMap.put("India", India);
        database.teamMap.put("England", England);

        Match match1 = new Match("Border-Gavaskar-2022-1",India, England, numOfPlayers, numOfOvers);
        database.matchMap.put("Border-Gavaskar-2022-1", match1);

        MatchService matchService = new MatchService();
        matchService.addPlayersInTeam(match1.getMatchName(), India,Arrays.asList("P1","P2","P3","P4","P5"));
        matchService.startMatch(match1.getMatchName(), India);
    }
}
