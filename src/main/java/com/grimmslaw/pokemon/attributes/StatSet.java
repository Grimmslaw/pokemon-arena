package com.grimmslaw.pokemon.attributes;

import java.util.Objects;
import java.util.StringJoiner;

import com.grimmslaw.pokemon.constants.Statistics.Stat;
import com.grimmslaw.pokemon.model.StatMap;

/**
 * TODO
 *
 * @author wesrickey
 * @since 0.0.1
 */
public class StatSet {

    private StatMap<Double> stats = new StatMap<>();

    /**
     * Empty constructor setting each stat to 0.0.
     */
    public StatSet() {
        this(0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
    }

    /**
     * Constructs a StatSet object, setting each stat's value to the given corresponding number.
     *
     * @param hp				the pokemon's hit points
     * @param attack			the pokemon's attack level
     * @param defense			the pokemon's defense level
     * @param specialAttack		the pokemon's special attack level
     * @param specialDefense	the pokemon's special defense level
     * @param speed				the pokemon's speed
     */
    public StatSet(double hp, double attack, double defense, double specialAttack,
                   double specialDefense, double speed) {
        setOneStat(Stat.HIT_POINTS, hp);
        setOneStat(Stat.ATTACK, attack);
        setOneStat(Stat.DEFENSE, defense);
        setOneStat(Stat.SPECIAL_ATTACK, specialAttack);
        setOneStat(Stat.SPECIAL_DEFENSE, specialDefense);
        setOneStat(Stat.SPEED, speed);
    }

    /**
     * Gets the value of the stat with the given name.
     *
     * @param statToGet	the name of the stat whose value should be retrieved
     * @return	the given stat's value
     */
    public double get(Stat statToGet) {
        return stats.get(statToGet);
    }

    public StatMap<Double> getStats() {
        return stats;
    }

    /**
     * Sets the stat with the given name to the given value.
     *
     * @param stat	the stat to set
     * @param value	the value to set the given stat to
     */
    public void setOneStat(Stat stat, double value) {
        getStats().put(stat, value);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", StatSet.class.getSimpleName() + "[", "]")
                .add("stats=" + stats)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatSet statSet = (StatSet) o;
        return Objects.equals(stats, statSet.stats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stats);
    }
}
