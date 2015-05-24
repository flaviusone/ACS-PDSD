package ro.pub.cs.systems.pdsd.lab03.phonedialer.general;

import ro.pub.cs.systems.pdsd.lab03.phonedialer.R;

public interface Constants {
	
	final public static int[] textButtonIds = {
		R.id.button_digit_1,
		R.id.button_digit_2,
		R.id.button_digit_3,
		R.id.button_digit_4,
		R.id.button_digit_5,
		R.id.button_digit_6,
		R.id.button_digit_7,
		R.id.button_digit_8,
		R.id.button_digit_9,
		R.id.button_character_star,
		R.id.button_digit_0,
		R.id.button_character_pound
	};
	
	final public static int[] imageButtonIds = {
		R.id.button_backspace,
		R.id.button_call,
		R.id.button_hangup,
		R.id.button_add_contact
	};
	
	final public static String CONTACTS_MANAGER_ACTIVITY = "ro.pub.cs.systems.pdsd.lab04.contactsmanager.intent.action.ContactsManagerActivity";
	final public static String PHONE_NUMBER_KEY = "ro.pub.cs.systems.pdsd.lab04.contactsmanager.PHONE_NUMBER_KEY";
	final public static int CONTACTS_MANAGER_REQUEST_CODE = 2015;
}
