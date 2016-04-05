package org.brwick.algo.intro.heapsort;

import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Array;

/**
 * Created on 2/11/2016.
 */
public class Heap <T extends Comparable> {

    private Comparable[] heapArray;

    public Heap(Class<T> clazz, int size) {
        heapArray = (T[])Array.newInstance(clazz, size);
    }

    public int parent(int i) {
        if (i > heapArray.length || i < 1) {
            throw new IndexOutOfBoundsException("Node index was out of bounds");
        }
        return i/2;
    }

    public void insert(int index, Comparable value) {
        heapArray[index - 1] = value;
    }

    public Comparable getRoot() {
        return heapArray[0];
    }

    public Comparable get(int index) {
        return heapArray[index - 1];
    }

    public int getLeft(int index) {
        return 2*index;
    }


    public int getRight(int index) {
        return 2*index + 1;
    }

    public int getSize() {
        return heapArray.length;
    }

    public Heap copy() {
        final Heap copy = new Heap(heapArray[0].getClass(), heapArray.length);
        for (int i = 1; i <= this.getSize(); i++) {
            copy.insert(i, this.get(i));
        }

        return copy;
    }

    public Comparable removeLastElement() {
        Comparable lastValue = heapArray[heapArray.length-1];
        heapArray = ArrayUtils.subarray(heapArray, 0, heapArray.length-1);

        return lastValue;
    }
}
