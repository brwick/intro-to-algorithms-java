package org.brwick.algo.intro.heapsort;

/**
 * Created by jkim on 3/10/16.
 */
public class HeapUtil {

    private HeapUtil() {}

    public static <T> Heap build_max_heap(T[] unorderedArray) {
        return null;
    }

    public static <T> void maxHeapify(Heap<T> heap, int index) {
        int leftIndex = heap.getLeft(index);
        int rightIndex = heap.getRight(index);
        int largestNodeIndex;

        if (leftIndex <= heap.getSize() && heap.get(leftIndex) > heap.get(index)) {
            largestNodeIndex = leftIndex;
        } else {
            largestNodeIndex = index;
        }
        if (rightIndex <= heap.getSize() && heap.get(rightIndex) > heap.get(index)) {
            largestNodeIndex = rightIndex;
        }

        if (largestNodeIndex != index) {
            T buffer = heap.get(index);
            heap.insert(index, heap.get(largestNodeIndex));
            heap.insert(largestNodeIndex, buffer);
        }
        return;
    }
}
