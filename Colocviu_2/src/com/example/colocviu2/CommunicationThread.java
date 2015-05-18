package com.example.colocviu2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.util.Log;

public class CommunicationThread extends Thread {

	ServerThread serverThread;
	Socket socket;
	
	public CommunicationThread(ServerThread serverThread, Socket socket) {
		this.serverThread = serverThread;
		this.socket = socket;
	}
	

	@Override
	public void run() {
		
		try {
			BufferedReader bufferedReader = Utilities.getReader(socket);
			PrintWriter printWriter = Utilities.getWriter(socket);
			
			String city = bufferedReader.readLine();
			String spinner = bufferedReader.readLine();
			WeatherForecastInformation weatherForecastInformation = null;
			Log.i("MUF", "City + Info in CommThread " + city + " " + spinner);
			if (serverThread.data.containsKey(city)) {
				weatherForecastInformation = serverThread.data.get(city);
			} else {
				HttpClient httpClient = new DefaultHttpClient();
				HttpPost httpPost = new HttpPost("http://www.wunderground.com/cgi-bin/findweather/getForecast");
				List<NameValuePair> params = new ArrayList<NameValuePair>();        
				params.add(new BasicNameValuePair("query", city));
				UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(params, HTTP.UTF_8);
				httpPost.setEntity(urlEncodedFormEntity);
				ResponseHandler<String> responseHandler = new BasicResponseHandler();
				String pageSourceCode = httpClient.execute(httpPost, responseHandler);
				
				Document document = Jsoup.parse(pageSourceCode);
				Element element = document.child(0);
				Elements scripts = element.getElementsByTag("script");
				
				for (Element script: scripts) {
					
					String scriptData = script.data();
					
					if (scriptData.contains("wui.api_data =\n")) {
						int position = scriptData.indexOf("wui.api_data =\n") +"wui.api_data =\n".length();
						scriptData = scriptData.substring(position);
						
						JSONObject content = new JSONObject(scriptData);
						
						JSONObject currentObservation = content.getJSONObject("current_observation");
						String temperature = currentObservation.getString("temperature");
						String windSpeed = currentObservation.getString("wind_speed");
						String condition = currentObservation.getString("condition");
						String pressure = currentObservation.getString("pressure");
						String humidity = currentObservation.getString("humidity");
						
						weatherForecastInformation = new WeatherForecastInformation(
								temperature,
								windSpeed,
								condition,
								pressure,
								humidity);

						serverThread.data.put(city, weatherForecastInformation);
						break;
					}
				}
			}
			
			if (weatherForecastInformation != null) {
				String result = null;
				if ("all".equals(spinner)) {
					result = weatherForecastInformation.toString();
				} else if ("temperature".equals(spinner)) {
					result = weatherForecastInformation.temperature;
				} else if ("wind_speed".equals(spinner)) {
					result = weatherForecastInformation.wind_speed;
				} else if ("condition".equals(spinner)) {
					result = weatherForecastInformation.condition;
				} else if ("humidity".equals(spinner)) {
					result = weatherForecastInformation.humidity;
				} else if ("pressure".equals(spinner)) {
					result = weatherForecastInformation.pressure;
				} else {
					result = "Wrong information type (all / temperature / wind_speed / condition / humidity / pressure)!";
				}
				printWriter.println(result);
				printWriter.flush();
			} else {
				Log.e("MUF", "[COMMUNICATION THREAD] Weather Forecast information is null!");
			}
			
			socket.close();			
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}
	
}
