package com.dawoo.lotterybox.view.activity.chart.ssc;

import com.dawoo.lotterybox.bean.lottery.Handicap;
import com.dawoo.lotterybox.bean.lottery.HandicapWithOpening;
import com.dawoo.lotterybox.bean.playtype.PlayTypeBean;

import java.util.ArrayList;

/**
 * Created by benson on 18-3-8.
 */

public class TempObject {
    ArrayList<Handicap> mRencentList;
    PlayTypeBean.PlayBean playBean;

    public TempObject() {
    }

    public TempObject(ArrayList<Handicap> rencentList, PlayTypeBean.PlayBean playBean) {
        mRencentList = rencentList;
        this.playBean = playBean;
    }

    public ArrayList<Handicap> getRencentList() {
        return mRencentList;
    }

    public void setRencentList(ArrayList<Handicap> rencentList) {
        mRencentList = rencentList;
    }

    public PlayTypeBean.PlayBean getPlayBean() {
        return playBean;
    }

    public void setPlayBean(PlayTypeBean.PlayBean playBean) {
        this.playBean = playBean;
    }
}
