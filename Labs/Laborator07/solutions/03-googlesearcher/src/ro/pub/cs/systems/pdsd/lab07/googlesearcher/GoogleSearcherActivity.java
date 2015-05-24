package ro.pub.cs.systems.pdsd.lab07.googlesearcher;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import ro.pub.cs.systems.pdsd.lab07.googlesearcher.general.Constants;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class GoogleSearcherActivity extends Activity {
	
	private EditText keywordEditText;
	private WebView googleResultsWebView;
	
	private class GoogleSearcherThread extends Thread {
		
		private String keyword;
		
		public GoogleSearcherThread(String keyword) {
			this.keyword = keyword;
		}
		
		@Override
		public void run() {
			HttpClient httpClient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(Constants.GOOGLE_INTERNET_ADDRESS + keyword);
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			try {
				final String content = httpClient.execute(httpGet, responseHandler);
				googleResultsWebView.post(new Runnable() {
					@Override
					public void run() {
						googleResultsWebView.loadDataWithBaseURL(Constants.GOOGLE_INTERNET_ADDRESS, content, Constants.MIME_TYPE, Constants.CHARACTER_ENCODING, null);
					}
				});
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
	
	private SearchGoogleButtonClickListener searchGoogleButtonClickListener = new SearchGoogleButtonClickListener();
	private class SearchGoogleButtonClickListener implements Button.OnClickListener {
		
		@Override
		public void onClick(View view) {
			String keyword = keywordEditText.getText().toString();
			if (keyword == null || keyword.isEmpty()) {
				Toast.makeText(getApplication(), Constants.EMPTY_KEYWORD_ERROR_MESSAGE, Toast.LENGTH_LONG).show();
			} else {
				String[] keywords = keyword.split(" ");
				keyword = Constants.SEARCH_PREFIX + keywords[0];
				for (int k = 1; k < keywords.length; k++) {
					keyword += "+" + keywords[k];
				}
				new GoogleSearcherThread(keyword).start();
			}
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_google_searcher);
		
		keywordEditText = (EditText)findViewById(R.id.keyword_edit_text);
		googleResultsWebView = (WebView)findViewById(R.id.google_results_web_view);
		
		Button searchGoogleButton = (Button)findViewById(R.id.search_google_button);
		searchGoogleButton.setOnClickListener(searchGoogleButtonClickListener);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.google_searcher, menu);
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
