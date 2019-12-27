package com.grimmslaw.pokemon.moves;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.grimmslaw.pokemon.exceptions.MovesetException;
import com.grimmslaw.pokemon.exceptions.MovesetFullException;

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

}
