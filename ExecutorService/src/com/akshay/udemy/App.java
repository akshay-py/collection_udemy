package com.akshay.udemy;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * 	1. ExecutorService executorService = Executors.newCachedThreadPool();
 * 		- going to return an executorService that can dynamically
 * 			reuse threads
 * 		- before starting a job -> it is going to check whether there are any threads that 
 * 			have finished the job.. reuse them
 * 		- if there are no waiting threads -> it is going to create another one
 * 
 * 	2. ExecutorService executorService = Executors.newFixedThreadPool(3);
 * 		- maximize the number of threads
 * 		- if we want to start a job -> if all the threads are busy, we have to wait for one 
 * 			to terminate
 * 
 *  3. ExecutorService executorService = Executors.newSingleThreadExecutor();
 *  	It uses a single thread for the job
 *  
 *  execute() -> runnable + callable
 *  submit() -> runnable
 *  
 */

class Worker implements Runnable {

	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			System.out.println("Counting.. " + i + " by thread.. " + Thread.currentThread().getName());
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

public class App {

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();

		for (int i = 0; i < 3; i++) {
			executorService.execute(new Worker());
		}

	}

}
