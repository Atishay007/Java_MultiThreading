package org.thread.practice;

//Example of Class level and Object level lock.
//static methods and synchronized(Abc.class) both are class level lock 
//and they both interfere with each other.
//Object level and Class level are different locks.
public class ClassLevelVSObjectLevelLock {
	public static void main(String[] args) {
		ClassLevelVSObjectLevelLock er = new ClassLevelVSObjectLevelLock();
		// Using method reference instead of Lambda.
		Thread t1 = new Thread(er::print1);
		Thread t2 = new Thread(ClassLevelVSObjectLevelLock::print2);
		Thread t3 = new Thread(er::print3);
		Thread t4 = new Thread(er::print4);

		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}

	private void print1() {
		// This is also a class level lock.
		synchronized (ClassLevelVSObjectLevelLock.class) {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Print 1");
		}
	}

	private void print3() {
		System.out.println("Print 3");
	}

	// All static methods are class level lock.
	private static synchronized void print2() {
		System.out.println("Print 2");
	}

	// This is Object level lock.
	private synchronized void print4() {
		System.out.println("Print 4");
	}
}
