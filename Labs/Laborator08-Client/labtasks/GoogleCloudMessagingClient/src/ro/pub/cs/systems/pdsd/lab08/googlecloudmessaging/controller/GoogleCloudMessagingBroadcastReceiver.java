package ro.pub.cs.systems.pdsd.lab08.googlecloudmessaging.controller;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;

public class GoogleCloudMessagingBroadcastReceiver extends WakefulBroadcastReceiver {
	
	@Override
	public void onReceive(Context context, Intent intent) {
		
		// create a component associated to the service that will handle the intent
		ComponentName component = new ComponentName(
				context.getPackageName(),
				GoogleCloudMessagingIntentService.class.getName()
				);
		
		// start the service
		// maintain the mobile device awake
		startWakefulService(context, (intent.setComponent(component)));
		setResultCode(Activity.RESULT_OK);
	}
	
}
