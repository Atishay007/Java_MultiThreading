package org.thread.practice;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLock_Impl {

	private int a = 0;

	public static void main(String[] args) throws InterruptedException {
		Lock lock = new ReentrantLock();
		ReentrantLock_Impl obj = new ReentrantLock_Impl();
		Thread4 t1 = new Thread4(lock, obj);
		Thread4 t2 = new Thread4(lock, obj);
		Thread4 t3 = new Thread4(lock, obj);

		t1.start();
		t2.start();
		t3.start();

		t1.join();
		t2.join();
		t3.join();

		System.out.println("Main Method Finished");
	}

	public void incr() {
		++a;
		System.out.println("A value " + a);
	}
}

class Thread4 extends Thread {
	private Lock lock;
	private ReentrantLock_Impl obj;

	public Thread4(Lock lock2, ReentrantLock_Impl obj) {
		super();
		this.lock = lock2;
		this.obj = obj;
	}

	public void run() {
		try {
			lock.lock();
			obj.incr();
		} finally {
			lock.unlock();
		}

	}
}
