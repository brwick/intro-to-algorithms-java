package org.brwick.algo.intro.avltrees;

import org.brwick.algo.intro.binarysearchtrees.Bst;
import org.brwick.algo.intro.binarysearchtrees.BstNode;

import java.util.Optional;

/**
 * Created on 6/24/16.
 */
public class Avl extends Bst {

    public Avl(int rootValue) {
        super(rootValue);
    }

    public static int calcHeight(Optional<BstNode> node) {
        if (node.isPresent()) {
            return node.get().getHeight();
        } else {
            return -1;
        }
    }

    public static void updateHeight(BstNode node) {
        node.setHeight(Math.max(calcHeight(node.getLeftChild()), calcHeight(node.getRightChild())) + 1);
    }

    public static void rebalance(Avl avl) {
        return;
    }

    public static void leftRotate(Avl avl, BstNode node) {
        final boolean replaceRoot = avl.getRoot().equals(node);
        final BstNode newParent = node.getRightChild().orElse(null);

        if (newParent != null) {
            final BstNode newRightChild = newParent.getLeftChild().orElse(null);
            if (newRightChild == null) {
                node.deleteRightChild();
            } else {
                node.setRightChild(newRightChild);
                newRightChild.setParent(node);
            }

            node.setParent(newParent);
            newParent.setLeftChild(node);

            avl.setRoot(newParent);
        }
    }

    public static void rightRotate(BstNode node) {
        final BstNode newParent = node.getLeftChild().orElse(null);
        if (newParent != null) {
            final BstNode newLeftChild = newParent.getRightChild().orElse(null);
            if (newLeftChild == null) {
                node.deleteLeftChild();;
            } else {
                node.setLeftChild(newLeftChild);
                newLeftChild.setParent(node);
            }

            node.setParent(newParent);
            newParent.setRightChild(node);
        }
    }
}
