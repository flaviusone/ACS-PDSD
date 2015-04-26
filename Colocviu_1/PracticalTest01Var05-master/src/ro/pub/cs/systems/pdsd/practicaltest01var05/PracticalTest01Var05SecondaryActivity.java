package ro.pub.cs.systems.pdsd.practicaltest01var05;

import android.app.Activity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class PracticalTest01Var05SecondaryActivity extends Activity {

	
	private ButtonClickListener2 buttonClickListener2 = new ButtonClickListener2();
	 
	  private class ButtonClickListener2 implements Button.OnClickListener {
	 
	    @Override
	    public void onClick(View view) {
	      switch(view.getId()) {
	        case R.id.registerButton:
	          setResult(RESULT_OK, new Intent());
	          finish();
	          break;
	        case R.id.cancelButton:
	          setResult(RESULT_CANCELED, new Intent());
	          finish();
	          break;
	      }
	    }
	  }
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_practical_test01_var05_secondary);
		
		TextView numberOfClicksTextView = (TextView)findViewById(R.id.number_of_clicks_text_view);
		TextView paths = (TextView)findViewById(R.id.path);
		
		Intent intent = getIntent();
	    if (intent != null) {
	      String numberOfClicks = intent.getStringExtra("register");
	      String directionss = intent.getStringExtra("path");
	      if (numberOfClicks != null) {
	        numberOfClicksTextView.setText(numberOfClicks);
	      }
	      if (directionss != null) {
	    	  paths.setText(directionss);
		      }
	    }
	    
	    Button buttonOk = (Button)findViewById(R.id.registerButton);
	    buttonOk.setOnClickListener(buttonClickListener2);
	    Button buttonCancel = (Button)findViewById(R.id.cancelButton);
	    buttonCancel.setOnClickListener(buttonClickListener2);     
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater()
				.inflate(R.menu.practical_test01_var05_secondary, menu);
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
