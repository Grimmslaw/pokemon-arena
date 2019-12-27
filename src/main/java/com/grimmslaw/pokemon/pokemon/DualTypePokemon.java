package com.grimmslaw.pokemon.pokemon;

import com.grimmslaw.pokemon.types.Type;

/**
 * TODO
 *
 * @author wesrickey
 * @since 0.0.1
 */
public class DualTypePokemon extends AbstractPokemon {

    private Type typeSecond;

    public Type getTypeSecond() {
        return typeSecond;
    }

    public void setTypeSecond(Type typeSecond) {
        this.typeSecond = typeSecond;
    }

}
