package com.test.algorithms.graph.tasks.topmovie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mikhail Bragin
 */
public class Movie implements Comparable<Movie> {

	long id;

	float rating;

	List<Movie> recommendations = new ArrayList<Movie>();

	public Movie(long id, float rating) {
		this.id = id;
		this.rating = rating;
	}

	public Movie(long id, float rating, List<Movie> recommendations) {
		this.id = id;
		this.rating = rating;
		this.recommendations = recommendations;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public List<Movie> getRecommendations() {
		return recommendations;
	}

	public void setRecommendations(List<Movie> recommendations) {
		this.recommendations = recommendations;
	}

	public void addRecommendation(Movie movie) {
		this.recommendations.add(movie);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Movie movie = (Movie) o;

		if (id != movie.id) return false;

		return true;
	}

	@Override
	public int hashCode() {
		return (int) (id ^ (id >>> 32));
	}

	@Override
	public int compareTo(Movie o) {

		if (o.getRating() > this.rating)
			return -1;
		else if (o.getRating() < this.rating) {
			return 1;
		}

		return 0;
	}
}
