package com.green0.customerApp.model;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

/**
 * Created by ironman on 11/21/16.
 */

public class FeedParent extends ExpandableGroup<FeedChildren>{

    private int iconResId;

    public FeedParent(String title, List<FeedChildren> items,int iconResId) {
        super(title, items);
        this.iconResId = iconResId;
    }
    public int getIconResId() {
        return iconResId;
    }
}
