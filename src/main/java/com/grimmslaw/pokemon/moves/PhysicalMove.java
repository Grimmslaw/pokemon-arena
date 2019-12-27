package com.grimmslaw.pokemon.moves;

import com.grimmslaw.pokemon.types.Type;

/**
 * TODO
 *
 * @author wesrickey
 * @since 0.0.1
 */
public class PhysicalMove extends Move {

    public PhysicalMove(String name, Type type, PowerPoints pp, int power, int accuracy, int priority) {
        super(name, type, pp, power, accuracy, priority);
    }

    public PhysicalMove(String name, Type type, int basePP, int maxPP, int power, int accuracy, int priority) {
        super(name, type, basePP, maxPP, power, accuracy, priority);
    }

    private boolean doesDirectDamage = false;

    public boolean doesDirectDamage() {
        return doesDirectDamage;
    }

    public void setDirectDamage(boolean doesDirectDamage) {
        this.doesDirectDamage = doesDirectDamage;
    }

}
