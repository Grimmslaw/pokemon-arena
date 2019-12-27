package com.grimmslaw.pokemon.moves;

/**
 * TODO
 *
 * @author wesrickey
 * @since 0.0.1
 */
public class PowerPoints {

    private final int base;
    private final int max;
    private int current;
    private int currentMax;

    public PowerPoints(int base, int max) {
        this.base = base;
        this.max = max;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int newCurrent, boolean override) {
        if (newCurrent <= this.max || override) {
            this.current = newCurrent;
        }
    }

    public int getCurrentMax() {
        return currentMax;
    }

    public void setCurrentMax(int currentMax) {
        this.currentMax = currentMax;
    }

    public int getBase() {
        return base;
    }

    public int getMax() {
        return max;
    }

    public void reset() {
        setCurrent(getBase(), true);
        setCurrentMax(getMax());
    }

}
