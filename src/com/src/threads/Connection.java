package com.src.threads;
import java.util.concurrent.Semaphore;

public class Connection {
	private Connection() {

	}

	private int connections = 0;
	private static Connection instance = new Connection();
	Semaphore semaphore = new Semaphore(10);

	public static Connection getConnection() {
		return instance;
	}

	public void connect() throws InterruptedException {
		try {
			Thread.sleep(1000);
			semaphore.acquire();
			doconnect();
		} finally {
			semaphore.release();
		}
	}

	public void doconnect() {
		synchronized (this) {
			connections++;
			System.out.println("No of Connections :" + connections);
		}
		synchronized (this) {
			connections--;
		}
	}

}
