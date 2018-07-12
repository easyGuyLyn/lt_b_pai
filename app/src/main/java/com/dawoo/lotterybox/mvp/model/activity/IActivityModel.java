package com.dawoo.lotterybox.mvp.model.activity;

import rx.Subscriber;
import rx.Subscription;

/**
 * Created by b on 18-2-16.
 */

public interface IActivityModel {


     Subscription getBanner(Subscriber subscriber);

     Subscription getBulletin(Subscriber subscriber);

     Subscription getPromoList(Subscriber subscriber , int pageSize, int pageNumber);

     Subscription getPromoContent(Subscriber subscriber ,int id);

     Subscription getCSLink(Subscriber subscriber);

}
