package com.winnerpeace.datastrucrues.chapter4;

import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.Optional;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public final class Node<E> {

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
        final var castedValue = (E) value;
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
            final var appendNode = next.append(element);
            return appendNode;
        }

        return createNext(element);
    }

    public Node<E> insert(final E element) {
        final var nextNode = this.next;
        final var insertNode = createNext(element);
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

        final var targetNode = next;
        if (targetNode.isLast()) {
            createNext(element);
        } else {
            final var targetNextNode = targetNode.getNext()
                    .orElseThrow(IndexOutOfBoundsException::new);

            next = createNext(element);
            next.next = targetNextNode;
        }

        return targetNode;
    }

    public Optional<Node<E>> changeNext(final Node<E> changeNext) {
        final var originalNext = this.next;
        this.next = changeNext;

        return Optional.ofNullable(originalNext);
    }

    @SuppressWarnings("ConstantConditions")
    public Optional<Node<E>> removeNext() {
        if (isLast()) {
            return Optional.empty();
        }

        final var targetNode = next;
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
