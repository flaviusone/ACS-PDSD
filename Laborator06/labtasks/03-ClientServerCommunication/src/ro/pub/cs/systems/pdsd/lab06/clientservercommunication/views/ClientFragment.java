package ro.pub.cs.systems.pdsd.lab06.clientservercommunication.views;

import java.io.BufferedReader;
import java.net.Socket;

import ro.pub.cs.systems.pdsd.lab06.clientservercommunication.R;
import ro.pub.cs.systems.pdsd.lab06.clientservercommunication.general.Constants;
import ro.pub.cs.systems.pdsd.lab06.clientservercommunication.general.Utilities;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ClientFragment extends Fragment {
	
	private EditText serverAddressEditText, serverPortEditText;
	private TextView serverMessageTextView;
	
	private class ClientThread implements Runnable {
		
		private Socket socket = null;
		
		@Override
		public void run() {
			try {
				
				// TODO: exercise 6b
				// - reset the content of serverMessageTextView (on UI thread !!!)
				serverMessageTextView.post(new Runnable() {
					@Override
					public void run() {
						serverMessageTextView.setText("");
					}
				});
				// - get the connection parameters (serverAddress from serverAddressEditText, serverPort from serverPortEditText)
				String serverAddress = serverAddressEditText.getText().toString();
				int serverPort = Integer.parseInt(serverPortEditText.getText().toString());
				// - open a socket to the server
				socket = new Socket(serverAddress, serverPort);
				if (socket == null)
					return;
				Log.v(Constants.TAG, "Connection opened with "+socket.getInetAddress()+":"+socket.getLocalPort());
				// - get the BufferedReader object in order to read from the socket (use Utilities.getReader())
				BufferedReader bufferedReader = Utilities.getReader(socket);
				// - while the line that was read is not null (EOF was not sent), append the content to serverMessageTextView (on UI thread !!!)
				String currentLine;
				while ((currentLine = bufferedReader.readLine()) != null) {
					final String finalizedCurrentLine = currentLine;
					serverMessageTextView.post(new Runnable() {
						@Override
						public void run() {
							serverMessageTextView.append(finalizedCurrentLine);
						}
					});
				}
				// - close the socket to the server	
				socket.close();
				Log.v(Constants.TAG, "Connection closed");
			} catch (Exception exception) {
				Log.e(Constants.TAG, "An exception has occurred: "+exception.getMessage());
				if (Constants.DEBUG) {
					exception.printStackTrace();
				}
			}			
		}
	}	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle state) {
		return inflater.inflate(R.layout.fragment_client, parent, false);
	}
	
	@Override
	public void onActivityCreated(Bundle state) {
		super.onActivityCreated(state);
		
		serverAddressEditText = (EditText)getActivity().findViewById(R.id.server_address_edit_text);
		serverPortEditText = (EditText)getActivity().findViewById(R.id.server_port_edit_text);
		serverMessageTextView = (TextView)getActivity().findViewById(R.id.server_message_text_view);
		
		Button displayMessageButton = (Button)getActivity().findViewById(R.id.display_message_button);
		displayMessageButton.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View view) {
				new Thread(new ClientThread()).start();
			}
		});
		
	}

}
