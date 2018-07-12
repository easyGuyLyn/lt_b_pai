package com.dawoo.lotterybox.mvp.model.activity;

import com.dawoo.lotterybox.bean.BannerBean;
import com.dawoo.lotterybox.bean.Bulletin;
import com.dawoo.lotterybox.bean.PromoContentBean;
import com.dawoo.lotterybox.bean.PromoListBean;
import com.dawoo.lotterybox.mvp.model.BaseModel;
import com.dawoo.lotterybox.mvp.service.IActivityService;
import com.dawoo.lotterybox.net.RetrofitHelper;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;

/**
 * Created by b on 18-2-16.
 */

public class ActivityModel extends BaseModel implements IActivityModel {

    @Override
    public Subscription getBanner(Subscriber subscriber) {
        Observable<List<BannerBean>> observable = RetrofitHelper
                .getService(IActivityService.class)
                .getBanner()
                .map(new HttpResultFunc<List<BannerBean>>());
        return toSubscribe(observable, subscriber);
    }

    @Override
    public Subscription getBulletin(Subscriber subscriber) {
        Observable<List<Bulletin>> observable = RetrofitHelper
                .getService(IActivityService.class)
                .getBulletin()
                .map(new HttpResultFunc<List<Bulletin>>());
        return toSubscribe(observable, subscriber);
    }

    @Override
    public Subscription getPromoList(Subscriber subscriber, int pageSize, int pageNumber) {
        Observable<PromoListBean> observable = RetrofitHelper
                .getService(IActivityService.class)
                .getPromoList(pageSize, pageNumber);
        return toSubscribe(observable, subscriber);
    }

    @Override
    public Subscription getPromoContent(Subscriber subscriber, int id) {
        Observable<PromoContentBean> observable = RetrofitHelper
                .getService(IActivityService.class)
                .getPromoContent(id)
                .map(new HttpResultFunc<PromoContentBean>());
        return toSubscribe(observable, subscriber);
    }

    @Override
    public Subscription getCSLink(Subscriber subscriber) {
        Observable<String> observable = RetrofitHelper
                .getService(IActivityService.class)
                .getCustomerServiceLink()
                .map(new HttpResultFunc<String>());
        return toSubscribe(observable, subscriber);
    }
}
