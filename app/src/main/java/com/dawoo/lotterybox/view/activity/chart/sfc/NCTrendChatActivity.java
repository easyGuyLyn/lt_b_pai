package com.dawoo.lotterybox.view.activity.chart.sfc;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dawoo.lotterybox.ConstantValue;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.bean.lottery.Handicap;
import com.dawoo.lotterybox.util.lottery.LotteryUtil;
import com.dawoo.lotterybox.view.activity.chart.BaseChartActivity;
import com.dawoo.lotterybox.view.activity.chart.ChartDataBean;
import com.dawoo.lotterybox.view.activity.chart.pk10.Pk10EventOB;
import com.dawoo.lotterybox.view.activity.chart.ssc.SSCStatistics;
import com.dawoo.lotterybox.view.fragment.BaseFragment;
import com.hwangjr.rxbus.RxBus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;

/**
 * Created by rain on 18-4-26.
 */

public class NCTrendChatActivity extends BaseChartActivity {

    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.tv_play_method)
    TextView mPlayName;
    @BindView(R.id.iv_go_back)
    ImageView mIvGoBack;

    private List<BaseFragment> fragments = new ArrayList<>();
    private List<ArrayList<ChartDataBean>> mData;
    private List<ArrayList<Integer>> mLinesData;
    private List<String> titles = new ArrayList<>();//tab项
    private ChartViewPagerAdapter chartViewPagerAdapter;

    @Override
    protected void createLayoutView() {
        setContentView(R.layout.activity_chat_cqxync);
    }

    @Override
    protected void initViews() {

        mIvGoBack.setOnClickListener(v -> finish());

    }


    @Override
    protected void initData() {
        super.initData();
        if (mLotteryCode == null) {
            finish();
            return;
        }
        mPlayName.setText(LotteryUtil.getLotteryNameByCode(mLotteryCode));
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mRencentList == null) {
                    mRencentList = getIntent().getParcelableArrayListExtra(ConstantValue.RENCT_DATA);
                    Collections.sort(mRencentList);
                }
                titles.add("开奖");
                titles.add("第一名");
                titles.add("第二名");
                titles.add("第三名");
                titles.add("第四名");
                titles.add("第五名");
                titles.add("第六名");
                titles.add("第七名");
                titles.add("第八名");
                initTab();
                setData();
                initFragment();
                chartViewPagerAdapter = new ChartViewPagerAdapter(getSupportFragmentManager(), fragments, titles);
                mViewPager.setAdapter(chartViewPagerAdapter);
                mTabLayout.setupWithViewPager(mViewPager);
                mProgressDialog.dismiss();
            }
        }, 50);
    }

    @Override
    public void refreshViews() {
        super.refreshViews();
        setData();
        RxBus.get().post(ConstantValue.EVENT_TYPE_OPENING_LOTTERY, "opened");
    }

    public SfcEventOB getSfcEventOB() {
        return new SfcEventOB(mData, mLinesData);
    }

    /**
     * 初始化 tab ui
     */
    private void initTab() {
        mTabLayout.removeAllTabs();
        mViewPager.removeAllViews();

        for (int i = 0; i < titles.size(); i++) {
            if (i == 0) {
                mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(i)), true);
            } else {
                mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(i)), false);
            }
        }
    }

    private void initFragment() {
        fragments.clear();
        fragments.add(OpenSFCChatFragment.newInstance(mRencentList));
        for (int i = 0; i < titles.size()-1; i++) {
            fragments.add(TrendChatNCFragment.newInstance(mData.get(i), mLinesData.get(i)));
        }
    }

    private void setData() {
        mData = new ArrayList<>();
        mLinesData = new ArrayList<>();
        mData.clear();
        mLinesData.clear();

        for (int h = 0; h < 8; h++) {
            ArrayList<ChartDataBean> data1 = new ArrayList<>();
            mData.add(data1);
            ArrayList<Integer> lines = new ArrayList<>();
            mLinesData.add(lines);
        }

        for (int i = 0; i < mRencentList.size(); i++) {
            Handicap handicap = mRencentList.get(i);
            String openCode = handicap.getOpenCode();
            String[] numbers = openCode.split(",");
            for (int j = 0; j < numbers.length; j++) {
                int number = Integer.valueOf(numbers[j]);
                List<Integer> linesBeans = mLinesData.get(j);
                linesBeans.add(number - 1);
                List<ChartDataBean> chartDataBeans = mData.get(j);
                ChartDataBean chartDataBean = new ChartDataBean();
                chartDataBean.setExpect(handicap.getExpect().substring(6));
                chartDataBean.setItemType(ChartDataBean.ITEM_TYPE_CHART);

                List<Integer> rowList = new ArrayList<>();
                if (i == 0) {
                    for (int k = 0; k < 22; k++) {
                        rowList.add(0);
                    }
                } else {
                    rowList.addAll(mData.get(j).get(i - 1).getRowList());    //赋值为上一条数据参数，用于叠加计算
                }

                for (int k = 0; k < 20; k++) {
                    if (k == number - 1) {
                        if (rowList.get(k) < 1000)
                            rowList.set(k, number + 1000);
                    } else {
                        int rowNum = rowList.get(k);
                        if (rowNum < 1000) {
                            rowList.set(k, rowList.get(k) + 1);
                        } else {
                            rowList.set(k, 1);
                        }
                    }

                }
                //大小单双
                if (number > 10) {
                    rowList.set(20, number);
                } else {
                    rowList.set(20, number + 1000);
                }

                if (number % 2 == 1) {
                    rowList.set(21, number);
                } else {
                    rowList.set(21, number + 1000);
                }
                chartDataBean.setRowList(rowList);
                chartDataBeans.add(chartDataBean);
            }
        }
    }

    @Override
    public void onRecentCloseExpect(Handicap handicap) {

    }


    //viewPager适配器
    private class ChartViewPagerAdapter extends FragmentPagerAdapter {
        List<BaseFragment> fragments;
        List<String> titles;

        public ChartViewPagerAdapter(FragmentManager fm, List<BaseFragment> fragments, List<String> titles) {
            super(fm);
            this.fragments = fragments;
            this.titles = titles;
        }


        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //  super.destroyItem(container, position, object);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }

}
