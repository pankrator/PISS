package com.scheduler.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class SchedulerServer {

	private ServerSocket server;
	private int port;
	private List<WorkerThread> workers;
	private Queue<Task> pendingTasks;

	public SchedulerServer(int port) {
		this.port = port;
		workers = new LinkedList<>();
		pendingTasks = new LinkedList<>();
	}

	public void start() throws IOException {
		Thread thread = new Thread(new SchedulerThread());
		thread.start();
	}
	
	public String getResult(String key) {
		int dotIndex = key.indexOf(".");
		int workerIndex = Integer.parseInt(key.substring(0, dotIndex));
		return workers.get(workerIndex).getResult(key.substring(dotIndex));
	}

	public String doTask(int index, String input) {
		// TODO: Choose which worker to take the next task
		if (workers.size() < 1) {
			throw new IllegalStateException("There are no running workers now!");
		} else {
			WorkerThread worker = workers.get(0);
			return worker.registerTask(new Task(index, input));
		}
	}

	public String doSyncTask(int index, String input) {
		// TODO: Choose which worker to take the next task
		if (workers.size() < 1) {
			throw new IllegalStateException("There are no running workers now!");
		} else {
			WorkerThread worker = workers.get(0);
			return worker.completeTaskSync(new Task(index, input));
		}
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
					if (pendingTasks.size() > 0) {
						thread.registerTask(pendingTasks.poll());
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
