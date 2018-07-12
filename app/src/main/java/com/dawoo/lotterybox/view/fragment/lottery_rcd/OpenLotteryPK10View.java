package com.dawoo.lotterybox.view.fragment.lottery_rcd;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dawoo.coretool.ToastUtil;
import com.dawoo.coretool.util.LogUtils;
import com.dawoo.coretool.util.date.DateTool;
import com.dawoo.lotterybox.ConstantValue;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.bean.lottery.BaseHandicap;
import com.dawoo.lotterybox.bean.lottery.Handicap;
import com.dawoo.lotterybox.bean.lottery.LotteryLastOpenAndOpening;
import com.dawoo.lotterybox.mvp.presenter.LotteryRecordPresenter;
import com.dawoo.lotterybox.mvp.view.ILastLotteryRecView;
import com.dawoo.lotterybox.util.ActivityUtil;
import com.dawoo.lotterybox.util.lottery.LotteryUtil;
import com.dawoo.lotterybox.util.lottery.SSCUtil;
import com.dawoo.lotterybox.view.view.Anticlockwise;
import com.dawoo.lotterybox.view.view.CountDownTimer;
import com.dawoo.lotterybox.view.view.CountDownTimerUtils;
import com.hwangjr.rxbus.RxBus;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by benson on 18-4-11.
 */

public class OpenLotteryPK10View extends LinearLayout implements View.OnClickListener, ILastLotteryRecView {

    @BindView(R.id.ssc_name_tv)
    TextView mSscNameTv;
    @BindView(R.id.ssc_expect)
    TextView mSscExpect;
    @BindView(R.id.ssc_time)
    TextView mSscTime;
    @BindView(R.id.pk10_ball_one_tv)
    TextView mPk10BallOneTv;
    @BindView(R.id.pk10_ball_two_tv)
    TextView mPk10BallTwoTv;
    @BindView(R.id.pk10_ball_three_tv)
    TextView mPk10BallThreeTv;
    @BindView(R.id.pk10_ball_four_tv)
    TextView mPk10BallFourTv;
    @BindView(R.id.pk10_ball_five_tv)
    TextView mPk10BallFiveTv;
    @BindView(R.id.pk10_ball_six_tv)
    TextView mPk10BallSixTv;
    @BindView(R.id.pk10_ball_seven_tv)
    TextView mPk10BallSevenTv;
    @BindView(R.id.pk10_ball_eight_tv)
    TextView mPk10BallEightTv;
    @BindView(R.id.pk10_ball_nine_tv)
    TextView mPk10BallNineTv;
    @BindView(R.id.pk10_ball_ten_tv)
    TextView mPk10BallTenTv;
    @BindView(R.id.lable_count_time)
    TextView mLableCountTime;
    @BindView(R.id.pk10_value_count_time)
    Anticlockwise mValueCountTime;
    private Context mContext;
    private LotteryLastOpenAndOpening rcd;
    private CountDownTimerUtils mCountDownTimerUtils;
    private LotteryRecordPresenter mPresenter;
    private CountDownTimerUtils mCountDownTimerUtils2;

    public OpenLotteryPK10View(Context context, Context context1, LayoutInflater layoutInflater, View cqsscView) {
        super(context);
        initUI(context);
    }

    public OpenLotteryPK10View(Context context, @Nullable AttributeSet attrs, Context context1, LayoutInflater layoutInflater, View cqsscView) {
        super(context, attrs);
        initUI(context);
    }

    public OpenLotteryPK10View(Context context, @Nullable AttributeSet attrs, int defStyleAttr, Context context1, LayoutInflater layoutInflater, View cqsscView) {
        super(context, attrs, defStyleAttr);
        initUI(context);
    }


    public OpenLotteryPK10View(Context context) {
        super(context);
        initUI(context);
    }

