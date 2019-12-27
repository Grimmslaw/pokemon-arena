package com.grimmslaw.pokemon.exceptions;

import com.grimmslaw.pokemon.constants.Statistics;

/**
 * TODO
 *
 * @author wesrickey
 * @since 0.0.1
 */
public class EVSlotsFullException extends EVException {

    private static final long serialVersionUID = -8580404453358661242L;

    public EVSlotsFullException(Class<?> clazz, String methodName, Statistics.Stat stat, int value, int totalEVs) {
        super("EVSlotsFullException in " + clazz.getName() + "#" + methodName + ", setting stat=" + stat + " to value=" + value + ", where totalEVs=" + totalEVs);
    }

}
