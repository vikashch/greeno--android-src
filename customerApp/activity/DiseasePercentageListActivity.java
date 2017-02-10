package com.green0.customerApp.activity;


import android.app.SearchManager;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import com.example.ironman.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.green0.customerApp.adapter.ImageResultAdapter;
import com.green0.customerApp.fragments.FragmentDrawer;
import com.green0.customerApp.model.ImageResultDisease;
import com.green0.customerApp.model.ImageResultDiseaseList;


public class DiseasePercentageListActivity extends AppCompatActivity implements  FragmentDrawer.FragmentDrawerListener {
    private Toolbar mToolbar;
    private FragmentDrawer drawerFragment;
    private RecyclerView diseaseRecyclerView;
    private ImageResultAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease_percentage_list);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        drawerFragment = (FragmentDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);
        List<ImageResultDisease> lst = new ArrayList<>();
        Gson gson = new Gson();
        String strObj = getIntent().getStringExtra("obj");
        lst = (List<ImageResultDisease>) gson.fromJson(strObj, new TypeToken<List<ImageResultDisease>>(){}.getType());
        ImageResultDiseaseList imageResultDiseaseList= new ImageResultDiseaseList(lst);


        diseaseRecyclerView = (RecyclerView) findViewById(R.id.disease_recyler_view);
        mAdapter = new ImageResultAdapter(imageResultDiseaseList.getImageResultDiseases());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        diseaseRecyclerView.setLayoutManager(mLayoutManager);
        diseaseRecyclerView.setItemAnimator(new DefaultItemAnimator());
        diseaseRecyclerView.setAdapter(mAdapter);
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
                //Toast.makeText(MainActivity.this,"Home selecetec",Toast.LENGTH_SHORT).show();
//                fragment = new HomeFragment();
                title = getString(R.string.title_home);
                break;
            case 1:
//                fragment = new FriendsFragment();
                //Toast.makeText(MainActivity.this,"Friends selected",Toast.LENGTH_SHORT).show();
                title = getString(R.string.title_friends);
                break;
            case 2:
//                fragment = new MessagesFragment();
                //Toast.makeText(MainActivity.this,"Messages selected",Toast.LENGTH_SHORT).show();
                title = getString(R.string.title_messages);
                break;
            default:
                break;
        }

    }
}
