package com.dawoo.lotterybox.mvp.service;

import com.dawoo.lotterybox.ConstantValue;
import com.dawoo.lotterybox.bean.Bank;
import com.dawoo.lotterybox.bean.InfoMation;
import com.dawoo.lotterybox.bean.MyBandCard;
import com.dawoo.lotterybox.bean.WithDrawBean;
import com.dawoo.lotterybox.bean.WithDrawsAduitBean;
import com.dawoo.lotterybox.bean.WithDrawsDetailBean;
import com.dawoo.lotterybox.bean.WithDrawsFeeBean;
import com.dawoo.lotterybox.net.HttpResult;

import java.util.List;
import java.util.Map;

import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by alex on 18-4-5.
 *
 * @author alex
 */

public interface IWithDrawsService {

    /**
     * 我名下的银行卡
     */
    @GET("mobile/bankcard/my-bankcard.html")
    Observable<HttpResult<List<MyBandCard>>> bankcardList();

    /**
     * 银行卡列表
     */
    @GET("mobile/bankcard/get-banks.html")
    Observable<HttpResult<List<Bank>>> getBanks();

    /**
     * 绑定银行卡
     */
    @FormUrlEncoded
    @POST("mobile/bankcard/bind-bankcard.html")
    Observable<HttpResult<Object>> bindBankCard(
            @Field("bankCode") String bankCode,
            @Field("bankName") String bankName,
            @Field("cardNumber") String cardNumber,
            @Field("masterName") String masterName
    );

    /**
     * 进入提现界面初始化
     */
    @GET("mobile/withdraw/init-withdraw.html")
    Observable<HttpResult<WithDrawBean>> withDrawsInit();

    /**
     * 提现计算
     */
    @FormUrlEncoded
    @POST("mobile/withdraw/withdraw-fee.html")
    Observable<HttpResult<WithDrawsFeeBean>> getWithDrawsFee(@Field("withdrawAmount") String money);

    /**
     * 稽核列表
     */
    @GET("mobile/withdraw/withdraw-audit.html")
    Observable<HttpResult<List<WithDrawsAduitBean>>> getWithDrawsAudit();
    /**
     * 稽核详情
     */
    @GET("mobile/withdraw/withdraw-audit-detail.html")
    Observable<HttpResult<WithDrawsDetailBean>> getWithDrawsAuditDetail(@Query("id")String id);
    /**
     * 提交取款
     */
    @FormUrlEncoded
    @POST("mobile/withdraw/apply-withdraw.html")
    Observable<HttpResult<Object>> applyWithDraws(@Field("withdrawAmount") String withdrawAmount);


    /**
     * 解绑银行卡
     */

    @GET("mobile/bankcard/un-bind-bankcard.html")
    Observable<HttpResult<Object>> unBindBankCard(
            @Query("bankCode") String bankCode,
            @Query("bankName") String bankName,
            @Query("cardNumber") String cardNumber,
            @Query("masterName") String masterName

    );
}
