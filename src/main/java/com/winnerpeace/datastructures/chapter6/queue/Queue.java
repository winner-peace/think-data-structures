package com.winnerpeace.datastructures.chapter6.queue;

public interface Queue<E> {

    boolean offer(final E element);

    E poll();

    E peek();

    boolean isEmpty();
}
