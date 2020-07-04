package com.winnerpeace.datastructures.chapter6.tree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

class UnbalancedBinarySearchTreeTest {

    private static final int START = 1;
    private static final int END = 10;

    private BinarySearchTree<Integer> tree;

    @BeforeEach
    void setUp() {
        final List<Integer> values = IntStream.rangeClosed(START, END)
                .boxed()
                .collect(toList());

        tree = UnbalancedBinarySearchTree.create(values);
    }

    @Test
    void exists() {
        assertThat(tree.exists(START)).isTrue();
    }

    @Test
    void underExists() {
        assertThat(tree.exists(START - 1)).isFalse();
    }

    @Test
    void overExists() {
        assertThat(tree.exists(END + 1)).isFalse();
    }
}