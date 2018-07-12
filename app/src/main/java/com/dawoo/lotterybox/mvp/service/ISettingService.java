package com.dawoo.lotterybox.mvp.service;

import com.dawoo.lotterybox.ConstantValue;
import com.dawoo.lotterybox.bean.SettingBean;
import com.dawoo.lotterybox.net.HttpResult;

import java.util.Date;
import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 设置相关
 * 手势密码找回
 * 声音开关
 * 开奖推送
 * 中奖推送
 * Created by benson on 18-2-8.
 */

public interface ISettingService {
    /**
     * 获取系统设置
     *
     * @return
     */
    @GET("mobile/setting/get-user-param.html")
    Observable<HttpResult<SettingBean>> getSystemSetting();

    /**
     * 更新系统列表
     */
    @FormUrlEncoded
    @POST("mobile/setting/update-user-param.html")
    Observable<HttpResult<Object>> updateSystemSetting(@FieldMap Map<String, String> params);

    /**
     * 获取银行卡列表
     */
    @GET("mobile/bankcard/my-bankcard")
    Observable<HttpResult<Object>> getBankCardList();

    /**
     * 意见反馈
     */
    @GET("mobile/feedback/save-feedback.html")
    Observable<HttpResult<Object>> getFeedback(@Query("type") String type,@Query("module") String module,@Query("faultTime") String faultTime,
                                               @Query("content") String content,@Query("contact") String contact,@Query("platform") String Platform);
}
