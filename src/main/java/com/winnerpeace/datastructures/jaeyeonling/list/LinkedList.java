package com.winnerpeace.datastructures.jaeyeonling.list;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import java.util.Optional;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class LinkedList<E> implements List<E> {

    private static final int PREV_NODE_TERM = 1;

    @Nullable
    private Node<E> head;

    private int size;

    @SuppressWarnings("ConstantConditions")
    @Override

    public boolean add(final E element) {
        if (isEmpty()) {
            head = Node.first(element);
        } else {
            head.append(element);
        }

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

    @SuppressWarnings("ConstantConditions")
    @Override
    public E set(final int index,
                 final E element) {
        if (index < FIRST_INDEX || size < index) {
            throw new IndexOutOfBoundsException();
        }
        if (isEmpty()) {
            add(element);
            return element;
        }
        if (index == FIRST_INDEX) {
            final Node<E> node = head;

            head = Node.first(element);
            node.getNext()
                    .ifPresent(head::changeNext);

            return node.getValue();
        }



        return getNode(index - PREV_NODE_TERM)
                .orElseThrow(IndexOutOfBoundsException::new)
                .changeNext(element)
                .getValue();
    }

    @SuppressWarnings("ConstantConditions")
    private Optional<Node<E>> getNode(final int index) {
        if (index < FIRST_INDEX || size <= index) {
            return Optional.empty();
        }

        Node<E> node = head;
        for (int i = FIRST_INDEX; i < index; i++) {
            node = node.getNext()
                    .orElseThrow(IndexOutOfBoundsException::new);
        }

        return Optional.ofNullable(node);
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public int indexOf(final E element) {
        if (isEmpty()) {
            return ELEMENT_NOT_FOUND;
        }

        Node<E> node = head;
        for (int i = FIRST_INDEX; i < size; i++) {
            if (node.equalsValue(element)) {
                return i;
            }

            node = node.getNext()
                    .orElseThrow(IndexOutOfBoundsException::new);
        }

        return ELEMENT_NOT_FOUND;
    }

    @Override
    public int lastIndexOf(final E element) {
        throw new UnsupportedOperationException();
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
    public boolean removeAll(final List<E> elements) {
        boolean isRemoved = false;
        for (final E element : elements) {
            isRemoved |= remove(element);
        }

        return isRemoved;
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public E remove(final int index) {
        if (index < FIRST_INDEX || size < index || isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        if (index == FIRST_INDEX) {
            final Node<E> node = head;

            if (head.hasNext()) {
                head = node.getNext()
                        .orElseThrow(IndexOutOfBoundsException::new);
                --size;
            } else {
                clear();
            }

            return node.getValue();
        }

        Node<E> prevNode = head;
        for (int i = FIRST_INDEX, end = index - PREV_NODE_TERM; i < end; i++) {
            prevNode = prevNode.getNext()
                    .orElseThrow(IndexOutOfBoundsException::new);
        }

        final E removedValue = prevNode.removeNext()
                .map(Node::getValue)
                .orElseThrow(IndexOutOfBoundsException::new);

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
            final E value = node.getValue();
            node = node.getNext()
                    .orElse(null);

            return value;
        }
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public String toString() {
        final Object[] array = new Object[size];

        Node<E> node = head;
        for (int i = FIRST_INDEX; i < size; i++) {
            array[i] = node.getValue();
            node = node.getNext().orElse(null);
        }

        return Arrays.toString(array);
    }

    static final class Node<E> {

        private static final Node<?> EMPTY = new Node<>(null);

        @Nullable
        private Node<E> next;

        private final Object value;

        private Node(final E value) {
            this.value = value;
        }


        public static <E> Node<E> first(final E element) {
            return new Node<>(element);
        }

        @SuppressWarnings("unchecked")
        public static <E> Node<E> empty() {
            return (Node<E>) EMPTY;
        }

        public E getValue() {
            @SuppressWarnings("unchecked")
            final E castedValue = (E) value;
            return castedValue;
        }

        public boolean equalsValue(final Object element) {
            return Objects.equals(value, element);
        }

        public Node<E> createNext(final E element) {
            next = new Node<>(element);
            return next;
        }

        public boolean hasNext() {
            return nonNull(next);
        }

        public Optional<Node<E>> getNext() {
            return Optional.ofNullable(next);
        }

        public Node<E> append(final E element) {
            if (hasNext()) {
                @SuppressWarnings("ConstantConditions")
                final Node<E> appendNode = next.append(element);
                return appendNode;
            }

            return createNext(element);
        }

        public Node<E> insert(final E element) {
            final Node<E> nextNode = this.next;
            final Node<E> insertNode = createNext(element);
            this.next = insertNode;
            insertNode.next = nextNode;

            return this.next;
        }

        @SuppressWarnings("ConstantConditions")
        public Node<E> changeNext(final E element) {
            if (isLast()) {
                createNext(element);
                return empty();
            }

            final Node<E> targetNode = next;
            if (targetNode.isLast()) {
                createNext(element);
            } else {
                final Node<E> targetNextNode = targetNode.getNext()
                        .orElseThrow(IndexOutOfBoundsException::new);

                next = createNext(element);
                next.next = targetNextNode;
            }

            return targetNode;
        }

        public Optional<Node<E>> changeNext(final Node<E> changeNext) {
            final Node<E> originalNext = this.next;
            this.next = changeNext;

            return Optional.ofNullable(originalNext);
        }

        @SuppressWarnings("ConstantConditions")
        public Optional<Node<E>> removeNext() {
            if (isLast()) {
                return Optional.empty();
            }

            final Node<E> targetNode = next;
            next = targetNode.getNext()
                    .orElse(null);

            return Optional.of(targetNode);
        }

        private boolean isLast() {
            return isNull(next);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    '}';
        }
    }
}
