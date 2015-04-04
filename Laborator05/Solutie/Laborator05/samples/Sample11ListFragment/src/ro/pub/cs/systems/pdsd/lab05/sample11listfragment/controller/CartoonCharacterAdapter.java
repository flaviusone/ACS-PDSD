package ro.pub.cs.systems.pdsd.lab05.sample11listfragment.controller;

import java.util.List;

import ro.pub.cs.systems.pdsd.lab05.sample11listfragment.R;
import ro.pub.cs.systems.pdsd.lab05.sample11listfragment.model.CartoonCharacter;
import ro.pub.cs.systems.pdsd.lab05.sample11listfragment.utils.PictureFinder;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CartoonCharacterAdapter extends BaseAdapter {
	
	private List<CartoonCharacter> content;
	private LayoutInflater layoutInflater;
	
	public static class ViewHolder {
		ImageView imageView1;
		TextView textView1, textView2, textView3, textView4;
	};
	
	public CartoonCharacterAdapter(Activity context, List<CartoonCharacter> content) {
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
	public View getView(int position, View convertView, ViewGroup parent) {
		View cartoonCharacterView;
		CartoonCharacter cartoonCharacterContent = (CartoonCharacter)getItem(position);
		if (convertView == null) {
			cartoonCharacterView = layoutInflater.inflate(R.layout.cartoon_character, parent, false);
			ViewHolder viewHolder = new ViewHolder();
			viewHolder.imageView1 = (ImageView)cartoonCharacterView.findViewById(R.id.imageView1);
			viewHolder.textView1 = (TextView)cartoonCharacterView.findViewById(R.id.textView1);
			viewHolder.textView2 = (TextView)cartoonCharacterView.findViewById(R.id.textView2);
			viewHolder.textView3 = (TextView)cartoonCharacterView.findViewById(R.id.textView3);
			viewHolder.textView4 = (TextView)cartoonCharacterView.findViewById(R.id.textView4);
			cartoonCharacterView.setTag(viewHolder);		
		}
		else
			cartoonCharacterView = convertView;
		ViewHolder viewHolder = (ViewHolder)cartoonCharacterView.getTag();
		viewHolder.imageView1.setImageResource(PictureFinder.findPictureByName(cartoonCharacterContent.getPicture()));
		viewHolder.textView1.setText(cartoonCharacterContent.getName());
		viewHolder.textView2.setText(cartoonCharacterContent.getSpecies());
		viewHolder.textView3.setText(cartoonCharacterContent.getGender());
		viewHolder.textView4.setText(cartoonCharacterContent.getDebut());
		return cartoonCharacterView;
	}
}
