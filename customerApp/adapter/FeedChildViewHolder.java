package com.green0.customerApp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ironman.myapplication.R;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

import com.green0.customerApp.activity.cropActionsActivity;

/**
 * Created by ironman on 21/11/16.
 */

public class   FeedChildViewHolder extends ChildViewHolder{
    private TextView childTextView;


    private ImageView childImageView;
    private int position;
    private int parentPosition;
    private CardView childCardView;

    public void setParentPosition(int parentPosition) {
        this.parentPosition = parentPosition;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public FeedChildViewHolder(View itemView) {
        super(itemView);

        childTextView = (TextView) itemView.findViewById(R.id.txt_cropname);
        childCardView = (CardView)itemView.findViewById(R.id.card_crop_view);
        childImageView = (ImageView)itemView.findViewById(R.id.icon_crop);
        childCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackbar = Snackbar
                        .make(v, "item position is "+ position, Snackbar.LENGTH_LONG)
                        .setAction("UNDO", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Snackbar snackbar1 = Snackbar.make(view, "parent position is "+ parentPosition, Snackbar.LENGTH_SHORT);
                                snackbar1.show();

                            }
                        });

                snackbar.show();
                Context context = v.getContext();
                Intent intent = new Intent(context, cropActionsActivity.class);
                intent.addCategory(childTextView.getText().toString());
                context.startActivity(intent);

            }
        });
    }

    public void setChildName(String name){
        childTextView.setText(name);
    }
    public  void setChildImageView(int resId) {
        childImageView.setBackgroundResource(resId);
    }

}
