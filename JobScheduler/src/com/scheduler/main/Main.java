package com.scheduler.main;
import java.io.IOException;

import com.scheduler.rest.ReceiveNeighbours;
import com.scheduler.rest.RestServer;
import com.scheduler.rest.SimpleRestHandler;
import com.scheduler.server.SchedulerServer;

import fi.iki.elonen.util.ServerRunner;


public class Main {

	public static void main(String[] args) {
		int port = 5000;
		if (args.length > 1) {
			port = Integer.parseInt(args[1]);
		}
		
		SchedulerServer scheduler = new SchedulerServer(port);
		try {
			scheduler.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		RestServer rest = new RestServer();
		rest.registerEndpointClass("/asd", new SimpleRestHandler());
		rest.registerEndpointClass("/asd", new ReceiveNeighbours());
		
		
		ServerRunner.executeInstance(rest);
	}
}
