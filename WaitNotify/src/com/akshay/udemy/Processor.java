package com.akshay.udemy;

public class Processor {
	private void produce() throws InterruptedException {
		synchronized (this) {
			System.out.println("We are in produce method..."); // run this code
			wait(); // wait for other thread to run
			System.out.println("Producing again.."); // after getting signal from notify come back to run this again
		}
	}

	private void consume() throws InterruptedException {
		synchronized (this) {
			System.out.println("We are consuming");
			notify();
		}
	}

	public static void main(String[] args) {
		Processor p = new Processor();
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					p.produce();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		});

		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					p.consume();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		});

		t1.start();
		t2.start();

		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {

		}

	}
}
