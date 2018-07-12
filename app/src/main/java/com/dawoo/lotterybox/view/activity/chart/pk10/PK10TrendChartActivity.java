package com.dawoo.lotterybox.view.activity.chart.pk10;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.TextView;

import com.dawoo.lotterybox.ConstantValue;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.bean.lottery.Handicap;
import com.dawoo.lotterybox.util.lottery.LotteryUtil;
import com.dawoo.lotterybox.view.activity.BaseActivity;
import com.dawoo.lotterybox.view.activity.chart.BaseChartActivity;
import com.dawoo.lotterybox.view.activity.chart.ChartDataBean;
import com.dawoo.lotterybox.view.fragment.BaseFragment;
import com.hwangjr.rxbus.RxBus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;

import static com.dawoo.lotterybox.ConstantValue.RENCT_DATA;

/**
 * Created by b on 18-4-4.
 * pk10折线图
 */

public class PK10TrendChartActivity extends BaseChartActivity {

    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    @BindView(R.id.iv_go_back)
    ImageView mIvGoBack;
    @BindView(R.id.tv_play_method)
    TextView mHeadView;
    @BindView(R.id.iv_right_menu)
    ImageView mIvRightMenu;

    List<BaseFragment> fragments = new ArrayList<>();
    private List<ArrayList<ChartDataBean>> mData;
    private List<ArrayList<LinesBean>> mLinesData;


    @Override
    protected void createLayoutView() {
        setContentView(R.layout.activity_pk10_trend_chart);
    }

