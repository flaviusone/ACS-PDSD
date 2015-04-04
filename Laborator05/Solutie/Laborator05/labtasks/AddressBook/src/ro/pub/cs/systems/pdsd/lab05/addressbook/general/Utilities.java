package ro.pub.cs.systems.pdsd.lab05.addressbook.general;

import java.util.Calendar;
import java.util.GregorianCalendar;

import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Phone;

public class Utilities {
	
	public static String displayDateAndTime(long value) {
		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		gregorianCalendar.setTimeInMillis(value);
		int day = gregorianCalendar.get(Calendar.DAY_OF_MONTH);
		int month = gregorianCalendar.get(Calendar.MONTH) + 1;
		int year = gregorianCalendar.get(Calendar.YEAR);
		int hour = gregorianCalendar.get(Calendar.HOUR);
		int minute = gregorianCalendar.get(Calendar.MINUTE);
		int second = gregorianCalendar.get(Calendar.SECOND);
		return ((day<10)?"0":"")+day+"/"
				+((month<10)?"0":"")+month+"/"
				+((year<10)?"0":"")+year+" "
				+((hour<10)?"0":"")+hour+":"
				+((minute<10)?"0":"")+minute+":"
				+((second<10)?"0":"")+second;
	}
	
	public static int convertIndexToPhoneType(int index) {
		switch(index) {
			case Constants.PHONE_TYPE_MOBILE:
				return Phone.TYPE_MOBILE;
			case Constants.PHONE_TYPE_WORK:
				return Phone.TYPE_WORK;
			case Constants.PHONE_TYPE_HOME:
				return Phone.TYPE_HOME;
			case Constants.PHONE_TYPE_MAIN: 
				return Phone.TYPE_MAIN;
			case Constants.PHONE_TYPE_WORK_FAX:
				return Phone.TYPE_FAX_WORK;
			case Constants.PHONE_TYPE_HOME_FAX: 
				return Phone.TYPE_FAX_HOME;
			case Constants.PHONE_TYPE_PAGER:
				return Phone.TYPE_PAGER;
			case Constants.PHONE_TYPE_OTHER:
				return Phone.TYPE_OTHER;
		}
		return -1;
	}
	
	public static int convertIndexToEmailType(int index) {
		switch(index) {
			case Constants.EMAIL_TYPE_HOME:
				return Email.TYPE_HOME;
			case Constants.EMAIL_TYPE_WORK:
				return Email.TYPE_WORK;
			case Constants.EMAIL_TYPE_OTHER:
				return Email.TYPE_OTHER;
			case Constants.EMAIL_TYPE_CUSTOM: 
				return Email.TYPE_CUSTOM;
		}
		return -1;
	}	

}
