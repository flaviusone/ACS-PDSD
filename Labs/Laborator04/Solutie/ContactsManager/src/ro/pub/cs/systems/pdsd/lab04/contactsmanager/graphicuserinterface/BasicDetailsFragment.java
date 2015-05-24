package ro.pub.cs.systems.pdsd.lab04.contactsmanager.graphicuserinterface;

import java.util.ArrayList;

import ro.pub.cs.systems.pdsd.lab04.contactsmanager.R;
import ro.pub.cs.systems.pdsd.lab04.contactsmanager.general.Constants;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BasicDetailsFragment extends Fragment {
	
	private ButtonOnClickListener buttonOnClickListener = new ButtonOnClickListener();
	
	private class ButtonOnClickListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			switch(v.getId()) {
				case R.id.additional_fields_button:
					FragmentManager fragmentManager = getActivity().getFragmentManager();
					FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
					AdditionalDetailsFragment additionalDetailsFragment = (AdditionalDetailsFragment)fragmentManager.findFragmentById(R.id.containerBottom);
					if (additionalDetailsFragment == null) {
						fragmentTransaction.add(R.id.containerBottom, new AdditionalDetailsFragment());
						((Button)v).setText(getActivity().getResources().getString(R.string.hide_additional_fields));
						fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
					} else {
						fragmentTransaction.remove(additionalDetailsFragment);
						((Button)v).setText(getActivity().getResources().getString(R.string.show_additional_fields));
						fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_EXIT_MASK);
					}
					fragmentTransaction.commit();
					break;
				case R.id.save_button:
					EditText nameEditText = (EditText)getActivity().findViewById(R.id.name_edit_text);
					EditText phoneEditText = (EditText)getActivity().findViewById(R.id.phone_edit_text);
					EditText emailEditText = (EditText)getActivity().findViewById(R.id.email_edit_text);
					EditText addressEditText = (EditText)getActivity().findViewById(R.id.address_edit_text);
					EditText jobTitleEditText = (EditText)getActivity().findViewById(R.id.job_title_edit_text);
					EditText companyEditText = (EditText)getActivity().findViewById(R.id.company_edit_text);
					EditText websiteEditText = (EditText)getActivity().findViewById(R.id.website_edit_text);
					EditText imEditText = (EditText)getActivity().findViewById(R.id.im_edit_text);
					String name = nameEditText.getText().toString();
					String phone = phoneEditText.getText().toString();
					String email = emailEditText.getText().toString();
					String address = addressEditText.getText().toString();
					String jobTitle = null;
					String company = null;
					String website = null;
					String im = null;
					if (jobTitleEditText != null) {
						jobTitle = jobTitleEditText.getText().toString();
					}
					if (companyEditText != null) {
						company = companyEditText.getText().toString();
					}					
					if (websiteEditText != null) {
						website = websiteEditText.getText().toString();
					}
					if (imEditText != null) {
						im = imEditText.getText().toString();
					}					

					Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);

					intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
					if (name != null) {
						intent.putExtra(ContactsContract.Intents.Insert.NAME, name);
					}
					if (phone != null) {
					intent.putExtra(ContactsContract.Intents.Insert.PHONE, phone);
					}
					if (email != null) {
					intent.putExtra(ContactsContract.Intents.Insert.EMAIL, email);
					}
					if (address != null) {
					intent.putExtra(ContactsContract.Intents.Insert.POSTAL, address);
					}
					if (jobTitle != null) {
						intent.putExtra(ContactsContract.Intents.Insert.JOB_TITLE, jobTitle);
					}
					if (company != null) {
						intent.putExtra(ContactsContract.Intents.Insert.COMPANY, company);
					}

					ArrayList<ContentValues> contactData = new ArrayList<ContentValues>();
					
					if (website != null) {
						ContentValues websiteRow = new ContentValues();
						websiteRow.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Website.CONTENT_ITEM_TYPE);
						websiteRow.put(ContactsContract.CommonDataKinds.Website.URL, website);
						contactData.add(websiteRow);
					}
					
					if (im != null) {
						ContentValues imRow = new ContentValues();
						imRow.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Im.CONTENT_ITEM_TYPE);
						imRow.put(ContactsContract.CommonDataKinds.Im.DATA, im);
						contactData.add(imRow);
					}

					intent.putParcelableArrayListExtra(ContactsContract.Intents.Insert.DATA, contactData);
					getActivity().startActivityForResult(intent, Constants.CONTACTS_MANAGER_REQUEST_CODE);
					break;
				case R.id.cancel_button:
					getActivity().setResult(Activity.RESULT_CANCELED, new Intent());
					getActivity().finish();
					break;
			}
			
		}
		
	}
	
	@Override
	public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle state) {
		return layoutInflater.inflate(R.layout.fragment_basic_details, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Button additionalDetailsButton = (Button)getActivity().findViewById(R.id.additional_fields_button);
		additionalDetailsButton.setOnClickListener(buttonOnClickListener);
		Button saveButton = (Button)getActivity().findViewById(R.id.save_button);
		saveButton.setOnClickListener(buttonOnClickListener);
		Button cancelButton = (Button)getActivity().findViewById(R.id.cancel_button);
		cancelButton.setOnClickListener(buttonOnClickListener);
		EditText phoneEditText = (EditText)getActivity().findViewById(R.id.phone_edit_text);
		Intent intent = getActivity().getIntent();
		if (intent != null) {
			String phone = intent.getStringExtra(Constants.PHONE_NUMBER_KEY);
			if (phone != null) {
				phoneEditText.setText(phone);
			} else {
				Activity activity = getActivity();
				Toast.makeText(activity, activity.getResources().getString(R.string.phone_error), Toast.LENGTH_LONG).show();
			}
		}
	}

}
