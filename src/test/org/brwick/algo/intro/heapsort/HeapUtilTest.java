package org.brwick.algo.intro.heapsort;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created on 3/11/16.
 */
public class HeapUtilTest {

    @Test
    public void testSwap() throws Exception {
        final Heap heap = createUnsortedMaxHeap();
        HeapUtil.swap(heap, 2, 5);
        assertThat(heap.get(2), is(7));
        assertThat(heap.get(5), is(4));
    }

    private Heap createUnsortedMaxHeap() {
        final Heap<Integer> heap = new Heap(Integer.class, 10);
        heap.insert(1, 16);
        heap.insert(2, 4);
        heap.insert(3, 10);
        heap.insert(4, 14);
        heap.insert(5, 7);
        heap.insert(6, 9);
        heap.insert(7, 3);
        heap.insert(8, 2);
        heap.insert(9, 8);
        heap.insert(10, 1);

        return heap;
    }

    @Test
    public void testMaxHeapify() throws Exception {
        final Heap heap = createUnsortedMaxHeap();
        HeapUtil.maxHeapify(heap, 2);
        assertThat(heap.get(2), is(14));
        assertThat(heap.get(4), is(8));
        assertThat(heap.get(9), is(4));
    }
}
