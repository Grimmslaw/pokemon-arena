package com.grimmslaw.pokemon.model;

import com.grimmslaw.pokemon.pokemon.AbstractPokemon;

public interface DamageDealing {

    void applyDamage(AbstractPokemon target, int damageToDeal);

}
