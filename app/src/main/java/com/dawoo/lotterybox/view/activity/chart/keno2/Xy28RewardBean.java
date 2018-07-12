package com.dawoo.lotterybox.view.activity.chart.keno2;

import com.dawoo.lotterybox.bean.lottery.Handicap;
import com.dawoo.lotterybox.view.activity.chart.ChartDataBean;
import com.dawoo.lotterybox.view.activity.chart.pk10.LinesBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by archar on 18-4-24.
 */

public class Xy28RewardBean {
    private ArrayList<Handicap> mHandicaps;

    public Xy28RewardBean(ArrayList<Handicap> handicaps) {
        mHandicaps = handicaps;
    }

    public ArrayList<Handicap> getHandicaps() {
        return mHandicaps;
    }

    public void setHandicaps(ArrayList<Handicap> handicaps) {
        mHandicaps = handicaps;
    }
}
