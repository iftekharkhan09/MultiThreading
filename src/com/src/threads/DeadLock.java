package com.src.threads;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLock {
	private Account acc1 = new Account();
	private Account acc2 = new Account();

	private Lock lock1 = new ReentrantLock();
	private Lock lock2 = new ReentrantLock();

	public void acquireLocks(Lock lock1, Lock lock2) throws InterruptedException {
		while (true) {
			boolean firstLockAcquired = false;
			boolean secondLockAcquired = false;
			try {
				firstLockAcquired = lock1.tryLock();
				secondLockAcquired = lock2.tryLock();
			} finally {
				if (firstLockAcquired && secondLockAcquired) {
					return;
				} else if (firstLockAcquired) {
					lock1.unlock();
				} else if (secondLockAcquired)
					lock2.unlock();
			}
		}
	}

	public void firstThread() {
		Random random = new Random();
		try {
			acquireLocks(lock1, lock2);
			for (int i = 0; i < 10000; i++) {
				Account.transfer(acc1, acc2, random.nextInt(100));
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock1.unlock();
			lock2.unlock();
		}
	}

	public void secondThread() {
		Random random = new Random();
		try {
			acquireLocks(lock2, lock1);
			for (int i = 0; i < 10000; i++) {
				Account.transfer(acc2, acc1, random.nextInt(100));
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			lock1.unlock();
			lock2.unlock();
		}
	}

	public void finished() {
		System.out.println("Account1 Balance is :" + acc1.checkBalance());
		System.out.println("Account2 Balance is :" + acc2.checkBalance());
		System.out.println("Total Balance is :" + (acc1.checkBalance() + acc2.checkBalance()));
	}

	public static void main(String[] args) throws InterruptedException {
		DeadLock deadLock = new DeadLock();
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				deadLock.firstThread();
			}
		});
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				deadLock.secondThread();
			}
		});
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		deadLock.finished();
	}

}
