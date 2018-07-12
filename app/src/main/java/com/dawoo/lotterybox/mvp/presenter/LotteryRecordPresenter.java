package com.dawoo.lotterybox.mvp.presenter;

import android.content.Context;

import com.dawoo.lotterybox.bean.lottery.BaseHandicap;
import com.dawoo.lotterybox.bean.lottery.Handicap;
import com.dawoo.lotterybox.bean.lottery.HandicapWithOpening;
import com.dawoo.lotterybox.bean.lottery.LotteryLastOpenAndOpening;
import com.dawoo.lotterybox.bean.lottery.lotteryenum.LotteryEnum;
import com.dawoo.lotterybox.mvp.model.Lottery.ILotteryModel;
import com.dawoo.lotterybox.mvp.model.Lottery.LotteryModel;
import com.dawoo.lotterybox.mvp.view.IBaseLotteryView;
import com.dawoo.lotterybox.mvp.view.IBaseView;
import com.dawoo.lotterybox.mvp.view.ILastLotteryRecView;
import com.dawoo.lotterybox.mvp.view.IRecentOpenRecView;
import com.dawoo.lotterybox.net.rx.ProgressSubscriber;
import com.dawoo.lotterybox.view.fragment.lottery_rcd.OpenLotteryBJKL8View;
import com.dawoo.lotterybox.view.fragment.lottery_rcd.OpenLotteryFC3DView;
import com.dawoo.lotterybox.view.fragment.lottery_rcd.OpenLotteryK3View;
import com.dawoo.lotterybox.view.fragment.lottery_rcd.OpenLotteryLHCView;
import com.dawoo.lotterybox.view.fragment.lottery_rcd.OpenLotteryPK10View;
import com.dawoo.lotterybox.view.fragment.lottery_rcd.OpenLotterySFCView;
import com.dawoo.lotterybox.view.fragment.lottery_rcd.OpenLotterySSCView;
import com.dawoo.lotterybox.view.fragment.lottery_rcd.OpenLotteryXY28View;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;

/**
 * 彩票开奖记录
 * Created by benson on 18-2-13.
 */

public class LotteryRecordPresenter<T extends IBaseView> extends BasePresenter {
    private final Context mContext;
    private T mView;
    private final ILotteryModel mModel;

    public LotteryRecordPresenter(Context context, T mView) {
        super(context, mView);

        mContext = context;
        this.mView = mView;
        mModel = new LotteryModel();
    }

    /**
     * 获取盘口数据
     */
    public void getLotteryExpect(String code) {
        Subscription subscription = mModel.getLotteryExpect(new ProgressSubscriber<>(o ->
                        ((ILastLotteryRecView) mView).onLotteryExpect((BaseHandicap) o), mContext,false),
                code);
        subList.add(subscription);
    }

    /**
     * 最新一期彩票开奖和下一期未开奖开票
     */
    public void getLastOpenedAndOpeningResult() {
        Subscription subscription = mModel.getLastOpenedAndOpeningResult(new ProgressSubscriber(o ->
                ((ILastLotteryRecView) mView).onLastLotteryRecResult((List<LotteryLastOpenAndOpening>) o), mContext));
        subList.add(subscription);
    }

    /**
     * 最新一期彩票开奖和下一期未开奖开票
     */
    public void getRefreshResult() {
        Subscription subscription = mModel.getLastOpenedAndOpeningResult(new ProgressSubscriber(o ->
                ((ILastLotteryRecView) mView).onRefreshResult((List<LotteryLastOpenAndOpening>) o), mContext));
        subList.add(subscription);
    }


    /**
     * 最新一期彩票开奖和下一期未开奖开票
     */
    public void getRefreshResult(String code) {
        Subscription subscription = mModel.getLastOpenedAndOpeningResult(new ProgressSubscriber(o ->
                        ((ILastLotteryRecView) mView).onRefreshByCodeResult((List<LotteryLastOpenAndOpening>) o), mContext, false)
                , code);
        subList.add(subscription);
    }

