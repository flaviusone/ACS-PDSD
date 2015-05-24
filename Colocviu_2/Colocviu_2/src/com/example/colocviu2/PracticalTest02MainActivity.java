package com.example.colocviu2;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


public class PracticalTest02MainActivity extends Activity {
	// Server widgets
	private EditText     serverPortEditText       = null;
	private Button       connectButton            = null;
	
	// Client widgets
	private EditText     clientAddressEditText    = null;
	private EditText     clientPortEditText       = null;
	private EditText     cityEditText             = null;
	private Spinner      informationTypeSpinner   = null;
	private Button       getWeatherForecastButton = null;
	private TextView     weatherForecastTextView  = null;
	
	private ServerThread serverThread             = null;
	private ClientThread clientThread             = null;
	
	private ConnectButtonClickListener connectButtonClickListener = new ConnectButtonClickListener();
	private class ConnectButtonClickListener implements Button.OnClickListener {
		
		@Override
		public void onClick(View view) {
			serverThread = new ServerThread(Integer.parseInt(serverPortEditText.getText().toString()));
			
			if (!serverThread.isAlive()) {
				serverThread.start();
			}
		}
	}
	
	private GetWeatherForecastButtonClickListener getWeatherForecastButtonClickListener = new GetWeatherForecastButtonClickListener();
	private class GetWeatherForecastButtonClickListener implements Button.OnClickListener {
		
		@Override
		public void onClick(View view) {
			String address = clientAddressEditText.getText().toString();
			String port = clientPortEditText.getText().toString();
			String city = cityEditText.getText().toString();
			String spinner = informationTypeSpinner.getSelectedItem().toString();
			
			clientThread = new ClientThread(address, port, city, spinner, weatherForecastTextView);
			
			if (!clientThread.isAlive()) {
				clientThread.start();
			}
		}
	}
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test02_main);
        
        serverPortEditText = (EditText)findViewById(R.id.server_port);
        connectButton = (Button)findViewById(R.id.Start_server);
        clientAddressEditText = (EditText)findViewById(R.id.clientAddr);
        clientPortEditText = (EditText)findViewById(R.id.clientPort);
        cityEditText = (EditText)findViewById(R.id.clientCity);
        informationTypeSpinner = (Spinner)findViewById(R.id.spinner);
        getWeatherForecastButton = (Button)findViewById(R.id.clientGetButton);
        weatherForecastTextView = (TextView)findViewById(R.id.weather_forecast_text_view);
        
        connectButton.setOnClickListener(connectButtonClickListener);
        getWeatherForecastButton.setOnClickListener(getWeatherForecastButtonClickListener);
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.practical_test02_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
