package com.test.algorithms.sort;

/**
 * Created by Mike on 12/28/13.
 */
public  abstract class Sort {

    protected static void exch(int i, int j, Object[] a) {
        Object temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    protected static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    public static void main(String[] args) {

        Integer[] a = new Integer[]{5,3,10,12,2};
        //Selection.sort(a);
        Insertion.sort(a);
    }
}
