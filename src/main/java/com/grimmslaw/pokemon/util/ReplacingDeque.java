package com.grimmslaw.pokemon.util;

import java.util.ArrayDeque;

public class ReplacingDeque<E> extends ArrayDeque<E> {

    private static final long serialVersionUID = 2800055321961172734L;

    /**
     * Peeks the top of the {@code Deque} by calling {@linkplain ArrayDeque#pop()} and replacing it at the bottom.
     *
     * @return the object peeked from the top of the {@code Deque}
     */
    public E peekAndShuffle() {
        E objectPeeked = super.pop();
        super.add(objectPeeked);
        return objectPeeked;
    }

}
