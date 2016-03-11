package org.brwick.algo.intro.heapsort;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created on 2/11/2016.
 */
public class HeapTest {

    @Test
    public void testParent_valid() throws Exception {
        Heap<Integer> heap = new Heap<>(16);
        assertThat(heap.parent(6), is(3));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testParent_invalid_beyondMaxInput() throws Exception {
        Heap<Integer> heap = new Heap<>(16);
        heap.parent(17);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testParent_invalid_negativeInput() throws Exception {
        Heap<Integer> heap = new Heap<>(16);
        heap.parent(-1);
    }

    @Test
    public void testRoot() throws Exception {
        Heap<Integer> heap = createSmallTestHeap();
        assertThat(heap.getRoot(), is(10));
    }

    @Test
    public void testGet() throws Exception {
        Heap<Integer> heap = createSmallTestHeap();
        assertThat(heap.get(1), is(10));
        assertThat(heap.get(2), is(20));
        assertThat(heap.get(3), is(30));
    }

    @Test
    public void testGetLeft() throws Exception {
        Heap<Integer> heap = createSmallTestHeap();
        assertThat(heap.getLeft(1), is(2));
    }

    private Heap<Integer> createSmallTestHeap() {
        Heap<Integer> heap = new Heap<>(3);
        heap.insert(1, 10);
        heap.insert(2, 20);
        heap.insert(3, 30);
        return heap;
    }

    @Test
    public void testGetRight() throws Exception {
        Heap<Integer> heap = createSmallTestHeap();
        assertThat(heap.getRight(1), is(3));
    }

    @Test
    public void testHeapSize() throws Exception {
        Heap<Integer> heap = createSmallTestHeap();
        assertThat(heap.getSize(), is(3));
    }
}
