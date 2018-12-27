package com.akshay.udemy;

import java.util.ArrayList;
import java.util.List;

public class Processor {
	private int LIMIT = 5;
	private int BOTTOM = 0;
	private int value = 0;
	private List<Integer> list = new ArrayList<Integer>();
	private Object lock = new Object();

	private void produce() throws InterruptedException {
		synchronized (lock) {
			while (true) {
				if (list.size() == LIMIT) {
					System.out.println("Waiting for consumer to remove..");
					lock.wait();
				} else {
					System.out.println("Adding... " + value);
					list.add(value);
					value++;
					lock.notify();
				}
				Thread.sleep(300);
			}
		}
	}

	private void consume() throws InterruptedException {
		synchronized (lock) {
			while (true) {
				if (list.size() == BOTTOM) {
					System.out.println("Waiting for producer to add..");
					lock.wait();
				} else {
					System.out.println("Removing... " + list.remove(--value));
					lock.notify();
				}
				Thread.sleep(300);
			}
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
