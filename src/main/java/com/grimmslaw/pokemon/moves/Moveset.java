package com.grimmslaw.pokemon.moves;

import java.util.*;

import com.grimmslaw.pokemon.exceptions.moves.MovesetException;
import com.grimmslaw.pokemon.exceptions.moves.MovesetFullException;

/**
 * TODO
 *
 * @author wesrickey
 * @since 0.0.1
 */
public class Moveset {

    private List<Move> movesList;

    public Moveset() {
        movesList = new ArrayList<>();
    }

    public Moveset(Move... moves) throws MovesetException {
        this(Arrays.asList(moves));
    }

    private Moveset(List<Move> moves) throws MovesetException {
        setMovesList(moves);
    }

    public List<Move> getMovesList() {
        return movesList;
    }

    public void setMovesList(List<Move> moves) throws MovesetException {
        if (moves.size() <= 4) {
            movesList = moves;
        } else {
            throw new MovesetException();
        }
    }

    public void setOneMove(Move move) throws MovesetFullException {
        if (getMovesList().size() < 4) {
            getMovesList().add(move);
        } else {
            throw new MovesetFullException();
        }
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Moveset.class.getSimpleName() + "[", "]")
                .add("movesList=" + movesList)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Moveset moveset = (Moveset) o;
        return Objects.equals(movesList, moveset.movesList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movesList);
    }

}
