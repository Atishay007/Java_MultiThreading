package org.thread.practice;

import java.util.concurrent.CountDownLatch;
import java.util.stream.Stream;

//Thread which want to wait till all task are not completed will use await() method
//Task that are getting completed will reduce the countDown latch using method
//latch.countDown()
public class CountDownLatchImpl {
	public static void main(String[] args) throws InterruptedException {

		CountDownLatch latch = new CountDownLatch(3);
		Thread t1 = new Thread(new ThreadWorker11(latch));
		Thread t2 = new Thread(new ThreadWorker2(latch));
		Thread t3 = new Thread(new ThreadWorker3(latch));

		t1.start();
		t2.start();
		t3.start();

		// important method
		latch.await();
		System.out.println("All Task Completed");
	}

}

class ThreadWorker11 extends Thread {
	private CountDownLatch latch = null;

	public ThreadWorker11(CountDownLatch latch) {
		super();
		this.latch = latch;
	}

	@Override
	public void run() {
		System.out.println("Thread Name:" + Thread.currentThread());
		Stream.iterate(0, a -> a + 1).limit(10).forEach(System.out::println);
		// important method
		latch.countDown();
	}
}

class ThreadWorker2 extends Thread {
	private CountDownLatch latch = null;

	public ThreadWorker2(CountDownLatch latch) {
		super();
		this.latch = latch;
	}

	@Override
	public void run() {
		System.out.println("Thread Name:" + Thread.currentThread());
		Stream.iterate(10, a -> a + 1).limit(10).forEach(System.out::println);
		latch.countDown();
	}
}

class ThreadWorker3 extends Thread {
	private CountDownLatch latch = null;

	public ThreadWorker3(CountDownLatch latch) {
		super();
		this.latch = latch;
	}

	@Override
	public void run() {
		System.out.println("Thread Name:" + Thread.currentThread());
		Stream.iterate(20, a -> a + 1).limit(10).forEach(System.out::println);
		latch.countDown();
	}
}
