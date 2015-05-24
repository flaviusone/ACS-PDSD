package ro.pub.cs.systems.pdsd.lab07.ocwcoursesdisplayer.controller;

import java.util.ArrayList;

import ro.pub.cs.systems.pdsd.lab07.ocwcoursesdisplayer.R;
import ro.pub.cs.systems.pdsd.lab07.ocwcoursesdisplayer.model.OcwCourseInformation;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class OcwCourseInformationAdapter extends BaseAdapter {

	private Context context;
	private ArrayList<OcwCourseInformation> content;
	
	private LayoutInflater layoutInflater;
	
	private static class OcwCourseInformationViewHolder {
		ImageView logoImageView;
		TextView nameTextView;
	}
	
	public OcwCourseInformationAdapter(Context context, ArrayList<OcwCourseInformation> content) {
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
		OcwCourseInformationViewHolder ocwCourseInformationViewHolder;
		
		OcwCourseInformation ocwCourseInformation = content.get(position);
		
	    if (convertView == null) {  
	      view = layoutInflater.inflate(R.layout.ocw_course_information, parent, false);
	      ocwCourseInformationViewHolder = new OcwCourseInformationViewHolder();
	      ocwCourseInformationViewHolder.logoImageView = (ImageView)view.findViewById(R.id.logo_image_view);
	      ocwCourseInformationViewHolder.nameTextView = (TextView)view.findViewById(R.id.name_text_view);
	      view.setTag(ocwCourseInformationViewHolder);
	    } else {
	      view = convertView;
	    }
	    ocwCourseInformationViewHolder = (OcwCourseInformationViewHolder)view.getTag();
	    ocwCourseInformationViewHolder.logoImageView.setImageBitmap(ocwCourseInformation.getLogo());
	    ocwCourseInformationViewHolder.nameTextView.setText(ocwCourseInformation.getName());
	    return view;
	}

}
