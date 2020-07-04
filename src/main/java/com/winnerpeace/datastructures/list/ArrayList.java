package com.winnerpeace.datastructures.list;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public final class ArrayList<E> implements List<E> {

    private static final int DEFAULT_CAPACITY = 10;
    private static final int DEFAULT_GROW_ELEMENT_SIZE = 1;

    private Object[] elements;
    private int size;

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayList(final int capacity) {
        elements = new Object[capacity];
    }

    public ArrayList(final List<E> list) {
        this(list.size());
        addAll(list);
    }

    @Override
    public boolean add(final E element) {
        grow();

        elements[size++] = element;

        return true;
    }

    @SuppressWarnings("ManualArrayCopy")
    @Override
    public boolean add(final int index,
                       final E element) {
        if (index < FIRST_INDEX || index > size) {
            throw new IndexOutOfBoundsException();
        }

        grow();

        // Note: System.arraycopy(elements, index, elements, index + 1, size - index);
        for (int i = index; i < size; i++) {
            elements[i + INDEX_TERM] = elements[i];
        }
        elements[index] = element;

        ++size;

        return true;
    }

    @Override
    public boolean addAll(final List<E> elements) {
        boolean isAdded = false;
        for (final E element : elements) {
            isAdded |= add(element);
        }

        return isAdded;
    }

    private void grow() {
        grow(DEFAULT_GROW_ELEMENT_SIZE);
    }

    @SuppressWarnings({"ManualArrayCopy", "SameParameterValue"})
    private void grow(final int addElementSize) {
        int growExpectSize = size + addElementSize;
        while (growExpectSize < elements.length) {
            growExpectSize <<= 1;
        }

        final Object[] newElements = new Object[growExpectSize];

//         Note: System.arraycopy(elements, FIRST_INDEX, newElements, FIRST_INDEX, size);
        for (int i = FIRST_INDEX; i < size; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;
    }

    @Override
    public E get(final int index) {
        if (index < FIRST_INDEX || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        @SuppressWarnings("unchecked") final E element = (E) elements[index];

        return element;
    }

    @Override
    public E set(final int index,
                 final E element) {
        if (index < FIRST_INDEX || index > size) {
            throw new IndexOutOfBoundsException();
        }

        grow();

        @SuppressWarnings("unchecked") final E originalElement = (E) elements[index];
        elements[index] = element;

        return originalElement;
    }

    @Override
    public int indexOf(final E element) {
        for (int i = FIRST_INDEX; i < size; i++) {
            if (Objects.equals(element, elements[i])) {
                return i;
            }
        }

        return ELEMENT_NOT_FOUND;
    }

    @Override
    public int lastIndexOf(final E element) {
        for (int i = size - INDEX_TERM; i >= FIRST_INDEX; i--) {
            if (Objects.equals(element, elements[i])) {
                return i;
            }
        }

        return ELEMENT_NOT_FOUND;
    }

    @Override
    public boolean isEmpty() {
        return size == EMPTY_SIZE;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean remove(final E element) {
        final int index = indexOf(element);
        if (index == ELEMENT_NOT_FOUND) {
            return false;
        }

        remove(index);
        return true;
    }

    @Override
    public E remove(final int index) {
        final E element = get(index);
        for (int i = index, end = size - index; i <= end; i++) {
            elements[i] = elements[i + INDEX_TERM];
        }

        --size;
        return element;
    }

    @Override
    public boolean removeAll(final List<E> elements) {
        boolean isRemoved = false;
        for (final E element : elements) {
            isRemoved |= remove(element);
        }

        return isRemoved;
    }

    @Override
    public boolean contains(final E element) {
        return indexOf(element) != ELEMENT_NOT_FOUND;
    }

    @Override
    public void clear() {
        this.elements = new Object[elements.length];
        size = EMPTY_SIZE;
    }

    @NotNull
    @Override
    public Iterator<E> iterator() {
        return new ArrayListIterator();
    }

    private final class ArrayListIterator implements Iterator<E> {

        private final AtomicInteger index = new AtomicInteger();

        @Override
        public boolean hasNext() {
            return size > index.get();
        }

        @Override
        public E next() {
            return get(index.getAndIncrement());
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(elements);
    }
}
