package ro.pub.cs.systems.pdsd.lab05.addressbook.view;

import ro.pub.cs.systems.pdsd.lab05.addressbook.R;
import ro.pub.cs.systems.pdsd.lab05.addressbook.controller.ContactAdapter;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

public class ContactBasicDetailsFragment extends Fragment {
	
    private ListView addressBookListView;
	private ContactAdapter addressBookContactAdapter;
	
	private EditText searchFieldEditText;
	private String searchCriteria;
	
	private EditTextListener searchFieldEditTextListener = new EditTextListener();
	
	private class EditTextListener implements TextWatcher {
		
		@Override
		public void onTextChanged(CharSequence text, int start, int before, int count) {
			searchCriteria = text.toString();
			addressBookContactAdapter.setCheckedItemPosition(-1);
			FragmentManager fragmentManager = getActivity().getFragmentManager();
			ContactAdditionalDetailsFragment contactAdditionalDetailsFragment = (ContactAdditionalDetailsFragment)fragmentManager.findFragmentById(R.id.contact_additional_details);
			FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
			if (contactAdditionalDetailsFragment != null) {
				fragmentTransaction.remove(contactAdditionalDetailsFragment);
			}
			fragmentTransaction.commit();			
			addressBookContactAdapter.resetData(searchCriteria);
		}

		@Override
		public void beforeTextChanged(CharSequence text, int start, int count, int after) {
		}

		@Override
		public void afterTextChanged(Editable view) {
		}
	}
	
	private ListViewItemClickListener addressBookListViewItemClickListener = new ListViewItemClickListener();
	
	private class ListViewItemClickListener implements AdapterView.OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			addressBookListView.setItemChecked(position, true);
			addressBookContactAdapter.setCheckedItemPosition(position);
			FragmentManager fragmentManager = getActivity().getFragmentManager();
			ContactAdditionalDetailsFragment contactAdditionalDetailsFragment = (ContactAdditionalDetailsFragment)fragmentManager.findFragmentById(R.id.contact_additional_details);
			FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
			if (contactAdditionalDetailsFragment != null) {
				fragmentTransaction.remove(contactAdditionalDetailsFragment);
			}
			
			fragmentTransaction.add(R.id.contact_additional_details, new ContactAdditionalDetailsFragment(addressBookContactAdapter.getContactLookupKey()));
			fragmentTransaction.commit();
		}		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle state) {
		return inflater.inflate(R.layout.fragment_contact_basic_details, parent, false);
	}
	
	@Override
	public void onActivityCreated(Bundle state) {
		super.onActivityCreated(state);
		addressBookListView = (ListView)getActivity().findViewById(R.id.address_book_list_view);
		addressBookListView.setOnItemClickListener(addressBookListViewItemClickListener);
		addressBookContactAdapter = new ContactAdapter(getActivity());
		addressBookListView.setAdapter(addressBookContactAdapter);
		
		searchFieldEditText = (EditText)getActivity().findViewById(R.id.search_field_edit_text);
		searchFieldEditText.addTextChangedListener(searchFieldEditTextListener);
	}

}
