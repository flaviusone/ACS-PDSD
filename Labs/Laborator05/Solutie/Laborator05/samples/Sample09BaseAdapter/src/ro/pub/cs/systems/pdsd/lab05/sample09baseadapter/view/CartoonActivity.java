package ro.pub.cs.systems.pdsd.lab05.sample09baseadapter.view;

import java.io.InputStream;
import java.util.ArrayList;

import ro.pub.cs.systems.pdsd.lab05.sample09baseadapter.R;
import ro.pub.cs.systems.pdsd.lab05.sample09baseadapter.controller.CartoonAdapter;
import ro.pub.cs.systems.pdsd.lab05.sample09baseadapter.model.Cartoon;
import ro.pub.cs.systems.pdsd.lab05.sample09baseadapter.utils.CartoonsXmlParser;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;

public class CartoonActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		try {
			ListView listView1 = (ListView)findViewById(R.id.listView1);
			CartoonsXmlParser cartoonXmlParser = new CartoonsXmlParser();	
			InputStream inputstream = getAssets().open("cartoons.xml");
		    ArrayList<Cartoon> cartoons = cartoonXmlParser.parse(inputstream);
		    for (int count=0; count<cartoons.size(); count++)
		    	Log.println(Log.DEBUG, "tag", cartoons.get(count).toString());
		    CartoonAdapter cartoonAdapter = new CartoonAdapter(this, cartoons);
		    listView1.setAdapter(cartoonAdapter);
		}
	    catch (Exception exception) {
	    	Log.println(Log.ERROR, "error", exception.getMessage());
	    }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
