package com.grimmslaw.pokemon.statuses;

import com.grimmslaw.pokemon.exceptions.statuses.TurnableNumTargetException;
import com.grimmslaw.pokemon.pokemon.Pokemon;

/**
 * TODO
 *
 * @author wesrickey
 * @since 0.0.1
 */
public interface Turnable {

    void applyEffect(Pokemon... targets) throws TurnableNumTargetException;

    void tick();

}
