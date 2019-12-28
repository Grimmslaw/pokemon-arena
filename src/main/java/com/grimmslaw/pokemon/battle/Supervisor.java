package com.grimmslaw.pokemon.battle;

import com.grimmslaw.pokemon.model.DamageDealing;
import com.grimmslaw.pokemon.pokemon.AbstractPokemon;

public class Supervisor implements DamageDealing {

    private static Supervisor _supervisor;

    private Supervisor() {}

    public static Supervisor getInstance() {
        if (_supervisor == null) {
            _supervisor = new Supervisor();
        }
        return _supervisor;
    }

    @Override
    public void applyDamage(AbstractPokemon target, int damageToDeal) {
        // TODO: implement any necessary checks here
        if (target.receiveDamage(damageToDeal) == 0.0) {
            target.doFaint();
        }
    }
}
