package com.test.algorithms.datastructure;

import java.util.Map;
import java.util.Random;

/**
 * Created by Mike on 12/23/13.
 */
public class HashMap<K, V> {

    Node[] table;
    int size;
    float loadFactor = 0.75f;
    int bucketsLoaded = 0;

    public HashMap(int capacity) {
        this.table = new Node[capacity];
        size = 0;
    }

    public HashMap() {
        this(16);
    }

    public V get(K key) {

        int hash = hashCode(key, table.length);

        Node n = table[hash];
        while (n != null) {
            if (n.key.equals(key))
                return (V) n.value;
            n = n.next;
        }

        return null;
    }


    public void put(K key, V value) {
        if (key == null || value == null)
            throw new NullPointerException();

        int hash = hashCode(key, table.length);

        for (Node n = table[hash]; n != null; n = n.next) {
            if (n.key.equals(key)) {
                n.value = value;
                return;
            }
        }

        table[hash] = new Node(key, value, table[hash]);
        size++;

        if (size > (int) (table.length * loadFactor))
            resizeAndRehash(table.length * 2);
    }

    private void resizeAndRehash(int newCapacity) {

        Node[] newTable = new Node[newCapacity];

        for (Node n : table) {
            while (n != null) {
                Node next = n.next;
                int hash = hashCode((K) n.key, newCapacity);
                n.next = newTable[hash];
                newTable[hash] = n;
                n = next;
            }
        }

        table = newTable;
    }

    private int hashCode(K key, int size) {
        return (key.hashCode() & 0x7FFFFFFF) % size;
    }

    class Node<K, V> {

        K key;

        V value;

        Node next;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        Node(K key, V value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>(3);

        Random r = new Random();

        for (int i = 0; i < 100; i++) {
            int j = r.nextInt();
            map.put(j, j);
        }
    }
}
