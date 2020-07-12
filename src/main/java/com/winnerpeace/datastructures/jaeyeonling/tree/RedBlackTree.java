package com.winnerpeace.datastructures.jaeyeonling.tree;

import lombok.Builder;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/*
반드시 지켜져야 할 5가지 규칙
1. 모든 노드는 Red or Black
2. 루트 노드는 반드시 Black
3. 레드는 경로 상에 연달아 올 수 없음
4. 최종 노드에는 비어있는 리프노드 2개가 붙으며, 이는 Black
5. 이 모든 규칙을 지킬 시 루트부터 리프노드에 이르는 경로에서 만나는 블랙 노드의 수는 항상 같음
*/
public class RedBlackTree {

    static final Node nil = Node.builder()
            .color(Color.BLACK)
            .build();

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
                    .color(Color.BLACK)
                    .left(nil)
                    .right(nil)
                    .parent(nil)
                    .build();
            return root;
        }

        Node parentNode = null;
        Node node = root;
        while (nonNull(node) && node != nil) {
            parentNode = node;
            node = element < node.value ? node.left : node.right;
        }

        final Node newNode = Node.builder()
                .value(element)
                .parent(parentNode)
                .left(nil)
                .right(nil)
                .color(Color.RED)
                .build();
        if (parentNode.value > newNode.value) {
            parentNode.left = newNode;
        } else {
            parentNode.right = newNode;
        }

        root.parent = nil;
        insertFixUp(newNode);

        return newNode;
    }

    void insertFixUp(Node currentNode) {
        while (currentNode.parent != root && currentNode.parent.color == Color.RED) {
            Node parent = currentNode.parent;
            final Node grandParent = parent.parent;

            if (parent == grandParent.left) {
                final Node uncle = grandParent.right;

                if (uncle.color == Color.RED) {
                    parent.color = Color.BLACK;
                    uncle.color = Color.BLACK;
                    grandParent.color = Color.RED;
                    currentNode = grandParent;
                } else {
                    if (currentNode == parent.right) {
                        currentNode = parent;
                        rotateLeft(currentNode);
                        parent = currentNode.parent;
                    }

                    parent.color = Color.BLACK;
                    grandParent.color = Color.RED;
                    rotateRight(grandParent);
                }
            } else if (parent == grandParent.right) {
                final Node uncle = grandParent.left;

                if (uncle.color == Color.RED) {
                    parent.color = Color.BLACK;
                    uncle.color = Color.BLACK;
                    grandParent.color = Color.RED;
                    currentNode = grandParent;
                } else {
                    if (currentNode == parent.left) {
                        currentNode = parent;
                        rotateRight(currentNode);
                        parent = currentNode.parent;
                    }

                    parent.color = Color.BLACK;
                    grandParent.color = Color.RED;
                    rotateLeft(grandParent);
                }
            }
        }

        root.color = Color.BLACK;
    }

    public Node delete(final int element) {
        return delete(search(element));
    }

    Node delete(final Node deleteNode) {
        if (isNull(deleteNode) || deleteNode == nil) {
            return deleteNode;
        }

        Node replaceNode;
        Color removeOrMoveNodeColor = deleteNode.color;

        if (deleteNode.left == nil) {
            replaceNode = deleteNode.right;
            transplant(deleteNode, deleteNode.right);
        } else if (deleteNode.right == nil) {
            replaceNode = deleteNode.left;
            transplant(deleteNode, deleteNode.left);
        } else {
            final Node removeOrMoveNode = getMinimum(deleteNode.right);
            removeOrMoveNodeColor = removeOrMoveNode.color;
            replaceNode = removeOrMoveNode.right;

            if (removeOrMoveNode.parent == deleteNode) {
                replaceNode.parent = removeOrMoveNode;
            } else {
                transplant(removeOrMoveNode, removeOrMoveNode.right);
                removeOrMoveNode.right = deleteNode.right;
                removeOrMoveNode.right.parent = removeOrMoveNode;
            }

            transplant(deleteNode, removeOrMoveNode);
            removeOrMoveNode.left = deleteNode.left;
            removeOrMoveNode.left.parent = removeOrMoveNode;
            removeOrMoveNode.color = deleteNode.color;
        }

        if (removeOrMoveNodeColor == Color.BLACK) {
            deleteFixUp(replaceNode);
        }

        return replaceNode;
    }

    void deleteFixUp(Node node) {
        while (node != root && isBlack(node)) {
            if (node == node.parent.left) {
                Node sibling = node.parent.right;
                if (isRed(sibling)) {
                    sibling.color = Color.BLACK;
                    node.parent.color = Color.RED;
                    rotateLeft(node.parent);
                    sibling = node.parent.right;
                }

                if (isBlack(sibling.left) && isBlack(sibling.right)) {
                    sibling.color = Color.RED;
                    node = sibling.parent;
                } else if (sibling != nil) {
                    if (isBlack(sibling.right)) {
                        sibling.left.color = Color.BLACK;
                        sibling.color = Color.RED;
                        rotateRight(sibling);

                        sibling = node.parent.right;
                    }

                    sibling.color = node.parent.color;
                    node.parent.color = Color.BLACK;
                    sibling.right.color = Color.BLACK;

                    rotateLeft(node.parent);
                    node = root;
                } else {
                    node.color = Color.BLACK;
                    node = node.parent;
                }

                continue;
            }

            Node sibling = node.parent.left;
            if (isRed(sibling)) {
                sibling.color = Color.BLACK;
                node.parent.color = Color.RED;

                rotateRight(node.parent);

                sibling = node.parent.left;
            }

            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                sibling.color = Color.RED;
                node = node.parent;
            } else if (sibling != nil) {
                if (isBlack(sibling.left)) {
                    sibling.right.color = Color.BLACK;
                    sibling.color = Color.BLACK;
                    rotateLeft(sibling);
                    sibling = node.parent.left;
                }

                sibling.color = node.parent.color;
                node.parent.color = Color.BLACK;
                sibling.left.color = Color.BLACK;
                rotateRight(node.parent);

                node = root;
            } else {
                node.color = Color.BLACK;
                node = node.parent;
            }
        }
    }

    boolean isBlack(final Node node) {
        if (isNull(node)) {
            return false;
        }

        return node.color == Color.BLACK;
    }

    boolean isRed(final Node node) {
        if (isNull(node)) {
            return false;
        }

        return node.color == Color.RED;
    }

    void transplant(
            final Node replaceTargetNode,
            final Node newNode
    ) {
        if (replaceTargetNode.parent == nil) {
            root = newNode;
        } else if (replaceTargetNode == replaceTargetNode.parent.left) {
            replaceTargetNode.parent.left = newNode;
        } else {
            replaceTargetNode.parent.right = newNode;
        }

        newNode.parent = replaceTargetNode.parent;
    }

    Node getMinimum(Node node) {
        while (nonNull(node.left)) {
            node = node.left;
        }

        return node;
    }

    void rotateLeft(final Node node) {
        final Node temp = node.right;
        temp.parent = node.parent;

        node.right = temp.left;
        if (node.right != nil) {
            node.right.parent = node;
        }

        temp.left = node;
        node.parent = temp;

        if (temp.parent != node.right) {
            if (node == temp.parent.left) {
                temp.parent.left = temp;
            } else {
                temp.parent.right = temp;
            }
        } else {
            root = temp;
        }
    }

    void rotateRight(final Node node) {
        final Node temp = node.left;
        temp.parent = node.parent;

        node.left = temp.right;
        if (node.left != node.right) {
            node.left.parent = node;
        }

        temp.right = node;
        node.parent = temp;

        if (temp.parent != node.right) {
            if (node == temp.parent.left) {
                temp.parent.left = temp;
            } else {
                temp.parent.right = temp;
            }
        } else {
            root = temp;
        }
    }

    @Builder
    static class Node {

        final int value;

        Color color;

        Node parent;
        Node left;
        Node right;
    }

    enum Color {
        RED,
        BLACK
    }
}