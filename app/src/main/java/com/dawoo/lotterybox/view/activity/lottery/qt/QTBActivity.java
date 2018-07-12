package com.dawoo.lotterybox.view.activity.lottery.qt;

import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.widget.ImageView;

import com.dawoo.coretool.util.date.DateTool;
import com.dawoo.lotterybox.ConstantValue;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.bean.OrderEventBean;
import com.dawoo.lotterybox.bean.lottery.BaseHandicap;
import com.dawoo.lotterybox.bean.lottery.Handicap;
import com.dawoo.lotterybox.bean.lottery.HandicapWithOpening;
import com.dawoo.lotterybox.bean.playtype.PlayChooseBean;
import com.dawoo.lotterybox.mvp.view.LotteryBView;
import com.dawoo.lotterybox.util.lottery.initdata.Lottery_B_DataUtils;
import com.dawoo.lotterybox.view.activity.lottery.BaseLotteryBActivity;
import com.dawoo.lotterybox.view.fragment.qt.QT_DW_Fragment;
import com.dawoo.lotterybox.view.fragment.qt.QT_HS_Fragment;
import com.dawoo.lotterybox.view.fragment.qt.QT_KD_Fragment;
import com.dawoo.lotterybox.view.fragment.qt.QT_ZH_Fragment;
import com.dawoo.lotterybox.view.fragment.qt.QT_ZX3_Fragment;
import com.dawoo.lotterybox.view.fragment.qt.QT_ZX6_Fragment;
import com.dawoo.lotterybox.view.view.CountDownTimerUtils;
import com.dawoo.lotterybox.view.view.LotteryBFragmentManager;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;

import static com.dawoo.lotterybox.ConstantValue.LT_CODE;
import static com.dawoo.lotterybox.ConstantValue.LT_NAME;

public class QTBActivity extends BaseLotteryBActivity implements LotteryBView, BaseLotteryBActivity.OnTheNextToDoListener {

    @BindView(R.id.iv_red_result_1)
    ImageView mIvRedResult1;
    @BindView(R.id.iv_red_result_2)
    ImageView mIvRedResult2;
    @BindView(R.id.iv_red_result_3)
    ImageView mIvRedResult3;

    // 必填
    protected void createLayoutView() {
        super.createLayoutView();

        setContentView(R.layout.activity_qtb);
        RxBus.get().register(this);
        // 当前彩种代码
        mLotteryCode = getIntent().getStringExtra(LT_CODE);
        mLotteryName = getIntent().getStringExtra(LT_NAME);
        // 120期历史列表ItemView
        mHistoryAdapter = new QTB_History_QuickAdapter(R.layout.item_ssc_b_lottery_result);
        setOnTheNextToDoListener(this);
    }

    @Override
    public void onNextInit() {
        OPEN_DELATED = 20000;
        setTickDelegate(mTickDelegate);
        setLeftOpenTimeFinishDelegate(mLeftOpenTimeFinishDelegate);
        setLeftTimeFinishDelegate(mLeftTimeFinishDelegate);
    }


    @Override
    public void onNextDataInit() {
        // 必填
        LotteryBFragmentManager.getInstance().switchFragment(mFragmentManager, QT_DW_Fragment.class);
    }


    @Override
    public void onNextResultByCode(List<Handicap> handicapList) {
        setResultToBall(handicapList.get(0).getOpenCode());
    }

    @Override
    public void onNextRecentRecords(List<HandicapWithOpening> handicapWithOpeningList) {

    }

    @Override
    public void openLottery() {
        mLlFendan.setVisibility(View.GONE);
        mIvRedResult1.setImageResource(R.drawable.anim_blue_ball_result);
        mIvRedResult2.setImageResource(R.drawable.anim_blue_ball_result);
        mIvRedResult3.setImageResource(R.drawable.anim_blue_ball_result);
        ((AnimationDrawable) mIvRedResult1.getDrawable()).start();
        ((AnimationDrawable) mIvRedResult2.getDrawable()).start();
        ((AnimationDrawable) mIvRedResult3.getDrawable()).start();
    }

