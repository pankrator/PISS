package com.worker.model;
import java.util.HashMap;


public class GraphModel {
	public String start;
	public String target;
	public HashMap<String, Edge[]> vertices;
	
	@Override
	public String toString() {
		return vertices.toString();
	}
}
