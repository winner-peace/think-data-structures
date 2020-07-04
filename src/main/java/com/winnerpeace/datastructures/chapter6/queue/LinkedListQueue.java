package com.winnerpeace.datastructures.chapter6.queue;

import lombok.EqualsAndHashCode;

import java.util.LinkedList;

@EqualsAndHashCode
public final class LinkedListQueue<E> implements Queue<E> {

    private final LinkedList<E> values = new LinkedList<>();

    @Override
    public boolean offer(final E element) {
        return values.offer(element);
    }

    @Override
    public E poll() {
        return values.poll();
    }

    @Override
    public E peek() {
        return values.peek();
    }

    @Override
    public boolean isEmpty() {
        return values.isEmpty();
    }

    @Override
    public String toString() {
        return values.toString();
    }
}
