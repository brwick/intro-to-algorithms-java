package org.brwick.algo.intro.binarysearchtrees;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created on 6/4/16.
 */
public class BstTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testCheckInvariant() {
        BinaryTreeFactory.createBasicThreeNodeBst().checkBstRi();
    }

    @Test
    public void testCheckInvariant_fail() {
        Bst bst = new Bst(20);
        bst.getRoot().setLeftChild(new BstNode(25));
        bst.getRoot().setRightChild(new BstNode(30));

        thrown.expect(IllegalStateException.class);
        thrown.expectMessage(Matchers.startsWith("Left child is greater"));

        bst.checkBstRi();
    }

    @Test
    public void testInsertLeft() {
        Bst bst = BinaryTreeFactory.createBasicThreeNodeBst();
        bst.insert(new BstNode(15));

        bst.checkBstRi();
        assertFalse(bst.getRoot().getLeftChild().get().getLeftChild().isPresent());
        assertTrue(bst.getRoot().getLeftChild().get().getRightChild().isPresent());
        assertTrue(bst.getRoot().getLeftChild().get().getRightChild().get().getParent().equals(bst.getRoot().getLeftChild()));
        assertThat(bst.getRoot().getLeftChild().get().getRightChild().get().getValue(), is(15));
    }

    @Test
    public void testInsertRight() {
        Bst bst = BinaryTreeFactory.createBasicThreeNodeBst();
        bst.insert(new BstNode(25));

        bst.checkBstRi();
        assertTrue(bst.getRoot().getRightChild().get().getLeftChild().isPresent());
        assertFalse(bst.getRoot().getRightChild().get().getRightChild().isPresent());
        assertTrue(bst.getRoot().getRightChild().get().getLeftChild().get().getParent().equals(bst.getRoot().getRightChild()));
        assertThat(bst.getRoot().getRightChild().get().getLeftChild().get().getValue(), is(25));
    }

    @Test
    public void testSearch_Root() {
        Bst bst = BinaryTreeFactory.createSixNodeBst();
        Optional<BstNode> found = bst.find(49);
        assertFalse(found.get().getParent().isPresent());
    }

    @Test
    public void testSearch_LeftSubTree() {
        Bst bst = BinaryTreeFactory.createSixNodeBst();
        Optional<BstNode> found = bst.find(43);
        assertThat(found.get().getParent().get().getValue(), is(46));
    }

    @Test
    public void testSearch_RightSubTree() {
        Bst bst = BinaryTreeFactory.createSixNodeBst();
        Optional<BstNode> found = bst.find(64);
        assertThat(found.get().getParent().get().getValue(), is(79));
    }

    @Test
    public void testSearch_NotFound() {
        Bst bst = BinaryTreeFactory.createSixNodeBst();
        Optional<BstNode> found = bst.find(63);
        assertFalse(found.isPresent());
    }

    @Test
    public void testFindMin() {
        Bst bst = BinaryTreeFactory.createSixNodeBst();
        assertThat(bst.findMin().getValue(), is(43));
    }

    @Test
    public void testFindMin_Root() {
        Bst bst = BinaryTreeFactory.createSixNodeBst();
        bst.getRoot().deleteLeftChild();
        assertThat(bst.findMin().getValue(), is(49));
    }

    @Test
    public void testFindMax() {
        Bst bst = BinaryTreeFactory.createSixNodeBst();
        assertThat(bst.findMax().getValue(), is(83));
    }

    @Test
    public void testFindMax_Root() {
        Bst bst = BinaryTreeFactory.createSixNodeBst();
        bst.getRoot().deleteRightChild();
        assertThat(bst.findMax().getValue(), is(49));
    }

    @Test
    public void testNextLarger_none() {
        Bst bst = BinaryTreeFactory.createSixNodeBst();
        BstNode node = bst.getRoot().getRightChild().get().getRightChild().get();
        Optional<BstNode> next = Bst.findNextLarger(node);
        assertFalse(next.isPresent());
    }

    @Test
    public void testNextLarger_rightSubTree() {
        Bst bst = BinaryTreeFactory.createSixNodeBst();
        BstNode node = bst.getRoot().getRightChild().get();
        Optional<BstNode> next = Bst.findNextLarger(node);
        assertTrue(next.isPresent());
        assertThat(next.get().getValue(), is(83));
    }

    @Test
    public void testNextLarger_leftSubTree() {
        Bst bst = BinaryTreeFactory.createSixNodeBst();
        BstNode node = bst.getRoot().getLeftChild().get();
        Optional<BstNode> next = Bst.findNextLarger(node);
        assertTrue(next.isPresent());
        assertThat(next.get().getValue(), is(49));
    }

    @Test
    public void testNextSmaller_none() {
        Bst bst = BinaryTreeFactory.createSixNodeBst();
        BstNode node = bst.getRoot().getLeftChild().get().getLeftChild().get();
        Optional<BstNode> next = Bst.findNextSmaller(node);
        assertFalse(next.isPresent());
    }

    @Test
    public void testNextSmaller_rightSubTree() {
        Bst bst = BinaryTreeFactory.createSixNodeBst();
        BstNode node = bst.getRoot().getRightChild().get();
        Optional<BstNode> next = Bst.findNextSmaller(node);
        assertTrue(next.isPresent());
        assertThat(next.get().getValue(), is(64));
    }

    @Test
    public void testNextSmaller_leftSubTree() {
        Bst bst = BinaryTreeFactory.createSixNodeBst();
        BstNode node = bst.getRoot().getLeftChild().get();
        Optional<BstNode> next = Bst.findNextSmaller(node);
        assertTrue(next.isPresent());
        assertThat(next.get().getValue(), is(43));
    }
}
