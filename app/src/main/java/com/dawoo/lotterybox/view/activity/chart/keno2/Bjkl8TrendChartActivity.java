package com.dawoo.lotterybox.view.activity.chart.keno2;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dawoo.lotterybox.ConstantValue;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.bean.lottery.Handicap;
import com.dawoo.lotterybox.util.lottery.LotteryUtil;
import com.dawoo.lotterybox.view.activity.chart.BaseChartActivity;
import com.dawoo.lotterybox.view.view.HeaderView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;

import static com.dawoo.lotterybox.view.fragment.keno.XYBDataUtils.getBjkl8RewardStatus;

/**
 * Created by archar on 18-4-23.
 */

public class Bjkl8TrendChartActivity extends BaseChartActivity {

    @BindView(R.id.head_view)
    HeaderView mHeadView;
    @BindView(R.id.rlv_reward_data)
    RecyclerView mRlvRewardData;

    private AwardAdapter mAwardAdapter;

    @Override
    protected void createLayoutView() {
        setContentView(R.layout.layout_bjkl8_chart);
    }

    @Override
    protected void initViews() {
        mRlvRewardData.setLayoutManager(new LinearLayoutManager(this));
        mAwardAdapter = new AwardAdapter(R.layout.layout_bjkl8_chart_item);
        mRlvRewardData.setAdapter(mAwardAdapter);

    }

    @Override
    protected void initData() {
        super.initData();
        mHeadView.setHeader(LotteryUtil.getLotteryNameByCode(mLotteryCode), true);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mRencentList == null) {
                    mRencentList = getIntent().getParcelableArrayListExtra(ConstantValue.RENCT_DATA);
                }
                Collections.sort(mRencentList);
                if (mRencentList != null) {
                    mAwardAdapter.setNewData(mRencentList);
                }
                mProgressDialog.dismiss();
            }
        }, 50);
    }

    public void refreshViews() {
        super.refreshViews();
        mAwardAdapter.setNewData(mRencentList);
    }


    private class AwardAdapter extends BaseQuickAdapter {

        public AwardAdapter(int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(BaseViewHolder helper, Object item) {

            Handicap cqsscAwardResultBean = (Handicap) item;
            String openCode = cqsscAwardResultBean.getOpenCode();
            helper.setText(R.id.tv_no, getString(R.string.which_periods, cqsscAwardResultBean.getExpect()));
            helper.setText(R.id.tv_award_results, cqsscAwardResultBean.getOpenCode().replace(",", " "));
            getBjkl8RewardStatus(openCode,
                    helper.getView(R.id.tv_hz),
                    helper.getView(R.id.tv_dx),
                    helper.getView(R.id.tv_ds),
                    helper.getView(R.id.tv_wx));

            if (helper.getAdapterPosition() % 2 == 0) {
                helper.itemView.setBackgroundColor(getResources().getColor(R.color.custom_blue_s));
            } else {
                helper.itemView.setBackgroundColor(getResources().getColor(R.color.custom_blue_light1));
            }

        }
    }
}
