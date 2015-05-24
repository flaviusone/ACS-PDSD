package ro.pub.cs.systems.pdsd.lab05.addressbook.view;

import ro.pub.cs.systems.pdsd.lab05.addressbook.R;
import ro.pub.cs.systems.pdsd.lab05.addressbook.general.Constants;
import android.app.Fragment;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Data;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ContactAdditionalDetailsFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
	
	private static final String[] PROJECTION = {
		Data._ID,
		Data.MIMETYPE,
		Data.DATA1,
		Data.DATA2
	};
	
	private static final int MIMETYPE_INDEX = 1;
	private static final int DATA1_INDEX = 2;
	private static final int DATA2_INDEX = 3;

	private static final String SELECTION = Data.LOOKUP_KEY + " = ?"
			+ " AND "
		    + "(" + Data.MIMETYPE + " = " + "'" + Phone.CONTENT_ITEM_TYPE + "'"
			+ " OR " + Data.MIMETYPE + " = " + "'" + Email.CONTENT_ITEM_TYPE + "')";
	private String[] selectionArguments;
	
	private static final String SORT_ORDER = Data.MIMETYPE;
	
	TextView contactPhonesTextView;
	TextView contactEmailsTextView;
	
	public ContactAdditionalDetailsFragment() {
		super();
		this.selectionArguments = new String[] { };
	}	

	public ContactAdditionalDetailsFragment(String selectionCriteria) {
		this.selectionArguments = new String[] { selectionCriteria };
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle state) {
		return inflater.inflate(R.layout.fragment_contact_additional_details, parent, false);
	}
	
	@Override
	public void onActivityCreated(Bundle state) {
		super.onActivityCreated(state);
		contactPhonesTextView = (TextView)getActivity().findViewById(R.id.contact_phones_text_view);
		contactEmailsTextView = (TextView)getActivity().findViewById(R.id.contact_emails_text_view);
		refresh();
	}

	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		return new CursorLoader(
				getActivity(),
				Data.CONTENT_URI,
				PROJECTION,
				SELECTION,
				selectionArguments,
				SORT_ORDER);
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
		if (cursor.getCount() != 0) {
			cursor.moveToFirst();
			StringBuffer phoneContent = new StringBuffer();
			StringBuffer emailContent = new StringBuffer();
			do {
				String data1 = cursor.getString(cursor.getColumnIndex(PROJECTION[DATA1_INDEX]));
				int data2 = cursor.getInt(cursor.getColumnIndex(PROJECTION[DATA2_INDEX]));
				String type = new String();
				if(cursor.getString(cursor.getColumnIndex(PROJECTION[MIMETYPE_INDEX])).equals(Phone.CONTENT_ITEM_TYPE)) {
					switch(data2) {
						case Phone.TYPE_HOME:
							type = getActivity().getResources().getString(R.string.home);
							break;
						case Phone.TYPE_MOBILE:
							type = getActivity().getResources().getString(R.string.mobile);
							break;
						case Phone.TYPE_WORK:
							type = getActivity().getResources().getString(R.string.work);
							break;
						default:
							type = getActivity().getResources().getString(R.string.other);
							break;
					}
					phoneContent.append(type+" "+data1+"\n");
				}
				if(cursor.getString(cursor.getColumnIndex(PROJECTION[MIMETYPE_INDEX])).equals(Email.CONTENT_ITEM_TYPE)) {
					switch(data2) {
						case Email.TYPE_HOME:
							type = getActivity().getResources().getString(R.string.home);
							break;
						case Email.TYPE_MOBILE:
							type = getActivity().getResources().getString(R.string.mobile);
							break;
						case Email.TYPE_WORK:
							type = getActivity().getResources().getString(R.string.work);
							break;
						default:
							type = getActivity().getResources().getString(R.string.other);
							break;
					}
					emailContent.append(type+" "+data1+"\n");
				}
			} while (cursor.moveToNext());
			contactPhonesTextView.setText(phoneContent.toString());
			contactEmailsTextView.setText(emailContent.toString());
			if (contactPhonesTextView.getText() == null || contactPhonesTextView.getText().toString().isEmpty()) {
				contactPhonesTextView.setText(getActivity().getResources().getString(R.string.empty));
				contactPhonesTextView.setGravity(Gravity.CENTER);
			}
			if (contactEmailsTextView.getText() == null || contactEmailsTextView.getText().toString().isEmpty()) {
				contactEmailsTextView.setText(getActivity().getResources().getString(R.string.empty));
				contactEmailsTextView.setGravity(Gravity.CENTER);
			}
		}
	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
	}
	
	public void refresh() {
		getLoaderManager().initLoader(Constants.ADDITIONAL_DETAILS_QUERY, null, this);
	}

}
