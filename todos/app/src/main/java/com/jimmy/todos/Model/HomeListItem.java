package com.jimmy.todos.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Jimmy on 2017/7/5.
 */

public class HomeListItem implements Parcelable {
    public boolean isDone;
    public String name;
    public long createTime;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.isDone ? (byte) 1 : (byte) 0);
        dest.writeString(this.name);
        dest.writeLong(this.createTime);
    }

    public HomeListItem() {
    }

    protected HomeListItem(Parcel in) {
        this.isDone = in.readByte() != 0;
        this.name = in.readString();
        this.createTime = in.readLong();
    }

    public static final Parcelable.Creator<HomeListItem> CREATOR = new Parcelable.Creator<HomeListItem>() {
        @Override
        public HomeListItem createFromParcel(Parcel source) {
            return new HomeListItem(source);
        }

        @Override
        public HomeListItem[] newArray(int size) {
            return new HomeListItem[size];
        }
    };
}
