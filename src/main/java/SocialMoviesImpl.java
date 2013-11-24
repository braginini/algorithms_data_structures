import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Mikhail Bragin
 */
public class SocialMoviesImpl implements SocialMovies {

	HashMap<Integer, Movie> movies;

	HashMap<Integer, Customer> customers;

	public SocialMoviesImpl() {
		this.movies = new HashMap<Integer, Movie>();
		this.customers = new HashMap<Integer, Customer>();
	}

	//constant time O(1) since we use hashmap
	@Override
	public void addMovie(int movieId, String title) {
		this.movies.put(movieId, new Movie(movieId, title));
	}

	//constant time O(1) since we use hashmap
	@Override
	public String lookupMovie(int movieId) {
		Movie movie = this.movies.get(movieId);
		if (movie != null)
			return movie.getTitle();

		return null;
	}

	//constant time O(1) since we use hashmap
	@Override
	public void addCustomer(int customerId, String name) {
		this.customers.put(customerId, new Customer(customerId, name));
	}

	//constant time O(1) since we use hashmap
	@Override
	public String lookupCustomer(int customerId) {
		Customer customer = this.customers.get(customerId);
		if (customer != null)
			return customer.getName();

		return null;
	}

	//constant time O(1) since we use hashset for movies
	@Override
	public void addLikedMovie(int customerId, int movieId) {
		Customer customer = this.customers.get(customerId);
		Movie movie = this.movies.get(movieId);

		if (customer != null && movie != null) {
			customer.likedMovie(movie);
		}
	}

	//constant time O(1) since we use hashset for movies
	@Override
	public void addWatchedMovie(int customerId, int movieId) {
		Customer customer = this.customers.get(customerId);
		Movie movie = this.movies.get(movieId);

		if (customer != null && movie != null) {
			customer.watchedMovie(movie);
		}
	}

	//constant time O(1) since we use hashset for movies
	@Override
	public void addFriend(int customerId1, int customerId2) {
		Customer customer1 = this.customers.get(customerId1);
		Customer customer2 = this.customers.get(customerId2);

		if (customer1 != null && customer2 != null) {
			customer1.addFriend(customer2);
			customer2.addFriend(customer1);
		}

	}

	@Override
	public Collection<Integer> getRecommendationsFromFriends(int customerId) {

		Customer customer = this.customers.get(customerId);

		if (customer != null) {

			//create a hash to exclude duplicates in case if the same movie was watched by 2 or more friends
			Collection<Integer> recommendations = new HashSet<Integer>();

			Set<Movie> watchedMovies = customer.getWatched();

			for (Customer friend : customer.friends) {

				for (Movie m : friend.getLiked()) {
					//check whether movies is not in the source user watched list
					if (!watchedMovies.contains(m)) {
						recommendations.add(m.getId());
					}
				}
			}

			return recommendations;

		}

		return Collections.emptyList();
	}

	// Returns the IDs of customers that:
	// - The given customer is not already friends with
	// - Are friends with at least <minimumCommonFriends> friends that the given customer is friends with
	// Example: Using the friend links shown below...
	//          A-B-D     getFriendRecommendations(A,1) -> {D,E}
	//           \ \      getFriendRecommendations(A,2) -> {E}
	//            C-E     getFriendRecommendations(A,3) -> {}
	@Override
	public Collection<Integer> getFriendRecommendations(int customerId, int minimumCommonFriends) {

		Customer srcCustomer = this.customers.get(customerId);

		if (srcCustomer != null) {

			Set<Integer> recommendations = new HashSet<Integer>();
			Set<Customer> friends = srcCustomer.getFriends();

			for (Customer friend : friends) {   //time proportional to size of friend list (N)

				for (Customer friendsFriend : friend.getFriends()) {  //time proportional to size of friends friend list (X)

					int commonFriends = 0;

					if (friendsFriend.equals(srcCustomer))
						continue;

					for (Customer fr : friendsFriend.getFriends()) {
						if (friends.contains(fr)) {
							commonFriends++;
						}
					}

					if (commonFriends >= minimumCommonFriends)
						recommendations.add(friendsFriend.getId());
				}

			}

			return recommendations;
		}

		return Collections.emptyList();
	}

	public class Movie {

		int id;

		String title;

		HashSet<Integer> recommendations;

		public Movie(int id, String title) {
			this.id = id;
			this.title = title;
		}

		public int getId() {
			return id;
		}

		public String getTitle() {
			return title;
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
			return id;
		}
	}

	public class Customer {

		int id;

		String name;

		HashSet<Customer> friends;

		HashSet<Movie> liked;

		HashSet<Movie> watched;

		public Customer(int id, String name) {
			this.id = id;
			this.name = name;
		}

		public int getId() {
			return id;
		}

		public String getName() {
			return name;
		}

		public void likedMovie(Movie movie) {
			this.liked.add(movie);
		}

		public void watchedMovie(Movie movie) {
			this.watched.add(movie);
		}

		public void addFriend(Customer friend) {
			this.friends.add(friend);
		}

		public HashSet<Movie> getLiked() {
			return liked;
		}

		public HashSet<Movie> getWatched() {
			return watched;
		}

		public HashSet<Customer> getFriends() {
			return friends;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;

			Customer customer = (Customer) o;

			if (id != customer.id) return false;

			return true;
		}

		@Override
		public int hashCode() {
			return id;
		}
	}
}
