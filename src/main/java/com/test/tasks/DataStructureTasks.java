package com.test.tasks;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 * Created by Mike on 12/25/13.
 */
public class DataStructureTasks {

    public static void reverseCStyleString() {
        String s = "abcd";
        char[] chars = s.toCharArray();
        int n = 0; //number of iterations;

        /*for (int i = 0; i < (s.length()) / 2; i++) {
            char ith = s.charAt(i);
            int otherIdx = s.length() - 1 - i;
            chars[i] = chars[otherIdx];
            chars[otherIdx] = ith;
            n++;
        }

        s = new String(chars);
        //s = new StringBuilder(s).reverse().toString();
        System.out.println(s + " " + n);*/

        s = "abcd0"; //C-style
        n = 0;
        chars = s.toCharArray();
        for (int i = s.length() - 2; i >= s.length() / 2; i--) {
            char ith = s.charAt(i);
            int otherIdx = s.length() - i - 2;
            chars[i] = s.charAt(otherIdx);
            chars[otherIdx] = ith;
            n++;
        }
        s = new String(chars);

        System.out.println(s + " " + n);

        s = "abcd0";
        n = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = s.length() - 2; i >= 0; i--) {
            sb.append(s.charAt(i));
            n++;
        }

        sb.append("0");

        System.out.println(sb.toString() + " " + n);


    }

    /**
     * first use sort (n*logn)
     * then check next elements until curr element !equlas to neighbour N
     * <p/>
     * n*logn solution
     */
    private static void removeDubs() {
        char[] s = new char[]{'a', 'a', 'a', 'a', 'c', 'b', 'b', 'b'};
        /*char[] s = new char[1000000];
        for (int i = 0; i < 1000000; i++) {
            s[i] = (char) r.nextInt(256);
        }*/
        //removeDuplicates(s);
        long ts = System.currentTimeMillis();
        Arrays.sort(s);

        int size = s.length;
        for (int i = 0; i < s.length - 2; i++) {
            int j = i + 1;

            while (j < s.length && s[i] == s[j]) {
                s[j] = 0;
                j++;
                size--;
            }

            i = j;
        }

        char[] newChar = new char[size];
        int j = 0;
        for (int i = 0; i < s.length; i++) {
            if (s[i] != 0) {
                newChar[j] = s[i];
                j++;
            }

        }

        System.out.println(System.currentTimeMillis() - ts);
        System.out.println(newChar);


    }

    /**
     * N^2 solution
     *
     * @param str
     */
    public static void removeDuplicates(char[] str) {
        long ts = System.currentTimeMillis();
        if (str == null) return;
        int len = str.length;
        if (len < 2) return;

        int tail = 1;

        for (int i = 1; i < len; ++i) {
            int j;
            for (j = 0; j < tail; ++j) {
                if (str[i] == str[j]) break;
            }
            if (j == tail) {
                str[tail] = str[i];
                ++tail;
            }
        }
        str[tail] = 0;

        System.out.println(System.currentTimeMillis() - ts);
    }

    /**
     * iceman and cinema are anagrams (second word can be constructed from the first one)
     * n*logn solution
     * <p/>
     * first sort both arrays
     * then compare characters
     *
     * @param s1
     * @param s2
     * @return
     */
    public static boolean areAnagrams(String s1, String s2) {
        if (s1 == null || s2 == null)
            return false;

        if (s1.length() != s2.length())
            return false;

        char[] ch1 = s1.toCharArray();
        char[] ch2 = s2.toCharArray();

        Arrays.sort(ch1);
        Arrays.sort(ch2);

        for (int i = 0; i < s1.length(); i++) {
            if (ch1[i] != ch2[i])
                return false;
        }

        return true;
    }

    /**
     * N complexity solution, requires src array to fit spaceCount*2 additional symbols since %20 is 3 characters
     * e.g char[] for string "ab " should be size 5 "ab \0\0"
     *
     * @param str
     * @param len
     */
    public static void replaceWhiteSpaces(char[] str, int len) {

        int newLen = len;

        for (int i = 0; i < len; i++) {
            if (str[i] == ' ')
                newLen += 2;
        }

        int shift = newLen - len;
        for (int i = len - 1; i >= 0; i--) {
            if (str[i] == ' ') {
                shift -= 2;
                str[i + shift] = '%';
                str[i + shift + 1] = '2';
                str[i + shift + 2] = '0';
            } else {
                str[i + shift] = str[i];
            }
        }

        System.out.println(str);

    }

    public static void ReplaceFun(char[] str, int length) {
        int spaceCount = 0, newLength, i = 0;
        for (i = 0; i < length; i++) {
            if (str[i] == ' ') {
                spaceCount++;
            }
        }
        newLength = length + spaceCount * 2;
        str[newLength] = '\0';
        for (i = length - 1; i >= 0; i--) {
            if (str[i] == ' ') {
                str[newLength - 1] = '0';
                str[newLength - 2] = '2';
                str[newLength - 3] = '%';
                newLength = newLength - 3;
            } else {
                str[newLength - 1] = str[i];
                newLength = newLength - 1;
            }
        }
    }

    public static void rotatateMatrix90(int[][] m, int n) {

        for (int layer = 0; layer < n / 2; layer++) {

            int first = layer;
            int last = n - 1 - layer;

            for (int i = first; i < last; i++) {
                int offset = i - first;
                int top = m[first][i];
                m[first][i] = m[last - offset][first];
                m[last - offset][first] = m[last][last - offset];
                m[last][last-offset] = m[i][last];
                m[i][last] = top;
            }
        }

    }

    /**
     * Write an algorithm such that if an element in an MxN matrix is 0, its entire row and column is set to 0.
     *
     * N+M space and MN time complexity
     * @param m
     */
    public static void setMatrixRowColumnTOZeroIfZeroElement(int[][] m) {

        boolean[] rows = new boolean[m.length];
        boolean[] cols = new boolean[m[0].length];

        //find 0 elements and mark that it's row and column should be zeroed
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                if (m[i][j] == 0) {
                    rows[i] = true;
                    cols[j] = true;
                }
            }
        }

        //do a second pass to set element to zero if its row or column should be zeroed
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                if (rows[i] || cols[j])
                    m[i][j] = 0;
            }
        }

        System.out.println();

    }

    public static boolean isRotation(String s1, String s2) {

        if (s1.length() > 0 && s1.length() == s2.length()) {
            String s3 = s1.concat(s1);
            return s3.contains(s2);
        }

        return false;
    }

    public static LinkedListNode nthToLast(LinkedListNode head, int n) {

        LinkedListNode p1 = head;
        LinkedListNode p2 = head;

        for (int i = 0; i < n - 1; i++) {

            if (p2 == null)  //list is smaller than n
                return null;

            p2 = p2.next;
        }

        while (p2.next != null) {
            p1 = p1.next;
            p2 = p2.next;
        }

        return p1;

    }

    public static void main(String[] args) {
        //reverseCStyleString();
        //removeDubs();
        //areAnagrams("cinema", "icemam");
        //replaceWhiteSpaces("aa bb c".toCharArray());
        //replaceWhiteSpaces("   \0\0\0\0\0\0".toCharArray(), 3);
        /*setMatrixRowColumnTOZeroIfZeroElement(new int[][]{
                {0, 1, 2, 3},
                {4, 5, 6, 7},
                {8, 9, 10, 11},
                {12, 13, 0, 15},
                {16, 17, 18, 19},
        });*/

        //boolean f = isRotation("mike", "ikem");
        //nthToLast(new LinkedListNode(1), 1);
        int max = ~0;
    }

    //this data structre
    class MedianDataStructure {

    }

    static class LinkedListNode {
        int data;
        LinkedListNode next;

        LinkedListNode(int data) {
            this.data = data;
        }
    }


}
