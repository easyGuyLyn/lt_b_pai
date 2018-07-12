package com.dawoo.lotterybox.view.activity.chart.keno2;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
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
import com.dawoo.lotterybox.view.activity.chart.pk10.LinesBean;
import com.dawoo.lotterybox.view.activity.chart.ChartDataBean;
import com.dawoo.lotterybox.view.fragment.BaseFragment;
import com.hwangjr.rxbus.RxBus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;

import static com.dawoo.lotterybox.ConstantValue.LT_NAME;
import static com.dawoo.lotterybox.ConstantValue.RENCT_DATA;
import static com.dawoo.lotterybox.view.activity.chart.keno2.TrendChartXy28Fragment.REFRSH;

/**
 * Created by b on 18-4-4.
 * pk10折线图
 */

public class Xy28TrendChartActivity extends BaseChartActivity {

    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.iv_go_back)
    ImageView mIvGoBack;
    @BindView(R.id.tv_play_method)
    TextView mHeadView;


    private List<BaseFragment> fragments = new ArrayList<>();
    private List<ArrayList<ChartDataBean>> mData;
    private List<ArrayList<LinesBean>> mLinesData;
    private List<String> titles = new ArrayList<>();//tab项
    private ChartViewPagerAdapter chartViewPagerAdapter;

    public static void goXy28ChartActivity(Context context, List<Handicap> recentList, String lotteryName, String lotteryCode) {
        Intent intent = new Intent(context, Xy28TrendChartActivity.class);
        intent.putParcelableArrayListExtra(RENCT_DATA, (ArrayList<? extends Parcelable>) recentList);
        intent.putExtra(LT_NAME, lotteryName);
        intent.putExtra(ConstantValue.LT_CODE, lotteryCode);
        context.startActivity(intent);
    }

    @Override
    protected void createLayoutView() {
        setContentView(R.layout.activity_xy28_trend_chart);
    }

    @Override
    protected void initViews() {
        mIvGoBack.setOnClickListener(v -> finish());
    }


    @Override
    protected void initData() {
        super.initData();
        mHeadView.setText(LotteryUtil.getLotteryNameByCode(mLotteryCode));

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mRencentList == null) {
                    mRencentList = getIntent().getParcelableArrayListExtra(ConstantValue.RENCT_DATA);
                }
                Collections.sort(mRencentList);
                titles.add("开奖");
                titles.add("百位");
                titles.add("十位");
                titles.add("个位");
                initTab();
                setData(mRencentList);
                initFragment();
                chartViewPagerAdapter = new ChartViewPagerAdapter(getSupportFragmentManager(), fragments, titles);
                mViewPager.setAdapter(chartViewPagerAdapter);
                mTabLayout.setupWithViewPager(mViewPager);
                mProgressDialog.dismiss();
            }
        }, 50);
    }


    /**
     * 初始化 tab ui
     */
    private void initTab() {
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
        fragments.add(Xy28RewardListFragment.newInstance(mRencentList));
        for (int i = 0; i < 3; i++) {
            fragments.add(TrendChartXy28Fragment.newInstance(mData.get(i), mLinesData.get(i), i));
        }
    }

    private void setData(List<Handicap> rencentList) {
        mData = new ArrayList<>();
        mLinesData = new ArrayList<>();
        for (int h = 0; h < 3; h++) {
            ArrayList<ChartDataBean> data1 = new ArrayList<>();
            mData.add(data1);
            ArrayList<LinesBean> lines = new ArrayList<>();
            mLinesData.add(lines);
        }

        for (int i = 0; i < rencentList.size(); i++) {
            Handicap handicap = rencentList.get(i);
            String openCode = handicap.getOpenCode();
            String[] numbers = openCode.split(",");
            for (int j = 0; j < numbers.length; j++) {
                int number = Integer.valueOf(numbers[j]);
                List<LinesBean> linesBeans = mLinesData.get(j);
                LinesBean linesBean = new LinesBean();
                linesBean.setBallIndex(number);
                List<ChartDataBean> chartDataBeans = mData.get(j);
                ChartDataBean chartDataBean = new ChartDataBean();
                chartDataBean.setExpect(handicap.getExpect());
                chartDataBean.setItemType(ChartDataBean.ITEM_TYPE_CHART);
                List<Integer> rowList = new ArrayList<>();
                if (i == 0) {
                    for (int k = 0; k < 10; k++) {
                        rowList.add(0);
                    }
                } else {
                    rowList.addAll(mData.get(j).get(i - 1).getRowList());    //赋值为上一条数据参数，用于叠加计算
                }

                for (int k = 0; k < rowList.size(); k++) {
                    if (k == number) {
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
                chartDataBean.setRowList(rowList);
                chartDataBeans.add(chartDataBean);
                linesBeans.add(linesBean);
            }
        }
    }

    public void refreshViews() {
        super.refreshViews();
        setData(mRencentList);
        RxBus.get().post(REFRSH, new Xy28EventBean(mData, mLinesData));
        RxBus.get().post(RENCT_DATA, new Xy28RewardBean(mRencentList));
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
