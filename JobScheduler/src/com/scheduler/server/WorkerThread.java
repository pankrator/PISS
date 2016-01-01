package com.scheduler.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Random;

public class WorkerThread extends Thread {
	
	private Socket socket;
	private Map<String, String> results;
	private DataOutputStream outputStream;
	private DataInputStream inputStream;
	
	private Queue<Task> tasks;
	
	public WorkerThread(Socket socket) throws IOException {
		super();
		tasks = new LinkedList<>();
		results = new HashMap<>();
		this.socket = socket;
		this.outputStream = new DataOutputStream(socket.getOutputStream());
		this.inputStream = new DataInputStream(socket.getInputStream());
	}
	
	public String getResult(String key) {
		return this.results.get(key);
	}
	
	public String registerTask(Task task) {
		String resultKey = generateKey();
		task.resultKey = resultKey;
		this.tasks.add(task);
		
		return resultKey;
	}
	
	public String completeTaskSync(Task task) {
		StringBuilder output = new StringBuilder("TASK");
		output.append(task.index + ":");
		output.append(task.input);
		try {
			outputStream.writeUTF(output.toString());
			String response = inputStream.readUTF();
			// TODO: Complete the logic for sync tasks
			return response;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public void run() {
		try {
			System.out.println();
			outputStream.writeUTF("CONNECTED");
			String tasksDescription = this.inputStream.readUTF();
			System.out.println(tasksDescription);
			
			while (true) {
				Task nextTask = tasks.poll();
				if (nextTask != null) {
					StringBuilder output = new StringBuilder("TASK");
					output.append(nextTask.resultKey + ":");
					output.append(nextTask.index + ":");
					output.append(nextTask.input);
					System.out.println("Sendin task with index " + nextTask.index);
					outputStream.writeUTF(output.toString());
					
					String response = inputStream.readUTF();
					if (response.startsWith("RESULT")) {
						String resultKey = response.substring(6);
						String actualResult = inputStream.readUTF();
						System.out.println("result " + actualResult);
						results.put(resultKey, actualResult);
					}
				}
				Thread.sleep(10);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String generateKey() {
		// TODO: Generate a more appropriate key
		Random rand = new Random();
		StringBuilder result = new StringBuilder();
		result.append(rand.nextInt(10));
		result.append(rand.nextInt(10));
		result.append(rand.nextInt(10));
		result.append(rand.nextInt(10));
		
		return result.toString();
	}
}
