import java.io.IOException;

import com.scheduler.server.SchedulerServer;

import fi.iki.elonen.util.ServerRunner;


public class Main {

	public static void main(String[] args) {
		SchedulerServer scheduler = new SchedulerServer(5000);
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
