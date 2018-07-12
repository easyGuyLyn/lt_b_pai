package com.dawoo.lotterybox.mvp.presenter;

import android.content.Context;

import com.dawoo.lotterybox.bean.Bank;
import com.dawoo.lotterybox.bean.WithDrawBean;
import com.dawoo.lotterybox.bean.WithDrawsAduitBean;
import com.dawoo.lotterybox.bean.WithDrawsDetailBean;
import com.dawoo.lotterybox.bean.WithDrawsFeeBean;
import com.dawoo.lotterybox.mvp.model.withdraws.IWithDrawsModel;
import com.dawoo.lotterybox.mvp.model.withdraws.WithDrawsModel;
import com.dawoo.lotterybox.mvp.view.IBandCardListIView;
import com.dawoo.lotterybox.mvp.view.IBankListView;
import com.dawoo.lotterybox.mvp.view.IBaseView;
import com.dawoo.lotterybox.mvp.view.IBindBankCardView;
import com.dawoo.lotterybox.mvp.view.IMCenterFragmentView;
import com.dawoo.lotterybox.mvp.view.IWithDAView;
import com.dawoo.lotterybox.mvp.view.IWithDrawView;
import com.dawoo.lotterybox.mvp.view.IWithDrawsDetailView;
import com.dawoo.lotterybox.net.rx.DefaultCallback;
import com.dawoo.lotterybox.net.rx.DefaultSubscriber;
import com.dawoo.lotterybox.net.rx.ProgressSubscriber;

import java.util.List;

import rx.Subscription;

/**
 * Created by alex on 18-4-5.
 * @author alex
 */

public class WithDrawsPresenter<T extends IBaseView> extends BasePresenter {
    private final Context mContext;
    private T mView;
    private final IWithDrawsModel mModel;

    public WithDrawsPresenter(Context context, T mView) {
        super(context, mView);
        mContext = context;
        this.mView = mView;
        mModel = new WithDrawsModel();
    }


    /**
     * 计算可以提出的额度
     */
    public void getWithDrawsFee(String money,DefaultCallback defaultCallback) {
        Subscription subscription = mModel.getWithDrawsFee(new DefaultSubscriber<>(o -> ((IWithDrawView) mView).onFeeResult((WithDrawsFeeBean)o),defaultCallback), money);
        subList.add(subscription);
    }

    /**
     * 区块界面初始化
     */
    public void withDrawInit() {
        Subscription subscription = mModel.withDrawsInit(new ProgressSubscriber(o -> ((IWithDrawView) mView).onInitResult((WithDrawBean) o), mContext));
        subList.add(subscription);
    }

    /**
     * 提交取现
     */
    public void applyWithDraws(String money) {
        Subscription subscription = mModel.applyWithDraws(new ProgressSubscriber(o -> ((IWithDrawView) mView).onApplyResult(o), mContext),money);
        subList.add(subscription);
    }
    /**
     * 稽核列表
     */
    public void withDrawsAuidtList(DefaultCallback defaultCallback) {
        Subscription subscription = mModel.withDrawsAudit(new DefaultSubscriber<>(o -> ((IWithDAView) mView).IWithDrawsAduitResult((List<WithDrawsAduitBean>) o), defaultCallback));
        subList.add(subscription);
    }
    /**
     * 稽核详情
     */
    public void withDrawsAuidDeatail(String id) {
        Subscription subscription = mModel.withDrawsAuditDetail(new ProgressSubscriber(o -> ((IWithDrawsDetailView) mView).resultDetail((WithDrawsDetailBean) o), mContext),id);
        subList.add(subscription);
    }


//    /**
//     * 银行卡列表
//     */
//
//    public void bankCardList() {
//        Subscription subscription = mModel.bankcardList(new ProgressSubscriber(o -> ((IBandCardListIView) mView).bandCardList(o), mContext));
//        subList.add(subscription);
//    }
//
//    /**
//     * 获取银行卡列表
//     */
//
//    public void getBankList() {
//        Subscription subscription = mModel.getBanks(new ProgressSubscriber(o -> ((IBankListView) mView).onResultDate((List<Bank>) o), mContext));
//        subList.add(subscription);
//    }
//
//    /**
//     * 绑定银行卡
//     */
//
//    public void bingBankCard(String bankCode, String bankName, String cardNumber, String masterName) {
//        Subscription subscription = mModel.bindBankCard(new ProgressSubscriber(o -> ((IBindBankCardView) mView).onResult(o), mContext), bankCode, bankName, cardNumber, masterName);
//        subList.add(subscription);
//    }


}
