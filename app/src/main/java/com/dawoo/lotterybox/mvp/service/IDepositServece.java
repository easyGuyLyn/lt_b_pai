package com.dawoo.lotterybox.mvp.service;

import com.dawoo.lotterybox.bean.Deposit.CallBackUrlBean;
import com.dawoo.lotterybox.bean.Deposit.OnlineResulltBean;
import com.dawoo.lotterybox.bean.Deposit.ParentDepositBean;
import com.dawoo.lotterybox.bean.Deposit.PayDetailBean;
import com.dawoo.lotterybox.bean.Deposit.SaleBean;
import com.dawoo.lotterybox.bean.lottery.LotteryType;
import com.dawoo.lotterybox.net.BaseHttpResult;
import com.dawoo.lotterybox.net.HttpResult;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by rain on 18-4-30.
 */

public interface IDepositServece {
    /**
     * 获取所有公司入款
     *
     * @return
     */
    @GET("mobile/deposit/init-pay-way.html")
    Observable<HttpResult<List<ParentDepositBean>>> getPayWay(
            @Query("type") String type);


    /**
     * 获取账户列表详情
     * @return
     */
    @GET("mobile/deposit/init-pay-account.html")
    Observable<HttpResult<List<PayDetailBean>>> getPayDetail(
            @Query("type") String type, @Query("item") String item);

    /**
     * 获取公司入款优惠列表
     *
     * @return
     */
    @GET("mobile/deposit/init-favorable-activity.html")
    Observable<HttpResult<List<SaleBean>>> getCompanySales(
            @Query("type") String type);

    /**
     * 获取公司入款优惠
     *
     * @return
     */
    @GET("mobile/deposit/init-favorable-money.html")
    Observable<HttpResult<Object>> getCompanySaleMoney(
            @Query("money") double money,
            @Query("activityId") int id);

    /**
     * 提交公司入款
     *
     * @return
     */
    @FormUrlEncoded
    @POST("mobile/deposit/apply-company-deposit.html")
    Observable<BaseHttpResult> postCompanyPay(
            @Field("payAccountId") int payAccountId,
            @Field("rechargeAmount") String rechargeAmount,
            @Field("favorableId") int favorableId,
            @Field("orderNumber") String orderNumber,
            @Field("payerName") String payerName);


    /**
     * 获取线上支付手续费
     *
     * @param * @return
     */
    @GET("mobile/deposit/init-fee-money.html")
    Observable<HttpResult<Object>> getFee(
            @Query("payAccountId") int payAccountId,
            @Query("money") double money);

    /**
     * 线上支付提交接口
     *
     * @param payAccountId
     * @param rechargeAmount
     * @return
     */
    @FormUrlEncoded
    @POST("mobile/deposit/apply-online-deposit.html")
    Observable<HttpResult<OnlineResulltBean>> postOnlinePay(
            @Field("payAccountId") int payAccountId,
            @Field("rechargeAmount") double rechargeAmount);

    /**
     * 线上支付回调接口 获取跳转的url
     *
     * @param billNo 存款后的订单号
     * @return
     */
    @GET("mobile/deposit/online-pay.html")
    Observable<HttpResult<CallBackUrlBean>> callBackOrder(
            @Query("billNo") String billNo);


}
