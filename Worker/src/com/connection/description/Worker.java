package com.connection.description;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.UnknownHostException;

public abstract class Worker {
	
	private static final int SCHEDULER_PORT = 5000;
	
	protected Connection connection;
	protected Descriptor descriptor;
	
	public Worker(int retryConnectionNumber) throws ConnectException {
		int count = 0;
		
		connection = new Connection("localhost", SCHEDULER_PORT);
		try {
			while (count < retryConnectionNumber && connection.initializeConnection() != true) {
				count++;
			}
			if (count > retryConnectionNumber) {
				throw new ConnectException("No connection could be established in reasonable time!");
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void start() throws IOException {
		DataOutputStream oStream = connection.getOutputStream();
		StringBuilder tasksResponse = new StringBuilder();
		for (int i = 0; i < descriptor.getNumberOfTasks(); i++) {
			tasksResponse.append(i + ":");
			tasksResponse.append(descriptor.getDescription(i));
		}
		oStream.writeUTF(tasksResponse.toString());
	}
	
	public void update() throws IOException {
		DataInputStream iStream = connection.getInputStream();
		String receivedMessage = iStream.readUTF();
		onMessageReceived(receivedMessage);
		if (receivedMessage.startsWith("TASK")) {
			int taskNumber = Integer.parseInt(receivedMessage.substring(4, 5));
			String taskInput = receivedMessage.substring(6);
			onTaskReceived(taskNumber, taskInput);
		}
		
		sendResponse(connection.getOutputStream());
	}
	
	protected abstract void onTaskReceived(int index, String input);
	protected abstract void onMessageReceived(String message);
	protected abstract void sendResponse(DataOutputStream oStream);
}
