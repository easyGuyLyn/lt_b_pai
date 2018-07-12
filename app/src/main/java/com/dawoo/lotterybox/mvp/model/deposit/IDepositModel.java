package com.dawoo.lotterybox.mvp.model.deposit;

import com.hwangjr.rxbus.annotation.Subscribe;

import rx.Subscriber;
import rx.Subscription;

/**
 * Created by rain on 18-4-30.
 */

public interface IDepositModel {
    Subscription getPayWay(Subscriber subscriber,String type);


    Subscription getPayDetail(Subscriber subscriber,String type,String item);

    Subscription getFee(Subscriber subscriber, int payAccountId,double money);

    Subscription postOnline(Subscriber subscriber,int payAccountId,double depositAmount);

    Subscription callBackOrder(Subscriber subscriber,String billNO);


    /*
    公司入款
     */
    Subscription getSales(Subscriber subscriber,String type);

    Subscription getSaleMoney(Subscriber subscriber,double money,int id);

    Subscription postCompanyPay(Subscriber subscriber,int payAccountId,double rechargeAmount, int favorableId, String orderNumber, String payerName);
}
