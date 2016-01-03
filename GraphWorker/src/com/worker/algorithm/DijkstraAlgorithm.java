package com.worker.algorithm;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Stack;


public class DijkstraAlgorithm {
	
	private ArrayList<ArrayList<Pair<Integer, Double>>> edges;
	private boolean[] marked;
	private Double[] distances;
	private Integer[] parents;

//	private int nodeKey;
//	Pair<Integer, Double> pair = edges.get(nodeKey);
//	int distance = pair.second;
	
	public void initialize(ArrayList<ArrayList<Pair<Integer, Double>>> edges) {
		this.edges = edges;	
//		TODO +1 refactor
		marked = new boolean[edges.size()+1];
		distances = new Double[edges.size() +1];
		parents = new Integer[edges.size()+1];
	}
	
	public void findShortestPath(int beginKey) {
		
		PriorityQueue<Node> queue = new PriorityQueue<Node>();
		Node firstNode = new Node(beginKey, -1, 0);
		queue.add(firstNode);
		
		while(!queue.isEmpty()) {
			Node currentNode = queue.poll();
			int currentKey = currentNode.key;
			
			if(!marked[currentKey]) {
				marked[currentKey] = true;
				distances[currentKey] = currentNode.distance;
				parents[currentKey] = currentNode.parent;
				
				for (int i = 0; i < edges.get(currentKey).size(); i++) {
					Node neighbour = new Node();
					neighbour.key = edges.get(currentKey).get(i).first;
					neighbour.parent =  currentKey;
					neighbour.distance = currentNode.distance + edges.get(currentKey).get(i).second;
					queue.add(neighbour);
				}				
			}
		}				
	}
	
	public Stack<Integer> getPathTo(int fromKey, int toKey) {
		int currentKey = toKey;
		Stack<Integer> result = new Stack<Integer>();
		while(parents[currentKey] != fromKey) {
			System.out.println(currentKey);
			result.push(currentKey);
			currentKey = parents[currentKey];
		}
		result.push(fromKey);
		
		return result;
	}
}
