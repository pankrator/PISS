import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;

import com.connection.description.Descriptor;
import com.connection.description.Worker;


public class GraphWorker extends Worker {

	public GraphWorker() throws ConnectException {
		super(5000, 3);
		descriptor = new GraphDescriptor();
		descriptor.addTask("Iterate over graph");
	}
	
	public GraphWorker(Descriptor descriptor) throws ConnectException {
		super(5000, 3);
		this.descriptor = descriptor;
	}
	
	@Override
	protected void onTaskReceived(int index, String input) {
		// TODO: From index you can determine which is the incoming task and do it.
		// Then in sendResponse method send a result
		System.out.println(index);
		System.out.println(input);
	}

	@Override
	protected void onMessageReceived(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void sendResponse(DataOutputStream oStream) {
		try {
			oStream.writeUTF("RESULT");
			oStream.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}
