package com.winnerpeace.datastructures.jaeyeonling.tree;

import lombok.Builder;
import lombok.ToString;

import static java.lang.System.lineSeparator;
import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;

public class BalancedMinusTree<K extends Comparable<K>, V> {

    private static final int MAX_DEGREE = 4;

    private Node root;

    private int height;

    private int size;

    public BalancedMinusTree() {
        root = new Node(0);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public int height() {
        return height;
    }

    public V get(final K key) {
        requireNonNull(key);

        return search(root, key, height);
    }

    private V search(final Node node,
                     final K key,
                     final int height) {
        if (height == 0) {
            for (int i = 0; i < node.childrenCount; i++) {
                @SuppressWarnings("unchecked")
                final Entry<K, V> child = (Entry<K, V>) node.children[i];

                if (equals(key, child.key)) {
                    return child.value;
                }
            }
        } else {
            for (int i = 0; i < node.childrenCount; i++) {
                @SuppressWarnings("unchecked")
                final Entry<K, V> nextChild = (Entry<K, V>) node.children[i + 1];

                if (i + 1 == node.childrenCount || less(key, nextChild.key)) {
                    @SuppressWarnings("unchecked")
                    final Entry<K, V> child = (Entry<K, V>) node.children[i];

                    return search(
                            child.next,
                            key,
                            height - 1
                    );
                }
            }
        }

        return null;
    }

    public void put(final K key,
                    final V value) {
        requireNonNull(key);
        requireNonNull(value);

        final Node node = insert(
                root,
                key,
                value,
                height
        );
        ++size;
        if (isNull(node)) {
            return;
        }

        final Node newRoot = new Node(2);
        newRoot.children[0] = Entry.builder()
                .key(this.root.children[0].key)
                .next(root)
                .build();
        newRoot.children[1] = Entry.builder()
                .key(node.children[0].key)
                .next(node)
                .build();
        root = newRoot;
        ++height;
    }

    private Node insert(final Node node,
                        final K key,
                        final V value,
                        final int height) {
        final Entry<K, V> entry = Entry.<K, V>builder()
                .key(key)
                .value(value)
                .build();


        int count;
        if (height == 0) {
            for (count = 0; count < node.childrenCount; count++) {
                @SuppressWarnings("unchecked")
                final Entry<K, V> child = (Entry<K, V>) node.children[count];

                if (less(key, child.key)) {
                    break;
                }
            }
        } else {
            for (count = 0; count < node.childrenCount; count++) {
                @SuppressWarnings("unchecked")
                final Entry<K, V> nextChild = (Entry<K, V>) node.children[count + 1];

                if (count + 1 == node.childrenCount || less(key, nextChild.key)) {
                    @SuppressWarnings("unchecked")
                    final Entry<K, V> child = (Entry<K, V>) node.children[count];
                    ++count;
                    final Node newNode = insert(
                            child.next,
                            key,
                            value,
                            height - 1
                    );
                    if (isNull(newNode)) {
                        return null;
                    }


                    @SuppressWarnings("unchecked")
                    final Entry<K, V> newNodeFirstEntry = (Entry<K, V>) newNode.children[0];
                    entry.key = newNodeFirstEntry.key;
                    entry.next = newNode;
                    break;
                }
            }
        }

        for (int i = node.childrenCount; i > count; i--) {
            node.children[i] = node.children[i - 1];
        }

        node.children[count] = entry;

        ++node.childrenCount;

        if (node.childrenCount < MAX_DEGREE) {
            return null;
        }

        return split(node);
    }

    private Node split(final Node node) {
        final int childrenCount = MAX_DEGREE / 2;
        final Node newNode = new Node(childrenCount);
        node.childrenCount = childrenCount;

        for (int i = 0; i < childrenCount; i++) {
            newNode.children[i] = node.children[childrenCount + i];
        }

        return newNode;
    }

    private boolean equals(final K key1,
                           final K key2) {
        return key1.compareTo(key2) == 0;
    }

    private boolean less(final K key1,
                         final K key2) {
        return key1.compareTo(key2) < 0;
    }

    private static final class Node {

        private int childrenCount;

        private final Entry<?, ?>[] children;

        public Node(final int childrenCount) {
            this.childrenCount = childrenCount;
            this.children = new Entry<?, ?>[MAX_DEGREE];
        }
    }

    @Override
    public String toString() {
        return toString(root, height, "") + lineSeparator();
    }

    private String toString(final Node node,
                            final int height,
                            final String indent) {
        final StringBuilder builder = new StringBuilder();

        if (height == 0) {
            for (int i = 0; i < node.childrenCount; i++) {
                builder.append(indent)
                        .append(node.children[i].key)
                        .append(" ")
                        .append(node.children[i].value)
                        .append(lineSeparator());
            }
        } else {
            for (int i = 0; i < node.childrenCount; i++) {
                if (i > 0) {
                    builder.append(indent)
                            .append("(")
                            .append(node.children[i].key)
                            .append(")")
                            .append(lineSeparator());
                }

                final String next = toString(node.children[i].next, height - 1, indent);
                builder.append(next)
                        .append("\t");
            }
        }

        return builder.toString();
    }

    @ToString
    private static class Entry<K, V> {

        private K key;

        private Node next;

        private final V value;

        @Builder
        private Entry(final K key,
                      final V value,
                      final Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
}
