package ro.pub.cs.systems.pdsd.practicaltest01var05;

import android.app.Activity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;



public class PracticalTest01Var05MainActivity extends Activity {
	private ButtonClickListener buttonClickListener = new ButtonClickListener();
	private final static int SECONDARY_ACTIVITY_REQUEST_CODE = 1;
	int count;
	private class ButtonClickListener implements Button.OnClickListener {
		 
	    @Override
	    public void onClick(View view) {
	      EditText direction_text = (EditText)PracticalTest01Var05MainActivity.this.findViewById(R.id.direction_text);

	      //int leftButtonClickedNumber = Integer.parseInt(direction_text.getText().toString());
	      //int rightButtonClickedNumber = Integer.parseInt(rightEditText.getText().toString());
	      String directions = direction_text.getText().toString();
	 
	      switch(view.getId()) {
	        case R.id.buttonSouth:
	        	directions = directions.concat("South,");
	        	direction_text.setText(directions);
	          break;
	        case R.id.buttonNorth:
	        	directions = directions.concat("North,");
	          //rightButtonClickedNumber++;
	          direction_text.setText(directions);
	          break;
	        case R.id.buttonWest:
	        	directions = directions.concat("West,");
	        	direction_text.setText(directions);
	        	break;
	        case R.id.buttonEast:
	        	directions =directions.concat("East,");
	        	direction_text.setText(directions);
	        	break;
	        case R.id.nextActivity:
				Intent i = new Intent("ro.cs.pub.secondary.intent.action.PracticalTest01Var05SecondaryActivity");
				i.putExtra("register", String.valueOf(count));
				i.putExtra("path", String.valueOf(directions));
				startActivityForResult(i, SECONDARY_ACTIVITY_REQUEST_CODE);
				break;
			}
	      }
	    }
	   

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        if (savedInstanceState != null) {
        	int countAux = savedInstanceState.getInt("register");
	        count = countAux;
        }else {
        	count=0;
        }
        
        
        setContentView(R.layout.activity_practical_test01_var05_main);
        EditText direction_text = (EditText)findViewById(R.id.direction_text);
        direction_text.setText(String.valueOf(""));
        
        Button west = (Button)findViewById(R.id.buttonWest);
        west.setOnClickListener(buttonClickListener);
        Button east = (Button)findViewById(R.id.buttonEast);
        east.setOnClickListener(buttonClickListener);
        Button north = (Button)findViewById(R.id.buttonNorth);
        north.setOnClickListener(buttonClickListener);
        Button south = (Button)findViewById(R.id.buttonSouth);
        south.setOnClickListener(buttonClickListener);
        Button navig = (Button) findViewById(R.id.nextActivity);
		navig.setOnClickListener(buttonClickListener);
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
    	savedInstanceState.putInt("register", count);
    }
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.practical_test01_var05_main, menu);
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
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
    	if(resultCode == -1)
    	{
    		count++;
    		
    	}
    	EditText direction_text = (EditText)PracticalTest01Var05MainActivity.this.findViewById(R.id.direction_text);
		direction_text.setText("");
      //Toast.makeText(this, "The activity returned with result "+resultCode, Toast.LENGTH_LONG).show();
    }
}
