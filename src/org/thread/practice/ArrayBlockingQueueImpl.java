package org.thread.practice;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ArrayBlockingQueueImpl {

	// put and take are the main methods that will be used in MultiThreading.
	// At a time only 2 elements can be added or present in the Queue.
	// If we try to add more elements using add method
	// It will throw exception: illegalStateException: Queue is full.
	// If we are using offer(special case): it will return false.

	// Same goes for remove, it will remove exception like Queue is empty
	// whereas poll() will not throw any exception.

	private BlockingQueue<Integer> lst = new ArrayBlockingQueue<>(2);
	private int ele = 0;

	public ArrayBlockingQueueImpl() {

	}

	public static void main(String[] args) throws InterruptedException {
		ArrayBlockingQueueImpl obj = new ArrayBlockingQueueImpl();
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
			try {
				lst.put(ele);
				System.out.println("Produced " + ele);
				System.out.println("Size of List: " + lst.size());
				ele++;
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void dequeue() {
		System.out.println("***Consumer Thread Started***");
		while (true) {
			try {
				System.out.println(" Consumed: " + lst.take());
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
