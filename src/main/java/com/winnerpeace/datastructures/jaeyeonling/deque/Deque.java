package com.winnerpeace.datastructures.jaeyeonling.deque;

import com.winnerpeace.datastructures.jaeyeonling.queue.Queue;

public interface Deque<E> extends Queue<E> {

    boolean offerFirst(final E element);

    E pollLast();

    E peekLast();
}
