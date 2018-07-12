package com.dawoo.lotterybox.util;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by alex on 18-4-30.
 *
 * @author alex
 */

public class RxUtils {
    //子线程执行
    public static <T> void doOnIOThread(final IOTask<T> task,int seconds) {
        Observable.just(task)
                .observeOn(Schedulers.io())
                .delay(seconds,TimeUnit.SECONDS)
                .subscribe(tioTask -> tioTask.doOnIOThread(), throwable -> throwable.printStackTrace());
    }

    //主线程执行
    public static <T> void doOnUiThread(final UITask<T> task,int seconds) {
        Observable.just(task)
                .observeOn(AndroidSchedulers.mainThread())
                .delay(seconds,TimeUnit.SECONDS)
                .subscribe(tuiTask -> task.doOnUIThread(), throwable -> throwable.printStackTrace());
    }

    //交互执行
    public static <T> void doTaskDelay(final Task<T> task,int seconds) {
        Observable.create((Observable.OnSubscribe<T>) subscriber -> {
            task.doOnIOThread();
            subscriber.onNext(task.getT());
            subscriber.onCompleted();
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .delay(seconds, TimeUnit.SECONDS)
                .subscribe(t -> task.doOnUIThread(), Throwable::printStackTrace);
    }

    public interface IOTask<T> {
        void doOnIOThread();
    }

    public interface UITask<T> {
        void doOnUIThread();
    }
}
