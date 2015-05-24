package ro.pub.cs.systems.pdsd.lab05.sample06simplecursoradapter;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CalendarContract.Calendars;
import android.view.Menu;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Cursor cursor = getContentResolver().query(
				Calendars.CONTENT_URI, 
				new String[]{Calendars._ID, Calendars.CALENDAR_DISPLAY_NAME, Calendars.CALENDAR_TIME_ZONE}, 
				null, 
				null, 
				null);
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(
				this, 
				android.R.layout.two_line_list_item, 
				cursor, 
				new String[]{Calendars.CALENDAR_DISPLAY_NAME, Calendars.CALENDAR_TIME_ZONE}, 
				new int[] {android.R.id.text1, android.R.id.text2}, 
				0);
		ListView listview = (ListView)findViewById(R.id.listview);
		listview.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
