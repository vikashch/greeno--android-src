package com.green0.customerApp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ironman on 7/12/16.
 */

public class ImageResultDisease {
   @SerializedName("category")
    private int category;
    @SerializedName("score")
    private String score;
    @SerializedName("disease")
    private Disease disease;

    public ImageResultDisease(int category, String score, Disease disease) {
        this.category = category;
        this.score = score;
        this.disease = disease;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Disease getDisease() {
        return disease;
    }

    public void setDisease(Disease disease) {
        this.disease = disease;
    }
}
