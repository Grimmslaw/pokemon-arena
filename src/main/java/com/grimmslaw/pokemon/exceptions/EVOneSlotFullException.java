package com.grimmslaw.pokemon.exceptions;

import com.grimmslaw.pokemon.constants.Statistics;

/**
 * TODO
 *
 * @author wesrickey
 * @since 0.0.1
 */
public class EVOneSlotFullException extends EVException {

    private static final long serialVersionUID = -9094899088855111486L;

    public EVOneSlotFullException(Class<?> clazz, String methodName, Statistics.Stat stat, int value) {
        super("EVOneSlotFullException in " + clazz.getName() + "#" + methodName + ", setting stat=" + stat + " to value=" + value);
    }

}
