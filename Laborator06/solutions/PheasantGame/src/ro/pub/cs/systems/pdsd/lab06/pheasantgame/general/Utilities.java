package ro.pub.cs.systems.pdsd.lab06.pheasantgame.general;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
 
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
 
import android.os.AsyncTask;
import android.util.Log;
 
public class Utilities {
 
	private static class WordFinderService extends AsyncTask<String, Object, List<String>> {
		@Override
		protected List<String> doInBackground(String... params) {
			HttpClient httpClient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(Constants.WORD_FINDER_SERVICE_INTERNET_ADDREESS + params[0] + ".html");
	   	 	ResponseHandler<String> responseHandler = new BasicResponseHandler();
	   	 	String content = null;
	   	 	try {  
	   	 		content = httpClient.execute(httpGet, responseHandler);
	   	 	} catch (ClientProtocolException clientProtocolException) {
	   	 		Log.d(Constants.TAG, "An exception has occurred: "+clientProtocolException.getMessage());
	   	 		if (Constants.DEBUG) {
	   	 			clientProtocolException.printStackTrace();
	   	 		}
			} catch (IOException ioException) {
				Log.d(Constants.TAG, "An exception has occurred: "+ioException.getMessage());
				if (Constants.DEBUG) {
					ioException.printStackTrace();
				}
			}
	   	 	Document htmlPage = Jsoup.parse(content);
	   	 	List<String> wordList = new LinkedList<String>();
	   	 	for (Element element: htmlPage.getElementsByClass(Constants.WORD_TAG)) {
	   	 		wordList.add(element.html());
	   	 	}
	   	 	Log.d(Constants.TAG, wordList.toString());
	        return wordList;
		}
	}
 
	public static List<String> getWordListStartingWith(String prefix) {
		AsyncTask<String, Object, List<String>> wordFinderService = new WordFinderService().execute(prefix);
		try {
			return wordFinderService.get();
		} catch (InterruptedException interruptedException) {
			Log.d(Constants.TAG, "An exception has occurred "+interruptedException.getMessage());
			if (Constants.DEBUG) {
				interruptedException.printStackTrace();
			}
		} catch (ExecutionException executionException) {
			Log.d(Constants.TAG, "An exception has occurred "+executionException.getMessage());
			if (Constants.DEBUG) {
				executionException.printStackTrace();
			}
		}
		return null;
	}
 
	public static boolean wordValidation(String word) {
		return (!getWordListStartingWith(word).isEmpty());
	}
 
}