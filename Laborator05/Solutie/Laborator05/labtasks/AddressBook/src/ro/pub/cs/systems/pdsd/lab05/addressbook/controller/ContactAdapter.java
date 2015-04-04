package ro.pub.cs.systems.pdsd.lab05.addressbook.controller;

import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import ro.pub.cs.systems.pdsd.lab05.addressbook.R;
import ro.pub.cs.systems.pdsd.lab05.addressbook.general.Constants;
import ro.pub.cs.systems.pdsd.lab05.addressbook.general.Utilities;
import ro.pub.cs.systems.pdsd.lab05.addressbook.model.Contact;
import android.app.Activity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.Contacts;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ContactAdapter extends BaseAdapter implements LoaderManager.LoaderCallbacks<Cursor> {
	
	Activity context;
	LayoutInflater inflater;
	
	ArrayList<Contact> data;
	String searchCriteria;
	
	Cursor cursor;
	int checkedItemPosition = -1;
	
    private static final String[] PROJECTION = {
    	Contacts._ID,
    	Contacts.LOOKUP_KEY,
    	Contacts.PHOTO_THUMBNAIL_URI,
    	Contacts.DISPLAY_NAME_PRIMARY,
    	Contacts.STARRED,
    	Contacts.TIMES_CONTACTED,
    	Contacts.LAST_TIME_CONTACTED
    };

    private static final int ID_INDEX = 0;
    private static final int LOOKUP_KEY_INDEX = 1;
    private static final int PHOTO_THUMBNAIL_URI_INDEX = 2;
    private static final int DISPLAY_NAME_INDEX = 3;
    private static final int STARRED_INDEX = 4;
    private static final int TIMES_CONTACTED_INDEX = 5;
    private static final int LAST_TIME_CONTACTED_INDEX = 6;
	
    private static final String SELECTION = "(" + Contacts.DISPLAY_NAME + " NOTNULL) AND ("
    		+ Contacts.HAS_PHONE_NUMBER + "=1) AND ("
    		+ Contacts.DISPLAY_NAME + "!='')";
    
    public static class ViewHolder {
    	ImageView contactPhotoImageView, contactStarredImageView;
    	TextView contactNameTextView, contactTimesContactedTextView, contactLastTimeContactedTextView;
    };
    
    public final static int CONTACT_VIEW_TYPES     = 2;
    public final static int CONTACT_VIEW_TYPE_ODD  = 0;
    public final static int CONTACT_VIEW_TYPE_EVEN = 1;

	public ContactAdapter(Activity context) {
		this.context = context;
		inflater = (LayoutInflater)context.getLayoutInflater();
		data = new ArrayList<Contact>();
		initData();
	}
	
	public void initData() {
		context.getLoaderManager().initLoader(Constants.BASIC_DETAILS_QUERY, null, this);
	}
	
	public void resetData(String searchCriteria) {
		this.searchCriteria = searchCriteria;
		context.getLoaderManager().restartLoader(Constants.BASIC_DETAILS_QUERY, null, this);
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
		View contactView;
		ViewHolder viewHolder;
		Contact contact = data.get(position);
		if (convertView == null) {
			if (position % 2 == 0) {
				contactView = inflater.inflate(R.layout.contact_view_even, parent, false);
			} else {
				contactView = inflater.inflate(R.layout.contact_view_odd, parent, false);
			}
			viewHolder = new ViewHolder();
			viewHolder.contactPhotoImageView = (ImageView)contactView.findViewById(R.id.contact_photo_image_view);
			viewHolder.contactNameTextView = (TextView)contactView.findViewById(R.id.contact_name_text_view);
			viewHolder.contactTimesContactedTextView = (TextView)contactView.findViewById(R.id.contact_times_contacted_text_view);
			viewHolder.contactLastTimeContactedTextView = (TextView)contactView.findViewById(R.id.contact_last_time_contacted_text_view);
			viewHolder.contactStarredImageView = (ImageView)contactView.findViewById(R.id.contact_starred_image_view);
			contactView.setTag(viewHolder);
		} else {
			contactView = convertView;
		}
		
		if (getCheckedItemPosition() == position) {
			contactView.setBackgroundColor(context.getResources().getColor(R.color.light_blue));
		} else {
			contactView.setBackgroundColor(context.getResources().getColor(R.color.light_gray));
		}
		
		viewHolder = (ViewHolder)contactView.getTag();

		AssetFileDescriptor assetFileDescriptor = null;
		try {
			Uri contactPhotoUri = contact.getPhoto();
			if (contactPhotoUri == Uri.EMPTY) {
				viewHolder.contactPhotoImageView.setImageResource(R.drawable.contact_photo);
			} else {
				assetFileDescriptor = context.getContentResolver().openAssetFileDescriptor(contactPhotoUri, "r");
				FileDescriptor fileDescriptor = assetFileDescriptor.getFileDescriptor();
				if (fileDescriptor != null) {
					viewHolder.contactPhotoImageView.setImageBitmap(BitmapFactory.decodeFileDescriptor(fileDescriptor, null, null));
				}
			}
		} catch (FileNotFoundException fileNotFoundException) {
			Log.e(Constants.TAG, "An exception has occurred: "+fileNotFoundException.getMessage());
			if (Constants.DEBUG) {
				fileNotFoundException.printStackTrace();
			}
		} finally {
			if (assetFileDescriptor != null) {
				try {
					assetFileDescriptor.close();
				} catch (IOException ioException) {
					Log.e(Constants.TAG, "An exception has occurred: "+ioException.getMessage());
					if (Constants.DEBUG) {
						ioException.printStackTrace();
					}					
				}
			}
		}

		viewHolder.contactNameTextView.setText(contact.getName());
		viewHolder.contactTimesContactedTextView.setText(context.getResources().getString(R.string.times_contacted)+" "+contact.getTimesContacted());
		long value = contact.getLastTimeContacted();
		if (value != 0) {
			viewHolder.contactLastTimeContactedTextView.setText(context.getResources().getString(R.string.last_time_contacted)+" "+Utilities.displayDateAndTime(value));
		} else {
			viewHolder.contactLastTimeContactedTextView.setText(context.getResources().getString(R.string.last_time_contacted)+" -");
		}
		if (contact.getStarred() == 1) {
			viewHolder.contactStarredImageView.setImageResource(R.drawable.star_set);
		} else {
			viewHolder.contactStarredImageView.setImageResource(R.drawable.star_unset);
		}
		return contactView;
	}
	
	@Override
	public int getViewTypeCount() {
		return CONTACT_VIEW_TYPES;
	}
	
	@Override
	public int getItemViewType(int position) {
		if (position % 2 == 0)
			return CONTACT_VIEW_TYPE_ODD;
		return CONTACT_VIEW_TYPE_EVEN;
	}

	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		Uri contentUri;
				
		if (searchCriteria != null && !searchCriteria.isEmpty()) {
			contentUri = Uri.withAppendedPath(
					Contacts.CONTENT_FILTER_URI,
					Uri.encode(searchCriteria));
		} else {
			contentUri = Contacts.CONTENT_URI;
		}
				
		return new CursorLoader(context,
				contentUri, 
				PROJECTION, 
				SELECTION, 
				null, 
				null);
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
		data = new ArrayList<Contact>();
		if (cursor.getCount() != 0) {
			cursor.moveToFirst();
			do {
				Contact contact = new Contact();
				String photoThumbnailUri = cursor.getString(cursor.getColumnIndex(PROJECTION[PHOTO_THUMBNAIL_URI_INDEX]));
				if (photoThumbnailUri != null) {
					contact.setPhoto(Uri.parse(photoThumbnailUri));
				}
				contact.setName(cursor.getString(cursor.getColumnIndex(PROJECTION[DISPLAY_NAME_INDEX])));
				contact.setStarred(cursor.getInt(cursor.getColumnIndex(PROJECTION[STARRED_INDEX])));
				contact.setTimesContacted(cursor.getInt(cursor.getColumnIndex(PROJECTION[TIMES_CONTACTED_INDEX])));
				contact.setLastTimeContacted(cursor.getLong(cursor.getColumnIndex(PROJECTION[LAST_TIME_CONTACTED_INDEX])));
				data.add(contact);
			} while (cursor.moveToNext());
		}
		this.cursor = cursor;
		notifyDataSetChanged();
	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		data = new ArrayList<Contact>();
		this.cursor = null;
	}
	
	public ArrayList<Contact> getData() {
		return data;
	}
	
	public Contact getSelectedContact() {
		return data.get(checkedItemPosition);
	}
	
	public String getSearchCriteria() {
		return searchCriteria;
	}
	
	public Cursor getCursor() {
		return cursor;
	}
	
	public void setCheckedItemPosition(int checkedItemPosition) {
		this.checkedItemPosition = checkedItemPosition;
	}	
	
	public int getCheckedItemPosition() {
		return checkedItemPosition;
	}
	
	public int getContactId() {
		
		if (cursor != null && checkedItemPosition >= 0) {
			cursor.moveToPosition(checkedItemPosition);
			return cursor.getInt(ID_INDEX);
		}
		return -1;
	}
	
	public String getContactLookupKey() {
		
		if (cursor != null && checkedItemPosition >= 0) {
			cursor.moveToPosition(checkedItemPosition);
			return cursor.getString(LOOKUP_KEY_INDEX);
		}
		return null;
	}
	
	public Uri getContactLookupUri() {
		
		if (cursor != null && checkedItemPosition >= 0) {
			cursor.moveToPosition(checkedItemPosition);
			int id = cursor.getInt(ID_INDEX);
			String lookupKey = cursor.getString(LOOKUP_KEY_INDEX);
			return Contacts.getLookupUri(id, lookupKey);
		}
		return Uri.EMPTY;
	}

}
