package com.grimmslaw.pokemon.model;

public interface Damageable {

    /**
     * Receives damage from a {@linkplain DamageDealing} object, setting current hp to the result of the current
     * hp minus the damage dealt, or 0.0 if the damage dealt is greater than or equal to the current hp.
     *
     * @param damage    the amount of damage to be dealt to the object implementing this interface
     * @return the amount of damage dealt (or 0.0 if damage dealt >= current hp)
     */
    double receiveDamage(int damage);

}
