package com.erdem.sert.hw2.Activities;

import android.os.Parcel;
import android.os.Parcelable;

public class Meal implements Parcelable {

    private String name;
    private int imgIds;
    private int type;

    public Meal(String name, int imgIds,int type) {
        this.name = name;
        this.imgIds = imgIds;
        this.type=type;
    }

    protected Meal(Parcel in) {
        name = in.readString();
        imgIds = in.readInt();
        type = in.readInt();
    }

    public static final Creator<Meal> CREATOR = new Creator<Meal>() {
        @Override
        public Meal createFromParcel(Parcel in) {
            return new Meal(in);
        }

        @Override
        public Meal[] newArray(int size) {
            return new Meal[size];
        }
    };

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

    public int getImgIds() {
        return imgIds;
    }

    public void setImgIds(int imgIds) {
        this.imgIds = imgIds;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(imgIds);
        parcel.writeInt(type);
    }
}
