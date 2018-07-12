package com.dawoo.lotterybox.mvp.presenter;

import android.content.Context;

import com.dawoo.lotterybox.mvp.model.message.IMessageModel;
import com.dawoo.lotterybox.mvp.model.message.MessageModel;
import com.dawoo.lotterybox.mvp.view.IBaseView;
import com.dawoo.lotterybox.mvp.view.IAnnouncementView;
import com.dawoo.lotterybox.mvp.view.IMessageBaseView;
import com.dawoo.lotterybox.mvp.view.IMessageView;
import com.dawoo.lotterybox.net.rx.ProgressSubscriber;

import rx.Subscription;

import static com.dawoo.lotterybox.view.activity.message.MessageMailActivity.receiveMail_index;
import static com.dawoo.lotterybox.view.activity.message.MessageMailActivity.sendMail_index;


/**
 * 消息相关的presenter
 * Created by benson on 18-1-7.
 */

public class MessagePresenter<T extends IBaseView> extends BasePresenter {
    private final Context mContext;
    private T mView;
    private final IMessageModel mModel;

    public MessagePresenter(Context context, T mView) {
        super(context, mView);

        mContext = context;
        this.mView = mView;
        mModel = new MessageModel();
    }


    /**
     * 消息公告列表
     */
    public void getBulletins(int pageSize, int pageNumber, Boolean isLoadMore) {
        Subscription subscription;
        if (!isLoadMore) {
            subscription = mModel.getBulletins(new ProgressSubscriber(o ->
                            ((IAnnouncementView) mView).onRefreshResult(o), mContext),
                    pageSize + "",
                    pageNumber + "");
        } else {
            subscription = mModel.getBulletins(new ProgressSubscriber(o ->
                            ((IAnnouncementView) mView).onLoadMoreResult(o), mContext),
                    pageSize + "",
                    pageNumber + "");
        }

        subList.add(subscription);
    }


    public void getMsgList(int type, int pageSize, int pageNumber, Boolean isLoadMore) {
        if (type == receiveMail_index) {
            getReceiveMsg(pageSize, pageNumber, isLoadMore);
        } else if (type == sendMail_index) {
            getSendMsg(pageSize, pageNumber, isLoadMore);
        }
    }


    /**
     * 收件箱列表
     */
    public void getReceiveMsg(int pageSize, int pageNumber, Boolean isLoadMore) {
        Subscription subscription;
        if (!isLoadMore) {
            subscription = mModel.getReceiveMsg(new ProgressSubscriber(o ->
                            ((IMessageView) mView).onRefreshResult(o), mContext),
                    pageSize + "",
                    pageNumber + "");
        } else {
            subscription = mModel.getReceiveMsg(new ProgressSubscriber(o ->
                            ((IMessageView) mView).onLoadMoreResult(o), mContext),
                    pageSize + "",
                    pageNumber + "");
        }

        subList.add(subscription);
    }


    /**
     * 发件箱列表
     */
    public void getSendMsg(int pageSize, int pageNumber, Boolean isLoadMore) {
        Subscription subscription;
        if (!isLoadMore) {
            subscription = mModel.getSendMsg(new ProgressSubscriber(o ->
                            ((IMessageView) mView).onRefreshResult(o), mContext),
                    pageSize + "",
                    pageNumber + "");
        } else {
            subscription = mModel.getSendMsg(new ProgressSubscriber(o ->
                            ((IMessageView) mView).onLoadMoreResult(o), mContext),
                    pageSize + "",
                    pageNumber + "");
        }

        subList.add(subscription);
    }

    /**
     * 删除消息
     *
     * @param ids
     */
    public void deleteMsg(Object[] ids) {
        Subscription subscription = mModel.deleteMsg(new ProgressSubscriber(o ->
                ((IMessageView) mView).onDeleteMsg(o), mContext), ids);

        subList.add(subscription);
    }

    /**
     * 信箱详情
     */
    public void getMsgDetail(int id, int type) {
        Subscription subscription = mModel.getMsgDetail(new ProgressSubscriber(o ->
                ((IMessageBaseView) mView).onRefreshResult(o), mContext), id, type);
        subList.add(subscription);
    }

    /**
     * 删除消息
     */
    public void replyMsg(String username, String content, String title) {
        Subscription subscription = mModel.replyMsg(new ProgressSubscriber(o ->
                ((IMessageBaseView) mView).onRefreshResult(o), mContext), username, content, title);

        subList.add(subscription);
    }


    /**
     * 删除消息
     */
    public void sendMsg(String sendType, String content, String title) {
        Subscription subscription = mModel.sendMsg(new ProgressSubscriber(o ->
                ((IMessageBaseView) mView).onRefreshResult(o), mContext), sendType, content, title);

        subList.add(subscription);
    }

    @Override
    public void onDestory() {
        super.onDestory();
    }
}
