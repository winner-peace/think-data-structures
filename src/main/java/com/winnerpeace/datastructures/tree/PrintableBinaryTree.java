package com.winnerpeace.datastructures.tree;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.jetbrains.annotations.Nullable;

import static java.util.Objects.isNull;

@ToString
@EqualsAndHashCode
@RequiredArgsConstructor(staticName = "of")
public final class PrintableBinaryTree<E> implements BinaryTree<E> {

    @Nullable
    private final Node<E> root;

    public void inOrder() {
        inOrder(root);
    }

    public void preOrder() {
        preOrder(root);
    }

    public void postOrder() {
        postOrder(root);
    }

    public void inOrder(final Node<E> node) {
        if (isNull(node)) {
            return;
        }

        inOrder(node.getLeft());
        System.out.println(node.getValue());
        inOrder(node.getRight());
    }

    public void preOrder(final Node<E> node) {
        if (isNull(node)) {
            return;
        }

        System.out.println(node.getValue());
        preOrder(node.getLeft());
        preOrder(node.getRight());
    }

    public void postOrder(final Node<E> node) {
        if (isNull(node)) {
            return;
        }

        postOrder(node.getLeft());
        postOrder(node.getRight());
        System.out.println(node.getValue());
    }
}
