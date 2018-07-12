package com.dawoo.lotterybox.view.activity.chart.keno2;

import com.dawoo.lotterybox.view.activity.chart.pk10.LinesBean;
import com.dawoo.lotterybox.view.activity.chart.ChartDataBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by archar on 18-4-24.
 */

public class Xy28EventBean {
    private List<ArrayList<ChartDataBean>> data;
    private List<ArrayList<LinesBean>> linesData;

    public Xy28EventBean(List<ArrayList<ChartDataBean>> data, List<ArrayList<LinesBean>> linesData) {
        this.data = data;
        this.linesData = linesData;
    }

    public List<ArrayList<ChartDataBean>> getData() {
        return data;
    }

    public void setData(List<ArrayList<ChartDataBean>> data) {
        this.data = data;
    }

    public List<ArrayList<LinesBean>> getLinesData() {
        return linesData;
    }

    public void setLinesData(List<ArrayList<LinesBean>> linesData) {
        this.linesData = linesData;
    }
}
