package ro.pub.cs.systems.pdsd.lab08.googlecloudmessaging.general;

import ro.pub.cs.systems.pdsd.lab08.googlecloudmessaging.view.GoogleCloudMessagingActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;

public class Utilities {

	/**
	 * @return Application's version code from the {@code PackageManager}.
	 */
	public static int getApplicationVersion(Context context) {
		try {
			PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
			return packageInfo.versionCode;
		} catch (NameNotFoundException nameNotFoundException) {
			Log.e(Constants.TAG, "An exception has occurred: "+nameNotFoundException.getMessage());
			if (Constants.DEBUG) {
				nameNotFoundException.printStackTrace();
			}
		}
		return -1;
	}	
	
	/**
	 * @return Application's {@code SharedPreferences}.
	 */
	public static SharedPreferences getSharedPreferences(Context context) {
		return context.getSharedPreferences(
				GoogleCloudMessagingActivity.class.getSimpleName(), 
				Context.MODE_PRIVATE
				);
	}
	
	/**
	 * Gets the registration status if it was previously set into the SharedPreferences.
	 * 
	 * @return applicationServerRegistrationStatus 
	 *  0 - registered
	 * -1 - unregistered or application version changed
	 */
	public static int getRegistrationStatusFromSharedPreferences(Context context) {
		final SharedPreferences sharedPreferences = getSharedPreferences(context);
		int registrationStatus = sharedPreferences.getInt(Constants.REGISTRATION_STATUS_PROPERTY, Integer.MIN_VALUE);
		if (registrationStatus == Integer.MIN_VALUE) {
			Log.i(Constants.TAG, Constants.REGISTRATION_STATUS_ERROR_MESSAGE);
			return Constants.FAILURE;
		}
		// check whether the application was updated 
		// if it is the case, the application server registration status must be cleared
		// as the mobile device needs to submit the new registration ID
		int registeredApplicationVersion = sharedPreferences.getInt(Constants.APPLICATION_VERSION_PROPERTY, Integer.MIN_VALUE);
		int currentVersion = getApplicationVersion(context);
		if (registeredApplicationVersion != currentVersion) {
			Log.i(Constants.TAG, Constants.APPLICATION_VERSION_ERROR_MESSAGE);
			return Constants.FAILURE;
		}
		return registrationStatus;
	}	
	
	/**
	 * Gets the registration ID previously provided to the application by the Google Cloud Messaging Server
	 * and set into the SharedPreferences.
	 * 
	 * @return registrationId, or empty string if no registrationId was previously supplied
	 */
	public static String getRegistrationIdFromSharedPreferences(Context context) {
		final SharedPreferences sharedPreferences = getSharedPreferences(context);
		String registrationId = sharedPreferences.getString(Constants.REGISTRATION_ID_PROPERTY, "");
		if (registrationId.isEmpty()) {
			Log.i(Constants.TAG, Constants.REGISTRATION_ID_ERROR_MESSAGE);
			return Constants.EMPTY_STRING;
		}
		// check whether the application was updated 
		// if it is the case, the registration ID previously supplied must be cleared
		// as it is not guaranteed to work with the current version
		int registeredApplicationVersion = sharedPreferences.getInt(Constants.APPLICATION_VERSION_PROPERTY, Integer.MIN_VALUE);
		int currentVersion = getApplicationVersion(context);
		if (registeredApplicationVersion != currentVersion) {
			Log.i(Constants.TAG, Constants.APPLICATION_VERSION_ERROR_MESSAGE);
			return Constants.EMPTY_STRING;
		}
		return registrationId;
	}	

	/**
	 * Sets the registration ID and registration status into the application's {@code SharedPreferences}.
	 *
	 * @param registrationId     - registration ID supplied by the Google Cloud Messaging Server
	 * @param registrationStatus - status of registering the ID to the application server
	 */
	public static void setInformationIntoSharedPreferences(Context context, String registrationId, int registrationStatus) {
		final SharedPreferences sharedPreferences = getSharedPreferences(context);
		int currentVersion = getApplicationVersion(context);
		Log.i(Constants.TAG, Constants.SHARED_PREFERENCES_INFORMATION_MESSAGE + currentVersion);
		SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();
		sharedPreferencesEditor.putString(Constants.REGISTRATION_ID_PROPERTY, registrationId);
		sharedPreferencesEditor.putInt(Constants.REGISTRATION_STATUS_PROPERTY, registrationStatus);
		sharedPreferencesEditor.putInt(Constants.APPLICATION_VERSION_PROPERTY, currentVersion);
		sharedPreferencesEditor.commit();
	}	

}
