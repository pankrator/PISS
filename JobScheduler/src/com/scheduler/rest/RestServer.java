package com.scheduler.rest;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import com.google.gson.Gson;

import fi.iki.elonen.NanoHTTPD;
import fi.iki.elonen.NanoHTTPD.Response.Status;


public class RestServer extends NanoHTTPD {

	private Map<String, EndPoint> getEndpoints;
	private Map<String, EndPoint> postEndpoints;
	private Gson gson;
	
	public RestServer() {
		super(8080);
		gson = new Gson();
		getEndpoints = new HashMap<>();
		postEndpoints = new HashMap<>();
		System.out.println("\nRunning! Point your browers to http://localhost:8080/ \n");
	}
	
	public void registerEndpointClass(String uri, EndPoint endpoint) {
		if (endpoint.getMethod() == Method.GET) {
			getEndpoints.put(uri, endpoint);
		} else if (endpoint.getMethod() == Method.POST) {
			postEndpoints.put(uri, endpoint);
		}
	}
	
	@Override
	public Response serve(IHTTPSession session) {
		System.out.println(session.getUri());
		EndPoint endpoint = null;
		if (session.getMethod() == Method.GET) {
			endpoint = getEndpoints.get(session.getUri());			
		} else if (session.getMethod() == Method.POST) {
			endpoint = postEndpoints.get(session.getUri());
		}
		
		if (endpoint != null) {
			return newFixedLengthResponse(endpoint.getStatus(), endpoint.getMimeType(), gson.toJson(endpoint.call(session)));			
		} else {
			return newFixedLengthResponse(Status.NOT_FOUND, "text/plain", "Not Found");
		}
	}
	
}
