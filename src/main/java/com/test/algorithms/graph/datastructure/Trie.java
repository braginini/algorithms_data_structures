package com.test.algorithms.graph.datastructure;

/**
 * Created by Mikhail Bragin
 */
public class Trie<V> {

	private static final int R = 128;

	Node root;

	boolean hasPrefix = false;

	public Trie() {
		this.root = new Node();
	}

	//!= null if value was overridden
	public void put(String key, V value) {

		char[] keyChars = key.toCharArray();
	}

	public Node insert(Node x, String key, V value, int idx) {

		if (x == null) x = new Node();

		if (idx == key.length()) {
			x.value = value;
			return x;
		}

		char c = key.charAt(idx);

		x.next[c] = insert(x.next[c], key, value, idx + 1);
		return x;

	}

	public boolean hasPrefix() {
		return hasPrefix;
	}

	/*//returns the
	public boolean putHasPrefixString(String key, V value) {

	}*/

	public V get(String key) {
		return null;
	}

	private static class Node  {
		Object value;

		Node[] next = new Node[R];
	}
}
