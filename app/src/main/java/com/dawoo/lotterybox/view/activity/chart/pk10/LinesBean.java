package com.dawoo.lotterybox.view.activity.chart.pk10;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by b on 18-4-5.
 */

public class LinesBean implements Parcelable{

    private int ballIndex;
    private int bigSmall;
    private int singleDouble;

    public LinesBean(){}

    public LinesBean(int ballIndex, int bigSmall , int singleDouble){
        this.ballIndex = ballIndex;
        this.bigSmall = bigSmall;
        this.singleDouble = singleDouble;
    }

    protected LinesBean(Parcel in) {
        ballIndex = in.readInt();
        bigSmall = in.readInt();
        singleDouble = in.readInt();
    }

    public static final Creator<LinesBean> CREATOR = new Creator<LinesBean>() {
        @Override
        public LinesBean createFromParcel(Parcel in) {
            return new LinesBean(in);
        }

        @Override
        public LinesBean[] newArray(int size) {
            return new LinesBean[size];
        }
    };

    public int getBallIndex() {
        return ballIndex;
    }

    public void setBallIndex(int ballIndex) {
        this.ballIndex = ballIndex;
    }

    public int getBigSmall() {
        return bigSmall;
    }

    public void setBigSmall(int bigSmall) {
        this.bigSmall = bigSmall;
    }

    public int getSingleDouble() {
        return singleDouble;
    }

    public void setSingleDouble(int singleDouble) {
        this.singleDouble = singleDouble;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ballIndex);
        dest.writeInt(bigSmall);
        dest.writeInt(singleDouble);
    }
}
