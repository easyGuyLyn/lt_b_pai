package com.dawoo.lotterybox.mvp.model.withdraws;

import com.dawoo.lotterybox.bean.Bank;
import com.dawoo.lotterybox.bean.MyBandCard;
import com.dawoo.lotterybox.bean.WithDrawBean;
import com.dawoo.lotterybox.bean.WithDrawsAduitBean;
import com.dawoo.lotterybox.bean.WithDrawsDetailBean;
import com.dawoo.lotterybox.bean.WithDrawsFeeBean;
import com.dawoo.lotterybox.mvp.model.BaseModel;
import com.dawoo.lotterybox.mvp.service.ISettingService;
import com.dawoo.lotterybox.mvp.service.IUserService;
import com.dawoo.lotterybox.mvp.service.IWithDrawsService;

import com.dawoo.lotterybox.net.RetrofitHelper;

import java.util.HashMap;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;

/**
 * Created by alex on 18-4-5.
 *
 * @author alex
 */

public class WithDrawsModel extends BaseModel implements IWithDrawsModel {

    /**
     * 我的银行卡
     */
    @Override
    public Subscription bankcardList(Subscriber subscriber) {
        Observable<List<MyBandCard>> observable = RetrofitHelper.getService(IWithDrawsService.class).bankcardList().map(new HttpResultFunc<>());
        return toSubscribe(observable, subscriber);
    }

    /**
     * 可绑定的银行卡列表
     *
     * @param subscriber
     * @return
     */
    @Override
    public Subscription getBanks(Subscriber subscriber) {
        Observable<List<Bank>> observable = RetrofitHelper.getService(IWithDrawsService.class).getBanks().map(new HttpResultFunc<>());
        return toSubscribe(observable, subscriber);
    }

    /**
     * 绑定银行卡
     *
     * @param subscriber
     * @param bankCode
     * @param bankName
     * @param cardNumber
     * @param masterName
     * @return
     */
    @Override
    public Subscription bindBankCard(Subscriber subscriber, String bankCode, String bankName, String cardNumber, String masterName) {
        Observable<Object> observable = RetrofitHelper.getService(IWithDrawsService.class).bindBankCard(bankCode, bankName, cardNumber, masterName).map(new HttpResultFunc<>());
        return toSubscribe(observable, subscriber);
    }

    @Override
    public Subscription getWithDrawsFee(Subscriber subscriber, String money) {
        Observable<WithDrawsFeeBean> observable = RetrofitHelper.getService(IWithDrawsService.class).getWithDrawsFee(money).map(new HttpResultFunc<>());
        return toSubscribe(observable, subscriber);
    }

    @Override
    public Subscription withDrawsInit(Subscriber subscriber) {
        Observable<WithDrawBean> observable = RetrofitHelper.getService(IWithDrawsService.class).withDrawsInit().map(new HttpResultFunc<>());
        return toSubscribe(observable, subscriber);
    }

    @Override
    public Subscription withDrawsAudit(Subscriber subscriber) {
        Observable<List<WithDrawsAduitBean>> observable = RetrofitHelper.getService(IWithDrawsService.class).getWithDrawsAudit().map(new HttpResultFunc<>());
        return toSubscribe(observable, subscriber);
    }

    @Override
    public Subscription withDrawsAuditDetail(Subscriber subscriber, String id) {
        Observable<WithDrawsDetailBean> observable = RetrofitHelper.getService(IWithDrawsService.class).getWithDrawsAuditDetail(id).map(new HttpResultFunc<>());
        return toSubscribe(observable, subscriber);

    }

    @Override
    public Subscription applyWithDraws(Subscriber subscriber, String withdrawAmount) {
        Observable<Object> observable = RetrofitHelper.getService(IWithDrawsService.class).applyWithDraws(withdrawAmount).map(new HttpResultFunc<>());
        return toSubscribe(observable, subscriber);
    }
}
