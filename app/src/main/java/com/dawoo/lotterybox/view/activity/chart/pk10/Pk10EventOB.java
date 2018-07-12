package com.dawoo.lotterybox.view.activity.chart.pk10;

import com.dawoo.lotterybox.view.activity.chart.*;
import com.dawoo.lotterybox.view.activity.chart.ChartDataBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 传送pk10到各个fragment的数据
 * Created by benson on 18-5-3.
 */

public class Pk10EventOB {
    public Pk10EventOB(List<ArrayList<ChartDataBean>> data, List<ArrayList<LinesBean>> linesData) {
        mData = data;
        mLinesData = linesData;
    }

    private List<ArrayList<ChartDataBean>> mData;
    private List<ArrayList<LinesBean>> mLinesData;

    public List<ArrayList<ChartDataBean>> getData() {
        return mData;
    }

    public void setData(List<ArrayList<ChartDataBean>> data) {
        mData = data;
    }

    public List<ArrayList<LinesBean>> getLinesData() {
        return mLinesData;
    }

    public void setLinesData(List<ArrayList<LinesBean>> linesData) {
        mLinesData = linesData;
    }
}
