package com.zetch.gamebox.tasks;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.zetch.gamebox.adapters.SearchGamesAdapter;
import com.zetch.gamebox.db.beans.Game;
import com.zetch.gamebox.db.engines.SearchEngine;
import com.zetch.gamebox.db.engines.thegamesdb.TheGamesDbEngine;

import java.util.List;

/**
 * Created by nauzet on 02/03/15.
 */
public class SearchGames extends AsyncTask<String, Void, List<Game>> {

    SearchGamesAdapter adapter;

    public SearchGames(SearchGamesAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        adapter.showLoadingDialog();
    }

    @Override
    protected List<Game> doInBackground(String... params) {
        SearchEngine engine = new TheGamesDbEngine();
        return engine.searchGames(params[0], null, null);
    }

    @Override
    protected void onPostExecute(List<Game> games) {
        super.onPostExecute(games);
        adapter.clearGames();
        if (games != null) {
            adapter.addGames(games);
            Toast.makeText(adapter.getContext(), games.size() + " games found", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(adapter.getContext(), "No games found", Toast.LENGTH_SHORT).show();
        }
        adapter.hideLoadingDialog();
    }
}
