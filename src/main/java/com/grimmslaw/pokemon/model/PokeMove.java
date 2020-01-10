package com.grimmslaw.pokemon.model;

import com.grimmslaw.pokemon.constants.Statistics;
import com.grimmslaw.pokemon.moves.Move;
import com.grimmslaw.pokemon.pokemon.Pokemon;
import com.grimmslaw.pokemon.util.MathUtilities;
import com.grimmslaw.pokemon.util.PrettyPrintable;

import java.util.Objects;
import java.util.StringJoiner;
import java.util.concurrent.ThreadLocalRandom;

public class PokeMove implements PrettyPrintable {

    private Pokemon attacker;
    private Move move;
    private Pokemon target;

    public PokeMove(Pokemon pokemon, Move move, Pokemon target) {
        this.attacker = pokemon;
        this.move = move;
        this.target = target;
    }

    public Pokemon getAttacker() {
        return attacker;
    }

    public Move getMove() {
        return move;
    }

    public Pokemon getTarget() {
        return target;
    }

    public static int comparePokeMoves(PokeMove pokeMove1, PokeMove pokeMove2) {
        if (pokeMove1.equals(pokeMove2)) {
            return 0;
        }

        if (pokeMove1.getMove().getPriority() > pokeMove2.getMove().getPriority()) {
            return 1;
        } else if (pokeMove1.getMove().getPriority() < pokeMove1.getMove().getPriority()) {
            return -1;
        }

        int statComparisonValue = MathUtilities.compareStageAdjustedStat(
                pokeMove1.getAttacker(), pokeMove2.getAttacker(), Statistics.Stat.SPEED);
        if (statComparisonValue > 0) {
            return 1;
        } else if (statComparisonValue < 0) {
            return -1;
        } else {
            // equal priorities and equal speeds mean the first mover is chosen at random
            return ThreadLocalRandom.current().nextInt(0,2) == 0 ? 1 : -1;
        }
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", PokeMove.class.getSimpleName() + "[", "]")
                .add("pokemon=" + attacker)
                .add("move=" + move)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PokeMove pokeMove = (PokeMove) o;
        return Objects.equals(attacker, pokeMove.attacker) &&
                Objects.equals(move, pokeMove.move);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attacker, move);
    }

}
