package com.winnerpeace.datastrucrues;

public interface List<E> extends Iterable<E> {

    int ELEMENT_NOT_FOUND = -1;
    int EMPTY_SIZE = 0;
    int FIRST_INDEX = 0;

    boolean add(final E element);

    boolean addAll(final List<E> elements);

    boolean add(final int index,
                final E element);

    E get(final int index);

    E set(final int index,
          final E element);

    int indexOf(final E element);

    int lastIndexOf(final E element);

    boolean isEmpty();

    int size();

    boolean remove(final E element);

    boolean removeAll(final List<E> elements);

    E remove(final int index);

    boolean contains(final E element);

    void clear();
}
