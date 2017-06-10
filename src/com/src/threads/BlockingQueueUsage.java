package com.src.threads;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class Message {
	private String msg;

	public Message(String msg) {
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}
}

class Producer implements Runnable {
	private BlockingQueue<Message> queue;

	public Producer(BlockingQueue<Message> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		for (int i = 1; i < 10; i++) {
			try {
				queue.put(new Message("" + i));
				System.out.println("Produced "+i);
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		try {
			queue.put(new Message("exit"));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Consumer implements Runnable {
	private BlockingQueue<Message> queue;

	public Consumer(BlockingQueue<Message> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		Message msg;
		try {
			while ((msg = queue.take()).getMsg() != "exit") {
				Thread.sleep(50);
				System.out.println("Consumed " + msg.getMsg());
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

public class BlockingQueueUsage {
	public static void main(String[] args) {
		BlockingQueue<Message> queue = new ArrayBlockingQueue<>(100);
		Producer producer = new Producer(queue);
		Consumer consumer = new Consumer(queue);
		Thread t1 = new Thread(producer);
		Thread t2 = new Thread(consumer);
		t1.start();
		t2.start();
	}
}
