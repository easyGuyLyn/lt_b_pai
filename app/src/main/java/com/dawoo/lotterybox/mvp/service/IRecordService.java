package com.dawoo.lotterybox.mvp.service;

import com.dawoo.lotterybox.bean.BillAmountBean;
import com.dawoo.lotterybox.bean.lottery.LotterySimpleBean;
import com.dawoo.lotterybox.bean.record.AssetsBean;
import com.dawoo.lotterybox.bean.record.BillCommonBean;
import com.dawoo.lotterybox.bean.record.BillItemBean;
import com.dawoo.lotterybox.bean.record.MutType;
import com.dawoo.lotterybox.bean.record.NoteRecordHisData;
import com.dawoo.lotterybox.bean.record.NoteRecordHis;
import com.dawoo.lotterybox.bean.record.ProfitBean;
import com.dawoo.lotterybox.net.HttpResult;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 报表和记录
 * Created by benson on 18-2-8.
 */

public interface IRecordService {

    /**
     * 玩家投注历史报表
     *
     * @return
     */
    @FormUrlEncoded
    @POST("mobile/bet/get-orders.html")
    Observable<NoteRecordHisData> getOrders(
            @Field("search.code") String code,
            @Field("search.expect") String expect,
            @Field("search.status") String status,
            @Field("search.queryStartDate") String queryStartDate,
            @Field("search.queryEndDate") String queryEndDate,
            @Field("paging.pageSize") String pageSize,
            @Field("paging.pageNumber") String pageNumber,
            @Field("search.playModel") String playModel);

    /**
     * 获取所有彩种
     *
     * @return
     */
    @GET("mobile/lottery/get-lottery.html")
    Observable<HttpResult<List<LotterySimpleBean>>> getLottery();


    /**
     * 获取下注总金额，派彩总金额（注单合计）
     *
     * @return
     */
    @FormUrlEncoded
    @POST("mobile/bet/get-assets.html")
    Observable<HttpResult<AssetsBean>> getAssets(
            @Field("search.queryStartDate") String queryStartDate,
            @Field("search.queryEndDate") String queryEndDate,
            @Field("search.status") String status,
            @Field("code") String code);


    /**
     * 根据盈亏状态获取近30天盈亏金额
     *
     * @return
     */
    @FormUrlEncoded
    @POST("mobile/bet/get-recent-profit.html")
    Observable<HttpResult<List<ProfitBean>>> getRecentProfit(
            @Field("status") String status,
            @Field("code") String code);


    /**
     * 获取注单详细
     *
     * @return
     */
    @FormUrlEncoded
    @POST("mobile/bet/get-order-detail.html")
    Observable<HttpResult<NoteRecordHis>> getOrderDetail(@Field("search.id") String id,
                                                         @Field("orign") String orign);

    /**
     * 根据彩种分组注单报表（游戏记录）
     */
    @GET("mobile/bet/get-order-group.html")
    Observable<HttpResult<Object>> getOrderGroup(@Query("search.queryStartDate") String startData, @Query("search.queryEndDate") String endData);

    /**
     * 充值记录
     */
    @GET("mobile/deposit/get-records.html")
    Observable<HttpResult<List<BillItemBean>>> getRechargeRecords(@Query("search.type") String type, @Query("search.item") String item,
                                                      @Query("search.startTime") String startData, @Query("search.endTime") String endData);

    /**
     * 获取item　type接口枚举
     */
    @GET("mobile/billchange/get-change-enums.html")
    Observable<HttpResult<MutType>> getRecordsType();

    /**
     * 提现记录
     */
    @GET("mobile/withdrawal/get-records.html")
    Observable<HttpResult<List<BillItemBean>>> getWithDrawsRecords(@Query("search.type") String type, @Query("search.item") String item,
                                                       @Query("search.startTime") String startData, @Query("search.endTime") String endData);

    /**
     * 充值记录充值金额显示
     */
    @GET("mobile/deposit/get-assets.html")
    Observable<HttpResult<BillAmountBean>> getRechargeAmountDisplay(@Query("search.type") String type, @Query("search.item") String item,
                                                                    @Query("search.startTime") String startData, @Query("search.endTime") String endData);

    /**
     * 提现记录提现金额显示
     */
    @GET("mobile/withdrawal/get-assets.html")
    Observable<HttpResult<BillAmountBean>> getWithDrawsAmountDisplay(@Query("search.type") String type, @Query("search.item") String item,
                                                             @Query("search.startTime") String startData, @Query("search.endTime") String endData);

    /**
     * 账单明细资金记录
     */
    @FormUrlEncoded
    @POST("mobile/billchange/get-changes.html")
    Observable<HttpResult<List<BillItemBean>>> getBillchangeChanges(
            @Field("paging.pageNumber") int pageNumber,
            @Field("paging.pageSize") int pageSize,
            @Field("search.queryStartDate") String startData,
            @Field("search.queryEndDate") String endData,
            @Field("search.way") String way,
            @Field("search.type") String type,
            @Field("search.item") String item);

    /**
     * 账单明细金额记录
     */
    @FormUrlEncoded
    @POST("mobile/billchange/get-assets.html")
    Observable<HttpResult<BillCommonBean>> getBillchangeAssets(@Field("search.queryStartDate") String startData, @Field("search.queryEndDate") String endData);
}
