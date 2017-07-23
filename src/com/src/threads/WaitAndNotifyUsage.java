package com.src.threads;
import java.util.Scanner;

public class WaitAndNotifyUsage {
	private Object lock = new Object();

	public void produce() throws InterruptedException {
		synchronized (lock) {
			System.out.println("Inside the Producer Method...");
			lock.wait();
			System.out.println("Released");
		}
	}

	public void consume() throws InterruptedException {
		Thread.sleep(2000);
		Scanner scanner = new Scanner(System.in);
		synchronized (lock) {
			System.out.println("Press Enter Key to release...");
			scanner.nextLine();
			lock.notify();
			Thread.sleep(5000);
		}
	}

	public static void main(String[] args) {
		WaitAndNotifyUsage waitAndNotifyUsage = new WaitAndNotifyUsage();
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					waitAndNotifyUsage.produce();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					waitAndNotifyUsage.consume();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		t1.start();
		t2.start();
	}
}
