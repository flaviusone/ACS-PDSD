package ro.pub.cs.systems.pdsd.lab03.examples.textsamples;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView.CommaTokenizer;

public class TextSamplesActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_text_samples);
		
		AutoCompleteTextView autoCompleteTextView1 = (AutoCompleteTextView)findViewById(R.id.autoCompleteTextView1);
		 
		String[] suggestions = {"vlsi", "pdsd", "ts", "std", "so2", "idp", "ia", "scad", "pw", "ecom"};
		ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, suggestions);
		 
		autoCompleteTextView1.setAdapter(arrayAdapter1);
		
		MultiAutoCompleteTextView multiAutoCompleteTextView1 = (MultiAutoCompleteTextView)findViewById(R.id.multiAutoCompleteTextView1);		 
		
		multiAutoCompleteTextView1.setAdapter(arrayAdapter1);
		multiAutoCompleteTextView1.setTokenizer(new CommaTokenizer());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.text_samples, menu);
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
