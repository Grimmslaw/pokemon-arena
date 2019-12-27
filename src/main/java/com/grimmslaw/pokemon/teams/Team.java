package com.grimmslaw.pokemon.teams;

import com.grimmslaw.pokemon.exceptions.teams.TeamsException;
import com.grimmslaw.pokemon.pokemon.AbstractPokemon;
import com.grimmslaw.pokemon.util.Utilities;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Team implements List<AbstractPokemon> {

    private List<AbstractPokemon> teamSlots;

    public Team() {
        teamSlots = Utilities.initListWithRepeatedValue(null, 6);
    }

    public Team(List<AbstractPokemon> teamToSet) throws TeamsException {
        if (teamToSet.size() > 0 && teamToSet.size() < 7) {
            teamSlots = teamToSet;
        }
        throw new TeamsException();
    }

    public Team(AbstractPokemon... pokemonToSet) {
        teamSlots = Utilities.initListWithValues(pokemonToSet);
    }

    public int numberSlotsFilled() {
        int slotsFilled = 0;
        for (AbstractPokemon slot : teamSlots) {
            if (slot != null) {
                slotsFilled++;
            }
        }
        return slotsFilled;
    }

    // interface fulfillment below
    @Override
    public int size() {
        return 6;
    }

    @Override
    public boolean isEmpty() {
        return teamSlots.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return teamSlots.contains(o);
    }

    @Override
    public Iterator<AbstractPokemon> iterator() {
        return teamSlots.iterator();
    }

    @Override
    public Object[] toArray() {
        return teamSlots.toArray();
    }

    // TODO: what?
    @Override
    public <T> T[] toArray(T[] ts) {
        return teamSlots.toArray(ts);
    }

    @Override
    public boolean add(AbstractPokemon abstractPokemon) {
        if (this.numberSlotsFilled() < 6) {
            return teamSlots.add(abstractPokemon);
        }
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return teamSlots.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        return teamSlots.containsAll(collection);
    }

    @Override
    public boolean addAll(Collection<? extends AbstractPokemon> collection) {
        if (this.numberSlotsFilled() + collection.size() < 7) {
            return teamSlots.addAll(collection);
        }
        return false;
    }

    @Override
    public boolean addAll(int i, Collection<? extends AbstractPokemon> collection) {
        if (this.numberSlotsFilled() + collection.size() < 7) {
            return teamSlots.addAll(i, collection);
        }
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        return teamSlots.removeAll(collection);
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        return teamSlots.retainAll(collection);
    }

    @Override
    public void clear() {

    }

    @Override
    public AbstractPokemon get(int i) {
        return null;
    }

    @Override
    public AbstractPokemon set(int i, AbstractPokemon abstractPokemon) {
        // TODO: this needs some checking around it
        return teamSlots.set(i, abstractPokemon);
    }

    @Override
    public void add(int i, AbstractPokemon abstractPokemon) {
        if (this.numberSlotsFilled() < 6) {
            teamSlots.add(i, abstractPokemon);
        }
    }

    @Override
    public AbstractPokemon remove(int i) {
        return teamSlots.remove(i);
    }

    @Override
    public int indexOf(Object o) {
        return teamSlots.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return teamSlots.lastIndexOf(o);
    }

    @Override
    public ListIterator<AbstractPokemon> listIterator() {
        return teamSlots.listIterator();
    }

    @Override
    public ListIterator<AbstractPokemon> listIterator(int i) {
        return teamSlots.listIterator(i);
    }

    @Override
    public List<AbstractPokemon> subList(int i, int i1) {
        return teamSlots.subList(i, i1);
    }
}
