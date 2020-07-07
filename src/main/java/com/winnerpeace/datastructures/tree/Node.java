package com.winnerpeace.datastructures.tree;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

import static java.util.Objects.nonNull;

@ToString
@EqualsAndHashCode
@RequiredArgsConstructor(staticName = "root")
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
    private final E value;

    public Node<E> left(final E value) {
        left = new Node<>(value);
        return left;
    }

    public Node<E> right(final E value) {
        right = new Node<>(value);
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

    public boolean graterThanBy(final int key) {
        return value.hashCode() > key;
    }

    public boolean lessThanBy(final int key) {
        return value.hashCode() < key;
    }
}