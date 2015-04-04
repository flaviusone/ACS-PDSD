package ro.pub.cs.systems.pdsd.lab05.sample09baseadapter.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import ro.pub.cs.systems.pdsd.lab05.sample09baseadapter.general.Constants;
import ro.pub.cs.systems.pdsd.lab05.sample09baseadapter.model.Cartoon;
import android.util.Xml;

public class CartoonsXmlParser {
    
	private static final String namespace = null;   
   
    public ArrayList<Cartoon> parse(InputStream in) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            return readCartoon(parser);
        } finally {
            in.close();
        }
    }
    
    private ArrayList<Cartoon> readCartoon(XmlPullParser parser) throws XmlPullParserException, IOException {
        ArrayList<Cartoon> cartoons = new ArrayList<Cartoon>();

        parser.require(XmlPullParser.START_TAG, namespace, Constants.ROOT_TAG);
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals(Constants.START_TAG)) {
                cartoons.add(addCartoon(parser));
            } else {
                skip(parser);
            }
        }  
        return cartoons;
    }
    
    private Cartoon addCartoon(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, namespace, Constants.START_TAG);
        Cartoon cartoon = new Cartoon();
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String attribute = parser.getName();
            String value = readAttribute(parser, attribute);
            cartoon.set(attribute, value);
        }
        return cartoon;
    }
    
    private String readAttribute(XmlPullParser parser, String attribute) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, namespace, attribute);
        String title = readText(parser);
        parser.require(XmlPullParser.END_TAG, namespace, attribute);
        return title;
    }
    
    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }
    
    private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
            case XmlPullParser.END_TAG:
                depth--;
                break;
            case XmlPullParser.START_TAG:
                depth++;
                break;
            }
        }
     }
}