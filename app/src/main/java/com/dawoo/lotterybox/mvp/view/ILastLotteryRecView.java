package com.dawoo.lotterybox.mvp.view;

import com.dawoo.lotterybox.bean.lottery.BaseHandicap;
import com.dawoo.lotterybox.bean.lottery.Handicap;
import com.dawoo.lotterybox.bean.lottery.LotteryLastOpenAndOpening;

import java.util.List;

/**
 * 最新一期的开奖记录和将要开奖的彩票倒计时
 * Created by benson on 18-2-13.
 */

public interface ILastLotteryRecView extends IBaseView {
    void onLotteryExpect(BaseHandicap baseHandicap);

    void onLastLotteryRecResult(List<LotteryLastOpenAndOpening> lastOpenAndOpenings);

    void getNextRec();

    void onRecentCloseExpect(Handicap handicap);

    void onRefreshResult(List<LotteryLastOpenAndOpening> lastOpenAndOpenings);

    void onRefreshByCodeResult(List<LotteryLastOpenAndOpening> lastOpenAndOpening);
}
