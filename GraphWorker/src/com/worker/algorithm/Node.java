package com.worker.algorithm;

public class Node implements Comparable<Node> {
	
	public String key;
	public String parent;
	public double distance;
	
	public Node() {
	}
	
	public Node(String key, String parent, int distance) {
		this.key = key;
		this.parent = parent;
		this.distance = distance;
	}

	@Override
	public int compareTo(Node o) {
		return (int)(this.distance - o.distance);
	}
}
