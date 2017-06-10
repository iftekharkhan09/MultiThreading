package com.src.threads;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ProducerConsumerProblem {
	public static void main(String[] args) throws InterruptedException {
		Program pr = new Program();
		Thread t1 = new Thread() {
			@Override
			public void run() {
				try {
					pr.producer();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		Thread t2 = new Thread() {
			@Override
			public void run() {
				try {
					pr.comsumer();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		t1.start();
		t2.start();
		t1.join();
		t2.join();
	}
}

class Program {
	final int CAPACITY = 20;
	int value = 0;
	LinkedList<Integer> list = new LinkedList<>();

	synchronized void producer() throws InterruptedException {
		while (value<10) {
			if (list.size() == CAPACITY)
				wait();
			System.out.println("produced -" + value);
			list.add(value++);
			notify();
			//Thread.sleep(1000);
		}
	}

	synchronized void comsumer() throws InterruptedException {
		while (true) {
			if (list.isEmpty())
				wait();
			System.out.println("Consumed - " + list.removeFirst());
			notify();
			//Thread.sleep(1000);
		}
	}
}
