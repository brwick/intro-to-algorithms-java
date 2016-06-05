package org.brwick.algo.intro.binarysearchtrees;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Created on 6/4/16.
 */
public class BstTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testCheckInvariant() {
        Bst bst = new Bst(20);
        bst.getRoot().setLeftChild(new BstNode(10));
        bst.getRoot().setRightChild(new BstNode(30));

        bst.checkTreeInvariant();
    }

    @Test
    public void testCheckInvariant_fail() {
        Bst bst = new Bst(20);
        bst.getRoot().setLeftChild(new BstNode(25));
        bst.getRoot().setRightChild(new BstNode(30));

        thrown.expect(IllegalStateException.class);
        thrown.expectMessage(Matchers.startsWith("Left child is greater"));

        bst.checkTreeInvariant();
    }
}
