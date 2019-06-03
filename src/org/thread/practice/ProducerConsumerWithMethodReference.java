package org.thread.practice;

import java.util.ArrayList;
import java.util.List;

public class ProducerConsumerWithMethodReference {

	private List<Integer> lst = new ArrayList<>();
	private static final int MAX_SIZE = 3;
	private static final int MIN_SIZE = 0;
	private int ele = 0;

	public ProducerConsumerWithMethodReference() {

	}

	public static void main(String[] args) throws InterruptedException {
		ProducerConsumerWithMethodReference obj = new ProducerConsumerWithMethodReference();
		// Method Reference in Java.
		Thread t1 = new Thread(obj::enqueue);
		Thread t2 = new Thread(obj::dequeue);

		t1.start();
		t2.start();
		t1.join();
		t2.join();
	}

	public synchronized void enqueue() {
		System.out.println("***Producer Thread Started***");
		while (true) {
			if (lst.size() < MAX_SIZE) {
				lst.add(ele);
				System.out.println(ele++);
				notify();
			} else {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public synchronized void dequeue() {
		System.out.println("***Consumer Thread Started***");
		while (true) {
			if (lst.size() > MIN_SIZE) {
				System.out.println(lst.remove(--ele));
				notify();
			} else {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