    /**
     * 获取已封盘最近一期开奖数据（开奖号码（空则说明正在开奖中））；
     */

    public void getRecentCloseExpect(String code) {
        Subscription subscription = mModel.getRecentCloseExpect(new ProgressSubscriber(o ->
                        ((ILastLotteryRecView) mView).onRecentCloseExpect((Handicap) o), mContext, false),
                code);
        subList.add(subscription);
    }


    public List<LotteryLastOpenAndOpening> addItemType(List<LotteryLastOpenAndOpening> lastOpenAndOpenings) {
        List<LotteryLastOpenAndOpening> list = new ArrayList();
        list.clear();
        LotteryLastOpenAndOpening itemData = null;
        for (int i = 0; i < lastOpenAndOpenings.size(); i++) {
            itemData = lastOpenAndOpenings.get(i);
            if (LotteryEnum.CQSSC.getCode().equals(itemData.getLastCode())) {
                itemData.setItemType(LotteryEnum.CQSSC.getRcdFlag());
                list.add(itemData);
            } else if (LotteryEnum.XJSSC.getCode().equals(itemData.getLastCode())) {
                itemData.setItemType(LotteryEnum.XJSSC.getRcdFlag());
                list.add(itemData);
            } else if (LotteryEnum.FFSSC.getCode().equals(itemData.getLastCode())) {
                itemData.setItemType(LotteryEnum.FFSSC.getRcdFlag());
                list.add(itemData);
            } else if (LotteryEnum.BJPK10.getCode().equals(itemData.getLastCode())) {
                itemData.setItemType(LotteryEnum.BJPK10.getRcdFlag());
                list.add(itemData);

            } else if (LotteryEnum.XYFT.getCode().equals(itemData.getLastCode())) {
                itemData.setItemType(LotteryEnum.XYFT.getRcdFlag());
                list.add(itemData);

            } else if (LotteryEnum.JSPK10.getCode().equals(itemData.getLastCode())) {
                itemData.setItemType(LotteryEnum.JSPK10.getRcdFlag());
                list.add(itemData);

            } else if (LotteryEnum.HKLHC.getCode().equals(itemData.getLastCode())) {
                itemData.setItemType(LotteryEnum.HKLHC.getRcdFlag());
                list.add(itemData);

            } else if (LotteryEnum.HBK3.getCode().equals(itemData.getLastCode())) {
                itemData.setItemType(LotteryEnum.HBK3.getRcdFlag());
                list.add(itemData);

            } else if (LotteryEnum.GXK3.getCode().equals(itemData.getLastCode())) {
                itemData.setItemType(LotteryEnum.GXK3.getRcdFlag());
                list.add(itemData);

            } else if (LotteryEnum.JSK3.getCode().equals(itemData.getLastCode())) {
                itemData.setItemType(LotteryEnum.JSK3.getRcdFlag());
                list.add(itemData);

            } else if (LotteryEnum.AHK3.getCode().equals(itemData.getLastCode())) {
                itemData.setItemType(LotteryEnum.AHK3.getRcdFlag());
                list.add(itemData);

            } else if (LotteryEnum.CQXYNC.getCode().equals(itemData.getLastCode())) {
                itemData.setItemType(LotteryEnum.CQXYNC.getRcdFlag());
                list.add(itemData);

            } else if (LotteryEnum.GDKL10.getCode().equals(itemData.getLastCode())) {
                itemData.setItemType(LotteryEnum.GDKL10.getRcdFlag());
                list.add(itemData);

            } else if (LotteryEnum.BJKL8.getCode().equals(itemData.getLastCode())) {
                itemData.setItemType(LotteryEnum.BJKL8.getRcdFlag());
                list.add(itemData);

            } else if (LotteryEnum.XY28.getCode().equals(itemData.getLastCode())) {
                itemData.setItemType(LotteryEnum.XY28.getRcdFlag());
                list.add(itemData);

            } else if (LotteryEnum.FC3D.getCode().equals(itemData.getLastCode())) {
                itemData.setItemType(LotteryEnum.FC3D.getRcdFlag());
                list.add(itemData);

            } else if (LotteryEnum.TCPL3.getCode().equals(itemData.getLastCode())) {
                itemData.setItemType(LotteryEnum.TCPL3.getRcdFlag());
                list.add(itemData);
            }
        }

        return list;
    }

