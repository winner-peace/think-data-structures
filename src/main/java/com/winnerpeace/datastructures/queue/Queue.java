package com.winnerpeace.datastructures.queue;

public interface Queue<E> {

    boolean offer(final E element);

    E poll();

    E peek();

    boolean isEmpty();
}
