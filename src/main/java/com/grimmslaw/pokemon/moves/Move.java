package com.grimmslaw.pokemon.moves;

import com.grimmslaw.pokemon.battle.Weather;
import com.grimmslaw.pokemon.pokemon.AbstractPokemon;
import com.grimmslaw.pokemon.pokemon.DualTypePokemon;
import com.grimmslaw.pokemon.pokemon.SingleTypePokemon;
import com.grimmslaw.pokemon.types.Type;

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

    public Move(String name, Type type, PowerPoints pp, int power, int accuracy, int priority) {
        setName(name);
        setType(type);
        setPp(pp);
        setPower(power);
        setAccuracy(accuracy);
        setPriority(priority);
    }

    public Move(String name, Type type, int basePP, int maxPP, int power, int accuracy, int priority) {
        setName(name);
        setType(type);
        setPp(new PowerPoints(basePP, maxPP));
        setPower(power);
        setAccuracy(accuracy);
        setPriority(priority);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public PowerPoints getPp() {
        return pp;
    }

    public void setPp(PowerPoints pp) {
        this.pp = pp;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public double calculateTypeEffectiveness(AbstractPokemon defender) {
        if (defender instanceof SingleTypePokemon) {
            return getType().checkEffect(defender.getType());
        } else if (defender instanceof DualTypePokemon) {
            return getType().checkEffect(defender.getType(), ((DualTypePokemon) defender).getTypeSecond());
        }
        return 1.0;
    }

}
