package com.test.algorithms.graph.algorithms;

import com.test.algorithms.graph.datastructure.UndirectedGraph;
import com.test.algorithms.graph.datastructure.Vertex;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by Mikhail Bragin
 */
public class BreadthFirstSearch {

	Vertex start;

	UndirectedGraph graph;

	HashSet<Vertex> visited;

	HashMap<Vertex, Integer> distTo;

	HashMap<Vertex, Vertex> edgeTo;

	public BreadthFirstSearch(Vertex start, UndirectedGraph graph) {
		this.start = start;
		this.graph = graph;
		this.visited = new HashSet<Vertex>();
		this.distTo = new HashMap<Vertex, Integer>();
		this.edgeTo = new HashMap<Vertex, Vertex>();
	}

	private void BFS() {

		Queue<Vertex> queue = new LinkedList<Vertex>();

		queue.offer(start);
		distTo.put(start, 0);
		visited.add(start);

		while (!queue.isEmpty()) {

			Vertex v = queue.poll();

			for (Vertex adj : UndirectedGraph.adjacent(graph, v.getId())) {
				if (!visited.contains(adj)) {
					queue.offer(adj);
					distTo.put(adj, distTo.get(v) + 1);
					edgeTo.put(adj, v);
					visited.add(adj);
				}
			}
		}
	}

	public Collection<Vertex> getPath(Vertex to) {
		Stack<Vertex> path = new Stack<Vertex>();


		Vertex prev = edgeTo.get(to);
		path.add(to);

		while (!prev.equals(start)) {
			path.add(prev);
			prev = edgeTo.get(prev);
		}

		return path;
	}
}
