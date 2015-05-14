package ro.pub.cs.systems.pdsd.lab07.earthquakelister.controller;

import java.util.ArrayList;

import ro.pub.cs.systems.pdsd.lab07.earthquakelister.R;
import ro.pub.cs.systems.pdsd.lab07.earthquakelister.model.EarthquakeInformation;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class EarthquakeInformationAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<EarthquakeInformation> content;
	
	private LayoutInflater layoutInflater;
	
	private static class EarthquakeInformationViewHolder {
		TextView latitudeTextView, longitudeTextView;
		TextView magnitudeTextView;
		TextView depthTextView;
		TextView sourceTextView;
		TextView datetimeTextView;
	}
	
	public EarthquakeInformationAdapter(Context context, ArrayList<EarthquakeInformation> content) {
		this.context = context;
	    this.content = content;
	    this.layoutInflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return content.size();
	}

	@Override
	public Object getItem(int position) {
		return content.get(position);
	}
	
	@Override
	public long getItemId(int position) {
		return 0;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view;
		EarthquakeInformationViewHolder earthquakeInformationViewHolder;
		
		EarthquakeInformation earthquakeInformation = content.get(position);
		
	    if (convertView == null) {  
	      view = layoutInflater.inflate(R.layout.earthquake_information, parent, false);
	      earthquakeInformationViewHolder = new EarthquakeInformationViewHolder();
	      earthquakeInformationViewHolder.latitudeTextView = (TextView)view.findViewById(R.id.latitude_text_view);
	      earthquakeInformationViewHolder.longitudeTextView = (TextView)view.findViewById(R.id.longitude_text_view);
	      earthquakeInformationViewHolder.magnitudeTextView = (TextView)view.findViewById(R.id.magnitude_text_view);
	      earthquakeInformationViewHolder.depthTextView = (TextView)view.findViewById(R.id.depth_text_view);
	      earthquakeInformationViewHolder.sourceTextView = (TextView)view.findViewById(R.id.source_text_view);
	      earthquakeInformationViewHolder.datetimeTextView = (TextView)view.findViewById(R.id.datetime_text_view);
	      view.setTag(earthquakeInformationViewHolder);
	    } else {
	      view = convertView;
	    }
	    earthquakeInformationViewHolder = (EarthquakeInformationViewHolder)view.getTag();
	    earthquakeInformationViewHolder.latitudeTextView.setText(String.valueOf(earthquakeInformation.getLatitude()));
	    earthquakeInformationViewHolder.longitudeTextView.setText(String.valueOf(earthquakeInformation.getLongitude()));
	    earthquakeInformationViewHolder.magnitudeTextView.setText(String.valueOf(earthquakeInformation.getMagnitude()));
	    earthquakeInformationViewHolder.depthTextView.setText(String.valueOf(earthquakeInformation.getDepth()));
	    earthquakeInformationViewHolder.sourceTextView.setText(earthquakeInformation.getSource());
	    earthquakeInformationViewHolder.datetimeTextView.setText(earthquakeInformation.getDatetime());
	 
	    return view;
	}

}
