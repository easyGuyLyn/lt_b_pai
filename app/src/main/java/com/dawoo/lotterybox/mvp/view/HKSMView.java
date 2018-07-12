package com.dawoo.lotterybox.mvp.view;

import com.dawoo.lotterybox.bean.lottery.Handicap;
import com.dawoo.lotterybox.bean.lottery.HandicapWithOpening;

import java.util.List;

/**
 * Created by rain on 18-3-6.
 */

public interface HKSMView extends IBaseView{
    void onAwardResults(List<HandicapWithOpening> awardResultBeans);

    void onAwardResultsAndNoOpen(List<Handicap> awardResultBeans);

    //void getLottertOdds(Map<String,PlayTypeBean.PlayBean>)
}
