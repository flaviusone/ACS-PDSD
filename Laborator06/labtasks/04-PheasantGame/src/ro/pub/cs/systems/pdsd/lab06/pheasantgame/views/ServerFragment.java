package ro.pub.cs.systems.pdsd.lab06.pheasantgame.views;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Random;

import ro.pub.cs.systems.pdsd.lab06.pheasantgame.R;
import ro.pub.cs.systems.pdsd.lab06.pheasantgame.general.Constants;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ServerFragment extends Fragment {
	
	private TextView serverHistoryTextView;
	private ServerThread serverThread;
	
	private class CommunicationThread implements Runnable {
		
		private Socket socket;
		private Random random = new Random();
		
		private String expectedWordPrefix = new String();
		
		public CommunicationThread(Socket socket) {
			if (socket != null) {
				this.socket = socket;
				Log.d(Constants.TAG, "[SERVER] Created communication thread with: "+socket.getInetAddress());
			}
		}
		
		public void run() {
			if (socket == null) {
				return;
			}
			boolean isRunning = true;
			
			InputStream requestStream = null;
			OutputStream responseStream = null;
			try {
				requestStream = socket.getInputStream();
			} catch (IOException ioException) {
				Log.e(Constants.TAG, "An exception has occurred: "+ioException.getMessage());
				if (Constants.DEBUG) {
					ioException.printStackTrace();
				}
			}
			
			try {
				responseStream = socket.getOutputStream();
			} catch (IOException ioException) {
				Log.e(Constants.TAG, "An exception has occurred: "+ioException.getMessage());
				if (Constants.DEBUG) {
					ioException.printStackTrace();
				}
			}
			
			BufferedReader requestReader = new BufferedReader(new InputStreamReader(requestStream));
			PrintStream responsePrintWriter = new PrintStream(responseStream);
			
			while (isRunning) {
				
				// TODO: exercise 7
				
			}

			try {
				socket.close();
			} catch (IOException ioException) {
				Log.e(Constants.TAG, "An exception has occurred: "+ioException.getMessage());
				if (Constants.DEBUG) {
					ioException.printStackTrace();
				}
			}			
		}
	}
	
	private class ServerThread implements Runnable {
 
		private ServerSocket serverSocket;
		
		private boolean isRunning;
 
		public ServerThread() {
			try {
				Log.d(Constants.TAG, "[SERVER] Created server thread, listening on port "+Constants.SERVER_PORT);
				serverSocket = new ServerSocket(Constants.SERVER_PORT);
				isRunning = true;
			} catch (IOException ioException) {
				Log.e(Constants.TAG, "An exception has occurred:" + ioException.getMessage());
				if (Constants.DEBUG) {
					ioException.printStackTrace();
				}
			}
		}
 
		@Override
		public void run() {
 
			while(isRunning) {
				
				Socket socket = null;
 
				try {
					socket = serverSocket.accept();
					Log.d(Constants.TAG, "[SERVER] Incomming communication "+socket.getInetAddress()+":"+socket.getLocalPort());
				} catch (SocketException socketException) {
					Log.e(Constants.TAG, "An exception has occurred: "+ socketException.getMessage());
					if (Constants.DEBUG) {
						socketException.printStackTrace();
					}
			    } catch (IOException ioException) {
					Log.e(Constants.TAG, "An exception has occurred:" + ioException.getMessage());
					if (Constants.DEBUG) {
						ioException.printStackTrace();
					}
				}
 
				new Thread(new CommunicationThread(socket)).start();
			}
 
		}
		
		public void stop() {
			try {
				isRunning = false;
				if (serverSocket != null) {
					serverSocket.close();
				}
			} catch (IOException ioException) {
				Log.e(Constants.TAG, "An exception has occurred: "+ioException.getMessage());
				if (Constants.DEBUG) {
					ioException.printStackTrace();
				}
			}
		}
	}	
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle state) {
		return inflater.inflate(R.layout.fragment_server, parent, false);
	}
	
	@Override
	public void onActivityCreated(Bundle state) {
		super.onActivityCreated(state);
		
		serverHistoryTextView = (TextView)getActivity().findViewById(R.id.server_history_text_view);
		
		serverThread = new ServerThread();
		new Thread(serverThread).start();
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		serverThread.stop();
	}

}
