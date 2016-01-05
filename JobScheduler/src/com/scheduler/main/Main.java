package com.scheduler.main;
import java.io.IOException;

import com.scheduler.rest.RestServer;
import com.scheduler.rest.ResultRestHandler;
import com.scheduler.rest.SimpleRestHandler;
import com.scheduler.server.SchedulerServer;

import fi.iki.elonen.util.ServerRunner;


public class Main {
	
	public static SchedulerServer scheduler;

	public static void main(String[] args) {
		int port = 5000;
		if (args.length > 0) {
			port = Integer.parseInt(args[0]);
		}
		
		scheduler = new SchedulerServer(port);
		try {
			scheduler.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		RestServer rest = new RestServer();
		rest.registerEndpointClass("/task", new SimpleRestHandler());
		rest.registerEndpointClass("/receive", new ResultRestHandler());
//		rest.registerEndpointClass("/asd", new ReceiveNeighbours());
		
		
		ServerRunner.executeInstance(rest);
	}
}
