package ro.pub.cs.systems.pdsd.lab05.addressbook.view;

import java.util.ArrayList;

import ro.pub.cs.systems.pdsd.lab05.addressbook.R;
import ro.pub.cs.systems.pdsd.lab05.addressbook.controller.ContactAdapter;
import ro.pub.cs.systems.pdsd.lab05.addressbook.entities.EmailAddress;
import ro.pub.cs.systems.pdsd.lab05.addressbook.entities.PhoneNumber;
import ro.pub.cs.systems.pdsd.lab05.addressbook.general.Constants;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ContentProviderOperation;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.provider.ContactsContract.RawContacts;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class AddressBookActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_address_book);
		
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.add(R.id.contact_basic_details, new ContactBasicDetailsFragment());
		fragmentTransaction.commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.address_book, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		ListView addressBookListView = (ListView)findViewById(R.id.address_book_list_view);
		ContactAdapter addressBookContactAdapter = (ContactAdapter)addressBookListView.getAdapter();
		
		int id = item.getItemId();
		if (id == R.id.action_insert) {
			Intent intentAdd = new Intent(this, ContactsManagerActivity.class);
			intentAdd.putExtra(Constants.OPERATION, Constants.OPERATION_INSERT);
			startActivityForResult(intentAdd, Constants.OPERATION_INSERT);
			return true;
		}
		if (id == R.id.action_update) {
			if (addressBookContactAdapter.getCheckedItemPosition() == -1) {
				Toast.makeText(this, "There is no selection made", Toast.LENGTH_LONG).show();
				return false;
			}
			Intent intentUpdate = new Intent(this, ContactsManagerActivity.class);
			intentUpdate.putExtra(Constants.OPERATION, Constants.OPERATION_UPDATE);
			intentUpdate.putExtra(Constants.CONTACT_NAME, addressBookContactAdapter.getSelectedContact().getName());
			int index;
			TextView contactPhonesTextView = (TextView)findViewById(R.id.contact_phones_text_view);
			String contactPhoneNumbers = contactPhonesTextView.getText().toString();
			String[] contactPhoneNumbersParts = contactPhoneNumbers.replaceAll(" ", "").split("[:\n]");
			if ((contactPhoneNumbersParts.length % 2) == 0) {
				ArrayList<PhoneNumber> contactPhones = new ArrayList<PhoneNumber>();
				index = 0;
				while (index < contactPhoneNumbersParts.length) {
					PhoneNumber contactPhoneNumber = new PhoneNumber(contactPhoneNumbersParts[index+1], contactPhoneNumbersParts[index]);
					contactPhones.add(contactPhoneNumber);
					index += 2;
				}
				intentUpdate.putExtra(Constants.CONTACT_PHONES, contactPhones);
				
			}
			
			TextView contactEmailsTextView = (TextView)findViewById(R.id.contact_emails_text_view);
			String contactEmailAddresses = contactEmailsTextView.getText().toString();
			String[] contactEmailAddressesParts = contactEmailAddresses.replaceAll(" ", "").split("[:\n]");
			if ((contactEmailAddressesParts.length % 2) == 0) {
				ArrayList<EmailAddress> contactEmails = new ArrayList<EmailAddress>();
				index = 0;
				while (index < contactEmailAddressesParts.length) {
					EmailAddress contactEmailAddress = new EmailAddress(contactEmailAddressesParts[index+1], contactEmailAddressesParts[index]);
					contactEmails.add(contactEmailAddress);
					index += 2;
				}
				intentUpdate.putExtra(Constants.CONTACT_EMAILS, contactEmails);
			}
			intentUpdate.putExtra(Constants.CONTACT_ID, String.valueOf(addressBookContactAdapter.getContactId()));
			startActivityForResult(intentUpdate, Constants.OPERATION_UPDATE);			
			return true;
		}
		if (id == R.id.action_delete) {
			ArrayList<ContentProviderOperation> contentProviderOperations = new ArrayList<ContentProviderOperation>();
			 
			contentProviderOperations.add(ContentProviderOperation
					.newDelete(ContactsContract.RawContacts.CONTENT_URI)
					.withSelection(
							RawContacts.CONTACT_ID + " = ?", 
							new String[] {
									String.valueOf(addressBookContactAdapter.getContactId())
							})
					.build()
			);
			try {
				getContentResolver().applyBatch(ContactsContract.AUTHORITY, contentProviderOperations);
			} catch (RemoteException remoteException) {
				Log.e(Constants.TAG, "An exception has occurred: "+remoteException.getMessage());
				if (Constants.DEBUG) {
					remoteException.printStackTrace();
				}
				return false;
			} catch (OperationApplicationException operationApplicationException) {
				Log.e(Constants.TAG, "An exception has occurred: "+operationApplicationException.getMessage());
				if (Constants.DEBUG) {
					operationApplicationException.printStackTrace();
				}
				return false;
			}
			FragmentManager fragmentManager = getFragmentManager();
			ContactAdditionalDetailsFragment contactAdditionalDetailsFragment = (ContactAdditionalDetailsFragment)fragmentManager.findFragmentById(R.id.contact_additional_details);
			if (contactAdditionalDetailsFragment != null) {
				FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
				fragmentTransaction.remove(contactAdditionalDetailsFragment);
				fragmentTransaction.commit();
				addressBookContactAdapter.setCheckedItemPosition(-1);
			}			
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		switch(requestCode) {
			case Constants.OPERATION_INSERT:
				if (resultCode == Activity.RESULT_OK) {
					Toast.makeText(this, "Insert operation succeeded", Toast.LENGTH_LONG).show();
				} else {
					Toast.makeText(this, "Insert operation failed", Toast.LENGTH_LONG).show();
				}
				break;
			case Constants.OPERATION_UPDATE:
				FragmentManager fragmentManager = getFragmentManager();
				ContactAdditionalDetailsFragment contactAdditionalDetailsFragment = (ContactAdditionalDetailsFragment)fragmentManager.findFragmentById(R.id.contact_additional_details);
				if (contactAdditionalDetailsFragment != null) {
					contactAdditionalDetailsFragment.refresh();
				}
				if (resultCode == Activity.RESULT_OK) {
					Toast.makeText(this, "Update operation succeeded", Toast.LENGTH_LONG).show();
				} else {
					Toast.makeText(this, "Update operation failed", Toast.LENGTH_LONG).show();
				}
				break;
		}
	}
}
