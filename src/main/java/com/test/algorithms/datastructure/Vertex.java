package com.test.algorithms.datastructure;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mikhail Bragin
 */
public class Vertex<T> {

	long id;

	T value;

	Map<Long, Vertex<T>> adjacentVertices;

	public Vertex(long id, T value) {
		this.id = id;
		this.value = value;
	}

	public Vertex(long id, T value, Map<Long, Vertex<T>> adjacentVertices) {
		this.id = id;
		this.value = value;
		this.adjacentVertices = adjacentVertices;
	}

	public long getId() {
		return id;
	}

	public T getValue() {
		return value;
	}

	public Collection<Vertex<T>> getAdjacentVertices() {
		return adjacentVertices.values();
	}

	public void addNeighbour(Vertex v) {
		if (adjacentVertices == null)
			adjacentVertices = new HashMap<Long, Vertex<T>>();

		adjacentVertices.put(v.getId(), v);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Vertex vertex = (Vertex) o;

		if (id != vertex.id) return false;

		return true;
	}

	@Override
	public int hashCode() {
		return (int) (id ^ (id >>> 32));
	}
}