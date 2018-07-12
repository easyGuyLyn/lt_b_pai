package com.dawoo.lotterybox.view.activity.lottery.ssc;

import android.graphics.drawable.AnimationDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dawoo.lotterybox.ConstantValue;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.bean.DataCenter;
import com.dawoo.lotterybox.bean.OrderEventBean;
import com.dawoo.lotterybox.bean.lottery.Handicap;
import com.dawoo.lotterybox.bean.playtype.PlayTypeBean;
import com.dawoo.lotterybox.util.AssetsReader;
import com.dawoo.lotterybox.util.lottery.SSCAComputeUtil;
import com.dawoo.lotterybox.view.activity.lottery.BaseLotteryAActivity;
import com.dawoo.lotterybox.view.view.CountDownTimerUtils;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;

import java.util.List;

import butterknife.BindView;


/**
 * 时时彩A盘
 * Created by b on 18-2-9.
 */

public class SSCAActivity extends BaseLotteryAActivity {

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

    private AwardResultsQuickAdapter mQuickAdapter;
    private SSCAComputeUtil mSSCAComputeUtil;

    @Override
    protected void createLayoutView() {
        setContentView(R.layout.activity_base_ssc);
        RxBus.get().register(this);
    }

    @Override
    protected void initViews() {
        super.initViews();
        mQuickAdapter = new AwardResultsQuickAdapter(R.layout.item_ssc_lottery_result);
        mQuickAdapter.setEmptyView(LayoutInflater.from(this).inflate(R.layout.empty_view, null));
        View headView = getLayoutInflater().inflate(R.layout.layout_ssc_lottery_record_head, (ViewGroup) mRlvLotteryRecord.getParent(), false);
        View footerView = getLayoutInflater().inflate(R.layout.layout_ssc_lottery_record_footer, (ViewGroup) mRlvLotteryRecord.getParent(), false);
        mQuickAdapter.addHeaderView(headView);
        mQuickAdapter.addFooterView(footerView);
        mRlvLotteryRecord.setLayoutManager(new LinearLayoutManager(this));
        mRlvLotteryRecord.setAdapter(mQuickAdapter);
        dataFile = AssetsReader.SSC_JSON_FILE;
    }

    @Override
    protected void initData() {
        super.initData();
        setLeftTimeFinishDelegate(mLeftTimeFinishDelegate);
        setLeftOpenTimeFinishDelegate(mLeftOpenTimeFinishDelegate);
        mSSCAComputeUtil = new SSCAComputeUtil();
        mSSCAComputeUtil.setPlayTypeBean(mPlayTypeBean);
    }

    //距离开盘时间 倒计时结束回调
    CountDownTimerUtils.FinishDelegate mLeftOpenTimeFinishDelegate = new CountDownTimerUtils.FinishDelegate() {
        @Override
        public void onFinish() {
            mTimeTextView.setText("00:00");
            openLottery();
            mLotteryPresenter.getLotteryExpect();
            mLotteryPresenter.getRecentCloseExpect();
            lLStopSale.setVisibility(View.GONE);
        }
    };

    protected void openLottery() {
        super.openLottery();
        mIvRedResult1.setImageResource(R.drawable.anim_blue_ball_result);
        mIvRedResult2.setImageResource(R.drawable.anim_blue_ball_result);
        mIvRedResult3.setImageResource(R.drawable.anim_blue_ball_result);
        mIvRedResult4.setImageResource(R.drawable.anim_blue_ball_result);
        mIvRedResult5.setImageResource(R.drawable.anim_blue_ball_result);
        ((AnimationDrawable) mIvRedResult1.getDrawable()).start();
        ((AnimationDrawable) mIvRedResult2.getDrawable()).start();
        ((AnimationDrawable) mIvRedResult3.getDrawable()).start();
        ((AnimationDrawable) mIvRedResult4.getDrawable()).start();
        ((AnimationDrawable) mIvRedResult5.getDrawable()).start();
//        countDownTimer.start();
    }

    private void waitOpenLottery(){
        mIvRedResult1.setImageResource(R.mipmap.red_ball_result_);
        mIvRedResult2.setImageResource(R.mipmap.red_ball_result_);
        mIvRedResult3.setImageResource(R.mipmap.red_ball_result_);
        mIvRedResult4.setImageResource(R.mipmap.red_ball_result_);
        mIvRedResult5.setImageResource(R.mipmap.red_ball_result_);
    }
    //距离封盘时间 倒计时结束回调
    CountDownTimerUtils.FinishDelegate mLeftTimeFinishDelegate = new CountDownTimerUtils.FinishDelegate() {
        @Override
        public void onFinish() {
            mTimeTextView.setText("00:00");
            waitOpenLottery();
            mLotteryPresenter.getLotteryExpect();
            mLotteryPresenter.getRecentCloseExpect();
        }
    };

