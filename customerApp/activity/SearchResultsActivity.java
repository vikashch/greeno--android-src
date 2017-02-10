package com.green0.customerApp.activity;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.ironman.myapplication.R;

/**
 * Created by ironman on 10/10/16.
 */

public class SearchResultsActivity extends Activity {
    private TextView txtQuery;
    @Override
    protected  void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_search_results);
        txtQuery = (TextView) findViewById(R.id.txtQuery);
        handleIntent(getIntent());
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        return true;
    }

    @Override
    protected  void onNewIntent(Intent intent){
        setIntent(intent);
        handleIntent(intent);
    }

    /**
     * Handling intent data
     *
     */
    private void handleIntent(Intent intent) {
        if(Intent.ACTION_SEARCH.equals(intent.getAction())){
            String query = intent.getStringExtra(SearchManager.QUERY);
            txtQuery.setText("seach query :"+query);
        }
    /**
     * Use this query to display search results like
     * 1. Getting the data from SQLite and showing in listview
     * 2. Making webrequest and displaying the data
     * For now we just display the query only
     */

    }
}
