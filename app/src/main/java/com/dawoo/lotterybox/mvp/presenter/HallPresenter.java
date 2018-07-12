package com.dawoo.lotterybox.mvp.presenter;

import android.content.Context;

import com.dawoo.lotterybox.bean.BannerBean;
import com.dawoo.lotterybox.bean.Bulletin;
import com.dawoo.lotterybox.bean.TypeAndLotteryBean;
import com.dawoo.lotterybox.mvp.model.Lottery.ILotteryModel;
import com.dawoo.lotterybox.mvp.model.Lottery.LotteryModel;
import com.dawoo.lotterybox.mvp.model.activity.ActivityModel;
import com.dawoo.lotterybox.mvp.model.activity.IActivityModel;
import com.dawoo.lotterybox.mvp.view.IBaseView;
import com.dawoo.lotterybox.mvp.view.IHallView;
import com.dawoo.lotterybox.mvp.view.IMCenterFragmentView;
import com.dawoo.lotterybox.net.rx.ProgressSubscriber;

import java.util.List;

import rx.Subscription;

/**
 * 购彩大厅相关
 * Created by b on 18-2-16.
 */

public class HallPresenter<T extends IBaseView> extends BasePresenter {


    private final Context mContext;
    private T mView;
    private final IActivityModel mActivityModel;
    private final ILotteryModel mLotteryModel;

    public HallPresenter(Context context, T mView) {
        super(context, mView);

        mContext = context;
        this.mView = mView;
        mActivityModel = new ActivityModel();
        mLotteryModel = new LotteryModel();
    }

    /**
     * 轮播图
     */
    public void getBanner() {
        Subscription subscription = mActivityModel.getBanner(new ProgressSubscriber(o ->
                ((IHallView) mView).onBanner((List<BannerBean>) o), mContext));
        subList.add(subscription);
    }

    /**
     * 公告
     */
    public void getBulletin() {
        Subscription subscription = mActivityModel.getBulletin(new ProgressSubscriber(o ->
                ((IHallView) mView).onBulletin((List<Bulletin>) o), mContext));
        subList.add(subscription);
    }

    /**
     * 获取彩种类型及其子彩种的代号和名称
     */
    public void getTypeAndLottery() {
        Subscription subscription = mLotteryModel.getTypeAndLottery(new ProgressSubscriber(o ->
                ((IHallView) mView).onTypeAndLottery((List<TypeAndLotteryBean>) o), mContext));
        subList.add(subscription);
    }

    /**
     * 获取客服链接
     */
    public void getCSLink() {
        Subscription subscription = mActivityModel.getCSLink(new ProgressSubscriber(o ->
                ((IMCenterFragmentView) mView).onCSLinkResult((String) o), mContext, false));
        subList.add(subscription);
    }

    @Override
    public void ondetach() {
        super.ondetach();
    }
}
