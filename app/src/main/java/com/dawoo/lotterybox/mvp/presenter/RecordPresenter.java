package com.dawoo.lotterybox.mvp.presenter;

import android.content.Context;

import com.dawoo.lotterybox.mvp.model.record.IRecordModel;
import com.dawoo.lotterybox.mvp.model.record.RecordModel;
import com.dawoo.lotterybox.mvp.service.IRecordService;
import com.dawoo.lotterybox.mvp.view.IBaseView;
import com.dawoo.lotterybox.mvp.view.INoteRecordDetailView;
import com.dawoo.lotterybox.mvp.view.INoteRecordHisView;
import com.dawoo.lotterybox.mvp.view.IRecordView;
import com.dawoo.lotterybox.net.RetrofitHelper;
import com.dawoo.lotterybox.net.rx.ProgressSubscriber;


import rx.Observable;
import rx.Subscription;


/**
 * 记录相关的presenter
 * Created by benson on 18-1-7.
 */

public class RecordPresenter<T extends IBaseView> extends BasePresenter {
    private final Context mContext;
    private T mView;
    private final IRecordModel mModel;

    public RecordPresenter(Context context, T mView) {
        super(context, mView);

        mContext = context;
        this.mView = mView;
        mModel = new RecordModel();
    }


    public void getOrders(String code,
                          String queryStartDate, String queryEndDate,
                          String pageSize, String pageNumber) {
        Subscription subscription = mModel.getOrders(new ProgressSubscriber(o ->
                        ((INoteRecordHisView) mView).onRefreshResult(o), mContext),
                code,
                "",
                "",
                queryStartDate + " 00:00:00",
                queryEndDate + " 23:59:59",
                pageSize,
                pageNumber,
                "");
        subList.add(subscription);
    }

    /**
     * 玩家投注历史报表
     */
    public void getOrders(String code, String expect,
                          String status, String queryStartDate, String queryEndDate,
                          String pageSize, String pageNumber, String playModel) {
        Subscription subscription = mModel.getOrders(new ProgressSubscriber(o ->
                        ((INoteRecordHisView) mView).onRefreshResult(o), mContext),
                code,
                expect,
                status,
                queryStartDate + " 00:00:00",
                queryEndDate + " 23:59:59",
                pageSize,
                pageNumber,
                playModel);
        subList.add(subscription);
    }

    /**
     * 玩家投注历史报表 加载更多
     */
    public void getMoreOrders(String code, String expect,
                              String status, String queryStartDate, String queryEndDate,
                              String pageSize, String pageNumber, String playModel) {
        Subscription subscription = mModel.getOrders(new ProgressSubscriber(o ->
                        ((INoteRecordHisView) mView).onLoadMoreResult(o), mContext),
                code,
                expect,
                status,
                queryStartDate + " 00:00:00",
                queryEndDate + " 23:59:59",
                pageSize,
                pageNumber,
                playModel);
        subList.add(subscription);
    }


    /**
     * 获取所有彩种
     */
    public void getLottery() {
        Subscription subscription = mModel.getLottery(new ProgressSubscriber(o ->
                ((INoteRecordHisView) mView).onLotteryDataResult(o), mContext));
        subList.add(subscription);
    }

    /**
     * 获取下注总金额，派彩总金额（注单合计）
     */
    public void getAssets(String queryStartDate, String queryEndDate, String status, String code) {
        Subscription subscription = mModel.getAssets(new ProgressSubscriber(o ->
                ((INoteRecordHisView) mView).onAssetsResult(o)
                , mContext,false), queryStartDate + " 00:00:00", queryEndDate + " 23:59:59", status, code);
        subList.add(subscription);
    }

    /**
     * 获取30天内的盈亏数据
     */
    public void getRecentProfit(String status, String code) {
        Subscription subscription = mModel.getRecentProfit(new ProgressSubscriber(o ->
                ((INoteRecordHisView) mView).onRecentProfit(o)
                , mContext,false), status, code);
        subList.add(subscription);
    }

    /**
     * 获取注单详细
     */
    public void getOrderDetail(String id , String orign) {
        Subscription subscription = mModel.getOrderDetail(new ProgressSubscriber(o ->
                ((INoteRecordDetailView) mView).onRefreshResult(o)
                , mContext), id , orign);
        subList.add(subscription);
    }

    /**
     * 提款记录
     */
    public void getRechargeRecords(String type, String item, String startData, String endData) {
        Subscription withDrawsRecords = mModel.getRechargeRecords(new ProgressSubscriber(o ->
                ((IRecordView) mView).result(o)
                , mContext), type, item, startData, endData);
        subList.add(withDrawsRecords);
    }

    /**
     * 取现记录
     */
    public void getRechangeWithDraws(String type, String item,
                                     String startData, String endData) {
        Subscription withDrawsRecords = mModel.getWithDrawsRecords(new ProgressSubscriber(o ->
                ((IRecordView) mView).result(o)
                , mContext), type, item, startData, endData);
        subList.add(withDrawsRecords);
    }

    /**
     *
     *
     */
    public void getOrderGroup(String startData, String endData) {
        Subscription orderGroup = mModel.getOrderGroup(new ProgressSubscriber(o ->
                ((IRecordView) mView).result(o)
                , mContext), startData, endData);
        subList.add(orderGroup);
    }

    /**
     *
     */
    public void getBillchangeAssets(String startData, String endData) {
        Subscription orderGroup = mModel.getBillchangeAssets(new ProgressSubscriber(o ->
                ((IRecordView) mView).result(o)
                , mContext), startData, endData);
        subList.add(orderGroup);
    }

    /**
     *
     */
    public void getBillchangeChanges(int pageNum, String startData, String endData, String way, String type, String item) {
        Subscription orderGroup = mModel.getBillchangeChanges(new ProgressSubscriber(o ->
                ((IRecordView) mView).result(o)
                , mContext), pageNum, startData, endData, way, type, item);
        subList.add(orderGroup);
    }

    /**
     *
     */
    public void getWithDrawsAmountDisplay(String type, String item, String startData, String endData) {
        Subscription orderGroup = mModel.getWithDrawsAmountDisplay(new ProgressSubscriber(o ->
                ((IRecordView) mView).result(o)
                , mContext), type, item, startData, endData);
        subList.add(orderGroup);
    }

    /**
     *
     */
    public void getRechargeAmountDisplay(String type, String item, String startData, String endData) {
        Subscription orderGroup = mModel.getRechargeAmountDisplay(new ProgressSubscriber(o ->
                ((IRecordView) mView).result(o)
                , mContext), type, item, startData, endData);
        subList.add(orderGroup);
    }


    @Override
    public void onDestory() {
        super.onDestory();
    }
}
