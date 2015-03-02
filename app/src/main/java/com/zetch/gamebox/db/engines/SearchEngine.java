package com.zetch.gamebox.db.engines;

import com.zetch.gamebox.db.beans.Game;
import com.zetch.gamebox.db.beans.GameMedia;
import com.zetch.gamebox.db.beans.Platform;

import java.util.List;

public interface SearchEngine {

    // Returns Game list due to string pattern
    public List<Game> searchGames(String name, String platform, String genre);
    public Game getGame(int id);

    // Get platform lists
    public List<Platform> searchPlatforms(String pattern);
    public List<Platform> getAllPlatforms();
    public Platform getPlatform(int id);

    // Get media
    public List<GameMedia> searchMedia(String pattern);
    public GameMedia getMediaCover(int id);
}
