package ro.pub.cs.systems.pdsd.lab05.sample09baseadapter.controller;

import java.util.ArrayList;

import ro.pub.cs.systems.pdsd.lab05.sample09baseadapter.R;
import ro.pub.cs.systems.pdsd.lab05.sample09baseadapter.general.Constants;
import ro.pub.cs.systems.pdsd.lab05.sample09baseadapter.model.Cartoon;
import ro.pub.cs.systems.pdsd.lab05.sample09baseadapter.utils.PictureFinder;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CartoonAdapter extends BaseAdapter {
	
	private ArrayList<Cartoon> content;
	private LayoutInflater layoutInflater;
	
	public static class ViewHolder {
		TextView textView1, textView2, textView3;
		ImageView imageView1;
	};
	
	public CartoonAdapter(Activity context, ArrayList<Cartoon> content) {
		this.content = content;
		layoutInflater = (LayoutInflater)context.getLayoutInflater();
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
	public int getViewTypeCount() {
		return Constants.LIST_VIEW_TYPES;
	}
	
	@Override
	public int getItemViewType(int position) {
		if (position % 2 == 1)
			return Constants.LIST_VIEW_TYPE_EVEN;
		return Constants.LIST_VIEW_TYPE_ODD;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View cartoonView;
		Cartoon cartoonContent = (Cartoon)getItem(position);
		if (convertView == null) {
			if (position % 2 == 0)
				cartoonView = layoutInflater.inflate(R.layout.cartoon1, parent, false);
			else
				cartoonView = layoutInflater.inflate(R.layout.cartoon2, parent, false);
			ViewHolder viewHolder = new ViewHolder();
			viewHolder.imageView1 = (ImageView)cartoonView.findViewById(R.id.imageView1);
			viewHolder.textView1 = (TextView)cartoonView.findViewById(R.id.textView1);
			viewHolder.textView2 = (TextView)cartoonView.findViewById(R.id.textView2);
			viewHolder.textView3 = (TextView)cartoonView.findViewById(R.id.textView3);
			cartoonView.setTag(viewHolder);		
		}
		else
			cartoonView = convertView;
		ViewHolder viewHolder = (ViewHolder)cartoonView.getTag();
		viewHolder.imageView1.setImageResource(PictureFinder.findPictureByName(cartoonContent.getPicture()));
		viewHolder.textView1.setText(cartoonContent.getName());
		viewHolder.textView2.setText(cartoonContent.getCreator());
		viewHolder.textView3.setText(cartoonContent.getDebut());
		
		return cartoonView;
	}
}
