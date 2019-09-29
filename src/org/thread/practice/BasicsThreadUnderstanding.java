package org.thread.practice;

public class BasicsThreadUnderstanding {
	public static void main(String[] args) {
		Check ck = new Check();
		Thread t1 = new Thread(ck::print);
		// If lock is acquired on Object and there are synchronized methods.
		// then 1 thread will wait for other thread.
		// If locks are acquired n different object then no 2 threads can take lock.
		Thread t2 = new Thread(new Check()::print2);

		// If lock is acquired on class then no other threads can take lock on objects.
		Check.increaseCount();

		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Check {

	private static long count = 0;

	public static synchronized void increaseCount() {
		try {
			System.out.println(Thread.currentThread().getName());
			count++;
			System.out.println("New Count: " + count);
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public synchronized void print() {
		try {
			System.out.println("Inside Print");
			System.out.println(Thread.currentThread().getName());
			System.out.println("Sleeping");
			Thread.sleep(3000);
			System.out.println("Releasing Lock");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public synchronized void print2() {
		try {
			System.out.println("Inside Print2");
			System.out.println(Thread.currentThread().getName());
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
