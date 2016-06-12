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
        this.createBasicThreeNodeBst().checkTreeInvariant();
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

    @Test
    public void testInsertLeft() {
        Bst bst = this.createBasicThreeNodeBst();
        bst.insert(new BstNode(15));

        bst.checkTreeInvariant();
        assertFalse(bst.getRoot().getLeftChild().get().getLeftChild().isPresent());
        assertTrue(bst.getRoot().getLeftChild().get().getRightChild().isPresent());
        assertTrue(bst.getRoot().getLeftChild().get().getRightChild().get().getParent().equals(bst.getRoot().getLeftChild()));
        assertThat(bst.getRoot().getLeftChild().get().getRightChild().get().getValue(), is(15));
    }

    @Test
    public void testInsertRight() {
        Bst bst = this.createBasicThreeNodeBst();
        bst.insert(new BstNode(25));

        bst.checkTreeInvariant();
        assertTrue(bst.getRoot().getRightChild().get().getLeftChild().isPresent());
        assertFalse(bst.getRoot().getRightChild().get().getRightChild().isPresent());
        assertTrue(bst.getRoot().getRightChild().get().getLeftChild().get().getParent().equals(bst.getRoot().getRightChild()));
        assertThat(bst.getRoot().getRightChild().get().getLeftChild().get().getValue(), is(25));
    }

    @Test
    public void testSearch_Root() {
        Bst bst = createSixNodeBst();
        Optional<BstNode> found = bst.find(49);
        assertFalse(found.get().getParent().isPresent());
    }

    @Test
    public void testSearch_LeftSubTree() {
        Bst bst = createSixNodeBst();
        Optional<BstNode> found = bst.find(43);
        assertThat(found.get().getParent().get().getValue(), is(46));
    }

    @Test
    public void testSearch_RightSubTree() {
        Bst bst = createSixNodeBst();
        Optional<BstNode> found = bst.find(64);
        assertThat(found.get().getParent().get().getValue(), is(79));
    }

    @Test
    public void testSearch_NotFound() {
        Bst bst = createSixNodeBst();
        Optional<BstNode> found = bst.find(63);
        assertFalse(found.isPresent());
    }

    @Test
    public void testFindMin() {
        Bst bst = createSixNodeBst();
        assertThat(bst.findMin().getValue(), is(43));
    }

    @Test
    public void testFindMin_Root() {
        Bst bst = createSixNodeBst();
        bst.getRoot().deleteLeftChild();
        assertThat(bst.findMin().getValue(), is(49));
    }

    @Test
    public void testFindMax() {
        Bst bst = createSixNodeBst();
        assertThat(bst.findMax().getValue(), is(83));
    }

    @Test
    public void testFindMax_Root() {
        Bst bst = createSixNodeBst();
        bst.getRoot().deleteRightChild();
        assertThat(bst.findMax().getValue(), is(49));
    }

    /**
     * @return root: 20, left: 10, right: 30
     */
    private Bst createBasicThreeNodeBst() {
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
    private Bst createSixNodeBst() {
        Bst bst = new Bst(49);
        bst.insert(new BstNode(46));
        bst.insert(new BstNode(79));
        bst.insert(new BstNode(43));
        bst.insert(new BstNode(64));
        bst.insert(new BstNode(83));

        return bst;
    }
}
