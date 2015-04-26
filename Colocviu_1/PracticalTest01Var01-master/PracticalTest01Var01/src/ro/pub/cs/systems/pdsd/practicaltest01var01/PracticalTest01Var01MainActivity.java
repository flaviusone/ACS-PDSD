package ro.pub.cs.systems.pdsd.practicaltest01var01;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;


public class PracticalTest01Var01MainActivity extends Activity {

	class NavigateListener implements OnClickListener {
		
		@Override
		public void onClick(View v) {
			CheckBox email = (CheckBox) findViewById(R.id.email);
	         CheckBox phone = (CheckBox) findViewById(R.id.phone);
	         CheckBox im = (CheckBox) findViewById(R.id.im);
	         String s = " ";
	         
	         if (email.isChecked()) { 
	        	s += "email ";
	         } 
	         if (phone.isChecked())
	        	 s += "phone ";
	         
	         if (im.isChecked())
	        	 s += "im";
	         
			Intent intent = new Intent("ro.pub.cs.systems.pdsd.practicaltest01var01.intent.action.Secondary");
			
			intent.putExtra("text", s);
			startActivityForResult(intent, 1);
		}
		
	}
	
	class CheckListener implements OnCheckedChangeListener {
		EditText edit;
		
		public CheckListener(EditText edit) {
			this.edit = edit;
		}
		
		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			int checks = Integer.parseInt(this.edit.getText().toString());
			if (isChecked)
				checks += 1;
			else
				checks -= 1;
			
			edit.setText("" + checks);
		}
		
	}
	
    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.activity_practical_test01_var01_main);
        EditText checks = (EditText) findViewById(R.id.checks);
        checks.setText("0");
        CheckListener l = new CheckListener(checks);
        CheckBox email = (CheckBox) findViewById(R.id.email);
        email.setOnCheckedChangeListener(l);
        CheckBox phone = (CheckBox) findViewById(R.id.phone);
        phone.setOnCheckedChangeListener(l);
        CheckBox im = (CheckBox) findViewById(R.id.im);
        im.setOnCheckedChangeListener(l);
        
        //android:saveEnabled:"false"
        /*if (state != null) {
        	boolean checked = state.getBoolean("email");
        	email.setChecked(checked);
        	checked = state.getBoolean("phone");
        	phone.setChecked(checked);
        	checked = state.getBoolean("im");
        	im.setChecked(checked);
        }*/
        
        Button b1 = (Button) findViewById(R.id.button1);
        b1.setOnClickListener(new NavigateListener());
        
    }
    
    @Override
    public void onRestoreInstanceState(Bundle state) {
      super.onRestoreInstanceState(state);
      CheckBox email = (CheckBox) findViewById(R.id.email);
      CheckBox phone = (CheckBox) findViewById(R.id.phone);
      CheckBox im = (CheckBox) findViewById(R.id.im);
      boolean checked = state.getBoolean("email");
  	  email.setChecked(checked);
  	  checked = state.getBoolean("phone");
  	  phone.setChecked(checked);
  	  checked = state.getBoolean("im");
  	  im.setChecked(checked);
    }
    
    @Override
    public void onSaveInstanceState(Bundle state) {
      super.onSaveInstanceState(state);
    	
    	 CheckBox email = (CheckBox) findViewById(R.id.email);
         CheckBox phone = (CheckBox) findViewById(R.id.phone);
         CheckBox im = (CheckBox) findViewById(R.id.im);
         
         Log.d("TAG", "onSaveInstanceState");
         
         state.putBoolean("email", email.isChecked());
         state.putBoolean("phone", phone.isChecked());
         state.putBoolean("im", im.isChecked());
    }
    
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
    	switch(requestCode) {
    	  case 1:
    	    setResult(resultCode, new Intent());
    	    Toast.makeText(this, "" + resultCode, Toast.LENGTH_LONG).show();
    	    break;
    	  }
    	}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.practical_test01_var01_main, menu);
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
