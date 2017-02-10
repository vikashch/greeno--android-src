package com.green0.customerApp.activity;

import android.app.SearchManager;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.support.v7.widget.SearchView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.ironman.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import com.green0.customerApp.adapter.FeedAdapter;
import com.green0.customerApp.fragments.FragmentDrawer;
import com.green0.customerApp.model.FeedChildren;
import com.green0.customerApp.model.FeedParent;


public class MainActivity extends AppCompatActivity implements  FragmentDrawer.FragmentDrawerListener {

    private Toolbar mToolbar;
    private FragmentDrawer drawerFragment;

    public FeedAdapter feedAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        drawerFragment = (FragmentDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);



        FeedChildren mirchi,paddy,banana,tobacco,tomato,tsunami,waterfall,headwinds,news1,news2;
        mirchi = new FeedChildren("mirchi",1,R.drawable.ic_hot_pepper);
        paddy = new FeedChildren("paddy",1,R.drawable.ic_aubergine);
        tobacco = new FeedChildren("tobacco",1,R.drawable.ic_bell_pepper);
        tomato = new FeedChildren("tomato",1,R.drawable.ic_carrot);
        tsunami = new FeedChildren("tsunami",2,R.drawable.ic_carrot);
        waterfall = new FeedChildren("waterfall",2,R.drawable.ic_carrot);
        headwinds = new FeedChildren("heavywinds",2,R.drawable.ic_carrot);
        news1 = new FeedChildren("blah blah 1 modi no 1",1,R.drawable.ic_carrot);
        news2 = new FeedChildren("blah blah modi no 1",1,R.drawable.ic_carrot);

        List<FeedChildren> crops = new ArrayList<FeedChildren>();
        crops.add(mirchi);
        crops.add(paddy);
        crops.add(tobacco);
        crops.add(tomato);

        List<FeedChildren> weather = new ArrayList<FeedChildren>();
        weather.add(tsunami);
        weather.add(waterfall);
        weather.add(headwinds);

        List<FeedChildren> news = new ArrayList<FeedChildren>();
        news.add(news1);
        news.add(news2);

        FeedParent cropsParent = new FeedParent("Crops",crops,R.drawable.ic_sprout);
        FeedParent weatherParent = new FeedParent("weather",weather,R.drawable.ic_weather);
        FeedParent newsParent = new FeedParent("news",news,R.drawable.ic_news);
        List<FeedParent> feedData = new ArrayList<FeedParent>();
        feedData.add(cropsParent);
        feedData.add(weatherParent);
        feedData.add(newsParent);


        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.feed_recyler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        feedAdapter = new FeedAdapter(feedData);
        recyclerView.setAdapter(feedAdapter);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        feedAdapter.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        feedAdapter.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id){
            case R.id.action_search:
                return true;
            case R.id.action_refresh:
                return true;
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
    displayView(position);
    }
    private void displayView(int position) {
        Fragment fragment = null;
        String title = getString(R.string.app_name);
        switch (position) {
            case 0:
                Toast.makeText(MainActivity.this,"Home selecetec",Toast.LENGTH_SHORT).show();
//                fragment = new HomeFragment();
                title = getString(R.string.title_home);
                break;
            case 1:
//                fragment = new FriendsFragment();
                Toast.makeText(MainActivity.this,"Friends selected",Toast.LENGTH_SHORT).show();
                title = getString(R.string.title_friends);
                break;
            case 2:
//                fragment = new MessagesFragment();
                Toast.makeText(MainActivity.this,"Messages selected",Toast.LENGTH_SHORT).show();
                title = getString(R.string.title_messages);
                break;
            default:
                break;
        }

    }
}
