package ro.pub.cs.systems.pdsd.lab08.googlecloudmessaging.view;

import ro.pub.cs.systems.pdsd.lab08.googlecloudmessaging.R;
import ro.pub.cs.systems.pdsd.lab08.googlecloudmessaging.general.Constants;
import ro.pub.cs.systems.pdsd.lab08.googlecloudmessaging.general.Utilities;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class UnregisteredFragment extends Fragment {
	
	private EditText usernameEditText, emailEditText;
	private Button sendRegistrationIdButton;
	private TextView registrationIdTextView;
	
	private SendRegistrationIdButtonClickListener sendRegistrationIdButtonClickListener = new SendRegistrationIdButtonClickListener();
	protected class SendRegistrationIdButtonClickListener implements Button.OnClickListener {
		
		@Override
		public void onClick(View view) {
			new AsyncTask<Object, Integer, Integer>() {
				@Override
				protected Integer doInBackground(Object... params) {

					try {
						
						// TODO: exercise 7
						// - create a HttpClient instance
						// - create a HttpPost instance according to the address of the device registration service
						// - create a the list of request parameters
						//  * Constants.USERNAME
						//  * Constants.EMAIL
						//  * Constants.REGISTRATION_ID
						// - encode the list of the request parameters (into an UrlEncodedFormEntity object), according to UTF-8
						// - attach the list of parameters to the HttpPost object
						// - execute the HttpPost request on the HttpClient and get the HttpResponse
						// - get the code from the status line of the response and return Constants.SUCCESS in case it is 200

					} catch (Exception exception) {
						Log.e(Constants.TAG, "An exception has occurred: " + exception.getMessage());
						if (Constants.DEBUG) {
							exception.printStackTrace();
						}
					}
					// transmission of information to the application server was not successful
					return Constants.FAILURE;
				}

				@Override
				protected void onPostExecute(Integer result) {
					switch(result.intValue()) {
						
						case Constants.SUCCESS:
							
							// store the registration ID into the SharedPreferences
							// the ID is registered with the application server
							Utilities.setInformationIntoSharedPreferences(getActivity(), registrationIdTextView.getText().toString(), Constants.SUCCESS);

							// replace the existing fragment
							// in order to forbid the user to register its ID to the registration server
							RegisteredFragment registeredFragment = new RegisteredFragment();
							FragmentManager fragmentManager = getActivity().getFragmentManager();
							FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
							fragmentTransaction.replace(R.id.container, registeredFragment);
							fragmentTransaction.commit();
							Log.i(Constants.TAG, Constants.REGISTRATION_PROCESS_SUCCESS);
							break;
						
						case Constants.FAILURE:
							Log.i(Constants.TAG, Constants.REGISTRATION_PROCESS_FAILURE);
							break;
					}
				}
			}.execute(null, null, null);			
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_unregistered, parent, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		usernameEditText = (EditText)getActivity().findViewById(R.id.username_edit_text);
		emailEditText = (EditText)getActivity().findViewById(R.id.email_edit_text);
		
		sendRegistrationIdButton = (Button)getActivity().findViewById(R.id.send_registration_id_button);
		sendRegistrationIdButton.setOnClickListener(sendRegistrationIdButtonClickListener);
		
		registrationIdTextView = (TextView)getActivity().findViewById(R.id.registration_id_text_view);
	}
	
	public void setRegistrationIdTextView(String registrationId) {
		registrationIdTextView.setText(registrationId);
	}
}
