package com.grimmslaw.pokemon.pokemon;

import com.grimmslaw.pokemon.abilities.Ability;
import com.grimmslaw.pokemon.attributes.StatSet;
import com.grimmslaw.pokemon.model.StatMap;
import com.grimmslaw.pokemon.moves.Moveset;
import com.grimmslaw.pokemon.natures.Nature;
import com.grimmslaw.pokemon.types.Type;

import java.util.StringJoiner;

/**
 * Defines attributes and methods that uniquely belong to a dual-type pokemon (inheriting all single-type pokemon
 * attributes and methods).
 *
 * @author wesrickey
 * @since 0.0.1
 */
public class DualTypePokemon extends Pokemon {

    private Type typeSecond;

    public DualTypePokemon(String name, StatSet baseStats, Type type, Type typeSecond, int level, Nature nature,
                           StatMap<Integer> evs, Ability ability, Moveset moveset) {
        super(name, baseStats, type, level, nature, evs, ability, moveset);
        setTypeSecond(typeSecond);
    }

    public Type getTypeSecond() {
        return typeSecond;
    }

    public void setTypeSecond(Type typeSecond) {
        this.typeSecond = typeSecond;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DualTypePokemon.class.getSimpleName() + "[", "]")
                .add("typeSecond=" + typeSecond)
                .add("super=" + super.toString())
                .toString();
    }
}
