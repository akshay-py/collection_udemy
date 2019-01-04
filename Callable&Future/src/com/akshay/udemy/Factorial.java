package com.akshay.udemy;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class FactorialCalculator implements Callable<Long> {
	private int number;

	public FactorialCalculator(int number) {
		this.number = number;
	}

	@Override
	public Long call() throws Exception {
		return factorial();
	}

	private Long factorial() throws InterruptedException {
		long result = 1;
		while (number != 0) {
			result = number * result;
			number = number - 1;
			Thread.sleep(100);
		}

		return result;
	}

}

public class Factorial {

	public static void main(String[] args) {
		ExecutorService es = Executors.newCachedThreadPool();

		System.out.println("Submitted callable task to calculate factorial of 10");
		Future<Long> result10 = es.submit(new FactorialCalculator(10));

		System.out.println("Submitted callable task to calculate factorial of 15");
		Future<Long> result15 = es.submit(new FactorialCalculator(15));

		System.out.println("Submitted callable task to calculate factorial of 20");
		Future<Long> result20 = es.submit(new FactorialCalculator(20));

		try {
			System.out.println("Factorial of 10 -> " + result10.get());
			System.out.println("Factorial of 15 -> " + result15.get());
			System.out.println("Factorial of 20 -> " + result20.get());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

		es.shutdown();
	}

}
