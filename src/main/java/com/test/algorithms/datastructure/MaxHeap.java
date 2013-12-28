package com.test.algorithms.datastructure;

import java.util.Arrays;
import java.util.Random;

/**
 * We represent a heap of N N in private array pq[] of length N+1, with pq[0] unused and the heap in pq[1] through pq[N]
 * Created by Mike on 12/25/13.
 */
public class MaxHeap<E extends Comparable<E>> {

    Object[] table;
    int N = 1;

    public MaxHeap(int initialCapacity) {
        this.table = new Object[initialCapacity];
    }

    public MaxHeap() {
        this(16);
    }

    public void insert(E e) {

        if (table.length == N)
            resize(table.length * 2);

        int i = N;
        table[i] = e;
        swim(i);
        N++;

    }

    public E max() {
        if (N > 1)
            return (E) table[1];
        return null;
    }

    public E delMax() {
        if (N == 1)
            return null;

        E max = (E) table[1];
        exch(1, N--);
        sink(1);
        table[N + 1] = null;
        //todo decrease N of table
        return max;
    }

    private void swim(int i) {
        while (i > 1 && less(i / 2, i)) {
            exch(i / 2, i);
            i = i / 2;
        }
    }

    private void sink(int i) {
        int j = 2 * i; //go to children

        while (j <= N) {
            if (j < N && less(j, j + 1))     //choose j+1 child if j < j+1
                j++;

            if (!less(i, j))
                break;

            exch(i, j);
            i = j;
        }
    }

    private void exch(int parent, int i) {
        Object parentObj = table[parent];
        table[parent] = table[i];
        table[i] = parentObj;
    }

    private boolean less(int i, int j) {
        int cmp = ((E) table[i]).compareTo((E) table[j]);
        return cmp < 0;
    }

    private void resize(int newCapacity) {
        table = Arrays.copyOf(table, newCapacity);
    }

    public static void main(String[] args) {
        MaxHeap<Integer> heap = new MaxHeap<Integer>(2);

        Random random = new Random();
        for (int j = 0; j < 100; j++) {
            int i = random.nextInt(10000);
            heap.insert(i);
        }

        System.out.println();
    }


}
