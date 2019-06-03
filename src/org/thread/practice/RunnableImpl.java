package org.thread.practice;

import java.util.Arrays;

public class RunnableImpl {
	public static void main(String[] args) throws InterruptedException {
		//Java 8 way
		// Thread wants runnable object, the below are 2 ways to implement thread.
		Thread t1 = new Thread(new ThreadWorker()::run);
		// Thread t1 = new Thread(()->new ThreadWorker().run());

		// Traditional way
		// ThreadWorker th = new ThreadWorker();
		// Thread t1 = new Thread(th);

		t1.start();
		t1.join();
		System.out.println("Main method terminated");

	}
}

class ThreadWorker implements Runnable {

	@Override
	public void run() {
		int[] arr = { 1, 4, 8, 9, 1, 0, 4, 5, 4 };
		System.out.println(Arrays.stream(arr).sum());
	}
}