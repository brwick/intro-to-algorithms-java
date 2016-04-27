package org.brwick.algo.intro.basicsorting;

import org.apache.commons.lang3.ArrayUtils;

/**
 * Created on 4/22/16.
 */
public class BasicSortUtil {

    public static int[] bubbleSort(int[] unsorted) {
        final int[] sorted = ArrayUtils.clone(unsorted);
        int buffer;

        for (int i = 0; i < sorted.length; i++) {
            for (int j = sorted.length-1; j > i; j--) {
                if (sorted[j] < sorted[j-1]) {
                    buffer = sorted[j];
                    sorted[j] = sorted[j-1];
                    sorted[j-1] = buffer;
                }
            }
        }

        return sorted;
    }

    public static int[] insertionSort(int[] unsorted) {
        final int[] sorted = ArrayUtils.clone(unsorted);
        int j;
        int key = 0;
        for (int i = 1; i < sorted.length; i++) {
            key = sorted[i];
            j = i;
            while (j > 0 && sorted[j-1] > key) {
                sorted[j] = sorted[j-1];
                --j;
            }
            sorted[j] = key;
        }

        return sorted;
    }

    public static int[] mergeSort(int[] unsorted) {
        int[] firstHalf = ArrayUtils.subarray(unsorted, 0, unsorted.length/2);
        int[] secondHalf = ArrayUtils.subarray(unsorted, unsorted.length/2, unsorted.length);

        return mergeSortRecursive(firstHalf, secondHalf);
    }

    private static int[] mergeSortRecursive(int[] firstHalf, int[] secondHalf) {
        int[] sortedFirstHalf;
        int[] sortedSecondHalf;

        if (firstHalf.length > 1) {
            sortedFirstHalf = mergeSortRecursive(ArrayUtils.subarray(firstHalf, 0, firstHalf.length/2),
                    ArrayUtils.subarray(firstHalf, firstHalf.length/2, firstHalf.length));
        } else {
            sortedFirstHalf = firstHalf;
        }
        if (secondHalf.length > 1) {
            sortedSecondHalf = mergeSortRecursive(ArrayUtils.subarray(secondHalf, 0, secondHalf.length/2),
                    ArrayUtils.subarray(secondHalf, secondHalf.length/2, secondHalf.length));
        } else {
            sortedSecondHalf = firstHalf;
        }

        return merge(sortedFirstHalf, sortedSecondHalf);
    }

    private static int[] merge(int[] firstHalf, int[] secondHalf) {
        int i = 0;
        int j = 0;
        int k = 0;
        int[] merged = new int[firstHalf.length + secondHalf.length];

        while (i < firstHalf.length || j < secondHalf.length) {
            if (j == secondHalf.length || i < firstHalf.length && firstHalf[i] < secondHalf[j]) {
                merged[k] = firstHalf[i];
                ++i;
            } else {
                merged[k] = secondHalf[j];
                ++j;
            }
            ++k;
        }

        return merged;
    }
}
