package ro.pub.cs.systems.pdsd.lab08.googlecloudmessaging.view;

import ro.pub.cs.systems.pdsd.lab08.googlecloudmessaging.R;
import ro.pub.cs.systems.pdsd.lab08.googlecloudmessaging.model.RegisteredDevice;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class RegisteredFragment extends Fragment {
	
	private Spinner registeredDevicesSpinner;
	private EditText messageEditText;
	
	private SendMessageButtonClickListener sendMessageButtonClickListener = new SendMessageButtonClickListener();
	private class SendMessageButtonClickListener implements Button.OnClickListener {
		
		@Override
		public void onClick(View view) {
			int position = registeredDevicesSpinner.getSelectedItemPosition();
			RegisteredDevice registeredDevice = (RegisteredDevice)registeredDevicesSpinner.getAdapter().getItem(position);
			
			if (registeredDevice == null || messageEditText.getText().toString().isEmpty()) {
				return;
			}
			
			new MessagePushExecutor(
					registeredDevice.getRegistrationId(),
					messageEditText.getText().toString()
					).start();
		}
		
	}
	
	private RefreshRegisteredDevicesListButtonClickListener refreshRegisteredDevicesListButtonClickListener = new RefreshRegisteredDevicesListButtonClickListener();
	private class RefreshRegisteredDevicesListButtonClickListener implements Button.OnClickListener {
		
		@Override
		public void onClick(View view) {
			new RegisteredDevicesFetcher().start();
		}
		
	}
	
	private class MessagePushExecutor extends Thread {
		
		private String registrationId;
		private String message;
		
		public MessagePushExecutor(String registrationId, String message) {
			this.registrationId = registrationId;
			this.message = message;
		}
		
		@Override
		public void run() {
			
			// TODO: exercise 8b
			// - create a HttpClient instance
			// - create a HttpPost instance according to the address of the message push service
			// - create a the list of request parameters
			//  * Constants.REGISTRATION_ID
			//  * Constants.MESSAGE
			// - encode the list of the request parameters (into an UrlEncodedFormEntity object), according to UTF-8
			// - attach the list of parameters to the HttpPost object
			// - create a ResponseHandler<String> instance
			// - execute the HttpPost request on the HttpClient and ResponseHandler and get response
			// - do nothing with the response
		}
		
	}
	
	private class RegisteredDevicesFetcher extends Thread {
		
		@Override
		public void run() {
			
			// TODO: exercise 8a
			// - create a HttpClient instance
			// - create a HttpGet instance according to the address of the registered devices service
			// - create a ResponseHandler<String> instance
			// - execute the HttpGet request on the HttpClient and ResponseHandler and get response
			// - instantiate the result (an ArrayList<RegisteredDevice> object)
			// - parse the result into a JSONArray object
			// - iterate over the records (instances of JSONObject) and create RegisteredDevice objects with the following information
			//  * Constants.ID
			//  * Constants.REGISTRATION_ID
			//  * Constants.USERNAME
			//  * Constants.EMAIL
			//  * Constants.TIMESTAMP
			// - add the registered device object to the result
			// - create an adapter (of type RegisteredDevicesAdapter) for the Spinner containing the registered devices
			// - attach the adapter to the Spinner
			
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_registered, parent, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		registeredDevicesSpinner = (Spinner)getActivity().findViewById(R.id.registered_devices_spinner);
		messageEditText = (EditText)getActivity().findViewById(R.id.message_edit_text);
		
		Button sendMessageButton = (Button)getActivity().findViewById(R.id.send_message_button);
		sendMessageButton.setOnClickListener(sendMessageButtonClickListener);
		
		Button refreshRegisteredDevicesListButton = (Button)getActivity().findViewById(R.id.refresh_registered_devices_list_button);
		refreshRegisteredDevicesListButton.setOnClickListener(refreshRegisteredDevicesListButtonClickListener);
		new RegisteredDevicesFetcher().start();
	}

}
