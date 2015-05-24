package ro.pub.cs.systems.pdsd.lab05.sample08simpleadapter;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ListView listview = (ListView)findViewById(R.id.listview);
		try {
			InputStream inputstream = getAssets().open("staff.xml");
			StaffXmlParser staffxmlparser = new StaffXmlParser();
			List<Map<String,?>> staff = staffxmlparser.parse(inputstream);
			SimpleAdapter adapter = new SimpleAdapter(
					this,
					staff,
					android.R.layout.simple_list_item_2,
					new String[] {"name", "position"},
					new int[] {android.R.id.text1, android.R.id.text2}
					);
			listview.setAdapter(adapter);
		} catch (Exception exception) {
			Log.println(Log.ERROR, "evenimente", exception.getMessage());
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
