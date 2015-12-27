import java.io.IOException;
import java.net.ConnectException;


public class Main {

	public static void main(String[] args) {
		GraphWorker worker;
		try {
			worker = new GraphWorker();
			worker.start();
			while (true) {
				worker.update();
			}
		} catch (ConnectException e) {
			
		} catch (IOException e) {
			
		}
	}

}
