package com.winnerpeace.datastructures.chapter5;

import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.Optional;

import static java.util.Objects.nonNull;

public final class Node<E> {

    @Nullable
    private Node<E> prev;

    @Nullable
    private Node<E> next;

    private final Object value;

    private Node(final E value) {
        this.value = value;
    }


    public static <E> Node<E> first(final E element) {
        return new Node<>(element);
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
        next.prev = this;
        return next;
    }

    public boolean hasNext() {
        return nonNull(next);
    }

    public Optional<Node<E>> getNext() {
        return Optional.ofNullable(next);
    }

    public Optional<Node<E>> getPrev() {
        return Optional.ofNullable(prev);
    }

    public Node<E> insert(final E element) {
        final Node<E> nextNode = this.next;
        final Node<E> insertNode = createNext(element);
        this.next = insertNode;
        insertNode.next = nextNode;

        return this.next;
    }

    public Node<E> change(final E element) {
        final Node<E> changedNode = new Node<>(element);

        getNext().ifPresent(changedNode::changeNext);
        changedNode.getNext()
                .ifPresent(node -> node.changePrev(changedNode));
        getPrev().ifPresent(changedNode::changePrev);
        changedNode.getPrev()
                .ifPresent(node -> node.changeNext(changedNode));

        return this;
    }

    private void changeNext(final Node<E> targetNode) {
        next = targetNode;
    }

    private void changePrev(final Node<E> targetNode) {
        prev = targetNode;
    }

    public Node<E> remove() {
        this.getPrev()
                .ifPresent(node -> node.changeNext(next));
        this.getNext()
                .ifPresent(node -> node.changePrev(prev));

        return this;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}
