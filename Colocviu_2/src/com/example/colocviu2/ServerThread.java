package com.example.colocviu2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import android.util.Log;

public class ServerThread  extends Thread {

	private int port;
	public HashMap<String, WeatherForecastInformation> data = null;
	private ServerSocket serverSocket = null;
	
	
	public ServerThread (int port) {
		this.port = port;
		try {
			this.serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.data = new HashMap<String, WeatherForecastInformation>();
	}
	
	@Override
	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			Socket socket = null;
			try {
				socket = serverSocket.accept();
				Log.i("MUF", "[SERVER] A connection request was received from " + socket.getInetAddress() + ":" + socket.getLocalPort());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			CommunicationThread communicationThread = new CommunicationThread(this, socket);
			communicationThread.start();
		}	
	
	}
	
	
}