    @Override
    public void onDestory() {
        super.onDestory();
    }

    public void clearCountDown(OpenLotterySSCView mCqsscview,
                               OpenLotterySSCView mXjsscview,
                               OpenLotterySSCView mFFsscview,
                               OpenLotteryPK10View mBJPK10view,
                               OpenLotteryPK10View mXYFTview,
                               OpenLotteryPK10View mJSPK10view,
                               OpenLotteryLHCView mXGLHCview,
                               OpenLotteryK3View mHBK3view,
                               OpenLotteryK3View mGXK3view,
                               OpenLotteryK3View mJSK3view,
                               OpenLotteryK3View mAHK3view,
                               OpenLotterySFCView mCQXYNCview,
                               OpenLotterySFCView mGDKLSFview,
                               OpenLotteryBJKL8View mBJKL8view,
                               OpenLotteryXY28View mXY28view,
                               OpenLotteryFC3DView mFC3Dview,
                               OpenLotteryFC3DView mTCPL3view) {
        if (mCqsscview != null) {
            mCqsscview.onDestroy();
        }

        if (mXjsscview != null) {
            mXjsscview.onDestroy();
        }

        if (mFFsscview != null) {
            mFFsscview.onDestroy();
        }

        if (mBJPK10view != null) {
            mBJPK10view.onDestroy();
        }
        if (mXYFTview != null) {
            mXYFTview.onDestroy();
        }
        if (mJSPK10view != null) {
            mJSPK10view.onDestroy();
        }
        if (mXGLHCview != null) {
            mXGLHCview.onDestroy();
        }
        if (mHBK3view != null) {
            mHBK3view.onDestroy();
        }
        if (mGXK3view != null) {
            mGXK3view.onDestroy();
        }
        if (mJSK3view != null) {
            mJSK3view.onDestroy();
        }
        if (mAHK3view != null) {
            mAHK3view.onDestroy();
        }
        if (mCQXYNCview != null) {
            mCQXYNCview.onDestroy();
        }
        if (mGDKLSFview != null) {
            mGDKLSFview.onDestroy();
        }
        if (mBJKL8view != null) {
            mBJKL8view.onDestroy();
        }
        if (mXY28view != null) {
            mXY28view.onDestroy();
        }
        if (mFC3Dview != null) {
            mFC3Dview.onDestroy();
        }
        if (mTCPL3view != null) {
            mTCPL3view.onDestroy();
        }
    }


    /**
     * 最近开奖记录
     */
    public void getRecentRecords(String code, String pageSize) {
        Subscription subscription = mModel.getRecentRecords(new ProgressSubscriber(o ->
                        ((IRecentOpenRecView) mView).onRecentRecResult((List<HandicapWithOpening>) o), mContext),
                code,
                pageSize);
        subList.add(subscription);
    }

    /**
     * 刷新最近开奖记录
     */
    public void refreshRecentRecords(String code, String pageSize) {
        Subscription subscription = mModel.getRecentRecords(new ProgressSubscriber(o ->
                        ((IRecentOpenRecView) mView).onRefreshRecResult((List<HandicapWithOpening>) o), mContext),
                code,
                pageSize);
        subList.add(subscription);
    }

    /**
     * 加载更多最近开奖记录
     */
    public void loadMoreRecentRecords(String code, String pageSize) {
        Subscription subscription = mModel.getRecentRecords(new ProgressSubscriber(o ->
                        ((IRecentOpenRecView) mView).onLoadMoreRecResult((List<HandicapWithOpening>) o), mContext),
                code,
                pageSize);
        subList.add(subscription);
    }


