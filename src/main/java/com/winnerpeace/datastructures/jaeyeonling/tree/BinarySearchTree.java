package com.winnerpeace.datastructures.jaeyeonling.tree;

import java.util.Optional;

public interface BinarySearchTree<E> extends BinaryTree<E> {

    boolean isEmpty();

    boolean exists(final E value);

    boolean exists(final int key);

    Optional<E> find(final int key);

    void insert(final E value);

    Optional<E> delete(final int key);

    boolean delete(final E value);
}
