package com.worker.algorithm;
import java.util.ArrayList;
import java.util.Arrays;


public class Main {

	public static void main(String[] args) {
		
		ArrayList<ArrayList<Pair<Integer, Double>>> edges = new ArrayList<ArrayList<Pair<Integer, Double>>> ();
//		Pair<Integer, Double> fir = new Pair<Integer, Double>(2, 1d);
//		Pair<Integer, Double> sec = new Pair<Integer, Double>(2, 1d);
//		ArrayList<Pair<Integer, Double>> arr= new ArrayList<>(Arrays.asList(fir, sec));
//		edges.add(1, arr);
//		
//		System.out.println(arr);
//		System.out.println(edges);
		
		for (int i = 0; i < 5; i++) {
			edges.add(new ArrayList<Pair<Integer,Double>>());
		}
		
		edges.add(1, new ArrayList<Pair<Integer,Double>>(Arrays.asList(
				new Pair<Integer, Double>(2,1d),
				new Pair<Integer, Double>(3,2d)
				)));
		edges.add(2, new ArrayList<Pair<Integer,Double>>(Arrays.asList(
				new Pair<Integer, Double>(4,4d)				
				)));
		edges.add(3, new ArrayList<Pair<Integer,Double>>(Arrays.asList(
				new Pair<Integer, Double>(4,4d)				
				)));
		edges.add(4, new ArrayList<Pair<Integer,Double>>(Arrays.asList(
				new Pair<Integer, Double>(5,5d)				
				)));
		
//		System.out.println(edges);
		
		DijkstraAlgorithm dijkstra = new DijkstraAlgorithm();
		dijkstra.initialize(edges);
		dijkstra.findShortestPath(1);
//		dijkstra.getPathTo(1, 5);
		System.out.println(dijkstra.getPathTo(1, 5));

	}

}
