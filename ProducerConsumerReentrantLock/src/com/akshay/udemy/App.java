package com.akshay.udemy;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Worker {
	Lock lock = new ReentrantLock();
	Condition cond = lock.newCondition();

	void producer() throws InterruptedException {
		lock.lock();
		System.out.println("Producer method..");
		cond.await();
		System.out.println("Producer again..");
	}

	void consumer() throws InterruptedException {
		lock.lock();
		System.out.println("Consumer method..");
		cond.signal();
		lock.unlock();
	}
}

public class App {

	public static void main(String[] args) {
		Worker worker = new Worker();

		Thread t1 = new Thread(() -> {
			try {
				worker.producer();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		Thread t2 = new Thread(() -> {
			try {
				worker.consumer();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
