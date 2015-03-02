package com.zetch.gamebox.adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zetch.gamebox.R;
import com.zetch.gamebox.db.beans.Game;
import com.zetch.gamebox.tasks.SearchGames;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by nauzet on 02/03/15.
 */
public class SearchGamesAdapter extends RecyclerView.Adapter<SearchGamesAdapter.ViewHolder> {

    ProgressDialog loading;
    List<Game> games = new ArrayList<>();
    Context context;

    public SearchGamesAdapter(Context context) {
        this.context = context;
    };

    public Context getContext() {
        return this.context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_search_game, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Game game = games.get(position);
        holder.titleView.setText(game.title);
        holder.platformView.setText(game.platform.name);
    }

    @Override
    public int getItemCount() {
        return games.size();
    }

    public void addGames(List<Game> games) {
        this.games.addAll(games);
        this.notifyDataSetChanged();
    }

    public void clearGames() {
        this.games.clear();
        this.notifyDataSetChanged();
    }

    public void searchGames(String name) {
        SearchGames task = new SearchGames(this);
        task.execute(name);
    }

    public void showLoadingDialog() {
        if (loading == null) {
            loading = new ProgressDialog(getContext());
            loading.setMessage(getContext().getResources().getString(R.string.activity_main_loading_message));
        }
        loading.show();
    }

    public void hideLoadingDialog() {
        if (loading != null) {
            loading.hide();
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.card_search_game_title) TextView titleView;
        @InjectView(R.id.card_search_game_platform) TextView platformView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }
}
