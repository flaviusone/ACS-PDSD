package ro.pub.cs.systems.pdsd.lab05.sample05gallery;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

@SuppressWarnings("deprecation")
public class MainActivity extends Activity {
	
	Integer[] imageIDs = {
			R.drawable.fac01inginerieelectrica,
			R.drawable.fac02energetica,
			R.drawable.fac03automaticasicalculatoare,
			R.drawable.fac04electronicatelecomunicatiisitehnologiainformatiei,
			R.drawable.fac05ingineriemecanicasimecatronica,
			R.drawable.fac06ingineriasimanagementulsistemelortehnologice,
			R.drawable.fac07ingineriasistemelorbiotehnice,
			R.drawable.fac08transporturi,
			R.drawable.fac09inginerieaerospatiala,
			R.drawable.fac10stiintasiingineriamaterialelor,
			R.drawable.fac11chimieaplicatasistiintamaterialelor,
			R.drawable.fac12inginerieinlimbistraine,
			R.drawable.fac13stiinteaplicate,
			R.drawable.fac15antreprenoriatingineriasimanagementulafacerilor
		};	
	
public class ImageAdapter extends BaseAdapter {
	Context context;

	public ImageAdapter(Context context) {
		this.context = context;
	}
	
	public int getCount() {
		return imageIDs.length;
	}
	
	public Object getItem(int position) {
		return position;
	}
	
	public long getItemId(int position) {
		return position;
	}
	
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageview;
		if (convertView == null) {
			imageview = new ImageView(context);
			imageview.setImageResource(imageIDs[position]);
			imageview.setScaleType(ImageView.ScaleType.FIT_XY);
			imageview.setLayoutParams(new Gallery.LayoutParams(160, 120));
		}
		else
			imageview = (ImageView)convertView;
		return imageview;
	}
}


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Gallery gallery = (Gallery)findViewById(R.id.gallery);
		gallery.setAdapter(new ImageAdapter(this));
		gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Toast.makeText(getBaseContext(), "pic "+(position + 1)+" selected", Toast.LENGTH_SHORT).show();
				ImageView imageview = (ImageView)(findViewById(R.id.imageview));
				imageview.setImageResource(imageIDs[position]);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
