package com.grimmslaw.pokemon.model;

import com.grimmslaw.pokemon.util.PrettyPrintable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;
import java.util.StringJoiner;

public class Duration implements PrettyPrintable {

    private static final Logger logger = LogManager.getLogger(Duration.class);

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
        logger.trace(this.getClass().getCanonicalName() + "#tick called");

        if (currentDuration > 0) {
            currentDuration--;
        }
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Duration.class.getSimpleName() + "[", "]")
                .add("startingDuration=" + startingDuration)
                .add("currentDuration=" + currentDuration)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Duration duration = (Duration) o;
        return startingDuration == duration.startingDuration &&
                currentDuration == duration.currentDuration;
    }

    @Override
    public int hashCode() {
        return Objects.hash(startingDuration, currentDuration);
    }

}
