package dev.chearcode.player;

import dev.chearcode.PlayerScore;

public abstract class Player {
    private final String name;
    private final PlayerScore playerScore;

    public Player(String name) {
        this.name = name;
        this.playerScore = new PlayerScore();
    }

    public String getName() {
        return name;
    }

    public PlayerScore getPlayerScore() {
        return playerScore;
    }
}
