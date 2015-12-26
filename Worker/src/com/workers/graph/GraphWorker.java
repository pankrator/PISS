package com.workers.graph;

import java.io.DataOutputStream;
import java.net.ConnectException;

import com.connection.description.Worker;

public class GraphWorker extends Worker {
	
	public GraphWorker() throws ConnectException {
		super(3);
		descriptor = new GraphDescriptor();
		descriptor.addTask("Iterate over graph");
	}
	
	@Override
	protected void onTaskReceived(int index, String input) {
		
	}

	@Override
	protected void onMessageReceived(String message) {
		
	}

	@Override
	protected void sendResponse(DataOutputStream oStream) {
		
	}
}
