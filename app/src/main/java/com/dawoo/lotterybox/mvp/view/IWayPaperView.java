package com.dawoo.lotterybox.mvp.view;

import com.dawoo.lotterybox.bean.lottery.BaseHandicap;
import com.dawoo.lotterybox.bean.lottery.Handicap;
import com.dawoo.lotterybox.bean.lottery.HandicapWithOpening;

import java.util.List;

/**
 * Created by b on 18-3-18.
 */

public interface IWayPaperView extends  IBaseView{

    void onResultByCode(List<Handicap> handicapList);

    void onLotteryExpect(BaseHandicap baseHandicap);

}
