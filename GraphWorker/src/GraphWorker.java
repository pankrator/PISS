import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.util.Arrays;

import com.connection.description.Descriptor;
import com.connection.description.Worker;
import com.google.gson.Gson;
import com.worker.algorithm.DijkstraAlgorithm;
import com.worker.formater.GraphDataConvertor;
import com.worker.model.Edge;
import com.worker.model.GraphModel;
import com.worker.model.Numbers;


public class GraphWorker extends Worker {
	
	private String result;
	private boolean isResultReady;
	
	public GraphWorker(String host) throws ConnectException {
		super(5000, host, 3);
		isResultReady = false;
		descriptor = new GraphDescriptor();
		descriptor.addTask("Dijkstra over a graph");
		descriptor.addTask("Sum two numbers");
	}
	
	@Override
	protected void onTaskReceived(int index, String input) {
		// TODO: From index you can determine which is the incoming task and do it.
		// Then in sendResponse method send a result
		System.out.println(index);

		Gson gson = new Gson();
		
		switch (index) {
		case 1:
			Numbers numbers = gson.fromJson(input, Numbers.class);
			System.out.println("Summing two numbers:" + numbers.a + " and " + numbers.b);	
			int c = numbers.a + numbers.b;
			System.out.println(c);
			result = String.valueOf(c);
			isResultReady = true;
			break;
			
		case 0:
			GraphModel model = gson.fromJson(input, GraphModel.class);
			DijkstraAlgorithm dijkstra = new DijkstraAlgorithm();
			dijkstra.initialize(GraphDataConvertor.fromModelToVertexList(model));
			dijkstra.findShortestPath(model.start);
			Object[] path = dijkstra.getPathTo(model.start, model.target).toArray();
			result = gson.toJson(path);
			isResultReady = true;
			break;
		}
	}

	@Override
	protected void onMessageReceived(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void sendResponse(DataOutputStream oStream) {
		try {
			if (isResultReady) {
				oStream.writeUTF(result);
				isResultReady = false;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
