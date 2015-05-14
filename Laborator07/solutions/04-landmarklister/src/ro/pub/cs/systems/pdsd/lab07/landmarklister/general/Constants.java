package ro.pub.cs.systems.pdsd.lab07.landmarklister.general;

public interface Constants {
	
	public static boolean DEBUG                                          = true;
	
	public static String  TAG                                            = "Landmark Lister";
	
	public static String  LANDMARK_LISTER_WEB_SERVICE_INTERNET_ADDRESS   = "http://api.geonames.org/citiesJSON?";
	
	public static String  MISSING_INFORMATION_ERROR_MESSAGE              = "All fields need to be completed!";
	
	public static String  NORTH                                          = "north=";
	public static String  SOUTH                                          = "south=";
	public static String  EAST                                           = "east=";
	public static String  WEST                                           = "west=";
	public static String  CREDENTIALS                                    = "username=pdsd";
	
	public static String  GEONAMES                                       = "geonames";
	public static String  LATITUDE                                       = "lat";
	public static String  LONGITUDE                                      = "lng";
	public static String  TOPONYM_NAME                                   = "toponymName";
	public static String  POPULATION                                     = "population";
	public static String  FCODE_NAME                                     = "fcodeName";
	public static String  NAME                                           = "name";
	public static String  WIKIPEDIA_WEB_PAGE_ADDRESS                     = "wikipedia";
	public static String  COUNTRY_CODE                                   = "countrycode";

}
