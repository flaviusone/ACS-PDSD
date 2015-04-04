package ro.pub.cs.systems.pdsd.lab05.addressbook.view;

import java.util.ArrayList;

import ro.pub.cs.systems.pdsd.lab05.addressbook.R;
import ro.pub.cs.systems.pdsd.lab05.addressbook.entities.EmailAddress;
import ro.pub.cs.systems.pdsd.lab05.addressbook.entities.PhoneNumber;
import ro.pub.cs.systems.pdsd.lab05.addressbook.general.Constants;
import ro.pub.cs.systems.pdsd.lab05.addressbook.general.Utilities;
import android.app.Activity;
import android.content.ContentProviderOperation;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.RawContacts;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

public class ContactsManagerActivity extends Activity {
	
	final ArrayList<View> contactPhoneNumberControls = new ArrayList<View>();
	final ArrayList<View> contactEmailAddressControls = new ArrayList<View>();

	public LinearLayout createPhoneNumberLayout(String phoneNumber, int phoneNumberType) {
		LinearLayout container = new LinearLayout(ContactsManagerActivity.this);
		
		EditText phoneNumberEditText = new EditText(ContactsManagerActivity.this);
		phoneNumberEditText.setInputType(InputType.TYPE_CLASS_PHONE);
		phoneNumberEditText.setFocusable(true);
		if (phoneNumber != null) {
			phoneNumberEditText.setText(phoneNumber);
		}
		LinearLayout.LayoutParams phoneNumberEditTextLayoutParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT);
		phoneNumberEditTextLayoutParams.weight = 3;
		phoneNumberEditTextLayoutParams.gravity = Gravity.CENTER;
		container.addView(phoneNumberEditText, phoneNumberEditTextLayoutParams);
		contactPhoneNumberControls.add(phoneNumberEditText);
		
