package com.grimmslaw.pokemon.constants;

import com.grimmslaw.pokemon.pokemon.AbstractPokemon;

import java.util.Arrays;

/**
 * TODO
 *
 * @author wesrickey
 * @since 0.0.1
 */
public class Statistics {

    public enum Stat {
        // permanent
        HIT_POINTS("HP"),
        ATTACK("Attack"),
        DEFENSE("Defense"),
        SPECIAL_ATTACK("Special Attack"),
        SPECIAL_DEFENSE("Special Defense"),
        SPEED("Speed"),
        // battle
        ACCURACY("Accuracy"),
        EVASION("Evasion");

        public final String label;

        Stat(String label) {
            this.label = label;
        }

        @Override
        public String toString() {
            return this.label;
        }
    }

    public static final Stat[] STATS = {Stat.HIT_POINTS, Stat.ATTACK, Stat.DEFENSE, Stat.SPECIAL_ATTACK,
            Stat.SPECIAL_DEFENSE, Stat.SPEED};

    private static final double[] STAT_STAGE_MULTIPLIERS = {2.0/8.0, 2.0/7.0, 2.0/6.0, 2.0/5.0, 2.0/4.0, 2.0/3.0,
            3.0/2.0, 4.0/2.0, 5.0/2.0, 6.0/2.0, 7.0/2.0, 8.0/2.0};

    public static final Stat[] BATTLE_STATS = {Stat.ACCURACY, Stat.EVASION};

    private static final double[] BATTLE_STAT_STAGE_MULTIPLIERS = {33.0/100.0, 36.0/100.0, 43.0/100.0, 50.0/100.0,
            60.0/100.0, 75.0/100.0, 100.0/100.0, 133.0/100.0, 166.0/100.0, 200.0/100.0, 250.0/100.0, 266.0/100.0,
            300.0/100.0};

    public static double getMultiplierByStage(Stat stat, int stage) {
        if (Arrays.asList(STATS).contains(stat)) {
            return STAT_STAGE_MULTIPLIERS[stage+6];
        } else if (Arrays.asList(BATTLE_STATS).contains(stat)) {
            return BATTLE_STAT_STAGE_MULTIPLIERS[stage+6];
        }
        return 1.0;
    }

    public static double getStatValueIncludingStage(Stat stat, double value, int stage) {
        return value * getMultiplierByStage(stat, stage);
    }

    public static double getStatValueIncludingStage(Stat stat, AbstractPokemon pokemon) {
        return getStatValueIncludingStage(stat, pokemon.getCurrentStats().get(stat), pokemon.getStatStages().get(stat));
    }

}
