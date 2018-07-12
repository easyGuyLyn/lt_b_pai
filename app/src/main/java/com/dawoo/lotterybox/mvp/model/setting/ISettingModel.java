package com.dawoo.lotterybox.mvp.model.setting;

import java.util.Date;
import java.util.Map;

import retrofit2.http.Query;
import rx.Subscriber;
import rx.Subscription;

/**
 * Created by alex on 18-4-3.
 */

public interface ISettingModel {

    Subscription getSystemSetting(Subscriber subscriber);

    Subscription updateSystemSetting(Subscriber subscriber, Map<String, String> params);


    Subscription getFeedback(Subscriber subscriber,String type,  String module, String faultTime,
                              String content,  String contact,  String Platform);

}
