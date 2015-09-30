package com.hackdelhi.dyingtocode.wallettracker;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Sarthak on 15-07-2015.
 */
public class Ishaandata implements Parcelable {


    String text;
    String url;

    Ishaandata(String text, String url) {

        this.text = text;
        this.url = url;
    }

    protected Ishaandata(Parcel in) {
        text = in.readString();
        url = in.readString();
    }

    public static final Creator<Ishaandata> CREATOR = new Creator<Ishaandata>() {
        @Override
        public Ishaandata createFromParcel(Parcel in) {
            return new Ishaandata(in);
        }

        @Override
        public Ishaandata[] newArray(int size) {
            return new Ishaandata[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(text);
        parcel.writeString(url);
    }
}
