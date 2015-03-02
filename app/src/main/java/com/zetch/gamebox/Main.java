package com.zetch.gamebox;


import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.support.v7.widget.SearchView;
import android.widget.Toast;

import com.zetch.gamebox.adapters.SearchGamesAdapter;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class Main extends ActionBarActivity {

    @InjectView(R.id.activity_main_recycler_games) RecyclerView recyclerView;
    @InjectView(R.id.activity_main_edit_search) EditText searchEdit;
    @InjectView(R.id.activity_main_button_search) Button buttonSearch;

    SearchView searchView;

    RecyclerView.LayoutManager layoutManager;
    SearchGamesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);

        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new SearchGamesAdapter(this);
        recyclerView.setAdapter(adapter);


        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!searchEdit.getText().equals("")) {
                    adapter.searchGames(searchEdit.getText().toString());
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);

        //searchView = (SearchView) menu.findItem(R.id.menu_main_search).getActionView();

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {

            case R.id.menu_main_search:
                Log.d("testing", String.valueOf(searchView.isIconified()));
                Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show();

        }
        return super.onOptionsItemSelected(item);
    }
}
