package com.scheduler.rest;

import java.util.Map;

import com.scheduler.main.Main;

import fi.iki.elonen.NanoHTTPD.IHTTPSession;
import fi.iki.elonen.NanoHTTPD.Method;
import fi.iki.elonen.NanoHTTPD.Response.IStatus;
import fi.iki.elonen.NanoHTTPD.Response.Status;

public class ResultRestHandler implements EndPoint {

	@Override
	public Object call(IHTTPSession session) {
		Map<String, String> params = session.getParms();
		String key = params.get("key");
		return Main.scheduler.getResult(key);
	}
	
	@Override
	public IStatus getStatus() {
		return Status.OK;
	}

	@Override
	public Method getMethod() {
		return Method.GET;
	}

	@Override
	public String getMimeType() {
		return "application/json";
	}
}
