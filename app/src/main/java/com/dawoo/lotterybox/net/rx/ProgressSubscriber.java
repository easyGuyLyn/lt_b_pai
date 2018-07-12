package com.dawoo.lotterybox.net.rx;

import android.content.Context;
import android.widget.Toast;

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
public class ProgressSubscriber<T> extends Subscriber<T> implements ProgressCancelListener {

    private SubscriberOnNextListener mSubscriberOnNextListener;
    private ProgressDialogHandler mProgressDialogHandler;

    private Context context;

    public ProgressSubscriber(SubscriberOnNextListener mSubscriberOnNextListener, Context context) {
        this.mSubscriberOnNextListener = mSubscriberOnNextListener;
        this.context = BoxApplication.getContext();
        mProgressDialogHandler = new ProgressDialogHandler(context, this, false);
    }

    public ProgressSubscriber(SubscriberOnNextListener mSubscriberOnNextListener, Context context, boolean isNeedDialog) {
        this.mSubscriberOnNextListener = mSubscriberOnNextListener;
        this.context = context;
        if (isNeedDialog) {
            mProgressDialogHandler = new ProgressDialogHandler(context, this, false);
        }
    }

    private void showProgressDialog() {
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG).sendToTarget();
        }
    }

    private void dismissProgressDialog() {
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG).sendToTarget();
            mProgressDialogHandler = null;
        }
    }

    /**
     * 订阅开始时调用
     * 显示ProgressDialog
     */
    @Override
    public void onStart() {
        showProgressDialog();
    }

    /**
     * 完成，隐藏ProgressDialog
     */
    @Override
    public void onCompleted() {
        dismissProgressDialog();
        //    Toast.makeText(context, "Get Top Movie Completed", Toast.LENGTH_SHORT).show();
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
            //不要显示这东西，界面就交给界面
           // SingleToast.showMsg(e.getMessage());
        }
        dismissProgressDialog();

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

    /**
     * 取消ProgressDialog的时候，取消对observable的订阅，同时也取消了http请求
     */
    @Override
    public void onCancelProgress() {
        if (!this.isUnsubscribed()) {
            this.unsubscribe();
        }
    }
}