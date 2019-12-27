package com.grimmslaw.pokemon.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.grimmslaw.pokemon.constants.Statistics.Stat;

/**
 * TODO
 *
 * @author wesrickey
 * @since 0.0.1
 * @param <E>
 */
public class StatMap<E> implements Map<Stat, E> {

    private Map<Stat, E> innerMap;

    /**
     * TODO
     */
    public StatMap() {
        innerMap = new HashMap<>();
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

}
