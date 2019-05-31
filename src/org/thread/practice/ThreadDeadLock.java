package org.thread.practice;

//Classic Example of Thread DeadLock.

//We can detect Thread DeadLock using 
//1. Jconsole
//2. Java Mission Control

//Solution of DeadLock is to keep lock in sequence.
//Like Always Bool will have 1st lock then Pen will have other lock.
public class ThreadDeadLock {
	public static void main(String[] args) {
		Object pen = new Object();
		Object book = new Object();

		Thread1 t1 = new Thread1(pen, book);

		Thread2 t2 = new Thread2(pen, book);

		t1.start();
		t2.start();
		System.out.println("Main Finished");
	}
}

class Thread1 extends Thread {

	private Object pen;
	private Object book;

	public Thread1(Object pen, Object book) {
		super();
		this.pen = pen;
		this.book = book;
	}

	@Override
	public void run() {
		synchronized (book) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized (pen) {
				System.out.println("Thread 1 working");
			}
		}
	}
}

class Thread2 extends Thread {

	private Object pen;
	private Object book;

	public Thread2(Object pen, Object book) {
		super();
		this.pen = pen;
		this.book = book;
	}

	@Override
	public void run() {
		synchronized (pen) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized (book) {
				System.out.println("Thread 2 working");
			}
		}
	}
}

class CustomObj {
	Object book;
	Object pen;

	public CustomObj(Object book, Object pen) {
		super();
		this.book = book;
		this.pen = pen;
	}
}