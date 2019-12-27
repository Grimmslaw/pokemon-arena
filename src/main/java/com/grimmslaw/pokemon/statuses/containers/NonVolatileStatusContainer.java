package com.grimmslaw.pokemon.statuses.containers;

import java.util.function.Consumer;

import com.grimmslaw.pokemon.exceptions.statuses.containers.StatusSlotFullException;
import com.grimmslaw.pokemon.exceptions.statuses.containers.StatusesException;
import com.grimmslaw.pokemon.exceptions.statuses.TurnableNumTargetException;
import com.grimmslaw.pokemon.pokemon.AbstractPokemon;
import com.grimmslaw.pokemon.statuses.Effectable;
import com.grimmslaw.pokemon.model.Duration;
import com.grimmslaw.pokemon.statuses.Turnable;

public class NonVolatileStatusContainer extends AbstractStatusContainer implements Turnable {

    private NonVolatileStatusType currentStatus;

    public enum NonVolatileStatusType implements Effectable {

        // TODO
        NONE(-1, (target) -> {}, () -> {}),
        BURN(-1, (target) -> {}, () -> {}),
        FREEZE(-1, (target) -> {}, () -> {}),
        PARALYSIS(-1, (target) -> {}, () -> {}),
        POISON(-1, (target) -> {}, () -> {}),
        SLEEP(-1, (target) -> {}, () -> {});

        NonVolatileStatusType(int startingDuration, Consumer<AbstractPokemon> applyEffectMethodToSet,
                              Runnable tickMethodToSet) {
            duration = new Duration(startingDuration);
            applyEffectMethod = applyEffectMethodToSet;
            tickMethod = tickMethodToSet;
        }

        public Duration duration;
        public Consumer<AbstractPokemon> applyEffectMethod;
        public Runnable tickMethod;

        @Override
        public void applyEffect(AbstractPokemon... targets) throws TurnableNumTargetException {
            if (targets.length == 1) {
                applyEffectMethod.accept(targets[0]);
            } else {
                throw new TurnableNumTargetException();
            }
        }

        @Override
        public void tick() {
            tickMethod.run();
        }

        @Override
        public int getCurrentDuration() {
            return this.duration.getCurrentDuration();
        }

    }

    public NonVolatileStatusType getCurrentStatus() {
        return currentStatus;
    }

    private void setCurrentStatus(NonVolatileStatusType status) {
        this.currentStatus = status;
    }

    @Override
    public void applyEffect(AbstractPokemon... targets) throws TurnableNumTargetException {
        getCurrentStatus().applyEffect(targets);
    }

    @Override
    public void tick() {
        getCurrentStatus().tick();
    }

    @Override
    public void addStatus(Effectable statusToAdd) throws StatusesException {
        if (getCurrentStatus() != NonVolatileStatusType.NONE && getCurrentStatus().getCurrentDuration() > 0) {
            throw new StatusSlotFullException();
        }

        if (!(statusToAdd instanceof NonVolatileStatusType)) {
            throw new StatusesException();
        }

        setCurrentStatus((NonVolatileStatusType) statusToAdd);
    }

    @Override
    public boolean hasStatus(Effectable status) {
        return getCurrentStatus() == status;
    }

}
