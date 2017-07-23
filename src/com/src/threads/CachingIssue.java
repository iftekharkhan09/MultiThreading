package com.src.threads;
import java.util.Scanner;

public class CachingIssue extends Thread {
	private boolean running = true;

	//Thread 1 is reading the variable....
	@Override
	public void run() {
		while (running)
			System.out.println("Hello!!");
	}

	//Main Thread is updating the variable....
	void shutdown() throws InterruptedException {
		running = false;
	}

	public static void main(String[] args) throws InterruptedException {
		CachingIssue c = new CachingIssue();
		c.start();
		System.out.println("Enter Here!!");
		Scanner sc = new Scanner(System.in);
		sc.nextLine();
		c.shutdown();
	}
}
