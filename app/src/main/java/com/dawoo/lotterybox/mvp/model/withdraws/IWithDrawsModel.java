package com.dawoo.lotterybox.mvp.model.withdraws;

import rx.Subscriber;
import rx.Subscription;

/**
 * Created by alex on 18-4-5.
 *
 * @author alex
 */

public interface IWithDrawsModel {


    Subscription bankcardList(Subscriber subscriber);

    Subscription getBanks(Subscriber subscriber);

    Subscription bindBankCard(Subscriber subscriber, String bankCode, String bankName, String cardNumber, String masterName);

    Subscription getWithDrawsFee(Subscriber subscriber, String money);

    Subscription withDrawsInit(Subscriber subscriber);

    Subscription withDrawsAudit(Subscriber subscriber);
    Subscription withDrawsAuditDetail(Subscriber subscriber,String id);

    Subscription applyWithDraws(Subscriber subscriber, String withdrawAmount);
}
