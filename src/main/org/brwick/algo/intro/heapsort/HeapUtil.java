package org.brwick.algo.intro.heapsort;

/**
 * Created on 3/10/16.
 */
public class HeapUtil {

    private HeapUtil() {}

    public static Heap build_max_heap(Comparable[] unorderedArray) {
        return null;
    }

    public static void swap(Heap heap, int a, int b) {
        final Comparable buffer = heap.get(a);
        heap.insert(a, heap.get(b));
        heap.insert(b, buffer);
    }

    public static void maxHeapify(Heap heap, int index) {
        final int leftIndex = heap.getLeft(index);
        final int rightIndex = heap.getRight(index);
        int largestNodeIndex;

        if (leftIndex <= heap.getSize() && heap.get(leftIndex).compareTo(heap.get(index)) > 0) {
            largestNodeIndex = leftIndex;
        } else {
            largestNodeIndex = index;
        }
        if (rightIndex <= heap.getSize() && heap.get(rightIndex).compareTo(heap.get(largestNodeIndex)) > 0) {
            largestNodeIndex = rightIndex;
        }

        if (largestNodeIndex != index) {
            HeapUtil.swap(heap, index, largestNodeIndex);
            HeapUtil.maxHeapify(heap, largestNodeIndex);
        }
    }
}
