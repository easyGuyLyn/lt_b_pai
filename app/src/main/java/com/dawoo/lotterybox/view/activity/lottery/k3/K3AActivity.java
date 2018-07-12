package com.dawoo.lotterybox.view.activity.lottery.k3;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dawoo.lotterybox.ConstantValue;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.bean.OrderEventBean;
import com.dawoo.lotterybox.bean.lottery.Handicap;
import com.dawoo.lotterybox.bean.playtype.PlayTypeBean;
import com.dawoo.lotterybox.util.AssetsReader;
import com.dawoo.lotterybox.util.lottery.K3AComputeUtil;
import com.dawoo.lotterybox.view.activity.lottery.BaseLotteryAActivity;
import com.dawoo.lotterybox.view.view.CountDownTimerUtils;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;

import java.util.List;

import butterknife.BindView;

/**
 * 江苏k3 A盘
 * Created by b on 18-2-9.
 */

public class K3AActivity extends BaseLotteryAActivity {

    @BindView(R.id.iv_red_result_1)
    ImageView mIvRedResult1;
    @BindView(R.id.iv_red_result_2)
    ImageView mIvRedResult2;
    @BindView(R.id.iv_red_result_3)
    ImageView mIvRedResult3;

    private AwardResultsQuickAdapter mQuickAdapter;
    private K3AComputeUtil mK3AComputeUtil;

    @Override
    protected void createLayoutView() {
        setContentView(R.layout.activity_k3_a);
        RxBus.get().register(this);
    }

    @Override
    protected void initViews() {
        super.initViews();
        mQuickAdapter = new AwardResultsQuickAdapter(R.layout.item_jsk3_b_lottery_result);
        mQuickAdapter.setEmptyView(LayoutInflater.from(this).inflate(R.layout.empty_view, null));
        View headView = getLayoutInflater().inflate(R.layout.layout_k3_bottom_first_head, (ViewGroup) mRlvLotteryRecord.getParent(), false);
        View footerView = getLayoutInflater().inflate(R.layout.layout_ssc_lottery_record_footer, (ViewGroup) mRlvLotteryRecord.getParent(), false);
        mQuickAdapter.addHeaderView(headView);
        mQuickAdapter.addFooterView(footerView);
        mRlvLotteryRecord.setLayoutManager(new LinearLayoutManager(this));
        mRlvLotteryRecord.setAdapter(mQuickAdapter);
        dataFile = AssetsReader.JSK3_A_JSON_FILE;
    }


    @Override
    protected void initData() {
        super.initData();
        setLeftTimeFinishDelegate(mLeftTimeFinishDelegate);
        setLeftOpenTimeFinishDelegate(mLeftOpenTimeFinishDelegate);
        mK3AComputeUtil = new K3AComputeUtil();
        mK3AComputeUtil.setPlayTypeBean(mPlayTypeBean);
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
        mIvRedResult1.setImageResource(R.drawable.anim_fast_result);
        mIvRedResult2.setImageResource(R.drawable.anim_fast_result);
        mIvRedResult3.setImageResource(R.drawable.anim_fast_result);
        ((AnimationDrawable) mIvRedResult1.getDrawable()).start();
        ((AnimationDrawable) mIvRedResult2.getDrawable()).start();
        ((AnimationDrawable) mIvRedResult3.getDrawable()).start();
//        countDownTimer.start();
    }

    private void waitOpenLottery(){
        mIvRedResult1.setImageResource(R.mipmap.fast3_ask);
        mIvRedResult2.setImageResource(R.mipmap.fast3_ask);
        mIvRedResult3.setImageResource(R.mipmap.fast3_ask);
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
        mK3AComputeUtil.setPlayTypeBean(playBean);
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
        mK3AComputeUtil.setData(handicapList);
    }

    @Override
    public void openHotColdOrOmit(int type) {
        super.openHotColdOrOmit(type);
        if (type == 0){
            mK3AComputeUtil.compute(true);
        }else if (type == 1){
            mK3AComputeUtil.compute(false);
        }else{
            for (PlayTypeBean.PlayBean.LayoutBean layoutBean:mPlayTypeBean.getLayoutBeans()){
                for (PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean childLayoutBean:layoutBean.getChildLayoutBeans()){
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

    class AwardResultsQuickAdapter extends BaseQuickAdapter {
        public AwardResultsQuickAdapter(int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(BaseViewHolder helper, Object item) {
            Handicap cqsscAwardResultBean = (Handicap) item;
            String[] nums = cqsscAwardResultBean.getOpenCode().split(",");
            String openCode = cqsscAwardResultBean.getOpenCode().replace(",", "");
            helper.setText(R.id.tv_no, getString(R.string.which_periods_, cqsscAwardResultBean.getExpect()));
            helper.setText(R.id.tv_num, openCode);
            helper.setImageResource(R.id.img_1, getImageResourceFormNumber(nums[0]));
            helper.setImageResource(R.id.img_2, getImageResourceFormNumber(nums[1]));
            helper.setImageResource(R.id.img_3, getImageResourceFormNumber(nums[2]));
            int addNums = Integer.valueOf(nums[0]) + Integer.valueOf(nums[1]) + Integer.valueOf(nums[2]);
            helper.setText(R.id.tv_hz, addNums + "");
            helper.setText(R.id.tv_dx, getString(addNums < 11 ? R.string.small : R.string.big));
            helper.setText(R.id.tv_ds, getString((addNums % 2) == 0 ? R.string.double_ : R.string.single));

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