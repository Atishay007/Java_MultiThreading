package org.thread.practice;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapImpl {
	public static void main(String[] args) {
		Map<Integer, Integer> map = new ConcurrentHashMap<>();
		Thread t1 = new Thread(new Producer(map));
		Thread t2 = new Thread(new Consumer(map));

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

class Producer extends Thread {
	private Map<Integer, Integer> map;

	Producer(Map<Integer, Integer> map) {
		super("Producer");
		this.map = map;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			map.put(i, i);
		}
	}
}

class Consumer extends Thread {
	private Map<Integer, Integer> map;

	Consumer(Map<Integer, Integer> map) {
		super("Consumer");
		this.map = map;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(map.remove(i));
		}
	}
}