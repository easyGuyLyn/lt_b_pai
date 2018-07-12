package com.dawoo.lotterybox.view.activity.chart.lhc;

import android.app.ProgressDialog;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.dawoo.lotterybox.ConstantValue;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.bean.lottery.Handicap;
import com.dawoo.lotterybox.util.SingleToast;
import com.dawoo.lotterybox.util.lottery.LotteryUtil;
import com.dawoo.lotterybox.view.activity.chart.BaseChartActivity;
import com.dawoo.lotterybox.view.activity.chart.ChartHeaderView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;

/**
 * Created by rain on 18-3-16.
 */

public class SMRecordActivity extends BaseChartActivity {
    @BindView(R.id.head_view)
    ChartHeaderView mHeadView;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    List<Fragment> mFragments = new ArrayList<>();
    List<String> titles = new ArrayList<>();
    private ProgressDialog pDialog;

    @Override
    protected void createLayoutView() {
        pDialog = new ProgressDialog(this);
        pDialog.setMessage(this.getString(R.string.loading_tip));
        pDialog.setCancelable(true);
        setContentView(R.layout.activity_chart_hlc);
        showProgress();
    }

    @Override
    protected void initViews() {
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
        // 获取当前玩法
        titles.add("开奖");
        titles.add("号码走势");
        titles.add("生肖走势");
        titles.add("特码走势");
        titles.add("单双走势");
        titles.add("尾数走势");
        mViewPager.setOffscreenPageLimit(5);
        mTabLayout.removeAllTabs();
        mTabLayout.addTab(mTabLayout.newTab().setText("开奖"), 0, true);
        mTabLayout.addTab(mTabLayout.newTab().setText("号码走势"), 1);
        mTabLayout.addTab(mTabLayout.newTab().setText("生肖走势"), 2);
        mTabLayout.addTab(mTabLayout.newTab().setText("特码走势"), 3);
        mTabLayout.addTab(mTabLayout.newTab().setText("单双走势"), 4);
        mTabLayout.addTab(mTabLayout.newTab().setText("尾数走势"), 5);
    }



    private void addData() {
        mFragments.clear();
        mFragments.add(LHC_Record_Open_Fragment.newInstace(mRencentList));
        mFragments.add(LHC_Record_code_Fragment.newInstace(mRencentList));
        mFragments.add(LHC_Record_Animal_Fragment.newInstace(mRencentList));
        mFragments.add(LHC_Record_S_line_Fragment.newInstace(mRencentList));
        mFragments.add(LHC_Record_BM_Fragment.newInstace(mRencentList));
        mFragments.add(LHC_Record_LCode_line_Fragment.newInstace(mRencentList));
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                showProgress();
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return titles.get(position);
            }
        });

        mTabLayout.setupWithViewPager(mViewPager);

    }

    @Override
    protected void initData() {
        super.initData();
        mHeadView.setTitleName(LotteryUtil.getLotteryNameByCode(mLotteryCode));
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(mRencentList == null) {
                    mRencentList = getIntent().getParcelableArrayListExtra(ConstantValue.RENCT_DATA);
                    Collections.sort(mRencentList);
                }
                if (mRencentList == null) {
                    mRencentList = new ArrayList<>();
                    SingleToast.showMsg("未获取到开奖记录");
                    finish();
                    return;
                }

                addData();
                mProgressDialog.dismiss();
            }
        }, 50);
    }

    @Override
    public void refreshViews() {
        super.refreshViews();
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        if (mFragments.size() == 0) {
//            addData();
//        }
    }
    public void showProgress(){
        if(pDialog!=null){
            if(!pDialog.isShowing()){
                pDialog.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pDialog.dismiss();
                    }
                },1500);
            }
        }
    }
    public void dimissProgress(){
        if(pDialog.isShowing()) {
            pDialog.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(pDialog!=null){
            pDialog.dismiss();
        }
    }

    @Override
    public void onRecentCloseExpect(Handicap handicap) {

    }
}
