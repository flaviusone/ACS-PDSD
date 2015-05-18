package com.example.colocviu2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import android.widget.TextView;

public class ClientThread extends Thread {

	public String address;
	public String port;
	public String city;
	public String spinner;
	public TextView forecast;
	
	public Socket socket;
	
	public ClientThread (String address, String port, String city, String spinner, TextView forecast) {
		this.address = address;
		this.port = port;
		this.city = city;
		this.spinner = spinner;
		this.forecast = forecast;
	}
	
	@Override
	public void run() {

		try {
			socket = new Socket(address, Integer.parseInt(port));
			
			BufferedReader bufferedReader = Utilities.getReader(socket);
			PrintWriter printWriter = Utilities.getWriter(socket);
			
			printWriter.println(city);
			printWriter.flush();
			printWriter.println(spinner);
			printWriter.flush();
			String weather;
			while((weather = bufferedReader.readLine()) != null) {
				final String finalWeather = weather;
				forecast.post(new Runnable(){
					@Override
					public void run() {
						forecast.append(finalWeather + "\n");
					}
				});
			}
			
			
			
			socket.close();
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
