package com.winnerpeace.datastructures.deque;

import com.winnerpeace.datastructures.queue.Queue;

public interface Deque<E> extends Queue<E> {

    boolean offerFirst(final E element);

    E pollLast();

    E peekLast();
}
