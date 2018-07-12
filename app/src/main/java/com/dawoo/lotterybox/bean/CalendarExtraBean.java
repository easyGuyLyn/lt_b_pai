package com.dawoo.lotterybox.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by archar on 18-2-15.
 */

public class CalendarExtraBean implements Parcelable {
    private long time;
    private Double value;
    private String timeData;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getTimeData() {
        return timeData;
    }

    public void setTimeData(String timeData) {
        this.timeData = timeData;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.time);
        dest.writeValue(this.value);
        dest.writeString(this.timeData);
    }

    public CalendarExtraBean() {
    }

    protected CalendarExtraBean(Parcel in) {
        this.time = in.readLong();
        this.value = (Double) in.readValue(Double.class.getClassLoader());
        this.timeData = in.readString();
    }

    public static final Parcelable.Creator<CalendarExtraBean> CREATOR = new Parcelable.Creator<CalendarExtraBean>() {
        @Override
        public CalendarExtraBean createFromParcel(Parcel source) {
            return new CalendarExtraBean(source);
        }

        @Override
        public CalendarExtraBean[] newArray(int size) {
            return new CalendarExtraBean[size];
        }
    };
}
