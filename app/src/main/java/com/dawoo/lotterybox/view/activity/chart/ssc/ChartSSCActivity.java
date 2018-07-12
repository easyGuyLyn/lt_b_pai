package com.dawoo.lotterybox.view.activity.chart.ssc;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.dawoo.lotterybox.ConstantValue;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.bean.lottery.Handicap;
import com.dawoo.lotterybox.bean.playtype.PlayTypeBean;
import com.dawoo.lotterybox.util.lottery.LotteryUtil;
import com.dawoo.lotterybox.view.activity.chart.BaseChartActivity;
import com.dawoo.lotterybox.view.activity.chart.ChartHeaderView;
import com.dawoo.lotterybox.view.activity.chart.pk10.PK10TrendChartActivity;
import com.dawoo.lotterybox.view.activity.chart.pk10.Pk10EventOB;
import com.dawoo.lotterybox.view.fragment.BaseFragment;
import com.hwangjr.rxbus.RxBus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 时时彩的图表
 * Created by benson on 18-2-19.
 */

public class ChartSSCActivity extends BaseChartActivity implements ChartHeaderView.OnClickPlayTypeListener {
    @BindView(R.id.head_view)
    ChartHeaderView mHeadView;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    private PlayTypeBean.PlayBean mPlayBean;
    ArrayList<ArrayList<SSCStatistics>> mSscStatisticsListList;


    @Override
    protected void createLayoutView() {
        setContentView(R.layout.activity_chart_ssc);
    }

    @Override
    protected void initViews() {
        mHeadView.setOnClickPlayTypeListener(this);
        // 获取当前玩法
        mPlayBean = mHeadView.getDefaultHowToPlay();
        mHeadView.setTitleName(mPlayBean.getScheme() + mPlayBean.getPlayTypeName());
    }

    @Override
    protected void initData() {
        super.initData();
//        mHandler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                if (mRencentList == null) {
//                    mRencentList = getIntent().getParcelableArrayListExtra(ConstantValue.RENCT_DATA);
//                }
//
//                Collections.sort(mRencentList);
//                mSscStatisticsListList = getSSCStatisticsList(mRencentList);
//                setTab(mPlayBean);
//                dissMissDialog(1500);
//            }
//        }, 50);
        Observable.just(1).map(new Func1<Integer, Integer>() {
            @Override
            public Integer call(Integer integer) {
                if (mRencentList == null) {
                    mRencentList = getIntent().getParcelableArrayListExtra(ConstantValue.RENCT_DATA);
                    Collections.sort(mRencentList);
                }
                mSscStatisticsListList = getSSCStatisticsList(mRencentList);
                return 1;
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        setTab(mPlayBean);
                        mProgressDialog.dismiss();
                    }
                });

    }

    @Override
    public void refreshViews() {
        super.refreshViews();
        mSscStatisticsListList = getSSCStatisticsList(mRencentList);
        RxBus.get().post(ConstantValue.EVENT_TYPE_OPENING_LOTTERY, "opened");
    }

    public ArrayList<ArrayList<SSCStatistics>> getmSscStatisticsListList() {
        return mSscStatisticsListList;
    }

