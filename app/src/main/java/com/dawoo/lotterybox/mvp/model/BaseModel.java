package com.dawoo.lotterybox.mvp.model;

import android.provider.Telephony;

import com.dawoo.lotterybox.net.ApiException;
import com.dawoo.lotterybox.net.HttpResult;
import com.hwangjr.rxbus.RxBus;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by benson on 17-12-21.
 */

public class BaseModel {
    protected <T> Subscription toSubscribe(Observable<T> o, Subscriber<T> s) {
        Subscription subscription = o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s);

        return subscription;
    }

    /**
     * 用来统一处理Http的resultCode,并将HttpResult的Data部分剥离出来返回给subscriber
     *
     * @param <T> Subscriber真正需要的数据类型，也就是Data部分的数据类型
     */
    public class HttpResultFunc<T> implements Func1<HttpResult<T>, T> {

        @Override
        public T call(HttpResult<T> httpResult) {
            if (0 != httpResult.getError()) {
                throw new ApiException(httpResult.getCode(), httpResult.getMessage());

            }
            return httpResult.getData();
        }
    }

    public class HttpResultFunc2<T> implements Func1<HttpResult<T>, HttpResult<T>> {

        @Override
        public HttpResult<T> call(HttpResult<T> httpResult) {

            if (0 != httpResult.getError()) {
                throw new ApiException(httpResult.getCode(), httpResult.getMessage());
            }

            return httpResult;
        }
    }
}
