package org.thread.practice;

import java.util.ArrayList;
import java.util.List;

public class ProducerConsumerProblem {

	private static int MAX = 5;
	private static int MIN = 0;
	private List<Integer> lst = new ArrayList<>();
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

		t1.setName("Producer Thread");
		t1.start();
		t2.start();
		t1.join();
		t2.join();
	}

	private void producer() throws InterruptedException {
		System.out.println("Producer Thread Started: ");
		System.out.println(Thread.currentThread().getName());
		synchronized (lst) {

			while (true) {
				if (lst.size() == MAX) {
					// Code after wait() method will not be executed.
					lst.wait();
				} else {
					lst.add(COUNT);
					System.out.println("No Added: " + COUNT);
					COUNT++;
					// if there is code after notify()method then that code will be
					// executed.
					lst.notify();
				}
				Thread.sleep(300);
			}
		}
	}

	private void consumer() throws InterruptedException {
		System.out.println("Consumer Thread Started: ");
		synchronized (lst) {
			while (true) {
				if (lst.size() == MIN) {
					// Code after wait() method will not be executed.
					lst.wait();
				} else {
					System.out.println("No Removed: " + lst.remove(--COUNT));
					// Code after notify will get executed.
					lst.notify();
				}
				Thread.sleep(300);
			}
		}
	}
}
