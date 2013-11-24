package com.test.algorithms.graph.tasks.topmovie;

import java.util.Collection;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * Used when 1 client access is done.
 * Instead of sorting the entire array of items this class uses min heap to store top m items.
 * So assuming that n is total number of items and m is number of top movies to return we have
 * a time complexity of n * log(m) which is way smaller than n * log(n).
 */
public class RealTimeRatingFirstSearch {

	HashSet<Long> visited; //keeps the visited vertices

	PriorityQueue<Movie> topMovies;

	int topMoviesSize;

	public RealTimeRatingFirstSearch(Movie startMovie, int m) {
		this.topMovies = new PriorityQueue<Movie>(m);
		this.topMoviesSize = m;

		if (startMovie.getRecommendations().isEmpty() || m <= 0)
			return;

		topMovies.add(startMovie);
		dfs(startMovie);
	}

	private void dfs(Movie movie) {

		if (visited.contains(movie.getId()))
			return;

		visited.add(movie.getId());

		for (Movie m : movie.getRecommendations()) {

			if (topMovies.size() == topMoviesSize) {
				Movie lowestInTop = topMovies.peek();
				if (lowestInTop.getRating() < m.getRating()) {
					topMovies.poll();
					topMovies.offer(m);
				}
			} else {
				topMovies.offer(m);
			}

			dfs(m);
		}

	}

	public Collection<Movie> getTopMovies() {
		return topMovies;
	}
}
