package org.thread.practice;

import java.util.NoSuchElementException;

public class QueueImpl {
	public static void main(String[] args) {
		Queue<Integer> queue = new Queue<>();
		queue.add(10);
		queue.add(20);
		queue.add(1);
		queue.add(5);

		System.out.println(queue.poll());
		System.out.println(queue.poll());
		System.out.println(queue.poll());
		System.out.println(queue.poll());
	}
}

class Queue<T> {
	private Node<T> head;
	private Node<T> tail;

	private static class Node<T> {
		private T data;
		private Node<T> nextNode;

		Node(T data, Node<T> node) {
			this.data = data;
			this.nextNode = node;
		}
	}

	public void add(T data) {
		Node<T> tempNode = new Node<>(data, null);
		if (head == null) {
			head = tempNode;
			tail = tempNode;
		} else {
			tail.nextNode = tempNode;
			tail = tempNode;
		}
	}

	public T poll() {
		if (head == null) {
			throw new NoSuchElementException("Queue is Empty");
		} else {
			T tempValue = head.data;
			head = head.nextNode;
			if (head == null) {
				tail = null;
			}
			return tempValue;
		}
	}
}
