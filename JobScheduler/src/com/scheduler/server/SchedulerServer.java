package com.scheduler.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
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
		System.out.println("Trying to receive result with key: " + key);
		if (workers.size() == 0) {
			return null;
		}
		int workerIndex = Integer.parseInt(key.substring(0, 1));
		// TODO: Include in result key the workerIndex!
		return workers.get(workerIndex).getResult(key);
	}

	public String doTask(int index, String input) {
		System.out.println("Scheduler received task with index " + index);
		WorkerThread worker = null;
		try {
			worker = getWorker();
		} catch (IllegalStateException e) {
			return "No workers found!";
		}
		return worker.registerTask(new Task(index, input));
	}

	public String doSyncTask(int index, String input) {
		System.out.println("Scheduler received task with index " + index);
		WorkerThread worker = getWorker();
		return worker.completeTaskSync(new Task(index, input));
	}
	
	private WorkerThread getWorker() {
		if (workers.size() < 1) {
			throw new IllegalStateException("There are no running workers now!");
		}
		
		return workers.stream().min((WorkerThread a, WorkerThread b) -> {
			return a.getNumberOfTasks() - b.getNumberOfTasks();
		}).get();
	}

	private class SchedulerThread implements Runnable {
		@Override
		public void run() {
			try {
				server = new ServerSocket(port);
				while (true) {
					Socket workerSocket = server.accept();
					System.out.println("New worker connected!");
					WorkerThread thread = new WorkerThread(workerSocket, workers.size());
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

	public void removeWorker(int workerIndex) {
		this.workers.remove(workerIndex);
	}
}
