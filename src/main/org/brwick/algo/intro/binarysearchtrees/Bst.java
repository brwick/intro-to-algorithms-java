package org.brwick.algo.intro.binarysearchtrees;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;

/**
 * Created on 6/4/16.
 */
public class Bst {
    private BstNode root;

    public Bst(int rootValue) {
        root = new BstNode(rootValue);
    }

    public void checkTreeInvariant() {
        checkInvariant(root);
    }

    public static void checkInvariant(BstNode bstNode) {
        if (bstNode.getLeftChild().isPresent()) {
            Preconditions.checkState(bstNode.getLeftChild().get().getValue() <= bstNode.getValue(),
                    "Left child is greater (%s) than node (%s)", new String[]{Integer.toString(bstNode.getLeftChild().get().getValue()),
                            Integer.toString(bstNode.getValue())});
            checkInvariant(bstNode.getLeftChild().get());
        }
        if (bstNode.getRightChild().isPresent()) {
            Preconditions.checkState(bstNode.getRightChild().get().getValue() >= bstNode.getValue(),
                    "Right child is greater (%s) than node (%s)", new String[]{Integer.toString(bstNode.getRightChild().get().getValue()),
                            Integer.toString(bstNode.getValue())});
            checkInvariant(bstNode.getRightChild().get());
        }
    }

    @VisibleForTesting
    public BstNode getRoot() {
        return root;
    }
}
