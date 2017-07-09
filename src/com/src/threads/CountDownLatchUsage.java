package com.src.threads;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CountDownLatchUsage {
	public static void main(String[] args) throws InterruptedException {
		CountDownLatch latch = new CountDownLatch(3);
		Executor exec = Executors.newFixedThreadPool(3);
		for (int i = 0; i < 3; i++)
			exec.execute(new Processor(latch));
		latch.await();
		System.out.println("Completed!!");
	}
}

class Processor implements Runnable {
	private CountDownLatch latch;

	public Processor(CountDownLatch latch) {
		this.latch = latch;
	}

	@Override
	public void run() {
		System.out.println("I Am Here!!");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		latch.countDown();
	}
}
