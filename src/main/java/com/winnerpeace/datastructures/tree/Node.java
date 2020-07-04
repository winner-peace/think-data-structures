package com.winnerpeace.datastructures.tree;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.jetbrains.annotations.Nullable;

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
}