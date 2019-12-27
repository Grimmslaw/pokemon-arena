package com.grimmslaw.pokemon.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

}
