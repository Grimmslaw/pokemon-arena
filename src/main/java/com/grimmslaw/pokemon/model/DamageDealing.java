package com.grimmslaw.pokemon.model;

import com.grimmslaw.pokemon.pokemon.Pokemon;

public interface DamageDealing {

    void applyDamage(Pokemon target, int damageToDeal);

    void makeFaint(Pokemon target);

}
