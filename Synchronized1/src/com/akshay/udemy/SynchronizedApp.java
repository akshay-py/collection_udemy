package com.akshay.udemy;

public class SynchronizedApp {

	// both threads t1 and t2 will work on counter variable same time
	private static int counter = 0;

	public synchronized static void increment() {
		++counter;
	}

	public static void process() {
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 100; i++) {
					increment();
				}
			}
		});

		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 100; i++) {
					increment();
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
