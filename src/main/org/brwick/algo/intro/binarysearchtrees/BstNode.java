package org.brwick.algo.intro.binarysearchtrees;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Objects;
import java.util.Optional;

/**
 * Created on 6/4/16.
 */
public class BstNode {
    private int value;
    private int height;
    private Optional<BstNode> leftChild = Optional.empty();
    private Optional<BstNode> rightChild = Optional.empty();
    private Optional<BstNode> parent = Optional.empty();

    public BstNode(int nodeValue) {
        value = nodeValue;
    }

    public Optional<BstNode> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(BstNode leftChild) {
        this.leftChild = Optional.of(Objects.requireNonNull(leftChild));
    }

    public Optional<BstNode> getRightChild() {
        return rightChild;
    }

    public void setRightChild(BstNode rightChild) {
        this.rightChild = Optional.of(Objects.requireNonNull(rightChild));
    }

    public Optional<BstNode> getParent() {
        return parent;
    }

    public void setParent(BstNode parent) {
        this.parent = Optional.of(Objects.requireNonNull(parent));
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void deleteLeftChild() {
        this.leftChild = Optional.empty();
    }

    public void deleteRightChild() {
        this.rightChild = Optional.empty();
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(value)
                .append(leftChild)
                .append(rightChild)
                .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof BstNode)) {
            return false;
        }

        BstNode other = (BstNode)obj;
        return new EqualsBuilder()
                .append(value, other.getValue())
                .append(leftChild, other.getLeftChild())
                .append(rightChild, other.getRightChild())
                .isEquals();
    }
}
