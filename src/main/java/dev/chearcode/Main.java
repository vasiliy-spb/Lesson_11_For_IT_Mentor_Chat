package dev.chearcode;

import dev.chearcode.player.Player;
import dev.chearcode.player.RealPlayer;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Player> players = List.of(new RealPlayer("First Player"), new RealPlayer("Second Player"));
        Game game = new Game(players);
        game.start();
    }
}
