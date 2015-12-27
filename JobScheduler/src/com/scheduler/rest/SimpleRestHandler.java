package com.scheduler.rest;
import java.util.Map;

import com.scheduler.main.Main;

import fi.iki.elonen.NanoHTTPD.IHTTPSession;
import fi.iki.elonen.NanoHTTPD.Method;
import fi.iki.elonen.NanoHTTPD.Response.IStatus;
import fi.iki.elonen.NanoHTTPD.Response.Status;

public class SimpleRestHandler implements EndPoint {

	@Override
	public Object call(IHTTPSession session) {
		Map<String, String> params = session.getParms();
		int index = Integer.parseInt(params.get("index"));
		return Main.scheduler.doSyncTask(index, "test");
	}

	@Override
	public IStatus getStatus() {
		return Status.OK;
	}

	@Override
	public String getMimeType() {
		return "application/json";
	}
	
	@Override
	public Method getMethod() {
		return Method.GET;
	}
}
