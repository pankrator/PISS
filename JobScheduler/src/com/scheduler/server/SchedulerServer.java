package com.scheduler.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class SchedulerServer {

	private ServerSocket server;
	private int port;
	private List<WorkerThread> workers;

	public SchedulerServer(int port) {
		this.port = port;
		workers = new LinkedList<>();
	}

	public void start() throws IOException {
		Thread thread = new Thread(new SchedulerThread());
		thread.start();
	}
	
	public void doTask(int index, String input) {
		// TODO: Choose which worker to take the next task
	}

	private class SchedulerThread implements Runnable {
		@Override
		public void run() {
			try {
				server = new ServerSocket(port);
				while (true) {
					Socket workerSocket = server.accept();
					System.out.println("New worker connected!");
					WorkerThread thread = new WorkerThread(workerSocket);
					thread.start();
					workers.add(thread);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
