package com.src.threads;

public class PrintOddAndEvenNumbers {
	public static void main(String[] args) throws InterruptedException {
		final Counters counter = new Counters();
		new Thread() {
			@Override
			public void run() {
				try {
					counter.printEven();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			};
		}.start();
		new Thread() {
			@Override
			public void run() {
				try {
					counter.printOdd();
				} catch (Exception e) {
					e.printStackTrace();
				}
			};
		}.start();
	}
}

class Counters {
	private boolean even=true;
	synchronized void printEven() throws InterruptedException {
		for (int i = 0; i < 10; i = i + 2) {
			if(even)
			System.out.println(i);
			even=false;
			wait();
		}
	}

	synchronized void printOdd() {
		for (int i = 1; i < 10; i = i + 2) {
			if(!even)
			System.out.println(i);
			even=true;
			notify();
		}
	}
}
