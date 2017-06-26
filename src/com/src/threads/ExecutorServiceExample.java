package com.src.threads;

import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceExample {
	public static void main(String[] args) {
		ExecutorService executor=Executors.newFixedThreadPool(10);
		Random random=new Random();
		int waitTime=600;
		for(int i=0;i<10;i++) {
			String name="name"+i;
			waitTime=random.nextInt()+waitTime;
			Runnable runner=new TaskPrinter(name, waitTime);
			executor.execute(runner);
		}
		try {
			executor.awaitTermination(waitTime, TimeUnit.MILLISECONDS);
			executor.shutdown();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}

}
