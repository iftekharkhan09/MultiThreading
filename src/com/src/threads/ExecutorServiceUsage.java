package com.src.threads;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceUsage implements Runnable {
	private List<Integer> list1 = new ArrayList<>();
	private List<Integer> list2 = new ArrayList<>();

	void updateObject() throws InterruptedException {
		synchronized (list1) {
			System.out.println("List 1");
			for (int i = 0; i < 1000; i++) {
				Thread.sleep(1);
				list1.add(1);
			}
		}

		synchronized (list2) {
			System.out.println("List 2");
			for (int i = 0; i < 1000; i++) {
				Thread.sleep(1);
				list2.add(1);
			}
		}
	}

	@Override
	public void run() {
		try {
			updateObject();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		ExecutorService exec = Executors.newCachedThreadPool();
		long currentTime = System.currentTimeMillis();
		for (int i = 0; i < 2; i++) {
			exec.submit(new ExecutorServiceUsage());
		}
		exec.shutdown();
		exec.awaitTermination(1, TimeUnit.DAYS);
		long finishTime = System.currentTimeMillis();
		System.out.println("Time Taken :" + (finishTime - currentTime));
	}
}