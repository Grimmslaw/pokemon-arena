package com.grimmslaw.pokemon.statuses;

import com.grimmslaw.pokemon.exceptions.statuses.TurnableNumTargetException;
import com.grimmslaw.pokemon.pokemon.AbstractPokemon;
import com.grimmslaw.pokemon.statuses.containers.NonVolatileStatusContainer;
import com.grimmslaw.pokemon.statuses.containers.VolatileBattleStatusContainer;
import com.grimmslaw.pokemon.statuses.containers.VolatileStatusContainer;

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
    public void applyEffect(AbstractPokemon... targets) throws TurnableNumTargetException {
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

}
