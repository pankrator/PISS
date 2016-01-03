package com.worker.algorithm;

public class Node implements Comparable<Node> {
	
	public int key;
	public int parent;
	public double distance;
	
	public Node() {
	}
	
	public Node(int key, int parent, int distance) {
		this.key = key;
		this.parent = parent;
		this.distance = distance;
	}

	@Override
	public int compareTo(Node o) {
		return (int)(this.distance - o.distance);
	}
}