    @Override
    protected void initViews() {
        mIvGoBack.setOnClickListener(v -> finish());
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mCurrentIndex = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    @Override
    protected void initData() {
        super.initData();
        refresh(mCurrentIndex);

    }

    private void refresh(int currentIndex) {
        mHeadView.setText(LotteryUtil.getLotteryNameByCode(mLotteryCode));
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mRencentList == null) {
                    mRencentList = getIntent().getParcelableArrayListExtra(ConstantValue.RENCT_DATA);
                    Collections.sort(mRencentList);
                }
                mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
                mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
                List<String> titles = new ArrayList<>();
                titles.add("开奖");
                titles.add("冠军");
                titles.add("亚军");
                titles.add("季军");
                titles.add("第四名");
                titles.add("第五名");
                titles.add("第六名");
                titles.add("第七名");
                titles.add("第八名");
                titles.add("第九名");
                titles.add("第十名");
                setData();
                initFragment();
                mViewPager.setOffscreenPageLimit(10);
                ChartViewPagerAdapter chartViewPagerAdapter = new ChartViewPagerAdapter(getSupportFragmentManager(), fragments, titles);
                mViewPager.setAdapter(chartViewPagerAdapter);
                mTabLayout.setupWithViewPager(mViewPager);
                mViewPager.setCurrentItem(mCurrentIndex);
                mProgressDialog.dismiss();
            }
        }, 50);
    }

    @Override
    public void refreshViews() {
        super.refreshViews();
        setData();
        RxBus.get().post(ConstantValue.EVENT_TYPE_OPENING_LOTTERY, "opened");
        // refresh(mCurrentIndex);
    }


    public Pk10EventOB getPK10EventOB() {
        return new Pk10EventOB(mData, mLinesData);
    }

    private void initFragment() {
        fragments.clear();
        fragments.add(PK10OpenCodeFragment.newInstance(mRencentList));
        fragments.add(TrendChartPK10Fragment.newInstance(0, mData.get(0), mLinesData.get(0)));
        fragments.add(TrendChartPK10Fragment.newInstance(1, mData.get(1), mLinesData.get(1)));
        fragments.add(TrendChartPK10Fragment.newInstance(2, mData.get(2), mLinesData.get(2)));
        fragments.add(TrendChartPK10Fragment.newInstance(3, mData.get(3), mLinesData.get(3)));
        fragments.add(TrendChartPK10Fragment.newInstance(4, mData.get(4), mLinesData.get(4)));
        fragments.add(TrendChartPK10Fragment.newInstance(5, mData.get(5), mLinesData.get(5)));
        fragments.add(TrendChartPK10Fragment.newInstance(6, mData.get(6), mLinesData.get(6)));
        fragments.add(TrendChartPK10Fragment.newInstance(7, mData.get(7), mLinesData.get(7)));
        fragments.add(TrendChartPK10Fragment.newInstance(8, mData.get(8), mLinesData.get(8)));
        fragments.add(TrendChartPK10Fragment.newInstance(9, mData.get(9), mLinesData.get(9)));

        mTabLayout.removeAllTabs();

        for (int i = 0; i < fragments.size(); i++) {
            if (mCurrentIndex == i) {
                mTabLayout.addTab(mTabLayout.newTab(), true);
            } else {
                mTabLayout.addTab(mTabLayout.newTab(), false);
            }
        }
    }

    private void setData() {
        mData = new ArrayList<>();
        mLinesData = new ArrayList<>();
        List<List<Integer>> occurrences = new ArrayList<>();    //出现次数
        List<List<Integer>> averagOmission = new ArrayList<>(); //平均遗漏
        List<List<Integer>> maxOmission = new ArrayList<>();    //最大遗漏
        List<List<Integer>> maxContinuity = new ArrayList<>();  //最大连出
        for (int h = 0; h < 10; h++) {
            ArrayList<ChartDataBean> data1 = new ArrayList<>();
            mData.add(data1);
            ArrayList<LinesBean> lines = new ArrayList<>();
            mLinesData.add(lines);
            List<Integer> occ = new ArrayList<>();
            occurrences.add(occ);
            List<Integer> maxO = new ArrayList<>();
            maxOmission.add(maxO);
            List<Integer> maxC = new ArrayList<>();
            maxContinuity.add(maxC);
        }

        for (int i = 0; i < mRencentList.size(); i++) {
            Handicap handicap = mRencentList.get(i);
            String openCode = handicap.getOpenCode();
            String[] numbers = openCode.split(",");
            for (int j = 0; j < numbers.length; j++) {
                int number = Integer.valueOf(numbers[j]);
                List<LinesBean> linesBeans = mLinesData.get(j);
                LinesBean linesBean = new LinesBean();
                linesBean.setBallIndex(number - 1);
                List<Integer> occ = occurrences.get(j);  //出现次数
                List<Integer> maxO = maxOmission.get(j); //最大遗漏
                List<Integer> maxC = maxContinuity.get(j); //最大连出

                List<ChartDataBean> chartDataBeans = mData.get(j);
                ChartDataBean chartDataBean = new ChartDataBean();
                chartDataBean.setExpect(handicap.getExpect());
                chartDataBean.setItemType(ChartDataBean.ITEM_TYPE_CHART);

                List<Integer> rowList = new ArrayList<>();
                if (i == 0) {
                    for (int k = 0; k < 14; k++) {
                        rowList.add(0);
                        occ.add(0);
                        maxO.add(0);
                        maxC.add(0);
                    }
                } else {
                    rowList.addAll(mData.get(j).get(i - 1).getRowList());    //赋值为上一条数据参数，用于叠加计算
                }

                for (int k = 0; k < 14; k++) {
                    if (k < 10) {
                        if (k == number - 1) {
                            occ.set(k, occ.get(k) + 1); //统计出现次数
                            if (rowList.get(k) < 1000)
                                rowList.set(k, number + 1000);
                        } else {
                            int rowNum = rowList.get(k);
                            if (rowNum < 1000) {
                                rowList.set(k, rowList.get(k) + 1);
                                if (maxO.get(k) < rowList.get(k)) {  //记录最大遗漏值
                                    maxO.set(k, rowList.get(k));
                                }
                            } else {
                                rowList.set(k, 1);
                                computeMaxContinuity(maxC, chartDataBeans, i, k);//记录最大连出
                            }
                        }
                    }

                }
                //大小单双
                if (number > 5) {
                    linesBean.setBigSmall(10);
                    int num = rowList.get(10);
                    int num1 = rowList.get(11);
                    if (num < 1000) {
                        rowList.set(10, num + 1000);
                        occ.set(10, occ.get(10) + 1); //统计大出现次数
                    }
                    if (num1 < 1000) {
                        rowList.set(11, num1 + 1);
                        if (maxO.get(11) < rowList.get(11)) {  //记录大最大遗漏值
                            maxO.set(11, rowList.get(11));
                        }
                    } else {
                        rowList.set(11, 1);
                        computeMaxContinuity(maxC, chartDataBeans, i, 11);//记录小最大连出
                    }
                } else {
                    linesBean.setBigSmall(11);
                    int num = rowList.get(11);
                    int num1 = rowList.get(10);
                    if (num < 1000) {
                        rowList.set(11, num + 1000);
                        occ.set(11, occ.get(11) + 1); //统计小出现次数
                    }
                    if (num1 < 1000) {
                        rowList.set(10, num1 + 1);
                        if (maxO.get(10) < rowList.get(10)) {  //记录小最大遗漏值
                            maxO.set(10, rowList.get(10));
                        }
                    } else {
                        rowList.set(10, 1);
                        computeMaxContinuity(maxC, chartDataBeans, i, 10);//记录大最大连出
                    }
                }

                if (number % 2 == 1) {
                    linesBean.setSingleDouble(12);
                    int num = rowList.get(12);
                    int num1 = rowList.get(13);
                    if (num < 1000) {
                        rowList.set(12, num + 1000);
                        occ.set(12, occ.get(12) + 1); //统计单出现次数
                    }
                    if (num1 < 1000) {
                        rowList.set(13, num1 + 1);
                        if (maxO.get(13) < rowList.get(13)) {  //记录单最大遗漏值
                            maxO.set(13, rowList.get(13));
                        }
                    } else {
                        rowList.set(13, 1);
                        computeMaxContinuity(maxC, chartDataBeans, i, 13);//记录双最大连出
                    }
                } else {
                    linesBean.setSingleDouble(13);
                    int num = rowList.get(13);
                    int num1 = rowList.get(12);
                    if (num < 1000) {
                        rowList.set(13, num + 1000);
                        occ.set(13, occ.get(13) + 1); //统计双出现次数
                    }
                    if (num1 < 1000) {
                        rowList.set(12, num1 + 1);
                        if (maxO.get(12) < rowList.get(12)) {  //记录双最大遗漏值
                            maxO.set(12, rowList.get(12));
                        }
                    } else {
                        rowList.set(12, 1);
                        computeMaxContinuity(maxC, chartDataBeans, i, 12);//记录单最大连出
                    }
                }
                chartDataBean.setRowList(rowList);
                chartDataBeans.add(chartDataBean);
                linesBeans.add(linesBean);
            }
        }

        for (int i = 0; i < occurrences.size(); i++) {   //根据出现次数计算平均遗漏
            List<Integer> nums = new ArrayList<>();
            List<Integer> occ = occurrences.get(i);
            for (int j : occ) {
                if (j == 0) {
                    nums.add(120);
                } else
                    nums.add((120 - j) / j);
            }
            averagOmission.add(nums);
        }

        //出现次数、平均遗漏、最大遗漏、最大连出
        for (int i = 0; i < mData.size(); i++) {
            ArrayList<ChartDataBean> kp10charts = mData.get(i);

            ChartDataBean chartDataBean = new ChartDataBean();
            chartDataBean.setExpect("出现次数");
            chartDataBean.setItemType(ChartDataBean.ITEM_TYPE_STATISTIC);
            chartDataBean.setRowList(occurrences.get(i));
            kp10charts.add(chartDataBean);

            ChartDataBean chartDataBean2 = new ChartDataBean();
            chartDataBean2.setExpect("平均遗漏");
            chartDataBean2.setItemType(ChartDataBean.ITEM_TYPE_STATISTIC);
            chartDataBean2.setRowList(averagOmission.get(i));
            kp10charts.add(chartDataBean2);

            ChartDataBean chartDataBean3 = new ChartDataBean();
            chartDataBean3.setExpect("最大遗漏");
            chartDataBean3.setItemType(ChartDataBean.ITEM_TYPE_STATISTIC);
            chartDataBean3.setRowList(maxOmission.get(i));
            kp10charts.add(chartDataBean3);

            ChartDataBean chartDataBean4 = new ChartDataBean();
            chartDataBean4.setExpect("最大连击");
            chartDataBean4.setItemType(ChartDataBean.ITEM_TYPE_STATISTIC);
            chartDataBean4.setRowList(maxContinuity.get(i));
            kp10charts.add(chartDataBean4);
        }


    }

    private void computeMaxContinuity(List<Integer> maxC, List<ChartDataBean> chartDataBeans, int i, int k) {
        int comput = 0;
        int position = i;
        boolean isNum = true;
        while (isNum) {
            position--;
            if (position >= 0) {
                int last = chartDataBeans.get(position).getRowList().get(k);
                if (last >= 1000) {
                    comput += 1;
                } else {
                    isNum = false;
                }
            } else {
                isNum = false;
            }

        }
        if (maxC.get(k) < comput) {
            maxC.set(k, comput);
        }
    }


    class ChartViewPagerAdapter extends FragmentStatePagerAdapter {
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

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }

//    public void onError() {
//        if (countDownTimer != null) {
//            countDownTimer.start();
//        }
//    }
}
