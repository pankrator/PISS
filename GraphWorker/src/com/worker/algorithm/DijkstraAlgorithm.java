package com.worker.algorithm;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;


public class DijkstraAlgorithm {
	
	private HashMap<String, ArrayList<Pair<String, Double>>> edges;
	private Map<String, Boolean> marked;
	private Map<String, Double> distances;
	private Map<String, String> parents;

//	private int nodeKey;
//	Pair<Integer, Double> pair = edges.get(nodeKey);
//	int distance = pair.second;
	
	public void initialize(HashMap<String, ArrayList<Pair<String, Double>>> edges) {
		this.edges = edges;	
		marked = new HashMap<String, Boolean>();
		distances = new HashMap<String, Double>();
		parents = new HashMap<String, String>();
	}
	
	public void findShortestPath(String beginKey) {
		
		PriorityQueue<Node> queue = new PriorityQueue<Node>();
		Node firstNode = new Node(beginKey, null, 0);
		queue.add(firstNode);
		
		while(!queue.isEmpty()) {
			Node currentNode = queue.poll();
			String currentKey = currentNode.key;
			
			if(marked.get(currentKey) == null) {
				marked.put(currentKey, true);
				distances.put(currentKey, currentNode.distance);
				parents.put(currentKey, currentNode.parent);
				
				List<Pair<String, Double>> edgeList = edges.get(currentKey);
				if (edgeList == null) {
					continue;
				}
				for (int i = 0; i < edgeList.size(); i++) {
					Node neighbour = new Node();
					neighbour.key = edgeList.get(i).first;
					neighbour.parent =  currentKey;
					neighbour.distance = currentNode.distance + edgeList.get(i).second;
					queue.add(neighbour);
				}				
			}
		}				
	}
	
	public Stack<String> getPathTo(String fromKey, String toKey) {
		String currentKey = toKey;
		Stack<String> result = new Stack<>();
		while(parents.get(currentKey) != fromKey) {
			System.out.println(currentKey);
			result.push(currentKey);
			currentKey = parents.get(currentKey);
		}
		result.push(fromKey);
		
		return result;
	}
}
