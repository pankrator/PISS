package com.worker.algorithm;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class Main {

	public static void main(String[] args) {
		
		HashMap<String, ArrayList<Pair<String, Double>>> edges = new HashMap<String, ArrayList<Pair<String, Double>>> ();
		
		edges.put("1", new ArrayList<Pair<String,Double>>(Arrays.asList(
				new Pair<String, Double>("2",1d),
				new Pair<String, Double>("3",2d)
				)));
		
		edges.put("2", new ArrayList<Pair<String,Double>>(Arrays.asList(
				new Pair<String, Double>("4", 4d)				
				)));
		edges.put("3", new ArrayList<Pair<String,Double>>(Arrays.asList(
				new Pair<String, Double>("4", 4d)				
				)));
		edges.put("4", new ArrayList<Pair<String,Double>>(Arrays.asList(
				new Pair<String, Double>("5", 5d)				
				)));
		
//		System.out.println(edges);
		
		DijkstraAlgorithm dijkstra = new DijkstraAlgorithm();
		dijkstra.initialize(edges);
		dijkstra.findShortestPath("1");
//		dijkstra.getPathTo(1, 5);
		System.out.println(dijkstra.getPathTo("1", "5"));

	}

}
