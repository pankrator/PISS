package com.scheduler.rest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.scheduler.main.Main;

import fi.iki.elonen.NanoHTTPD.IHTTPSession;
import fi.iki.elonen.NanoHTTPD.Method;
import fi.iki.elonen.NanoHTTPD.Response.IStatus;
import fi.iki.elonen.NanoHTTPD.Response.Status;
import fi.iki.elonen.NanoHTTPD.ResponseException;

public class SimpleRestHandler implements EndPoint {

	@Override
	public Object call(IHTTPSession session) {
		Map<String, String> params = session.getParms();
		int index = Integer.parseInt(params.get("index"));
		Map<String, String> body = new HashMap<>();
		try {
			session.parseBody(body);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ResponseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Main.scheduler.doTask(index, body.get("postData"));
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
		return Method.POST;
	}
}
