package org.brwick.algo.intro.binarysearchtrees;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;

import java.util.Optional;

/**
 * Created on 6/4/16.
 */
public class Bst {
    private BstNode root;

    public Bst(int rootValue) {
        root = new BstNode(rootValue);
    }

    public void checkBstRi() {
        checkBstNodeRi(root);
    }

    public Optional<BstNode> find(int value) {
        if (root.getValue() == value) {
            return Optional.of(root);
        } else if (value < root.getValue()) {
            return search(root.getLeftChild(), value);
        } else {
            return search(root.getRightChild(), value);
        }
    }

    private static Optional<BstNode> search(Optional<BstNode> node, int value) {
        if (!node.isPresent()) {
            return Optional.empty();
        } else if (value == node.get().getValue()) {
            return node;
        } else if (value < node.get().getValue()) {
            return search(node.get().getLeftChild(), value);
        } else {
            return search(node.get().getRightChild(), value);
        }
    }

    public BstNode findMin() {
        return findMinValuedNode(root);
    }

    public static BstNode findMinValuedNode(BstNode node) {
        if (!node.getLeftChild().isPresent()) {
            return node;
        } else {
            return findMinValuedNode(node.getLeftChild().get());
        }
    }

    public BstNode findMax() {
        return findMaxValuedNode(root);
    }

    public static BstNode findMaxValuedNode(BstNode node) {
        if (!node.getRightChild().isPresent()) {
            return node;
        } else {
            return findMaxValuedNode(node.getRightChild().get());
        }
    }

    public void insert(BstNode nodeToInsert) {
        if (nodeToInsert.getValue() <= root.getValue()) {
            insertLeft(root, nodeToInsert);
        } else {
            insertRight(root, nodeToInsert);
        }
    }

    private static void insertLeft(BstNode parentNode, BstNode nodeToInsert) {
        if (parentNode.getLeftChild().isPresent()) {
            BstNode leftChild = parentNode.getLeftChild().get();
            if (nodeToInsert.getValue() <= leftChild.getValue()) {
                insertLeft(leftChild, nodeToInsert);
            } else {
                insertRight(leftChild, nodeToInsert);
            }
        } else {
            nodeToInsert.setParent(parentNode);
            parentNode.setLeftChild(nodeToInsert);
        }
    }

    private static void insertRight(BstNode parentNode, BstNode nodeToInsert) {
        if (parentNode.getRightChild().isPresent()) {
            BstNode rightChild = parentNode.getRightChild().get();
            if (nodeToInsert.getValue() <= rightChild.getValue()) {
                insertLeft(rightChild, nodeToInsert);
            } else {
                insertRight(rightChild, nodeToInsert);
            }
        } else {
            nodeToInsert.setParent(parentNode);
            parentNode.setRightChild(nodeToInsert);
        }
    }

    public static void checkBstNodeRi(BstNode bstNode) {
        if (bstNode.getLeftChild().isPresent()) {
            Preconditions.checkState(bstNode.getLeftChild().get().getValue() <= bstNode.getValue(),
                    "Left child is greater (%s) than node (%s)", new String[]{Integer.toString(bstNode.getLeftChild().get().getValue()),
                            Integer.toString(bstNode.getValue())});
            checkBstNodeRi(bstNode.getLeftChild().get());
        }
        if (bstNode.getRightChild().isPresent()) {
            Preconditions.checkState(bstNode.getRightChild().get().getValue() >= bstNode.getValue(),
                    "Right child is greater (%s) than node (%s)", new String[]{Integer.toString(bstNode.getRightChild().get().getValue()),
                            Integer.toString(bstNode.getValue())});
            checkBstNodeRi(bstNode.getRightChild().get());
        }
    }

    public BstNode getRoot() {
        return root;
    }

    public static Optional<BstNode> findNextLarger(BstNode node) {
        if (node.getRightChild().isPresent()) {
            return Optional.of(findMinValuedNode(node.getRightChild().get()));
        } else {
            BstNode current = node;
            while (current.getParent().isPresent() && current.getParent().get().getRightChild().isPresent() &&
                    current.equals(current.getParent().get().getRightChild().get())) {
                current = current.getParent().get();
            }
            return current.getParent();
        }
    }

    public static Optional<BstNode> findNextSmaller(BstNode node) {
        if (node.getLeftChild().isPresent()) {
            return Optional.of(findMaxValuedNode(node.getLeftChild().get()));
        } else {
            BstNode current = node;
            while (current.getParent().isPresent() && current.getParent().get().getLeftChild().isPresent() &&
                    current.equals(current.getParent().get().getLeftChild().get())) {
                current = current.getParent().get();
            }
            return current.getParent();
        }
    }

    public static void deleteNode(BstNode node) {
        Preconditions.checkArgument(node.getParent().isPresent(), "Cannot delete root node of tree. Delete tree instead");

        if (!node.getLeftChild().isPresent() && !node.getRightChild().isPresent()) {
            if (isLeftChild(node.getParent().get(), node)) {
                node.getParent().get().deleteLeftChild();
            } {
                node.getParent().get().deleteRightChild();
            }
        } else if (!node.getLeftChild().isPresent() || !node.getRightChild().isPresent()) {
            if (isLeftChild(node.getParent().get(), node)) {
                node.getParent().get().setLeftChild(node.getLeftChild().isPresent() ? node.getLeftChild().get() :
                        node.getRightChild().get());
            } else {
                node.getParent().get().setRightChild(node.getLeftChild().isPresent() ? node.getLeftChild().get() :
                        node.getRightChild().get());
            }
        } else {
            Optional<BstNode> replacement = findNextLarger(node);
            node.setValue(replacement.get().getValue());
            deleteNode(replacement.get());
        }
    }

    private static boolean isLeftChild(BstNode parent, BstNode child) {
        return parent.getLeftChild().isPresent() && parent.getLeftChild().get().equals(child);
    }
}
