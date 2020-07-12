package com.winnerpeace.datastructures.jaeyeonling.tree;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@ToString
@EqualsAndHashCode
public final class Node<E> {

    @Getter
    @Setter
    @Nullable
    private Node<E> left;

    @Getter
    @Setter
    @Nullable
    private Node<E> right;

    @Getter
    private E value;

    private Node(final E value) {
        this.value = value;
    }

    public static <E> Node<E> root(final E value) {
        return new Node<>(value);
    }

    public Node<E> left(final E value) {
        return left(root(value));
    }

    public Node<E> left(final Node<E> node) {
        left = node;
        return left;
    }

    public Node<E> right(final E value) {
        return right(root(value));
    }

    public Node<E> right(final Node<E> node) {
        right = node;
        return right;
    }

    public void ifPresentLeft(Consumer<Node<E>> action) {
        if (nonNull(left)) {
            action.accept(left);
        }
    }

    public void ifPresentRight(Consumer<Node<E>> action) {
        if (nonNull(right)) {
            action.accept(right);
        }
    }

    public boolean matchKey(final int key) {
        return value.hashCode() == key;
    }

    public boolean nonMatchKey(final int key) {
        return !matchKey(key);
    }

    public boolean graterThanBy(final E other) {
        return graterThanBy(other.hashCode());
    }

    public boolean graterThanBy(final int key) {
        return value.hashCode() > key;
    }

    public boolean lessThanBy(final E other) {
        return lessThanBy(other.hashCode());
    }

    public boolean lessThanBy(final int key) {
        return value.hashCode() < key;
    }

    public boolean isLeaf() {
        return emptyLeft() && emptyRight();
    }

    public boolean emptyLeft() {
        return isNull(left);
    }

    public boolean hasLeft() {
        return !emptyLeft();
    }

    public boolean emptyRight() {
        return isNull(right);
    }

    public void change(final E value) {
        this.value = value;
    }

    public int getKey() {
        return value.hashCode();
    }

    @SuppressWarnings("ConstantConditions")
    @Nullable
    public E getLeftValue() {
        if (emptyLeft()) {
            return null;
        }

        return left.getValue();
    }
}