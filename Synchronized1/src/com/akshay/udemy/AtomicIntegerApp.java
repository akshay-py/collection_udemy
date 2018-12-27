package com.akshay.udemy;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerApp {

	// both threads t1 and t2 will work on counter variable same time
	private static AtomicInteger counter = new AtomicInteger(0);

	public static void process() {
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 100; i++) {
					counter.getAndIncrement();
				}
			}
		});

		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 100; i++) {
					counter.getAndIncrement();
				}
			}
		});

		t1.start();
		t2.start();

		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		process();
		System.out.println("Counter -> " + counter);
	}

}
