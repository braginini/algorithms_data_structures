package com.test.algorithms.sort;

/**
 * Created by Mike on 12/27/13.
 */
public class Heap {

    /**
     * 2 phases:
     * - build a heap going through array backwards (use heap property - parent of K-th element is at K/2)
     * - delete maximum and put it at the end of array
     *
     * @param arr
     */
    public static void sort(Comparable[] arr) {
        int len = arr.length;

        //we start from len/2 cause the remaining half has only 1 node heaps
        for (int i = len / 2; i >= 1; i--) {
            sink(i, len, arr);
        }

        while (len > 1) {
            exch(1, len--, arr);
            sink(1, len, arr);
        }
    }

    private static void sink(int i, int len, Comparable[] arr) {
        while (2 * i <= len) {

            int j = 2 * i;
            if (j < len && less(j, j + 1, arr))
                j++;

            if (!less(i, j, arr))
                break;

            exch(i, j, arr);
            i = j;
        }

    }

    /**
     * i-1 and j-1 since we r using 1..N array heap representation
     * @param i
     * @param j
     * @param arr
     */
    private static void exch(int i, int j, Object[] arr) {
        Object temp = arr[i-1];
        arr[i-1] = arr[j-1];
        arr[j-1] = temp;
    }

    /**
     * i-1 and j-1 since we r using 1...N array heap representation
     * @param i
     * @param j
     * @param arr
     */
    private static boolean less(int i, int j, Comparable[] arr) {
        return arr[i-1].compareTo(arr[j-1]) < 0;
    }
}
