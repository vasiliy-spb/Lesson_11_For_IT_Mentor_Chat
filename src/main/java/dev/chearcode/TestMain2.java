package dev.chearcode;

import dev.chearcode.player.RealPlayer;

public class TestMain2 {
    public static void main(String[] args) {
        ScoreBank bank = new ScoreBank();
        RealPlayer firstPlayer = new RealPlayer("First Player");
        bank.addPlayer(firstPlayer);
        System.out.println(bank.getScore(firstPlayer));

        bank.sumScore(firstPlayer, 5);
        System.out.println(bank.getScore(firstPlayer));

        bank.sumScore(firstPlayer, 5);
        bank.sumScore(firstPlayer, 5);
        bank.sumScore(firstPlayer, 5);
        System.out.println(bank.getScore(firstPlayer));

        bank.resetScore(firstPlayer);
        System.out.println(bank.getScore(firstPlayer));
    }
}
