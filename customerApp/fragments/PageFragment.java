package com.green0.customerApp.fragments;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ironman.myapplication.R;
import com.green0.customerApp.adapter.RecycleViewAdapter;

public class PageFragment extends android.support.v4.app.Fragment {

    TextView textView;
    String[] instructions;

    public PageFragment(){

    }


    public PageFragment(int position) {
        if (position==1){
            instructions = new String[]{"Keep hands in your pocket", "point hands to the sky", "Do balle balle", "go fuck yourself"};;

        }
        else if (position==2){
            instructions = new String[]{"point hands to the sky", "Do balle balle", "go fuck yourself"};;

        }
        else if (position==3){
            instructions = new String[]{"Do balle balle", "go fuck yourself"};;

        }
        else if (position==4){
            instructions = new String[]{"go fuck yourself"};;

        }
        else {
            instructions = new String[]{"Keep hands in your pocket", "point hands to the sky", "Do balle balle", "go fuck yourself"};;

        }

    }


    @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View rootView = inflater.inflate(R.layout.fragment_page, container, false);
            RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.rv_recycler_view);
            rv.setHasFixedSize(true);
            RecycleViewAdapter adapter = new RecycleViewAdapter(instructions);
            rv.setAdapter(adapter);

            LinearLayoutManager llm = new LinearLayoutManager(getActivity());
            rv.setLayoutManager(llm);

            return rootView;
        }

    }

