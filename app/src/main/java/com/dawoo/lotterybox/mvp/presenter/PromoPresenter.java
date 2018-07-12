package com.dawoo.lotterybox.mvp.presenter;

import android.content.Context;

import com.dawoo.lotterybox.bean.BannerBean;
import com.dawoo.lotterybox.bean.PromoContentBean;
import com.dawoo.lotterybox.bean.PromoListBean;
import com.dawoo.lotterybox.mvp.model.activity.ActivityModel;
import com.dawoo.lotterybox.mvp.view.IBaseView;
import com.dawoo.lotterybox.mvp.view.IHallView;
import com.dawoo.lotterybox.mvp.view.IPromoContentView;
import com.dawoo.lotterybox.mvp.view.IPromoListView;
import com.dawoo.lotterybox.net.rx.ProgressSubscriber;

import java.util.List;

import rx.Subscription;

/**
 * Created by b on 18-3-15.
 * 活动相关
 */

public class PromoPresenter<T extends IBaseView> extends BasePresenter {

    protected ActivityModel mActivityModel;

    public PromoPresenter(Context mContext, IBaseView view) {
        super(mContext, view);
        this.mContext  = mContext;
        this.mView = view;
        mActivityModel = new ActivityModel();
    }

    public void getPromoList(int pageNumber) {
        Subscription subscription = mActivityModel.getPromoList(new ProgressSubscriber(o ->
                ((IPromoListView) mView).onGetPromoList((PromoListBean) o), mContext)
        ,20,pageNumber);
        subList.add(subscription);
    }

    public void getPromoContent(int id){
        Subscription subscription = mActivityModel.getPromoContent(new ProgressSubscriber(o ->
                        ((IPromoContentView) mView).onPromoContent((PromoContentBean) o), mContext)
                ,id);
        subList.add(subscription);
    }
}
