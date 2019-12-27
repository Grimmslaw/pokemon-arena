package com.grimmslaw.pokemon.statuses.containers;

import com.grimmslaw.pokemon.exceptions.statuses.containers.StatusesException;
import com.grimmslaw.pokemon.exceptions.statuses.TurnableNumTargetException;
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
public class VolatileBattleStatusContainer extends AbstractStatusContainer implements Turnable {

    private ReplacingDeque<VolatileBattleStatusType> statuses;

    public enum VolatileBattleStatusType implements Effectable {

        // TODO
        NONE(-1, (target) -> {}, () -> {}),
        BRACING(-1, (target) -> {}, () -> {}),
        CHARGING(-1, (target) -> {}, () -> {}),
        CENTER_OF_ATTENTION(-1, (target) -> {}, () -> {}),
        DEFENSE_CURL(-1, (target) -> {}, () -> {}),
        ROOTING(-1, (target) -> {}, () -> {}),
        MAGIC_COAT(-1, (target) -> {}, () -> {}),
        MINIMIZE(-1, (target) -> {}, () -> {}),
        PROTECTION(-1, (target) -> {}, () -> {}),
        RECHARGING(-1, (target) -> {}, () -> {}),
        SEMI_INVULNERABLE(-1, (target) -> {}, () -> {}),
        SUBSTITUTE(-1, (target) -> {}, () -> {}),
        TAKING_AIM(-1, (target) -> {}, () -> {}),
        WITHDRAWING(-1, (target) -> {}, () -> {});

        VolatileBattleStatusType(int startingDuration, Consumer<AbstractPokemon> applyEffectMethodToSet,
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

    public VolatileBattleStatusContainer() {
        this.statuses = new ReplacingDeque<>();
    }

    public ReplacingDeque<VolatileBattleStatusType> getCurrentStatuses() {
        return statuses;
    }

    @Override
    public void applyEffect(AbstractPokemon... targets) throws TurnableNumTargetException {
        for (VolatileBattleStatusType status : statuses) {
            status.applyEffect(targets);
        }
    }

    @Override
    public void tick() {
        for (VolatileBattleStatusType status : statuses) {
            status.tick();
        }
    }

    @Override
    public void addStatus(Effectable statusToAdd) throws StatusesException {
        if (statusToAdd instanceof VolatileBattleStatusType) {
            getCurrentStatuses().add((VolatileBattleStatusType) statusToAdd);
        } else {
            throw new StatusesException();
        }
    }

    @Override
    public boolean hasStatus(Effectable status) {
        if (status instanceof VolatileBattleStatusType) {
            return getCurrentStatuses().contains(status);
        }

        return false;
    }

}
