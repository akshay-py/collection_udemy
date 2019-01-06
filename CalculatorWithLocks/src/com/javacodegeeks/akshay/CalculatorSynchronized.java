package com.javacodegeeks.akshay;

/*
 * Synchronized access to a pair of variables.
 * 
 * Disadvantage -> if we perform a lot of reads and few writes, 
 * 					synchronization coult hurt performance.
 */

public class CalculatorSynchronized {
	private int calculatedValue;
	private int value;

	public synchronized void calculate(int value) {
		this.value = value;
		this.calculatedValue = doMySlowCalculation(value);
	}

	public synchronized int getCalculatedValue() {
		return calculatedValue;
	}

	public synchronized int getValue() {
		return value;
	}

	public int doMySlowCalculation(int value) {
		int calculatedVal = 0;
		// Run in a different thread;
		return calculatedVal;
	}
}
