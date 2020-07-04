package com.winnerpeace.datastructures.chapter6.stack;

import lombok.EqualsAndHashCode;

import java.util.EmptyStackException;
import java.util.List;

@EqualsAndHashCode
public abstract class ListStack<E> implements Stack<E> {

    private static final int INDEX_TERM = 1;

    private List<E> values;

    protected abstract List<E> getList();

    @Override
    public boolean isEmpty() {
        return values.isEmpty();
    }

    @Override
    public boolean push(final E element) {
        return values.add(element);
    }

    @Override
    public E pop() {
        final E element = peek();
        values.remove(getLastIndex());

        return element;
    }

    @Override
    public E peek() {
        return values.get(getLastIndex());
    }

    @Override
    public void clear() {
        values = getList();
    }

    private int getLastIndex() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        return values.size() - INDEX_TERM;
    }

    @Override
    public String toString() {
        return values.toString();
    }
}
