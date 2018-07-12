package com.dawoo.lotterybox.mvp.model.record;


import com.dawoo.lotterybox.ConstantValue;
import com.dawoo.lotterybox.bean.BillAmountBean;
import com.dawoo.lotterybox.bean.lottery.LotterySimpleBean;
import com.dawoo.lotterybox.bean.record.AssetsBean;
import com.dawoo.lotterybox.bean.record.BillCommonBean;
import com.dawoo.lotterybox.bean.record.BillItemBean;
import com.dawoo.lotterybox.bean.record.NoteRecordHisData;
import com.dawoo.lotterybox.bean.record.NoteRecordHis;
import com.dawoo.lotterybox.bean.record.ProfitBean;
import com.dawoo.lotterybox.mvp.model.BaseModel;
import com.dawoo.lotterybox.mvp.service.IRecordService;
import com.dawoo.lotterybox.net.RetrofitHelper;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;


/**
 * Created by benson on 17-12-21.
 */

public class RecordModel extends BaseModel implements IRecordModel {
    @Override
    public Subscription getOrders(Subscriber subscriber, String code, String expect,
                                  String status, String queryStartDate, String queryEndDate,
                                  String pageSize, String pageNumber, String playModel) {
        Observable<NoteRecordHisData> observable = RetrofitHelper
                .getService(IRecordService.class)
                .getOrders(code, expect, status, queryStartDate, queryEndDate, pageSize, pageNumber, playModel);
        return toSubscribe(observable, subscriber);
    }

    @Override
    public Subscription getLottery(Subscriber subscriber) {
        Observable<List<LotterySimpleBean>> observable = RetrofitHelper
                .getService(IRecordService.class)
                .getLottery()
                .map(new HttpResultFunc<>());
        return toSubscribe(observable, subscriber);
    }

    @Override
    public Subscription getAssets(Subscriber subscriber, String queryStartDate, String queryEndDate, String status, String code) {
        Observable<AssetsBean> observable = RetrofitHelper
                .getService(IRecordService.class)
                .getAssets(queryStartDate, queryEndDate, status, code)
                .map(new HttpResultFunc<>());
        return toSubscribe(observable, subscriber);
    }

    @Override
    public Subscription getRecentProfit(Subscriber subscriber, String status, String code) {
        Observable<List<ProfitBean>> observable = RetrofitHelper
                .getService(IRecordService.class)
                .getRecentProfit(status, code)
                .map(new HttpResultFunc<>());
        return toSubscribe(observable, subscriber);
    }

    @Override
    public Subscription getOrderDetail(Subscriber subscriber, String id ,String orign) {
        Observable<NoteRecordHis> observable = RetrofitHelper
                .getService(IRecordService.class)
                .getOrderDetail(id,orign)
                .map(new HttpResultFunc<>());
        return toSubscribe(observable, subscriber);
    }

    @Override
    public Subscription getRechargeAmountDisplay(Subscriber subscriber, String type, String item, String startData, String endData) {
        Observable<Object> observable = RetrofitHelper
                .getService(IRecordService.class)
                .getRechargeAmountDisplay(type, item, startData, endData)
                .map(new HttpResultFunc<BillAmountBean>());
        return toSubscribe(observable, subscriber);
    }

    @Override
    public Subscription getWithDrawsAmountDisplay(Subscriber subscriber, String type, String item, String startData, String endData) {
        Observable<Object> observable = RetrofitHelper
                .getService(IRecordService.class)
                .getWithDrawsAmountDisplay(type, item, startData, endData)
                .map(new HttpResultFunc<BillAmountBean>());
        return toSubscribe(observable, subscriber);
    }

    @Override
    public Subscription getBillchangeChanges(Subscriber subscriber,int pageNum, String startData, String endData, String way, String type, String item) {
        Observable<Object> observable = RetrofitHelper
                .getService(IRecordService.class)
                .getBillchangeChanges(pageNum, ConstantValue.RECORD_LIST_PAGE_SIZE,startData, endData, way, type, item)
                .map(new HttpResultFunc<List<BillItemBean>>());
        return toSubscribe(observable, subscriber);
    }

    @Override
    public Subscription getBillchangeAssets(Subscriber subscriber, String startData, String endData) {
        Observable<Object> observable = RetrofitHelper
                .getService(IRecordService.class)
                .getBillchangeAssets(startData, endData)
                .map(new HttpResultFunc<BillCommonBean>());
        return toSubscribe(observable, subscriber);
    }

    @Override
    public Subscription getOrderGroup(Subscriber subscriber, String startData, String endData) {
        Observable<Object> observable = RetrofitHelper
                .getService(IRecordService.class)
                .getOrderGroup(startData, endData)
                .map(new HttpResultFunc<>());
        return toSubscribe(observable, subscriber);
    }

    @Override
    public Subscription getRechargeRecords(Subscriber subscriber, String type, String item, String startData, String endData) {
        Observable<Object> observable = RetrofitHelper
                .getService(IRecordService.class)
                .getRechargeRecords(type, item, startData, endData)
                .map(new HttpResultFunc<List<BillItemBean>>());
        return toSubscribe(observable, subscriber);
    }

    @Override
    public Subscription getRecordsType(Subscriber subscriber) {
        Observable<Object> observable = RetrofitHelper
                .getService(IRecordService.class)
                .getRecordsType()
                .map(new HttpResultFunc<>());
        return toSubscribe(observable, subscriber);
    }

    @Override
    public Subscription getWithDrawsRecords(Subscriber subscriber, String type, String item, String startData, String endData) {
        Observable<Object> observable = RetrofitHelper
                .getService(IRecordService.class)
                .getWithDrawsRecords(type, item, startData, endData)
                .map(new HttpResultFunc<List<BillItemBean>>());
        return toSubscribe(observable, subscriber);
    }


}
