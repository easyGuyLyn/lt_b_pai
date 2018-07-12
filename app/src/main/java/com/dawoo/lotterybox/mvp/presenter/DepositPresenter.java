package com.dawoo.lotterybox.mvp.presenter;

import android.content.Context;

import com.dawoo.lotterybox.bean.Deposit.CallBackUrlBean;
import com.dawoo.lotterybox.bean.Deposit.OnlineResulltBean;
import com.dawoo.lotterybox.bean.Deposit.ParentDepositBean;
import com.dawoo.lotterybox.bean.Deposit.PayDetailBean;
import com.dawoo.lotterybox.bean.Deposit.SaleBean;
import com.dawoo.lotterybox.mvp.model.deposit.DepositModel;
import com.dawoo.lotterybox.mvp.model.deposit.IDepositModel;
import com.dawoo.lotterybox.mvp.view.IBaseView;
import com.dawoo.lotterybox.mvp.view.IDepositView;
import com.dawoo.lotterybox.net.BaseHttpResult;
import com.dawoo.lotterybox.net.rx.ProgressSubscriber;

import java.util.List;

import rx.Subscription;

/**
 * Created by rain on 18-4-30.
 */

public class DepositPresenter<T extends IBaseView> extends BasePresenter {
    private final Context mContext;
    private T mView;
    private final IDepositModel mModel;

    public DepositPresenter(Context mContext, T view) {
        super(mContext, view);
        this.mContext = mContext;
        this.mView = view;
        this.mModel = new DepositModel();
    }

    /**
     * 获取支付方式
     *
     * @param isCompany
     */
    public void getDepositWay(boolean isCompany) {
        String type;
        if (isCompany) {
            type = "company_income";
        } else {
            type = "online_pay";
        }
        Subscription subscription = mModel.getPayWay(new ProgressSubscriber(o ->
                ((IDepositView) mView).getDepositWay((List<ParentDepositBean>) o), mContext), type);
        subList.add(subscription);
    }


    /**
     * 获取账户列表
     * @param type        线上type=2
     * @param item    线上bankcode=null
     */
    public void getPayDetail( String type, String item) {
        Subscription subscription = mModel.getPayDetail(new ProgressSubscriber(o ->
                ((IDepositView) mView).getBankDepositDetail((List<PayDetailBean>) o), mContext), type, item);
        subList.add(subscription);
    }



    /**
     * 获取公司入库优惠
     *
     * @param type
     */
    public void getSales(String type) {
        Subscription subscription = mModel.getSales(new ProgressSubscriber(o ->
                ((IDepositView) mView).getSales((List<SaleBean>) o), mContext), type);
        subList.add(subscription);
    }

    /**
     * 获取手续费
     *
     * @param id
     * @param money
     */
    public void getFee(int id, double money) {
        Subscription subscription = mModel.getFee(new ProgressSubscriber(o ->
                ((IDepositView) mView).getFee(o), mContext), id, money);
        subList.add(subscription);
    }

    /**
     * 提交线上申请
     *
     * @param payAccountId
     * @param rechargeAmount
     * @param favorableId
     * @param orderNumber
     * @param payerName
     */
    public void postCompanyPay(int payAccountId, double rechargeAmount, int favorableId, String orderNumber, String payerName) {
        Subscription subscription = mModel.postCompanyPay(new ProgressSubscriber(o ->
                ((IDepositView) mView).postCompany((BaseHttpResult) o), mContext), payAccountId, rechargeAmount, favorableId, orderNumber, payerName);
        subList.add(subscription);
    }

    /**
     * 提交线上支付
     */
    public void postOnline(int id, double money) {
        Subscription subscription = mModel.postOnline(new ProgressSubscriber(o ->
                ((IDepositView) mView).postResult((OnlineResulltBean) o), mContext), id, money);
        subList.add(subscription);
    }

    /**
     * 获取url
     *
     * @param billNO
     */
    public void callBackOrder(String billNO) {
        Subscription subscription = mModel.callBackOrder(new ProgressSubscriber(o ->
                ((IDepositView) mView).callBackOrder((CallBackUrlBean) o), mContext, false), billNO);
        subList.add(subscription);
    }

    /**
     * 获取优惠金额
     *
     * @param inputMoney
     * @param id
     */
    public void getSaleMoney(double inputMoney, int id) {
        if (id == 0) {
            return;
        }
        Subscription subscription = mModel.getSaleMoney(new ProgressSubscriber(o ->
                ((IDepositView) mView).getSaleMoney(o), mContext, false), inputMoney, id);
        subList.add(subscription);
    }
}
