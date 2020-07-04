package com.winnerpeace.datastructures.queue.circular;

import java.util.Arrays;

public final class ArrayCircularQueue<E> implements CircularQueue<E> {

    private static final int DEFAULT_CAPACITY = 10;
    private static final int FIRST_INDEX = 0;
    private static final int INDEX_TERM = 1;

    private final Object[] elements;

    private int front;
    private int rear;

    public ArrayCircularQueue() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayCircularQueue(final int capacity) {
        elements = new Object[capacity];
    }

    @Override
    public boolean isFull() {
        if (front == FIRST_INDEX) {
            return elements.length == rear + INDEX_TERM;
        }

        return front == rear + INDEX_TERM;
    }

    @Override
    public boolean offer(final E element) {
        if (isFull()) {
            return false;
        }

        elements[front] = element;
        front = safetyIncrementIndex(front);

        return true;
    }

    @Override
    public E poll() {
        final E element = peek();

        // Notice: Lazy GC (if you want to be Eager GC, change it to null.)
        rear = safetyIncrementIndex(rear);

        return element;
    }

    private int safetyIncrementIndex(final int index) {
        if (index >= elements.length) {
            return FIRST_INDEX;
        }

        return index + INDEX_TERM;
    }

    @Override
    public E peek() {
        @SuppressWarnings("unchecked")
        final E element = (E) elements[rear];

        return element;
    }

    @Override
    public boolean isEmpty() {
        return front == rear;
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        final ArrayCircularQueue<?> that = (ArrayCircularQueue<?>) other;
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
