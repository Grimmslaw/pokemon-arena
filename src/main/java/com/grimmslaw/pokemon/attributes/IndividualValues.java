package com.grimmslaw.pokemon.attributes;

import java.util.Objects;
import java.util.Random;
import java.util.StringJoiner;

import com.grimmslaw.pokemon.constants.Statistics;
import com.grimmslaw.pokemon.constants.Statistics.Stat;
import com.grimmslaw.pokemon.model.StatMap;
import com.grimmslaw.pokemon.util.PrettyPrintable;

/**
 * Defines an IndividualValues object, with IVs corresponding to each stat and causing each stat to grow at a speed
 * relative to its value.
 *
 * Each value is determined at random.
 *
 * @author wesrickey
 * @since 0.0.1
 */
public class IndividualValues implements PrettyPrintable {

    private StatMap<Integer> valueMap;

    public IndividualValues() {
        setValues(generateIVs());
    }

    public IndividualValues(StatMap<Integer> initIVs) {
        setValues(initIVs);
    }

    public StatMap<Integer> getIVs() {
        return valueMap;
    }

    public void setValues(StatMap<Integer> valueMap) {
        this.valueMap = valueMap;
    }

    public int get(Stat stat) {
        return valueMap.get(stat);
    }

    /**
     * Generates each individual value by generating random numbers from 0 to 31, inclusive, for each stat.
     *
     * @return an initialized mapping of stats to their IVs
     */
    public static StatMap<Integer> generateIVs() {
        Random rand = new Random();
        StatMap<Integer> ivsMap = new StatMap<>();
        for (Stat stat : Statistics.STATS) {
            ivsMap.put(stat, rand.nextInt(32));
        }
        return ivsMap;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", IndividualValues.class.getSimpleName() + "[", "]")
                .add("valueMap=" + valueMap)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IndividualValues that = (IndividualValues) o;
        return Objects.equals(valueMap, that.valueMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valueMap);
    }

}
