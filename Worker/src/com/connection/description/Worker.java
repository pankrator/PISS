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
	
	public Worker(int port, int retryConnectionNumber) throws ConnectException {
		int count = 0;
		if (port <= 0) {
			port = SCHEDULER_PORT;
		}
		connection = new Connection("localhost", port);
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
			// TODO parse resultKey and better parsing of taskNumber
			int taskNumber = Integer.parseInt(receivedMessage.substring(4, 5));
			String taskInput = receivedMessage.substring(6);
			onTaskReceived(taskNumber, taskInput);
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
