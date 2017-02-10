package com.green0.customerApp.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.example.ironman.myapplication.R;
import com.thoughtbot.expandablerecyclerview.MultiTypeExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

import com.green0.customerApp.model.FeedChildren;
import com.green0.customerApp.model.FeedParent;

import static android.view.LayoutInflater.from;

/**
 * Created by ironman on 21/11/16.
 */

public class FeedAdapter extends MultiTypeExpandableRecyclerViewAdapter<FeedParentViewHolder,FeedChildViewHolder> {
    public FeedAdapter(List<FeedParent> groups) {
        super(groups);
    }

    @Override
    public FeedParentViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = from(parent.getContext())
                .inflate(R.layout.list_item_header, parent, false);
        return new FeedParentViewHolder(view);
    }

    @Override
    public FeedChildViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = from(parent.getContext())
                .inflate(R.layout.list_item_crop, parent, false);
        return new FeedChildViewHolder(view);
    }

    @Override
    public void onBindChildViewHolder(FeedChildViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        holder.setPosition(childIndex);
        holder.setParentPosition(flatPosition);
        final FeedChildren feedChildren = ((FeedParent) group).getItems().get(childIndex);
        holder.setChildName(feedChildren.getName());
        holder.setChildImageView(feedChildren.getResId());

    }

    @Override
    public void onBindGroupViewHolder(FeedParentViewHolder holder, int flatPosition, ExpandableGroup group) {
        holder.setFeedParentTitle(group);
        holder.setPosition(flatPosition);
    }
}
