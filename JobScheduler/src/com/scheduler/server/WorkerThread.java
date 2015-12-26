package com.scheduler.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class WorkerThread extends Thread {
	
	private Socket socket;
	private DataOutputStream outputStream;
	private DataInputStream inputStream;
	
	private Queue<Task> tasks;
	
	public WorkerThread(Socket socket) throws IOException {
		super();
		tasks = new LinkedList<>();
		this.socket = socket;
		this.outputStream = new DataOutputStream(socket.getOutputStream());
		this.inputStream = new DataInputStream(socket.getInputStream());
	}
	
	public void registerTask(Task task) {
		this.tasks.add(task);
	}
	
	@Override
	public void run() {
		try {
			outputStream.writeUTF("CONNECTED");
			String tasksDescription = this.inputStream.readUTF();
			System.out.println(tasksDescription);
			
			while (true) {
				Task nextTask = tasks.poll();
				if (nextTask != null) {
					StringBuilder output = new StringBuilder("TASK");
					output.append(nextTask.index + ":");
					output.append(nextTask.input);
					outputStream.writeUTF(output.toString());
				}
			}
		} catch (IOException e) {
		}
		
	}
}
