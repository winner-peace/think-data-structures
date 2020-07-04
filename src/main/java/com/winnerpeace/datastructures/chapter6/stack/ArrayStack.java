package com.winnerpeace.datastructures.chapter6.stack;

import lombok.EqualsAndHashCode;

import java.util.Arrays;
import java.util.EmptyStackException;

@EqualsAndHashCode
public final class ArrayStack<E> implements Stack<E> {

    private static final int DEFAULT_CAPACITY = 10;
    private static final int INDEX_TERM = 1;
    private static final int MIN_SIZE = 0;

    private Object[] elements;

    private int size;

    public ArrayStack() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayStack(final int capacity) {
        elements = new Object[capacity];
    }

    @Override
    public boolean isEmpty() {
        return size == MIN_SIZE;
    }

    @Override
    public boolean push(final E element) {
        grow();

        elements[size++] = element;

        return true;
    }

    private void grow() {
        if (elements.length > size) {
            return;
        }

        final Object[] newElements = new Object[elements.length << 1];
        System.arraycopy(elements, 0, newElements, 0, elements.length);

        elements = newElements;
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        final E element = peek();
        elements[--size] = null;

        return element;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E peek() {
        return (E) elements[getLastIndex()];
    }

    @Override
    public void clear() {
        elements = new Object[elements.length];
    }

    private int getLastIndex() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        return elements.length - INDEX_TERM;
    }

    @Override
    public String toString() {
        return Arrays.toString(elements);
    }
}
