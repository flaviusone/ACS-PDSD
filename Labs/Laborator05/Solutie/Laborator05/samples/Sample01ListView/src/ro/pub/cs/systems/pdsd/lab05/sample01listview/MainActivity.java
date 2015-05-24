package ro.pub.cs.systems.pdsd.lab05.sample01listview;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

	String[] cartoons = new String[] { 
			"Mickey Mouse", 
			"The Simpsons", 
			"Looney Toons", 
			"Road Runner", 
			"Tom & Jerry", 
			"Scooby Doo", 
			"Family Guy", 
			"The Flinstones", 
			"Dragon Ball Z", 
			"South Park" 
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ListView listview = (ListView)findViewById(R.id.cartoon_listview);
		final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, cartoons);
		listview.setAdapter(adapter);
		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Toast.makeText(MainActivity.this, "You have clicked on "+adapter.getItem(position)+" item", Toast.LENGTH_SHORT).show();
			}
		});
		listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				Toast.makeText(MainActivity.this, "You have long clicked on "+adapter.getItem(position)+" item", Toast.LENGTH_SHORT).show();
				return true;
			}
		});		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
