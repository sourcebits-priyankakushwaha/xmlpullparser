package com.example.parsexmlpullparser;



import java.io.IOException;
import java.io.StringReader;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

	private String XMLData = "<?xml version=\"1.0\"?>" + "<login>" + "<status>OK</status>" + "<jobs><job>"
			+ "<id>4</id>" + "<companyid>4</companyid>" + "<company>Android Example</company>"
			+ "<address>Parse XML Android</address>" + "<city>Tokio</city>" + "<state>Xml Parsing Tutorial</state>"
			+ "<zipcode>601301</zipcode>" + "<country>Japan</country>" + "<telephone>9287893558</telephone>"
			+ "<fax>1234567890</fax>" + "<date>2012-03-15 12:00:00</date>" + "</job><job>" + "<id>5</id>"
			+ "<companyid>6</companyid>" + "<company>Xml Parsing In Java</company>"
			+ "<address>B-22</address><city>Cantabill</city><state>XML Parsing Basics</state><zipcode>201301</zipcode><country>America</country><telephone>9287893558</telephone><fax>1234567890</fax><date>2012-05-18 13:00:00</date></job></jobs></login>";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		TextView textView = (TextView) findViewById(R.id.xmlTextview);
		String xmlContent;

		xmlContent = getAllXML();
		textView.setText(xmlContent);
	}

	public String getAllXML() {

		Activity activity = this;
		String str = "";

		try {
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			factory.setNamespaceAware(true);
			XmlPullParser xmlpp = factory.newPullParser();
			xmlpp.setInput(new StringReader(XMLData));
			xmlpp.next();
			int eventType = xmlpp.getEventType();
			System.out.println("eventType : " + eventType);
			
			while (eventType != XmlPullParser.END_DOCUMENT) {
				if (eventType == XmlPullParser.START_DOCUMENT) {
					str += "nXML Parsing Starting...n";
				} else if (eventType == XmlPullParser.START_TAG) {
					str += "nroot tag: " + xmlpp.getName();
				} else if (eventType == XmlPullParser.END_TAG) {
					str += "nending tag: " + xmlpp.getName();
				} else if (eventType == XmlPullParser.TEXT) {
					str += "nvalue : " + xmlpp.getText();
				}
				eventType = xmlpp.next();
			}
			str += "nnXML parsing Ending......";

		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;
	}
}