package com.javacodegeeks.akshay;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CalculatorReadWriteLock {
	private int calculatedValue;
	private int value;
	private ReadWriteLock lock = new ReentrantReadWriteLock();

	public void calculate(int value) {
		lock.writeLock().lock();
		try {
			this.value = value;
			this.calculatedValue = doMySlowCalculation(value);
		} finally {
			lock.writeLock().unlock();
		}
	}

	public int getCalculatedValue() {
		lock.readLock().lock();
		try {
			return calculatedValue;
		} finally {
			lock.readLock().unlock();
		}
	}

	public int getValue() {
		lock.readLock().lock();
		try {
			return value;
		} finally {
			lock.readLock().unlock();
		}
	}

	public int doMySlowCalculation(int value) {
		int calculatedVal = 0;
		// Run in a different thread;
		return calculatedVal;
	}
}
