package com.dawoo.lotterybox.net.rx;

import android.content.Context;

import com.dawoo.lotterybox.BoxApplication;
import com.dawoo.lotterybox.ConstantValue;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.util.SingleToast;
import com.hwangjr.rxbus.RxBus;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import rx.Subscriber;

/**
 * 用于在Http请求开始时，自动显示一个ProgressDialog
 * 在Http请求结束是，关闭ProgressDialog
 * 调用者自己对请求数据进行处理
 */
public class DefaultSubscriber<T> extends Subscriber<T> {

    private SubscriberOnNextListener mSubscriberOnNextListener;
    private DefaultCallback mDefaultCallback;

    public DefaultSubscriber(SubscriberOnNextListener mSubscriberOnNextListener, DefaultCallback defaultCallback) {
        this.mSubscriberOnNextListener = mSubscriberOnNextListener;
        this.mDefaultCallback = defaultCallback;

    }


    /**
     * 订阅开始时调用
     * 显示ProgressDialog
     */
    @Override
    public void onStart() {
        mDefaultCallback.start();
    }

    /**
     * 完成，隐藏ProgressDialog
     */
    @Override
    public void onCompleted() {
        mDefaultCallback.complete();
    }

    /**
     * 对错误进行统一处理
     * 隐藏ProgressDialog
     *
     * @param e
     */
    @Override
    public void onError(Throwable e) {
        if (e instanceof SocketTimeoutException) {
            SingleToast.showMsg(BoxApplication.getContext().getString(R.string.Network_Connection_timeout));
        } else if (e instanceof ConnectException) {
            SingleToast.showMsg(BoxApplication.getContext().getString(R.string.no_network));
        } else {
            SingleToast.showMsg(e.getMessage());
        }


        RxBus.get().post(ConstantValue.EVENT_TYPE_NETWORK_EXCEPTION, "" + e.getMessage());
    }

    /**
     * 将onNext方法中的返回结果交给Activity或Fragment自己处理
     *
     * @param t 创建Subscriber时的泛型类型
     */
    @Override
    public void onNext(T t) {
        if (mSubscriberOnNextListener != null) {
            mSubscriberOnNextListener.onNext(t);
        }
    }

}