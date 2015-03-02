package com.zetch.gamebox.db.engines.thegamesdb;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;


public class TheGamesDbXMLParser {

    XmlPullParser parser;
    static final String ns = null;
    InputStream input;


    public TheGamesDbXMLParser(InputStream input) throws IOException, XmlPullParserException {
        this.input = input;
        this.parser = Xml.newPullParser();
        this.parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
        this.parser.setInput(input, null);
        this.parser.nextTag();
    }

    String readString(XmlPullParser parser) throws IOException, XmlPullParserException {
        String text = "";
        if (parser.next() == XmlPullParser.TEXT) {
            text = parser.getText();
            parser.nextTag();
        }
        return text;
    }

    int readInt(XmlPullParser parser) throws IOException, XmlPullParserException {
        int number = 0;
        if (parser.next() == XmlPullParser.TEXT) {
            number = Integer.valueOf(parser.getText());
            parser.nextTag();
        }
        return number;
    }

    float readFloat(XmlPullParser parser) throws IOException, XmlPullParserException {
        float number = 0;
        if (parser.next() == XmlPullParser.TEXT) {
            number = Float.valueOf(parser.getText());
            parser.nextTag();
        }
        return number;
    }

    public void skip(XmlPullParser parser) throws  IOException, XmlPullParserException {
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
