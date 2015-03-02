package com.zetch.gamebox.db.engines.thegamesdb;

import android.util.Log;

import com.zetch.gamebox.db.beans.Game;
import com.zetch.gamebox.db.beans.GameMedia;
import com.zetch.gamebox.db.beans.Platform;
import com.zetch.gamebox.db.engines.SearchEngine;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nauzet on 02/03/15.
 */
public class TheGamesDbEngine implements SearchEngine {

    private static final String LOGTAG = TheGamesDbEngine.class.getName();

    private static final int TIMEOUT            = 10000; // 10s
    private static final int CONNECTION_TIMEOUT = 15000; // 15s

    static final String GAME_SEARCH_ENTRYPOINT = "http://thegamesdb.net/api/GetGamesList.php";
    static final String GAME_ENTRYPOINT = "http://thegamesdb.net/api/GetGame.php";
    static final String PLATFORM_SEARCH_ENTRYPOINT = "http://thegamesdb.net/api/GetGamesList.php";
    static final String ART_ENTRYPOINT = "http://thegamesdb.net/api/GetArt.php";

    @Override
    public List<Game> searchGames(String name, String platform, String genre) {
        String finalQuery = GAME_SEARCH_ENTRYPOINT;

        finalQuery += "?name=" + name.replaceAll(" ", "+");

        if (platform != null) {
            finalQuery += "&platform=" + platform;
        }
        if (genre != null) {
            finalQuery += "&genre=" + genre;
        }

        Log.d(LOGTAG, "Query: " + finalQuery);

        try {
            List<Game> games;
            InputStream input = downloadData(finalQuery);

            TheGamesDbXMLGameListParser parser = new TheGamesDbXMLGameListParser(input);
            games = parser.readGames();
            input.close();
            return games;

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Game getGame(int id) {
        return null;
    }

    @Override
    public List<Platform> searchPlatforms(String pattern) {
        return null;
    }

    @Override
    public List<Platform> getAllPlatforms() {
        return null;
    }

    @Override
    public Platform getPlatform(int id) {
        return null;
    }

    @Override
    public List<GameMedia> searchMedia(String pattern) {
        return null;
    }

    @Override
    public GameMedia getMediaCover(int id) {
        return null;
    }

    private InputStream downloadData(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(TIMEOUT);
        conn.setConnectTimeout(CONNECTION_TIMEOUT);
        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        return conn.getInputStream();
    }

}
