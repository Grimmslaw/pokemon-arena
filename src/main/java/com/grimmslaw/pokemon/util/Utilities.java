package com.grimmslaw.pokemon.util;

import java.util.*;

public class Utilities {

    public static <E> List<E> initListWithValues(E[] values) {
        return new ArrayList<>(Arrays.asList(values));
    }

    public static <E> List<E> initListWithRepeatedValue(E value, int numberOfRepetitions) {
        List<E> listToReturn = new ArrayList<>();
        for (int i = 0; i < numberOfRepetitions; i++) {
            listToReturn.add(value);
        }
        return listToReturn;
    }

    public static <K, V> Map<K, V> initMapFromTwoArgs(K arg1, V arg2) {
        Map<K, V> mapToReturn = new HashMap<>();
        mapToReturn.put(arg1, arg2);
        return mapToReturn;
    }

}
