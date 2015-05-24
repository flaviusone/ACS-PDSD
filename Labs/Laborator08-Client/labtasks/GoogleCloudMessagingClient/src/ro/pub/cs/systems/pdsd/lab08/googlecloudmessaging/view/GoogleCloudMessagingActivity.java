package ro.pub.cs.systems.pdsd.lab08.googlecloudmessaging.view;

import java.io.IOException;

import ro.pub.cs.systems.pdsd.lab08.googlecloudmessaging.general.Constants;
import ro.pub.cs.systems.pdsd.lab08.googlecloudmessaging.general.Utilities;
import ro.pub.cs.systems.pdsd.lab08.googlecloudmessaging.R;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;

public class GoogleCloudMessagingActivity extends Activity {
	
	private GoogleCloudMessaging 				googleCloudMessaging;
	
	private String 								registrationId;
	
	private UnregisteredFragment                unregisteredFragment = null;
	private RegisteredFragment                  registeredFragment = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_google_cloud_messaging_client);	
			
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

		if (Utilities.getRegistrationStatusFromSharedPreferences(this) == Constants.FAILURE) {
			unregisteredFragment = new UnregisteredFragment();
			fragmentTransaction.add(R.id.container, unregisteredFragment);
		} else {
			registeredFragment = new RegisteredFragment();
			fragmentTransaction.add(R.id.container, registeredFragment);
		}
			
		fragmentTransaction.commit();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		// check whether the device has Google Play Services APK has been installed meanwhile
		if (checkGooglePlayServices()) {
			// the check is successful
			googleCloudMessaging = GoogleCloudMessaging.getInstance(this);

			// get the Google Cloud Messaging registration Id, if it was provided previously
			// and stored into SharedPreferences
			registrationId = Utilities.getRegistrationIdFromSharedPreferences(this);

			if (registrationId.isEmpty()) {
				// the Google Cloud Messaging registration Id was not provided previously
				// connect to the Google Cloud Messaging server in background and register
				registerInBackground();   
			} else {
				if (unregisteredFragment != null) {
					unregisteredFragment.setRegistrationIdTextView(registrationId);
				}
			}

		} else {	
			// the check is not successful
			Log.i(Constants.TAG, Constants.GOOGLE_PLAY_SERVICES_ERROR_MESSAGE1);
		}
	}

	/**
	 * Check the mobile device to verify whether it has the Google Play Services APK installed. 
	 * If the application is not installed, display a dialog that allows users to download it
	 * from the Google Play Store or enable it in the device's system settings.
	 */
	private boolean checkGooglePlayServices() {
	    int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
	    if (resultCode != ConnectionResult.SUCCESS) {
	        if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
	            GooglePlayServicesUtil.getErrorDialog(resultCode, this, Constants.PLAY_SERVICES_RESOLUTION_REQUEST).show();
	        } else {
	            Log.i(Constants.TAG, Constants.GOOGLE_PLAY_SERVICES_ERROR_MESSAGE2);
	            finish();
	        }
	        return false;
	    }
	    return true;
	}
	
	/**
	 * Registers the application with Google Cloud Messaging server asynchronously
	 */
	private void registerInBackground() {
	    new AsyncTask<Object, Integer, String>() {
	        @Override
			protected String doInBackground(Object... params) {
			    try {
			        if (googleCloudMessaging == null) {
			        	googleCloudMessaging = GoogleCloudMessaging.getInstance(GoogleCloudMessagingActivity.this);
					}
					registrationId = googleCloudMessaging.register(Constants.PROJECT_NUMBER);
					// store the registration ID into the SharedPreferences
					// the ID is not yet registered with the application server
					Utilities.setInformationIntoSharedPreferences(GoogleCloudMessagingActivity.this, registrationId, Constants.FAILURE);
		        } catch (IOException ioException) {
			        registrationId = "An exception has occurred: " + ioException.getMessage();
			        if (Constants.DEBUG) {
			        	ioException.printStackTrace();
			        }
		        }
			    return registrationId;
			}
		 
			@Override
			protected void onPostExecute(String registrationId) {
				Log.i(Constants.TAG, Constants.REGISTRATION_ID_INFORMATION_MESSAGE + registrationId);
				if (unregisteredFragment != null) {
					unregisteredFragment.setRegistrationIdTextView(registrationId);
				}
				//registrationIdTextView.append(registrationId + "\n");
			}
	    }.execute(null, null, null);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.google_cloud_messaging_client, menu);
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
