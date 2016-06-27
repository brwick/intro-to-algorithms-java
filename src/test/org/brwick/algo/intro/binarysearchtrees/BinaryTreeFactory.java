package org.brwick.algo.intro.binarysearchtrees;

import org.brwick.algo.intro.avltrees.Avl;

/**
 * Created on 6/15/16.
 */
public class BinaryTreeFactory {
    private BinaryTreeFactory() {}

    /**
     * @return root: 20, left: 10, right: 30
     */
    public static Bst createBasicThreeNodeBst() {
        Bst bst = new Bst(20);
        addTwoChildren(bst);

        return bst;
    }

    private static void addTwoChildren(Bst bst) {
        bst.getRoot().setLeftChild(new BstNode(10));
        bst.getRoot().getLeftChild().get().setParent(bst.getRoot());
        bst.getRoot().setRightChild(new BstNode(30));
        bst.getRoot().getRightChild().get().setParent(bst.getRoot());
    }

    public static Avl createBasicThreeNodeAvlWithoutHeights() {
        Avl avl = new Avl(20);
        addTwoChildren(avl);

        return avl;
    }

    public static Avl createBasicThreeNodeAvl() {
        Avl avl = new Avl(20);
        addTwoChildren(avl);
        avl.getRoot().setHeight(1);
        avl.getRoot().getLeftChild().get().setHeight(0);
        avl.getRoot().getRightChild().get().setHeight(0);

        return avl;
    }

    public static Avl createLeftRotateableAvl() {
        Avl avl = createBasicThreeNodeAvl();
        BstNode rightChild = avl.getRoot().getRightChild().get();
        rightChild.setLeftChild(new BstNode(25));
        rightChild.getLeftChild().get().setHeight(0);
        rightChild.setRightChild(new BstNode(35));
        rightChild.getRightChild().get().setHeight(0);
        rightChild.setHeight(1);
        avl.getRoot().getLeftChild().get().setHeight(1);
        avl.getRoot().setHeight(2);

        return avl;
    }

    public static Avl createLeftRotateableAvl_noLeftChild() {
        Avl avl = createBasicThreeNodeAvl();
        BstNode rightChild = avl.getRoot().getRightChild().get();
        rightChild.setRightChild(new BstNode(35));
        rightChild.getRightChild().get().setHeight(0);
        rightChild.setHeight(1);
        avl.getRoot().getLeftChild().get().setHeight(1);
        avl.getRoot().setHeight(2);

        return avl;
    }

    /**
     * @return                    49
     *                  46                    79
     *           43            x       64            83
     */
    public static Bst createSixNodeBst() {
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
    public static Bst createSevenNodeBst() {
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
