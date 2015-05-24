package ro.pub.cs.systems.pdsd.lab08.googlecloudmessaging.view;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ro.pub.cs.systems.pdsd.lab08.googlecloudmessaging.R;
import ro.pub.cs.systems.pdsd.lab08.googlecloudmessaging.controller.RegisteredDevicesAdapter;
import ro.pub.cs.systems.pdsd.lab08.googlecloudmessaging.general.Constants;
import ro.pub.cs.systems.pdsd.lab08.googlecloudmessaging.model.RegisteredDevice;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
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
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(Constants.MESSAGE_PUSH_SERVICE_ADDRESS);
			
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair(Constants.REGISTRATION_ID, registrationId));
			params.add(new BasicNameValuePair(Constants.MESSAGE, message));
			UrlEncodedFormEntity urlEncodedFormEntity = null;
			try {
				urlEncodedFormEntity = new UrlEncodedFormEntity(params, HTTP.UTF_8);
			} catch (UnsupportedEncodingException unsupportedEncodingException) {
				Log.e(Constants.TAG, "An exception has occurred: "+unsupportedEncodingException.getMessage());
				if (Constants.DEBUG) {
					unsupportedEncodingException.printStackTrace();
				}
			}
			httpPost.setEntity(urlEncodedFormEntity);
			
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			try {
				String result = httpClient.execute(httpPost, responseHandler);
				Log.i(Constants.TAG, result);
			} catch (IOException ioException) {
				Log.e(Constants.TAG, "An exception has occurred: "+ioException.getMessage());
				if (Constants.DEBUG) {
					ioException.printStackTrace();
				}
			}
		}
		
	}
	
	private class RegisteredDevicesFetcher extends Thread {
		
		@Override
		public void run() {
			HttpClient httpClient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(Constants.REGISTERED_DEVICES_SERVICE_ADDRESS);
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			try {
				String response = httpClient.execute(httpGet, responseHandler);
				final ArrayList<RegisteredDevice> registeredDevices = new ArrayList<RegisteredDevice>();
				JSONArray jsonArray = new JSONArray(response);
				for (int k = 0; k < jsonArray.length(); k++) {
					JSONObject jsonObject = jsonArray.getJSONObject(k);
					registeredDevices.add(new RegisteredDevice(
							jsonObject.getInt(Constants.ID),
							jsonObject.getString(Constants.REGISTRATION_ID),
							jsonObject.getString(Constants.USERNAME),
							jsonObject.getString(Constants.EMAIL),
							jsonObject.getString(Constants.TIMESTAMP)
							));
				}
				registeredDevicesSpinner.post(new Runnable() {
					@Override
					public void run() {
						RegisteredDevicesAdapter registeredDevicesAdapter = new RegisteredDevicesAdapter(getActivity(), registeredDevices);
						registeredDevicesSpinner.setAdapter(registeredDevicesAdapter);
					}
				});
			} catch (IOException ioException) {
				Log.e(Constants.TAG, "An exception has occurred: "+ioException.getMessage());
				if (Constants.DEBUG) {
					ioException.printStackTrace();
				}
			} catch (JSONException jsonException) {
				Log.e(Constants.TAG, "An exception has occurred: "+jsonException.getMessage());
				if (Constants.DEBUG) {
					jsonException.printStackTrace();
				}
			}
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
