package com.zetch.gamebox.db.engines.thegamesdb;

import com.zetch.gamebox.db.beans.GameMedia;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;



public class TheGamesDbXMLArtParser extends TheGamesDbXMLParser {

    public TheGamesDbXMLArtParser(InputStream input) throws IOException, XmlPullParserException {
        super(input);
    }

    public List<GameMedia> readMedia() throws IOException, XmlPullParserException {
        List<GameMedia> media = new ArrayList<>();

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }

            String tagName = parser.getName();
            switch (tagName) {
                case "boxart":
                    media.add(readBoxart(parser));
                    break;

                default:
                    skip(parser);
            }
        }

        return media;
    }

    private GameMedia readBoxart(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "boxart");

        String side;
        String thumb;
        String image;

        int count = parser.getAttributeCount();
        while (count > 0) {
            switch(parser.getAttributeName(count)) {
                case "side":
                    side = parser.getAttributeValue(count);
                    break;

                case "thumb":
                    thumb = parser.getAttributeValue(count);
                    break;
            }
            count--;
        }

        image = readString(parser);
        parser.require(XmlPullParser.END_TAG, ns, "boxart");
        return new GameMedia(image);
    }
}
