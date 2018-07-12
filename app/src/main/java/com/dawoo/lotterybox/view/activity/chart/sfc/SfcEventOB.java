package com.dawoo.lotterybox.view.activity.chart.sfc;

import com.dawoo.lotterybox.view.activity.chart.ChartDataBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by benson on 18-5-3.
 */

public class SfcEventOB {
    private List<ArrayList<ChartDataBean>> mData;
    private List<ArrayList<Integer>> mLinesData;

    public SfcEventOB(List<ArrayList<ChartDataBean>> data, List<ArrayList<Integer>> linesData) {
        mData = data;
        mLinesData = linesData;
    }

    public List<ArrayList<ChartDataBean>> getData() {
        return mData;
    }

    public void setData(List<ArrayList<ChartDataBean>> data) {
        mData = data;
    }

    public List<ArrayList<Integer>> getLinesData() {
        return mLinesData;
    }

    public void setLinesData(List<ArrayList<Integer>> linesData) {
        mLinesData = linesData;
    }
}
