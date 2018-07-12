package com.dawoo.lotterybox.view.activity.chart;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import com.dawoo.coretool.util.LogUtils;
import com.dawoo.coretool.util.date.DateTool;
import com.dawoo.lotterybox.ConstantValue;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.bean.lottery.BaseHandicap;
import com.dawoo.lotterybox.bean.lottery.Handicap;
import com.dawoo.lotterybox.bean.lottery.HandicapWithOpening;
import com.dawoo.lotterybox.bean.lottery.Lottery;
import com.dawoo.lotterybox.bean.lottery.LotteryOddBean;
import com.dawoo.lotterybox.bean.lottery.SaveOrderResult;
import com.dawoo.lotterybox.bean.lottery.lotteryenum.LotteryEnum;
import com.dawoo.lotterybox.mvp.presenter.LotteryPresenter;
import com.dawoo.lotterybox.mvp.view.IBaseLotteryView;
import com.dawoo.lotterybox.util.SingleToast;
import com.dawoo.lotterybox.util.lottery.initdata.BLotteryOddFactory;
import com.dawoo.lotterybox.view.activity.BaseActivity;
import com.dawoo.lotterybox.view.view.CountDownTimer;
import com.dawoo.lotterybox.view.view.CountDownTimerUtils;
import com.dawoo.lotterybox.view.view.CustomProgressDialog;
import com.dawoo.lotterybox.view.view.TimeTextView;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

import static com.dawoo.lotterybox.ConstantValue.LT_NAME;

/**
 * Created by benson on 18-4-30.
 */

public class BaseChartActivity extends BaseActivity implements IBaseLotteryView {
    @BindView(R.id.tv_now_stage)
    TextView mNextExpectTv;
    @BindView(R.id.tv_now_stage_state)
    TextView mNextExpectTvState;
    @BindView(R.id.ttv_timer)
    TimeTextView mTimeTextView;

    SimpleDateFormat sdf1 = new SimpleDateFormat("mm:ss");
    protected CustomProgressDialog mProgressDialog;
    protected Handler mHandler;
    protected CountDownTimerUtils mCDTimerUtils;
    protected LotteryPresenter mPresenter;
    protected String mLotteryCode;
    private boolean isNotLotteryWait = false; //处理某些彩种没有封盘的情况
    protected ArrayList<Handicap> mRencentList;
    protected int mCurrentIndex;
    private boolean mIsLowFLottery;

    @Override
    protected void createLayoutView() {

    }

    @Override
    protected void initViews() {

    }

    public static void startChartActivity(Context context, Class clazz, List<Handicap> mHandicapList, String lotteryCode) {
        Intent intent = new Intent(context, clazz);
        intent.putParcelableArrayListExtra(ConstantValue.RENCT_DATA, (ArrayList<? extends Parcelable>) mHandicapList);
        intent.putExtra(ConstantValue.LT_CODE, lotteryCode);
        context.startActivity(intent);
    }

    @Override
    protected void initData() {
        mProgressDialog = new CustomProgressDialog(this, getResources().getString(R.string.loading_tip));
        mProgressDialog.show();
        mHandler = new Handler();
        setCountTime();
    }


    private void setCountTime() {
        mLotteryCode = getIntent().getStringExtra(ConstantValue.LT_CODE);
        mCDTimerUtils = CountDownTimerUtils.getCountDownTimer(); // 计数倒计时
        mPresenter = new LotteryPresenter(this, this, mLotteryCode);
        mPresenter.getLotteryExpect(false); // 彩票期数
        isLowFrequency(mLotteryCode);
    }

    @Override
    protected void onDestroy() {
        if (mProgressDialog != null) {
            mProgressDialog.cancel();
        }
        mCDTimerUtils.cancel();
        countDownTimer.cancel();
        mPresenter.onDestory();
        super.onDestroy();
    }


    @Override
    public void onResultByCode(List<Handicap> handicapList) {

    }

    @Override
    public void onOneResultByCode(List<Handicap> handicapList) {
//        if (handicapList != null && handicapList.size() != 0 && mRencentList != null && mRencentList.get(0) != null) {
//            mRencentList.addAll(handicapList);
//            mRencentList.remove(0);
//            refreshViews();
//        }
    }

    @Override
    public void onRecentCloseExpect(Handicap handicap) {
        if (handicap != null && !TextUtils.isEmpty(handicap.getOpenCode()) && mRencentList != null && mRencentList.get(0) != null) {
            mRencentList.remove(0);
            mRencentList.add(handicap);
            refreshViews();
            mPresenter.getLotteryExpect(false);
            LogUtils.e("onRecentCloseExpect -> success");
        } else {
            LogUtils.e("onRecentCloseExpect -> faile countDownTime start");
            countDownTimer.start();
        }
    }

    public void refreshViews() {

    }


    @Override
    public void onLotteryExpect(BaseHandicap baseHandicap) {
        // 处理彩票的盘口
        doHandicap(baseHandicap);
    }

    @Override
    public void onLotteryOdd(Map<String, LotteryOddBean> o) {

    }

    @Override
    public void onLtToken(String token) {

    }

