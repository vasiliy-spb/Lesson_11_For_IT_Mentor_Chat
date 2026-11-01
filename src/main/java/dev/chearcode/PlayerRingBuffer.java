package dev.chearcode;

import dev.chearcode.player.Player;

import java.util.List;

public class PlayerRingBuffer {
    private List<Player> players;
    private int index;

    public PlayerRingBuffer(List<Player> players) {
        this.players = players;
    }

    public Player getCurrent() {
        return players.get(index);
    }

    public void next() {
        index++;
        if (index == players.size()) {
            index = 0;
        }
    }
}
