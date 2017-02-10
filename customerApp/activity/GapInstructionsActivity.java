package com.green0.customerApp.activity;

import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.view.WindowManager;

import com.example.ironman.myapplication.R;


import com.green0.customerApp.adapter.SwipeAdaper;

public class GapInstructionsActivity extends FragmentActivity {

    private Toolbar mToolbar;
    ViewPager viewPager;
    //TitlePageIndicator titlePageIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_gap_instructions);
        //titlePageIndicator = (TitlePageIndicator) findViewById(R.id.indicator);
        viewPager = (ViewPager)findViewById(R.id.view_page);
        SwipeAdaper swipeAdaper = new SwipeAdaper(getSupportFragmentManager());
        viewPager.setAdapter(swipeAdaper);
        //titlePageIndicator.setViewPager(viewPager);
    }
}
