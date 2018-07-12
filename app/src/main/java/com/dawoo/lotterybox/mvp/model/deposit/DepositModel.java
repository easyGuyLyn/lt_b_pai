package com.dawoo.lotterybox.mvp.model.deposit;

import com.dawoo.lotterybox.bean.Deposit.ParentDepositBean;
import com.dawoo.lotterybox.bean.Deposit.PayDetailBean;
import com.dawoo.lotterybox.bean.Deposit.SaleBean;
import com.dawoo.lotterybox.mvp.model.BaseModel;
import com.dawoo.lotterybox.mvp.service.IDepositServece;
import com.dawoo.lotterybox.net.BaseHttpResult;
import com.dawoo.lotterybox.net.RetrofitHelper;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;

/**
 * Created by rain on 18-4-30.
 */

public class DepositModel extends BaseModel implements IDepositModel {

    @Override
    public Subscription getPayWay(Subscriber subscriber,String type) {
        Observable<List<ParentDepositBean>> observable = RetrofitHelper
                .getService(IDepositServece.class)
                .getPayWay(type)
                .map(new HttpResultFunc<>());
        return toSubscribe(observable, subscriber);
    }

    /**
     * 获取公司入款信息
     *
     * @param subscriber
     * @return
     */
    @Override
    public Subscription getPayDetail(Subscriber subscriber,String type,String item) {
        Observable<List<PayDetailBean>> observable = RetrofitHelper
                .getService(IDepositServece.class)
                .getPayDetail(type,item)
                .map(new HttpResultFunc<>());
        return toSubscribe(observable, subscriber);
    }



    @Override
    public Subscription getFee(Subscriber subscriber, int payAccountId, double money) {
        Observable<Object> observable = RetrofitHelper
                .getService(IDepositServece.class)
                .getFee(payAccountId, money)
                .map(new HttpResultFunc<>());
        return toSubscribe(observable, subscriber);
    }

    @Override
    public Subscription postOnline(Subscriber subscriber, int payAccountId, double money) {
        Observable<Object> observable = RetrofitHelper
                .getService(IDepositServece.class)
                .postOnlinePay(payAccountId, money)
                .map(new HttpResultFunc<>());
        return toSubscribe(observable, subscriber);
    }

    @Override
    public Subscription callBackOrder(Subscriber subscriber, String billNO) {
        Observable<Object> observable = RetrofitHelper
                .getService(IDepositServece.class)
                .callBackOrder(billNO)
                .map(new HttpResultFunc<>());
        return toSubscribe(observable, subscriber);
    }

    @Override
    public Subscription getSales(Subscriber subscriber, String type) {
        Observable<List<SaleBean>> observable = RetrofitHelper
                .getService(IDepositServece.class)
                .getCompanySales(type)
                .map(new HttpResultFunc<>());
        return toSubscribe(observable, subscriber);
    }

    @Override
    public Subscription getSaleMoney(Subscriber subscriber, double money, int id) {
        Observable<Object> observable = RetrofitHelper
                .getService(IDepositServece.class)
                .getCompanySaleMoney(money, id)
                .map(new HttpResultFunc<>());
        return toSubscribe(observable, subscriber);
    }

    @Override
    public Subscription postCompanyPay(Subscriber subscriber, int payAccountId, double rechargeAmount, int favorableId, String orderNumber, String payerName) {
        Observable<BaseHttpResult> observable = RetrofitHelper
                .getService(IDepositServece.class)
                .postCompanyPay(payAccountId, String.valueOf(rechargeAmount), favorableId, orderNumber, payerName);
        return toSubscribe(observable, subscriber);
    }
}
