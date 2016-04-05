package org.brwick.algo.intro.heapsort;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.greaterThan;

/**
 * Created on 3/11/16.
 */
public class HeapUtilTest {

    @Test
    public void testSwap() throws Exception {
        final Heap heap = createUnsortedHeap();
        HeapUtil.swap(heap, 2, 5);
        assertThat(heap.get(2), is(7));
        assertThat(heap.get(5), is(4));
    }

    private Heap createUnsortedHeap() {
        final Heap<Integer> heap = new Heap(Integer.class, 10);
        heap.insert(1, 16);
        heap.insert(2, 4);
        heap.insert(3, 10);
        heap.insert(4, 14);
        heap.insert(5, 7);
        heap.insert(6, 9);
        heap.insert(7, 3);
        heap.insert(8, 7);
        heap.insert(9, 8);
        heap.insert(10, 1);

        return heap;
    }

    @Test
    public void testMaxHeapify() throws Exception {
        final Heap heap = createUnsortedHeap();
        HeapUtil.maxHeapify(heap, 2);
        assertThat(heap.get(2), is(14));
        assertThat(heap.get(4), is(8));
        assertThat(heap.get(9), is(4));
    }

    @Test
    public void testBuildMaxHeap() throws Exception {
        final Heap heap = createUnsortedHeap();
        final Heap maxedHeap = HeapUtil.buildMaxHeap(heap);

        for (int i=1; i<maxedHeap.getSize()/2; i++) {
            assertThat(maxedHeap.get(i), is(greaterThan(maxedHeap.get(maxedHeap.getLeft(i)))));
            assertThat(maxedHeap.get(i), is(greaterThan(maxedHeap.get(maxedHeap.getRight(i)))));
        }
    }

    @Test
    public void testRemoveLastElement() throws Exception {
        final Heap heap = createUnsortedHeap();
        final int originalSize = heap.getSize();
        final Comparable lastElem = heap.get(heap.getSize());
        final Comparable removedElem = heap.removeLastElement();

        assertThat(heap.getSize(), is(originalSize-1));
        assertThat(lastElem, is(removedElem));
    }

    @Test
    public void testHeapSort() throws Exception {
        final Heap heap = createUnsortedHeap();
        final Comparable[] sortedArray = HeapUtil.heapSort(heap);

        for (int i=0; i < sortedArray.length-1; i++) {
            assertThat(sortedArray[i], is(greaterThanOrEqualTo(sortedArray[i+1])));
        }
    }
}
