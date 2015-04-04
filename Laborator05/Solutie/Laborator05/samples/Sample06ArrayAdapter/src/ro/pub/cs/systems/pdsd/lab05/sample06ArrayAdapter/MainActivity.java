package ro.pub.cs.systems.pdsd.lab05.sample06ArrayAdapter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

class Point {
    double x, y;
    public Point() { x = 0; y = 0; }
    public Point(double x, double y) { this.x = x; this.y = y; }
    public double getX() { return x; }
    public void setX(double x) { this.x = x; }
    public double getY() { return y; }
    public void setY(double y) { this.y = y; }
    public String toString() { return "("+x+", "+y+")"; }
}

class PointArrayAdapter extends ArrayAdapter<Point> {
	private Context context;
	private int resource;
	private ArrayList<Point> content;
	
	public PointArrayAdapter(Context context, int resource, ArrayList<Point> content) {
		super(context, resource, content);
		this.context = context;
		this.resource = resource;
		this.content = content;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view;
		if (convertView == null) {
			LayoutInflater layoutinflator = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = layoutinflator.inflate(resource, null);
		}
		else
			view = convertView;
		ImageView imageview = (ImageView)view.findViewById(R.id.imageview);
		TextView textview = (TextView)view.findViewById(R.id.textview);
		Point point = content.get(position);
		if (point.getX() >= 0 && point.getY() >= 0)
			imageview.setImageResource(R.drawable.firstquadrant);
		else if (point.getX() < 0 && point.getY() >= 0)
			imageview.setImageResource(R.drawable.secondquadrant);
		else if (point.getX() < 0 && point.getY() < 0)
			imageview.setImageResource(R.drawable.thirdquadrant);
		else if (point.getX() >= 0 && point.getY() < 0)
			imageview.setImageResource(R.drawable.forthquadrant);
		textview.setText(point.toString());
		return view;
	}
}

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ListView listview = (ListView)findViewById(R.id.points_listview);
		ArrayList<Point> content = new ArrayList<Point>();
		content.add(new Point(1,1));
		content.add(new Point(-2,2));
		PointArrayAdapter adapter = new PointArrayAdapter(this, R.layout.pointlayout, content);
		listview.setAdapter(adapter);
		adapter.add(new Point(-3,-3));
		adapter.add(new Point(4,-4));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
