package com.winnerpeace.datastructures.tree;

import com.winnerpeace.datastructures.queue.LinkedListQueue;
import com.winnerpeace.datastructures.queue.Queue;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.jetbrains.annotations.Nullable;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

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

    public void levelOrder() {
        if (isNull(root)) {
            return;
        }

        final Queue<Node<E>> nodes = new LinkedListQueue<>();

        Node<E> node = root;
        while (nonNull(node)) {
            System.out.println(node.getValue());

            node.ifPresentLeft(nodes::offer);
            node.ifPresentRight(nodes::offer);

            node = nodes.poll();
        }
    }
}
