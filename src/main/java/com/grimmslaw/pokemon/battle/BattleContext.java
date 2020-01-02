package com.grimmslaw.pokemon.battle;

public class BattleContext {

    private int numberOfTargets;
    private Weather currentWeather;
    private boolean isCritical;
    private double otherMultipliers;

    public BattleContext(int numberOfTargets, Weather currentWeather, boolean isCritical, double otherMultipliers) {
        this.numberOfTargets = numberOfTargets;
        this.currentWeather = currentWeather;
        this.isCritical = isCritical;
        this.otherMultipliers = otherMultipliers;
    }

    public int getNumberOfTargets() {
        return numberOfTargets;
    }

    public Weather getCurrentWeather() {
        return currentWeather;
    }

    public boolean isCritical() {
        return isCritical;
    }

    public double getOtherMultipliers() {
        return otherMultipliers;
    }
}
