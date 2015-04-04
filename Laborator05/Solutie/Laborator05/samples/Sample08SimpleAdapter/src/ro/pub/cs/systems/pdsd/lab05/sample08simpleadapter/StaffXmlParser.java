package ro.pub.cs.systems.pdsd.lab05.sample08simpleadapter;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Xml;

public class StaffXmlParser {
    private static final String ns = null;   
   
    public List<Map<String,?>> parse(InputStream in) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            return readStaff(parser);
        } finally {
            in.close();
        }
    }
    
    private List<Map<String,?>> readStaff(XmlPullParser parser) throws XmlPullParserException, IOException {
        List<Map<String,?>> entries = new ArrayList<Map<String,?>>();

        parser.require(XmlPullParser.START_TAG, ns, "people");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            // Starts by looking for the entry tag
            if (name.equals("person")) {
                entries.add(readPerson(parser));
            } else {
                skip(parser);
            }
        }  
        return entries;
    }
    
    private HashMap<String, String> readPerson(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "person");
        HashMap<String, String> entry = new HashMap<String, String>();
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String field = parser.getName();
            if (field.equals("name")) {
            	entry.put("name", readAttribute(parser, "name"));
            } else if (field.equals("position")) {
                entry.put("position", readAttribute(parser, "position"));
            } else {
                skip(parser);
            }
        }
        return entry;
    }
    
    private String readAttribute(XmlPullParser parser, String attribute) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, attribute);
        String title = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, attribute);
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