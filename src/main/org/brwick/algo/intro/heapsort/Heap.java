package org.brwick.algo.intro.heapsort;

import java.lang.reflect.Array;

/**
 * Created on 2/11/2016.
 */
public class Heap<T> {

    private final T[] heapArray;

    public Heap(Class<T> clazz, int size) {
        heapArray = (T[])Array.newInstance(clazz, size);
    }
}
