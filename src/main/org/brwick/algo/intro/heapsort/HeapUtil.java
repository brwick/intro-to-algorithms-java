package org.brwick.algo.intro.heapsort;

import com.google.common.annotations.VisibleForTesting;

/**
 * Created on 3/10/16.
 */
public class HeapUtil {

    private HeapUtil() {}

    public static Comparable[] heapSort(Heap heap) {
        final Heap unSortedHeap = heap.copy();
        final Comparable[] sorted = new Comparable[unSortedHeap.getSize()];
        buildMaxHeap(unSortedHeap);
        int i = 0;
        while (unSortedHeap.getSize() > 0) {
            swap(unSortedHeap, 1, unSortedHeap.getSize());
            sorted[i] = unSortedHeap.removeLastElement();
            maxHeapify(unSortedHeap, 1);
            i++;
        }

        return sorted;
    }

    public static Heap buildMaxHeap(Heap heap) {
        for (int i=heap.getSize()/2; i > 0; i--) {
            HeapUtil.maxHeapify(heap, i);
        }

        return heap;
    }

    @VisibleForTesting
    static void maxHeapify(Heap heap, int index) {
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

    public static void swap(Heap heap, int a, int b) {
        final Comparable buffer = heap.get(a);
        heap.insert(a, heap.get(b));
        heap.insert(b, buffer);
    }
}
