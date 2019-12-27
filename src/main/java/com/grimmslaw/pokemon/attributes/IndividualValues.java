package com.grimmslaw.pokemon.attributes;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.grimmslaw.pokemon.constants.Statistics;
import com.grimmslaw.pokemon.constants.Statistics.Stat;

/**
 * Defines an IndividualValues object, with IVs corresponding to each stat and causing each stat to grow at a speed
 * relative to its value.
 *
 * Each value is determined at random.
 *
 * @author wesrickey
 * @since 0.0.1
 */
public class IndividualValues {

    private Map<Stat, Integer> valueMap;

    public IndividualValues() {
        setValues(generateIVs());
    }

    public IndividualValues(Map<Stat, Integer> initIVs) {
        setValues(initIVs);
    }

    public Map<Stat, Integer> getIVs() {
        return valueMap;
    }

    public void setValues(Map<Stat, Integer> valueMap) {
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
    public static Map<Stat, Integer> generateIVs() {
        Random rand = new Random();
        Map<Stat, Integer> ivsMap = new HashMap<>();
        for (Stat stat : Statistics.STATS) {
            ivsMap.put(stat, rand.nextInt(32));
        }
        return ivsMap;
    }
}
