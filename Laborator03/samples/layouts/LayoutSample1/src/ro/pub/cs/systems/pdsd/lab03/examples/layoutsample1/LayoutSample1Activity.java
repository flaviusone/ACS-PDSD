package ro.pub.cs.systems.pdsd.lab03.examples.layoutsample1;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;


public class LayoutSample1Activity extends Activity {
	
	final private static long TRANSPARENCY_EFFECT_DURATION = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_sample_1);
        Button submitButton = (Button)findViewById(R.id.submit_button);
        submitButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				EditText introduceYourselfEditText = (EditText)findViewById(R.id.introduce_yourself_edit_text);
				CheckBox displayIdentityCheckBox = (CheckBox)findViewById(R.id.display_identity_check_box);
				TextView greetingTextView = (TextView)findViewById(R.id.greeting_text_view);
				String identity = introduceYourselfEditText.getText().toString();
				greetingTextView.setText(getResources().getString(R.string.greeting).replace("???", (displayIdentityCheckBox.isChecked())?identity:getResources().getString(R.string.anonymous)));
				greetingTextView.setAlpha(1);
				AlphaAnimation fadeEffect = new AlphaAnimation(1.0f, 0.0f);
				fadeEffect.setDuration(TRANSPARENCY_EFFECT_DURATION);
				fadeEffect.setFillAfter(true);
				greetingTextView.setAnimation(fadeEffect);
			}
        	
        });
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
