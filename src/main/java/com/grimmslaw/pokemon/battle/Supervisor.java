package com.grimmslaw.pokemon.battle;

import com.grimmslaw.pokemon.model.DamageDealing;
import com.grimmslaw.pokemon.pokemon.Pokemon;

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
    public void applyDamage(Pokemon target, int damageToDeal) {
        // TODO: implement any necessary checks here
        if (target.receiveDamage(damageToDeal) == 0.0) {
            target.doFaint();
        }
    }

    @Override
    public void makeFaint(Pokemon target) {
        // TODO: implement any necessary checks here
        target.doFaint();
    }
}
