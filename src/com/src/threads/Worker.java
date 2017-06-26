package com.src.threads;

public class Worker {
	public static void main(String[] args) {
		final PrintEvenAndOddNumbers printEvenAndOddNumbers = new PrintEvenAndOddNumbers();
		final ProducerAndConsumer producerAndConsumer = new ProducerAndConsumer();
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					// printEvenAndOddNumbers.printEven();
					producerAndConsumer.produce();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					// printEvenAndOddNumbers.printOdd();
					producerAndConsumer.consume();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
