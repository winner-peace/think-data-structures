package com.winnerpeace.datastructures.chapter6.deque;

import com.winnerpeace.datastructures.chapter6.queue.Queue;

public interface Deque<E> extends Queue<E> {

    boolean offerFirst(final E element);

    E pollLast();

    E peekLast();
}
