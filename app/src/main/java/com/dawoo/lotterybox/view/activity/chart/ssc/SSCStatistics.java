package com.dawoo.lotterybox.view.activity.chart.ssc;

import android.os.Parcel;
import android.os.Parcelable;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 时时彩的统计
 * Created by benson on 18-2-27.
 */

public class SSCStatistics implements Parcelable, MultiItemEntity {
    private List<Integer> statistics; // 出现次数 平均遗漏 最大遗漏 最大连出
    private List<Integer> rowList; // 一排数据
    private String expect;// 期号 统计标题头
    private int itemType;

    public List<Integer> getStatistics() {
        return statistics;
    }

    public void setStatistics(List<Integer> statistics) {
        this.statistics = statistics;
    }

    public List<Integer> getRowList() {
        return rowList;
    }

    public void setRowList(List<Integer> rowList) {
        this.rowList = rowList;
    }

    public String getExpect() {
        return expect;
    }

    public void setExpect(String expect) {
        this.expect = expect;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public SSCStatistics() {
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.statistics);
        dest.writeList(this.rowList);
        dest.writeString(this.expect);
        dest.writeInt(this.itemType);
    }

    protected SSCStatistics(Parcel in) {
        this.statistics = new ArrayList<Integer>();
        in.readList(this.statistics, Integer.class.getClassLoader());
        this.rowList = new ArrayList<Integer>();
        in.readList(this.rowList, Integer.class.getClassLoader());
        this.expect = in.readString();
        this.itemType = in.readInt();
    }

    public static final Creator<SSCStatistics> CREATOR = new Creator<SSCStatistics>() {
        @Override
        public SSCStatistics createFromParcel(Parcel source) {
            return new SSCStatistics(source);
        }

        @Override
        public SSCStatistics[] newArray(int size) {
            return new SSCStatistics[size];
        }
    };
}
