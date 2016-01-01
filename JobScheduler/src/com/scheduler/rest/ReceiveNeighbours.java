package com.scheduler.rest;
import fi.iki.elonen.NanoHTTPD.IHTTPSession;
import fi.iki.elonen.NanoHTTPD.Method;
import fi.iki.elonen.NanoHTTPD.Response.IStatus;
import fi.iki.elonen.NanoHTTPD.Response.Status;


public class ReceiveNeighbours implements EndPoint {

	@Override
	public IStatus getStatus() {
		return Status.OK;
	}

	@Override
	public Method getMethod() {
		return Method.POST;
	}

	@Override
	public String getMimeType() {
		return "text/plain";
	}

	@Override
	public Object call(IHTTPSession session) {
		System.out.println(session.getParms());
		
		return "";
	}
	
	
}
