package ro.pub.cs.systems.pdsd.lab06.daytimeprotocol.views;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import ro.pub.cs.systems.pdsd.lab06.daytimeprotocol.R;
import ro.pub.cs.systems.pdsd.lab06.daytimeprotocol.general.Constants;
import ro.pub.cs.systems.pdsd.lab06.daytimeprotocol.general.Utilities;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DaytimeProtocolActivity extends Activity {
	
	Button getInformationButton;
	TextView daytimeProtocolTextView;
	
	protected class NISTCommunicationThread extends Thread {
		
		@Override
		public void run() {
			try {
				Socket socket = new Socket (
						Constants.NIST_SERVER_HOST,
						Constants.NIST_SERVER_PORT
						);
				BufferedReader bufferedReader = Utilities.getReader(socket);
				bufferedReader.readLine();
				final String daytimeProtocol = bufferedReader.readLine();
				Log.d(Constants.TAG, "The server returned *"+daytimeProtocol+"*");
				daytimeProtocolTextView.post(new Runnable() {
					@Override
					public void run() {
						daytimeProtocolTextView.setText(daytimeProtocol);
					}
				});
				socket.close();
			} catch (UnknownHostException unknownHostException) {
				Log.e(Constants.TAG, "An exception has occurred: "+unknownHostException.getMessage());
				if (Constants.DEBUG) {
					unknownHostException.printStackTrace();
				}
			} catch (IOException ioException) {
				Log.e(Constants.TAG, "An exception has occurred: "+ioException.getMessage());
				if (Constants.DEBUG) {
					ioException.printStackTrace();
				}
			}
		}
	}
	
	protected ButtonClickListener buttonClickListener = new ButtonClickListener();
	
	protected class ButtonClickListener implements Button.OnClickListener {
		
		@Override
		public void onClick(View view) {
			new NISTCommunicationThread().start();
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_daytime_protocol);
		
		daytimeProtocolTextView = (TextView)findViewById(R.id.daytime_protocol_text_view);
		
		getInformationButton = (Button)findViewById(R.id.get_information_button);
		getInformationButton.setOnClickListener(buttonClickListener);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.daytime_protocol, menu);
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
