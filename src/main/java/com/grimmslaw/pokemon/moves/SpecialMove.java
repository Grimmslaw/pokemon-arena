package com.grimmslaw.pokemon.moves;

import com.grimmslaw.pokemon.types.Type;

/**
 * TODO
 *
 * @author wesrickey
 * @since 0.0.1
 */
public class SpecialMove extends Move {

    public SpecialMove(String name, Type type, PowerPoints pp, int power, int accuracy, int priority) {
        super(name, type, pp, power, accuracy, priority);
    }

    public SpecialMove(String name, Type type, int basePP, int maxPP, int power, int accuracy, int priority) {
        super(name, type, basePP, maxPP, power, accuracy, priority);
    }

}
