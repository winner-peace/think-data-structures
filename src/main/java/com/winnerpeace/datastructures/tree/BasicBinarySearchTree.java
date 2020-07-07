package com.winnerpeace.datastructures.tree;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.Comparator.comparingInt;
import static java.util.Objects.nonNull;

@ToString
@EqualsAndHashCode
@AllArgsConstructor(staticName = "of")
public final class BasicBinarySearchTree<E> implements BinarySearchTree<E> {

    // Notice: 트리의 구조를 배우기 위해 해시 충돌 고려하지 않음

    private Node<E> root;

    private int size;

    public static <E> BinarySearchTree<E> create(final List<E> values) {
        @SuppressWarnings("unchecked")
        final E[] array = (E[]) values.toArray();

        return create(array);
    }

    public static <E> BinarySearchTree<E> create(final E[] values) {
        Arrays.sort(values, comparingInt(Object::hashCode));

        final Node<E> root = createNode(values, 0, values.length - 1);
        final int size = values.length;

        return of(root, size);
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
    public Optional<E> find(final int key) {
        return findLocation(key).map(Node::getValue);
    }

    @Override
    public Optional<E> delete(final int key) {
        return null;
    }

    @Override
    public boolean delete(final E value) {
        return delete(value.hashCode()).isPresent();
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void insert(final E value) {
        if (isEmpty()) {
            root = Node.root(value);
            return;
        }

        final int key = value.hashCode();

        findLocation(key)
                .filter(node -> node.nonMatchKey(key))
                .map(node -> {
                    ++size;

                    if (node.graterThanBy(key)) {
                        return node.left(value);
                    }

                    return node.right(value);
                })
                .orElseThrow(RuntimeException::new); // TODO Key is already insert exception
    }

    @Override
    public boolean exists(final E value) {
        return exists(value.hashCode());
    }

    @Override
    public boolean exists(final int key) {
        return findLocation(key).isPresent();
    }

    private Optional<Node<E>> findLocation(final int key) {
        if (isEmpty()) {
            return Optional.empty();
        }

        Node<E> parent = null;
        Node<E> value = root;

        while (nonNull(value)) {
            if (value.matchKey(key)) {
                return Optional.of(value);
            }

            parent = value;

            if (value.graterThanBy(key)) {
                value = value.getLeft();
                continue;
            }

            value = value.getRight();
        }

        return Optional.ofNullable(parent);
    }
}
