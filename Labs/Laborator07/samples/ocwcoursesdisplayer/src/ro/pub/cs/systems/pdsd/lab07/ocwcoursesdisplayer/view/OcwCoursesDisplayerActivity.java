package ro.pub.cs.systems.pdsd.lab07.ocwcoursesdisplayer.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import ro.pub.cs.systems.pdsd.lab07.ocwcoursesdisplayer.R;
import ro.pub.cs.systems.pdsd.lab07.ocwcoursesdisplayer.controller.OcwCourseInformationAdapter;
import ro.pub.cs.systems.pdsd.lab07.ocwcoursesdisplayer.general.Constants;
import ro.pub.cs.systems.pdsd.lab07.ocwcoursesdisplayer.general.Utilities;
import ro.pub.cs.systems.pdsd.lab07.ocwcoursesdisplayer.model.OcwCourseInformation;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class OcwCoursesDisplayerActivity extends Activity {
	
	private ListView ocwCoursesListView;
	
	private class OcwCoursesDisplayerThread extends Thread {
		
		@Override
		public void run() {
			ArrayList<OcwCourseInformation> ocwCourseInformationList = new ArrayList<OcwCourseInformation>();
			
			HttpClient httpClient = new DefaultHttpClient();
			HttpGet httpWebPageGet = new HttpGet(Constants.OCW_BASE_INTERNET_ADDRESS + Constants.OCW_REFERRENCE_INTERNET_ADDRESS);
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			try {
				String pageSourceCode = httpClient.execute(httpWebPageGet, responseHandler);
				Document document = Jsoup.parse(pageSourceCode);
				Element htmlTag = document.child(0);
				Elements imgTagsClassMediaright = htmlTag.getElementsByAttributeValue(Constants.CLASS_ATTRIBUTE, Constants.MEDIARIGHT_VALUE);
				Iterator<Element> logoIterator = imgTagsClassMediaright.iterator();
				while (logoIterator.hasNext()) {
					Element imgTagClassMediaRight = logoIterator.next();
					String logoAddress = Constants.OCW_BASE_INTERNET_ADDRESS + imgTagClassMediaRight.attr(Constants.SRC_ATTRIBUTE);
					HttpGet httpLogoGet = new HttpGet(logoAddress);
					HttpResponse httpResponse = httpClient.execute(httpLogoGet);
					HttpEntity httpEntity = httpResponse.getEntity();
					
					OcwCourseInformation ocwCourseInformation = new OcwCourseInformation();
					Bitmap logo = Bitmap.createScaledBitmap(BitmapFactory.decodeStream(httpEntity.getContent()),
							Constants.LOGO_WIDTH,
							Constants.LOGO_HEIGHT,
							false); 
					ocwCourseInformation.setLogo(Utilities.makeTransparent(logo));
					ocwCourseInformationList.add(ocwCourseInformation);
				}
				Elements strongTags = htmlTag.getElementsByTag(Constants.STRONG_TAG);
				Iterator<Element> nameIterator = strongTags.iterator();
				int currentPosition = 0;
				while (nameIterator.hasNext()) {
					Element strongTag = nameIterator.next();
					String name = strongTag.ownText();

					ocwCourseInformationList.get(currentPosition++).setName(name);
				}
				final OcwCourseInformationAdapter ocwCourseInformationAdapter = new OcwCourseInformationAdapter(OcwCoursesDisplayerActivity.this, ocwCourseInformationList);
				ocwCoursesListView.post(new Runnable() {
					@Override
					public void run() {
						ocwCoursesListView.setAdapter(ocwCourseInformationAdapter);
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ocw_courses_displayer);
		
		ocwCoursesListView = (ListView)findViewById(R.id.ocw_courses_list_view);
		new OcwCoursesDisplayerThread().start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ocw_courses_displayer, menu);
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
