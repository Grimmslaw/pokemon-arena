package com.grimmslaw.pokemon.teams;

import com.grimmslaw.pokemon.exceptions.teams.TeamsException;
import com.grimmslaw.pokemon.pokemon.Pokemon;
import com.grimmslaw.pokemon.util.Utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Team implements List<Pokemon> {

    private static final Logger logger = LogManager.getLogger(Team.class);

    private List<Pokemon> teamSlots;
    private Pokemon activePokemon;

    public Team() {
        teamSlots = Utilities.initListWithRepeatedValue(null, 6);
    }

    public Team(List<Pokemon> teamToSet) throws TeamsException {
        if (teamToSet.size() > 0 && teamToSet.size() < 7) {
            teamSlots = teamToSet;
        }

        logger.warn("Team must be of a size in the range [0,6]. Actual size=" + teamToSet.size());
        throw new TeamsException();
    }

    public Team(Pokemon... pokemonToSet) {
        teamSlots = Utilities.initListWithValues(pokemonToSet);
    }

    public Pokemon getActivePokemon() {
        return activePokemon;
    }

    public Pokemon makePokemonActive(Pokemon pokemon) {
        if (this.contains(pokemon)) {
            Pokemon switchedFrom = getActivePokemon();
            activePokemon = pokemon;
            logger.info("Switched active pokemon from pokemon=" + switchedFrom + " to pokemon=" + pokemon);
            return switchedFrom;
        } else {
            logger.warn("Can't switch active pokemon, as pokemon=" + pokemon + " is not on the Team.");
            return null;
        }
    }


    // interface fulfillment below

    /**
     * Determines how large this {@code Team} is by how many slots are filled.
     *
     * The number returned by this method should always be in the range [0,6].
     *
     * @return the current size of this {@code Team}
     */
    @Override
    public int size() {
        int slotsFilled = 0;
        for (Pokemon slot : teamSlots) {
            if (slot != null) {
                slotsFilled++;
            }
        }
        return slotsFilled;
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
    public Iterator<Pokemon> iterator() {
        return teamSlots.iterator();
    }

    @Override
    public Object[] toArray() {
        return teamSlots.toArray();
    }

    // TODO: figure out why `'Pokemon[]' expected, 'T[]' found`
    @Override
    public <T> T[] toArray(T[] ts) {
        return teamSlots.toArray(ts);
    }

    @Override
    public boolean add(Pokemon pokemon) {
        if (this.size() < 6) {
            return teamSlots.add(pokemon);
        }

        logger.info("Team already full. Can't add pokemon=" + pokemon);
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
    public boolean addAll(Collection<? extends Pokemon> collection) {
        if (this.size() + collection.size() < 7) {
            return teamSlots.addAll(collection);
        }

        logger.info("Collection="
                + collection
                + " can't be added to Team because of size limit. Size before adding is: "
                + this.size());
        return false;
    }

    @Override
    public boolean addAll(int i, Collection<? extends Pokemon> collection) {
        if (this.size() + collection.size() < 7) {
            return teamSlots.addAll(i, collection);
        }

        logger.info("Collection="
                + collection
                + " can't be added to Team because of size limit. Size before adding is: "
                + this.size());
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
        teamSlots.clear();
    }

    @Override
    public Pokemon get(int i) {
        return teamSlots.get(i);
    }

    @Override
    public Pokemon set(int i, Pokemon pokemon) {
        // TODO: this needs some checking around it
        return teamSlots.set(i, pokemon);
    }

    @Override
    public void add(int i, Pokemon pokemon) {
        if (this.size() < 6) {
            teamSlots.add(i, pokemon);
        } else {
            logger.info("Team already full; can't add pokemon=" + pokemon);
        }
    }

    @Override
    public Pokemon remove(int i) {
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
    public ListIterator<Pokemon> listIterator() {
        return teamSlots.listIterator();
    }

    @Override
    public ListIterator<Pokemon> listIterator(int i) {
        return teamSlots.listIterator(i);
    }

    @Override
    public List<Pokemon> subList(int i, int i1) {
        return teamSlots.subList(i, i1);
    }
}
