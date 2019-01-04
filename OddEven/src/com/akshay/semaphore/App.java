package com.akshay.semaphore;

import java.util.concurrent.Semaphore;

public class App {

	public static void main(String[] args) {
		Semaphore evenSemaphore = new Semaphore(0);
		Semaphore oddSemaphore = new Semaphore(1);

		PrintOddNumberNoSync printOddNumber = new PrintOddNumberNoSync(evenSemaphore, oddSemaphore);
		PrintEvenNumberNoSync printEvenNumber = new PrintEvenNumberNoSync(evenSemaphore, oddSemaphore);

		new Thread(printOddNumber, "Print Odd").start();
		new Thread(printEvenNumber, "Print Even").start();
	}
}

class PrintEvenNumber implements Runnable {

	Semaphore semaphoreEven;
	Semaphore semaphoreOdd;

	public PrintEvenNumber(Semaphore semaphoreEven, Semaphore semaphoreOdd) {
		super();
		this.semaphoreEven = semaphoreEven;
		this.semaphoreOdd = semaphoreOdd;
	}

	@Override
	public void run() {
		for (int i = 2;; i = i + 2) {
			try {
				semaphoreEven.acquire();
				System.out.println(i);
				semaphoreOdd.release();
			} catch (InterruptedException e) {

			}
		}
	}
}

class PrintOddNumber implements Runnable {

	Semaphore semaphoreEven;
	Semaphore semaphoreOdd;

	public PrintOddNumber(Semaphore semaphoreEven, Semaphore semaphoreOdd) {
		super();
		this.semaphoreEven = semaphoreEven;
		this.semaphoreOdd = semaphoreOdd;
	}

	@Override
	public void run() {
		for (int i = 1;; i = i + 2) {
			try {
				semaphoreOdd.acquire();
				Thread.sleep(1000);
				System.out.println(i);
				semaphoreEven.release();
			} catch (InterruptedException e) {

			}
		}
	}

}
