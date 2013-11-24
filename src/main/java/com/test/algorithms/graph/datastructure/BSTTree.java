package com.test.algorithms.graph.datastructure;

/**
 * Created by Mikhail Bragin
 */
public class BSTTree<K extends Comparable<K>, V> {

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

	private class Node<K extends Comparable<K>, V> {

		K key;

		V value;

		Node<K, V> left;

		Node<K, V> right;

		private Node(K key, V value) {
			this.key = key;
			this.value = value;
		}

		private Node(K key, V value, Node<K, V> left, Node<K, V> right) {
			this(key, value);
			this.left = left;
			this.right = right;
		}
	}
}