    public List<HandicapWithOpening> addItemType2(List<HandicapWithOpening> lastOpenAndOpenings) {
        List<HandicapWithOpening> list = new ArrayList();
        list.clear();
        HandicapWithOpening itemData = null;
        for (int i = 0; i < lastOpenAndOpenings.size(); i++) {
            itemData = lastOpenAndOpenings.get(i);
            if (LotteryEnum.CQSSC.getCode().equals(itemData.getCode())) {
                itemData.setItemType(LotteryEnum.CQSSC.getRcdFlag());
                list.add(itemData);
            } else if (LotteryEnum.XJSSC.getCode().equals(itemData.getCode())) {
                itemData.setItemType(LotteryEnum.XJSSC.getRcdFlag());
                list.add(itemData);
            } else if (LotteryEnum.FFSSC.getCode().equals(itemData.getCode())) {
                itemData.setItemType(LotteryEnum.FFSSC.getRcdFlag());
                list.add(itemData);
            } else if (LotteryEnum.BJPK10.getCode().equals(itemData.getCode())) {
                itemData.setItemType(LotteryEnum.BJPK10.getRcdFlag());
                list.add(itemData);

            } else if (LotteryEnum.XYFT.getCode().equals(itemData.getCode())) {
                itemData.setItemType(LotteryEnum.XYFT.getRcdFlag());
                list.add(itemData);

            } else if (LotteryEnum.JSPK10.getCode().equals(itemData.getCode())) {
                itemData.setItemType(LotteryEnum.JSPK10.getRcdFlag());
                list.add(itemData);

            } else if (LotteryEnum.HKLHC.getCode().equals(itemData.getCode())) {
                itemData.setItemType(LotteryEnum.HKLHC.getRcdFlag());
                list.add(itemData);

            } else if (LotteryEnum.HBK3.getCode().equals(itemData.getCode())) {
                itemData.setItemType(LotteryEnum.HBK3.getRcdFlag());
                list.add(itemData);

            } else if (LotteryEnum.GXK3.getCode().equals(itemData.getCode())) {
                itemData.setItemType(LotteryEnum.GXK3.getRcdFlag());
                list.add(itemData);

            } else if (LotteryEnum.JSK3.getCode().equals(itemData.getCode())) {
                itemData.setItemType(LotteryEnum.JSK3.getRcdFlag());
                list.add(itemData);

            } else if (LotteryEnum.AHK3.getCode().equals(itemData.getCode())) {
                itemData.setItemType(LotteryEnum.AHK3.getRcdFlag());
                list.add(itemData);

            } else if (LotteryEnum.CQXYNC.getCode().equals(itemData.getCode())) {
                itemData.setItemType(LotteryEnum.CQXYNC.getRcdFlag());
                list.add(itemData);

            } else if (LotteryEnum.GDKL10.getCode().equals(itemData.getCode())) {
                itemData.setItemType(LotteryEnum.GDKL10.getRcdFlag());
                list.add(itemData);

            } else if (LotteryEnum.BJKL8.getCode().equals(itemData.getCode())) {
                itemData.setItemType(LotteryEnum.BJKL8.getRcdFlag());
                list.add(itemData);

            } else if (LotteryEnum.XY28.getCode().equals(itemData.getCode())) {
                itemData.setItemType(LotteryEnum.XY28.getRcdFlag());
                list.add(itemData);

            } else if (LotteryEnum.FC3D.getCode().equals(itemData.getCode())) {
                itemData.setItemType(LotteryEnum.FC3D.getRcdFlag());
                list.add(itemData);

            } else if (LotteryEnum.TCPL3.getCode().equals(itemData.getCode())) {
                itemData.setItemType(LotteryEnum.TCPL3.getRcdFlag());
                list.add(itemData);
            }
        }

        return list;
    }


}
