package com.winnerpeace.datastructures.tree;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static org.assertj.core.api.Assertions.assertThat;

@Disabled // Broken test
class RedBlackTreeTest {

    @Test
    public void insertionFixupColoring() {
        final RedBlackTree tree = new RedBlackTree();
        final RedBlackTree.Node node8 = tree.insert(8);
        final RedBlackTree.Node node3 = tree.insert(3);
        final RedBlackTree.Node node4 = tree.insert(4);

        assertThat(node3.color).isEqualTo(RedBlackTree.Color.RED);
        assertThat(node4.color).isEqualTo(RedBlackTree.Color.BLACK);
        assertThat(node8.color).isEqualTo(RedBlackTree.Color.RED);
    }

    @Test
    public void insert() {
        final RedBlackTree tree = new RedBlackTree();
        tree.insert(20);
        tree.insert(15);
        tree.insert(25);
        tree.insert(10);
        
        assertThat(tree.root.color).isEqualTo(RedBlackTree.Color.BLACK);
        assertThat(tree.search(15).color).isEqualTo(RedBlackTree.Color.BLACK);
        assertThat(tree.search(25).color).isEqualTo(RedBlackTree.Color.BLACK);
        tree.insert(17);
        tree.insert(8);

        assertThat(tree.search(15).color).isEqualTo(RedBlackTree.Color.RED);
        assertThat(tree.search(10).color).isEqualTo(RedBlackTree.Color.BLACK);
        assertThat(tree.search(17).color).isEqualTo(RedBlackTree.Color.BLACK);
        assertThat(tree.search(8).color).isEqualTo(RedBlackTree.Color.RED);

        tree.insert(9);

        assertThat(tree.search(10).color).isEqualTo(RedBlackTree.Color.RED);
        assertThat(tree.search(8).color).isEqualTo(RedBlackTree.Color.RED);
        assertThat(tree.search(9).left.value).isEqualTo(8);

        testProperties(tree.root);
    }

    @Test
    public void simpleDelete() {
        final RedBlackTree tree = new RedBlackTree();
        tree.insert(20);
        tree.insert(15);
        tree.insert(25);
        tree.insert(23);

        assertThat(tree.root.color).isEqualTo(RedBlackTree.Color.BLACK);

        tree.delete(15);

        assertThat(tree.root.value).isEqualTo(23);

        testProperties(tree.root);
    }

    @Test
    public void delete() {
        final RedBlackTree tree = new RedBlackTree();
        tree.insert(20);
        tree.insert(15);
        tree.insert(25);
        tree.insert(23);
        tree.insert(27);
        
        assertThat(tree.root.color).isEqualTo(RedBlackTree.Color.BLACK);
        assertThat(tree.root.right.value).isEqualTo(25);
        assertThat(tree.root.right.left.value).isEqualTo(23);
        assertThat(tree.root.right.left.color).isEqualTo(RedBlackTree.Color.RED);

        tree.delete(25);

        assertThat(tree.root.value).isEqualTo(20);
        assertThat(tree.root.right.value).isEqualTo(27);
        assertThat(tree.root.right.color).isEqualTo(RedBlackTree.Color.BLACK);
        assertThat(tree.root.right.left.value).isEqualTo(23);
        assertThat(tree.root.right.left.color).isEqualTo(RedBlackTree.Color.RED);

        testProperties(tree.root);
    }


    void testProperties(final RedBlackTree.Node entry) {
        if (isNull(entry) || entry == RedBlackTree.nil) {
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