package com.dawoo.lotterybox.mvp.presenter;

import android.content.Context;

import com.dawoo.lotterybox.bean.lottery.LotteryLastOpenAndOpening;
import com.dawoo.lotterybox.bean.lottery.OrderInfo;
import com.dawoo.lotterybox.mvp.model.Lottery.LotteryModel;
import com.dawoo.lotterybox.mvp.view.IBaseView;
import com.dawoo.lotterybox.mvp.view.ILastLotteryRecView;
import com.dawoo.lotterybox.mvp.view.IOrderView;
import com.dawoo.lotterybox.net.rx.ProgressSubscriber;

import java.util.List;

import rx.Subscription;

/**
 * Created by b on 18-4-18.
 * 注单信息
 */

public class OrderInfoPresenter<T extends IBaseView> extends BasePresenter {
    private final LotteryModel mModel;
    private T mView;

    public OrderInfoPresenter(Context mContext, T view) {
        super(mContext, view);
        this.mView = view;
        mModel = new LotteryModel();
    }

    /**
     * 获取注单信息
     */
    public void getOrderInfo(String billNo, String code) {
        Subscription subscription = mModel.getOrderInfo(new ProgressSubscriber(o ->
                        ((IOrderView) mView).onOrderResult((OrderInfo) o), mContext)
                , billNo,code);
        subList.add(subscription);
    }

}
