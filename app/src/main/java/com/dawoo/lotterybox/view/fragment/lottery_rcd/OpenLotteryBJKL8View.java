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
import com.dawoo.coretool.util.date.DateUtils;
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

public class OpenLotteryBJKL8View extends LinearLayout implements View.OnClickListener, ILastLotteryRecView {
    @BindView(R.id.ssc_name_tv)
    TextView mSscNameTv;
    @BindView(R.id.ssc_expect)
    TextView mSscExpect;
    @BindView(R.id.ssc_time)
    TextView mSscTime;
    @BindView(R.id.keno_xy28_ball_one_tv)
    TextView mKenoXy28BallOneTv;
    @BindView(R.id.keno_xy28_ball_two_tv)
    TextView mKenoXy28BallTwoTv;
    @BindView(R.id.keno_xy28_ball_three_tv)
    TextView mKenoXy28BallThreeTv;
    @BindView(R.id.keno_xy28_ball_four_tv)
    TextView mKenoXy28BallFourTv;
    @BindView(R.id.keno_xy28_ball_five_tv)
    TextView mKenoXy28BallFiveTv;
    @BindView(R.id.keno_xy28_ball_six_tv)
    TextView mKenoXy28BallSixTv;
    @BindView(R.id.keno_xy28_ball_seven_tv)
    TextView mKenoXy28BallSevenTv;
    @BindView(R.id.keno_xy28_ball_eight_tv)
    TextView mKenoXy28BallEightTv;
    @BindView(R.id.keno_xy28_ball_nine_tv)
    TextView mKenoXy28BallNineTv;
    @BindView(R.id.keno_xy28_ball_ten_tv)
    TextView mKenoXy28BallTenTv;
    @BindView(R.id.keno_xy28_ball_eleven_tv)
    TextView mKenoXy28BallElevenTv;
    @BindView(R.id.keno_xy28_ball_twelve_tv)
    TextView mKenoXy28BallTwelveTv;
    @BindView(R.id.keno_xy28_ball_thirteen_tv)
    TextView mKenoXy28BallThirteenTv;
    @BindView(R.id.keno_xy28_ball_fourteen_tv)
    TextView mKenoXy28BallFourteenTv;
    @BindView(R.id.keno_xy28_ball_fifteen_tv)
    TextView mKenoXy28BallFifteenTv;
    @BindView(R.id.keno_xy28_ball_sixteen_tv)
    TextView mKenoXy28BallSixteenTv;
    @BindView(R.id.keno_xy28_ball_seventeen_tv)
    TextView mKenoXy28BallSeventeenTv;
    @BindView(R.id.keno_xy28_ball_eighteen_tv)
    TextView mKenoXy28BallEighteenTv;
    @BindView(R.id.keno_xy28_ball_nineteen_tv)
    TextView mKenoXy28BallNineteenTv;
    @BindView(R.id.keno_xy28_ball_twenty_tv)
    TextView mKenoXy28BallTwentyTv;
    @BindView(R.id.lable_count_time)
    TextView mLableCountTime;
    @BindView(R.id.keno_value_count_time)
    Anticlockwise mValueCountTime;
    private Context mContext;
    private LotteryLastOpenAndOpening rcd;
    private CountDownTimerUtils mCountDownTimerUtils;
    private LotteryRecordPresenter mPresenter;
    private CountDownTimerUtils mCountDownTimerUtils2;

    public OpenLotteryBJKL8View(Context context, Context context1, LayoutInflater layoutInflater, View cqsscView) {
        super(context);
        initUI(context);
    }

    public OpenLotteryBJKL8View(Context context, @Nullable AttributeSet attrs, Context context1, LayoutInflater layoutInflater, View cqsscView) {
        super(context, attrs);
        initUI(context);
    }

    public OpenLotteryBJKL8View(Context context, @Nullable AttributeSet attrs, int defStyleAttr, Context context1, LayoutInflater layoutInflater, View cqsscView) {
        super(context, attrs, defStyleAttr);
        initUI(context);
    }


    public OpenLotteryBJKL8View(Context context) {
        super(context);
        initUI(context);
    }

    private void initUI(Context context) {
        mContext = context;
        mCountDownTimerUtils = CountDownTimerUtils.getCountDownTimer();
        mCountDownTimerUtils2 = CountDownTimerUtils.getCountDownTimer();
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_lottery_rcd_keno, null);
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


        if (rcd.getLastOpenCode() != null && 59 == rcd.getLastOpenCode().length()) {
            String[] lastOpenCode = rcd.getLastOpenCode().split(",");
            mKenoXy28BallOneTv.setText(lastOpenCode[0]);
            mKenoXy28BallTwoTv.setText(lastOpenCode[1]);
            mKenoXy28BallThreeTv.setText(lastOpenCode[2]);
            mKenoXy28BallFourTv.setText(lastOpenCode[3]);
            mKenoXy28BallFiveTv.setText(lastOpenCode[4]);
            mKenoXy28BallSixTv.setText(lastOpenCode[5]);
            mKenoXy28BallSevenTv.setText(lastOpenCode[6]);
            mKenoXy28BallEightTv.setText(lastOpenCode[7]);
            mKenoXy28BallNineTv.setText(lastOpenCode[8]);
            mKenoXy28BallTenTv.setText(lastOpenCode[9]);
            mKenoXy28BallElevenTv.setText(lastOpenCode[10]);
            mKenoXy28BallTwelveTv.setText(lastOpenCode[11]);
            mKenoXy28BallThirteenTv.setText(lastOpenCode[12]);
            mKenoXy28BallFourteenTv.setText(lastOpenCode[13]);
            mKenoXy28BallFifteenTv.setText(lastOpenCode[14]);
            mKenoXy28BallSixteenTv.setText(lastOpenCode[15]);
            mKenoXy28BallSeventeenTv.setText(lastOpenCode[16]);
            mKenoXy28BallEighteenTv.setText(lastOpenCode[17]);
            mKenoXy28BallNineteenTv.setText(lastOpenCode[18]);
            mKenoXy28BallTwentyTv.setText(lastOpenCode[19]);
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
                mValueCountTime.setText("00:00");
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
