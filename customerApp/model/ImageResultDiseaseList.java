package com.green0.customerApp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by ironman on 16/12/16.
 */

public class ImageResultDiseaseList implements Parcelable {
    public ImageResultDiseaseList(List<ImageResultDisease> imageResultDiseases) {
        this.imageResultDiseases = imageResultDiseases;
    }

    public List<ImageResultDisease> getImageResultDiseases() {
        return imageResultDiseases;
    }

    public void setImageResultDiseases(List<ImageResultDisease> imageResultDiseases) {
        this.imageResultDiseases = imageResultDiseases;
    }

    private List<ImageResultDisease> imageResultDiseases;
    protected ImageResultDiseaseList(Parcel in) {
    }

    public static final Creator<ImageResultDiseaseList> CREATOR = new Creator<ImageResultDiseaseList>() {
        @Override
        public ImageResultDiseaseList createFromParcel(Parcel in) {
            return new ImageResultDiseaseList(in);
        }

        @Override
        public ImageResultDiseaseList[] newArray(int size) {
            return new ImageResultDiseaseList[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
