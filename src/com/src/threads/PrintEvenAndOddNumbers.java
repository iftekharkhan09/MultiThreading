package com.src.threads;

public class PrintEvenAndOddNumbers {
	private Object lock = new Object();
	private int value = 0;
	private boolean even = true;

	public void printEven() throws InterruptedException {
		while (value < 20) {
			synchronized (lock) {
				while (even) {
					System.out.println("Even :" + value++);
					even = false;
					lock.wait();
				}
			}
		}
	}

	public void printOdd() throws InterruptedException {
		while (value < 21) {
			synchronized (lock) {
				while (!even) {
					System.out.println("odd :" + value++);
					even = true;
					lock.notify();
				}
			}
		}
	}
}
