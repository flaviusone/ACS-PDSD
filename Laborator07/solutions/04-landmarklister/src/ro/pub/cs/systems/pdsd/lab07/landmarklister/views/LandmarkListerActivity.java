package ro.pub.cs.systems.pdsd.lab07.landmarklister.views;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ro.pub.cs.systems.pdsd.lab07.earthquakelister.R;
import ro.pub.cs.systems.pdsd.lab07.landmarklister.controller.LandmarkInformationAdapter;
import ro.pub.cs.systems.pdsd.lab07.landmarklister.general.Constants;
import ro.pub.cs.systems.pdsd.lab07.landmarklister.model.LandmarkInformation;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
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
			String northString = northEditText.getText().toString();
			String southString = southEditText.getText().toString();
			String eastString = eastEditText.getText().toString();
			String westString = westEditText.getText().toString();
			
			HttpClient httpClient = new DefaultHttpClient();
			String url = Constants.LANDMARK_LISTER_WEB_SERVICE_INTERNET_ADDRESS + 
					Constants.NORTH + northString +
					"&" + Constants.SOUTH + southString +
					"&" + Constants.EAST + eastString + 
					"&" + Constants.WEST + westString +
					"&" + Constants.CREDENTIALS;
			HttpGet httpGet = new HttpGet(url);
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			try {
				String content = httpClient.execute(httpGet, responseHandler);
				final ArrayList<LandmarkInformation> landMarkInformationList = new ArrayList<LandmarkInformation>();
				JSONObject result = new JSONObject(content);
				JSONArray jsonArray = result.getJSONArray(Constants.GEONAMES);
				for (int k = 0; k < jsonArray.length(); k++) {
					JSONObject jsonObject = jsonArray.getJSONObject(k);
					landMarkInformationList.add(new LandmarkInformation(
							jsonObject.getDouble(Constants.LATITUDE),
							jsonObject.getDouble(Constants.LONGITUDE),
							jsonObject.getString(Constants.TOPONYM_NAME),
							jsonObject.getLong(Constants.POPULATION),
							jsonObject.getString(Constants.FCODE_NAME),
							jsonObject.getString(Constants.NAME),
							jsonObject.getString(Constants.WIKIPEDIA_WEB_PAGE_ADDRESS),
							jsonObject.getString(Constants.COUNTRY_CODE)));
				}
				landmarksListView.post(new Runnable() {
					@Override
					public void run() {
						landmarksListView.setAdapter(new LandmarkInformationAdapter(
								getBaseContext(),
								landMarkInformationList));
					}
				});
			} catch (JSONException jsonException) {
				Log.e(Constants.TAG, jsonException.getMessage());
				if (Constants.DEBUG) {
					jsonException.printStackTrace();
				}
			} catch (ClientProtocolException clientProtocolException) {
				Log.e(Constants.TAG, clientProtocolException.getMessage());
				if (Constants.DEBUG) {
					clientProtocolException.printStackTrace();
				}
			} catch (IOException ioException) {
				Log.e(Constants.TAG, ioException.getMessage());
				if (Constants.DEBUG) {
					ioException.printStackTrace();
				}
			}
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
