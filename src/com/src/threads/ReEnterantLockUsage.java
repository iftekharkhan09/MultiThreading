package com.src.threads;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReEnterantLockUsage {
	public static void main(String[] args) throws InterruptedException {
		new Locking().execute();
	}
}

class Locking {
	private int count;
	private Lock lock = new ReentrantLock();
	private Condition cond = lock.newCondition();

	void increment() {
		for (int i = 0; i < 1000000; i++)
			count++;
	}

	void decrement() {
		for (int i = 0; i < 1000000; i++)
			count--;
	}

	public void execute() throws InterruptedException {
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					lock.lock();
					increment();
					cond.await();
					System.out.println("Thread 1");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					lock.unlock();
				}
			}
		});
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					lock.lock();
					decrement();
					cond.signal();
				} finally {
					lock.unlock();
				}
			}
		});
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println("Count is :" + count);
	}
}