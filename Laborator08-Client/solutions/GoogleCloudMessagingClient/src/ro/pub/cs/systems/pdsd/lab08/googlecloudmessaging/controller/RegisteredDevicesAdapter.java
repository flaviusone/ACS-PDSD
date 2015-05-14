package ro.pub.cs.systems.pdsd.lab08.googlecloudmessaging.controller;

import java.util.ArrayList;

import ro.pub.cs.systems.pdsd.lab08.googlecloudmessaging.R;
import ro.pub.cs.systems.pdsd.lab08.googlecloudmessaging.model.RegisteredDevice;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class RegisteredDevicesAdapter extends BaseAdapter {
	
	private Context context;
	private LayoutInflater layoutInflater;
	
	private ArrayList<RegisteredDevice> data;
	
	private static class RegisteredDeviceViewHolder {
		private TextView registeredDeviceInformationTextView;
	}
	
	public RegisteredDevicesAdapter(Context context, ArrayList<RegisteredDevice> data) {
		this.context = context;
		this.layoutInflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.data = data;
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view;
		
		RegisteredDeviceViewHolder registeredDeviceViewHolder;
		
		RegisteredDevice registeredDevice = (RegisteredDevice)getItem(position);
		
		if (convertView == null) {
			view = layoutInflater.inflate(R.layout.registered_device, parent, false);
			registeredDeviceViewHolder = new RegisteredDeviceViewHolder();
			registeredDeviceViewHolder.registeredDeviceInformationTextView = (TextView)view.findViewById(R.id.registered_device_information_text_view);
			view.setTag(registeredDeviceViewHolder);
		} else {
			view = convertView;
		}
		
		registeredDeviceViewHolder = (RegisteredDeviceViewHolder)view.getTag();
		registeredDeviceViewHolder.registeredDeviceInformationTextView.setText(registeredDevice.getUsername());
		
		return view;
	}

}
