package org.thread.practice;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumerBlockingQueue_Impl {

	private BlockingQueue<Integer> lst = new ArrayBlockingQueue<>(10);
	private static final int MAX_SIZE = 3;
	private static final int MIN_SIZE = 0;
	private int ele = 0;

	public ProducerConsumerBlockingQueue_Impl() {

	}

	public static void main(String[] args) throws InterruptedException {
		ProducerConsumerBlockingQueue_Impl obj = new ProducerConsumerBlockingQueue_Impl();
		// Method Reference in Java.
		// Producers
		Thread t1 = new Thread(obj::enqueue);
		Thread t2 = new Thread(obj::enqueue);
		Thread t3 = new Thread(obj::enqueue);

		// consumers
		Thread t4 = new Thread(obj::dequeue);

		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t1.join();
		t2.join();
		t3.join();
		t4.join();
	}

	public void enqueue() {
		System.out.println("***Producer Thread Started***");
		while (true) {
			if (lst.size() < MAX_SIZE) {
				try {
					lst.put(ele);
					System.out.println("Produced " + ele);
					ele++;
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void dequeue() {
		System.out.println("***Consumer Thread Started***");
		while (true) {
			if (lst.size() > MIN_SIZE) {
				try {
					System.out.println(" Consumed: " + lst.take());
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
