package org.thread.practice;

import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class FutureAndCallableImpl {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService exe = Executors.newFixedThreadPool(3);
		Future<Integer> future = exe.submit(new Worker());
		System.out.println(future.get());

		exe.shutdown();
		exe.awaitTermination(1000, TimeUnit.MILLISECONDS);
		exe.shutdownNow();
	}

}

class Worker implements Callable<Integer> {

	@Override
	public Integer call() throws Exception {
		int[] arr = { 1, 4, 8, 9, 1, 0, 4, 5, 4 };
		return Arrays.stream(arr).sum();
	}
}