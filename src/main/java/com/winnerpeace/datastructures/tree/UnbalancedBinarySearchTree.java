package com.winnerpeace.datastructures.tree;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static java.util.Objects.isNull;

@ToString
@EqualsAndHashCode
@RequiredArgsConstructor(staticName = "of")
public final class UnbalancedBinarySearchTree<E> implements BinarySearchTree<E> {

    private final Node<E> root;

    public static <E> BinarySearchTree<E> create(final List<E> values) {
        @SuppressWarnings("unchecked")
        final E[] array = (E[]) values.toArray();

        return create(array);
    }

    public static <E> BinarySearchTree<E> create(final E[] values) {
        return of(createNode(values, 0, values.length - 1));
    }

    @Nullable
    private static <E> Node<E> createNode(final E[] values,
                                          final int startIndex,
                                          final int endIndex) {
        if (startIndex > endIndex) {
            return null;
        }

        final int midIndex = (startIndex + endIndex) / 2;
        final Node<E> node = Node.root(values[midIndex]);
        node.setLeft(createNode(values, startIndex, midIndex - 1));
        node.setRight(createNode(values, midIndex + 1, endIndex));

        return node;
    }

    @Override
    public boolean exists(final E value) {
        return exists(root, value);
    }

    // Notice: 트리의 구조를 배우기 위해 해시 충돌 고려하지 않음
    private boolean exists(final Node<E> node,
                           final E value) {
        if (isNull(node)) {
            return false;
        }

        final E nodeValue = node.getValue();
        if (value.hashCode() < nodeValue.hashCode()) {
            return exists(node.getLeft(), value);
        }
        if (value.hashCode() > nodeValue.hashCode()) {
            return exists(node.getRight(), value);
        }

        return value.equals(node.getValue());
    }

}
