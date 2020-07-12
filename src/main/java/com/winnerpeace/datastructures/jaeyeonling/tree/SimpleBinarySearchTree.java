package com.winnerpeace.datastructures.jaeyeonling.tree;

import lombok.Builder;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class SimpleBinarySearchTree {

    Node root;

    Node search(final int element) {
        Node node = root;

        while (nonNull(node) && node.value != element) {
            node = element < node.value ? node.left : node.right;
        }

        return node;
    }

    Node insert(final int element) {
        if (isNull(root)) {
            root = Node.builder()
                    .value(element)
                    .build();
            return root;
        }

        Node parentNode = null;
        Node node = root;
        while (nonNull(node)) {
            parentNode = node;
            node = element < node.value ? node.left : node.right;
        }

        final Node newNode = Node.builder()
                .value(element)
                .parent(parentNode)
                .build();
        if (parentNode.value > newNode.value) {
            parentNode.left = newNode;
        } else {
            parentNode.right = newNode;
        }

        return newNode;
    }

    public Node delete(final int element) {
        return delete(search(element));
    }

    Node delete(final Node deleteNode) {
        if (isNull(deleteNode)) {
            return null;
        }
        if (isNull(deleteNode.left)) {
            return transplant(deleteNode, deleteNode.right);
        }
        if (isNull(deleteNode.right)) {
            return transplant(deleteNode, deleteNode.left);
        }

        final Node successor = getMinimum(deleteNode.right);
        if (successor.parent != deleteNode) {
            transplant(successor, successor.right);
            successor.right = deleteNode.right;
            successor.right.parent = successor;
        }

        transplant(deleteNode, successor);
        successor.left = deleteNode.left;
        successor.left.parent = successor;

        return successor;
    }

    Node transplant(
            final Node replaceTargetNode,
            final Node newNode
    ) {
        if (isNull(replaceTargetNode.parent)) {
            root = newNode;
        } else if (replaceTargetNode == replaceTargetNode.parent.left) {
            replaceTargetNode.parent.left = newNode;
        } else {
            replaceTargetNode.parent.right = newNode;
        }

        if (nonNull(newNode)) {
            newNode.parent = replaceTargetNode.parent;
        }

        return newNode;
    }

    Node getMinimum(Node node) {
        while (nonNull(node.left)) {
            node = node.left;
        }

        return node;
    }

    @Builder
    static class Node {

        final int value;

        Node parent;
        Node left;
        Node right;
    }
}
