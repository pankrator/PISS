package com.worker.model;

public class Edge {
	public String neighbourKey;
	public double distance;
	
	@Override
	public String toString() {
		return "{ neighbourKey: " + neighbourKey + ", distance: " + distance + " }";
	}
}
