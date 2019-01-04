package com.akshay.semaphore;

import java.util.concurrent.Semaphore;

public class AppNoSync {

	public static void main(String[] args) {
		Semaphore evenSemaphore = new Semaphore(0);
		Semaphore oddSemaphore = new Semaphore(1);

		PrintOddNumber printOddNumber = new PrintOddNumber(evenSemaphore, oddSemaphore);
		PrintEvenNumber printEvenNumber = new PrintEvenNumber(evenSemaphore, oddSemaphore);

		new Thread(printOddNumber, "Print Odd").start();
		new Thread(printEvenNumber, "Print Even").start();
	}
}

class PrintEvenNumberNoSync implements Runnable {

	Semaphore semaphoreEven;
	Semaphore semaphoreOdd;

	public PrintEvenNumberNoSync(Semaphore semaphoreEven, Semaphore semaphoreOdd) {
		super();
		this.semaphoreEven = semaphoreEven;
		this.semaphoreOdd = semaphoreOdd;
	}

	@Override
	public void run() {
		for (int i = 2;; i = i + 2) {
			// try {
			// semaphoreEven.acquire();
			System.out.println(i);
			// semaphoreOdd.release();
			// } catch (InterruptedException e) {

			// }
		}
	}
}

class PrintOddNumberNoSync implements Runnable {

	Semaphore semaphoreEven;
	Semaphore semaphoreOdd;

	public PrintOddNumberNoSync(Semaphore semaphoreEven, Semaphore semaphoreOdd) {
		super();
		this.semaphoreEven = semaphoreEven;
		this.semaphoreOdd = semaphoreOdd;
	}

	@Override
	public void run() {
		for (int i = 1;; i = i + 2) {
			// try {
			// semaphoreOdd.acquire();
			// Thread.sleep(1000);
			System.out.println(i);
			// semaphoreEven.release();
			// } catch (InterruptedException e) {

			// }
		}
	}

}
