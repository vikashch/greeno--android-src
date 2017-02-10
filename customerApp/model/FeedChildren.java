package com.green0.customerApp.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ironman on 21/11/16.
 */

public class FeedChildren implements Parcelable {
    private String name;
    private int type;

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    private int resId;

    public  FeedChildren(String name , int type,int resId){
        this.name = name;
        this.type = type;
        this.resId=resId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    protected FeedChildren(Parcel in) {
        name = in.readString();
    }

    public static final Creator<FeedChildren> CREATOR = new Creator<FeedChildren>() {
        @Override
        public FeedChildren createFromParcel(Parcel in) {
            return new FeedChildren(in);
        }

        @Override
        public FeedChildren[] newArray(int size) {
            return new FeedChildren[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }
}
