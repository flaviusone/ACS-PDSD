package ro.pub.cs.systems.pdsd.lab08.googlecloudmessaging.general;

public interface Constants {
	
	final public static boolean DEBUG                                  = true;
	
	final public static String  TAG                                    = "Google Cloud Messaging Client";
	
	// TODO: exercise 6 modify PROJECT_NUMBER and, if is the case, the IP address corresponding to the services
	final public static String  PROJECT_NUMBER                         = "279663592308";
	
	final public static String  DEVICE_REGISTRATION_SERVICE_ADDRESS    = "http://pdsd2015.andreirosucojocaru.ro/exemple/laboratoare/laborator08/googlecloudmessaging/device_registration.php";
	final public static String  REGISTERED_DEVICES_SERVICE_ADDRESS     = "http://pdsd2015.andreirosucojocaru.ro/exemple/laboratoare/laborator08/googlecloudmessaging/registered_devices.php";
	final public static String  MESSAGE_PUSH_SERVICE_ADDRESS           = "http://pdsd2015.andreirosucojocaru.ro/exemple/laboratoare/laborator08/googlecloudmessaging/message_push.php";
	
	/*
	final public static String  DEVICE_REGISTRATION_SERVICE_ADDRESS    = "http://192.168.56.1:8080/GoogleCloudMessagingServer/DeviceRegistrationServlet";
	final public static String  REGISTERED_DEVICES_SERVICE_ADDRESS     = "http://192.168.56.1:8080/GoogleCloudMessagingServer/RegisteredDevicesServlet";
	final public static String  MESSAGE_PUSH_SERVICE_ADDRESS           = "http://192.168.56.1:8080/GoogleCloudMessagingServer/MessagePushServlet";
	*/

	final public static int		PLAY_SERVICES_RESOLUTION_REQUEST       = 9000;
	
	final public static int		SUCCESS                                = 0;
	final public static int		FAILURE                                = -1;
	
	final public static String 	APPLICATION_VERSION_PROPERTY           = "application_version";
	final public static String 	REGISTRATION_ID_PROPERTY               = "registration_id";
	final public static String 	REGISTRATION_STATUS_PROPERTY           = "application_server_registration_status";
	
	final public static String  REGISTRATION_STATUS_ERROR_MESSAGE      = "Registration Status not found into the SharedPreferences.";
	final public static String  REGISTRATION_ID_ERROR_MESSAGE          = "Registration ID not found into the SharedPreferences.";
	final public static String  APPLICATION_VERSION_ERROR_MESSAGE      = "The application version was changed.";
	final public static String  GOOGLE_PLAY_SERVICES_ERROR_MESSAGE1    = "Google Play Services APK was not found on the mobile device.";
	final public static String  GOOGLE_PLAY_SERVICES_ERROR_MESSAGE2    = "This mobile device does not support Google Play Services.";
	
	final public static String  SHARED_PREFERENCES_INFORMATION_MESSAGE = "Setting the registrationId and registrationStatus on application version ";
	final public static String  REGISTRATION_ID_INFORMATION_MESSAGE    = "Registration ID supplied by the Google Cloud Messaging Server is: ";
	
	final public static String  EMPTY_STRING                           = "";
	
	final public static String  REGISTRATION_PROCESS_SUCCESS           = "The registration process was successfull.";
	final public static String  REGISTRATION_PROCESS_FAILURE           = "The registration process failed.";
	
	final public static int 	NOTIFICATION_ID                        = 1;

	final public static String  NOTIFICATION_MESSAGE                   = "You have a new notification";
	
	final public static String  ID                                     = "id";
	final public static String  REGISTRATION_ID                        = "registration_id";
	final public static String  USERNAME                               = "username";
	final public static String  EMAIL                                  = "email";
	final public static String  TIMESTAMP                              = "timestamp";
	final public static String  MESSAGE                                = "message";

}
