package ro.pub.cs.systems.pdsd.lab05.sample03gridview;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract.Contacts;
import android.view.Menu;
import android.widget.GridView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends Activity implements LoaderManager.LoaderCallbacks<Cursor> {

	SimpleCursorAdapter adapter;
	GridView gridview;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		gridview = (GridView)findViewById(R.id.gridview);
		String[] columns = new String[] {Contacts.DISPLAY_NAME};
		int[] views = new int[] {android.R.id.text1}; 
		adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, null, columns, views, 0);
		gridview.setAdapter(adapter);
		getLoaderManager().initLoader(0, null, this);
	}
	
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		return new CursorLoader(this, Contacts.CONTENT_URI, null, null, null, Contacts.DISPLAY_NAME);
	}
	
	public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
		adapter.swapCursor(data);	
	}
	
	public void onLoaderReset(Loader<Cursor> loader) {
		adapter.swapCursor(null);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
