package com.test.tasks;

import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * This data structure has two frequent operations - add element and median.
 * The implementation has the benefit of basic data structures to make this operations fast.
 * Created by Mike on 12/30/13.
 */
public class MedianDataStructure {

    PriorityQueue<Integer> left; //max heap

    PriorityQueue<Integer> right; //min heap

    public MedianDataStructure() {
        this.left = new PriorityQueue<Integer>(11, Collections.reverseOrder());
        this.right = new PriorityQueue<Integer>();
    }

    public void add(int i) {
        Integer leftMax = left.peek();
        Integer rightMin = right.peek();

        //both heaps are empty
        if (leftMax == null && rightMin == null) {
            left.add(i);
        } else if (leftMax != null && rightMin != null) { //both heaps are not empty
            if (i <= leftMax)
                left.add(i);
            else
                right.add(i);
        } else if (leftMax != null) {  //left is not empty, right is empty
            if (i <= leftMax)
                left.add(i);
            else
                right.add(i);
        } else if (rightMin != null) {
            if (i > rightMin)
                right.add(i);
            else
                left.add(i);
        }

        rebalance();

    }

    private void rebalance() {
        if (Math.abs(left.size() - right.size()) > 1) {
            if (left.size() > right.size()) {
                right.add(left.poll());
            } else
                left.add(right.poll());
        }
    }

    public Integer median() {
        if (left.isEmpty() && right.isEmpty())
            return null;

        if (left.size() > right.size())
            return left.peek();
        else if (left.size() < right.size())
            return right.peek();

        return right.peek();
    }

    public static void main(String[] args) {

        MedianDataStructure ds = new MedianDataStructure();

        Random r = new Random();
        for (int i = 0; i < 9; i ++) {
            ds.add(r.nextInt(20));
            System.out.println(ds.median());
        }
    }
}
