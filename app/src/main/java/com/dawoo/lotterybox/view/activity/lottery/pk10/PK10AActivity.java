package com.dawoo.lotterybox.view.activity.lottery.pk10;

import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dawoo.lotterybox.ConstantValue;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.bean.OrderEventBean;
import com.dawoo.lotterybox.bean.lottery.Handicap;
import com.dawoo.lotterybox.bean.playtype.PlayTypeBean;
import com.dawoo.lotterybox.util.AssetsReader;
import com.dawoo.lotterybox.util.lottery.PK10AComputeUtil;
import com.dawoo.lotterybox.view.activity.lottery.BaseLotteryAActivity;
import com.dawoo.lotterybox.view.view.CountDownTimerUtils;
import com.dawoo.lotterybox.view.view.sortcar.SortCarSurfaceView;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 北京PK10 A盘
 * Created by b on 18-2-9.
 */

public class PK10AActivity extends BaseLotteryAActivity {

    @BindView(R.id.carView)
    SortCarSurfaceView mSortCarSurfaceView;

    private AwardResultsQuickAdapter mQuickAdapter;
    private PK10AComputeUtil mPk10AComputeUtil;

    @Override
    protected void createLayoutView() {
        setContentView(R.layout.activity_pk10_a);
        RxBus.get().register(this);
    }

    @Override
    protected void initViews() {
        super.initViews();
        mQuickAdapter = new AwardResultsQuickAdapter(R.layout.item_pk10_lottery_result);
        mQuickAdapter.setEmptyView(LayoutInflater.from(this).inflate(R.layout.empty_view, null));
        View headView = getLayoutInflater().inflate(R.layout.layout_pk10_bottom_first_head, (ViewGroup) mRlvLotteryRecord.getParent(), false);
        View footerView = getLayoutInflater().inflate(R.layout.layout_ssc_lottery_record_footer, (ViewGroup) mRlvLotteryRecord.getParent(), false);
        mQuickAdapter.addHeaderView(headView);
        mQuickAdapter.addFooterView(footerView);
        mRlvLotteryRecord.setLayoutManager(new LinearLayoutManager(this));
        mRlvLotteryRecord.setAdapter(mQuickAdapter);
        dataFile = AssetsReader.PK10_A_JSON_FILE;
    }

    @Override
    protected void initData() {
        super.initData();
        setLeftTimeFinishDelegate(mLeftTimeFinishDelegate);
        setLeftOpenTimeFinishDelegate(mLeftOpenTimeFinishDelegate);
        mPk10AComputeUtil = new PK10AComputeUtil();
        mPk10AComputeUtil.setPlayTypeBean(mPlayTypeBean);
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

    @Override
    protected void changePlayBean(PlayTypeBean.PlayBean playBean) {
        super.changePlayBean(playBean);
        mPk10AComputeUtil.setPlayTypeBean(playBean);
    }

    protected void openLottery() {
        super.openLottery();
        mSortCarSurfaceView.setRandPlay();
//        countDownTimer.start();
    }

    //距离封盘时间 倒计时结束回调
    CountDownTimerUtils.FinishDelegate mLeftTimeFinishDelegate = new CountDownTimerUtils.FinishDelegate() {
        @Override
        public void onFinish() {
            mTimeTextView.setText("00:00");
            mSortCarSurfaceView.setFenDanUI();
            mLotteryPresenter.getLotteryExpect();
            mLotteryPresenter.getRecentCloseExpect();
        }
    };

    @Override
    protected void onOpenLottery() {
        super.onOpenLottery();
        openLottery();
    }

    @Override
    protected void onResultByCodeChild(List<Handicap> handicapList) {
        super.onResultByCodeChild(handicapList);
        mQuickAdapter.setNewData(handicapList);
        mPk10AComputeUtil.setData(handicapList);
    }

    @Override
    public void openHotColdOrOmit(int type) {
        super.openHotColdOrOmit(type);
        if (type == 0) {
            mPk10AComputeUtil.compute(true);
        } else if (type == 1) {
            mPk10AComputeUtil.compute(false);
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
        ArrayList<Integer> nums = new ArrayList<>();
        for (int i = 0; i < numbers.length; i++) {
            nums.add(Integer.valueOf(numbers[i]));
        }
        mSortCarSurfaceView.setResultAndPlay(nums);

    }


    class AwardResultsQuickAdapter extends BaseQuickAdapter {
        public AwardResultsQuickAdapter(int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(BaseViewHolder helper, Object item) {
            Handicap cqsscAwardResultBean = (Handicap) item;
            String[] nums = cqsscAwardResultBean.getOpenCode().split(",");
            helper.setText(R.id.tv_no, getString(R.string.which_periods_, cqsscAwardResultBean.getExpect()));
            helper.setText(R.id.tv_award_results, cqsscAwardResultBean.getOpenCode());
            StringBuffer strNum = new StringBuffer();
            int num = Integer.valueOf(nums[0]) + Integer.valueOf(nums[1]);
            strNum.append(num + ",");
            strNum.append((num % 2) == 0 ? getString(R.string.double_) + "," : getString(R.string.single) + ",");
            strNum.append(num < 12 ? getString(R.string.small) + "," : getString(R.string.big) + ",");
            strNum.append(Integer.valueOf(nums[0]) > Integer.valueOf(nums[9]) ? getString(R.string.dragon) + "," : getString(R.string.tiger) + ",");
            strNum.append(Integer.valueOf(nums[1]) > Integer.valueOf(nums[8]) ? getString(R.string.dragon) + "," : getString(R.string.tiger) + ",");
            strNum.append(Integer.valueOf(nums[2]) > Integer.valueOf(nums[7]) ? getString(R.string.dragon) + "," : getString(R.string.tiger) + ",");
            strNum.append(Integer.valueOf(nums[3]) > Integer.valueOf(nums[6]) ? getString(R.string.dragon) + "," : getString(R.string.tiger) + ",");
            strNum.append(Integer.valueOf(nums[4]) > Integer.valueOf(nums[5]) ? getString(R.string.dragon) : getString(R.string.tiger));
            helper.setText(R.id.tv_status, strNum);
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