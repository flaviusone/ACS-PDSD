 package ro.pub.cs.systems.pdsd.examples.multimediasamples;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.MediaController;
import android.widget.VideoView;

public class MultimediaSamplesActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_multimedia_samples);
		
		VideoView androidHistoryVideoView = (VideoView)findViewById(R.id.android_history_video_view);
		androidHistoryVideoView.setVideoURI(Uri.parse("http://pdsd2015.andreirosucojocaru.ro/TheShortHistoryOfAndroid480p.mp4"));
		MediaController mediaController = new MediaController(this);
		mediaController.setAnchorView(androidHistoryVideoView);
		androidHistoryVideoView.setMediaController(mediaController);
		androidHistoryVideoView.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.multimedia_samples, menu);
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