    private void initUI(Context context) {
        mContext = context;
        mCountDownTimerUtils = CountDownTimerUtils.getCountDownTimer();
        mCountDownTimerUtils2 = CountDownTimerUtils.getCountDownTimer();
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_lottery_rcd_pk10, null);
        ButterKnife.bind(this, view);
        this.addView(view);
        initViews();
    }

    public void initViews() {
        setOnClickListener(this);
        mPresenter = new LotteryRecordPresenter<>(mContext, this);
    }

    public void setData(LotteryLastOpenAndOpening rcd) {
        this.rcd = rcd;

        setViewsData();
    }

    @Override
    public void onClick(View v) {
        if (rcd != null) {
            ActivityUtil.startRecentOpenRecActivity(rcd.getLastCode());
        }
    }

    public void setViewsData() {
        // 彩种名称
        mSscNameTv.setText(LotteryUtil.getLotteryNameByCode(rcd.getLastCode()));
        // 这期期数
        mSscExpect.setText(mContext.getResources().getString(R.string.the_expect, rcd.getLastExpect()));
        // 这期开奖时间
        mSscTime.setText(DateTool.convert2String(new Date(rcd.getLastOpenTime()), DateTool.FMT_TIME_3));
        // 下期期数
        mLableCountTime.setText(mContext.getResources().getString(R.string.the_expect_note_end, rcd.getOpeningExpect()));


        mCountDownTimerUtils.cancel();
        mCountDownTimerUtils2.cancel();
        mCountDownTimerUtils.setMillisInFuture(rcd.getLeftTime() * 1000).setCountDownInterval(1000);
        mCountDownTimerUtils.setTickDelegate(new CountDownTimerUtils.TickDelegate() {
            @Override
            public void onTick(long p) {
                mValueCountTime.setText(DateTool.convert2String(new Date(p), DateTool.FMT_TIME_2));
            }
        });
        mCountDownTimerUtils.setFinishDelegate(new CountDownTimerUtils.FinishDelegate() {
            @Override
            public void onFinish() {
                mPresenter.getLotteryExpect(rcd.getLastCode());
                // 进行封盘倒计时
                // 封盘倒计时完了拉取数据
                mValueCountTime.setText("00:00");
                mPresenter.getRecentCloseExpect(rcd.getLastCode());
            }
        });
        mCountDownTimerUtils.start();

        if (rcd.getLastOpenCode() != null && 29 == rcd.getLastOpenCode().length()) {
            String[] lastOpenCode = rcd.getLastOpenCode().split(",");
            mPk10BallOneTv.setBackgroundResource(getBgPk10(lastOpenCode[0]));
            mPk10BallTwoTv.setBackgroundResource(getBgPk10(lastOpenCode[1]));
            mPk10BallThreeTv.setBackgroundResource(getBgPk10(lastOpenCode[2]));
            mPk10BallFourTv.setBackgroundResource(getBgPk10(lastOpenCode[3]));
            mPk10BallFiveTv.setBackgroundResource(getBgPk10(lastOpenCode[4]));
            mPk10BallSixTv.setBackgroundResource(getBgPk10(lastOpenCode[5]));
            mPk10BallSevenTv.setBackgroundResource(getBgPk10(lastOpenCode[6]));
            mPk10BallEightTv.setBackgroundResource(getBgPk10(lastOpenCode[7]));
            mPk10BallNineTv.setBackgroundResource(getBgPk10(lastOpenCode[8]));
            mPk10BallTenTv.setBackgroundResource(getBgPk10(lastOpenCode[9]));
        }
    }

    int getBgPk10(String code) {
        switch (code) {
            case "01":
                return R.mipmap.car_1;
            case "02":
                return R.mipmap.car_2;
            case "03":
                return R.mipmap.car_3;
            case "04":
                return R.mipmap.car_4;
            case "05":
                return R.mipmap.car_5;
            case "06":
                return R.mipmap.car_6;
            case "07":
                return R.mipmap.car_7;
            case "08":
                return R.mipmap.car_8;
            case "09":
                return R.mipmap.car_9;
            case "10":
                return R.mipmap.car_10;
            default:
                return 0;
        }
    }

    public void onDestroy() {
        if (mCountDownTimerUtils != null) {
            mCountDownTimerUtils.cancel();
        }

        if (mCountDownTimerUtils2 != null){
            mCountDownTimerUtils2.cancel();
        }


        if (mPresenter != null) {
            mPresenter = null;
        }

        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }


    @Override
    public void onLotteryExpect(BaseHandicap baseHandicap) {
        if (rcd == null || baseHandicap == null) {
            return;
        }
        mLableCountTime.setText(mContext.getResources().getString(R.string.the_expect_note_end, baseHandicap.getExpect()));
        mCountDownTimerUtils2.cancel();
        mCountDownTimerUtils2.setMillisInFuture(baseHandicap.getLeftTime() * 1000).setCountDownInterval(1000);
        mCountDownTimerUtils2.setTickDelegate(new CountDownTimerUtils.TickDelegate() {
            @Override
            public void onTick(long p) {
                mValueCountTime.setText(DateTool.convert2String(new Date(p), DateTool.FMT_TIME_2));
            }
        });
        mCountDownTimerUtils2.setFinishDelegate(new CountDownTimerUtils.FinishDelegate() {
            @Override
            public void onFinish() {
                mValueCountTime.setText("00:00:00");
            }
        });
        mCountDownTimerUtils2.start();
    }

    @Override
    public void onLastLotteryRecResult(List<LotteryLastOpenAndOpening> lastOpenAndOpenings) {

    }

    @Override
    public void getNextRec() {

    }

    @Override
    public void onRecentCloseExpect(Handicap handicap) {
        if (handicap != null && !TextUtils.isEmpty(handicap.getOpenCode())) {
//            mPresenter.getLotteryExpect(false);
            RxBus.get().post(ConstantValue.EVENT_LOTTERY_RCD_DATA_REFRESH, rcd.getLastCode());
            LogUtils.e("onRecentCloseExpect -> success");
        } else {
            LogUtils.e("onRecentCloseExpect -> faile countDownTime start");
            countDownTimer.start();
        }
    }

    @Override
    public void onRefreshResult(List<LotteryLastOpenAndOpening> lastOpenAndOpenings) {

    }

    @Override
    public void onRefreshByCodeResult(List<LotteryLastOpenAndOpening> lastOpenAndOpening) {

    }


    protected CountDownTimer countDownTimer = new CountDownTimer(15000, 15000) {
        @Override
        public void onTick(long millisUntilFinished) {
            LogUtils.e("countDownTimer -> onTick");
        }

        @Override
        public void onFinish() {
//            mTimeTextView.setText("00:00");
            LogUtils.e("countDownTimer -> onFinish");
            mPresenter.getRecentCloseExpect(rcd.getLastCode());
        }
    };
}
