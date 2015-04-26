package ro.pub.cs.systems.pdsd.practicaltest02;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PracticalTest02SecondaryActivity extends Activity {

	protected class MyListener implements View.OnClickListener {
		@Override
		public void onClick(View view) {
			if (view instanceof Button) {
				if (view.getId() == R.id.button_cancel) {
					setResult(RESULT_CANCELED, new Intent());
					finish();
				}
				if (view.getId() == R.id.button_ok) {
					setResult(RESULT_OK, new Intent());
					finish();
				}
			}
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_practical_test02_secondary);
		
		MyListener ml = new MyListener();
		Button cancel = (Button)findViewById(R.id.button_cancel);
		cancel.setOnClickListener(ml);
		Button ok = (Button)findViewById(R.id.button_ok);
		ok.setOnClickListener(ml);
		
		TextView rezultat = (TextView)findViewById(R.id.rezultat);
		Intent intent = getIntent();
		if (intent != null) {
			String value = intent.getStringExtra("suma");
			if (value != null) {
				rezultat.setText(value);
			} else {
				rezultat.setText("0");
			}
		} else {
			rezultat.setText("0");
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.practical_test02_secondary, menu);
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
