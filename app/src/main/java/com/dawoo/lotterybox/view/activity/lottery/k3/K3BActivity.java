package com.dawoo.lotterybox.view.activity.lottery.k3;

import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.widget.ImageView;

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
import com.dawoo.lotterybox.view.fragment.k3_b.K3_CP_Fragment;
import com.dawoo.lotterybox.view.fragment.k3_b.K3_DP_Fragment;
import com.dawoo.lotterybox.view.fragment.k3_b.K3_DS_Fragment;
import com.dawoo.lotterybox.view.fragment.k3_b.K3_SJ_Fragment;
import com.dawoo.lotterybox.view.fragment.k3_b.K3_WS_QS_Fragment;
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

/**
 * Created by archar on 18-3-26.
 */

public class K3BActivity extends BaseLotteryBActivity implements LotteryBView, BaseLotteryBActivity.OnTheNextToDoListener {

    @BindView(R.id.iv_red_result_1)
    ImageView mIvRedResult1;
    @BindView(R.id.iv_red_result_2)
    ImageView mIvRedResult2;
    @BindView(R.id.iv_red_result_3)
    ImageView mIvRedResult3;

    @Override
    protected void createLayoutView() {
        super.createLayoutView();
        setContentView(R.layout.activity_jsk3_b);
        RxBus.get().register(this);
        // 当前彩种代码
        mLotteryCode = getIntent().getStringExtra(LT_CODE);
        mLotteryName = getIntent().getStringExtra(LT_NAME);
        mHistoryAdapter = new AwardResultsQuickAdapter(R.layout.item_jsk3_b_lottery_result);
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
        LotteryBFragmentManager.getInstance().switchFragment(mFragmentManager, K3_DS_Fragment.class);
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
        mIvRedResult1.setImageResource(R.drawable.anim_fast_result);
        mIvRedResult2.setImageResource(R.drawable.anim_fast_result);
        mIvRedResult3.setImageResource(R.drawable.anim_fast_result);
        ((AnimationDrawable) mIvRedResult1.getDrawable()).start();
        ((AnimationDrawable) mIvRedResult2.getDrawable()).start();
        ((AnimationDrawable) mIvRedResult3.getDrawable()).start();
    }

    @Override
    public void onNextShowFragment(PlayChooseBean playChooseBean) {
        String name = playChooseBean.getBetName();
        if (name.equals(mPlayChooseBeans.get(0).getBetName())) {
            LotteryBFragmentManager.getInstance().switchFragment(mFragmentManager, K3_DS_Fragment.class, mLl_anim_dog);
        } else if (name.equals(mPlayChooseBeans.get(1).getBetName())) {
            LotteryBFragmentManager.getInstance().switchFragment(mFragmentManager, K3_SJ_Fragment.class, mLl_anim_dog);
        } else if (name.equals(mPlayChooseBeans.get(2).getBetName())) {
            LotteryBFragmentManager.getInstance().switchFragment(mFragmentManager, K3_WS_QS_Fragment.class, mLl_anim_dog);
        } else if (name.equals(mPlayChooseBeans.get(3).getBetName())) {
            LotteryBFragmentManager.getInstance().switchFragment(mFragmentManager, K3_CP_Fragment.class, mLl_anim_dog);
        } else if (name.equals(mPlayChooseBeans.get(4).getBetName())) {
            LotteryBFragmentManager.getInstance().switchFragment(mFragmentManager, K3_DP_Fragment.class, mLl_anim_dog);
        }
    }

    public void setResultToBall(String string) {
        String[] numbers = string.split(",");
        mIvRedResult1.setImageResource(getImageResourceFormNumber(numbers[0]));
        mIvRedResult2.setImageResource(getImageResourceFormNumber(numbers[1]));
        mIvRedResult3.setImageResource(getImageResourceFormNumber(numbers[2]));

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
            if (cqsscAwardResultBean.getExpect().length() > 6) {
                helper.setText(R.id.tv_no, getString(R.string.which_periods_, cqsscAwardResultBean.getExpect().substring(4)));
            } else {
                helper.setText(R.id.tv_no, getString(R.string.which_periods_, cqsscAwardResultBean.getExpect()));
            }

            helper.setText(R.id.tv_num, openCode);
            helper.setImageResource(R.id.img_1, getImageResourceFormNumber(nums[0]));
            helper.setImageResource(R.id.img_2, getImageResourceFormNumber(nums[1]));
            helper.setImageResource(R.id.img_3, getImageResourceFormNumber(nums[2]));
            int addNums = Integer.valueOf(nums[0]) + Integer.valueOf(nums[1]) + Integer.valueOf(nums[2]);
            helper.setText(R.id.tv_hz, addNums + "");
            helper.setText(R.id.tv_dx, getString(addNums < 11 ? R.string.small : R.string.big));
            helper.setText(R.id.tv_ds, getString((addNums % 2) == 0 ? R.string.double_ : R.string.single));

            if (helper.getAdapterPosition() % 2 == 0) {
                helper.itemView.setBackgroundColor(getResources().getColor(R.color.custom_blue_s));
            } else {
                helper.itemView.setBackgroundColor(getResources().getColor(R.color.custom_blue_light1));
            }
        }
    }


    public int getImageResourceFormNumber(String number) {
        int imageResource = 0;
        if ("1".equals(number))
            imageResource = R.mipmap.fast1;
        else if ("2".equals(number))
            imageResource = R.mipmap.fast2;
        else if ("3".equals(number))
            imageResource = R.mipmap.fast3;
        else if ("4".equals(number))
            imageResource = R.mipmap.fast4;
        else if ("5".equals(number))
            imageResource = R.mipmap.fast5;
        else if ("6".equals(number))
            imageResource = R.mipmap.fast6;
        return imageResource;
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
