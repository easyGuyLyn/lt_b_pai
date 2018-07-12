package com.dawoo.lotterybox.mvp.service;

import com.dawoo.lotterybox.bean.message.AnnouncementBean;
import com.dawoo.lotterybox.bean.message.MailDetailBean;
import com.dawoo.lotterybox.bean.message.ReceiveMsgBean;
import com.dawoo.lotterybox.bean.message.SendMsgBean;
import com.dawoo.lotterybox.net.BaseHttpResult;
import com.dawoo.lotterybox.net.HttpResult;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * 消息
 * Created by benson on 18-2-8.
 */

public interface IMessageService {

    /**
     * 消息公告列表
     *
     * @return
     */
    @FormUrlEncoded
    @POST("mobile/bulletin/get-bulletins.html")
    Observable<AnnouncementBean> getBulletins(
            @Field("paging.pageSize") String pageSize,
            @Field("paging.pageNumber") String pageNumber);

    /**
     * 收件箱列表
     *
     * @return
     */
    @FormUrlEncoded
    @POST("mobile/message/receive-msgs.html")
    Observable<ReceiveMsgBean> getReceiveMsg(
            @Field("paging.pageSize") String pageSize,
            @Field("paging.pageNumber") String pageNumber);

    /**
     * 发件箱列表
     *
     * @return
     */
    @FormUrlEncoded
    @POST("mobile/message/send-msgs.html")
    Observable<SendMsgBean> getSendMsg(
            @Field("paging.pageSize") String pageSize,
            @Field("paging.pageNumber") String pageNumber);


    /**
     * 收件删除
     *
     * @return
     */
    @FormUrlEncoded
    @POST("mobile/message/delete-msgs.html")
    Observable<BaseHttpResult> deleteMsg(@Field("search.ids") Object[] ids);

    /**
     * 信件详情
     *
     * @return
     */
    @FormUrlEncoded
    @POST("mobile/message/detail-msgs.html")
    Observable<HttpResult<MailDetailBean>> getMsgDetail(@Field("id") int id, @Field("type") int type);

    /**
     * 回复消息
     *
     * @return
     */
    @FormUrlEncoded
    @POST("mobile/message/add-one-msg.html")
    Observable<BaseHttpResult> replyMsg(@Field("search.username") String username,
                                        @Field("search.content") String content,
                                        @Field("search.title") String title);

    /**
     * 发送消息
     *
     * @return
     */
    @FormUrlEncoded
    @POST("mobile/message/add-msgs.html")
    Observable<BaseHttpResult> sendMsg(@Field("search.sendType") String sendType,
                                       @Field("search.content") String content,
                                       @Field("search.title") String title);

}
