package ro.pub.cs.systems.pdsd.lab02.general;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import ro.pub.cs.systems.pdsd.lab02.entities.Credential;
import android.content.Context;
import android.util.Log;

public class Utilities {
	
	public static boolean allowAccess(Context context, String username, String password) {

		try {
			XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory.newInstance();
			XmlPullParser xmlPullParser = xmlPullParserFactory.newPullParser();
			InputStream inputStream = context.getAssets().open(Constants.CREDENTIAL_FILE);
		    xmlPullParser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
	        xmlPullParser.setInput(inputStream, null);
	        ArrayList<Credential> credentials = parseXML(xmlPullParser);
	        for (Credential credential:credentials)
	        	if (credential.getUsername().equals(username) && credential.getPassword().equals(password))
	        		return true;
		} catch (XmlPullParserException xmlPullParserException) {
			xmlPullParserException.printStackTrace();
			if (Constants.DEBUG)
				Log.e(Constants.TAG, "An exception has occured: "+xmlPullParserException.getMessage());
		} catch (IOException ioException) {
			ioException.printStackTrace();
			if (Constants.DEBUG)
				Log.e(Constants.TAG, "An exception has occured: "+ioException.getMessage());
		}
		
		return false;
	}
	
	private static ArrayList<Credential> parseXML(XmlPullParser parser) throws XmlPullParserException, IOException {
		ArrayList<Credential> credentials = null;
        int eventType = parser.getEventType();
        Credential currentCredential = null;

        while (eventType != XmlPullParser.END_DOCUMENT) {
            String tag = null;
            switch (eventType) {
                case XmlPullParser.START_DOCUMENT:
                	credentials = new ArrayList<Credential>();
                    break;
                case XmlPullParser.START_TAG:
                    tag = parser.getName();
                    if (tag.equalsIgnoreCase(Constants.CREDENTIAL_TAG)) {
                    	currentCredential = new Credential();
                    } else if (currentCredential != null) {
                        if (tag.equalsIgnoreCase(Constants.USERNAME_TAG)) {
                        	currentCredential.setUsername(parser.nextText());
                        } else if (tag.equalsIgnoreCase(Constants.PASSWORD_TAG)) {
                        	currentCredential.setPassword(parser.nextText());
                        }  
                    }
                    break;
                case XmlPullParser.END_TAG:
                    tag = parser.getName();
                    if (tag.equalsIgnoreCase(Constants.CREDENTIAL_TAG) && currentCredential != null){
                    	credentials.add(currentCredential);
                    }
                    break;
                case XmlPullParser.END_DOCUMENT:
                	return credentials;
            }
            eventType = parser.next();
        }
        
        return credentials;
	}

}
