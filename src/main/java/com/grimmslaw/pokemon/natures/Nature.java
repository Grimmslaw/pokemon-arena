package com.grimmslaw.pokemon.natures;

import com.grimmslaw.pokemon.constants.Statistics;
import com.grimmslaw.pokemon.constants.Statistics.Stat;

/**
 * TODO
 *
 * @author wesrickey
 * @since 0.0.1
 */
public enum Nature {

    HARDY(1,1),	LONELY(1,2),	ADAMANT(1,3),	NAUGHTY(1,4),    BRAVE(1,5),
    BOLD(2,1),	DOCILE(2,2),	IMPISH(2,3),	    LAX(2,4),		RELAXED(2,5),
    MODEST(3,1),	MILD(3,2),	BASHFUL(3,3),	RASH(3,4),		QUIET(3,5),
    CALM(4,1), 	GENTLE(4,2),	CAREFUL(4,3),	QUIRKY(4,4),	    SASSY(4,5),
    TIMID(5,1), 	HASTY(5,2), 	JOLLY(5,3),		NAIVE(5,4),		SERIOUS(5,5);

    private final Stat helpful;
    private final Stat hindering;

    Nature(int helps, int hinders) {
        this.helpful = Statistics.STATS[helps-1];
        this.hindering = Statistics.STATS[hinders-1];
    }

    public double getMultiplierForStat(Stat stat) {
        if (stat == this.helpful) {
            return 1.1;
        } else if (stat == this.hindering) {
            return 0.9;
        } else {
            return 1.0;
        }
    }

}
