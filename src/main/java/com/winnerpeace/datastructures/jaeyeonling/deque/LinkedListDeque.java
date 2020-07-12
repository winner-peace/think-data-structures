package com.winnerpeace.datastructures.jaeyeonling.deque;

import lombok.EqualsAndHashCode;

import java.util.LinkedList;

@EqualsAndHashCode
public final class LinkedListDeque<E> implements Deque<E> {

    private final LinkedList<E> values = new LinkedList<>();

    @Override
    public boolean offerFirst(final E element) {
        return values.offerFirst(element);
    }

    @Override
    public E pollLast() {
        return values.pollLast();
    }

    @Override
    public E peekLast() {
        return values.peekLast();
    }

    @Override
    public boolean offer(final E element) {
        return values.offerFirst(element);
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
