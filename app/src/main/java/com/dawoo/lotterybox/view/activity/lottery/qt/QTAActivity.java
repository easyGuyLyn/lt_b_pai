package com.dawoo.lotterybox.view.activity.lottery.qt;

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
import com.dawoo.coretool.util.date.DateTool;
import com.dawoo.lotterybox.ConstantValue;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.bean.OrderEventBean;
import com.dawoo.lotterybox.bean.lottery.Handicap;
import com.dawoo.lotterybox.bean.playtype.PlayTypeBean;
import com.dawoo.lotterybox.util.AssetsReader;
import com.dawoo.lotterybox.util.lottery.QTAComputeUtil;
import com.dawoo.lotterybox.view.activity.lottery.BaseLotteryAActivity;
import com.dawoo.lotterybox.view.view.CountDownTimerUtils;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;

/**
 * qt彩票A盘
 * 福彩3D 体彩排列三
 */
public class QTAActivity extends BaseLotteryAActivity{

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
    private QTAComputeUtil mQtaComputeUtil;

    @Override
    protected void createLayoutView() {
        setContentView(R.layout.activity_qt);
        RxBus.get().register(this);
    }

    @Override
    protected void initViews() {
        super.initViews();
        mIvRedResult4.setVisibility(View.GONE);
        mIvRedResult5.setVisibility(View.GONE);
        mQuickAdapter = new AwardResultsQuickAdapter(R.layout.item_ssc_lottery_result);
        mQuickAdapter.setEmptyView(LayoutInflater.from(this).inflate(R.layout.empty_view, null));
        View headView = getLayoutInflater().inflate(R.layout.layout_ssc_lottery_record_head, (ViewGroup) mRlvLotteryRecord.getParent(), false);
        View footerView = getLayoutInflater().inflate(R.layout.layout_ssc_lottery_record_footer, (ViewGroup) mRlvLotteryRecord.getParent(), false);
        mQuickAdapter.addHeaderView(headView);
        mQuickAdapter.addFooterView(footerView);
        mRlvLotteryRecord.setLayoutManager(new LinearLayoutManager(this));
        mRlvLotteryRecord.setAdapter(mQuickAdapter);
        dataFile = AssetsReader.QT_A_JSON_FILE;
    }

    @Override
    protected void initData() {
        super.initData();
        setTickDelegate(mTickDelegate);
        setLeftTimeFinishDelegate(mLeftTimeFinishDelegate);
        setLeftOpenTimeFinishDelegate(mLeftOpenTimeFinishDelegate);
        mQtaComputeUtil = new QTAComputeUtil();
        mQtaComputeUtil.setPlayTypeBean(mPlayTypeBean);
    }

    //倒计时期间回调/覆盖父类的分秒制
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
            mTimeTextView.setText("00:00:00");
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
        ((AnimationDrawable) mIvRedResult1.getDrawable()).start();
        ((AnimationDrawable) mIvRedResult2.getDrawable()).start();
        ((AnimationDrawable) mIvRedResult3.getDrawable()).start();
//        countDownTimer.start();
    }

    private void waitOpenLottery(){
        mIvRedResult1.setImageResource(R.mipmap.red_ball_result_);
        mIvRedResult2.setImageResource(R.mipmap.red_ball_result_);
        mIvRedResult3.setImageResource(R.mipmap.red_ball_result_);
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
        mQtaComputeUtil.setPlayTypeBean(playBean);
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
        mQtaComputeUtil.setData(handicapList);
    }

    @Override
    public void openHotColdOrOmit(int type) {
        super.openHotColdOrOmit(type);
        if (type == 0){
            mQtaComputeUtil.compute(true);
        }else if (type == 1){
            mQtaComputeUtil.compute(false);
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
//        mIvRedResult4.setImageResource(getImageResourceFormNumber(numbers[3]));
//        mIvRedResult5.setImageResource(getImageResourceFormNumber(numbers[4]));

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

    /**
     * 下层列表120期彩票开奖历史数据适配器
     */
    class AwardResultsQuickAdapter extends BaseQuickAdapter {
        public AwardResultsQuickAdapter(int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(BaseViewHolder helper, Object item) {
            Handicap awardResultBean = (Handicap) item;
            String openCode = awardResultBean.getOpenCode().replace(",", "");
            SpannableString ssb = new SpannableString(openCode);
            ssb.setSpan(new ForegroundColorSpan(ContextCompat.getColor(QTAActivity.this, R.color.colorPrimary)), 0, 3, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
            helper.setText(R.id.tv_no, getString(R.string.which_periods_, awardResultBean.getExpect()));
            helper.setText(R.id.tv_award_results, ssb);
            String[] numbers = awardResultBean.getOpenCode().split(",");
            if (numbers.length == 3) {   //接口没做好，可能会有奇怪的结果
                for (int i = 1; i < 3; i++) {
                    StringBuffer numStatus = new StringBuffer();
                    int num = Integer.valueOf(numbers[i]);
                    if (num < 5)
                        numStatus.append(getString(R.string.small));
                    else numStatus.append(getString(R.string.big));
                    if ((num % 2) == 0)
                        numStatus.append(getString(R.string.double_));
                    else numStatus.append(getString(R.string.single));
                    if (i == 1)
                        helper.setText(R.id.tv_ten, numStatus);
                    else if (i == 2)
                        helper.setText(R.id.tv_a_bit, numStatus);
                }

                if (Integer.valueOf(numbers[0]) == Integer.valueOf(numbers[1]) ||
                        Integer.valueOf(numbers[0]) == Integer.valueOf(numbers[2]) ||
                        Integer.valueOf(numbers[1]) == Integer.valueOf(numbers[2]))
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
