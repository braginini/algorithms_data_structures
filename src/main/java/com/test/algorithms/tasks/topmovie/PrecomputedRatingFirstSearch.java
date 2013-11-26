package com.test.algorithms.tasks.topmovie;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Used when many clients access the result many times.
 * <p/>
 * First uses Depth-First Search to go through all the items and them sorts the result list.
 */
public class PrecomputedRatingFirstSearch {

	HashSet<Long> visited; //keeps the visited vertices

	HashMap<Long, Long> edgeTo;

	LinkedList<Movie> topMovies;  //use linked list not to resize array every time

	public PrecomputedRatingFirstSearch(Movie startMovie) {
		this.topMovies = new LinkedList<Movie>();
		this.edgeTo = new HashMap<Long, Long>();

		if (startMovie.getRecommendations().isEmpty())
			return;

		dfs(startMovie);

		Collections.sort(topMovies, Collections.reverseOrder());
	}

	private void dfs(Movie movie) {

		if (visited.contains(movie.getId()))
			return;

		visited.add(movie.getId());

		for (Movie m : movie.getRecommendations()) {
			topMovies.add(m);
			dfs(m);
		}

	}

	private void dfs(Movie movie, Movie from) {

		visited.add(movie.getId());

		edgeTo.put(movie.getId(), from.getId());

		for (Movie m : movie.getRecommendations()) {
			if (!visited.contains(m.getId())) {
				topMovies.add(m);
				dfs(m);
			}
		}

	}

	public Collection<Movie> getPath(Movie to) {

		long start = 0;  //dog nail we start from 0

		Collection<Movie> path = new Stack<Movie>();

		List<Long> idPath = new LinkedList<Long>();

		Long prev = edgeTo.get(to);
		idPath.add(to.getId());

		while (prev != start) {
			idPath.add(prev);
			prev = edgeTo.get(prev);
		}

		return path;
	}

	public Collection<Movie> getTopMovies(int n) {
		return topMovies.subList(0, n - 1);
	}
}
