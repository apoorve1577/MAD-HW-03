package com.example.hw_04;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Date;

public class Drink implements Serializable, Parcelable {

    int size;
    int percent;
    Date createDatetime;

    public Drink(int size, int percent, Date createDatetime) {
        this.size = size;
        this.percent = percent;
        this.createDatetime = createDatetime;
    }

    public Drink(){

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }
}
