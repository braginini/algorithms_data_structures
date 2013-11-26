package com.test.algorithms.datastructure;

/**
 *
 */
public class RedBlackBST<K extends Comparable<K>, V> {

    public static final boolean RED = true;
    public static final boolean BLACK = false;

    Node<K, V> root;

    public V get(K key) {
        Node x = get(root, key);

        if (x == null)
            return null;

        return (V) x.value;

    }

    public void put(K key, V value) {
         root = put(root, key, value);
    }

    private Node put (Node x, K key, V value) {
        if (x == null)
            return new Node(key, value, RED);

        if (isRed(x.left) && isRed(x.right))
            flipColor(x);

        int cmp = key.compareTo((K) x.key);
        if (cmp < 0)
            x.left = put(x.left, key, value);
        else if (cmp > 0)
            x.right =  put(x.right, key, value);
        else if (cmp == 0)
            x.value = value;

        if (isRed(x.right) && !isRed(x.left))
            x = rotateLeft(x);
        if (isRed(x.left) && isRed(x.left.left))
            x = rotateRight(x);

        return x;
    }

    private Node rotateLeft(Node h) {

        assert isRed(h.right);

        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;

        return x;
    }

    private Node rotateRight(Node h) {

        assert isRed(h.left);

        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;

        return x;
    }

    private void flipColor(Node h) {
        assert !isRed(h);
        assert isRed(h.left);
        assert isRed(h.right);

        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    public V iterativeGet(K key) {

        Node x = root;

        while (x != null) {
            int cmp = key.compareTo((K) x.key);
            if (cmp < 0)
                x = x.left;
            else if (cmp > 0)
                x = x.right;
            else if (cmp == 0)
                return (V) x.value;
        }

        return null;
    }


    private Node get(Node x, K key) {

        if (x == null)
            return null;

        int cmp = key.compareTo((K) x.key);
        if (cmp < 0)
            return get(x.left, key);
        else if (cmp > 0)
            return get(x.right, key);
        else if (cmp == 0)
            return x;

        return null;

    }

    private boolean isRed(Node x) {
        if (x == null)
            return false;

       return x.color == RED;
    }

    private class Node<K, V> {

        K key;

        V value;

        Node<K, V> left, right;

        boolean color;


        public Node(K key, V value, boolean color) {
            this.key = key;
            this.value = value;
            this.color = color;
        }
    }
}
