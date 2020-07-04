package com.winnerpeace.datastructures.chapter6.queue;

import java.util.Arrays;

public final class ArrayQueue<E> implements Queue<E> {

    private static final int DEFAULT_CAPACITY = 10;

    private Object[] elements;

    private int front;
    private int rear;

    public ArrayQueue() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayQueue(final int capacity) {
        elements = new Object[capacity];
    }

    @Override
    public boolean offer(final E element) {
        grow();

        elements[++front] = element;
        return true;
    }

    private void grow() {
        if (elements.length > front) {
            return;
        }

        final Object[] newElements = new Object[elements.length << 1];
        System.arraycopy(elements, 0, newElements, 0, elements.length);

        elements = newElements;
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }

        final E element = peek();
        ++rear;

        return element;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }

        @SuppressWarnings("unchecked")
        final E element = (E) elements[rear];

        return element;
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
        final ArrayQueue<?> that = (ArrayQueue<?>) other;
        return Arrays.equals(elements, that.elements);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(elements);
    }

    @Override
    public String toString() {
        return Arrays.toString(elements);
    }
}
