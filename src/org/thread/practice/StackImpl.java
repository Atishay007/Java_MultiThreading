package org.thread.practice;

import java.util.EmptyStackException;

public class StackImpl {
	public static void main(String[] args) {
		Stack<Integer> stk = new Stack<>();
		stk.push(10);
		stk.push(2);
		stk.push(7);
		stk.push(8);

		System.out.println(stk.pop());
		System.out.println(stk.pop());
		System.out.println(stk.pop());
		System.out.println(stk.pop());
		System.out.println(stk.pop());
	}
}

class Stack<T> {
	private Node<T> top;

	private static class Node<T> {
		private Node<T> nextNode;
		private T data;

		Node(T data, Node<T> node) {
			this.nextNode = node;
			this.data = data;
		}
	}

	public void push(T data) {
		Node<T> tempNode = new Node<>(data, null);
		if (top == null) {
			top = tempNode;
		} else {
			tempNode.nextNode = top;
			top = tempNode;
		}
	}

	public T pop() {
		if (top == null) {
			throw new EmptyStackException();
		} else {
			T tempValue = top.data;
			top = top.nextNode;
			return tempValue;
		}
	}
}
