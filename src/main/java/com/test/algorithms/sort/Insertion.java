package com.test.algorithms.sort;

/**
 * Created by Mike on 12/28/13.
 */
public class Insertion extends Sort {


    public static void sort(Comparable[] a) {

        for (int i = 0; i < a.length; i++) {
            for (int j = i; j > 0; j--) {
                if (less(a[j], a[j-1]))
                    exch(j, j-1, a);
                else break;
            }
        }
    }
}
