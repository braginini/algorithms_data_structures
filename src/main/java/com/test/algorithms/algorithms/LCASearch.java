package com.test.algorithms.algorithms;

import com.test.algorithms.datastructure.Tree;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * Created by Mikhail Bragin
 */
public class LCASearch {

	/**
	 * Breath-First Search + ancestor list.
	 * time complexity O(h), h - tree height.
	 * Use full when only one request is needed.
	 *
	 * @param v
	 * @param w
	 * @return
	 */
	public static Tree.Node linearLCA(Tree.Node v, Tree.Node w) {

		//next 3 ifs treat simplest cases, so we don't need to build ancestor stack to root from each node
		if (v.getParent() == w)
			return w;

		if (w.getParent() == v)
			return v;

		if (w.getParent() == v.getParent())
			return v.getParent();

		Stack<Tree.Node> vAncestors = new Stack<Tree.Node>();
		Stack<Tree.Node> wAncestors = new Stack<Tree.Node>();

		buildAncestorStack(vAncestors, v);
		buildAncestorStack(wAncestors, w);

		if (!vAncestors.isEmpty() && !wAncestors.isEmpty()) { //exclude possible EmptyStackException

			Tree.Node vNode = vAncestors.pop();
			Tree.Node wNode = wAncestors.pop();
			Tree.Node lca = null;

			while (vNode == wNode && !vAncestors.isEmpty() && !wAncestors.isEmpty()) {
				lca = vNode;
				vNode = vAncestors.pop();
				wNode = wAncestors.pop();
			}

			if (vNode == wNode) // One node is descended from the other
				return vNode;

			return lca;
		}

		return null;
	}

	/**
	 * the same as above but with try catch instead of checking empty
	 *
	 * @param v
	 * @param w
	 * @return
	 */
	public static Tree.Node linearLCATryCatch(Tree.Node v, Tree.Node w) {

		//next 3 ifs treat simplest cases, so we don't need to build ancestor stack to root from each node
		if (v.getParent() == w)
			return w;

		if (w.getParent() == v)
			return v;

		if (w.getParent() == v.getParent())
			return v.getParent();

		Stack<Tree.Node> vAncestors = new Stack<Tree.Node>();
		Stack<Tree.Node> wAncestors = new Stack<Tree.Node>();

		buildAncestorStack(vAncestors, v);
		buildAncestorStack(wAncestors, w);

		Tree.Node lca = null;
		try {

			Tree.Node vNode = vAncestors.pop();
			Tree.Node wNode = wAncestors.pop();

			while (vNode == wNode) {
				lca = vNode;
				vNode = vAncestors.pop();
				wNode = wAncestors.pop();
			}

		} catch (EmptyStackException e) {
			return lca;
		}

		return lca;
	}



	/**
	 * Builds and ancestor stack for a given tree node
	 * O(h) time complexity, where h - tree height
	 *
	 * @param ancestorStack
	 * @param node
	 */
	private static void buildAncestorStack(Stack<Tree.Node> ancestorStack, Tree.Node node) {

		while (node != null) {
			ancestorStack.push(node);
			node = node.getParent();
		}
	}


}
