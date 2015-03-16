package ro.pub.cs.systems.pdsd.lab03.examples.gridlayoutsamples;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class GridLayoutSamplesActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_grid_layout_samples);
		TextView monday_08to10_text_view = (TextView)findViewById(R.id.monday_08to10_textView);
		monday_08to10_text_view.setText(monday_08to10_text_view.getText().toString()+"\n"+
				getResources().getString(R.string.elective2)+"\n"+
				getResources().getString(R.string.dragos_niculescu)+"\n"+
				getResources().getString(R.string.eg106));
		TextView monday_10to12_text_view = (TextView)findViewById(R.id.monday_10to12_textView);
		monday_10to12_text_view.setText(monday_10to12_text_view.getText().toString()+"\n"+
				getResources().getString(R.string.group_341C1b)+"\n"+
				getResources().getString(R.string.dragos_niculescu)+"\n"+
				getResources().getString(R.string.eg106));
		TextView monday_12to14_text_view = (TextView)findViewById(R.id.monday_12to14_textView);
		monday_12to14_text_view.setText(monday_12to14_text_view.getText().toString()+"\n"+
				getResources().getString(R.string.group_341C1a)+"\n"+
				getResources().getString(R.string.dragos_niculescu)+"\n"+
				getResources().getString(R.string.eg106));
		TextView monday_14to16_text_view = (TextView)findViewById(R.id.monday_14to16_textView);
		monday_14to16_text_view.setText(monday_14to16_text_view.getText().toString()+"\n"+
				getResources().getString(R.string.dragos_niculescu)+"\n"+
				getResources().getString(R.string.ec105));
		TextView monday_18to20_text_view = (TextView)findViewById(R.id.monday_18to20_textView);
		monday_18to20_text_view.setText(monday_18to20_text_view.getText().toString()+"\n"+
				getResources().getString(R.string.group_343C1a)+"\n"+
				getResources().getString(R.string.andrei_rosu_cojocaru)+"\n"+
				getResources().getString(R.string.eg106));
		TextView tuesday_08to10_text_view = (TextView)findViewById(R.id.tuesday_08to10_textView);
		tuesday_08to10_text_view.setText(tuesday_08to10_text_view.getText().toString()+"\n"+
				getResources().getString(R.string.group_342C1a)+"\n"+
				getResources().getString(R.string.radu_stoenescu)+"\n"+
				getResources().getString(R.string.eg106));
		TextView tuesday_10to12_text_view = (TextView)findViewById(R.id.tuesday_10to12_textView);
		tuesday_10to12_text_view.setText(tuesday_10to12_text_view.getText().toString()+"\n"+
				getResources().getString(R.string.group_342C1b)+"\n"+
				getResources().getString(R.string.radu_stoenescu)+"\n"+
				getResources().getString(R.string.eg106));
		TextView tuesday_19to21_text_view = (TextView)findViewById(R.id.tuesday_19to21_textView);
		tuesday_19to21_text_view.setText(tuesday_19to21_text_view.getText().toString()+"\n"+
				getResources().getString(R.string.elective1)+"\n"+
				getResources().getString(R.string.andrei_rosu_cojocaru)+"\n"+
				getResources().getString(R.string.eg106));
		TextView friday_18to20_text_view = (TextView)findViewById(R.id.friday_18to20_textView);
		friday_18to20_text_view.setText(friday_18to20_text_view.getText().toString()+"\n"+
				getResources().getString(R.string.group_343C1b)+"\n"+
				getResources().getString(R.string.andrei_rosu_cojocaru)+"\n"+
				getResources().getString(R.string.eg106));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.grid_layout_samples, menu);
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
