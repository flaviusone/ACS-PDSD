package ro.pub.cs.systems.pdsd.lab07.landmarklister.views;

import ro.pub.cs.systems.pdsd.lab07.earthquakelister.R;
import ro.pub.cs.systems.pdsd.lab07.landmarklister.general.Constants;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class LandmarkListerActivity extends Activity {
	
	private EditText northEditText, southEditText, eastEditText, westEditText;
	
	private ListView landmarksListView;

	private ShowResultsButtonClickListener showResultsButtonClickListener = new ShowResultsButtonClickListener();
	private class ShowResultsButtonClickListener implements Button.OnClickListener {
		
		@Override
		public void onClick(View view) {
			String northString = northEditText.getText().toString();
			if (northString == null || northString.isEmpty()) {
				Toast.makeText(getApplication(), Constants.MISSING_INFORMATION_ERROR_MESSAGE, Toast.LENGTH_LONG).show();
				return;
			}
			String southString = southEditText.getText().toString();
			if (southString == null || southString.isEmpty()) {
				Toast.makeText(getApplication(), Constants.MISSING_INFORMATION_ERROR_MESSAGE, Toast.LENGTH_LONG).show();
				return;
			}
			String eastString = eastEditText.getText().toString();
			if (eastString == null || eastString.isEmpty()) {
				Toast.makeText(getApplication(), Constants.MISSING_INFORMATION_ERROR_MESSAGE, Toast.LENGTH_LONG).show();
				return;
			}
			String westString = westEditText.getText().toString();
			if (westString == null || westString.isEmpty()) {
				Toast.makeText(getApplication(), Constants.MISSING_INFORMATION_ERROR_MESSAGE, Toast.LENGTH_LONG).show();
				return;
			}
			
			new EarthquakeListerThread().start();
		}
	}
	
	private class EarthquakeListerThread extends Thread {
		
		@Override
		public void run() {
			
			// TODO: exercise 7
			// - create an instance of a HttpClient object
			// - create the URL to the web service, appending the bounding box coordinates and the username to the base Internet address
			// - create an instance of a HttGet object
			// - create an instance of a ReponseHandler object
			// - execute the request, thus obtaining the response
			// - get the JSON object representing the response
			// - get the JSON array (the value corresponding to the "geonames" attribute)
			// - iterate over the results list and create a LandmarkInformation for each element
			// - create a LandmarkInformationAdapter with the array and attach it to the landmarksListView

		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_landmark_lister);
		
		northEditText = (EditText)findViewById(R.id.north_edit_text);
		southEditText = (EditText)findViewById(R.id.south_edit_text);
		eastEditText = (EditText)findViewById(R.id.east_edit_text);
		westEditText = (EditText)findViewById(R.id.west_edit_text);
		
		landmarksListView = (ListView)findViewById(R.id.landmarks_list_view);
		
		Button showResultsButton = (Button)findViewById(R.id.show_results_button);
		showResultsButton.setOnClickListener(showResultsButtonClickListener);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.landmark_lister, menu);
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
