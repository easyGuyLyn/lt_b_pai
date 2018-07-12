package com.dawoo.lotterybox.view.activity.chart.k3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.dawoo.lotterybox.ConstantValue;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.bean.lottery.BaseHandicap;
import com.dawoo.lotterybox.bean.lottery.Handicap;
import com.dawoo.lotterybox.bean.lottery.HandicapWithOpening;
import com.dawoo.lotterybox.bean.lottery.Lottery;
import com.dawoo.lotterybox.bean.lottery.LotteryOddBean;
import com.dawoo.lotterybox.bean.lottery.SaveOrderResult;
import com.dawoo.lotterybox.bean.lottery.lotteryenum.LotteryEnum;
import com.dawoo.lotterybox.mvp.presenter.LotteryPresenter;
import com.dawoo.lotterybox.mvp.view.IBaseLotteryView;
import com.dawoo.lotterybox.util.lottery.LotteryUtil;
import com.dawoo.lotterybox.view.activity.BaseActivity;
import com.dawoo.lotterybox.view.activity.chart.BaseChartActivity;
import com.dawoo.lotterybox.view.activity.chart.pk10.PK10TrendChartActivity;
import com.dawoo.lotterybox.view.activity.chart.pk10.Pk10EventOB;
import com.dawoo.lotterybox.view.fragment.BaseFragment;
import com.dawoo.lotterybox.view.view.CountDownTimerUtils;
import com.dawoo.lotterybox.view.view.CustomProgressDialog;
import com.dawoo.lotterybox.view.view.HeaderView;
import com.hwangjr.rxbus.RxBus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.dawoo.lotterybox.ConstantValue.RENCT_DATA;

/**
 * Created by b on 18-4-24.
 * k3走势图
 */

public class K3TrendChartActivity extends BaseChartActivity {
    @BindView(R.id.head_view)
    HeaderView mHeadView;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    List<BaseFragment> fragments = new ArrayList<>();


    public static void goK3TrendChartActivity(Context context, List<Handicap> recentList) {
        Intent intent = new Intent(context, K3TrendChartActivity.class);
        intent.putParcelableArrayListExtra(RENCT_DATA, (ArrayList<? extends Parcelable>) recentList);
        context.startActivity(intent);
    }

    @Override
    protected void createLayoutView() {
        setContentView(R.layout.activity_k3_trend_chart);
    }

    @Override
    protected void initViews() {

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
                List<String> titles = new ArrayList<>();
                titles.add("开奖");
                titles.add("基本走势");
                titles.add("和值走势");
                titles.add("冷热");
                initFragment();
                mViewPager.setOffscreenPageLimit(3);
                ChartViewPagerAdapter chartViewPagerAdapter = new ChartViewPagerAdapter(getSupportFragmentManager(), fragments, titles);
                mViewPager.setAdapter(chartViewPagerAdapter);
                mTabLayout.setupWithViewPager(mViewPager);
                mProgressDialog.dismiss();
            }
        }, 50);
    }

    @Override
    public void refreshViews() {
        super.refreshViews();
        RxBus.get().post(ConstantValue.EVENT_TYPE_OPENING_LOTTERY, "opened");
    }

    public ArrayList<Handicap> getmRencentList() {
        return mRencentList;
    }


    private void initFragment() {
        K3OpenFragment k3OpenFragment = K3OpenFragment.newInstance(mRencentList);
        fragments.add(k3OpenFragment);
        K3BasicTrendFragment k3BasicTrendFragment = K3BasicTrendFragment.newInstance(mRencentList);
        fragments.add(k3BasicTrendFragment);
        K3SumTrendFragment k3SumTrendFragment = K3SumTrendFragment.newInstance(mRencentList);
        fragments.add(k3SumTrendFragment);
        K3ColdHotFragment k3ColdHotFragment = K3ColdHotFragment.newInstance(mRencentList);
        fragments.add(k3ColdHotFragment);
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
}
