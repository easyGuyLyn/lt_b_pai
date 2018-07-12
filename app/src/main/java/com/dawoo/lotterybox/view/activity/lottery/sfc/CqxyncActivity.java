package com.dawoo.lotterybox.view.activity.lottery.sfc;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dawoo.lotterybox.ConstantValue;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.bean.OrderEventBean;
import com.dawoo.lotterybox.bean.lottery.BaseHandicap;
import com.dawoo.lotterybox.bean.lottery.Handicap;
import com.dawoo.lotterybox.bean.lottery.HandicapWithOpening;
import com.dawoo.lotterybox.bean.playtype.PlayChooseBean;
import com.dawoo.lotterybox.mvp.view.LotteryBView;
import com.dawoo.lotterybox.view.activity.lottery.BaseLotteryBActivity;
import com.dawoo.lotterybox.view.fragment.xync.Cqxync_DataUtils;
import com.dawoo.lotterybox.view.fragment.xync.Cqxync_Dragon_Tiger_Fragment;
import com.dawoo.lotterybox.view.fragment.xync.Cqxync_Eight_Fragment;
import com.dawoo.lotterybox.view.fragment.xync.Cqxync_First_Fragment;
import com.dawoo.lotterybox.view.fragment.xync.Cqxync_Five_Fragment;
import com.dawoo.lotterybox.view.fragment.xync.Cqxync_Fourth_Fragment;
import com.dawoo.lotterybox.view.fragment.xync.Cqxync_SM_Fragment;
import com.dawoo.lotterybox.view.fragment.xync.Cqxync_Second_Fragment;
import com.dawoo.lotterybox.view.fragment.xync.Cqxync_Seven_Fragment;
import com.dawoo.lotterybox.view.fragment.xync.Cqxync_Six_Fragment;
import com.dawoo.lotterybox.view.fragment.xync.Cqxync_Third_Fragment;
import com.dawoo.lotterybox.view.view.CountDownTimerUtils;
import com.dawoo.lotterybox.view.view.LotteryBFragmentManager;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.dawoo.lotterybox.ConstantValue.LT_CODE;
import static com.dawoo.lotterybox.ConstantValue.LT_NAME;

/**
 * Created by archar on 18-3-27.
 */

public class CqxyncActivity extends BaseLotteryBActivity implements LotteryBView, BaseLotteryBActivity.OnTheNextToDoListener {

    @BindView(R.id.tv_small_helper)
    TextView tvSmallHelper;
    @BindView(R.id.rl_activity_title)
    RelativeLayout rlActivityTitle;
    @BindView(R.id.iv_red_result_1)
    ImageView mIvRedResult1;
    @BindView(R.id.iv_red_result_2)
    ImageView mIvRedResult2;
    @BindView(R.id.iv_red_result_3)
    ImageView mIvRedResult3;
    @BindView(R.id.iv_red_result_4)
    ImageView mIvRedResult4;
    @BindView(R.id.iv_red_result_5)
    ImageView mIvRedResult5;
    @BindView(R.id.iv_red_result_6)
    ImageView mIvRedResult6;
    @BindView(R.id.iv_red_result_7)
    ImageView mIvRedResult7;
    @BindView(R.id.iv_red_result_8)
    ImageView mIvRedResult8;

    @Override
    protected void createLayoutView() {
        super.createLayoutView();
        setContentView(R.layout.activity_cqxync);
        RxBus.get().register(this);
        // 当前彩种代码
        mLotteryCode = getIntent().getStringExtra(LT_CODE);
        mLotteryName = getIntent().getStringExtra(LT_NAME);
        mHistoryAdapter = new AwardResultsQuickAdapter(R.layout.item_sfc_lottery_result);
        setOnTheNextToDoListener(this);
    }


    @Override
    public void onNextInit() {
        OPEN_DELATED = 10000;
        setTickDelegate(mTickDelegate);
        setLeftOpenTimeFinishDelegate(mLeftOpenTimeFinishDelegate);
        setLeftTimeFinishDelegate(mLeftTimeFinishDelegate);
    }

    @Override
    public void onNextDataInit() {
        LotteryBFragmentManager.getInstance().switchFragment(mFragmentManager, Cqxync_SM_Fragment.class);
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
        runAnimation();
    }

    @Override
    public void onNextShowFragment(PlayChooseBean playChooseBean) {
        String name = playChooseBean.getBetName();
        if (name.equals(mPlayChooseBeans.get(0).getBetName())) {
            LotteryBFragmentManager.getInstance().switchFragment(mFragmentManager, Cqxync_SM_Fragment.class, mLl_anim_dog);
        } else if (name.equals(mPlayChooseBeans.get(1).getBetName())) {
            LotteryBFragmentManager.getInstance().switchFragment(mFragmentManager, Cqxync_First_Fragment.class, mLl_anim_dog);
        } else if (name.equals(mPlayChooseBeans.get(2).getBetName())) {
            LotteryBFragmentManager.getInstance().switchFragment(mFragmentManager, Cqxync_Second_Fragment.class, mLl_anim_dog);
        } else if (name.equals(mPlayChooseBeans.get(3).getBetName())) {
            LotteryBFragmentManager.getInstance().switchFragment(mFragmentManager, Cqxync_Third_Fragment.class, mLl_anim_dog);
        } else if (name.equals(mPlayChooseBeans.get(4).getBetName())) {
            LotteryBFragmentManager.getInstance().switchFragment(mFragmentManager, Cqxync_Fourth_Fragment.class, mLl_anim_dog);
        } else if (name.equals(mPlayChooseBeans.get(5).getBetName())) {
            LotteryBFragmentManager.getInstance().switchFragment(mFragmentManager, Cqxync_Five_Fragment.class, mLl_anim_dog);
        } else if (name.equals(mPlayChooseBeans.get(6).getBetName())) {
            LotteryBFragmentManager.getInstance().switchFragment(mFragmentManager, Cqxync_Six_Fragment.class, mLl_anim_dog);
        } else if (name.equals(mPlayChooseBeans.get(7).getBetName())) {
            LotteryBFragmentManager.getInstance().switchFragment(mFragmentManager, Cqxync_Seven_Fragment.class, mLl_anim_dog);
        } else if (name.equals(mPlayChooseBeans.get(8).getBetName())) {
            LotteryBFragmentManager.getInstance().switchFragment(mFragmentManager, Cqxync_Eight_Fragment.class, mLl_anim_dog);
        } else if (name.equals(mPlayChooseBeans.get(9).getBetName())) {
            LotteryBFragmentManager.getInstance().switchFragment(mFragmentManager, Cqxync_Dragon_Tiger_Fragment.class, mLl_anim_dog);
        }
    }