    @Override
    public void onHowToplayLClickListener(PlayTypeBean.PlayBean playTypeBean) {
        mPlayBean = playTypeBean;
        mHeadView.setTitleName(playTypeBean.getScheme() + playTypeBean.getPlayTypeName());
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                setTab(playTypeBean);
            }
        }, 50);
    }

    public PlayTypeBean.PlayBean getPlayBean() {
        return mPlayBean;
    }


    class ChartViewPagerAdapter extends FragmentPagerAdapter {
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

    /**
     * 根据玩法设置tab
     * 玩法不同，tab有可能不一样
     *
     * @param playBean
     */
    void setTab(PlayTypeBean.PlayBean playBean) {
        if (mSscStatisticsListList == null) {
            return;
        }

        mTabLayout.removeAllTabs();
        List<String> titles = new ArrayList<>();
        List<BaseFragment> fragments = new ArrayList<>();
        mViewPager.removeAllViews();
        titles.add("开奖");

        fragments.add(ChartOpenSSCFragment.newInstance(mRencentList, playBean));
//        RxBus.get().post(ConstantValue.EVENT_TYPE_CHART_HOW_TO_PLAY, new TempObject(mRencentList,playBean));

        mTabLayout.addTab(mTabLayout.newTab(), true);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        if (playBean.getSampleBet().contains("前三")) {
            titles.add("万位" + "走势");
            titles.add("千位" + "走势");
            titles.add("百位" + "走势");
            fragments.add(ChartTrendSSCFragment.newInstance(0, mSscStatisticsListList.get(0), playBean));
            fragments.add(ChartTrendSSCFragment.newInstance(1, mSscStatisticsListList.get(1), playBean));
            fragments.add(ChartTrendSSCFragment.newInstance(2, mSscStatisticsListList.get(2), playBean));
            mTabLayout.addTab(mTabLayout.newTab(), false);
            mTabLayout.addTab(mTabLayout.newTab(), false);
            mTabLayout.addTab(mTabLayout.newTab(), false);
        } else if (playBean.getSampleBet().contains("后三")) {
            titles.add("百位" + "走势");
            titles.add("十位" + "走势");
            titles.add("个位" + "走势");
            fragments.add(ChartTrendSSCFragment.newInstance(2, mSscStatisticsListList.get(2), playBean));
            fragments.add(ChartTrendSSCFragment.newInstance(3, mSscStatisticsListList.get(3), playBean));
            fragments.add(ChartTrendSSCFragment.newInstance(4, mSscStatisticsListList.get(4), playBean));
            mTabLayout.addTab(mTabLayout.newTab(), false);
            mTabLayout.addTab(mTabLayout.newTab(), false);
            mTabLayout.addTab(mTabLayout.newTab(), false);
        } else if (playBean.getSampleBet().contains("前二")) {
            titles.add("万位" + "走势");
            titles.add("千位" + "走势");
            fragments.add(ChartTrendSSCFragment.newInstance(0, mSscStatisticsListList.get(0), playBean));
            fragments.add(ChartTrendSSCFragment.newInstance(1, mSscStatisticsListList.get(1), playBean));
            mTabLayout.addTab(mTabLayout.newTab(), false);
            mTabLayout.addTab(mTabLayout.newTab(), false);
        } else {
            mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
            mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
            titles.add("万位" + "走势");
            titles.add("千位" + "走势");
            titles.add("百位" + "走势");
            titles.add("十位" + "走势");
            titles.add("个位" + "走势");
            fragments.add(ChartTrendSSCFragment.newInstance(0, mSscStatisticsListList.get(0), playBean));
            fragments.add(ChartTrendSSCFragment.newInstance(1, mSscStatisticsListList.get(1), playBean));
            fragments.add(ChartTrendSSCFragment.newInstance(2, mSscStatisticsListList.get(2), playBean));
            fragments.add(ChartTrendSSCFragment.newInstance(3, mSscStatisticsListList.get(3), playBean));
            fragments.add(ChartTrendSSCFragment.newInstance(4, mSscStatisticsListList.get(4), playBean));
            mTabLayout.addTab(mTabLayout.newTab(), false);
            mTabLayout.addTab(mTabLayout.newTab(), false);
            mTabLayout.addTab(mTabLayout.newTab(), false);
            mTabLayout.addTab(mTabLayout.newTab(), false);
            mTabLayout.addTab(mTabLayout.newTab(), false);
        }
        mViewPager.setOffscreenPageLimit(5);
        ChartViewPagerAdapter chartViewPagerAdapter = new ChartViewPagerAdapter(getSupportFragmentManager(), fragments, titles);
        mViewPager.setAdapter(chartViewPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    /**
     * 返回五个code的图表统计
     *
     * @param rencentList
     * @return
     */
    private ArrayList<ArrayList<SSCStatistics>> getSSCStatisticsList(List<Handicap> rencentList) {
        if (rencentList == null) {
            rencentList = new ArrayList<>();
        }
        // 编造数据
        // 随机数据
        int codeNum = 5;
//        List<Handicap> list = new ArrayList<>();
//        Handicap bean;
//        Random random = new Random();
//        StringBuilder sb;
//        for (int i = 0; i < rencentList.size(); i++) {
//            bean = rencentList.get(i);
//            bean.setExpect(i + "期");
//            // integers.add(random.nextInt(10));
//            sb = new StringBuilder();
//            sb.append(random.nextInt(10))
//                    .append(",")
//                    .append(random.nextInt(10))
//                    .append(",")
//                    .append(random.nextInt(10))
//                    .append(",")
//                    .append(random.nextInt(10))
//                    .append(",")
//                    .append(random.nextInt(10));
//
//            bean.setOpenCode(sb.toString());
//            list.add(bean);
//        }

        ArrayList<ArrayList<SSCStatistics>> sscStatisticsListList = new ArrayList<>();

        for (int j = 0; j < codeNum; j++) {
            sscStatisticsListList.add(getSSCStatisticsList(rencentList, j));
        }
        return sscStatisticsListList;
    }


    /**
     * 返回一个code的图表统计
     *
     * @param list
     * @param j
     * @return
     */
    ArrayList<SSCStatistics> getSSCStatisticsList(List<Handicap> list, int j) {
        int count = list.size();
        // 统计数据
        int aviableCodeNum = 10;
        ArrayList<SSCStatistics> sscStatisticsList = new ArrayList<>();
        Handicap bean2;
        List<Integer> missListRow;
        List<Integer> appearCountList = new ArrayList<>(); // 出现次数
        List<Integer> avargeMissList = new ArrayList<>(); // 平均遗漏  (期数-出现次数)/出现次数
        List<Integer> maxMissList = new ArrayList<>(); // 最大遗漏
        List<Integer> maxLinkOutList = new ArrayList<>(); // 最大连出
        List<Integer> selectedCodeList = new ArrayList<>(); // 选中


        int[] c = new int[aviableCodeNum];
        int[] appear = new int[aviableCodeNum];
        int[] maxMiss = new int[aviableCodeNum];
        int[] maxLinkOut = new int[aviableCodeNum];
        for (int k = 0; k < aviableCodeNum; k++) {
            maxLinkOutList.add(maxLinkOut[k]);
        }

        // 120组开奖数据
        for (int i = 0; i < count; i++) {
            bean2 = list.get(i);
            String openCode = bean2.getOpenCode();
            if (openCode == null) {
                continue;
            }
            String[] arrCode = openCode.split(",");

            missListRow = new ArrayList<>();

            // 遗漏全部装入
            for (int h = 0; h < aviableCodeNum; h++) {
                missListRow.add(c[h]);
            }
            // 多加一个 做为选中
            missListRow.add(0);

            // 根据每个球的值 进行判断
            if (0 == Integer.parseInt(arrCode[j])) {
                missListRow.set(0, 0);
                missListRow.set(10, 0);
                selectedCodeList.add(0);
                appear[0]++;
                maxMiss[0] = Math.max(c[0], maxMiss[0]);
                c[0] = 0;
                doMaxLinkOut(0, aviableCodeNum, maxLinkOutList, maxLinkOut);

//                for (int k = 0; k < aviableCodeNum; k++) {
//                    if (0 == k) {
//                        maxLinkOut[0]++;
//                    } else {
//                        maxLinkOut[k] = 0;
//                    }
//                }
//                int maxLinOutTemp = Math.max(maxLinkOut[0], maxLinkOutList.get(0));
//                maxLinkOutList.set(0, maxLinOutTemp);
            } else if (1 == Integer.parseInt(arrCode[j])) {
                missListRow.set(1, 1);
                missListRow.set(10, 1);
                selectedCodeList.add(1);
                appear[1]++;
                maxMiss[1] = Math.max(c[1], maxMiss[1]);
                c[1] = 0;

                maxLinkOut[1]++;
                for (int k = 0; k < aviableCodeNum; k++) {
                    if (1 != k) {
                        maxLinkOut[k] = 0;
                    }
                }
                int maxLinOutTemp = Math.max(maxLinkOut[1], maxLinkOutList.get(1));
                maxLinkOutList.set(1, maxLinOutTemp);

            } else if (2 == Integer.parseInt(arrCode[j])) {
                missListRow.set(2, 2);
                missListRow.set(10, 2);
                selectedCodeList.add(2);
                appear[2]++;
                maxMiss[2] = Math.max(c[2], maxMiss[2]);
                c[2] = 0;
                doMaxLinkOut(2, aviableCodeNum, maxLinkOutList, maxLinkOut);

            } else if (3 == Integer.parseInt(arrCode[j])) {
                missListRow.set(3, 3);
                missListRow.set(10, 3);
                selectedCodeList.add(3);
                appear[3]++;
                maxMiss[3] = Math.max(c[3], maxMiss[3]);
                c[3] = 0;
                doMaxLinkOut(3, aviableCodeNum, maxLinkOutList, maxLinkOut);

            } else if (4 == Integer.parseInt(arrCode[j])) {
                missListRow.set(4, 4);
                missListRow.set(10, 4);
                selectedCodeList.add(4);
                appear[4]++;
                maxMiss[4] = Math.max(c[4], maxMiss[4]);
                c[4] = 0;
                doMaxLinkOut(4, aviableCodeNum, maxLinkOutList, maxLinkOut);

            } else if (5 == Integer.parseInt(arrCode[j])) {
                missListRow.set(5, 5);
                missListRow.set(10, 5);
                selectedCodeList.add(5);
                appear[5]++;
                maxMiss[5] = Math.max(c[5], maxMiss[5]);
                c[5] = 0;
                doMaxLinkOut(5, aviableCodeNum, maxLinkOutList, maxLinkOut);

            } else if (6 == Integer.parseInt(arrCode[j])) {
                missListRow.set(6, 6);
                missListRow.set(10, 6);
                selectedCodeList.add(6);
                appear[6]++;
                maxMiss[6] = Math.max(c[6], maxMiss[6]);
                c[6] = 0;
                doMaxLinkOut(6, aviableCodeNum, maxLinkOutList, maxLinkOut);

            } else if (7 == Integer.parseInt(arrCode[j])) {
                missListRow.set(7, 7);
                missListRow.set(10, 7);
                selectedCodeList.add(7);
                appear[7]++;
                maxMiss[7] = Math.max(c[7], maxMiss[7]);
                c[7] = 0;
                doMaxLinkOut(7, aviableCodeNum, maxLinkOutList, maxLinkOut);

            } else if (8 == Integer.parseInt(arrCode[j])) {
                missListRow.set(8, 8);
                missListRow.set(10, 8);
                selectedCodeList.add(8);
                appear[8]++;
                maxMiss[8] = Math.max(c[8], maxMiss[8]);
                c[8] = 0;
                doMaxLinkOut(8, aviableCodeNum, maxLinkOutList, maxLinkOut);

            } else if (9 == Integer.parseInt(arrCode[j])) {
                missListRow.set(9, 9);
                missListRow.set(10, 9);
                selectedCodeList.add(9);
                appear[9]++;
                maxMiss[9] = Math.max(c[9], maxMiss[9]);
                c[9] = 0;
                doMaxLinkOut(9, aviableCodeNum, maxLinkOutList, maxLinkOut);

            }

            for (int m = 0; m < aviableCodeNum; m++) {
                c[m]++;
            }

            SSCStatistics sscStatistics = new SSCStatistics();
            sscStatistics.setItemType(ChartTrendSSCFragment.ITEM_TYPE_CHART);
            sscStatistics.setRowList(missListRow);
            sscStatistics.setExpect(bean2.getExpect());
            sscStatisticsList.add(sscStatistics);
        }

        for (int n = 0; n < aviableCodeNum; n++) {
            appearCountList.add(appear[n]);
            if (0 == appear[n]) {
                avargeMissList.add(0);
            } else {
                avargeMissList.add((count - appear[n]) / appear[n]);
            }

            maxMissList.add(maxMiss[n]);
        }

        SSCStatistics sscStatistics1 = new SSCStatistics();
        sscStatistics1.setItemType(ChartTrendSSCFragment.ITEM_TYPE_STATISTIC);
        sscStatistics1.setExpect("出现次数");
        sscStatistics1.setStatistics(appearCountList);
        sscStatisticsList.add(sscStatistics1);

        SSCStatistics sscStatistics2 = new SSCStatistics();
        sscStatistics2.setItemType(ChartTrendSSCFragment.ITEM_TYPE_STATISTIC);
        sscStatistics2.setExpect("平均遗漏");
        sscStatistics2.setStatistics(avargeMissList);
        sscStatisticsList.add(sscStatistics2);

        SSCStatistics sscStatistics3 = new SSCStatistics();
        sscStatistics3.setItemType(ChartTrendSSCFragment.ITEM_TYPE_STATISTIC);
        sscStatistics3.setExpect("最大遗漏");
        sscStatistics3.setStatistics(maxMissList);
        sscStatisticsList.add(sscStatistics3);


        SSCStatistics sscStatistics4 = new SSCStatistics();
        sscStatistics4.setItemType(ChartTrendSSCFragment.ITEM_TYPE_STATISTIC);
        sscStatistics4.setExpect("最大连出");
        sscStatistics4.setStatistics(maxLinkOutList);
        sscStatisticsList.add(sscStatistics4);

        return sscStatisticsList;
    }


    private void doMaxLinkOut(int index, int aviableCodeNum, List<Integer> maxLinkOutList, int[] maxLinkOut) {

        for (int k = 0; k < aviableCodeNum; k++) {
            if (index == k) {
                maxLinkOut[index]++;
            } else {
                maxLinkOut[k] = 0;
            }
        }
        int maxLinOutTemp = Math.max(maxLinkOut[index], maxLinkOutList.get(index));
        maxLinkOutList.set(index, maxLinOutTemp);
    }
}
