package com.test.concurrency;

/**
 * Created by Mikhail Bragin
 */
public class Threads {

	public static void main (String[] args) {

		Runnable r = new Runnable() {
			@Override
			public void run() {
				System.out.println("Hey1");
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					return;
				}


				System.out.println("Hey2 " + Thread.activeCount());
			}
		};
		Thread.interrupted();


		Thread t = new Thread(r);
		t.start();
		try {
			t.join();
		} catch (InterruptedException e) {
			return;
		}
	}
}
