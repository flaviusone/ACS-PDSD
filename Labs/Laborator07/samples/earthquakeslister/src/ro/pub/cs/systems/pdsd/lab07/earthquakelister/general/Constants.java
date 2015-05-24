package ro.pub.cs.systems.pdsd.lab07.earthquakelister.general;

public interface Constants {
	
	public static boolean DEBUG                                          = true;
	
	public static String  TAG                                            = "Earthquake Lister";
	
	public static String  EARTHQUAKE_LISTER_WEB_SERVICE_INTERNET_ADDRESS = "http://api.geonames.org/earthquakesJSON?";
	
	public static String  MISSING_INFORMATION_ERROR_MESSAGE              = "All fields need to be completed!";
	
	public static String  NORTH                                          = "north=";
	public static String  SOUTH                                          = "south=";
	public static String  EAST                                           = "east=";
	public static String  WEST                                           = "west=";
	public static String  CREDENTIALS                                    = "username=pdsd";
	
	public static String  EARTHQUAKES                                    = "earthquakes";
	public static String  LATITUDE                                       = "lat";
	public static String  LONGITUDE                                      = "lng";
	public static String  MAGNITUDE                                      = "magnitude";
	public static String  DEPTH                                          = "depth";
	public static String  SOURCE                                         = "src";
	public static String  DATETIME                                       = "datetime";

}