    @Override
    protected void changePlayBean(PlayTypeBean.PlayBean playBean) {
        super.changePlayBean(playBean);
        mSSCAComputeUtil.setPlayTypeBean(playBean);
    }


    @Override
    protected void onOpenLottery() {
        super.onOpenLottery();
        openLottery();
    }

    @Override
    protected void onResultByCodeChild(List<Handicap> handicapList) {
        super.onResultByCodeChild(handicapList);
        mQuickAdapter.setNewData(handicapList);
        mSSCAComputeUtil.setData(handicapList);
    }



    @Override
    public void openHotColdOrOmit(int type) {
        super.openHotColdOrOmit(type);
        if (type == 0) {
            mSSCAComputeUtil.compute(true);
        } else if (type == 1) {
            mSSCAComputeUtil.compute(false);
        } else {
            for (PlayTypeBean.PlayBean.LayoutBean layoutBean : mPlayTypeBean.getLayoutBeans()) {
                for (PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean childLayoutBean : layoutBean.getChildLayoutBeans()) {
                    childLayoutBean.setNumberRelevant(-1);
                }
            }
        }
        mGamePlayAdapter.notifyDataSetChanged();
    }

    protected void setResultToBall(String string) {
        super.setResultToBall(string);
        String[] numbers = string.split(",");
        mIvRedResult1.setImageResource(getImageResourceFormNumber(numbers[0]));
        mIvRedResult2.setImageResource(getImageResourceFormNumber(numbers[1]));
        mIvRedResult3.setImageResource(getImageResourceFormNumber(numbers[2]));
        mIvRedResult4.setImageResource(getImageResourceFormNumber(numbers[3]));
        mIvRedResult5.setImageResource(getImageResourceFormNumber(numbers[4]));

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

    class AwardResultsQuickAdapter extends BaseQuickAdapter {
        public AwardResultsQuickAdapter(int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(BaseViewHolder helper, Object item) {
            Handicap cqsscAwardResultBean = (Handicap) item;
            String openCode = cqsscAwardResultBean.getOpenCode().replace(",", "");
            SpannableString ssb = new SpannableString(openCode);
            ssb.setSpan(new ForegroundColorSpan(ContextCompat.getColor(SSCAActivity.this, R.color.colorPrimary)), 2, 5, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
            helper.setText(R.id.tv_no, getString(R.string.which_periods_, cqsscAwardResultBean.getExpect()));
            helper.setText(R.id.tv_award_results, ssb);
            String[] numbers = cqsscAwardResultBean.getOpenCode().split(",");
            if (numbers.length == 5) {   //接口没做好，包含有3个号的结果
                for (int i = 3; i < 5; i++) {
                    StringBuffer numStatus = new StringBuffer();
                    int num = Integer.valueOf(numbers[i]);
                    if (num < 5)
                        numStatus.append(getString(R.string.small));
                    else numStatus.append(getString(R.string.big));
                    if ((num % 2) == 0)
                        numStatus.append(getString(R.string.double_));
                    else numStatus.append(getString(R.string.single));
                    if (i == 3)
                        helper.setText(R.id.tv_ten, numStatus);
                    else if (i == 4)
                        helper.setText(R.id.tv_a_bit, numStatus);
                }

                if (Integer.valueOf(numbers[2]) == Integer.valueOf(numbers[3]) ||
                        Integer.valueOf(numbers[2]) == Integer.valueOf(numbers[4]) ||
                        Integer.valueOf(numbers[3]) == Integer.valueOf(numbers[4]))
                    helper.setText(R.id.tv_mantissa, getString(R.string.zusan));
                else helper.setText(R.id.tv_mantissa, getString(R.string.zuliu));

            }
        }
    }


    @Subscribe(tags = {@Tag(ConstantValue.EVENT_LOTTERY_CART_DATA_CHANGE)})
    public void orderDataChange(OrderEventBean orderEventBean) {
        super.orderDataChange(orderEventBean);
    }

    /**
     * 加载帐户数据后回调
     */
    @Subscribe(tags = {@Tag(ConstantValue.EVENT_TYPE_USER_INFO)})
    public void onUserInfoCallBack(String s) {
        // 当加载用户相关信息后显示用户信息
        mGamePlayAdapter.notifyItemChanged(0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.get().unregister(this);
    }
}
