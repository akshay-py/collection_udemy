package com.geekforgeeks.akshay;

import java.util.concurrent.Semaphore;

/*
 * Semaphore in Java
	A semaphore controls access to a shared resource through the use of a counter. 
	If the counter is greater than zero, then access is allowed. If it is zero, then access is denied. 
	What the counter is counting are permits that allow access to the shared resource. 
	Thus, to access the resource, a thread must be granted a permit from the semaphore.

	Working of semaphore

	In general, to use a semaphore, the thread that wants access to the shared resource tries to acquire a permit.

	If the semaphore’s count is greater than zero, then the thread acquires a permit, which causes the semaphore’s count to be decremented.
	Otherwise, the thread will be blocked until a permit can be acquired.
	When the thread no longer needs an access to the shared resource, it releases the permit, which causes the semaphore’s count to be incremented.
	If there is another thread waiting for a permit, then that thread will acquire a permit at that time.*/

class Shared {
	static int count = 0;
}

class MyThread extends Thread {
	Semaphore sem;
	String threadName;

	public MyThread(Semaphore sem, String threadName) {
		super(threadName);
		this.sem = sem;
		this.threadName = threadName;
	}

	@Override
	public void run() {

		// run by thread A
		if (this.getName().equals("A")) {
			System.out.println("Starting " + threadName);
			try {
				// First, get a permit.
				System.out.println(threadName + " is waiting for a permit.");

				// acquiring the lock
				sem.acquire();

				System.out.println(threadName + " acquired a permit.");

				// Now, accessing the shared resource.
				// other waiting threads will wait, until this
				// thread release the lock

				for (int i = 0; i < 5; i++) {
					Shared.count++;
					System.out.println(threadName + ": " + Shared.count);

					// Now, allowing a context switch
					// for thread B to execute
					Thread.sleep(10);
				}
			} catch (InterruptedException e) {
				System.out.println(e);
			}

			// Release the permit.
			System.out.println(threadName + " released the permit.");
			sem.release();
		} else {
			// run by thread B
			System.out.println("Starting " + threadName);

			try {
				// First, get a permit.
				System.out.println(threadName + " is waiting for a permit.");

				// acquiring the lock
				sem.acquire();

				System.out.println(threadName + " acquired a permit.");

				// Now, accessing the shared resource.
				// other waiting threads will wait, until this
				// thread release the lock

				for (int i = 0; i < 5; i++) {
					Shared.count--;
					System.out.println(threadName + ": " + Shared.count);

					// Now, allowing a context switch
					// for thread B to execute
					Thread.sleep(10);
				}
			} catch (InterruptedException e) {
				System.out.println(e);
			}

			// Release the permit.
			System.out.println(threadName + " released the permit.");
			sem.release();
		}
	}
}

// Main class
public class SemaphoreDemo {

	public static void main(String[] args) {
		// creating a Semaphore object
		// with number of permits 1
		Semaphore sem = new Semaphore(1);

		// creating two threads with name A and B
		// Note that thread A will increment the count
		// and thread B will decrement the count
		MyThread mt1 = new MyThread(sem, "A");
		MyThread mt2 = new MyThread(sem, "B");

		// starting threads A and B
		mt1.start();
		mt2.start();

		// waiting for threads A and B
		try {
			mt1.join();
			mt2.join();
		} catch (InterruptedException e) {
		}

		// count will always remain 0 after
		// both threads will complete their execution
		System.out.println("Count: " + Shared.count);
	}

}
