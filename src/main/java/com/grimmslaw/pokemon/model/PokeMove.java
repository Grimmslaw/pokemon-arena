package com.grimmslaw.pokemon.model;

import com.grimmslaw.pokemon.moves.Move;
import com.grimmslaw.pokemon.pokemon.Pokemon;

public class PokeMove {

    private Pokemon pokemon;
    private Move move;

    public PokeMove(Pokemon pokemon, Move move) {
        this.pokemon = pokemon;
        this.move = move;
    }

    public Pokemon getPokemon() {
        return pokemon;
    }

    public Move getMove() {
        return move;
    }

}
