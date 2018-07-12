package com.dawoo.lotterybox.bean.lottery;

import android.os.Parcel;
import android.os.Parcelable;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;

/**
 * 获取近期数据（包含未开奖期）
 * Created by benson on 18-2-8.
 */

public class HandicapWithOpening extends Handicap implements MultiItemEntity ,Serializable{

    /**
     * fmOpenTime : 2018-01-10 18:20:00  时间文本（yyyy-mm-dd hh:mm:ss）
     * codeMemo : 重庆时时彩               游戏种类国际化（）
     */

    protected String fmOpenTime;
    protected String codeMemo;
    private int itemType;

    public String getFmOpenTime() {
        return fmOpenTime;
    }

    public void setFmOpenTime(String fmOpenTime) {
        this.fmOpenTime = fmOpenTime;
    }

    public String getCodeMemo() {
        return codeMemo;
    }

    public void setCodeMemo(String codeMemo) {
        this.codeMemo = codeMemo;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    @Override
    public int getItemType() {
        return itemType;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.fmOpenTime);
        dest.writeString(this.codeMemo);
        dest.writeInt(this.itemType);
    }

    public HandicapWithOpening() {
    }

    protected HandicapWithOpening(Parcel in) {
        super(in);
        this.fmOpenTime = in.readString();
        this.codeMemo = in.readString();
        this.itemType = in.readInt();
    }

    public static final Creator<HandicapWithOpening> CREATOR = new Creator<HandicapWithOpening>() {
        @Override
        public HandicapWithOpening createFromParcel(Parcel source) {
            return new HandicapWithOpening(source);
        }

        @Override
        public HandicapWithOpening[] newArray(int size) {
            return new HandicapWithOpening[size];
        }
    };
}
