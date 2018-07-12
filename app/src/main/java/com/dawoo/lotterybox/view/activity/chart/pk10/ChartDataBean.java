package com.dawoo.lotterybox.view.activity.chart.pk10;

import android.os.Parcel;
import android.os.Parcelable;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * Created by b on 18-4-5.
 */

public class ChartDataBean implements Parcelable, MultiItemEntity {

    public static final int ITEM_TYPE_CHART = 1;
    public static final int ITEM_TYPE_STATISTIC = 2;

    List<Integer> rowList;  //一行数据
    private String expect;  // 期号 统计标题头
    private int itemType;

    public ChartDataBean(){}

    protected ChartDataBean(Parcel in) {
        expect = in.readString();
        itemType = in.readInt();
    }

    public static final Creator<ChartDataBean> CREATOR = new Creator<ChartDataBean>() {
        @Override
        public ChartDataBean createFromParcel(Parcel in) {
            return new ChartDataBean(in);
        }

        @Override
        public ChartDataBean[] newArray(int size) {
            return new ChartDataBean[size];
        }
    };

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

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(expect);
        dest.writeInt(itemType);
    }
}
