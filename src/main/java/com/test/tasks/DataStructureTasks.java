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
        Random r = new Random();
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

            i = j - 1;
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


    public static void main(String[] args) {
        //reverseCStyleString();
        //removeDubs();
        //areAnagrams("cinema", "icemam");
        //replaceWhiteSpaces("aa bb c".toCharArray());
        replaceWhiteSpaces("   \0\0\0\0\0\0".toCharArray(), 3);
        String s = new String();

        int[][] m = new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
    }


}
