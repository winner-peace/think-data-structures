package com.winnerpeace.datastructures.jaeyeonling.queue;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@EqualsAndHashCode
public final class NodeQueue<E> implements Queue<E> {

    private Node<E> front;

    @Override
    public boolean offer(final E element) {
        if (isEmpty()) {
            front = Node.first(element);
        } else {
            front.append(element);
        }

        return true;
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }

        final E value = front.value;
        front = front.next;

        return value;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }

        return front.value;
    }

    @Override
    public boolean isEmpty() {
        return isNull(front);
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }

        final List<E> values = new ArrayList<>();
        for (Node<E> node = front; nonNull(node); node = node.next) {
            values.add(node.value);
        }

        return values.toString();
    }

    @ToString
    @EqualsAndHashCode
    @RequiredArgsConstructor(staticName = "first")
    private static class Node<E> {

        private Node<E> next;

        private final E value;

        public Node<E> append(final E value) {
            if (nonNull(next)) {
                return next.append(value);
            }

            next = new Node<>(value);
            return next;
        }
    }
}
