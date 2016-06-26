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
}
