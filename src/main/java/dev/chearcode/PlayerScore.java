package dev.chearcode;

public class PlayerScore {
    private int guaranteedPoints = 0;
    private int turnPoints = 0;

    public int getGuaranteedPoints() {
        return guaranteedPoints;
    }

    public int getTurnPoints() {
        return turnPoints;
    }

    public int getCurrentPoints() {
        return guaranteedPoints + turnPoints;
    }

    public void resetTurnPoints() {
        turnPoints = 0;
    }

    public void persistMovePoints() {
        guaranteedPoints += turnPoints;
        resetTurnPoints();
    }

    public void addTurnPoints(int turnPoints) {
        this.turnPoints += turnPoints;
    }
}
