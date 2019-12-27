package com.grimmslaw.pokemon.model;

public class Duration {

    private int startingDuration;
    private int currentDuration;

    // empty constructor intentionally excluded

    public Duration(int startingDuration) {
        this.startingDuration = startingDuration;
        this.currentDuration = startingDuration;
    }

    // TODO revisit if accessors can be removed
    public int getStartingDuration() {
        return startingDuration;
    }

    public int getCurrentDuration() {
        return currentDuration;
    }

    public boolean isEndingNextTurn() {
        return currentDuration == 1;
    }

    public boolean isActive() {
        return currentDuration > 0;
    }

    public void tick() {
        if (currentDuration > 0) {
            currentDuration--;
        }
    }

}
