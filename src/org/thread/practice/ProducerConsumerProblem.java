package org.thread.practice;

import java.util.ArrayList;
import java.util.List;

public class ProducerConsumerProblem {

	private static int MAX = 5;
	private static int MIN = 0;
	private static List<Integer> lstofInt = new ArrayList<>();
	private static int COUNT = 0;

	public ProducerConsumerProblem() {

	}

	public static void main(String[] args) throws InterruptedException {
		ProducerConsumerProblem tp = new ProducerConsumerProblem();
		Thread t1 = new Thread(() -> {
			try {
				tp.producer();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		Thread t2 = new Thread(() -> {
			try {
				tp.consumer();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		t1.start();
		t2.start();
		t1.join();
		t2.join();
	}

	private void producer() throws InterruptedException {
		System.out.println("Producer Thread Started: ");
		synchronized (this) {

			while (true) {
				if (lstofInt.size() == MAX) {
					wait();
				} else {
					lstofInt.add(COUNT);
					System.out.println("No Added: " + COUNT);
					COUNT++;
					// if there is code after notify()method then that code will be
					// executed.
					notify();
				}
				Thread.sleep(300);
			}
		}
	}

	private void consumer() throws InterruptedException {
		System.out.println("Consumer Thread Started: ");
		synchronized (this) {
			while (true) {
				if (lstofInt.size() == MIN) {
					wait();
				} else {
					System.out.println("No Removed: " + lstofInt.remove(--COUNT));
					notify();
				}
				Thread.sleep(300);
			}
		}
	}
}
