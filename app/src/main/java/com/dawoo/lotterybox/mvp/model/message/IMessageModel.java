package com.dawoo.lotterybox.mvp.model.message;


import rx.Subscriber;
import rx.Subscription;

/**
 * Created by archar on 17-12-21.
 */

public interface IMessageModel {
    /**
     * 消息公告列表
     */

    Subscription getBulletins(Subscriber subscriber, String pageSize, String pageNumber);

    /**
     * 收件箱列表
     */

    Subscription getReceiveMsg(Subscriber subscriber, String pageSize, String pageNumber);

    /**
     * 发件箱列表
     */

    Subscription getSendMsg(Subscriber subscriber, String pageSize, String pageNumber);

    /**
     * 删除消息
     */

    Subscription deleteMsg(Subscriber subscriber, Object[] ids);

    /**
     * 消息详情
     */

    Subscription getMsgDetail(Subscriber subscriber, int id, int type);

    /**
     * 回复消息
     */

    Subscription replyMsg(Subscriber subscriber, String username, String content, String title);

    /**
     * 发送消息
     */

    Subscription sendMsg(Subscriber subscriber, String sendType, String content, String title);

}
