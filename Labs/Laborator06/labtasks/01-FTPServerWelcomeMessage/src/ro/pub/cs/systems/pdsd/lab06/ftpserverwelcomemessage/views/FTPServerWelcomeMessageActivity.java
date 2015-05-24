package ro.pub.cs.systems.pdsd.lab06.ftpserverwelcomemessage.views;

import java.io.BufferedReader;
import java.net.Socket;

import ro.pub.cs.systems.pdsd.lab06.ftpserverwelcomemessage.R;
import ro.pub.cs.systems.pdsd.lab06.ftpserverwelcomemessage.general.Constants;
import ro.pub.cs.systems.pdsd.lab06.ftpserverwelcomemessage.general.Utilities;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FTPServerWelcomeMessageActivity extends Activity {
	
	private EditText FTPServerAddressEditText;
	private TextView welcomeMessageTextView;
	
	protected class FTPServerCommunicationThread extends Thread {
		
		@Override
		public void run() {
			try {
				
				// TODO: exercise 4
				// open socket with FTPServerAddress (taken from FTPServerAddressEditText edit text) and port (Constants.FTP_PORT = 21)
				Socket socket = new Socket (FTPServerAddressEditText.getText().toString(),Constants.FTP_PORT);
				Log.v(Constants.TAG, "Connected to: "+socket.getInetAddress()+":"+socket.getLocalPort());
				// get the BufferedReader attached to the socket (call to the Utilities.getReader() method)
				BufferedReader bufferedReader = Utilities.getReader(socket);
				String line = bufferedReader.readLine();
				Log.v(Constants.TAG, "A line has been read from FTP server: "+line);
				// should the line start with Constants.FTP_MULTILINE_START_CODE, the welcome message is processed
				if (line.startsWith(Constants.FTP_MULTILINE_START_CODE)) {
				// read lines from server while 
					while ((line = bufferedReader.readLine()) != null) {
						// - the value is different from Constants.FTP_MULTILINE_END_CODE1
						// - the value does not start with Constants.FTP_MULTILINE_START_CODE2
						if (!line.equals(Constants.FTP_MULTILINE_END_CODE1) &&
								!line.startsWith(Constants.FTP_MULTILINE_END_CODE2)) {
							Log.v(Constants.TAG, "A line has been read from FTP server: "+line);
							final String finalizedLine = line;
							// append the line to the welcomeMessageTextView text view content (on the UI thread!!!)
							welcomeMessageTextView.post(new Runnable() {
								@Override
								public void run() {
									welcomeMessageTextView.append(finalizedLine + "\n");
								}
							});
						} else {
							break;
						}
					}
				}
				// close the socket
				socket.close();
			} catch (Exception exception) {
				Log.e(Constants.TAG, "An exception has occurred: "+exception.getMessage());
				if (Constants.DEBUG) {
					exception.printStackTrace();
				}
			}
		}
	}	
	
	private ButtonClickListener buttonClickListener = new ButtonClickListener();
	private class ButtonClickListener implements Button.OnClickListener {
		
		@Override
		public void onClick(View view) {
			new FTPServerCommunicationThread().start();
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ftpserver_welcome_message);
		
		FTPServerAddressEditText = (EditText)findViewById(R.id.ftp_server_address_edit_text);
		welcomeMessageTextView = (TextView)findViewById(R.id.welcome_message_text_view);
		
		Button displayWelcomeMessageButton = (Button)findViewById(R.id.display_welcome_message_button);
		displayWelcomeMessageButton.setOnClickListener(buttonClickListener);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ftpserver_welcome_message, menu);
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
