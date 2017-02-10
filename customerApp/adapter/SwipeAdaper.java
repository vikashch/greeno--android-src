package com.green0.customerApp.adapter;

import android.os.Bundle;
import android.support.v4.app.*;

import com.green0.customerApp.fragments.PageFragment;

/**
 * Created by sachinchandra on 10/19/16.
 */

public class SwipeAdaper extends FragmentStatePagerAdapter {

    public SwipeAdaper(FragmentManager fm) {
        super(fm);
    }

    private static String[] titles = new String[] {
            "week1",
            "week2",
            "week3",
            "week4",
            "week5"
    };

    public String getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment = new PageFragment(position);
        Bundle bundle = new Bundle();
        bundle.putInt("count",position +1);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return 5;
    }
}
