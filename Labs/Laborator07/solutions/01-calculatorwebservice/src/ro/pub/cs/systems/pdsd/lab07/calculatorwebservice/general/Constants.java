package ro.pub.cs.systems.pdsd.lab07.calculatorwebservice.general;

public interface Constants {
	
	public static boolean DEBUG                    = true;
	
	public static String  TAG                      = "Calculator Web Service";
	
	public static String  GET_WEB_SERVICE_ADDRESS  = "http://pdsd2015.andreirosucojocaru.ro/exemple/laboratoare/laborator07/calculator/calculator_get.php";
	public static String  POST_WEB_SERVICE_ADDRESS = "http://pdsd2015.andreirosucojocaru.ro/exemple/laboratoare/laborator07/calculator/calculator_post.php";

	public static String  ERROR_MESSAGE_EMPTY      = "Operator fields cannot be empty";
	
	public static int     GET_OPERATION            = 0;
	public static int     POST_OPERATION           = 1;
	
	public static String  OPERATION_ATTRIBUTE      = "operation";
	public static String  OPERATOR1_ATTRIBUTE      = "operator1";
	public static String  OPERATOR2_ATTRIBUTE      = "operator2";

}
