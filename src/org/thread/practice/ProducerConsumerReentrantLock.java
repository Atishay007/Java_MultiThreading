package org.thread.practice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//By using Reentrant Lock we can remove requirement of Synchronized block.
public class ProducerConsumerReentrantLock {

	private List<Integer> lst = new ArrayList<>();
	private static final int MAX_SIZE = 3;
	private static final int MIN_SIZE = 0;
	private int ele = 0;

	// Requirements to use with Reentrant Lock.
	private Lock lock = new ReentrantLock();
	private Condition con = lock.newCondition();

	public ProducerConsumerReentrantLock() {

	}

	public static void main(String[] args) throws InterruptedException {
		ProducerConsumerReentrantLock obj = new ProducerConsumerReentrantLock();
		Thread t1 = new Thread(obj::enqueue);
		Thread t2 = new Thread(obj::dequeue);
		t1.setName("Producer");
		t2.setName("Consumer");
		t1.start();
		t2.start();
		t1.join();
		t2.join();
	}

	public void enqueue() {
		System.out.println("***Producer Thread Started***");
		try {

			// Full BLock should be locked.
			lock.lock();

			while (true) {
				if (lst.size() < MAX_SIZE) {
					lst.add(ele);
					System.out.println(ele++);
					// //Similar to notify
					con.signalAll();
				} else {
					try {
						// Similar to wait
						con.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} finally {
			lock.unlock();
		}
	}

	public void dequeue() {
		System.out.println("***Consumer Thread Started***");
		try {
			// Full Block should be locked.
			lock.lock();
			while (true) {
				if (lst.size() > MIN_SIZE) {
					// NOTE: This is a preDecrement and it will return element.
					System.out.println(lst.remove(--ele));
					con.signalAll();
				} else {
					try {
						con.await();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} finally {
			lock.unlock();
		}
	}
}
