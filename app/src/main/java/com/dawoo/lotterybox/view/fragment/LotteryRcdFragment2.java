package com.dawoo.lotterybox.view.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.dawoo.coretool.util.LogUtils;
import com.dawoo.lotterybox.ConstantValue;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.bean.BetParamBean;
import com.dawoo.lotterybox.bean.lottery.BaseHandicap;
import com.dawoo.lotterybox.bean.lottery.Handicap;
import com.dawoo.lotterybox.bean.lottery.LotteryLastOpenAndOpening;
import com.dawoo.lotterybox.bean.lottery.lotteryenum.LotteryEnum;
import com.dawoo.lotterybox.mvp.presenter.LotteryRecordPresenter;
import com.dawoo.lotterybox.mvp.view.ILastLotteryRecView;
import com.dawoo.lotterybox.view.fragment.lottery_rcd.OpenLotteryBJKL8View;
import com.dawoo.lotterybox.view.fragment.lottery_rcd.OpenLotteryFC3DView;
import com.dawoo.lotterybox.view.fragment.lottery_rcd.OpenLotteryK3View;
import com.dawoo.lotterybox.view.fragment.lottery_rcd.OpenLotteryLHCView;
import com.dawoo.lotterybox.view.fragment.lottery_rcd.OpenLotteryPK10View;
import com.dawoo.lotterybox.view.fragment.lottery_rcd.OpenLotterySFCView;
import com.dawoo.lotterybox.view.fragment.lottery_rcd.OpenLotterySSCView;
import com.dawoo.lotterybox.view.fragment.lottery_rcd.OpenLotteryXY28View;
import com.dawoo.lotterybox.view.view.dialog.BettingSetDialog;
import com.dawoo.lotterybox.view.view.dialog.OrderTipDialog;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.dawoo.lotterybox.view.activity.BaseActivity.addStatusView;


public class LotteryRcdFragment2 extends BaseFragment implements ILastLotteryRecView, OnRefreshListener {


    @BindView(R.id.message_push_tv)
    TextView mMessagePushTv;
    @BindView(R.id.content_ll)
    LinearLayout mContentLL;
    @BindView(R.id.swipeToLoadLayout)
    SwipeToLoadLayout mSwipeToLoadLayout;
    Unbinder unbinder;
    private LotteryRecordPresenter mPresenter;
    private Handler mHandler;
    private OpenLotterySSCView mCqsscview;
    private OpenLotterySSCView mXjsscview;
    private OpenLotterySSCView mFFsscview;
    private OpenLotteryPK10View mBJPK10view;
    private OpenLotteryPK10View mXYFTview;
    private OpenLotteryPK10View mJSPK10view;
    private OpenLotteryLHCView mXGLHCview;
    private OpenLotteryK3View mHBK3view;
    private OpenLotteryK3View mGXK3view;
    private OpenLotteryK3View mJSK3view;
    private OpenLotteryK3View mAHK3view;
    private OpenLotterySFCView mCQXYNCview;
    private OpenLotterySFCView mGDKLSFview;
    private OpenLotteryBJKL8View mBJKL8view;
    private OpenLotteryXY28View mXY28view;
    private OpenLotteryFC3DView mFC3Dview;
    private OpenLotteryFC3DView mTCPL3view;

    public LotteryRcdFragment2() {
    }

