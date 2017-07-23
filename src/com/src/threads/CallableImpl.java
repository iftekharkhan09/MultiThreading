package com.src.threads;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class CallableImpl {
	public static void main(String[] args) throws InterruptedException {
		long startTime = System.currentTimeMillis();
		ExecutorService executorService = Executors.newFixedThreadPool(1);
		List<Future<Integer>> list = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			Future<Integer> future = executorService.submit(new Callable<Integer>() {
				@Override
				public Integer call() throws Exception {
					Thread.sleep(10000);
					return 5;
				}
			});
			list.add(future);
		}
		executorService.shutdown();
		executorService.awaitTermination(1, TimeUnit.DAYS);
		// Wait for the Executor Threads to complete...
		long endTime = System.currentTimeMillis();
		System.out.println("Duration is :" + (endTime - startTime));
		try {
			Iterator<Future<Integer>> it = list.iterator();
			while (it.hasNext()) {
				System.out.println(it.next().get());
			}
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
}
