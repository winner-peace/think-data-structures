package com.winnerpeace.datastructures.chapter6.deque;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@EqualsAndHashCode
public final class NodeDeque<E> implements Deque<E> {

    @Nullable
    private Node<E> head;

    @Nullable
    private Node<E> tail;

    @SuppressWarnings("ConstantConditions")
    @Override
    public boolean offerFirst(final E element) {
        if (isEmpty()) {
            head = Node.first(element);
            tail = head;
        } else {
            head = head.createPrev(element);
        }

        return true;
    }

    @Override
    public E pollLast() {
        if (isEmpty()) {
            return null;
        }

        @SuppressWarnings("ConstantConditions")
        final E value = head.value;

        head = head.next;
        if (isNull(head)) {
            tail = null;
        }

        return value;
    }

    @Override
    public E peekLast() {
        if (isEmpty()) {
            return null;
        }

        @SuppressWarnings("ConstantConditions")
        final E value = head.value;

        return value;
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public boolean offer(final E element) {
        if (isEmpty()) {
            head = Node.first(element);
            tail = head;
        } else {
            tail = tail.createNext(element);
        }

        return true;
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }

        @SuppressWarnings("ConstantConditions")
        final E value = tail.value;

        tail = tail.prev;
        if (isNull(tail)) {
            head = null;
        }

        return value;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }

        @SuppressWarnings("ConstantConditions")
        final E value = tail.value;
        return value;
    }

    @Override
    public boolean isEmpty() {
        return isNull(head) && isNull(tail);
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }

        final List<E> values = new ArrayList<>();
        for (Node<E> node = head; nonNull(node); node = node.next) {
            values.add(node.value);
        }

        return values.toString();
    }

    @ToString
    @EqualsAndHashCode
    @RequiredArgsConstructor(staticName = "first")
    private static final class Node<E> {

        @Nullable
        private Node<E> prev;

        @Nullable
        private Node<E> next;

        @Getter
        private final E value;

        public Node<E> createNext(final E element) {
            next = new Node<>(element);
            next.prev = this;
            return next;
        }

        public Node<E> createPrev(final E element) {
            prev = new Node<>(element);
            prev.next = this;
            return prev;
        }
    }
}
