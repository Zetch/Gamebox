package com.zetch.gamebox.db.engines.thegamesdb;

import com.zetch.gamebox.db.beans.Game;
import com.zetch.gamebox.db.beans.Platform;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class TheGamesDbXMLGameListParser extends TheGamesDbXMLGameParser {

    public TheGamesDbXMLGameListParser(InputStream input) throws IOException, XmlPullParserException {
        super(input);
    }

    public List<Game> readGames() throws IOException, XmlPullParserException {
        List<Game> games = new ArrayList<>();

        parser.require(XmlPullParser.START_TAG, ns, "Data");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }

            String tagName = parser.getName();
            if (tagName.equals("Game")) {
                games.add(readGame());
            } else {
                skip(parser);
            }
        }
        return games;
    }
}
