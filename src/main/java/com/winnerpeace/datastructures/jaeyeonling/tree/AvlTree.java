package com.winnerpeace.datastructures.jaeyeonling.tree;

import lombok.Builder;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class AvlTree {

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

        reBalance(newNode);
        return newNode;
    }

    void reBalance(Node node) {
        while (nonNull(node)) {
            final Node parent = node.parent;

            final int leftHeight = isNull(node.left) ? - 1 : node.left.height;
            final int rightHeight = isNull(node.right) ? - 1 : node.right.height;
            final int balanceFactor = rightHeight - leftHeight;

            if (balanceFactor == 2) {
                if (nonNull(node.right.right)) {
                    node = rotateLeft(node);
                } else {
                    node = rotateRightLeft(node);
                }
            }
            if (balanceFactor == -2) {
                if (nonNull(node.left.left)) {
                    node = rotateRight(node);
                } else {
                    node = rotateLeftRight(node);
                }
            }

            updateHeight(node);
            node = parent;
        }
    }

    Node rotateLeft(final Node node) {
        final Node temp = node.right;
        temp.parent = node.parent;

        node.right = temp.left;
        if (nonNull(node.right)) {
            node.right.parent = node;
        }

        temp.left = node;
        node.parent = temp;

        if (nonNull(temp.parent)) {
            if (node == temp.parent.left) {
                temp.parent.left = temp;
            } else {
                temp.parent.right = temp;
            }
        } else {
            root = temp;
        }

        updateHeight(temp.left);
        updateHeight(temp);

        return temp;
    }

    Node rotateRight(final Node node) {
        final Node temp = node.left;
        temp.parent = node.parent;

        node.left = temp.right;
        if (nonNull(node.left)) {
            node.left.parent = node;
        }

        temp.right = node;
        node.parent = temp;

        if (nonNull(temp.parent)) {
            if (node == temp.parent.left) {
                temp.parent.left = temp;
            } else {
                temp.parent.right = temp;
            }
        } else {
            root = temp;
        }

        updateHeight(temp.right);
        updateHeight(temp);

        return temp;
    }

    Node rotateLeftRight(final Node node) {
        node.left = rotateLeft(node.left);
        return rotateRight(node);
    }

    Node rotateRightLeft(final Node node) {
        node.right = rotateRight(node.right);
        return rotateLeft(node);
    }

    void updateHeight(final Node node) {
        final int leftHeight = isNull(node.left) ? -1 : node.left.height;
        final int rightHeight = isNull(node.right) ? -1 : node.right.height;

        node.height = 1 + Math.max(leftHeight, rightHeight);
    }

    public Node delete(final int element) {
        final Node deleteNode = search(element);
        if (isNull(deleteNode)) {
            return null;
        }

        final Node successor = delete(deleteNode);
        if (nonNull(successor)) {
            final Node minimum = nonNull(successor.right) ? getMinimum(successor.right) : successor;
            recomputeHeight(minimum);
            reBalance(minimum);
        } else {
            recomputeHeight(deleteNode.parent);
            reBalance(deleteNode);
        }

        return successor;
    }

    void recomputeHeight(Node node) {
        while (nonNull(node)) {
            node.height = maxHeight(node.left, node.right) + 1;
            node = node.parent;
        }
    }

    int maxHeight(
            final Node node1,
            final Node node2
    ) {
        if (nonNull(node1) && nonNull(node2)) {
            return Math.max(node1.height, node2.height);
        }
        if (isNull(node1)) {
            return nonNull(node2) ? node2.height : -1;
        }
        if (isNull(node2)) {
            return nonNull(node1) ? node1.height : -1;
        }
        return -1;
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

        int height;

        Node parent;
        Node left;
        Node right;
    }
}
