package com.test.algorithms.datastructure;

/**
 * Created by Mikhail Bragin
 */
public class Tree {

	Node root;

	int size = 0;

	public class Node<T> {

		Node parent;

		Node left;

		Node right;

		T data;

		public Node(Node parent, T data) {
			this.parent = parent;
			this.data = data;
		}

		public Node(Node parent, Node left, Node right, T data) {
			this.parent = parent;
			this.left = left;
			this.right = right;
			this.data = data;
		}

		public Node getParent() {
			return parent;
		}

		public Node getLeft() {
			return left;
		}

		public Node getRight() {
			return right;
		}

		public T getData() {
			return data;
		}

		public boolean hasLeft() {
			return left != null;
		}

		public boolean hasRight() {
			return right != null;
		}

		public boolean isRoot() {
			return parent == null;
		}
	}
}
