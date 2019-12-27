package com.grimmslaw.pokemon.statuses;

import com.grimmslaw.pokemon.exceptions.statuses.TurnableNumTargetException;
import com.grimmslaw.pokemon.pokemon.AbstractPokemon;

/**
 * TODO
 *
 * @author wesrickey
 * @since 0.0.1
 */
public interface Turnable {

    void applyEffect(AbstractPokemon... targets) throws TurnableNumTargetException;

    void tick();

}
