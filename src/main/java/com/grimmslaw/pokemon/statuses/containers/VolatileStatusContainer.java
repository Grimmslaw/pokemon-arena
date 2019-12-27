package com.grimmslaw.pokemon.statuses.containers;

import com.grimmslaw.pokemon.exceptions.StatusesException;
import com.grimmslaw.pokemon.exceptions.TurnableNumTargetException;
import com.grimmslaw.pokemon.pokemon.AbstractPokemon;
import com.grimmslaw.pokemon.statuses.Effectable;
import com.grimmslaw.pokemon.model.Duration;
import com.grimmslaw.pokemon.statuses.Turnable;
import com.grimmslaw.pokemon.util.ReplacingDeque;

import java.util.function.Consumer;

/**
 *
 * @author Wes Rickey
 * @since 0.0.1
 */
public class VolatileStatusContainer extends AbstractStatusContainer implements Turnable {

    private ReplacingDeque<VolatileStatusType> statuses;

    public enum VolatileStatusType implements Effectable {

        // TODO
        BIND(-1, (target) -> {}, () -> {}),
        CANT_ESCAPE(-1, (target) -> {}, () -> {}),
        CONFUSION(-1, (target) -> {}, () -> {}),
        CURSE(-1, (target) -> {}, () -> {}),
        ENCORE(-1, (target) -> {}, () -> {}),
        FLINCH(-1, (target) -> {}, () -> {}),
        IDENTIFICATION(-1, (target) -> {}, () -> {}),
        INFATUATION(-1, (target) -> {}, () -> {}),
        LEECH_SEED(-1, (target) -> {}, () -> {}),
        NIGHTMARE(-1, (target) -> {}, () -> {}),
        PERISH_SONG(-1, (target) -> {}, () -> {}),
        TAUNT(-1, (target) -> {}, () -> {}),
        TORMENT(-1, (target) -> {}, () -> {});

        VolatileStatusType(int startingDuration, Consumer<AbstractPokemon> applyEffectMethodToSet,
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

    public VolatileStatusContainer() {
        this.statuses = new ReplacingDeque<>();
    }

    public ReplacingDeque<VolatileStatusType> getCurrentStatuses() {
        return statuses;
    }

    @Override
    public void applyEffect(AbstractPokemon... targets) throws TurnableNumTargetException {
        for (VolatileStatusType status : statuses) {
            status.applyEffect(targets);
        }
    }

    @Override
    public void tick() {
        for (VolatileStatusType status : statuses) {
            status.tick();
        }
    }

    @Override
    public void addStatus(Effectable statusToAdd) throws StatusesException {
        if (statusToAdd instanceof VolatileStatusType) {
            getCurrentStatuses().add((VolatileStatusType) statusToAdd);
        } else {
            throw new StatusesException();
        }
    }

    @Override
    public boolean hasStatus(Effectable status) {
        if (status instanceof VolatileStatusType) {
            return getCurrentStatuses().contains(status);
        }

        return false;
    }

}
