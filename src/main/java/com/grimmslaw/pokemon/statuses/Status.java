package com.grimmslaw.pokemon.statuses;

import com.grimmslaw.pokemon.exceptions.statuses.TurnableNumTargetException;
import com.grimmslaw.pokemon.pokemon.Pokemon;
import com.grimmslaw.pokemon.statuses.containers.NonVolatileStatusContainer;
import com.grimmslaw.pokemon.statuses.containers.VolatileBattleStatusContainer;
import com.grimmslaw.pokemon.statuses.containers.VolatileStatusContainer;

import java.util.Objects;
import java.util.StringJoiner;

public class Status implements Turnable {

    private NonVolatileStatusContainer nonVolatileStatus;
    private VolatileStatusContainer volatileStatuses;
    private VolatileBattleStatusContainer volatileBattleStatuses;

    public Status() {
        nonVolatileStatus = new NonVolatileStatusContainer();
        volatileStatuses = new VolatileStatusContainer();
        volatileBattleStatuses = new VolatileBattleStatusContainer();
    }

    public NonVolatileStatusContainer getNonVolatileStatus() {
        return nonVolatileStatus;
    }

    public NonVolatileStatusContainer.NonVolatileStatusType getCurrentNonVolatileStatus() {
        return nonVolatileStatus.getCurrentStatus();
    }

    public VolatileStatusContainer getVolatileStatuses() {
        return volatileStatuses;
    }

    public VolatileBattleStatusContainer getVolatileBattleStatuses() {
        return volatileBattleStatuses;
    }

    @Override
    public void applyEffect(Pokemon... targets) throws TurnableNumTargetException {
        getNonVolatileStatus().applyEffect(targets);
        getVolatileBattleStatuses().applyEffect(targets);
        getVolatileStatuses().applyEffect(targets);
    }

    @Override
    public void tick() {
        getNonVolatileStatus().tick();
        getVolatileStatuses().tick();
        getVolatileBattleStatuses().tick();
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Status.class.getSimpleName() + "[", "]")
                .add("nonVolatileStatus=" + nonVolatileStatus)
                .add("volatileStatuses=" + volatileStatuses)
                .add("volatileBattleStatuses=" + volatileBattleStatuses)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Status status = (Status) o;
        return Objects.equals(nonVolatileStatus, status.nonVolatileStatus) &&
                Objects.equals(volatileStatuses, status.volatileStatuses) &&
                Objects.equals(volatileBattleStatuses, status.volatileBattleStatuses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nonVolatileStatus, volatileStatuses, volatileBattleStatuses);
    }
}
