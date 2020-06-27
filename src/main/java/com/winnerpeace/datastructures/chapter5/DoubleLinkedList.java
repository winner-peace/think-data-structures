package com.winnerpeace.datastructures.chapter5;

import com.winnerpeace.datastructures.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Optional;

import static java.util.Objects.isNull;

public class DoubleLinkedList<E> implements List<E> {

    @Nullable
    private Node<E> head;

    @Nullable
    private Node<E> tail;

    private int size;

    @SuppressWarnings("ConstantConditions")
    @Override
    public boolean add(final E element) {
        if (isEmpty()) {
            head = Node.first(element);
            tail = head;
        } else {
            tail = tail.createNext(element);
        }

        ++size;
        return true;
    }

    @Override
    public boolean addAll(final List<E> elements) {
        var isAdded = false;
        for (final var element : elements) {
            isAdded |= add(element);
        }

        return isAdded;
    }

    @Override
    public boolean add(final int index,
                       final E element) {
        if (index < FIRST_INDEX || size < index) {
            throw new IndexOutOfBoundsException();
        }
        if (isEmpty()) {
            return add(element);
        }

        getNode(index)
                .map(node -> node.insert(element))
                .orElseThrow(IndexOutOfBoundsException::new);

        return true;
    }

    @Override
    public E get(final int index) {
        return getNode(index)
                .map(Node::getValue)
                .orElseThrow(IndexOutOfBoundsException::new);
    }

    @Override
    public E set(final int index,
                 final E element) {
        if (index < FIRST_INDEX || size < index) {
            throw new IndexOutOfBoundsException();
        }
        if (isEmpty() || size == index) {
            add(element);
            return element;
        }

        return getNode(index)
                .orElseThrow(IndexOutOfBoundsException::new)
                .change(element)
                .getValue();
    }

    @SuppressWarnings("ConstantConditions")
    private Optional<Node<E>> getNode(final int index) {
        if (index < FIRST_INDEX || size <= index) {
            return Optional.empty();
        }
        if (index == FIRST_INDEX) {
            return Optional.of(head);
        }
        if (index == size - INDEX_TERM) {
            return Optional.of(tail);
        }

        Node<E> node;
        if (size / 2 > index) {
            node = head;
            for (var i = FIRST_INDEX; i < index; i++) {
                node = node.getNext()
                        .orElseThrow(IndexOutOfBoundsException::new);
            }
        } else {
            node = tail;
            for (int i = FIRST_INDEX, end = size - INDEX_TERM - index; i < end; i++) {
                node = node.getPrev()
                        .orElseThrow(IndexOutOfBoundsException::new);
            }
        }

        return Optional.ofNullable(node);
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public int indexOf(final E element) {
        if (isEmpty()) {
            return ELEMENT_NOT_FOUND;
        }

        var node = head;
        for (int i = FIRST_INDEX; i < size; i++) {
            if (node.equalsValue(element)) {
                return i;
            }

            node = node.getNext()
                    .orElseThrow(IndexOutOfBoundsException::new);
        }

        return ELEMENT_NOT_FOUND;
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public int lastIndexOf(final E element) {
        if (isEmpty()) {
            return ELEMENT_NOT_FOUND;
        }

        var node = tail;
        for (int i = size - INDEX_TERM; i >= FIRST_INDEX; i--) {
            if (node.equalsValue(element)) {
                return i;
            }

            node = node.getPrev()
                    .orElseThrow(IndexOutOfBoundsException::new);
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
        final var index = indexOf(element);
        if (index == ELEMENT_NOT_FOUND) {
            return false;
        }

        remove(index);
        return true;
    }

    @Override
    public boolean removeAll(final List<E> elements) {
        var isRemoved = false;
        for (final var element : elements) {
            isRemoved |= remove(element);
        }

        return isRemoved;
    }

    @Override
    public E remove(final int index) {
        if (index < FIRST_INDEX || size < index || isEmpty()) {
            throw new IndexOutOfBoundsException();
        }

        final var removedValue = getNode(index)
                .orElseThrow(IndexOutOfBoundsException::new)
                .remove()
                .getValue();
        --size;

        return removedValue;
    }

    @Override
    public boolean contains(final E element) {
        return indexOf(element) != ELEMENT_NOT_FOUND;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = EMPTY_SIZE;
    }

    @NotNull
    @Override
    public Iterator<E> iterator() {
        return new LinkedListIterator(head);
    }

    private final class LinkedListIterator implements Iterator<E> {

        private Node<E> node;

        public LinkedListIterator(final Node<E> node) {
            this.node = node;
        }

        @Override
        public boolean hasNext() {
            if (isNull(node)) {
                return false;
            }

            return node.hasNext();
        }

        @Override
        public E next() {
            final var value = node.getValue();
            node = node.getNext()
                    .orElse(null);

            return value;
        }
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public String toString() {
        final var array = new Object[size];

        var node = head;
        for (int i = FIRST_INDEX; i < size; i++) {
            array[i] = node.getValue();
            node = node.getNext().orElse(null);
        }

        return Arrays.toString(array);
    }
}
