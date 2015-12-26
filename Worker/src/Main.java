import java.io.IOException;
import java.net.ConnectException;

import com.workers.graph.GraphWorker;


public class Main {

	public static void main(String[] args) {
		GraphWorker worker;
		try {
			worker = new GraphWorker();
			worker.start();
		} catch (ConnectException e) {
			
		} catch (IOException e) {
			
		}
	}

}
