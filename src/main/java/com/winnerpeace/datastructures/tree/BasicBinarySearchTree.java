package com.winnerpeace.datastructures.tree;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.Comparator.comparingInt;
import static java.util.Objects.isNull;

@ToString
@EqualsAndHashCode
@AllArgsConstructor(staticName = "of")
public final class BasicBinarySearchTree<E> implements BinarySearchTree<E> {

    // Notice: 트리의 구조를 배우기 위해 해시 충돌 고려하지 않음

    private Node<E> root;

    public static <E> BinarySearchTree<E> create(final List<E> values) {
        @SuppressWarnings("unchecked")
        final E[] array = (E[]) values.toArray();

        return create(array);
    }

    public static <E> BinarySearchTree<E> create(final E[] values) {
        Arrays.sort(values, comparingInt(Object::hashCode));

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
    public Optional<E> find(final int key) {
        return find(root, key).map(Node::getValue);
    }

    private Optional<Node<E>> find(final Node<E> root,
                             final int key) {
        if (isNull(root) || root.matchKey(key)) {
            return Optional.ofNullable(root);
        }

        if (root.graterThanBy(key)) {
            return find(root.getLeft(), key);
        }

        return find(root.getRight(), key);
    }

    @Override
    public void insert(final E value) {
        root = insert(root, value);
    }

    private Node<E> insert(final Node<E> root,
                           final E value) {
        if (isNull(root)) {
            return Node.root(value);
        }

        if (root.graterThanBy(value)) {
            root.left(insert(root.getLeft(), value));
        }
        if (root.lessThanBy(value)) {
            root.right(insert(root.getRight(), value));
        }

        return root;
    }

    @Override
    public Optional<E> delete(final int key) {
        return delete(root, key).map(Node::getValue);
    }

    @Override
    public boolean delete(final E value) {
        return delete(value.hashCode()).isPresent();
    }

    private Optional<Node<E>> delete(final Node<E> root,
                                     final int key) {
        if (isNull(root)) {
            return Optional.empty();
        }

        if (root.graterThanBy(key)) {
            root.setLeft(delete(root.getLeft(), key).orElse(null));
        } else if (root.lessThanBy(key)) {
            root.setRight(delete(root.getRight(), key).orElse(null));
        } else {
            if (root.isLeaf()) {
                return Optional.empty();
            }
            if (root.emptyLeft()) {
                return Optional.ofNullable(root.getRight());
            }
            if (root.emptyRight()) {
                return Optional.ofNullable(root.getRight());
            }

            root.change(findMin(root.getRight()));
            root.setRight(delete(root.getRight(), root.getKey()).orElse(null));
        }

        return Optional.of(root);
    }

    @SuppressWarnings("ConstantConditions")
    @Nullable
    private E findMin(final Node<E> root) {
        if (isNull(root)) {
            return null;
        }

        Node<E> node = root;
        E min = node.getValue();
        while (node.hasLeft()) {
            min = node.getLeftValue();
            node = node.getLeft();
        }

        return min;
    }

    @Override
    public boolean exists(final E value) {
        return exists(value.hashCode());
    }

    @Override
    public boolean exists(final int key) {
        return find(key).isPresent();
    }

    @Override
    public boolean isEmpty() {
        return isNull(root);
    }
}
