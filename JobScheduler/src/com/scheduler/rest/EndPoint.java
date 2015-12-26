package com.scheduler.rest;
import fi.iki.elonen.NanoHTTPD.IHTTPSession;
import fi.iki.elonen.NanoHTTPD.Method;
import fi.iki.elonen.NanoHTTPD.Response.IStatus;

public interface EndPoint {
	
	public IStatus getStatus();
	public Method getMethod();
	public String getMimeType();
	public Object call(IHTTPSession session);
}
