package com.src.threads;

import java.util.LinkedList;

public class ProducerAndConsumer {
	private LinkedList<Integer> list = new LinkedList<Integer>();
	private Object lock = new Object();
	private int value = 0;
	private final int LIMIT = 10;

	public void produce() throws InterruptedException {
		while (true) {
			synchronized (lock) {
				while (list.size() == LIMIT) {
					lock.wait();
				}
				System.out.println("Producer produced :" + value);
				list.add(value++);
				lock.notify();
			}
		}
	}
	public void consume() throws InterruptedException {
		while (true) {
			synchronized (lock) {
				while (list.size() == 0) {
					lock.wait();
				}
				System.out.println("Consumer consumed :" + list.removeFirst());
				lock.notify();
			}
		}
	}
}
