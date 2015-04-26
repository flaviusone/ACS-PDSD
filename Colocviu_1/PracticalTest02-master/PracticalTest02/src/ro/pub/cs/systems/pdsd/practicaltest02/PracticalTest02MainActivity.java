package ro.pub.cs.systems.pdsd.practicaltest02;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PracticalTest02MainActivity extends Activity {

	//clasa listener
	protected class MyListener implements View.OnClickListener {
		
		int cont1=0;
		int cont2=0;
		@Override
		public void onClick(View view) {
			EditText left_text = (EditText)findViewById(R.id.left_text);
			EditText right_text = (EditText)findViewById(R.id.right_text);
			if (view instanceof Button) {
				if (view.getId() == R.id.button_left) {
					cont1++;
					String value = Integer.toString(cont1);
					left_text.setText(value);
				}
				if (view.getId() == R.id.button_right) {
					cont2++;
					String value = Integer.toString(cont2);
					right_text.setText(value);
				}
				if (view.getId() == R.id.navigate) {
					Intent intent = new Intent("ro.pub.cs.systems.pdsd.practicaltest02.PracticalTest02SecondaryActivity");
					int sum = cont1 + cont2;
					String suma = Integer.toString(sum);
					intent.putExtra("suma", suma);
					startActivityForResult(intent, 2015);
				}
			}
		}
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_practical_test02_main);
		
		EditText left_text = (EditText)findViewById(R.id.left_text);
		EditText right_text = (EditText)findViewById(R.id.right_text);
		left_text.setText(String.valueOf(0));
		right_text.setText(String.valueOf(0));
		
		MyListener ml = new MyListener();
		Button button_left = (Button)findViewById(R.id.button_left);
		button_left.setOnClickListener(ml);
		Button button_right =(Button)findViewById(R.id.button_right);
		button_right.setOnClickListener(ml);
		Button navigate = (Button)findViewById(R.id.navigate);
		navigate.setOnClickListener(ml);
		
		//salvare
		if (savedInstanceState != null) {
			String value_l = savedInstanceState.getString("left_text");
			if (value_l != null) {
				left_text.setText(value_l);
			} else {
				left_text.setText(String.valueOf(0));
			}
			String value_r = savedInstanceState.getString("right_text");
			if (value_r != null) {
				right_text.setText(value_r);
			} else {
				right_text.setText(String.valueOf(0));
			}
		} else {
			left_text.setText(String.valueOf(0));
			right_text.setText(String.valueOf(0));
		}
	}

	//salvare context
	@Override
	protected void onSaveInstanceState(Bundle savedInstanceState) {
		super.onSaveInstanceState(savedInstanceState);
		
		EditText left_text = (EditText)findViewById(R.id.left_text);
		EditText right_text = (EditText)findViewById(R.id.right_text);
		savedInstanceState.putString("left_text", left_text.getText().toString());
		savedInstanceState.putString("right_text", right_text.getText().toString());
		
	};
	
	//returneaza result code
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		Toast.makeText(this, "The activity returned with result "+resultCode, Toast.LENGTH_LONG).show();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.practical_test02_main, menu);
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
