import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;

import com.connection.description.Descriptor;
import com.connection.description.Worker;
import com.google.gson.Gson;
import com.worker.model.Numbers;


public class GraphWorker extends Worker {
	
	private String result;
	private boolean isResultReady;

	public GraphWorker() throws ConnectException {
		super(5000, 3);
		isResultReady = false;
		descriptor = new GraphDescriptor();
		descriptor.addTask("Iterate over graph");
		descriptor.addTask("Sum two numbers");
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
			
		case 2:
//			gson.fromJson(json, classOfT)
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
