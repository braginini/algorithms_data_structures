package com.test.algorithms.graph.datastructure;

import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeMap;

/**
 * Created by Mikhail Bragin
 */
public class BST<K extends Comparable<K>, V> {

    Node<K, V> root;

    public void put(K key, V value) {
        root = put(root, key, value);
    }

    private Node<K, V> put(Node<K, V> x, K key, V value) {
        if (x == null)
            return new Node<K, V>(key, value);

        int cmp = key.compareTo(x.key);

        if (cmp < 0)
            x.left = put(x.left, key, value);
        else if (cmp > 0)
            x.right = put(x.right, key, value);
        else if (cmp == 0)
            x.value = value;

        return x;

    }

    public V get(K key) {

        Node<K, V> x = root;

        while (x != null) {
            int cmp = key.compareTo(x.key);

            if (cmp > 0)
                x = x.left;
            else if (cmp < 0)
                x = x.right;
            else if (cmp == 0)
                return x.value;
        }

        return null;
    }

    private V get(Node<K, V> x, K key) {
        if (x == null)
            return null;

        int cmp = key.compareTo(x.key);

        if (cmp < 0)
            return get(x.left, key);
        else if (cmp > 0)
            return get(x.right, key);
        else if (cmp == 0)
            return x.value;

        return null;
    }

    public V floor(K key) {
        Node<K, V> x = floor(root, key);

        if (x != null)
            return x.value;

        return null;
    }

    private Node<K, V> floor(Node<K, V> x, K key) {

        if (x == null)
            return null;

        int cmp = key.compareTo(key);

        if (cmp == 0)
            return x;

        if (cmp < 0)
            return floor(x.left, key);

        Node<K, V> n = floor(x.right, key);
        if (n != null)
            return n;
        else return x;
    }

    public Iterable<K> keysInOrder() {
        Queue<K> keys = new LinkedList<K>();
        inOrder(root, keys);

        return keys;
    }

    private void inOrder(Node<K, V> x, Queue<K> q) {
        if (x == null)
            return;

        inOrder(x.left, q);
        q.offer(x.key);
        inOrder(x.right, q);
    }

    private void preOrder(Node<K, V> x, Queue<K> q) {
        if (x == null)
            return;

        q.offer(x.key);
        preOrder(x.left, q);
        preOrder(x.right, q);
    }

    private void postOrder(Node<K, V> x, Queue<K> q) {
        if (x == null)
            return;

        postOrder(x.left, q);
        postOrder(x.right, q);
        q.offer(x.key);

    }

    public void delete(K key) {

        root = delete(root, key);
    }

    private Node<K, V> delete(Node<K, V> x, K key) {

        if (x == null)
            return null;

        int cmp = key.compareTo(x.key);

        if (cmp < 0)
            x.left = delete(x.left, key);
        else if (cmp > 0)
            x.right = delete(x.left, key);
        else {
            if (x.right == null)
                return x.left;

            Node t = x;

            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }

        return x;
    }

    private Node<K, V> min(Node<K, V> root) {

        Node x = root;

        if (x == null)
            return null;

        while (x.left != null) {
            x = x.left;
        }

        return x;
    }

    private Node<K, V> deleteMin(Node<K, V> x) {
        if (x.left == null)
            return x.right;

        x.left = deleteMin(x.left);

        return x;
    }

    private class Node<K extends Comparable<K>, V> {

        K key;

        V value;

        Node<K, V> left;

        Node<K, V> right;

        private Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public void remove(K key) {
        root = remove(root, key);
    }

    private Node remove(Node x, K key) {
        if (x == null)
            return null;

        int cmp = key.compareTo((K) x.key);

        if (cmp < 0)
            x.left = remove(x.left, key);
        else if (cmp > 0)
            x.right = remove(x.right, key);
        else {

            if (x.right == null)
                return x.left;

            Node n = x;

            x = min(x.right);
            x.right = removeMin(n.right);
            x.left = n.left;
        }

        return x;
    }

    private Node removeMin(Node<K, V> x) {

        if (x.left == null)
            return x.right;

        x.left = removeMin(x.left);

        return x;

    }

    private Node newMin(Node n) {

        Node x = n;
        if (x == null)
            return null;

        while (x.left != null)
            x = x.left;

        return x;
    }


}
