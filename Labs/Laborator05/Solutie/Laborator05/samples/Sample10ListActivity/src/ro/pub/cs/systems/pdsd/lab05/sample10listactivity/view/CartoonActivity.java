package ro.pub.cs.systems.pdsd.lab05.sample10listactivity.view;

import java.io.InputStream;
import java.util.ArrayList;

import ro.pub.cs.systems.pdsd.lab05.sample10listactivity.R;
import ro.pub.cs.systems.pdsd.lab05.sample10listactivity.controller.CartoonAdapter;
import ro.pub.cs.systems.pdsd.lab05.sample10listactivity.model.Cartoon;
import ro.pub.cs.systems.pdsd.lab05.sample10listactivity.utils.CartoonsXmlParser;
import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

public class CartoonActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		try {
			CartoonsXmlParser cartoonXmlParser = new CartoonsXmlParser();	
			InputStream inputstream = getAssets().open("cartoons.xml");
		    ArrayList<Cartoon> cartoons = cartoonXmlParser.parse(inputstream);
		    for (int count=0; count<cartoons.size(); count++)
		    	Log.println(Log.DEBUG, "tag", cartoons.get(count).toString());
		    CartoonAdapter cartoonAdapter = new CartoonAdapter(this, cartoons);
		    setListAdapter(cartoonAdapter);
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
