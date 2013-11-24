package com.test.algorithms.graph.datastructure;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

/**
 * Created by Mikhail Bragin
 */
public class UndirectedGraph<T> {

	Map<Long, Vertex<T>> vertices;

	public UndirectedGraph(Map<Long, Vertex<T>> vertices) {
		this.vertices = vertices;
	}

	/**
	 * Connects two vertices in specified graph
	 *
	 * @param graph
	 * @param id1
	 * @param id2
	 * @return
	 */
	public static boolean connect(UndirectedGraph graph, long id1, long id2) {

		if (!graph.contains(id1) || !graph.contains(id2))
			return false;

		Vertex v1 = graph.getVertex(id1);
		Vertex v2 = graph.getVertex(id2);

		v1.addNeighbour(v2);
		v2.addNeighbour(v1);

		return true;
	}

	/**
	 * Returns all adjacent vertices for given one
	 *
	 * @param graph
	 * @param vertexId
	 * @return
	 */
	public static Collection<Vertex> adjacent(UndirectedGraph graph, long vertexId) {

		if (graph.contains(vertexId)) {
			Vertex vertex = graph.getVertex(vertexId);
			return vertex.getAdjacentVertices();
		}

		return Collections.emptyList();
	}

	public Vertex<T> getVertex(long id) {
		return vertices.get(id);
	}

	public boolean contains(long id) {
		return vertices.containsKey(id);
	}
}
