package org.brwick.algo.intro.avltrees;

import org.brwick.algo.intro.binarysearchtrees.BinaryTreeFactory;
import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created on 6/25/2016.
 */
public class AvlTest {
    @Test
    public void testCalcHeight() {
        Avl avl = BinaryTreeFactory.createBasicThreeNodeAvl();
        assertThat(Avl.calcHeight(Optional.of(avl.getRoot())), is(1));
        assertThat(Avl.calcHeight(avl.getRoot().getLeftChild()), is(0));
        assertThat(Avl.calcHeight(avl.getRoot().getRightChild()), is(0));
        assertThat(Avl.calcHeight(avl.getRoot().getLeftChild().get().getLeftChild()), is(-1));
        assertThat(Avl.calcHeight(avl.getRoot().getLeftChild().get().getRightChild()), is(-1));
    }

    @Test
    public void testUpdateHeight() {
        Avl avl = BinaryTreeFactory.createBasicThreeNodeAvlWithoutHeights();

        Avl.updateHeight(avl.getRoot().getLeftChild().get());
        Avl.updateHeight(avl.getRoot().getRightChild().get());
        Avl.updateHeight(avl.getRoot());

        assertThat(Avl.calcHeight(avl.getRoot().getLeftChild()), is(0));
        assertThat(Avl.calcHeight(avl.getRoot().getRightChild()), is(0));
        assertThat(Avl.calcHeight(Optional.of(avl.getRoot())), is(1));
        assertThat(Avl.calcHeight(avl.getRoot().getLeftChild().get().getLeftChild()), is(-1));
        assertThat(Avl.calcHeight(avl.getRoot().getLeftChild().get().getRightChild()), is(-1));
    }
}
