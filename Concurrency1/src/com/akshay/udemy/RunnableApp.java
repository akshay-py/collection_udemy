package com.akshay.udemy;

class Runner3 implements Runnable {

	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			System.out.println("Runner3: " + i);
		}
	}
}

class Runner4 implements Runnable {

	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			System.out.println("Runner4: " + i);
		}
	}
}

public class RunnableApp {
	public static void main(String[] args) {
		Thread t1 = new Thread(new Runner3());
		Thread t2 = new Thread(new Runner4());

		long startTime = System.currentTimeMillis();

		t1.start();
		t2.start();

		try {
			t1.join(); // wait for t1 to die
			t2.join(); // wait for t2 to die
		} catch (InterruptedException e) {
		}

		long endTime = System.currentTimeMillis();

		System.out.println("Took " + (endTime - startTime));
	}
}
