package ro.pub.cs.systems.pdsd.lab07.googlesearcher;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

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
			
			// TODO: exercise 6b)
			// create an instance of a HttpClient object
			// create an instance of a HttpGet object, encapsulating the base Internet address (http://www.google.com) and the keyword
			// create an instance of a ResponseHandler object
			// execute the request, thus generating the result
			// display the result into the googleResultsWebView through loadDataWithBaseURL() method
			// - base Internet address is http://www.google.com
			// - page source code is the response
			// - mimetype is text/html
			// - encoding is UTF-8
			// - history is null

		}
	}
	
	private SearchGoogleButtonClickListener searchGoogleButtonClickListener = new SearchGoogleButtonClickListener();
	private class SearchGoogleButtonClickListener implements Button.OnClickListener {
		
		@Override
		public void onClick(View view) {
			
			// TODO: exercise 6a)
			// obtain the keyword from keywordEditText
			// signal an empty keyword through an error message displayed in a Toast window
			// split a multiple word (separated by space) keyword and link them through +
			// prepend the keyword with "search?q=" string
			// start the GoogleSearcherThread passing the keyword

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
