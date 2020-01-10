package com.grimmslaw.pokemon.model;

import java.util.*;

import com.grimmslaw.pokemon.constants.Statistics.Stat;
import com.grimmslaw.pokemon.util.PrettyPrintable;

/**
 * TODO
 *
 * @author wesrickey
 * @since 0.0.1
 * @param <E>
 */
public class StatMap<E> implements Map<Stat, E>, PrettyPrintable {

    private Map<Stat, E> innerMap;

    /**
     * TODO
     */
    public StatMap() {
        innerMap = new HashMap<>();
    }

    public static StatMap<Integer> getIntegerStatMap(int hp, int attack, int defense, int spAttack, int spDefense, int speed) {
        StatMap<Integer> intStatMap = new StatMap<>();
        intStatMap.put(Stat.HIT_POINTS, hp);
        intStatMap.put(Stat.ATTACK, attack);
        intStatMap.put(Stat.DEFENSE, defense);
        intStatMap.put(Stat.SPECIAL_ATTACK, spAttack);
        intStatMap.put(Stat.SPECIAL_DEFENSE, spDefense);
        intStatMap.put(Stat.SPEED, speed);
        return intStatMap;
    }

    @Override
    public int size() {
        return innerMap.size();
    }

    @Override
    public boolean isEmpty() {
        return innerMap.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return innerMap.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return innerMap.containsValue(value);
    }

    @Override
    public E get(Object key) {
        return innerMap.get(key);
    }

    @Override
    public E put(Stat key, E value) {
        return innerMap.put(key, value);
    }

    @Override
    public E remove(Object key) {
        return innerMap.remove(key);
    }

    @Override
    public void putAll(Map<? extends Stat, ? extends E> m) {
        innerMap.putAll(m);
    }

    @Override
    public void clear() {
        innerMap.clear();
    }

    @Override
    public Set<Stat> keySet() {
        return innerMap.keySet();
    }

    @Override
    public Collection<E> values() {
        return innerMap.values();
    }

    @Override
    public Set<Entry<Stat, E>> entrySet() {
        return innerMap.entrySet();
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", StatMap.class.getSimpleName() + "[", "]")
                .add("innerMap=" + innerMap)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatMap<?> statMap = (StatMap<?>) o;
        return Objects.equals(innerMap, statMap.innerMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(innerMap);
    }

}
