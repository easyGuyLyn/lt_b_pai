package com.dawoo.lotterybox.mvp.presenter;

import android.content.Context;

import com.dawoo.lotterybox.ConstantValue;
import com.dawoo.lotterybox.mvp.view.IBaseView;
import com.dawoo.lotterybox.util.SingleToast;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;

/**
 * Created by benson on 17-12-21.
 */

public abstract class BasePresenter<T extends IBaseView> {
    protected Context mContext;
    protected T mView;
    protected List<Subscription> subList = new ArrayList<>();

    public BasePresenter(Context mContext, T view) {
        this.mContext = mContext;
        this.mView = view;
    }

    /**
     * 绑定View
     */
    public void onAttch(T view) {
        this.mView = view;

    }


    public void ondetach() {
        mView = null;
    }

    public void onDestory() {
        this.mContext = null;
        mView = null;
        clearSubscription();
    }

    void clearSubscription() {
        for (Subscription subscription : subList) {
            if (subscription != null && !subscription.isUnsubscribed()) {
                subscription.unsubscribe();
            }
        }
    }

    /**
     * 对 ACTIVITY 生命周期进行管理
     *
     * @return
     */
    protected LifecycleProvider getLifecycleProvider() {
        LifecycleProvider provider = null;
        if (null != mContext && mContext instanceof LifecycleProvider) {
            provider = (LifecycleProvider) mContext;
        }
        return provider;
    }


}