    @Override
    public void onSaveBetOrder(SaveOrderResult saveOrderResult) {

    }

    @Override
    public void onSureBetOrder() {

    }


    @Override
    public void onRecentRecords(List<HandicapWithOpening> handicapWithOpeningList) {

    }


    void doHandicap(BaseHandicap baseHandicap) {
        if (baseHandicap != null) {
            if (baseHandicap.getLeftOpenTime() == 0 && baseHandicap.getLeftTime() == 0) {
                SingleToast.showMsg("盘口数据获取失败");
                //mPresenter.getLotteryExpect();
                return;
            }

            mNextExpectTv.setText(getString(R.string.which_periods, baseHandicap.getExpect()));

            // 设置封单倒计时
            if (baseHandicap.getLeftOpenTime() > 0) {
                mCDTimerUtils.cancel();
                mCDTimerUtils.setMillisInFuture(Math.abs(baseHandicap.getLeftOpenTime()) * 1000)
                        .setCountDownInterval(1000)
                        .setTickDelegate(mTickDelegate)
                        .setFinishDelegate(mLeftOpenTimeFinishDelegate)
                        .start();
                mNextExpectTvState.setText(R.string.not_bet + ":");
                isNotLotteryWait = false;
            } else {
                // 设置投注截止倒计时
                mCDTimerUtils.cancel();
                mCDTimerUtils.setMillisInFuture(baseHandicap.getLeftTime() * 1000)
                        .setCountDownInterval(1000)
                        .setTickDelegate(mTickDelegate)
                        .setFinishDelegate(mLeftTimeFinishDelegate)
                        .start();
                mNextExpectTvState.setText(getString(R.string.stop) + ":");
                if (isNotLotteryWait)
//                    mPresenter.getResultByCode();
                    isNotLotteryWait = true;
            }
        }
    }

    //倒计时期间回调
    CountDownTimerUtils.TickDelegate mTickDelegate = new CountDownTimerUtils.TickDelegate() {


        @Override
        public void onTick(long pMillisUntilFinished) {
            if (mLotteryCode.equalsIgnoreCase(LotteryEnum.HKLHC.getCode())
                    || mLotteryCode.equalsIgnoreCase(LotteryEnum.FC3D.getCode())
                    || mLotteryCode.equalsIgnoreCase(LotteryEnum.TCPL3.getCode())) {
                mTimeTextView.setText(DateTool.convert2String(pMillisUntilFinished));
            } else {
                mTimeTextView.setText(DateTool.convert2String(new Date(pMillisUntilFinished), DateTool.FMT_TIME_2));
            }

        }
    };
    //距离开盘时间 倒计时结束回调
    CountDownTimerUtils.FinishDelegate mLeftOpenTimeFinishDelegate = new CountDownTimerUtils.FinishDelegate() {
        @Override
        public void onFinish() {
            if (mLotteryCode.equalsIgnoreCase(LotteryEnum.HKLHC.getCode())
                    || mLotteryCode.equalsIgnoreCase(LotteryEnum.FC3D.getCode())) {
                mTimeTextView.setText("00:00:00");
            } else {
                mTimeTextView.setText("00:00");
            }
            mPresenter.getOneResultByCode();
        }
    };
    //距离封盘时间 倒计时结束回调
    CountDownTimerUtils.FinishDelegate mLeftTimeFinishDelegate = new CountDownTimerUtils.FinishDelegate() {
        @Override
        public void onFinish() {
            if (mLotteryCode.equalsIgnoreCase(LotteryEnum.HKLHC.getCode())
                    || mLotteryCode.equalsIgnoreCase(LotteryEnum.FC3D.getCode())
                    || mLotteryCode.equalsIgnoreCase(LotteryEnum.TCPL3.getCode())) {
                mTimeTextView.setText("00:00:00");
            } else {
                mTimeTextView.setText("00:00");
            }
            mPresenter.getRecentCloseExpect();
            mPresenter.getLotteryExpect();
            RxBus.get().post(ConstantValue.EVENT_TYPE_OPENING_LOTTERY, "opening");
        }
    };

    protected CountDownTimer countDownTimer = new CountDownTimer(15000, 15000) {
        @Override
        public void onTick(long millisUntilFinished) {
            LogUtils.e("countDownTimer -> onTick");
        }

        @Override
        public void onFinish() {
            if (mLotteryCode.equalsIgnoreCase(LotteryEnum.HKLHC.getCode())
                    || mLotteryCode.equalsIgnoreCase(LotteryEnum.FC3D.getCode())
                    || mLotteryCode.equalsIgnoreCase(LotteryEnum.TCPL3.getCode())) {
                mTimeTextView.setText("00:00:00");
            } else {
                mTimeTextView.setText("00:00");
            }
            LogUtils.e("countDownTimer -> onFinish");
            mPresenter.getRecentCloseExpect();
        }
    };


    void isLowFrequency(String c) {
        if (LotteryEnum.HKLHC.getCode().equals(c) || LotteryEnum.TCPL3.getCode().equals(c) || LotteryEnum.FC3D.getCode().equals(c)) {
            mIsLowFLottery = true;
        } else {
            mIsLowFLottery = false;
        }
    }

}
