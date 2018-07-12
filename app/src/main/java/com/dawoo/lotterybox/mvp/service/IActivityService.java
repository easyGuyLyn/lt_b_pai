package com.dawoo.lotterybox.mvp.service;

import com.dawoo.lotterybox.bean.BannerBean;
import com.dawoo.lotterybox.bean.Bulletin;
import com.dawoo.lotterybox.bean.PromoContentBean;
import com.dawoo.lotterybox.bean.PromoListBean;
import com.dawoo.lotterybox.net.HttpResult;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 活动相关
 * 轮播图
 * 首页公告
 * 手机客服链接
 * 获取活动优惠列表
 * 我的优惠
 * Created by benson on 18-2-8.
 */

public interface IActivityService {
    /**
     * 获取轮播图
     *
     * @return
     */
    @POST("mobile/banner.html")
    Observable<HttpResult<List<BannerBean>>> getBanner();


    /**
     * 获取公告
     *
     * @return
     */
    @POST("mobile/bulletin.html")
    Observable<HttpResult<List<Bulletin>>> getBulletin();

    /**
     * 获取优惠活动列表
     *
     * @return
     */
    @FormUrlEncoded
    @POST("mobile/promo/get-promos.html")
    Observable<PromoListBean> getPromoList(
            @Field("paging.pageSize") int pageSize,
            @Field("paging.pageNumber") int pageNumber

    );

    /**
     * 获取优惠详情
     *
     * @return
     */
    @FormUrlEncoded
    @POST("mobile/promo/get-detail.html")
    Observable<HttpResult<PromoContentBean>> getPromoContent(
            @Field("id") int id

    );

    /**
     * 手机客服链接
     *
     * @return
     */
    @GET("mobile/get-cs.html")
    Observable<HttpResult<String>> getCustomerServiceLink();

}
