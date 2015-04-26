package ro.pub.cs.systems.pdsd.practicaltest01var04;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PracticalTest01Var04MainActivity extends Activity {

	ButtonClickListener buttonClickListener = new ButtonClickListener();
	Integer all_tries = new Integer(0);
	Integer incorrect_matches = new Integer(0);
	Integer correct_matches = new Integer(0);
	int SECONDARY_ACTIVITY_REQUEST_CODE = 1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_practical_test01_var04_main);

		if(savedInstanceState != null){
			all_tries = (Integer) savedInstanceState.getInt("all");
			incorrect_matches = (Integer) savedInstanceState.getInt("incorrect");
			correct_matches = (Integer) savedInstanceState.getInt("correct");
			if(all_tries != null){
				 Log.d("AllTries", Integer.toString(all_tries));
			}
			if(incorrect_matches != null){
				Log.d("Incorrect", Integer.toString(incorrect_matches));
			}
			if(correct_matches != null){
				Log.d("Correct", Integer.toString(correct_matches));
			}
		}
		Button do_star = (Button) findViewById(R.id.button_do_star);
		do_star.setOnClickListener(buttonClickListener);
		Button sol = (Button) findViewById(R.id.button_sol);
		sol.setOnClickListener(buttonClickListener);
		Button mi = (Button) findViewById(R.id.button_mi);
		mi.setOnClickListener(buttonClickListener);
		Button do_first = (Button) findViewById(R.id.button_do);
		do_first.setOnClickListener(buttonClickListener);
		EditText notes = (EditText) findViewById(R.id.edit_text);
		
		Button navig = (Button) findViewById(R.id.navigate);
		navig.setOnClickListener(buttonClickListener);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.practical_test01_var04_main, menu);
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
	@Override
	protected void onSaveInstanceState(Bundle savedInstaceState){
		 savedInstaceState.putInt("all", all_tries);
		 savedInstaceState.putInt("incorrect", incorrect_matches);
		 savedInstaceState.putInt("correct", correct_matches);
	}
	protected void onActivityResult(int reqCode, int resultCode, Intent intent){
		Toast.makeText(this, "The activity returned with result "+ resultCode, Toast.LENGTH_LONG).show();
		if(resultCode == RESULT_OK){
			all_tries++;
			correct_matches++;
			Log.d("AllTries", Integer.toString(all_tries));
			Log.d("Correct", Integer.toString(correct_matches));
		}else{
			all_tries++;
			incorrect_matches++;
			Log.d("AllTries", Integer.toString(all_tries));
			Log.d("Incorrect", Integer.toString(incorrect_matches));
		}
	}
	private class ButtonClickListener implements Button.OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			EditText notes = (EditText) findViewById(R.id.edit_text);
			String current_state;
			current_state = notes.getText().toString();
			int current_seq = 0;
			switch(v.getId()){
			case R.id.button_do_star:
				if(!current_state.isEmpty())
					current_state = current_state + ",Do*";
				else
					current_state = current_state + "Do*";
				notes.setText(current_state);
				current_seq = current_seq * 10 + 4;
				break;
			case R.id.button_sol:
				if(!current_state.isEmpty())
					current_state = current_state + ",Sol";
				else
					current_state = current_state + "Sol";
				notes.setText(current_state);
				current_seq = current_seq * 10 + 3;
				break;
			case R.id.button_mi:
				if(!current_state.isEmpty())
					current_state = current_state + ",Mi";
				else
					current_state = current_state + "Mi";
				notes.setText(current_state);
				current_seq = current_seq * 10 + 2;
				break;
			case R.id.button_do:
				if(!current_state.isEmpty())
					current_state = current_state + ",Do";
				else
					current_state = current_state + "Do";
				notes.setText(current_state);
				current_seq = current_seq * 10 + 1;
				break;
			case R.id.navigate:
				Intent i = new Intent("ro.cs.pub.secondary.intent.action.SEC");
				i.putExtra("seq", current_state);
				startActivityForResult(i, SECONDARY_ACTIVITY_REQUEST_CODE);
				break;
			}
		}

	}
}
