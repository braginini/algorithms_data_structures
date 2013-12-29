package com.test.algorithms.sort;

/**
 * Created by Mike on 12/29/13.
 */
public class Merge extends Sort {

    public static void sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        sort(a, aux, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        int mid = lo + (hi - lo) / 2;

        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        merge(a, aux, lo, mid, hi);

    }

    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {

        //make a copy for aux array
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        int i = lo;
        int j = mid + 1;

        //k is an index of a[]
        for (int k = lo; k <= hi; k++) {
            if (i > mid)
                a[k] = aux[j++];  //if left array is exhausted, copy elements from right to a[]
            else if (j > hi)
                a[k] = aux[i++];  //if right array is exhausted, copy elements from left to a[]
            else if (less(aux[i], aux[j]))
                a[k] = aux[i++];
            else
                a[k] = aux[j++];
        }
    }
}
