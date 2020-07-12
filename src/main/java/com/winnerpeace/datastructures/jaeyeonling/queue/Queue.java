package com.winnerpeace.datastructures.jaeyeonling.queue;

public interface Queue<E> {

    boolean offer(final E element);

    E poll();

    E peek();

    boolean isEmpty();
}
