package com.dawoo.lotterybox.mvp.view;

import com.dawoo.lotterybox.bean.lottery.BaseHandicap;
import com.dawoo.lotterybox.bean.lottery.Handicap;
import com.dawoo.lotterybox.bean.lottery.HandicapWithOpening;
import com.dawoo.lotterybox.bean.lottery.LotteryOddBean;
import com.dawoo.lotterybox.bean.lottery.SaveOrderResult;

import java.util.List;
import java.util.Map;

/**
 * 基本的彩票view
 * Created by benson on 18-3-11.
 */

public interface IBaseLotteryView extends IBaseView {
    void onResultByCode(List<Handicap> handicapList);

    void onOneResultByCode(List<Handicap> handicapList);

    void onRecentCloseExpect(Handicap handicap);

    void onRecentRecords(List<HandicapWithOpening> handicapWithOpeningList);

    void onLotteryExpect(BaseHandicap baseHandicap);

    void onLotteryOdd(Map<String, LotteryOddBean> o);

    void onLtToken(String token);

    void onSaveBetOrder(SaveOrderResult saveOrderResult);

    void onSureBetOrder();

}
