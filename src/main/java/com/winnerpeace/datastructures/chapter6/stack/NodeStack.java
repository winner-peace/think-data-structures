package com.winnerpeace.datastructures.chapter6.stack;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@EqualsAndHashCode
public final class NodeStack<E> implements Stack<E> {

    private Node<E> tail;

    @Override
    public boolean isEmpty() {
        return isNull(tail);
    }

    @Override
    public boolean push(final E element) {
        if (isEmpty()) {
            tail = Node.first(element);
        } else {
            tail = tail.next(element);
        }

        return true;
    }

    @Override
    public E pop() {
        final Node<E> node = peekNode();
        tail = node.prev;

        return node.value;
    }

    @Override
    public E peek() {
        return peekNode().value;
    }

    private Node<E> peekNode() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        return tail;
    }

    @Override
    public void clear() {
        tail = null;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }

        final List<E> values = new ArrayList<>();
        for (Node<E> node = tail; nonNull(node); node = node.prev) {
            values.add(node.value);
        }

        return values.toString();
    }

    @ToString
    @EqualsAndHashCode
    @RequiredArgsConstructor(staticName = "first")
    private static class Node<E> {

        @Nullable
        private Node<E> prev;

        private final E value;

        private Node<E> next(final E value) {
            final Node<E> next = new Node<>(value);
            next.prev = this;

            return next;
        }
    }
}
