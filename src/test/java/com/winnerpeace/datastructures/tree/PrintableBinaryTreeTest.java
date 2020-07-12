package com.winnerpeace.datastructures.tree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PrintableBinaryTreeTest {

    private PrintableBinaryTree<Integer> printableBinaryTree;

    /**
     *        1
     *       /  \
     *     2     3
     *    / \
     *  4    5
     */
    @BeforeEach
    void setUp() {
        final Node<Integer> root = Node.root(1);
        final Node<Integer> node2 = root.left(2);
        final Node<Integer> node3 = root.right(3);
        final Node<Integer> node4 = node2.left(4);
        final Node<Integer> node5 = node2.right(5);

        printableBinaryTree = PrintableBinaryTree.of(root);
    }

    // 4 -> 2 -> 5 -> 1 -> 3
    @Test
    void inOrder() {
        printableBinaryTree.inOrder();
    }

    // 1 -> 2 -> 4 -> 5 -> 3
    @Test
    void preOrder() {
        printableBinaryTree.preOrder();
    }

    // 4 -> 5 -> 2 -> 3 -> 1
    @Test
    void postOrder() {
        printableBinaryTree.postOrder();
    }

    // 1-> 2 -> 3 -> 4 -> 5
    @Test
    void levelOrder() {
        printableBinaryTree.levelOrder();
    }
}