    public static LotteryRcdFragment2 newInstance() {
        LotteryRcdFragment2 fragment = new LotteryRcdFragment2();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        RxBus.get().unregister(this);
        if (mPresenter != null) {
            mPresenter.clearCountDown(mCqsscview,
                    mXjsscview,
                    mFFsscview,
                    mBJPK10view,
                    mXYFTview,
                    mJSPK10view,
                    mXGLHCview,
                    mHBK3view,
                    mGXK3view,
                    mJSK3view,
                    mAHK3view,
                    mCQXYNCview,
                    mGDKLSFview,
                    mBJKL8view,
                    mXY28view,
                    mFC3Dview,
                    mTCPL3view);
            mPresenter.onDestory();
        }
        unbinder.unbind();
        super.onDestroyView();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_lottery_record2, container, false);
        addStatusView(v, mContext);
        unbinder = ButterKnife.bind(this, v);
        initViews();
        initData();
        return v;
    }

    private void initViews() {
        mSwipeToLoadLayout.setRefreshEnabled(true);
        mSwipeToLoadLayout.setLoadMoreEnabled(false);
        mSwipeToLoadLayout.setOnRefreshListener(this);

    }

    void initData() {
        mHandler = new Handler();
        RxBus.get().register(this);
        mPresenter = new LotteryRecordPresenter<>(mContext, this);
    }

    @Override
    protected void loadData() {
        mPresenter.getLastOpenedAndOpeningResult();
    }


    @OnClick(R.id.message_push_tv)
    public void onViewClicked() {
        BettingSetDialog bettingSetDialog = new BettingSetDialog(mContext, new BetParamBean());
        bettingSetDialog.show();
    }

    @Override
    public void onRefresh() {
        mPresenter.getRefreshResult();
    }


    @Override
    public void onLotteryExpect(BaseHandicap baseHandicap) {

    }

    @Override
    public void onLastLotteryRecResult(List<LotteryLastOpenAndOpening> lastOpenAndOpenings) {
        setData(lastOpenAndOpenings);

    }


    @Override
    public void onRefreshResult(List<LotteryLastOpenAndOpening> lastOpenAndOpenings) {
        mSwipeToLoadLayout.setRefreshing(false);
        setData(lastOpenAndOpenings);
    }

    @Override
    public void onRefreshByCodeResult(List<LotteryLastOpenAndOpening> lastOpenAndOpenings) {
        // 单条刷新
        setData(lastOpenAndOpenings);
    }

    @Override
    public void getNextRec() {

    }

    @Override
    public void onRecentCloseExpect(Handicap handicap) {
    }

    private void setData(List<LotteryLastOpenAndOpening> list) {
        if (list != null) {
            LotteryLastOpenAndOpening itemData;
            for (int i = 0; i < list.size(); i++) {
                itemData = list.get(i);
                // 时时彩
                if (LotteryEnum.CQSSC.getCode().equals(itemData.getLastCode())) {
                    setCQSSC(itemData);

                } else if (LotteryEnum.XJSSC.getCode().equals(itemData.getLastCode())) {
                    setXJSSC(itemData);
                } else if (LotteryEnum.FFSSC.getCode().equals(itemData.getLastCode())) {
                    setFFSSC(itemData);
                }
                // pk10
                else if (LotteryEnum.BJPK10.getCode().equals(itemData.getLastCode())) {
                    setBJPK10(itemData);
                } else if (LotteryEnum.XYFT.getCode().equals(itemData.getLastCode())) {
                    setXYFT(itemData);
                } else if (LotteryEnum.JSPK10.getCode().equals(itemData.getLastCode())) {
                    setJSPK10(itemData);
                }
                //LHC
                else if (LotteryEnum.HKLHC.getCode().equals(itemData.getLastCode())) {
                    setXGLHC(itemData);
                }
                // k3
                else if (LotteryEnum.HBK3.getCode().equals(itemData.getLastCode())) {
                    setHBK3(itemData);
                } else if (LotteryEnum.GXK3.getCode().equals(itemData.getLastCode())) {
                    setGXK3(itemData);
                } else if (LotteryEnum.JSK3.getCode().equals(itemData.getLastCode())) {
                    setJSK3(itemData);
                } else if (LotteryEnum.AHK3.getCode().equals(itemData.getLastCode())) {
                    setAHK3(itemData);
                }
                // sfc 重庆幸运农场

                else if (LotteryEnum.CQXYNC.getCode().equals(itemData.getLastCode())) {
                    setCQXYNC(itemData);
                }
                // sfc 广东快乐十分
                else if (LotteryEnum.GDKL10.getCode().equals(itemData.getLastCode())) {
                    setGDKLSF(itemData);
                }
                // keno 北京快乐8
                else if (LotteryEnum.BJKL8.getCode().equals(itemData.getLastCode())) {
                    setBJKL8(itemData);
                }
                // 幸运28
                else if (LotteryEnum.XY28.getCode().equals(itemData.getLastCode())) {
                    setXY28(itemData);
                }
                // 福彩3D
                else if (LotteryEnum.FC3D.getCode().equals(itemData.getLastCode())) {
                    setFC3D(itemData);
                }
                // 体彩排列3
                else if (LotteryEnum.TCPL3.getCode().equals(itemData.getLastCode())) {
                    setTCPL3(itemData);
                }

            }
        }
    }

    @Subscribe(tags = {@Tag(ConstantValue.EVENT_TYPE_NETWORK_EXCEPTION)})
    public void stopTheRefreshStatus(String s) {
        mSwipeToLoadLayout.setRefreshing(false);
    }

    @Subscribe(tags = {@Tag(ConstantValue.EVENT_LOTTERY_RCD_DATA_REFRESH)})
    public void rcdDataRefresh(String code) {
        //刷新data
        mPresenter.getRefreshResult(code);
    }

    /**
     * 重庆时时彩
     *
     * @param rcd
     */
    void setCQSSC(LotteryLastOpenAndOpening rcd) {
        if (mCqsscview == null) {
            mCqsscview = new OpenLotterySSCView(mContext);
            mContentLL.addView(mCqsscview);
        }
        mHandler.post(() -> mCqsscview.setData(rcd));
    }

    /**
     * 新疆时时彩
     *
     * @param rcd
     */
    void setXJSSC(LotteryLastOpenAndOpening rcd) {
        if (mXjsscview == null) {
            mXjsscview = new OpenLotterySSCView(mContext);
            mContentLL.addView(mXjsscview);
        }
        mHandler.post(() -> mXjsscview.setData(rcd));
    }

    /**
     * 分分时时彩
     *
     * @param rcd
     */
    void setFFSSC(LotteryLastOpenAndOpening rcd) {
        if (mFFsscview == null) {
            mFFsscview = new OpenLotterySSCView(mContext);
            mContentLL.addView(mFFsscview);
        }
        mHandler.post(() -> mFFsscview.setData(rcd));
    }

    /**
     * 北京pk10
     *
     * @param rcd
     */
    void setBJPK10(LotteryLastOpenAndOpening rcd) {
        if (mBJPK10view == null) {
            mBJPK10view = new OpenLotteryPK10View(mContext);
            mContentLL.addView(mBJPK10view);
        }
        mHandler.post(() -> mBJPK10view.setData(rcd));
    }

    /**
     * 幸运飞艇
     *
     * @param rcd
     */
    void setXYFT(LotteryLastOpenAndOpening rcd) {
        if (mXYFTview == null) {
            mXYFTview = new OpenLotteryPK10View(mContext);
            mContentLL.addView(mXYFTview);
        }
        mHandler.post(() -> mXYFTview.setData(rcd));
    }

    /**
     * 极速PK10
     *
     * @param rcd
     */
    void setJSPK10(LotteryLastOpenAndOpening rcd) {
        if (mJSPK10view == null) {
            mJSPK10view = new OpenLotteryPK10View(mContext);
            mContentLL.addView(mJSPK10view);
        }
        mHandler.post(() -> {
            mJSPK10view.setData(rcd);
        });
    }

    /**
     * 香港六合彩
     *
     * @param rcd
     */
    void setXGLHC(LotteryLastOpenAndOpening rcd) {
        if (mXGLHCview == null) {
            mXGLHCview = new OpenLotteryLHCView(mContext);
            mContentLL.addView(mXGLHCview);
        }
        mHandler.post(() -> mXGLHCview.setData(rcd));
    }

    /**
     * 湖北k3
     *
     * @param rcd
     */
    void setHBK3(LotteryLastOpenAndOpening rcd) {
        if (mHBK3view == null) {
            mHBK3view = new OpenLotteryK3View(mContext);
            mContentLL.addView(mHBK3view);
        }
        mHandler.post(() -> mHBK3view.setData(rcd));
    }

    /**
     * 广西k3
     *
     * @param rcd
     */
    void setGXK3(LotteryLastOpenAndOpening rcd) {
        if (mGXK3view == null) {
            mGXK3view = new OpenLotteryK3View(mContext);
            mContentLL.addView(mGXK3view);
        }
        mHandler.post(() -> mGXK3view.setData(rcd));
    }

    /**
     * 江苏k3
     *
     * @param rcd
     */
    void setJSK3(LotteryLastOpenAndOpening rcd) {
        if (mJSK3view == null) {
            mJSK3view = new OpenLotteryK3View(mContext);
            mContentLL.addView(mJSK3view);
        }
        mHandler.post(() -> mJSK3view.setData(rcd));
    }

    /**
     * 安徽k3
     *
     * @param rcd
     */
    void setAHK3(LotteryLastOpenAndOpening rcd) {
        if (mAHK3view == null) {
            mAHK3view = new OpenLotteryK3View(mContext);
            mContentLL.addView(mAHK3view);
        }
        mHandler.post(() -> mAHK3view.setData(rcd));
    }

    /**
     * 重庆快乐农场
     *
     * @param rcd
     */
    void setCQXYNC(LotteryLastOpenAndOpening rcd) {
        if (mCQXYNCview == null) {
            mCQXYNCview = new OpenLotterySFCView(mContext);
            mContentLL.addView(mCQXYNCview);
        }
        mHandler.post(() -> mCQXYNCview.setData(rcd));
    }

    /**
     * 广东快乐十分
     *
     * @param rcd
     */
    void setGDKLSF(LotteryLastOpenAndOpening rcd) {
        if (mGDKLSFview == null) {
            mGDKLSFview = new OpenLotterySFCView(mContext);
            mContentLL.addView(mGDKLSFview);
        }
        mHandler.post(() -> mGDKLSFview.setData(rcd));
    }

    /**
     * 北京快乐8
     *
     * @param rcd
     */
    void setBJKL8(LotteryLastOpenAndOpening rcd) {
        if (mBJKL8view == null) {
            mBJKL8view = new OpenLotteryBJKL8View(mContext);
            mContentLL.addView(mBJKL8view);
        }
        mHandler.post(() -> mBJKL8view.setData(rcd));
    }

    /**
     * 幸运28
     *
     * @param rcd
     */
    void setXY28(LotteryLastOpenAndOpening rcd) {
        if (mXY28view == null) {
            mXY28view = new OpenLotteryXY28View(mContext);
            mContentLL.addView(mXY28view);
        }
        mHandler.post(() -> mXY28view.setData(rcd));
    }

    /**
     * 福彩3D
     *
     * @param rcd
     */
    void setFC3D(LotteryLastOpenAndOpening rcd) {
        if (mFC3Dview == null) {
            mFC3Dview = new OpenLotteryFC3DView(mContext);
            mContentLL.addView(mFC3Dview);
        }
        mHandler.post(() -> mFC3Dview.setData(rcd));
    }

    /**
     * 体彩排列3
     *
     * @param rcd
     */
    void setTCPL3(LotteryLastOpenAndOpening rcd) {
        if (mTCPL3view == null) {
            mTCPL3view = new OpenLotteryFC3DView(mContext);
            mContentLL.addView(mTCPL3view);
        }
        mHandler.post(() -> mTCPL3view.setData(rcd));
    }


    /**
     * 单条刷新
     *
     * @param singleData
     */
    public void setSingleData(LotteryLastOpenAndOpening singleData) {
        List<LotteryLastOpenAndOpening> list = new ArrayList<>();
        list.add(singleData);
        setData(list);
    }
}

