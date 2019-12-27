package com.grimmslaw.pokemon.constants;

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

    public static final Stat[] BATTLE_STATS = {Stat.ACCURACY, Stat.EVASION};

}
