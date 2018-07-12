package com.dawoo.lotterybox.mvp.view;

import com.dawoo.lotterybox.bean.lottery.HandicapWithOpening;

import java.util.List;

/**
 * Created by b on 18-2-27.
 */

public interface ILotteryAWayPaperView extends IBaseView{
    void onAwardResultsAndNoOpen(List<HandicapWithOpening> awardResultBeans);
}
