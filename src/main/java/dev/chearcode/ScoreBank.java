package dev.chearcode;

import dev.chearcode.player.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScoreBank {
    private final Map<Player, Integer> bank = new HashMap<>();

    public ScoreBank() {
    }

    public ScoreBank(List<Player> players) {
        players.forEach(this::addPlayer);
    }

    public void addPlayer(Player player) {
        if (bank.containsKey(player)) {
            throw new IllegalArgumentException("Player already exists: " + player);
        }
        bank.put(player, 0);
    }

    public int getScore(Player player) {
        validate(player);
        return bank.get(player);
    }

    public void sumScore(Player player, int score) {
        validate(player);
        int currentScore = bank.get(player);
        int newScore = currentScore + score;
        bank.put(player, newScore);
    }

    private void validate(Player player) {
        if (!bank.containsKey(player)) {
            throw new IllegalArgumentException("Score does not exists for player: " + player);
        }
    }

    public void resetScore(Player player) {
        validate(player);
        bank.put(player, 0);
    }
}
