package ro.pub.cs.systems.pdsd.lab07.webpagekeywordsearch.graphicaluserinterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import ro.pub.cs.systems.pdsd.lab07.webpagekeywordsearch.R;
import ro.pub.cs.systems.pdsd.lab07.webpagekeywordsearch.general.Constants;
import ro.pub.cs.systems.pdsd.lab07.webpagekeywordsearch.general.Utilities;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class WebPageKeywordSearchActivity extends Activity {
	
	private EditText webPageAddressEditText, keywordEditText;
	private TextView resultsTextView;
	
	private class WebPageKeywordSearchThread extends Thread {
		
		@Override
		public void run() {
			HttpURLConnection httpURLConnection = null;
			String errorMessage = null;
			try {
				String webPageAddress = webPageAddressEditText.getText().toString();
				String keyword = keywordEditText.getText().toString();
				if (webPageAddress == null || webPageAddress.isEmpty()) {
					errorMessage = "Web Page address cannot be empty";
				}
				if (keyword == null || keyword.isEmpty()) {
					errorMessage = "Keyword cannot be empty";
				}
				if (errorMessage != null) {
					final String finalizedErrorMessage = errorMessage;
					resultsTextView.post(new Runnable() {
						@Override
						public void run() {
							resultsTextView.setText(finalizedErrorMessage);
						}
					});
					return;
				}
				URL url = new URL(webPageAddress);
				StringBuffer resultsTextViewContent = new StringBuffer();
				resultsTextViewContent.append("Protocol: " + url.getProtocol() + "\n");
				resultsTextViewContent.append("Host: " + url.getHost() + "\n");
				resultsTextViewContent.append("Port: " + url.getPort() + "\n");
				resultsTextViewContent.append("File: " + url.getFile() + "\n");
				resultsTextViewContent.append("Reference: " + url.getRef() + "\n");
				resultsTextViewContent.append("==========\n");
				URLConnection urlConnection = url.openConnection();
				if (urlConnection instanceof HttpURLConnection) {
					httpURLConnection = (HttpURLConnection)urlConnection;
					BufferedReader bufferedReader = Utilities.getReader(httpURLConnection);
					int currentLineNumber = 0, numberOfOccurrencies = 0;
					String currentLineContent;
					while ((currentLineContent = bufferedReader.readLine()) != null) {
						currentLineNumber++;
						if (currentLineContent.contains(keyword)) {
							resultsTextViewContent.append("line: "+currentLineNumber+" / "+currentLineContent+"\n");
							numberOfOccurrencies++;
						}
					}
					resultsTextViewContent.append("Number of occurrencies: "+numberOfOccurrencies+"\n");
					final String finalizedResultsTextViewContent = resultsTextViewContent.toString();
					resultsTextView.post(new Runnable() {
						@Override
						public void run() {
							resultsTextView.setText(finalizedResultsTextViewContent);
						}
					});
				}
			} catch (MalformedURLException malformedURLException) {
		        Log.e(Constants.TAG, malformedURLException.getMessage());
		        if (Constants.DEBUG) {
		        	malformedURLException.printStackTrace();
		        }
			} catch (IOException ioException) {
		        Log.e(Constants.TAG, ioException.getMessage());
		        if (Constants.DEBUG) {
		        	ioException.printStackTrace();
		        }
		    } finally {
		    	if (httpURLConnection != null) { 
		    		httpURLConnection.disconnect();
		    	}
		    }					
		}
	}
	
	private DisplayResultsButtonClickListener displayResultsButtonClickListener = new DisplayResultsButtonClickListener();
	private class DisplayResultsButtonClickListener implements Button.OnClickListener {
		
		@Override
		public void onClick(View view) {
			new WebPageKeywordSearchThread().start();
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_web_page_keyword_search);
		
		webPageAddressEditText = (EditText)findViewById(R.id.web_page_address_edit_text);
		keywordEditText = (EditText)findViewById(R.id.keyword_edit_text);
		resultsTextView = (TextView)findViewById(R.id.results_text_view);
		
		Button displayResultsButton = (Button)findViewById(R.id.display_results_button);
		displayResultsButton.setOnClickListener(displayResultsButtonClickListener);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.web_page_keyword_search, menu);
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
