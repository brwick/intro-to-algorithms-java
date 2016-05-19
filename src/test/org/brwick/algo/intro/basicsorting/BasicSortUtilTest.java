package org.brwick.algo.intro.basicsorting;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import sun.plugin.dom.exception.InvalidStateException;

/**
 * Created on 4/22/16.
 */
public class BasicSortUtilTest {

    @Test
    public void testBubbleSort() throws Exception {
        validateSorted(BasicSortUtil.bubbleSort(createUnsortedArray(1000)));
    }

    @Test
    public void testInsertionSort() throws Exception {
        validateSorted(BasicSortUtil.insertionSort(createUnsortedArray(1000)));
    }

    @Test
    public void testMergeSort() throws Exception {
        validateSorted(BasicSortUtil.mergeSort(createUnsortedArray(1000)));
    }

    private void validateSorted(int[] sorted) {
        int last = -1;
        for (int i = 0; i < sorted.length; i++) {
            if (last > sorted[i]) {
                throw new InvalidStateException("Array not sorted");
            }
            last = sorted[i];
        }
    }

    private int[] createUnsortedArray(int length) {
        final int[] unsorted = new int[length];

        for (int i = 0; i < length; i++) {
            unsorted[i] = RandomUtils.nextInt(0, 20000);
        }

        return unsorted;
    }
}
