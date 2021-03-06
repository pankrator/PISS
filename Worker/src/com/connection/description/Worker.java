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
	
	public Worker(int port, String host, int retryConnectionNumber) throws ConnectException {
		int count = 0;
		if (port <= 0) {
			port = SCHEDULER_PORT;
		}
		connection = new Connection(host, port);
		try {
			while (count < retryConnectionNumber && connection.initializeConnection() != true) {
				count++;
			}
			if (count > retryConnectionNumber) {
				throw new ConnectException("No connection could be established in reasonable time!");
			}
			
			System.out.println("Successfully connected to Scheduler server");
			
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
			tasksResponse.append("\n");
		}
		oStream.writeUTF(tasksResponse.toString());
	}
	
	public void update() throws IOException {
		DataInputStream iStream = connection.getInputStream();
		String receivedMessage = iStream.readUTF();
		System.out.println("received message: " + receivedMessage);
		onMessageReceived(receivedMessage);
		if (receivedMessage.startsWith("TASK")) {
			// TODO parse resultKey and better parsing of taskNumber
			String resultKey = receivedMessage.substring(4, 9); // Result key will be with fixed length
			System.out.println("Doing task with key " + resultKey);
			int taskNumber = Integer.parseInt(receivedMessage.substring(10, 11));
			String taskInput = receivedMessage.substring(12);
			onTaskReceived(taskNumber, taskInput);
			connection.getOutputStream().writeUTF("RESULT" + resultKey);
		}
		
		sendResponse(connection.getOutputStream());
	}
	
	/**
	 * This method will be invoked when a new Task is received and needs to be done.
	 * 
	 * @param index is the identifier of the task
	 * @param input is the data that is needed for the task to be done. You can think of it as arguments to a function
	 */
	protected abstract void onTaskReceived(int index, String input);
	
	/**
	 * This method is called on every new message that is received by the worker
	 * @param message the received information
	 */
	protected abstract void onMessageReceived(String message);
	
	/**
	 * This method must send a response with either a result from a task or some kind of information
	 * It is called on every message that the worker receives.
	 * @see Worker#update()
	 * @param oStream
	 */
	protected abstract void sendResponse(DataOutputStream oStream);
}
