package ro.pub.cs.systems.pdsd.lab07.landmarklister.controller;

import java.util.ArrayList;

import ro.pub.cs.systems.pdsd.lab07.earthquakelister.R;
import ro.pub.cs.systems.pdsd.lab07.landmarklister.model.LandmarkInformation;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class LandmarkInformationAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<LandmarkInformation> content;
	
	private LayoutInflater layoutInflater;
	
	private static class EarthquakeInformationViewHolder {
		TextView latitudeTextView, longitudeTextView;
		TextView toponymNameTextView;
		TextView populationTextView;
		TextView fCodeNameTextView;
		TextView nameTextView;
		TextView wikipediaWebPageAddressTextView;
		TextView countryCodeTextView;
	}
	
	public LandmarkInformationAdapter(Context context, ArrayList<LandmarkInformation> content) {
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
		
		LandmarkInformation earthquakeInformation = content.get(position);
		
	    if (convertView == null) {  
	      view = layoutInflater.inflate(R.layout.landmark_information, parent, false);
	      earthquakeInformationViewHolder = new EarthquakeInformationViewHolder();
	      earthquakeInformationViewHolder.latitudeTextView = (TextView)view.findViewById(R.id.latitude_text_view);
	      earthquakeInformationViewHolder.longitudeTextView = (TextView)view.findViewById(R.id.longitude_text_view);
	      earthquakeInformationViewHolder.toponymNameTextView = (TextView)view.findViewById(R.id.toponym_name_text_view);
	      earthquakeInformationViewHolder.populationTextView = (TextView)view.findViewById(R.id.population_text_view);
	      earthquakeInformationViewHolder.fCodeNameTextView = (TextView)view.findViewById(R.id.f_code_name_text_view);
	      earthquakeInformationViewHolder.nameTextView = (TextView)view.findViewById(R.id.name_text_view);
	      earthquakeInformationViewHolder.wikipediaWebPageAddressTextView = (TextView)view.findViewById(R.id.wikipedia_web_page_address_text_view);
	      earthquakeInformationViewHolder.countryCodeTextView = (TextView)view.findViewById(R.id.country_code_text_view);
	      view.setTag(earthquakeInformationViewHolder);
	    } else {
	      view = convertView;
	    }
	    earthquakeInformationViewHolder = (EarthquakeInformationViewHolder)view.getTag();
	    earthquakeInformationViewHolder.latitudeTextView.setText(String.valueOf(earthquakeInformation.getLatitude()));
	    earthquakeInformationViewHolder.longitudeTextView.setText(String.valueOf(earthquakeInformation.getLongitude()));
	    earthquakeInformationViewHolder.toponymNameTextView.setText(String.valueOf(earthquakeInformation.getToponymName()));
	    earthquakeInformationViewHolder.populationTextView.setText(String.valueOf(earthquakeInformation.getPopulation()));
	    earthquakeInformationViewHolder.fCodeNameTextView.setText(earthquakeInformation.getFCodeName());
	    earthquakeInformationViewHolder.nameTextView.setText(earthquakeInformation.getName());
	    earthquakeInformationViewHolder.wikipediaWebPageAddressTextView.setText(earthquakeInformation.getWikipediaWebPageAddress());
	    earthquakeInformationViewHolder.countryCodeTextView.setText(earthquakeInformation.getCountryCode());
	 
	    return view;
	}

}
