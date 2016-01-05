package com.worker.formater;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import com.worker.algorithm.Pair;
import com.worker.model.Edge;
import com.worker.model.GraphModel;

public class GraphDataConvertor {
	
	public static HashMap<String, ArrayList<Pair<String, Double>>> fromModelToVertexList(GraphModel model) {
		HashMap<String, ArrayList<Pair<String, Double>>> result = new HashMap<String, ArrayList<Pair<String, Double>>>();
		
		for (Entry<String, Edge[]> entry : model.vertices.entrySet()) {
			ArrayList<Pair<String, Double>> edges = new ArrayList<Pair<String,Double>>();
			for (Edge edge : entry.getValue()) {
				edges.add(new Pair<String, Double>(edge.neighbourKey, edge.distance));				
			}
			result.put(entry.getKey(), edges);
		}
		
		return result;
	}
}
