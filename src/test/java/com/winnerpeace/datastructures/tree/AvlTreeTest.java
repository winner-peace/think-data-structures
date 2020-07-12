package com.winnerpeace.datastructures.tree;

import org.junit.jupiter.api.Test;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static org.assertj.core.api.Assertions.assertThat;

class AvlTreeTest {

    @Test
    public void delete() {
        final AvlTree tree = new AvlTree();
        tree.insert(20);
        tree.insert(15);
        tree.insert(25);
        tree.insert(23);

        assertThat(tree.search(15)).isNotNull();
        tree.delete(15);
        assertThat(tree.search(15)).isNull();

        assertThat(tree.root.value).isEqualTo(23);
        assertThat(tree.root.height).isEqualTo(1);
        assertThat(tree.root.left.value).isEqualTo(20);
        assertThat(tree.root.right.value).isEqualTo(25);

        testProperties(tree.root);
    }

    @Test
    public void insert() {
        final AvlTree tree = new AvlTree();
        tree.insert(20);
        tree.insert(15);
        tree.insert(25);
        tree.insert(22);
        tree.insert(21);

        assertThat(tree.root.value).isEqualTo(20);
        assertThat(tree.root.left.value).isEqualTo(15);

        final AvlTree.Node rightSubtree = tree.root.right;

        assertThat(rightSubtree.value).isEqualTo(22);
        assertThat(rightSubtree.height).isEqualTo(1);
        assertThat(rightSubtree.right.value).isEqualTo(25);
        assertThat(rightSubtree.right.height).isEqualTo(0);
        assertThat(rightSubtree.left.value).isEqualTo(21);
        assertThat(rightSubtree.left.height).isEqualTo(0);

        testProperties(tree.root);
    }

    @Test
    public void rotateLeft() {
        final AvlTree.Node root = AvlTree.Node.builder()
                .value(4)
                .build();
        final AvlTree.Node rightChild = AvlTree.Node.builder()
                .value(6)
                .parent(root)
                .build();

        root.right = rightChild;
        final AvlTree.Node leftGrandChild = AvlTree.Node.builder()
                .value(5)
                .parent(rightChild)
                .build();
        final AvlTree.Node rightGrandChild = AvlTree.Node.builder()
                .value(7)
                .parent(rightChild)
                .build();

        rightChild.left = leftGrandChild;
        rightChild.right = rightGrandChild;

        final AvlTree tree = new AvlTree();
        final AvlTree.Node rotated = tree.rotateLeft(root);

        assertThat(rotated.value).isEqualTo(6);
        assertThat(rotated.left.value).isEqualTo(4);
        assertThat(rotated.right.value).isEqualTo(7);
        assertThat(rotated.left.right.value).isEqualTo(5);

        assertThat(rotated.parent).isNull();
        assertThat(rotated.left.parent.value).isEqualTo(rotated.value);
        assertThat(rotated.right.parent.value).isEqualTo(rotated.value);
        assertThat(rotated.left.right.parent.value).isEqualTo(rotated.left.value);
    }

    @Test
    public void rotateRight() {
        final AvlTree.Node root = AvlTree.Node.builder()
                .value(8)
                .build();
        final AvlTree.Node leftChild = AvlTree.Node.builder()
                .value(6)
                .parent(root)
                .build();
        root.left = leftChild;

        final AvlTree.Node leftGrandChild = AvlTree.Node.builder()
                .value(5)
                .parent(leftChild)
                .build();
        final AvlTree.Node rightGrandChild = AvlTree.Node.builder()
                .value(7)
                .parent(leftChild)
                .build();
        leftChild.left = leftGrandChild;
        leftChild.right = rightGrandChild;

        final AvlTree tree = new AvlTree();
        final AvlTree.Node rotated = tree.rotateRight(root);

        assertThat(rotated.value).isEqualTo(6);
        assertThat(rotated.left.value).isEqualTo(5);
        assertThat(rotated.right.value).isEqualTo(8);
        assertThat(rotated.right.left.value).isEqualTo(7);

        assertThat(rotated.parent).isNull();
        assertThat(rotated.left.parent.value).isEqualTo(rotated.value);
        assertThat(rotated.right.parent.value).isEqualTo(rotated.value);
        assertThat(rotated.right.left.parent.value).isEqualTo(rotated.right.value);
    }

    @Test
    public void rotateRightLeft() {
        final AvlTree.Node root = AvlTree.Node.builder()
                .value(5)
                .parent(null)
                .build();
        final AvlTree.Node rightChild = AvlTree.Node.builder()
                .value(8)
                .parent(root)
                .build();
        root.right = rightChild;

        final AvlTree.Node leftGrandChild = AvlTree.Node.builder()
                .value(7)
                .parent(rightChild)
                .build();
        final AvlTree.Node rightGrandChild = AvlTree.Node.builder()
                .value(1)
                .parent( rightChild)
                .build();
        rightChild.left = leftGrandChild;
        rightChild.right = rightGrandChild;

        final AvlTree tree = new AvlTree();
        final AvlTree.Node rotated = tree.rotateRightLeft(root);

        assertThat(rotated.value).isEqualTo(7);
        assertThat(rotated.left.value).isEqualTo(5);
        assertThat(rotated.right.value).isEqualTo(8);
        assertThat(rotated.right.right.value).isEqualTo(10);

        assertThat(rotated.parent).isNull();
        assertThat(rotated.left.parent.value).isEqualTo(rotated.value);
        assertThat(rotated.right.parent.value).isEqualTo(rotated.value);
        assertThat(rotated.right.right.parent.value).isEqualTo(rotated.right.value);
    }

    @Test
    public void rotateLeftRight() {
        final AvlTree.Node root = AvlTree.Node.builder()
                .value(5)
                .parent(null)
                .build();
        final AvlTree.Node leftChild = AvlTree.Node.builder()
                .value(3)
                .parent(root)
                .build();
        root.left = leftChild;

        final AvlTree.Node leftGrandChild = AvlTree.Node.builder()
                .value(1)
                .parent(leftChild)
                .build();
        final AvlTree.Node rightGrandChild = AvlTree.Node.builder()
                .value(4)
                .parent(leftChild)
                .build();
        leftChild.left = leftGrandChild;
        leftChild.right = rightGrandChild;

        final AvlTree tree = new AvlTree();
        final AvlTree.Node rotated = tree.rotateLeftRight(root);

        assertThat(rotated.value).isEqualTo(4);
        assertThat(rotated.left.value).isEqualTo(3);
        assertThat(rotated.right.value).isEqualTo(5);
        assertThat(rotated.left.left.value).isEqualTo(1);

        assertThat(rotated.parent).isNull();
        assertThat(rotated.left.parent.value).isEqualTo(rotated.value);
        assertThat(rotated.right.parent.value).isEqualTo(rotated.value);
        assertThat(rotated.left.left.parent.value).isEqualTo(rotated.left.value);
    }

    void testProperties(final AvlTree.Node entry) {
        if (isNull(entry)) {
            return;
        }
        if (nonNull(entry.left)) {
            assertThat(entry.value).isGreaterThanOrEqualTo(entry.left.value);
        }
        if (nonNull(entry.right)) {
            assertThat(entry.value).isLessThanOrEqualTo(entry.right.value);
        }

        testProperties(entry.left);
        testProperties(entry.right);
    }
}