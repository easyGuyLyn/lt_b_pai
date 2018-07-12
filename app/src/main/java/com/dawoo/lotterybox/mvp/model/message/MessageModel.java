package com.dawoo.lotterybox.mvp.model.message;

import com.dawoo.lotterybox.bean.message.AnnouncementBean;
import com.dawoo.lotterybox.bean.message.MailDetailBean;
import com.dawoo.lotterybox.bean.message.ReceiveMsgBean;
import com.dawoo.lotterybox.bean.message.SendMsgBean;
import com.dawoo.lotterybox.mvp.model.BaseModel;
import com.dawoo.lotterybox.mvp.service.IMessageService;
import com.dawoo.lotterybox.net.BaseHttpResult;
import com.dawoo.lotterybox.net.RetrofitHelper;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;

/**
 * Created by archar on 18-4-26.
 */

public class MessageModel extends BaseModel implements IMessageModel {
    @Override
    public Subscription getBulletins(Subscriber subscriber, String pageSize, String pageNumber) {
        Observable<AnnouncementBean> observable = RetrofitHelper
                .getService(IMessageService.class)
                .getBulletins(pageSize, pageNumber);
        return toSubscribe(observable, subscriber);
    }

    @Override
    public Subscription getReceiveMsg(Subscriber subscriber, String pageSize, String pageNumber) {
        Observable<ReceiveMsgBean> observable = RetrofitHelper
                .getService(IMessageService.class)
                .getReceiveMsg(pageSize, pageNumber);
        return toSubscribe(observable, subscriber);
    }

    @Override
    public Subscription getSendMsg(Subscriber subscriber, String pageSize, String pageNumber) {
        Observable<SendMsgBean> observable = RetrofitHelper
                .getService(IMessageService.class)
                .getSendMsg(pageSize, pageNumber);
        return toSubscribe(observable, subscriber);
    }

    @Override
    public Subscription deleteMsg(Subscriber subscriber, Object[] ids) {
        Observable<BaseHttpResult> observable = RetrofitHelper
                .getService(IMessageService.class)
                .deleteMsg(ids);
        return toSubscribe(observable, subscriber);
    }

    @Override
    public Subscription getMsgDetail(Subscriber subscriber, int id, int type) {
        Observable<MailDetailBean> observable = RetrofitHelper
                .getService(IMessageService.class)
                .getMsgDetail(id, type)
                .map(new HttpResultFunc<>());
        return toSubscribe(observable, subscriber);
    }

    @Override
    public Subscription replyMsg(Subscriber subscriber, String username, String content, String title) {
        Observable<BaseHttpResult> observable = RetrofitHelper
                .getService(IMessageService.class)
                .replyMsg(username, content, title);
        return toSubscribe(observable, subscriber);
    }

    @Override
    public Subscription sendMsg(Subscriber subscriber, String sendType, String content, String title) {
        Observable<BaseHttpResult> observable = RetrofitHelper
                .getService(IMessageService.class)
                .sendMsg(sendType, content, title);
        return toSubscribe(observable, subscriber);
    }
}
