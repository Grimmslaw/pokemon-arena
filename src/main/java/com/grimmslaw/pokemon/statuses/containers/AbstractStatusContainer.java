package com.grimmslaw.pokemon.statuses.containers;

import com.grimmslaw.pokemon.exceptions.statuses.containers.StatusesException;
import com.grimmslaw.pokemon.statuses.Effectable;

public abstract class AbstractStatusContainer {

    public abstract void addStatus(Effectable statusToAdd) throws StatusesException;

    public abstract boolean hasStatus(Effectable status);

}
