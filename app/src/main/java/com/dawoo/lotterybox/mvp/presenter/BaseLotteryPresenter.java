package com.dawoo.lotterybox.mvp.presenter;

import android.content.Context;
import android.util.Log;

import com.dawoo.lotterybox.ConstantValue;
import com.dawoo.lotterybox.bean.BetParamBean;
import com.dawoo.lotterybox.bean.lottery.BaseHandicap;
import com.dawoo.lotterybox.bean.lottery.Handicap;
import com.dawoo.lotterybox.bean.lottery.HandicapWithOpening;
import com.dawoo.lotterybox.bean.lottery.LotteryOddBean;
import com.dawoo.lotterybox.bean.lottery.SaveOrderResult;
import com.dawoo.lotterybox.mvp.model.Lottery.ILotteryModel;
import com.dawoo.lotterybox.mvp.model.Lottery.LotteryModel;
import com.dawoo.lotterybox.mvp.view.IBaseLotteryView;
import com.dawoo.lotterybox.mvp.view.IBaseView;
import com.dawoo.lotterybox.net.rx.ProgressSubscriber;
import com.dawoo.lotterybox.net.rx.SubscriberOnNextListener;
import com.dawoo.lotterybox.view.view.dialog.SureNoteDialog;

import java.util.List;
import java.util.Map;

import rx.Subscription;

/**
 * Created by benson on 18-3-13.
 */

public class BaseLotteryPresenter<T extends IBaseView> extends BasePresenter {
    protected static final int PAGE_NUMBER = ConstantValue.LOTTERY_LIST_PAGE_NUMBER;
    protected static final int PAGE_SIZE = ConstantValue.LOTTERY_LIST_PAGE_SIZE;

    protected Context mContext;
    protected T mView;
    protected final ILotteryModel mModel;
    protected String mCode;
    private SureNoteDialog mSureNoteDialog;

    public BaseLotteryPresenter(Context context, T mView) {
        super(context, mView);

        mContext = context;
        this.mView = mView;
        mModel = new LotteryModel();
    }

    /**
     * 获取近120期开奖数据
     */

    public void getResultByCode() {
        Subscription subscription = mModel.getResultByCode(new ProgressSubscriber(o ->
                        ((IBaseLotteryView) mView).onResultByCode((List<Handicap>) o), mContext,false),
                mCode,
                String.valueOf(PAGE_SIZE),
                String.valueOf(PAGE_NUMBER));
        subList.add(subscription);
    }

    /**
     * 获取近1期开奖数据
     */

    public void getOneResultByCode() {
        Subscription subscription = mModel.getResultByCode(new ProgressSubscriber(o ->
                        ((IBaseLotteryView) mView).onOneResultByCode((List<Handicap>) o), mContext),
                mCode,
                String.valueOf(1),
                String.valueOf(1));
        subList.add(subscription);
    }

    /**
     * 获取已封盘最近一期开奖数据（开奖号码（空则说明正在开奖中））；
     */

    public void getRecentCloseExpect() {
        Subscription subscription = mModel.getRecentCloseExpect(new ProgressSubscriber(o ->
                        ((IBaseLotteryView) mView).onRecentCloseExpect((Handicap) o), mContext, false),
                mCode);
        subList.add(subscription);
    }

    /**
     * 获取近120期开奖数据（包含未开奖数据）
     */
    public void getRecentRecords() {
        Subscription subscription = mModel.getRecentRecords(new ProgressSubscriber(o ->
                        ((IBaseLotteryView) mView).onRecentRecords((List<HandicapWithOpening>) o), mContext,false),
                mCode,
                String.valueOf(PAGE_SIZE));
        subList.add(subscription);
    }

    /**
     * 获取盘口数据
     */
    public void getLotteryExpect() {
        Log.e("Finish getLotteryExpect","  getLotteryExpect");
        Subscription subscription = mModel.getLotteryExpect(new ProgressSubscriber<>(o ->
                        ((IBaseLotteryView) mView).onLotteryExpect((BaseHandicap) o), mContext),
                mCode);
        subList.add(subscription);
    }

    /**
     * 获取盘口数据
     */
    public void getLotteryExpect(boolean isNeedProgress) {
        Subscription subscription = mModel.getLotteryExpect(new ProgressSubscriber<>(o ->
                        ((IBaseLotteryView) mView).onLotteryExpect((BaseHandicap) o), mContext, isNeedProgress),
                mCode);
        subList.add(subscription);
    }

    /**
     * 获取赔率
     */
    public void getLotteryOdd(String betCode) {
        Subscription subscription = mModel.getLotteryOdd(new ProgressSubscriber<>(o ->
                        ((IBaseLotteryView) mView).onLotteryOdd((Map<String, LotteryOddBean>) o), mContext),
                mCode,
                betCode);
        subList.add(subscription);
    }

    /**
     * 获取下单防重token
     */
    public void getLtToken() {
        Subscription subscription = mModel.getLtToken(new ProgressSubscriber<>(o ->
                ((IBaseLotteryView) mView).onLtToken((String) o), mContext,false)
        );
        subList.add(subscription);
    }

    /**
     * 下单
     */
    public void saveBetOrder(String token, String betForm) {
        Subscription subscription = mModel.saveBetOrder(new ProgressSubscriber<>(o ->
                        ((IBaseLotteryView) mView).onSaveBetOrder((SaveOrderResult) o), mContext),
                token, betForm);
        subList.add(subscription);
    }

    /**
     * 下单提示dialog
     */

    public void sureNoteDialogShow(String playTypeName, String expect, String betCount, String totalMoney) {
        if (mSureNoteDialog == null)
            mSureNoteDialog = new SureNoteDialog(mContext, (IBaseLotteryView) mView, playTypeName, expect, betCount, totalMoney);
        else mSureNoteDialog.setData(playTypeName, expect, betCount, totalMoney);
    }

    @Override
    public void onDestory() {
        super.onDestory();
        if (mSureNoteDialog != null)
            mSureNoteDialog.dismiss();

    }
}
