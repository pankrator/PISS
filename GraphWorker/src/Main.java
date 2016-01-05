import java.io.IOException;
import java.net.ConnectException;


public class Main {

	public static void main(String[] args) {
		String host = "localhost";
		if (args.length > 0) {
			host = args[0];
		}
		GraphWorker worker;
		try {
			worker = new GraphWorker(host);
			worker.start();
			while (true) {
				worker.update();
			}
		} catch (ConnectException e) {
			
		} catch (IOException e) {
			
		}
	}

}