		Spinner phoneNumberTypeSpinner = new Spinner(ContactsManagerActivity.this);
		ArrayAdapter<String> phoneNumberTypeSpinnerAdapter = new ArrayAdapter<String>(ContactsManagerActivity.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.phone_number_types));
		phoneNumberTypeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		phoneNumberTypeSpinner.setAdapter(phoneNumberTypeSpinnerAdapter);
		if (phoneNumberType != -1) {
			phoneNumberTypeSpinner.setSelection(phoneNumberType);
		}
		LinearLayout.LayoutParams phoneNumberTypeSpinnerLayoutParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT);
		phoneNumberTypeSpinnerLayoutParams.weight = 2;
		phoneNumberTypeSpinnerLayoutParams.gravity = Gravity.CENTER;
		container.addView(phoneNumberTypeSpinner, phoneNumberTypeSpinnerLayoutParams);
		contactPhoneNumberControls.add(phoneNumberTypeSpinner);
		
		return container;
	}
	
	public LinearLayout createEmailAddressLayout(String emailAddress, int emailAddressType) {
		LinearLayout container = new LinearLayout(ContactsManagerActivity.this);
		
		EditText emailAddressEditText = new EditText(ContactsManagerActivity.this);
		emailAddressEditText.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
		emailAddressEditText.setFocusable(true);
		if (emailAddress != null) {
			emailAddressEditText.setText(emailAddress);
		}
		LinearLayout.LayoutParams phoneNumberEditTextLayoutParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT);
		phoneNumberEditTextLayoutParams.weight = 3;
		phoneNumberEditTextLayoutParams.gravity = Gravity.CENTER;
		container.addView(emailAddressEditText, phoneNumberEditTextLayoutParams);
		contactEmailAddressControls.add(emailAddressEditText);
		
		Spinner emailAddressTypeSpinner = new Spinner(ContactsManagerActivity.this);
		ArrayAdapter<String> emailAddressTypeSpinnerAdapter = new ArrayAdapter<String>(ContactsManagerActivity.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.email_addresses_types));
		emailAddressTypeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		emailAddressTypeSpinner.setAdapter(emailAddressTypeSpinnerAdapter);
		if (emailAddressType != -1) {
			emailAddressTypeSpinner.setSelection(emailAddressType);
		}
		LinearLayout.LayoutParams emailAddressTypeSpinnerLayoutParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT);
		emailAddressTypeSpinnerLayoutParams.weight = 2;
		emailAddressTypeSpinnerLayoutParams.gravity = Gravity.CENTER;
		container.addView(emailAddressTypeSpinner, emailAddressTypeSpinnerLayoutParams);
		contactEmailAddressControls.add(emailAddressTypeSpinner);
		
		return container;
	}
	
	@Override
	protected void onCreate(Bundle state) {
		super.onCreate(state);
		setContentView(R.layout.activity_contacts_manager);
		Intent intent = getIntent();
		Bundle extra = null;
		if (intent != null) {
			extra = intent.getExtras();
		}
		int operation = -1;
		if (extra != null) {
			operation = extra.getInt(Constants.OPERATION);
		}

		final EditText contactNameEditText = (EditText)findViewById(R.id.contact_name_edit_text);
		if (operation == Constants.OPERATION_UPDATE) {
			contactNameEditText.setText(extra.getString(Constants.CONTACT_NAME));
		}
		
		ArrayList<PhoneNumber> contactPhones = null;
		ArrayList<EmailAddress> contactEmails = null;
		
		if (operation == Constants.OPERATION_UPDATE) {
			contactPhones = extra.getParcelableArrayList(Constants.CONTACT_PHONES);
			contactEmails = extra.getParcelableArrayList(Constants.CONTACT_EMAILS);
		}
		
		final ArrayList<PhoneNumber> finalContactPhones = contactPhones;
		final ArrayList<EmailAddress> finalContactEmails = contactEmails;
		
		EditText contactPhoneNumberEditText = (EditText)findViewById(R.id.contact_phone_number_edit_text);
		if (contactPhones != null) {
			contactPhoneNumberEditText.setText(contactPhones.get(0).getValue());
		}
		contactPhoneNumberControls.add(contactPhoneNumberEditText);
		Spinner contactPhoneNumberTypeSpinner = (Spinner)findViewById(R.id.contact_phone_number_type_spinner);
		if (contactPhones != null) {
			contactPhoneNumberTypeSpinner.setSelection(contactPhones.get(0).getType());
		}
		contactPhoneNumberControls.add(contactPhoneNumberTypeSpinner);
		
		final LinearLayout phoneNumberLayout = (LinearLayout)findViewById(R.id.contact_phone_number_layout);
		Button addAnotherPhoneNumberButton = (Button)findViewById(R.id.add_another_phone_number_button);
		addAnotherPhoneNumberButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				LinearLayout.LayoutParams phoneNumberLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
				phoneNumberLayout.addView(createPhoneNumberLayout(null, -1), phoneNumberLayoutParams);
			}
			
		});
		if (operation == Constants.OPERATION_UPDATE) {
			addAnotherPhoneNumberButton.setEnabled(false);
		}
		
		if (contactPhones != null && contactPhones.size() > 1) {
			for (int index = 1; index < contactPhones.size(); index++) {
				LinearLayout.LayoutParams phoneNumberLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
				phoneNumberLayout.addView(createPhoneNumberLayout(contactPhones.get(index).getValue(), contactPhones.get(index).getType()), phoneNumberLayoutParams);				
			}
		}
		
		EditText contactEmailAddressEditText = (EditText)findViewById(R.id.contact_email_address_edit_text);
		if (contactEmails != null) {
			contactEmailAddressEditText.setText(contactEmails.get(0).getValue());
		}		
		contactEmailAddressControls.add(contactEmailAddressEditText);
		Spinner contactEmailAddressTypeSpinner = (Spinner)findViewById(R.id.contact_email_address_type_spinner);
		if (contactEmails != null) {
			contactEmailAddressTypeSpinner.setSelection(contactEmails.get(0).getType());
		}
		contactEmailAddressControls.add(contactEmailAddressTypeSpinner);		
		
		final LinearLayout emailAddressLayout = (LinearLayout)findViewById(R.id.contact_email_address_layout);
		Button addAnotherEmailAddressButton = (Button)findViewById(R.id.add_another_email_address_button);
		addAnotherEmailAddressButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				LinearLayout.LayoutParams emailAddressLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
				emailAddressLayout.addView(createEmailAddressLayout(null, -1), emailAddressLayoutParams);
			}
			
		});
		if (operation == Constants.OPERATION_UPDATE) {
			addAnotherEmailAddressButton.setEnabled(false);
		}
		
		if (contactEmails != null && contactEmails.size() > 1) {
			for (int index = 1; index < contactEmails.size(); index++) {
				LinearLayout.LayoutParams emailAddressLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
				emailAddressLayout.addView(createEmailAddressLayout(contactEmails.get(index).getValue(), contactEmails.get(index).getType()), emailAddressLayoutParams);				
			}
		}		
		
		Button saveButton = (Button)findViewById(R.id.save_button);
		saveButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				ArrayList<ContentProviderOperation> contentProviderOperations = null;
				int index;
				switch(getIntent().getExtras().getInt(Constants.OPERATION)) {
					case Constants.OPERATION_INSERT:
						contentProviderOperations = new ArrayList<ContentProviderOperation>();
						contentProviderOperations.add(ContentProviderOperation
								.newInsert(ContactsContract.RawContacts.CONTENT_URI)
								.withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
								.withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
								.build());
						contentProviderOperations.add(ContentProviderOperation
								.newInsert(ContactsContract.Data.CONTENT_URI)
								.withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
								.withValue(
										ContactsContract.Data.MIMETYPE,
										ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
								.withValue(
										ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME,
										contactNameEditText.getText().toString()).build());
						index = 0;
						while (index < contactPhoneNumberControls.size()) {
							contentProviderOperations.add(ContentProviderOperation
									.newInsert(ContactsContract.Data.CONTENT_URI)
									.withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
									.withValue(
											ContactsContract.Data.MIMETYPE,
											ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
									.withValue(
											ContactsContract.CommonDataKinds.Phone.NUMBER,
											((EditText)contactPhoneNumberControls.get(index)).getText().toString())
									.withValue(
											ContactsContract.CommonDataKinds.Phone.TYPE,
											Utilities.convertIndexToPhoneType(((Spinner)contactPhoneNumberControls.get(index+1)).getSelectedItemPosition()))
									.build());
							index += 2;
						}
						index = 0;
						while (index < contactEmailAddressControls.size()) {
							contentProviderOperations.add(ContentProviderOperation
									.newInsert(ContactsContract.Data.CONTENT_URI)
									.withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
									.withValue(
											ContactsContract.Data.MIMETYPE,
											ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE)
									.withValue(
											ContactsContract.CommonDataKinds.Email.ADDRESS,
											((EditText)contactEmailAddressControls.get(index)).getText().toString())
									.withValue(
											ContactsContract.CommonDataKinds.Email.TYPE,
											Utilities.convertIndexToEmailType(((Spinner)contactEmailAddressControls.get(index+1)).getSelectedItemPosition()))
									.build());
							index += 2;
						}						
						break;
					case Constants.OPERATION_UPDATE:
						contentProviderOperations = new ArrayList<ContentProviderOperation>();
						contentProviderOperations.add(ContentProviderOperation
								.newUpdate(ContactsContract.Data.CONTENT_URI)
								.withSelection(
									RawContacts.CONTACT_ID + " = ? AND "
									+ ContactsContract.Data.MIMETYPE + " = '" + ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE + "'", 
									new String[] {
											getIntent().getStringExtra(Constants.CONTACT_ID)
									})
								.withValue(
										ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME,
										contactNameEditText.getText().toString())
								.build());
						index = 0;
						while ((index < contactPhoneNumberControls.size()) && (finalContactPhones != null)) {
							contentProviderOperations.add(ContentProviderOperation
									.newUpdate(ContactsContract.Data.CONTENT_URI)
									.withSelection(
										RawContacts.CONTACT_ID + " = ? AND "
										+ ContactsContract.Data.MIMETYPE + " = '" + ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE + "' AND "
										+ ContactsContract.CommonDataKinds.Phone.NUMBER + " = ? AND "
										+ ContactsContract.CommonDataKinds.Phone.TYPE + " = ?", 
										new String[] {
												getIntent().getStringExtra(Constants.CONTACT_ID),
												finalContactPhones.get(index/2).getValue(),
												String.valueOf(Utilities.convertIndexToPhoneType(finalContactPhones.get(index/2).getType()))
												
									})
									.withValue(
											ContactsContract.CommonDataKinds.Phone.NUMBER,
											((EditText)contactPhoneNumberControls.get(index)).getText().toString())
									.withValue(
											ContactsContract.CommonDataKinds.Phone.TYPE,
											Utilities.convertIndexToPhoneType(((Spinner)contactPhoneNumberControls.get(index+1)).getSelectedItemPosition()))
									.build());
							index += 2;
						}
						index = 0;
						while ((index < contactEmailAddressControls.size()) && (finalContactEmails != null)) {
							contentProviderOperations.add(ContentProviderOperation
									.newUpdate(ContactsContract.Data.CONTENT_URI)
									.withSelection(
										RawContacts.CONTACT_ID + " = ? AND "
										+ ContactsContract.Data.MIMETYPE + " = '" + ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE + "' AND "
										+ ContactsContract.CommonDataKinds.Email.ADDRESS + " = ? AND "
										+ ContactsContract.CommonDataKinds.Email.TYPE + " = ?", 
										new String[] {
												getIntent().getStringExtra(Constants.CONTACT_ID),
												finalContactEmails.get(index/2).getValue(),
												String.valueOf(Utilities.convertIndexToEmailType(finalContactEmails.get(index/2).getType()))
										})
									.withValue(
											ContactsContract.CommonDataKinds.Email.ADDRESS,
											((EditText)contactEmailAddressControls.get(index)).getText().toString())
									.withValue(
											ContactsContract.CommonDataKinds.Email.TYPE,
											Utilities.convertIndexToEmailType(((Spinner)contactEmailAddressControls.get(index+1)).getSelectedItemPosition()))
									.build());
							index += 2;
						}						
						break;						
				}
				
				try {
					getContentResolver().applyBatch(ContactsContract.AUTHORITY, contentProviderOperations);
					contentProviderOperations = null;
					setResult(Activity.RESULT_OK, new Intent());
				} catch (Exception exception) {
					Log.e(Constants.TAG, "An exception has occurred: "+exception.getMessage());
					if (Constants.DEBUG) {
						exception.printStackTrace();
					}
					setResult(Activity.RESULT_CANCELED, new Intent());
				}
				finish();				
			}
			
		});
		
		Button cancelButton = (Button)findViewById(R.id.cancel_button);
		cancelButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				setResult(Activity.RESULT_CANCELED, new Intent());
				finish();				
			}
			
		});
	}

}
