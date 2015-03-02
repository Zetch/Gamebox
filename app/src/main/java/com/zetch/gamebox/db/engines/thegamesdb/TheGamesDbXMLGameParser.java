package com.zetch.gamebox.db.engines.thegamesdb;

import com.zetch.gamebox.db.beans.Game;
import com.zetch.gamebox.db.beans.Platform;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by nauzet on 02/03/15.
 */
public class TheGamesDbXMLGameParser extends TheGamesDbXMLParser {

    public TheGamesDbXMLGameParser(InputStream input) throws IOException, XmlPullParserException {
        super(input);
    }

    public Game readGame() throws IOException, XmlPullParserException {
        int id = 0;
        String title = "Unknown";
        Platform platform = null;

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }

            String tagName = parser.getName();
            switch (tagName) {
                case "id":
                    id = readId(parser);
                    break;

                case "GameTitle":
                    title = readTitle(parser);
                    break;

                case "Platform":
                    platform = readPlatform(parser);
                    break;

                default:
                    skip(parser);
            }
        }

        return new Game(title, platform);
    }

    int readId(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "id");
        int number = readInt(parser);
        parser.require(XmlPullParser.END_TAG, ns, "id");
        return number;
    }

    String readTitle(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "GameTitle");
        String title = readString(parser);
        parser.require(XmlPullParser.END_TAG, ns, "GameTitle");
        return title;
    }

    Platform readPlatform(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "Platform");
        Platform platform = new Platform(readString(parser));
        parser.require(XmlPullParser.END_TAG, ns, "Platform");
        return platform;
    }
}
