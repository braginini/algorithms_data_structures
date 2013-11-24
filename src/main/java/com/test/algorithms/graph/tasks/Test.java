package com.test.algorithms.graph.tasks;

/**
 * Created by Mikhail Bragin
 */
public class Test {

	//I assume that Node.Children field is never null
	public static void main(String[] args) {

		Node root = new Node();

		Node n1 = new Node();
		Node n2 = new Node();
		Node n3 = new Node();
		Node n4 = new Node();
		Node n5 = new Node();
		Node n6 = new Node();

		n4.Children = new Node[0];
		n5.Children = new Node[0];
		n6.Children = new Node[0];
		n1.Children = new Node[]{n4, n5};
		n2.Children = new Node[0];
		n3.Children = new Node[]{n6};
		root.Children = new Node[]{n1, n2, n3};

		link(root);

		System.out.println();

	}

	public static void link(Node current) {

		if (current.Children == null)
			return;

		for (int i = 0; i < current.Children.length; i++) {
			link(current.Children[i], i, current);
		}

		for (int i = 0; i < current.Children.length; i++) {
			link(current.Children[i]);
		}

	}

	public static void link(Node current, int idx, Node parent) {

		if (idx < parent.Children.length - 1) { //if this node is not the last child of a parent
			current.Right = parent.Children[idx + 1]; //we set next parent's child as a right neighbour
		} else if (idx == parent.Children.length - 1) {   //if the node is the last child of a parent

			//we take parent's right neighbour
			Node parentRight = parent.Right;
			if (parentRight == null)
				return;

			//we search for parent's right neighbour with at least 1 child
			while (parentRight.Children.length == 0) {
				parentRight = parentRight.Right;

				if (parentRight == null) // no right neighbour for current node
					return;
			}

			current.Right = parentRight.Children[0];
		}
	}

	public static class Node {
		public Node[] Children;
		public Node Right;
	}
}
