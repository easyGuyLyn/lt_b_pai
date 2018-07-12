package com.dawoo.lotterybox.mvp.service;

import com.dawoo.lotterybox.bean.BetOrdersListBean;
import com.dawoo.lotterybox.bean.TypeAndLotteryBean;
import com.dawoo.lotterybox.bean.lottery.BaseHandicap;
import com.dawoo.lotterybox.bean.lottery.BaseLottery;
import com.dawoo.lotterybox.bean.lottery.Handicap;
import com.dawoo.lotterybox.bean.lottery.HandicapWithOpening;
import com.dawoo.lotterybox.bean.lottery.LotteryLastOpenAndOpening;
import com.dawoo.lotterybox.bean.lottery.LotteryOddBean;
import com.dawoo.lotterybox.bean.lottery.LotteryType;
import com.dawoo.lotterybox.bean.lottery.OrderInfo;
import com.dawoo.lotterybox.bean.lottery.SaveOrderResult;
import com.dawoo.lotterybox.net.HttpResult;

import java.util.List;
import java.util.Map;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 彩票相关接口
 * Created by benson on 18-2-8.
 */

public interface ILotteryService {


    /**
     * 获取所有彩种类型
     *
     * @return
     */
    @GET("mobile/lottery/get-type.html")
    Observable<HttpResult<List<LotteryType>>> getLotteryType();

    /**
     * 获取所有彩种
     *
     * @return
     */
    @GET("mobile/lottery/get-lottery.html")
    Observable<HttpResult<List<BaseLottery>>> getLottery();

    /**
     * 获取彩种类型及其子彩种的代号和名称
     *
     * @return
     */
    @GET("mobile/lottery/get-type-and-lottery.html")
    Observable<HttpResult<List<TypeAndLotteryBean>>> getTypeAndLottery();

    /**
     * 获取盘口数据
     *
     * @return
     */
    @GET("mobile/lottery/get-expect.html")
    Observable<HttpResult<BaseHandicap>> getLotteryExpect(@Query("code") String code);

    /**
     * 获取近期开奖结果
     *
     * @return
     */
    @GET("mobile/lottery/get-result-by-code.html")
    Observable<HttpResult<List<Handicap>>> getResultByCode(
            @Query("search.code") String code,
            @Query("paging.pageSize") String pageSize,
            @Query("paging.pageNumber") String pageNumber);

    /**
     * 获取近期数据（包含未开奖期）
     *
     * @return
     */
    @GET("mobile/lottery/get-recent-records.html")
    Observable<HttpResult<List<HandicapWithOpening>>> getRecentRecords(
            @Query("code") String code,
            @Query("pageSize") String pageSize);

    /**
     * 获取每个彩种最后一期开奖结果和未开奖一期的数据
     *
     * @return
     */
    @GET("mobile/lottery/get-last-opened-and-opening-result.html")
    Observable<HttpResult<List<LotteryLastOpenAndOpening>>> getLastOpenedAndOpeningResult();

    /**
     * 获取每个彩种最后一期开奖结果和未开奖一期的数据
     *
     * @return
     */
    @GET("mobile/lottery/get-last-opened-and-opening-result.html")
    Observable<HttpResult<List<LotteryLastOpenAndOpening>>> getLastOpenedAndOpeningResult(@Query("code") String code);

    /**
     * 获取彩种是否拥有传统玩法，官方玩法
     * 类型(1.全部2.官方玩法3.传统玩法)
     *
     * @return
     */
    @GET("mobile/lottery/get-lottery-genre.html")
    Observable<HttpResult<Integer>> getLotterygenre(
            @Query("code") String code);

    /**
     * 获取防重复下注token
     *
     * @return
     */
    @GET("mobile/get-lt-token.html")
    Observable<HttpResult<String>> getLtToken();


    /**
     * 获取赔率
     *
     * @return
     */
    @GET("mobile/lottery/get-lottery-odd.html")
    Observable<HttpResult<Map<String, LotteryOddBean>>> getLotteryOdd(
            @Query("code") String code,
            @Query("betCode") String betCode);


    /**
     * 官方下单
     */
    @FormUrlEncoded
    @POST("mobile/lottery/save-bet-order.html")
    Observable<SaveOrderResult> saveBetOrder(
            @Field("lb.token") String token,
            @Field("betForm") String betForm
    );

    /**
     * 获取已封盘最近一期开奖数据
     *
     * @return
     */
    @GET("mobile/lottery/get-recent-close-expect.html")
    Observable<HttpResult<Handicap>> getRecentCloseExpect(
            @Query("code") String code);

    /**
     * 根据订单编号，查询订单信息
     * */
    @FormUrlEncoded
    @POST("mobile/bet/get-order-billNo.html")
    Observable<HttpResult<OrderInfo>> getOrderInfo(
            @Field("billNo") String billNo,
            @Field("code") String code);
}
