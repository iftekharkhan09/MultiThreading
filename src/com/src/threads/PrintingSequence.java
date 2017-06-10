package com.src.threads;

public class PrintingSequence {
	public static void main(String[] args) {
		Thread t1 = new Thread(new T1());
		Thread t2 = new Thread(new T2());
		t1.start();
		t2.start();
	}
}

class T1 extends Thread {
	@Override
	public void run() {
		for (int i = 0; i < 10; i = i + 2) {
			System.out.println(i);
		}
	}
}

class T2 extends Thread {
	@Override
	public void run() {
		for (int i = 0; i < 10; i = i + 2) {
			System.out.println(i);
		}
	}
}