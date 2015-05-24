package ro.pub.cs.systems.pdsd.lab08.googlecloudmessaging.controller;

import ro.pub.cs.systems.pdsd.lab08.googlecloudmessaging.R;
import ro.pub.cs.systems.pdsd.lab08.googlecloudmessaging.general.Constants;
import ro.pub.cs.systems.pdsd.lab08.googlecloudmessaging.view.NotificationHandlerActivity;
import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

import com.google.android.gms.gcm.GoogleCloudMessaging;

public class GoogleCloudMessagingIntentService extends IntentService {

	public GoogleCloudMessagingIntentService() {
		super(Constants.TAG);
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		// the intent is supplied via the BroadcastReceiver

		GoogleCloudMessaging googleCloudMessaging = GoogleCloudMessaging.getInstance(this);

		String messageType = googleCloudMessaging.getMessageType(intent);

		Bundle extras = intent.getExtras();
		if (!extras.isEmpty()) {
			// filter the message according to its type
			if (GoogleCloudMessaging.MESSAGE_TYPE_SEND_ERROR.equals(messageType)) {
				sendNotification(extras);
			} else if (GoogleCloudMessaging.MESSAGE_TYPE_DELETED.equals(messageType)) {
				sendNotification(extras);
			} else if (GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE.equals(messageType)) {
				sendNotification(extras);
			}
		}
		
		// release the wake lock provided by the WakefulBroadcastReceiver.
		GoogleCloudMessagingBroadcastReceiver.completeWakefulIntent(intent);
	}

	// create a notification containing the message and post it
	private void sendNotification(Bundle bundle) {
		Intent notificationHandlerIntent = new Intent(this, NotificationHandlerActivity.class);
		notificationHandlerIntent.putExtras(bundle);
		
		TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(this);
		taskStackBuilder.addParentStack(NotificationHandlerActivity.class);
		taskStackBuilder.addNextIntent(notificationHandlerIntent);

		PendingIntent notificationHandlerPendingIntent = taskStackBuilder.getPendingIntent(
				0,
				PendingIntent.FLAG_UPDATE_CURRENT);

		NotificationCompat.Builder builder =
				new NotificationCompat.Builder(this)
					.setSmallIcon(R.drawable.ic_launcher)
					.setContentTitle(Constants.TAG)
					.setStyle(new NotificationCompat.BigTextStyle().bigText(Constants.NOTIFICATION_MESSAGE))
					.setContentText(Constants.NOTIFICATION_MESSAGE);

		builder.setContentIntent(notificationHandlerPendingIntent);
		
		NotificationManager notificationManager = (NotificationManager)this.getSystemService(Context.NOTIFICATION_SERVICE);
		notificationManager.notify(Constants.NOTIFICATION_ID, builder.build());
	}
}

