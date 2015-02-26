package ro.pub.cs.systems.pdsd.lab01;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends Activity {
	
	final private static long TRANSPARENCY_EFFECT_DURATION = 5000;
	
	private ButtonClickListener buttonClickListener = new ButtonClickListener();
	
	private class ButtonClickListener implements Button.OnClickListener {

		@Override
		public void onClick(View view) {
			EditText userNameEditText = (EditText)findViewById(R.id.user_name_edit_text);
			TextView greetingTextView = (TextView)findViewById(R.id.greeting_text_view);
			greetingTextView.setText(greetingTextView.getText().toString().replace("xxx", "\n"+userNameEditText.getText()));
			greetingTextView.setAlpha(1);
			AlphaAnimation fadeEffect = new AlphaAnimation(1.0f, 0.0f);
			fadeEffect.setDuration(TRANSPARENCY_EFFECT_DURATION);
			fadeEffect.setFillAfter(true);
			greetingTextView.setAnimation(fadeEffect);
		}
		
	}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button submitButton = (Button)findViewById(R.id.submit_button);
        submitButton.setOnClickListener(buttonClickListener);
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
