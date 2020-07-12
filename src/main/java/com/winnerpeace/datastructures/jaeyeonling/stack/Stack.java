package com.winnerpeace.datastructures.jaeyeonling.stack;

public interface Stack<E> {

    boolean isEmpty();

    boolean push(final E element);

    E pop();

    E peek();

    void clear();
}
