package com.dawoo.lotterybox.mvp.model.setting;

import com.dawoo.lotterybox.bean.SettingBean;
import com.dawoo.lotterybox.mvp.model.BaseModel;
import com.dawoo.lotterybox.mvp.service.ISettingService;
import com.dawoo.lotterybox.mvp.service.IUserService;
import com.dawoo.lotterybox.net.RetrofitHelper;

import java.util.Date;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;

/**
 * Created by alex on 18-4-3.
 *
 * @author alex
 */

public class SettingModel extends BaseModel implements ISettingModel {
    @Override
    public Subscription getSystemSetting(Subscriber subscriber) {
        Observable<SettingBean> observable = RetrofitHelper.getService(ISettingService.class).getSystemSetting().map(new HttpResultFunc<>());
        return toSubscribe(observable, subscriber);

    }

    @Override
    public Subscription updateSystemSetting(Subscriber subscriber, Map<String, String> params) {
        Observable<Object> observable = RetrofitHelper.getService(ISettingService.class).updateSystemSetting(params).map(new HttpResultFunc<>());
        return toSubscribe(observable, subscriber);

    }

    @Override
    public Subscription getFeedback(Subscriber subscriber, String type, String module, String faultTime, String content, String contact, String Platform) {
        Observable<Object> observable = RetrofitHelper.getService(ISettingService.class).getFeedback(type, module, faultTime, content, contact, Platform).map(new HttpResultFunc<>());
        return toSubscribe(observable, subscriber);
    }

}
