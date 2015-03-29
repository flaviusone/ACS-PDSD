package ro.pub.cs.systems.pdsd.lab03.examples.layoutsample2;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class LayoutSample2Activity extends Activity {

	final private static long TRANSPARENCY_EFFECT_DURATION = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    	RelativeLayout container = new RelativeLayout(this);
    	container.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
    	
    	final EditText introduceYourselfEditText = new EditText(this);
    	introduceYourselfEditText.setId(1);
    	introduceYourselfEditText.setEms(10);
    	introduceYourselfEditText.setHint(getString(R.string.introduce_yourself));
    	introduceYourselfEditText.setInputType(InputType.TYPE_CLASS_TEXT);
    	introduceYourselfEditText.setFocusable(true);
        RelativeLayout.LayoutParams introduceYourselfEditTextLayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
    	container.addView(introduceYourselfEditText, introduceYourselfEditTextLayoutParams);
    	
    	final CheckBox displayIdentityCheckBox = new CheckBox(this);
    	displayIdentityCheckBox.setId(2);
    	displayIdentityCheckBox.setText(getString(R.string.display_identity));
    	RelativeLayout.LayoutParams displayIdentityCheckBoxLayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);		
    	displayIdentityCheckBoxLayoutParams.addRule(RelativeLayout.ALIGN_LEFT, introduceYourselfEditText.getId());
    	displayIdentityCheckBoxLayoutParams.addRule(RelativeLayout.BELOW, introduceYourselfEditText.getId());
    	container.addView(displayIdentityCheckBox, displayIdentityCheckBoxLayoutParams);

    	final Button submitButton = new Button(this);
    	submitButton.setId(3);
    	submitButton.setText(getString(R.string.submit));
    	RelativeLayout.LayoutParams submitButtonLayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);		
    	submitButtonLayoutParams.addRule(RelativeLayout.ALIGN_LEFT, displayIdentityCheckBox.getId());
    	submitButtonLayoutParams.addRule(RelativeLayout.BELOW, displayIdentityCheckBox.getId());
    	container.addView(submitButton, submitButtonLayoutParams);
    	
    	final TextView greetingTextView = new TextView(this);
    	greetingTextView.setId(4);
    	greetingTextView.setText(getString(R.string.greeting));
    	greetingTextView.setTextSize(32);
    	greetingTextView.setGravity(Gravity.CENTER);
    	greetingTextView.setAlpha(0);
    	RelativeLayout.LayoutParams greetingTextViewLayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
    	greetingTextViewLayoutParams.addRule(RelativeLayout.ALIGN_LEFT, submitButton.getId());
    	greetingTextViewLayoutParams.addRule(RelativeLayout.BELOW, submitButton.getId());
    	container.addView(greetingTextView, greetingTextViewLayoutParams);    	

        submitButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				String identity = introduceYourselfEditText.getText().toString();
				greetingTextView.setText(getResources().getString(R.string.greeting).replace("???", (displayIdentityCheckBox.isChecked())?identity:getResources().getString(R.string.anonymous)));
				greetingTextView.setAlpha(1);
				AlphaAnimation fadeEffect = new AlphaAnimation(1.0f, 0.0f);
				fadeEffect.setDuration(TRANSPARENCY_EFFECT_DURATION);
				fadeEffect.setFillAfter(true);
				greetingTextView.setAnimation(fadeEffect);
			}
        	
        });
        
    	setContentView(container);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.layout_sample2, menu);
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
