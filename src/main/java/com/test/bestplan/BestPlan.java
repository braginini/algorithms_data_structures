package com.test.bestplan;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

/**
 * I assume that feature and plan number is not big and each feature should be covered by at least one plan.
 *
 * Play with prices and plans features to test the solution
 *
 * Created by Mikhail Bragin
 */
public class BestPlan {

	public static void main(String[] args) {

		HashSet<Plan> plans = new HashSet<Plan>();

		HashSet<String> features = new HashSet<String>();

		long start = System.currentTimeMillis();
		for (int i = 0; i < 11; i++) {
			plans.add(new Plan("p" + i, 1.0, "f" + i));
			features.add("f" + i);
		}
		Collection<Plan> result = bestPlans(plans, features);


		long end = System.currentTimeMillis() - start;

		System.out.println(end);

		for (Plan p : result) {
			System.out.println(p);
		}
	}

	/**
	 * While there are uncovered features I take all plans one by one which cover feature and go deeper in recursion
	 *
	 * @param plans
	 * @param features
	 * @return  the best Combination
	 */
	private static Collection<Plan> bestPlans(Collection<Plan> plans, Collection<String> features) {
		if (features.isEmpty()) //if all features are removed return and empty list (combination) to start adding plans
			return new ArrayList<Plan>();

		Collection<Plan> best = new ArrayList<Plan>();
		double bestCost = Double.MAX_VALUE;

		for (Plan p : plans) {

			Collection<String> currFeatures = new HashSet<String>(features); //keeps uncovered features
			//if the feature is covered by plan, remove it from uncovered list
			for (String f : p.Features) {
				currFeatures.remove(f);
			}

			Collection<Plan> currPlans = new HashSet<Plan>(plans); //keeps not scanned plans
			currPlans.remove(p); //remove the current plan since it's features were checked

			//go deeper in recursion to scan features of not scanned plans and see if they cover current uncovered features
			Collection<Plan> currBest = bestPlans(currPlans, currFeatures);
			currBest.add(p);

			double curCost = cost(currBest);
			if (curCost < bestCost) {
				bestCost = curCost;
				best = currBest;
			}
		}

		return best;
	}

	private static double cost(Collection<Plan> plans)
	{
		double cost = 0;
		for (Plan p : plans) {
			cost += p.Cost;
		}

		return cost;
	}

	static class Feature {
		public String Name; // treat as id

		public Feature(String Name) {
			this.Name = Name;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;

			Feature feature = (Feature) o;

			if (Name != null ? !Name.equals(feature.Name) : feature.Name != null) return false;

			return true;
		}

		@Override
		public int hashCode() {
			return Name != null ? Name.hashCode() : 0;
		}
	}

	static class Plan {
		public String Name; //treat as id
		public double Cost;
		public String[] Features;

		public Plan(String Name, double Cost, String ... features) {
			this.Name = Name;
			this.Cost = Cost;
			this.Features = features;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;

			Plan plan = (Plan) o;

			if (Name != null ? !Name.equals(plan.Name) : plan.Name != null) return false;

			return true;
		}

		@Override
		public int hashCode() {
			return Name != null ? Name.hashCode() : 0;
		}

		@Override
		public String toString() {
			return "Plan{" +
					"Name='" + Name + '\'' +
					", Cost=" + Cost + "}";
		}
	}

}
