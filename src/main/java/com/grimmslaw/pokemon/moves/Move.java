package com.grimmslaw.pokemon.moves;

import com.grimmslaw.pokemon.pokemon.AbstractPokemon;
import com.grimmslaw.pokemon.pokemon.DualTypePokemon;
import com.grimmslaw.pokemon.pokemon.SingleTypePokemon;
import com.grimmslaw.pokemon.types.Type;
import com.grimmslaw.pokemon.util.MathUtilities;

import java.util.concurrent.ThreadLocalRandom;

/**
 * The parent class to all move classes.
 *
 * This class provides the basic mechanics that all move subclasses require.
 *
 * @author wesrickey
 * @since 0.0.1
 */
public abstract class Move {

    private String name;
    private Type type;
    private PowerPoints pp;
    private int power;
    private int accuracy;
    private int priority;
    private boolean hasEffect;

    public Move(String name, Type type, PowerPoints pp, int power, int accuracy, int priority, boolean hasEffect) {
        this.name = name;
        this.type = type;
        this.pp = pp;
        this.power = power;
        this.accuracy = accuracy;
        this.priority = priority;
        this.hasEffect = hasEffect;
    }

    public Move(String name, Type type, PowerPoints pp, int power, int accuracy, int priority) {
        this(name, type, pp, power, accuracy, priority, false);
    }

    public Move(String name, Type type, int basePP, int maxPP, int power, int accuracy, int priority) {
        this(name, type, new PowerPoints(basePP, maxPP), power, accuracy, priority);
    }

    public Move(String name, Type type, int basePP, int maxPP, int power, int accuracy, int priority, boolean hasEffect) {
        this(name, type, new PowerPoints(basePP, maxPP), power, accuracy, priority, hasEffect);
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public PowerPoints getPp() {
        return pp;
    }

    public int getPower() {
        return power;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public int getPriority() {
        return priority;
    }

    public boolean hasEffect() {
        return hasEffect;
    }

    public double calculateTypeEffectiveness(AbstractPokemon defender) {
        if (defender instanceof SingleTypePokemon) {
            return getType().checkEffect(defender.getType());
        } else if (defender instanceof DualTypePokemon) {
            return getType().checkEffect(defender.getType(), ((DualTypePokemon) defender).getTypeSecond());
        }
        return 1.0;
    }

    public boolean attackDoesHit(AbstractPokemon attacker, AbstractPokemon defender) {
        double thresholdToHit = MathUtilities.calculateHitThreshold(attacker, defender, this);
        int hitPercent = ThreadLocalRandom.current().nextInt(0, 101);
        return hitPercent <= thresholdToHit;
    }

    public void applyEffect() {
        // TODO: hasEffect() should be checked already by the time this method is called
        // TODO: apply the effect
    }

}
