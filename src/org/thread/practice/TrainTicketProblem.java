package org.thread.practice;

import java.util.HashMap;
import java.util.Map;

public class TrainTicketProblem {
	public static void main(String[] args) throws InterruptedException {
		TicketReservationSystem ticketSystem = new TicketReservationSystem();
		ThreadWorker1 t1 = new ThreadWorker1("a", 2, ticketSystem);
		ThreadWorker1 t2 = new ThreadWorker1("b", 4, ticketSystem);
		ThreadWorker1 t3 = new ThreadWorker1("a", 7, ticketSystem);
		ThreadWorker1 t4 = new ThreadWorker1("b", 20, ticketSystem);

		t1.start();
		t2.start();
		t3.start();
		t4.start();

		t1.join();
		t2.join();
		t3.join();
		t4.join();

		System.out.println("Main Method Ended");
	}
}

class ThreadWorker1 extends Thread {
	private TicketReservationSystem ticketReserve;
	private String trainName;
	private int ticketCount;

	public ThreadWorker1(String trainName, int ticketCount, TicketReservationSystem ticketReserve) {
		super();
		this.ticketReserve = ticketReserve;
		this.trainName = trainName;
		this.ticketCount = ticketCount;
	}

	@Override
	public void run() {
		ticketReserve.reserveTicket(trainName, ticketCount);
	}
}

class TicketReservationSystem {
	private static Map<String, Integer> trainInfo = new HashMap<>();
	static {
		trainInfo.put("a", 100);
		trainInfo.put("b", 100);
	}

	public void reserveTicket(String trainName, int ticketCount) {
		// As the list is static, so lock should be on Class level.
		synchronized (TicketReservationSystem.class) {
			trainInfo.put(trainName, trainInfo.get(trainName) - ticketCount);
			System.out.println("Seats Left " + trainInfo.get(trainName) + " " + " for Train " + trainName + " ");
		}
	}
}
