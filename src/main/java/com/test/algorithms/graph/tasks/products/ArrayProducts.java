package com.test.algorithms.graph.tasks.products;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Mikhail Bragin
 */
public class ArrayProducts {

	static int multiply(int[] nums, int p, int n) {
		return (n == nums.length) ? 1 : nums[n] * (p = multiply(nums, nums[n] * (nums[n] = p), n + 1))
				+ 0 * (nums[n] *= p);
	}

	static int[] multiply(int[] a) {
		int[] products = new int[a.length];

		int p = 1;
		for (int i = 0;i < a.length; i++) {
			products[i]= p;
			p*= a[i];
		}

		p = 1;

		for (int i = a.length-1; i >= 0; i--) {
			products[i]*= p;
			p*= a[i];
		}

		return products;
	}

	private static void testNoZero() {

		int[] a = new int[]{1, 2, 1, 4};

		a = arrayProduct(a);

		printResultArray(a, "No zero test:");
	}

	private static void testOneZero() {

		int[] a = new int[]{1, 2, 0, 4};

		a = arrayProduct(a);

		printResultArray(a, "One zero test:");

	}

	private static void testTwoZero() {

		int[] a = new int[]{1, 2, 0, 0, 7, 21};

		a = arrayProduct(a);

		printResultArray(a, "Two zeroes test:");

	}

	private static void printResultArray(int[] a, String name) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < a.length; i++) {
			sb.append(a[i]);
			if (i != a.length - 1)
				sb.append(",");
		}

		System.out.println(name);
		System.out.println(sb.toString());
		System.out.println();
	}

	//we need to treat zero cases if we have 1 zero -> we don't  include it in product
	//if we have 2 or more zeroes -> we have the result array with all zero elements
	public static int[] arrayProduct(int[] a) {

		int zeroIdx = -1;  //remember zero number index to improve the performance later
		int p = 1;
		for (int i = 0; i < a.length; i++) {
			if (a[i] == 0) {
				if (zeroIdx > -1) {
					return new int[a.length]; //return all zero elements if we had a zero before
				} else {
					zeroIdx = i;
				}
			} else {
				p *= a[i];
			}
		}

		int[] products = new int[a.length];

		//if there is a zero number in the array, all other number products will be zero
		//since we have zero index we put the product under this index and all other elements stay zero
		//in this case we have go through the array only once
		if (zeroIdx > -1) {
			products[zeroIdx] = p;
			return products;
		}

		for (int i = 0; i < a.length; i++) {
			products[i] = p / a[i];
		}

		return products;
	}


	public static void main(String[] args) {

		int[] a = new int[]{1,2,3};

		multiply(a);

		multiply(a, 1, 0);

		testOneZero();
		testTwoZero();
		testNoZero();
	}
}
