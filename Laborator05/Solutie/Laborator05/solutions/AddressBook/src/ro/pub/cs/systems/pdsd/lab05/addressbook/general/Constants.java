package ro.pub.cs.systems.pdsd.lab05.addressbook.general;

public interface Constants {
	
	public final static boolean DEBUG                    = true;
	public final static String  TAG                      = "Address Book";
	
	public final static int     BASIC_DETAILS_QUERY      = 1;
	public final static int     ADDITIONAL_DETAILS_QUERY = 2;
	
	public final static int     OPERATION_INSERT         = 1;
	public final static int     OPERATION_UPDATE         = 2;
	
	public final static String  OPERATION                = "operation";
	public final static String  CONTACT_NAME             = "contact_name";
	public final static String  CONTACT_PHONES           = "contact_phones";
	public final static String  CONTACT_EMAILS           = "contact_emails";
	public final static String  CONTACT_ID               = "contact_id";
	
	public final static String  TYPE_HOME                = "Home";
	public final static String  TYPE_MOBILE              = "Mobile";
	public final static String  TYPE_WORK                = "Work";
	public final static String  TYPE_OTHER               = "Other";
	
	public final static int     PHONE_TYPE_MOBILE        = 0;
	public final static int     PHONE_TYPE_WORK          = 1;  
	public final static int     PHONE_TYPE_HOME          = 2;  
	public final static int     PHONE_TYPE_MAIN          = 3;  
	public final static int     PHONE_TYPE_WORK_FAX      = 4;
	public final static int     PHONE_TYPE_HOME_FAX      = 5;  
	public final static int     PHONE_TYPE_PAGER         = 6;  
	public final static int     PHONE_TYPE_OTHER         = 7;  
	
	public final static int     EMAIL_TYPE_HOME          = 0;
	public final static int     EMAIL_TYPE_WORK          = 1;  
	public final static int     EMAIL_TYPE_OTHER         = 2;  
	public final static int     EMAIL_TYPE_CUSTOM        = 3;  

}
