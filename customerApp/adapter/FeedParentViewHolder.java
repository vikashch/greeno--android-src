package com.green0.customerApp.adapter;

import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ironman.myapplication.R;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import com.green0.customerApp.model.FeedParent;

import static android.view.animation.Animation.RELATIVE_TO_SELF;

/**
 * Created by ironman on 21/11/16.
 */

public class FeedParentViewHolder extends GroupViewHolder {

    private TextView headerName;
    private ImageView headerIcon,arrow;

    public void setPosition(int position) {
        this.position = position;
    }

    private int position ;

    public FeedParentViewHolder(View itemView) {
        super(itemView);
        headerIcon = (ImageView)itemView.findViewById(R.id.list_item_header_icon);
        headerName = (TextView)itemView.findViewById(R.id.list_item_header_name);
        arrow = (ImageView)itemView.findViewById(R.id.list_item_arrow);
    }

    public void setFeedParentTitle(ExpandableGroup feedParent){
        //TODO change to mulitype
        headerName.setText(feedParent.getTitle());
        headerIcon.setBackgroundResource(((FeedParent) feedParent).getIconResId());
    }

    @Override
    public void expand() {
        animateExpand();
    }

    @Override
    public void collapse() {
        animateCollapse();
    }

    private void animateExpand() {
        RotateAnimation rotate =
                new RotateAnimation(360, 180, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(300);
        rotate.setFillAfter(true);
        arrow.setAnimation(rotate);
    }

    private void animateCollapse() {
        RotateAnimation rotate =
                new RotateAnimation(180, 360, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(300);
        rotate.setFillAfter(true);
        arrow.setAnimation(rotate);
    }
}
