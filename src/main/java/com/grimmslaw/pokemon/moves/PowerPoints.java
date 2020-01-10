package com.grimmslaw.pokemon.moves;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.grimmslaw.pokemon.util.PrettyPrintable;

import java.util.StringJoiner;

import static com.grimmslaw.pokemon.constants.Constants.GSON_BUILDER;

/**
 * TODO
 *
 * @author wesrickey
 * @since 0.0.1
 */
public class PowerPoints implements PrettyPrintable {

    private final int base;
    private final int max;
    private int current;
    private int currentMax;

    public PowerPoints(int base, int current, int max, int currentMax) {
        this.base = base;
        this.current = current;
        this.max = max;
        this.currentMax = currentMax;
    }

    public PowerPoints(int base, int max) {
        this(base, base, max, max);
    }

    public PowerPoints(String json, Gson gson) {
        PowerPoints ppToCopy = gson.fromJson(json, PowerPoints.class);
        this.base = ppToCopy.getBase();
        this.current = ppToCopy.getCurrent();
        this.max = ppToCopy.getMax();
        this.currentMax = ppToCopy.getCurrentMax();
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

    @Override
    public String toString() {
        return new StringJoiner(", ", PowerPoints.class.getSimpleName() + "[", "]")
                .add("base=" + base)
                .add("max=" + max)
                .add("current=" + current)
                .add("currentMax=" + currentMax)
                .toString();
    }
}
