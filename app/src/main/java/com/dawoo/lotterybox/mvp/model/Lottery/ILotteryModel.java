package com.dawoo.lotterybox.mvp.model.Lottery;


import rx.Subscriber;
import rx.Subscription;

/**
 * Created by benson on 18-2-8.
 */

public interface ILotteryModel {

    Subscription getRecentCloseExpect(Subscriber subscriber,String code);

    Subscription getLastOpenedAndOpeningResult(Subscriber subscriber);

    Subscription getLastOpenedAndOpeningResult(Subscriber subscriber, String code);

    Subscription getResultByCode(Subscriber subscriber, String code, String pageSize, String pageNumber);

    Subscription getRecentRecords(Subscriber subscriber, String code, String pageSize);

    Subscription getLotteryExpect(Subscriber subscriber, String code);

    Subscription getLotteryOdd(Subscriber subscriber, String code, String betCode);

    Subscription getLtToken(Subscriber subscriber);

    Subscription saveBetOrder(Subscriber subscriber, String token, String betForm);

    /**
     * 获取彩种类型及其子彩种的代号和名称
     *
     * @param subscriber
     * @return
     */
    Subscription getTypeAndLottery(Subscriber subscriber);

    Subscription getOrderInfo(Subscriber subscriber,String billNo,String code);

}
