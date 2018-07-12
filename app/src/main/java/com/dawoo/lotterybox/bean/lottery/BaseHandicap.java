package com.dawoo.lotterybox.bean.lottery;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 盘口
 * Created by benson on 18-2-8.
 */

public class BaseHandicap implements Parcelable {
    /**
     * expect : 20180110001        期号
     * leftTime : 9668             距离封盘时间
     * leftOpenTime : 9188         距离下期开盘时间
     * opening : true              是否开盘
     */

    protected String expect;
    protected int leftTime;
    protected int leftOpenTime;
    protected boolean opening;

    public static final Creator<BaseHandicap> CREATOR = new Creator<BaseHandicap>() {
        @Override
        public BaseHandicap createFromParcel(Parcel in) {
            return new BaseHandicap(in);
        }

        @Override
        public BaseHandicap[] newArray(int size) {
            return new BaseHandicap[size];
        }
    };

    public String getExpect() {
        return expect;
    }

    public void setExpect(String expect) {
        this.expect = expect;
    }

    public int getLeftTime() {
        return leftTime;
    }

    public void setLeftTime(int leftTime) {
        this.leftTime = leftTime;
    }

    public int getLeftOpenTime() {
        return leftOpenTime;
    }

    public void setLeftOpenTime(int leftOpenTime) {
        this.leftOpenTime = leftOpenTime;
    }

    public boolean isOpening() {
        return opening;
    }

    public void setOpening(boolean opening) {
        this.opening = opening;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.expect);
        dest.writeInt(this.leftTime);
        dest.writeInt(this.leftOpenTime);
        dest.writeByte(this.opening ? (byte) 1 : (byte) 0);
    }

    public BaseHandicap() {
    }

    protected BaseHandicap(Parcel in) {
        this.expect = in.readString();
        this.leftTime = in.readInt();
        this.leftOpenTime = in.readInt();
        this.opening = in.readByte() != 0;
    }

}