    /**
     * 显示当前玩法的fragment
     *
     * @param playChooseBean
     */
    @Override
    public void onNextShowFragment(PlayChooseBean playChooseBean) {
        String name = playChooseBean.getBetName();
        LotteryBFragmentManager fm = LotteryBFragmentManager.getInstance();
        if (name.equals(mPlayChooseBeans.get(0).getBetName())) {
            fm.switchFragment(mFragmentManager, QT_DW_Fragment.class, mLl_anim_dog);
        } else if (name.equals(mPlayChooseBeans.get(1).getBetName())) {
            fm.switchFragment(mFragmentManager, QT_ZH_Fragment.class, mLl_anim_dog);
        } else if (name.equals(mPlayChooseBeans.get(2).getBetName())) {
            fm.switchFragment(mFragmentManager, QT_HS_Fragment.class, mLl_anim_dog);
        } else if (name.equals(mPlayChooseBeans.get(3).getBetName())) {
            fm.switchFragment(mFragmentManager, QT_ZX3_Fragment.class, mLl_anim_dog);
        } else if (name.equals(mPlayChooseBeans.get(4).getBetName())) {
            fm.switchFragment(mFragmentManager, QT_ZX6_Fragment.class, mLl_anim_dog);
        } else if (name.equals(mPlayChooseBeans.get(5).getBetName())) {
            fm.switchFragment(mFragmentManager, QT_KD_Fragment.class, mLl_anim_dog);
        }
    }

    public void setResultToBall(String string) {
        mTvXdh.setText(Lottery_B_DataUtils.getSanStarStatus(string));
        String[] numbers = string.split(",");
        mIvRedResult1.setImageResource(getImageResourceFormNumber(numbers[0]));
        mIvRedResult2.setImageResource(getImageResourceFormNumber(numbers[1]));
        mIvRedResult3.setImageResource(getImageResourceFormNumber(numbers[2]));
    }

    public int getImageResourceFormNumber(String number) {
        int imageResource = 0;
        if ("0".equals(number))
            imageResource = R.mipmap.red_ball_result_0;
        else if ("1".equals(number))
            imageResource = R.mipmap.red_ball_result_1;
        else if ("2".equals(number))
            imageResource = R.mipmap.red_ball_result_2;
        else if ("3".equals(number))
            imageResource = R.mipmap.red_ball_result_3;
        else if ("4".equals(number))
            imageResource = R.mipmap.red_ball_result_4;
        else if ("5".equals(number))
            imageResource = R.mipmap.red_ball_result_5;
        else if ("6".equals(number))
            imageResource = R.mipmap.red_ball_result_6;
        else if ("7".equals(number))
            imageResource = R.mipmap.red_ball_result_7;
        else if ("8".equals(number))
            imageResource = R.mipmap.red_ball_result_8;
        else if ("9".equals(number))
            imageResource = R.mipmap.red_ball_result_9;

        return imageResource;
    }

    //倒计时期间回调
    CountDownTimerUtils.TickDelegate mTickDelegate = new CountDownTimerUtils.TickDelegate() {

        @Override
        public void onTick(long pMillisUntilFinished) {
            mTimeTextView.setText(DateTool.convert2String(pMillisUntilFinished));
        }
    };
    //距离开盘时间 倒计时结束回调
    CountDownTimerUtils.FinishDelegate mLeftOpenTimeFinishDelegate = new CountDownTimerUtils.FinishDelegate() {
        @Override
        public void onFinish() {
            mTimeTextView.setText("00:00");
            openLottery();
            mPresenter.getLotteryExpect();
        }
    };

    //距离封盘时间 倒计时结束回调
    CountDownTimerUtils.FinishDelegate mLeftTimeFinishDelegate = new CountDownTimerUtils.FinishDelegate() {
        @Override
        public void onFinish() {

            mTimeTextView.setText("00:00");
            mLlFendan.setVisibility(View.VISIBLE);
            mIvRedResult1.setImageResource(R.mipmap.red_ball_result_);
            mIvRedResult2.setImageResource(R.mipmap.red_ball_result_);
            mIvRedResult3.setImageResource(R.mipmap.red_ball_result_);
            mTvXdh.setText("");
            mPresenter.getRecentCloseExpect();
            mPresenter.getLotteryExpect();

        }
    };


    @Subscribe(tags = {@Tag(ConstantValue.EVENT_LOTTERY_CART_DATA_CHANGE)})
    public void orderDataChange(OrderEventBean orderEventBean) {
        super.orderDataChange(orderEventBean);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.get().unregister(this);
    }
}
