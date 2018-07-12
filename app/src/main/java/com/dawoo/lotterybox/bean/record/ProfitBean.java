package com.dawoo.lotterybox.bean.record;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by archar on 18-2-19.
 */

public class ProfitBean implements Parcelable {
    private String tdate;
    private double profit;

    public String getTdate() {
        return tdate;
    }

    public void setTdate(String tdate) {
        this.tdate = tdate;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.tdate);
        dest.writeDouble(this.profit);
    }

    public ProfitBean() {
    }

    protected ProfitBean(Parcel in) {
        this.tdate = in.readString();
        this.profit = in.readDouble();
    }

    public static final Parcelable.Creator<ProfitBean> CREATOR = new Parcelable.Creator<ProfitBean>() {
        @Override
        public ProfitBean createFromParcel(Parcel source) {
            return new ProfitBean(source);
        }

        @Override
        public ProfitBean[] newArray(int size) {
            return new ProfitBean[size];
        }
    };
}
