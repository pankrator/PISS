package com.connection.description;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Connection {
	
	private Socket socket;
	private String schedulerIP;
	private int port;
	private DataOutputStream outputStream;
	private DataInputStream inputStream;
	
	public Connection(String ip, int port) {
		this.schedulerIP = ip;
		this.port = port;
	}
	
	public boolean initializeConnection() throws UnknownHostException, IOException {
		this.socket = new Socket(this.schedulerIP, this.port);
		this.inputStream = new DataInputStream(socket.getInputStream());
		this.outputStream = new DataOutputStream(socket.getOutputStream());
		
		socket.setSoTimeout(5000);
		String message = this.inputStream.readUTF();
		if (message.equals("CONNECTED")) {
			socket.setSoTimeout(0);
			return true;
		}
		
		return false;
	}
	
	public DataOutputStream getOutputStream() {
		return this.outputStream;
	}
	
	public DataInputStream getInputStream() {
		return this.inputStream;
	}
}
