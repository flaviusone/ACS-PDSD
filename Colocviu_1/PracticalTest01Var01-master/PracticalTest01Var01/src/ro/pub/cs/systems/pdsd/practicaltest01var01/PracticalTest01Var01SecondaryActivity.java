package ro.pub.cs.systems.pdsd.practicaltest01var01;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class PracticalTest01Var01SecondaryActivity extends Activity {

	class ButtonListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			Button b = (Button) v;
			
			if (b.getId() == R.id.save) {
				save();
			}
			else {
				cancel();
			}
			
			finish();
		}
		
		private void save() {
			setResult(1, new Intent());
		}
		
		private void cancel() {
			setResult(0, new Intent());
		}
		
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_practical_test01_var01_secondary);
		
		Intent intent = getIntent();
		
		EditText t1 = (EditText) findViewById(R.id.total);
		
		if (intent != null) {
			String value = intent.getStringExtra("text");
			t1.setText(value);
		}
		
		Button b1 = (Button) findViewById(R.id.save);
		Button b2 = (Button) findViewById(R.id.cancel);
		
		ButtonListener l = new ButtonListener();
		
		b1.setOnClickListener(l);
		b2.setOnClickListener(l);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater()
				.inflate(R.menu.practical_test01_var01_secondary, menu);
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
