package com.grimmslaw.pokemon.moves;

import com.grimmslaw.pokemon.pokemon.Pokemon;
import com.grimmslaw.pokemon.types.Type;

import java.util.Objects;
import java.util.StringJoiner;

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

    @Override
    public String toString() {
        return new StringJoiner(", ", PhysicalMove.class.getSimpleName() + "[", "]")
                .add("doesDirectDamage=" + doesDirectDamage)
                .add("super=" + super.toString())
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PhysicalMove that = (PhysicalMove) o;
        return doesDirectDamage == that.doesDirectDamage;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), doesDirectDamage);
    }
}
