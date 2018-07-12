package com.dawoo.lotterybox.mvp.view;

import com.dawoo.lotterybox.bean.lottery.HandicapWithOpening;

import java.util.List;

/**
 * 最近的开奖结果
 * Created by benson on 18-2-19.
 */

public interface IRecentOpenRecView extends IBaseView{
    void onRecentRecResult(List<HandicapWithOpening> list);
    void onRefreshRecResult(List<HandicapWithOpening> list);
    void onLoadMoreRecResult(List<HandicapWithOpening> list);
}
