package dev.chearcode;

import java.util.Random;

public class Dice {
    private static final int MIN_VALUE = 1;
    private static final int MAX_VALUE = 6;
    private final Random random;
    private int topValue;

    public Dice() {
        this.random = new Random();
        roll();
    }

    public void roll() {
        topValue = random.nextInt(MIN_VALUE, MAX_VALUE + MIN_VALUE);
    }

    public int getTopValue() {
        return topValue;
    }
}
