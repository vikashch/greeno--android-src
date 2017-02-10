package com.green0.customerApp.adapter;

/**
 * Created by sachinchandra on 10/24/16.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ironman.myapplication.R;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.MyViewHolder> {
    private String[] mDataset;
    private int expandedPosition = -1;


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView details;
        LinearLayout llExpandArea;

        public MyViewHolder(View v) {
            super(v);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            details = (TextView) itemView.findViewById(R.id.tvTitle);
            llExpandArea = (LinearLayout) itemView.findViewById(R.id.llExpandArea);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public RecycleViewAdapter(String[] myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecycleViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                              int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.tvTitle.setText(mDataset[position]);
        holder.details.setText(mDataset[position]);
        final boolean[] isExpanded = {true};
        if (isExpanded[0])
        {
            holder.llExpandArea.setVisibility(View.VISIBLE);
        }
        else {
            holder.llExpandArea.setVisibility(View.GONE);
        }
        holder.tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isExpanded[0])
                {
                    holder.llExpandArea.setVisibility(View.GONE);
                    isExpanded[0] =false;
                    notifyItemChanged(position);
                }
                else {
                    holder.llExpandArea.setVisibility(View.VISIBLE);
                    isExpanded[0] =true;
                    notifyItemChanged(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}