package org.brwick.algo.intro.binarysearchtrees;

/**
 * Created on 6/15/16.
 */
public class BaseBstTest {
    /**
     * @return root: 20, left: 10, right: 30
     */
    protected Bst createBasicThreeNodeBst() {
        Bst bst = new Bst(20);
        bst.getRoot().setLeftChild(new BstNode(10));
        bst.getRoot().getLeftChild().get().setParent(bst.getRoot());
        bst.getRoot().setRightChild(new BstNode(30));
        bst.getRoot().getRightChild().get().setParent(bst.getRoot());

        return bst;
    }

    /**
     * @return                    49
     *                  46                    79
     *           43            x       64            83
     */
    protected Bst createSixNodeBst() {
        Bst bst = new Bst(49);
        bst.insert(new BstNode(46));
        bst.insert(new BstNode(79));
        bst.insert(new BstNode(43));
        bst.insert(new BstNode(64));
        bst.insert(new BstNode(83));

        return bst;
    }

    /**
     * @return                    23
     *                  8                    42
     *           4            17
     *                      15   x
     *                     x 16 x x
     */
    protected Bst createSevenNodeBst() {
        Bst bst = new Bst(23);
        bst.insert(new BstNode(42));
        bst.insert(new BstNode(8));
        bst.insert(new BstNode(4));
        bst.insert(new BstNode(17));
        bst.insert(new BstNode(15));
        bst.insert(new BstNode(16));

        return bst;
    }
}
