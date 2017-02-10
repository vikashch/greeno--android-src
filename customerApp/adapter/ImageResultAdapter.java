package com.green0.customerApp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ironman.myapplication.R;
import com.google.gson.Gson;
import com.green0.customerApp.activity.DiseaseInfoActivity;
import com.green0.customerApp.model.Disease;
import com.green0.customerApp.model.ImageResultDisease;

import java.util.List;

/**
 * Created by ironman on 7/12/16.
 */

public class ImageResultAdapter extends RecyclerView.Adapter<ImageResultAdapter.diseaseViewHolder>{
    private List<ImageResultDisease> diseaseList;

    public ImageResultAdapter(List<ImageResultDisease> diseaseList) {
        this.diseaseList = diseaseList;
    }

    @Override
    public diseaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_disease_percentage_row,parent,false);
         
        return new diseaseViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(diseaseViewHolder holder, int position) {
        ImageResultDisease imageResultDisease = diseaseList.get(position);
        holder.diseaseTitle.setText(imageResultDisease.getDisease().getName());

        // If a context is needed, it can be retrieved
        // from the ViewHolder's root view.
        Context context = holder.itemView.getContext();

        float percentage = Float.parseFloat(imageResultDisease.getScore())*100;
        holder.diseasePercentage.setText(Float.toString(percentage)+"%");
        holder.setChildPosition(position);

        holder.diseaseImage.setBackgroundResource(R.mipmap.tomato_early_blight);
    }

    @Override
    public int getItemCount() {
        return diseaseList.size();
    }

    public class diseaseViewHolder extends RecyclerView.ViewHolder{
        public TextView diseaseTitle;
        public TextView diseasePercentage;
        public int position;
        public ImageView diseaseImage;


        public int getChildPosition() {
            return position;
        }

        public void setChildPosition(int position) {
            this.position = position;
        }


        public diseaseViewHolder(View itemView) {
            // Very important to call the parent constructor
            // as this ensures that the itemView field is populated.
            super(itemView);
            diseaseTitle = (TextView)itemView.findViewById(R.id.diseaseTitle);
            diseasePercentage = (TextView)itemView.findViewById(R.id.accuracyPercentage);
            diseaseImage = (ImageView) itemView.findViewById(R.id.icon_disease);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    int pos = getAdapterPosition();
                    ImageResultDisease imageResultDisease  = diseaseList.get(pos);
                    Gson gson = new Gson();
                    String objStr = gson.toJson(imageResultDisease.getDisease());
                    Intent intent = new Intent(context, DiseaseInfoActivity.class);
                    intent.putExtra("obj",objStr);
                    context.startActivity(intent);
                    Toast.makeText(context,"lala u have selected "+pos,Toast.LENGTH_SHORT).show();

                }
            });
        }

    }
}
