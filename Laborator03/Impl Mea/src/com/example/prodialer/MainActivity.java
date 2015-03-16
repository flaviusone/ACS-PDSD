package com.example.prodialer;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.example.prodialer.Constants;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class MainActivity extends Activity {

	private KeypadButtonClickListener buttonClickListener = new KeypadButtonClickListener();
	
	private class KeypadButtonClickListener implements View.OnClickListener {

		@Override
		@SuppressWarnings("all")
		public void onClick(View view) {
			
			EditText phoneNumberEditText = (EditText)findViewById(R.id.editText1);
			String phoneNumber = phoneNumberEditText.getText().toString();

			if (view instanceof Button) {
				switch(view.getId()) {
				case R.id.call_button:
					Intent intent = new Intent(Intent.ACTION_CALL);
					intent.setData(Uri.parse("tel:"+phoneNumber));
					startActivity(intent);
					break;
				case R.id.delete:
					if (phoneNumber.length() > 0) {
						
						phoneNumberEditText.setText(phoneNumber.substring(0, phoneNumber.length()-1));
					}
					break;
				case R.id.question:
					break;
				default:
					phoneNumberEditText.setText(phoneNumber+((Button)view).getText().toString());
				
				}
				
			}
			phoneNumberEditText.setSelection(phoneNumberEditText.length());
		}
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ArrayList<? super View> phoneButtons = new ArrayList<View>();
		
		for (int index = 0; index < Constants.textButtonIds.length; index++) {
			Button textButton = (Button)findViewById(Constants.textButtonIds[index]);
			textButton.setOnClickListener(buttonClickListener);
			phoneButtons.add(textButton);
		}
//		for (int index = 0; index < Constants.imageButtonIds.length; index++) {
//			ImageButton imageButton = (ImageButton)findViewById(Constants.imageButtonIds[index]);
//			imageButton.setOnClickListener(buttonClickListener);
//			phoneButtons.add(imageButton);
//		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
