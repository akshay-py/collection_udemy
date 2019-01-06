package com.akshay.udemy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class Processor implements Callable<String> {

	private int id;

	public Processor(int i) {
		this.id = i;
	}

	@Override
	public String call() throws Exception {
		Thread.sleep(1000);
		return "Id: " + id;
	}

}

public class App {

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		List<Future<String>> list = new ArrayList<>();

		for (int i = 0; i < 5; ++i) {
			Future<String> result = executorService.submit(new Processor(i));
			list.add(result);
		}

		list.forEach((Future value) -> {
			try {
				System.out.println(value.get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		});

		executorService.shutdown();
	}

}
