package dev.chearcode;

public class TestMain1 {
    public static void main(String[] args) {
        Dice dice = new Dice();
        for (int i = 0; i < 200; i++) {
            dice.roll();
            System.out.println(dice.getTopValue());
        }
    }
}
