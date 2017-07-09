package com.src.threads;

public class SynchronizationUsage {
	private volatile int count = 0;

	public static void main(String[] args) throws InterruptedException {
		new SynchronizationUsage().executeThread();
	}

	void increment(SynchronizationUsage threadCreation) {
		synchronized (threadCreation) {
			for (int i = 0; i < 10000000; i++) {
				count++;
			}
		}
	}

	void decrement(SynchronizationUsage threadCreation) {
		synchronized (threadCreation) {
			for (int i = 0; i < 10000000; i++) {
				count--;
			}
		}
	}

	void executeThread() throws InterruptedException {
		SynchronizationUsage threadCreation = new SynchronizationUsage();
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				increment(threadCreation);
			}
		});
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				decrement(threadCreation);
			}
		});
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println("Count is :" + count);
	}
}
