package org.thread.practice;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class PriorityQueueImpl {
	public static void main(String[] args) {
		BlockingQueue<Integer> queue = new PriorityBlockingQueue<>();

		queue.add(2);
		queue.add(45);
		queue.add(2);
		queue.add(74);

		// When we poll, it will give the element basis of sorting.
		// We have to use comparable elements or with comparator.
		System.out.println(queue.poll());
		System.out.println(queue.poll());
		System.out.println(queue.poll());
		System.out.println(queue.poll());
		System.out.println(queue.poll());
	}
}
