import java.util.LinkedList;
import java.util.List;

import com.connection.description.Descriptor;

public class GraphDescriptor implements Descriptor {
	
	private List<String> tasks;
	
	public GraphDescriptor() {
		tasks = new LinkedList<>();
	}
	
	@Override
	public void addTask(String description) {
		tasks.add(description);
	}
	
	@Override
	public String getDescription(int index) {
		return tasks.get(index);
	}
	
	@Override
	public int getNumberOfTasks() {
		return tasks.size();
	}
}
