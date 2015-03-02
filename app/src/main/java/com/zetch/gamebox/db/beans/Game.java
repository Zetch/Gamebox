package com.zetch.gamebox.db.beans;


import java.util.Date;

public class Game {

    public final String title;
    public final Platform platform;

    private Date releaseDate;

    public Game(String title, Platform platform) {
        this.title = title;
        this.platform = platform;
    }

    public void setReleaseDate(String stringDate) {
        this.releaseDate = new Date();
    }

    public Date getReleaseDate() {
        return this.releaseDate;
    }
}
