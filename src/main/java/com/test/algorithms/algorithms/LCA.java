package com.test.algorithms.algorithms;

import java.util.List;
import java.util.Stack;

/**
 *
 * The time complexity is O(n) and space complexity is O(n)
 *
 */
public class LCA {

	List<Integer>[] tree;

	int root;

	int[] edgeTo;

	/**
	 * Use if you have many requests and not changing tree.
	 *
	 * @param tree
	 * @param root
	 */
	public LCA(List<Integer>[] tree, int root) {
		this.tree = tree;
		this.root = root;
		this.edgeTo = new int[tree.length];

		dfs();
	}

	/**
	 * Traverses the tree using Depth-First Search in time O(n), space O(n)
	 */
	private void dfs() {

		Stack<Integer> stack = new Stack<Integer>();
		stack.push(root);

		while (!stack.isEmpty()) {

			int current = stack.pop();

			List<Integer> children = tree[current];

			for (int i : children) {
				edgeTo[i] = current;
				stack.push(i);
			}
		}
	}

	/**
	 * Uses precomputed (DFS) tree to find LCA of 2 given nodes in O(h) time where h - the height of the tree
	 *
	 * @param v
	 * @param w
	 * @return positive integer in range of [0; tree.length] in case if lca was found, -1 otherwise.
	 */
	public Integer lca(int v, int w) {

		//next 2 ifs check whether one of the specified nodes contain the other one as a direct child
		if (tree[v].contains(w))
			return v;

		if (tree[w].contains(v))
			return w;

		//since we made a pre-computation we can find the path from root to any node of the tree
		Stack<Integer> vPath = pathTo(v);
		Stack<Integer> wPath = pathTo(w);

		//define lca as not found
		int lca = -1;

		if (vPath.isEmpty() || wPath.isEmpty())
			return -1;

		int vNode = vPath.pop();
		int wNode = wPath.pop();

		while(vNode == wNode && !vPath.isEmpty() && !wPath.isEmpty()) {
			lca = vNode;

			vNode = vPath.pop();
			wNode = wPath.pop();
		}

		if (vNode == wNode) // One node is descended from the other one
			lca = vNode;

		return lca;
	}

	/**
	 * Calculates path from root to specified node in time proportional to path length
	 * O(h), where h is the height of the tree
	 *
	 * @param v
	 * @return
	 */
	public Stack<Integer> pathTo(int v) {

		Stack<Integer> path = new Stack<Integer>();

		for (int x = v; x != root; x = edgeTo[x]) {
			path.push(x);
		}

		return path;

	}
}
