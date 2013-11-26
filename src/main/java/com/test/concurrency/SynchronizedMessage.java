package com.test.concurrency;

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Mikhail Bragin
 */
public class SynchronizedMessage {

	private String message;

	// True if consumer should wait
	// for producer to send message,
	// false if producer should wait for
	// consumer to retrieve message.
	private boolean empty = true;

	public synchronized String take() {

		//if we have no message (is empty) we wait for producer to notify us that he put a message
		//this technique saves the processor resources (we can actually check the flag in while loop without wait()
		//construction but it will cause a huge CPU consuming
		while (empty) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}

		empty = true;  //here we set empty to true saying that we read a message
		notifyAll();  //notify producer that we read a message
		return message;
	}

	public synchronized void put(String message) {

		// Wait until message has
		// been retrieved.
		while (!empty) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}

		empty = false; //reset flag
		this.message = message;  //store the message
		notifyAll();  //notify consumer that we put a new message
	}

	public static void main(String[] args) {

		final SynchronizedMessage message = new SynchronizedMessage();
		final String[] messagesToSend = new String[]{"1", "2", "3", "4", "5", "LAST"};
		final Random random = new Random();

		Runnable producer = new Runnable() {
			@Override
			public void run() {
				for (String s : messagesToSend) {

					System.out.println("Produced message: " + s);
					message.put(s);

					try {
						Thread.sleep(random.nextInt(3000));
					} catch (InterruptedException e) {
						return;
					}

				}
			}
		};

		Runnable consumer = new Runnable() {
			@Override
			public void run() {

				for (String s = message.take(); !s.equals("LAST"); s = message.take()) {
					System.out.println("Consumed message: " + s);
					try {
						Thread.sleep(random.nextInt(3000));
					} catch (InterruptedException e) {
					}
				}
			}
		};

		new Thread(producer).start();
		new Thread(consumer).start();

	}


}
