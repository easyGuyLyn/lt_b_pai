package com.dawoo.lotterybox.mvp.presenter;

import android.content.Context;

import com.dawoo.lotterybox.bean.Bank;
import com.dawoo.lotterybox.bean.WithDrawBean;
import com.dawoo.lotterybox.mvp.model.user.IUserModel;
import com.dawoo.lotterybox.mvp.model.user.UserModel;
import com.dawoo.lotterybox.mvp.model.withdraws.IWithDrawsModel;
import com.dawoo.lotterybox.mvp.model.withdraws.WithDrawsModel;
import com.dawoo.lotterybox.mvp.view.IBandCardListIView;
import com.dawoo.lotterybox.mvp.view.IBankListView;
import com.dawoo.lotterybox.mvp.view.IBaseView;
import com.dawoo.lotterybox.mvp.view.IBindBankCardView;

import com.dawoo.lotterybox.net.rx.ProgressSubscriber;

import java.util.List;

import rx.Subscription;

/**
 * Created by jack on 18-3-14.
 */

public class BankCardPreserter<T extends IBaseView> extends BasePresenter {
    private final Context mContext;
    private T mView;
    private final IWithDrawsModel mModel;

    public BankCardPreserter(Context context, T mView) {
        super(context, mView);
        mContext = context;
        this.mView = mView;
        mModel = new WithDrawsModel();
    }

    /**
     * 银行卡列表
     */

    public void bankCardList() {
        Subscription subscription = mModel.bankcardList(new ProgressSubscriber(o -> ((IBandCardListIView) mView).bandCardList(o), mContext));
        subList.add(subscription);
    }

    /**
     * 获取银行卡列表
     */

    public void getBankList() {
        Subscription subscription = mModel.getBanks(new ProgressSubscriber(o -> ((IBankListView) mView).onResultDate((List<Bank>) o), mContext));
        subList.add(subscription);
    }

    /**
     * 绑定银行卡
     */

    public void bingBankCard(String bankCode, String bankName, String cardNumber, String masterName) {
        Subscription subscription = mModel.bindBankCard(new ProgressSubscriber(o -> ((IBindBankCardView) mView).onResult(o), mContext), bankCode, bankName, cardNumber, masterName);
        subList.add(subscription);
    }


    @Override
    public void onDestory() {
        super.onDestory();
    }
}
