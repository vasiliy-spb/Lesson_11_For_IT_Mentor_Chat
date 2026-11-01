package dev.chearcode;

import dev.chearcode.dialog.Dialog;
import dev.chearcode.dialog.StringDialog;
import dev.chearcode.dialog.VoidDialog;
import dev.chearcode.player.Player;

import java.util.List;

public class Game {
    private static final int RESET_VALUE = 1;
    private static final int WIN_SCORE = 100;
    private static final String DIALOG_KEY_ROLL = "R";
    private static final String DIALOG_KEY_SKIP = "S";
    private static final String DIALOG_TITLE = "Введите: %s - бросать кубик, %s - передать ход".formatted(DIALOG_KEY_ROLL, DIALOG_KEY_SKIP);
    private static final String DIALOG_ERROR = "Неправильный ввод";
    private static final List<String> DIALOG_KEYS = List.of(DIALOG_KEY_ROLL, DIALOG_KEY_SKIP);
    public static final String PASS_DIALOG_TITLE = "ДЛЯ ПЕРЕДАЧИ ХОДА НАЖМИТЕ ENTER";
    private final PlayerRingBuffer playerBuffer;
    private final ScoreBank guaranteedBank;
    private final ScoreBank turnBank;
    private final Dialog<String> turnDialog;
    private final Dialog<Void> passTurnDialog;
    private final Dice dice;

    public Game(List<Player> players) {
        this.playerBuffer = new PlayerRingBuffer(players);
        this.guaranteedBank = new ScoreBank(players);
        this.turnBank = new ScoreBank(players);
        this.turnDialog = new StringDialog(DIALOG_TITLE, DIALOG_ERROR, DIALOG_KEYS);
        this.passTurnDialog = new VoidDialog(PASS_DIALOG_TITLE);
        this.dice = new Dice();
    }

    public void start() {
        System.out.println("* СВИНЬЯ *");
        System.out.printf("Игра до %d очков %n", WIN_SCORE);
        System.out.println();

        while (true) {
            Player currentPlayer = playerBuffer.getCurrent();
            System.out.println("ХОД ИГРОКА: " + currentPlayer.getName());
            System.out.println("----------");
            System.out.println();

            makeMove(currentPlayer);

            int turnScore = turnBank.getScore(currentPlayer);
            guaranteedBank.sumScore(currentPlayer, turnScore);
            turnBank.resetScore(currentPlayer);

            if (isWon(currentPlayer)) {
                System.out.println("ОЧКИ: " + guaranteedBank.getScore(currentPlayer));
                System.out.println(currentPlayer.getName() + " ВЫИГРАЛ!");
                break;
            }

            playerBuffer.next();
        }
    }

    private boolean isWon(Player player) {
        int guaranteedScore = guaranteedBank.getScore(player);
        int turnScore = turnBank.getScore(player);
        return guaranteedScore + turnScore >= WIN_SCORE;
    }

    private void makeMove(Player player) {
        turnBank.resetScore(player);

        while (!isWon(player)) {
            showTurnInfo(player);
            System.out.println();

            String key = turnDialog.input();

            if (key.equals(DIALOG_KEY_SKIP)) {
                break;
            }

            dice.roll();

            int value = dice.getTopValue();
            System.out.println("Бросили кубик, результат: " + value);
            System.out.println();

            if (value == RESET_VALUE) {
                turnBank.resetScore(player);
                System.out.println("ОЧКИ ЗА ХОД СГОРЕЛИ!");
                passTurnDialog.input();
                break;
            }

            turnBank.sumScore(player, value);
        }
    }

    private void showTurnInfo(Player player) {
        int guaranteedScore = guaranteedBank.getScore(player);
        int turnScore = turnBank.getScore(player);
        int totalScore = guaranteedScore + turnScore;

        System.out.printf("%s, всего очков: %d %n", player.getName(), totalScore);
        System.out.printf("Несгораемые очки : %d %n", guaranteedScore);
        System.out.printf("Очки за ход      : %d %n", turnScore);
    }
}
