package ro.pub.cs.systems.pdsd.lab03.phonedialer.activities;

import java.util.ArrayList;

import ro.pub.cs.systems.pdsd.lab03.phonedialer.R;
import ro.pub.cs.systems.pdsd.lab03.phonedialer.general.Constants;
import android.app.Activity;
import android.content.Intent;
//import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class PhoneDialerActivity extends Activity {
	
	private KeypadButtonClickListener buttonClickListener = new KeypadButtonClickListener();
	
	private class KeypadButtonClickListener implements View.OnClickListener {

		@Override
		@SuppressWarnings("all")
		public void onClick(View view) {
			
			EditText phoneNumberEditText = (EditText)findViewById(R.id.edit_text_phone_number);
			String phoneNumber = phoneNumberEditText.getText().toString();

			if (view instanceof Button) {
				phoneNumberEditText.setText(phoneNumber+((Button)view).getText().toString());
			}
			
			if (view instanceof ImageButton) {
				switch(((ImageButton)view).getId()) {
					case R.id.button_backspace:
						if (phoneNumber.length() > 0) {
							phoneNumberEditText.setText(phoneNumber.substring(0, phoneNumber.length()-1));
						}
						break;
					case R.id.button_call:
						Intent intent = new Intent(Intent.ACTION_CALL);
						intent.setData(Uri.parse("tel:"+phoneNumber));
						startActivity(intent);
						break;
					case R.id.button_hangup:
						finish();
						break;
				}
			}
		}

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.activity_phone_dialer);
		ArrayList<? super View> phoneButtons = new ArrayList<View>();
		for (int index = 0; index < Constants.textButtonIds.length; index++) {
			Button textButton = (Button)findViewById(Constants.textButtonIds[index]);
			textButton.setOnClickListener(buttonClickListener);
			phoneButtons.add(textButton);
		}
		for (int index = 0; index < Constants.imageButtonIds.length; index++) {
			ImageButton imageButton = (ImageButton)findViewById(Constants.imageButtonIds[index]);
			imageButton.setOnClickListener(buttonClickListener);
			phoneButtons.add(imageButton);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.phone_dialer, menu);
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
