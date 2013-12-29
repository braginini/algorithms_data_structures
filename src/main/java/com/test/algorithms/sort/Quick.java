package com.test.algorithms.sort;

/**
 * Created by Mike on 12/29/13.
 */
public class Quick extends Sort {

    public static void sort(Comparable[] a) {

        shuffle(a);
        int j = partition(a, 0, a.length - 1);
        sort(a, 0, j - 1);
        sort(a, j + 1, a.length - 1);

    }

    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        while (true) {

            while (less(a[++i], a[lo]))
                if (i == hi)
                    break;
            while (less(a[lo], a[--j]))
                if (j == lo)
                    break;

            if (i >=j)
                break;
            exch(i, j, a);
        }

        exch(lo, j, a);
        return j;
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (lo >= hi)
            return;
        //use median-of-3 here
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);

    }

    private static void shuffle(Object[] a) {

    }
}
