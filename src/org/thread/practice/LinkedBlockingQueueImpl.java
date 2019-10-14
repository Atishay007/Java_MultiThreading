package org.thread.practice;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class LinkedBlockingQueueImpl {
	public static void main(String[] args) {
		BlockingQueue<Integer> bq = new LinkedBlockingQueue<>();
		Thread t1 = new Thread(() -> producer(bq));
		Thread t2 = new Thread(() -> consumer(bq));

		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void producer(BlockingQueue<Integer> bq) {

		for (int i = 0; i < 10; i++) {
			try {
				bq.put(i);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void consumer(BlockingQueue<Integer> bq) {

		for (int i = 0; i < 10; i++) {
			try {
				System.out.println(bq.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
