package com.dawoo.lotterybox.mvp.model.record;


import retrofit2.http.Query;
import rx.Subscriber;
import rx.Subscription;

/**
 * Created by benson on 17-12-21.
 */

public interface IRecordModel {
    /**
     * 获取投注记录
     */

    Subscription getOrders(Subscriber subscriber, String code, String expect,
                           String status, String queryStartDate, String queryEndDate,
                           String pageSize, String pageNumber, String playModel);

    /**
     * 获取所有彩种
     */

    Subscription getLottery(Subscriber subscriber);

    /**
     * 获取下注总金额，派彩总金额（注单合计）
     */

    Subscription getAssets(Subscriber subscriber, String queryStartDate, String queryEndDate, String status, String code);

    /**
     * 获取30天的盈亏数据
     */
    Subscription getRecentProfit(Subscriber subscriber, String status, String code);

    /**
     * 获取注单详细
     */
    Subscription getOrderDetail(Subscriber subscriber, String id , String orign);

    Subscription getRechargeAmountDisplay(Subscriber subscriber, String type, String item,
                                          String startData, String endData);

    Subscription getWithDrawsAmountDisplay(Subscriber subscriber, String type, String item,
                                           String startData, String endData);

    Subscription getBillchangeChanges(Subscriber subscriber, int pageNum,String startData, String endData,
                                      String way, String type, String item);

    Subscription getBillchangeAssets(Subscriber subscriber, String startData, String endData);


    Subscription getOrderGroup(Subscriber subscriber, String startData, String endData);


    Subscription getRechargeRecords(Subscriber subscriber, String type, String item,
                                    String startData, String endData);

    Subscription getRecordsType(Subscriber subscriber);

    Subscription getWithDrawsRecords(Subscriber subscriber, String type, String item,
                                     String startData, String endData);

}
