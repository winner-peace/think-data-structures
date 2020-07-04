package com.winnerpeace.datastructures.chapter6.queue;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class ArrayListQueue<E> implements Queue<E> {

    private static final int DEFAULT_CAPACITY = 10;

    private final List<E> values;

    private int front;
    private int rear;

    public ArrayListQueue() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayListQueue(final int capacity) {
        values = new ArrayList<>(capacity);
    }

    @Override
    public boolean offer(final E element) {
        values.add(element);
        front++;

        return true;
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }

        return values.remove(rear++);
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }

        return values.get(rear);
    }

    @Override
    public boolean isEmpty() {
        return front <= rear;
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        final ArrayListQueue<?> that = (ArrayListQueue<?>) other;
        return Objects.equals(values, that.values);
    }

    @Override
    public int hashCode() {
        return Objects.hash(values);
    }

    @Override
    public String toString() {
        return values.toString();
    }
}
