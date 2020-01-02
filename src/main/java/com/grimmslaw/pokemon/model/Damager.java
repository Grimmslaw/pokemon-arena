package com.grimmslaw.pokemon.model;

import com.grimmslaw.pokemon.pokemon.Pokemon;

public interface Damager {

    void applyDamageTo(Pokemon target, int damageToDeal);

    void makeFaint(Pokemon target);

}