    public void setResultToBall(String string) {
        String[] numbers = string.split(",");
        mIvRedResult1.setImageResource(Cqxync_DataUtils.getCode(numbers[0]));
        mIvRedResult2.setImageResource(Cqxync_DataUtils.getCode(numbers[1]));
        mIvRedResult3.setImageResource(Cqxync_DataUtils.getCode(numbers[2]));
        mIvRedResult4.setImageResource(Cqxync_DataUtils.getCode(numbers[3]));
        mIvRedResult5.setImageResource(Cqxync_DataUtils.getCode(numbers[4]));
        mIvRedResult6.setImageResource(Cqxync_DataUtils.getCode(numbers[5]));
        mIvRedResult7.setImageResource(Cqxync_DataUtils.getCode(numbers[6]));
        mIvRedResult8.setImageResource(Cqxync_DataUtils.getCode(numbers[7]));
        mTvXdh.setVisibility(View.VISIBLE);
        mTvXdh.setText(Cqxync_DataUtils.bigAndDorL(numbers));
    }

    //倒计时期间回调
    CountDownTimerUtils.TickDelegate mTickDelegate = new CountDownTimerUtils.TickDelegate() {
        SimpleDateFormat sdf1 = new SimpleDateFormat("mm:ss");

        @Override
        public void onTick(long pMillisUntilFinished) {
            String str1 = sdf1.format(pMillisUntilFinished);
            mTimeTextView.setText(str1);
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
            mIvRedResult4.setImageResource(R.mipmap.red_ball_result_);
            mIvRedResult5.setImageResource(R.mipmap.red_ball_result_);
            mIvRedResult6.setImageResource(R.mipmap.red_ball_result_);
            mIvRedResult7.setImageResource(R.mipmap.red_ball_result_);
            mIvRedResult8.setImageResource(R.mipmap.red_ball_result_);
            mTvXdh.setText("");
            mPresenter.getRecentCloseExpect();
            mPresenter.getLotteryExpect();

        }
    };


    class AwardResultsQuickAdapter extends BaseQuickAdapter {
        public AwardResultsQuickAdapter(int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(BaseViewHolder helper, Object item) {
            Handicap cqsscAwardResultBean = (Handicap) item;
            String[] nums = cqsscAwardResultBean.getOpenCode().split(",");
            String openCode = cqsscAwardResultBean.getOpenCode().replace(",", " ");
            helper.setText(R.id.tv_no, getString(R.string.which_periods_, cqsscAwardResultBean.getExpect()));
            helper.setText(R.id.tv_award_results, openCode);
            helper.itemView.findViewById(R.id.tv_status).setVisibility(View.VISIBLE);
            helper.setText(R.id.tv_status, Cqxync_DataUtils.bigAndDorL(nums));
            if (helper.getAdapterPosition() % 2 == 0) {
                helper.itemView.setBackgroundColor(getResources().getColor(R.color.custom_blue_s));
            } else {
                helper.itemView.setBackgroundColor(getResources().getColor(R.color.custom_blue_light1));
            }
        }
    }


    private void runAnimation() {
        mIvRedResult1.setImageResource(R.drawable.anim_blue_ball_result);
        mIvRedResult2.setImageResource(R.drawable.anim_blue_ball_result);
        mIvRedResult3.setImageResource(R.drawable.anim_blue_ball_result);
        mIvRedResult4.setImageResource(R.drawable.anim_blue_ball_result);
        mIvRedResult5.setImageResource(R.drawable.anim_blue_ball_result);
        mIvRedResult6.setImageResource(R.drawable.anim_blue_ball_result);
        mIvRedResult7.setImageResource(R.drawable.anim_blue_ball_result);
        mIvRedResult8.setImageResource(R.drawable.anim_blue_ball_result);

        ((AnimationDrawable) mIvRedResult1.getDrawable()).start();
        ((AnimationDrawable) mIvRedResult2.getDrawable()).start();
        ((AnimationDrawable) mIvRedResult3.getDrawable()).start();
        ((AnimationDrawable) mIvRedResult4.getDrawable()).start();
        ((AnimationDrawable) mIvRedResult5.getDrawable()).start();
        ((AnimationDrawable) mIvRedResult6.getDrawable()).start();
        ((AnimationDrawable) mIvRedResult7.getDrawable()).start();
        ((AnimationDrawable) mIvRedResult8.getDrawable()).start();
    }


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
