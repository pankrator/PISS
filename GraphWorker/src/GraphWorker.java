import java.io.DataOutputStream;
import java.net.ConnectException;

import com.connection.description.Worker;


public class GraphWorker extends Worker {

	public GraphWorker() throws ConnectException {
		super(3, 5000);
		descriptor = new GraphDescriptor();
		descriptor.addTask("Iterate over graph");
	}
	
	@Override
	protected void onTaskReceived(int index, String input) {
		// TODO: From index you can determine which is the incoming task and do it.
		// Then in sendResponse method send a result
	}

	@Override
	protected void onMessageReceived(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void sendResponse(DataOutputStream oStream) {
		// TODO Auto-generated method stub
		
	}

	
}
