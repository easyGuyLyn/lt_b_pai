package com.dawoo.lotterybox.mvp.presenter;

import android.content.Context;

import com.dawoo.lotterybox.bean.BillAmountBean;
import com.dawoo.lotterybox.bean.record.AssetsBean;
import com.dawoo.lotterybox.bean.record.BillCommonBean;
import com.dawoo.lotterybox.bean.record.BillItemBean;
import com.dawoo.lotterybox.mvp.model.record.IRecordModel;
import com.dawoo.lotterybox.mvp.model.record.RecordModel;
import com.dawoo.lotterybox.mvp.view.IBaseView;
import com.dawoo.lotterybox.mvp.view.IBillHisView;
import com.dawoo.lotterybox.net.rx.ProgressSubscriber;

import java.util.List;

import rx.Subscription;

/**
 * Created by rain on 18-4-19.
 */

public class BillPresenter<T extends IBaseView> extends BasePresenter {
    private final Context mContext;
    private T mView;
    private final IRecordModel mModel;

    public BillPresenter(Context mContext, T view) {
        super(mContext, view);
        this.mContext = mContext;
        this.mView = view;
        mModel = new RecordModel();
    }

    /**
     * 玩家资金历史报表
     */
    public void getBills(int pageNum, String startData, String endData, String way, String type, String item) {
        if (way.equalsIgnoreCase("all")) {
            way = "";
        }
        if (type.equalsIgnoreCase("all")) {
            type = "";
        }
        if (item.equalsIgnoreCase("all")) {
            item = "";
        }
        Subscription subscription = mModel.getBillchangeChanges(new ProgressSubscriber(o ->
                        ((IBillHisView) mView).getBillsData((List<BillItemBean>) o), mContext),
                pageNum,
                startData + " 00:00:00",
                endData + " 23:59:59",
                way, type, item);
        subList.add(subscription);
    }

    /**
     * 玩家资金历史报表
     */
    public void getBillCount(String startData, String endData) {
        Subscription subscription = mModel.getBillchangeAssets(new ProgressSubscriber(o ->
                        ((IBillHisView) mView).getBillCount((BillCommonBean) o), mContext),
                startData + " 00:00:00",
                endData + " 23:59:59");
        subList.add(subscription);
    }

    /**
     * 玩家存取历史报表
     */
    public void getBillsrecharge(String startData, String endData, String type, String item) {
        if (type.equalsIgnoreCase("all")) {
            type = "";
        }
        if (item.equalsIgnoreCase("all")) {
            item = "";
        }
        if (type.equalsIgnoreCase("deposit")) {
            Subscription subscription = mModel.getRechargeRecords(new ProgressSubscriber(o ->
                            ((IBillHisView) mView).getBillsData((List<BillItemBean>) o), mContext),
                    "", item, startData + " 00:00:00",
                    endData + " 23:59:59");
            subList.add(subscription);
        } else {
            Subscription subscription = mModel.getWithDrawsRecords(new ProgressSubscriber(o ->
                            ((IBillHisView) mView).getBillsData((List<BillItemBean>) o), mContext),
                    "", item, startData + " 00:00:00",
                    endData + " 23:59:59");
            subList.add(subscription);
        }

    }

    /**
     * 获取近30天盈亏记录
     */
    public void getEachDayBills() {
       /* Subscription subscription = mModel.getRechargeRecords(new ProgressSubscriber(o ->
                        ((IBillHisView) mView).getBillsData((List<BillItemBean>) o), mContext),
                "", item, startData + " 00:00:00",
                endData + " 23:59:59");
        subList.add(subscription);*/
    }

    /**
     * 获取充值或者提现记录
     *
     * @param startData
     * @param endData
     * @param type
     * @param item
     */
    public void getBillAssert(String startData, String endData, String type, String item) {
        if (type.equalsIgnoreCase("all")) {
            type = "";
        }
        if (item.equalsIgnoreCase("all")) {
            item = "";
        }
        if (type.equalsIgnoreCase("deposit")) {
            Subscription subscription = mModel.getRechargeAmountDisplay(new ProgressSubscriber(o ->
                            ((IBillHisView) mView).getBillAmount((BillAmountBean) o), mContext),
                    "", item, startData + " 00:00:00",
                    endData + " 23:59:59");
            subList.add(subscription);
        } else {
            Subscription subscription = mModel.getWithDrawsAmountDisplay(new ProgressSubscriber(o ->
                            ((IBillHisView) mView).getBillAmount((BillAmountBean) o), mContext),
                    "", item, startData + " 00:00:00",
                    endData + " 23:59:59");
            subList.add(subscription);
        }
    }
}
