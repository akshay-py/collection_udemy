package com.akshay.udemy;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class App {
	/*
	 * ReentrantLock - it has the same behavior as the "synchronized approach" - of
	 * course it has some additional features
	 * 
	 * new ReentrantLock(boolean fairnessParameter) - fairness parameter: if it is
	 * set to be true --> the longest-waiting thread will get the lock
	 * 
	 * fairness == false --> there is no access order !!!
	 * 
	 * IMPORTANT: we have to use try-catch block when doing critical section that
	 * may throw exceptions - we call unlock() in the finally block !!!-
	 */

	private static int counter = 0;
	private Lock reentrantLock = new ReentrantLock();

	private void increment() {
		reentrantLock.lock();
		try {
			for (int i = 0; i < 1000; i++) {
				counter++;
			}
		} finally {
			reentrantLock.unlock();
		}
	}

	public static void main(String[] args) {
		App app = new App();

		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				app.increment();
			}
		});

		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				app.increment();
			}
		});

		t1.start();
		t2.start();

		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Counter -> " + counter);

	}
}
