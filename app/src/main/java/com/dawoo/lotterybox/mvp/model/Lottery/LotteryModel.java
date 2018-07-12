package com.dawoo.lotterybox.mvp.model.Lottery;

import com.dawoo.lotterybox.bean.BetOrderABean;
import com.dawoo.lotterybox.bean.BetOrderBean;
import com.dawoo.lotterybox.bean.BetOrdersListBean;
import com.dawoo.lotterybox.bean.TypeAndLotteryBean;
import com.dawoo.lotterybox.bean.lottery.BaseHandicap;
import com.dawoo.lotterybox.bean.lottery.Handicap;
import com.dawoo.lotterybox.bean.lottery.HandicapWithOpening;
import com.dawoo.lotterybox.bean.lottery.LotteryLastOpenAndOpening;
import com.dawoo.lotterybox.bean.lottery.LotteryOddBean;
import com.dawoo.lotterybox.bean.lottery.LotteryType;
import com.dawoo.lotterybox.bean.lottery.OrderInfo;
import com.dawoo.lotterybox.bean.lottery.SaveOrderResult;
import com.dawoo.lotterybox.mvp.model.BaseModel;
import com.dawoo.lotterybox.mvp.service.ILotteryService;
import com.dawoo.lotterybox.net.RetrofitHelper;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okio.Utf8;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;

/**
 * Created by benson on 18-2-8.
 */

public class LotteryModel extends BaseModel implements ILotteryModel {


    @Override
    public Subscription getResultByCode(Subscriber subscriber, String code, String pageSize, String pageNumber) {
        Observable<List<Handicap>> observable = RetrofitHelper
                .getService(ILotteryService.class)
                .getResultByCode(code, pageSize, pageNumber)
                .map(new HttpResultFunc<>());
        return toSubscribe(observable, subscriber);
    }

    @Override
    public Subscription getRecentRecords(Subscriber subscriber, String code, String pageSize) {
        Observable<List<HandicapWithOpening>> observable = RetrofitHelper
                .getService(ILotteryService.class)
                .getRecentRecords(code, pageSize)
                .map(new HttpResultFunc<>());
        return toSubscribe(observable, subscriber);
    }

    @Override
    public Subscription getLotteryExpect(Subscriber subscriber, String code) {
        Observable<BaseHandicap> observable = RetrofitHelper
                .getService(ILotteryService.class)
                .getLotteryExpect(code)
                .map(new HttpResultFunc<>());
        return toSubscribe(observable, subscriber);
    }


    @Override
    public Subscription getRecentCloseExpect(Subscriber subscriber, String code) {
        Observable<Handicap> observable = RetrofitHelper
                .getService(ILotteryService.class)
                .getRecentCloseExpect(code)
                .map(new HttpResultFunc<>());
        return toSubscribe(observable, subscriber);
    }

    @Override
    public Subscription getLastOpenedAndOpeningResult(Subscriber subscriber) {
        Observable<List<LotteryLastOpenAndOpening>> observable = RetrofitHelper
                .getService(ILotteryService.class)
                .getLastOpenedAndOpeningResult()
                .map(new HttpResultFunc<>());
        return toSubscribe(observable, subscriber);
    }

    @Override
    public Subscription getLastOpenedAndOpeningResult(Subscriber subscriber, String code) {
        Observable<List<LotteryLastOpenAndOpening>> observable = RetrofitHelper
                .getService(ILotteryService.class)
                .getLastOpenedAndOpeningResult(code)
                .map(new HttpResultFunc<>());
        return toSubscribe(observable, subscriber);
    }

    @Override
    public Subscription getLotteryOdd(Subscriber subscriber, String code, String betCode) {
        Observable<Map<String, LotteryOddBean>> observable = RetrofitHelper
                .getService(ILotteryService.class)
                .getLotteryOdd(code, betCode)
                .map(new HttpResultFunc<>());
        return toSubscribe(observable, subscriber);
    }

    @Override
    public Subscription getLtToken(Subscriber subscriber) {
        Observable<String> observable = RetrofitHelper
                .getService(ILotteryService.class)
                .getLtToken()
                .map(new HttpResultFunc<>());
        return toSubscribe(observable, subscriber);
    }

    @Override
    public Subscription saveBetOrder(Subscriber subscriber, String token, String betForm) {
        Observable<SaveOrderResult> observable = RetrofitHelper
                    .getService(ILotteryService.class)
                    .saveBetOrder(token,
                            betForm
                    );
        return toSubscribe(observable, subscriber);
    }


    @Override
    public Subscription getTypeAndLottery(Subscriber subscriber) {
        Observable<List<TypeAndLotteryBean>> observable = RetrofitHelper
                .getService(ILotteryService.class)
                .getTypeAndLottery()
                .map(new HttpResultFunc<>());
        return toSubscribe(observable, subscriber);
    }

    @Override
    public Subscription getOrderInfo(Subscriber subscriber, String billNo, String code) {
        Observable<OrderInfo> observable = RetrofitHelper
                .getService(ILotteryService.class)
                .getOrderInfo(billNo,
                        code).map(new HttpResultFunc<>());
        return toSubscribe(observable, subscriber);
    }

}
