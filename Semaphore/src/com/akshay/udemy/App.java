package com.akshay.udemy;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/*
 * 	- semaphore maintains a set of permits
 * 	- acquire() -> if a permit is available then takes it 
 *  - release() -> adds a permit back
 *  
 *  	Semaphore just keeps a count of the number available
 *  	Semaphore(int permits, boolean fair)
 */

enum Downloader {
	INSTANCE;

	private Semaphore semaphore = new Semaphore(3, true);

	// 3 permits -> 3 threads can execute the below code
	// others will have to wait till 1 of the 3 finish and return the permit

	public void downloadData() {
		try {
			semaphore.acquire(); // get the permit
			download();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaphore.release(); // release the permit
		}
	}

	private void download() {
		System.out.println("Downloading data from the web... -> " + Thread.currentThread().getName());
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

public class App {

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();

		for (int i = 0; i < 12; i++) {
			executorService.execute(new Runnable() {

				@Override
				public void run() {
					Downloader.INSTANCE.downloadData();
				}
			});
		}

	}
}
