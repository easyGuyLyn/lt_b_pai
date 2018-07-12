package com.dawoo.lotterybox.view.activity.chart.k3;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dawoo.lotterybox.ConstantValue;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.bean.lottery.Handicap;
import com.dawoo.lotterybox.view.activity.chart.ChartDataBean;
import com.dawoo.lotterybox.view.activity.chart.ssc.ChartSSCActivity;
import com.dawoo.lotterybox.view.activity.chart.ssc.SSCStatistics;
import com.dawoo.lotterybox.view.fragment.BaseFragment;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.dawoo.lotterybox.view.activity.chart.ChartDataBean.ITEM_TYPE_CHART;
import static com.dawoo.lotterybox.view.activity.chart.ChartDataBean.ITEM_TYPE_STATISTIC;


/**
 * Created by b on 18-4-25.
 * k3基本走势
 */

public class K3BasicTrendFragment extends BaseFragment {
    @BindView(R.id.rlv_basic_trent)
    RecyclerView mRlvBasicTrent;

    private static final String ARG_PARAM1 = "param1";

    private ArrayList<ChartDataBean> mBasicTRentBeans = new ArrayList<>();
    private Unbinder mUnbinder;
    private BasicTrentAdapter mRightAdapter;
    private View mRightFooterView;
    private Handler mHandler;
    protected ArrayList<Handicap> mRencentList;

    public static K3BasicTrendFragment newInstance(ArrayList<Handicap> handicaps) {
        K3BasicTrendFragment fragment = new K3BasicTrendFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_PARAM1, handicaps);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_k3_basic_trent, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        initViews();
        return view;
    }

    private void setScroll() {
        RxBus.get().register(this);
        mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mRencentList != null) {
                    mRlvBasicTrent.scrollToPosition(mRencentList.size() - 1);
                }
            }
        }, 200);
    }


    private void initViews() {
        mRightAdapter = new BasicTrentAdapter(new ArrayList());
        mRlvBasicTrent.setLayoutManager(new LinearLayoutManager(mContext));
        mRlvBasicTrent.setAdapter(mRightAdapter);
        setScroll();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mRencentList = getArguments().getParcelableArrayList(ARG_PARAM1);
            convertData(mRencentList);
        }
    }


    @Override
    protected void loadData() {
        mRightAdapter.setNewData(mBasicTRentBeans);
    }

    private void convertData(ArrayList<Handicap> mHandicaps) {
        if (mHandicaps == null) {
            return;
        }
        List<Integer> occurrences = new ArrayList<>();    //出现次数
        List<Integer> averagOmission = new ArrayList<>(); //平均遗漏
        List<Integer> maxOmission = new ArrayList<>();    //最大遗漏
        List<Integer> maxContinuity = new ArrayList<>();  //最大连出
        for (int j = 0; j < 6; j++) {
            occurrences.add(0);
            maxOmission.add(0);
            maxContinuity.add(0);
        }
        for (int i = 0; i < mHandicaps.size(); i++) {
            Handicap handicap = mHandicaps.get(i);
            ChartDataBean chartDataBean = new ChartDataBean();
            chartDataBean.setExpect(handicap.getExpect());
            chartDataBean.setItemType(ChartDataBean.ITEM_TYPE_CHART);
            List<Integer> rowList = new ArrayList<>();
            if (i == 0) {
                for (int j = 0; j < 9; j++) {
                    rowList.add(0);
                }
            } else {
                rowList.addAll(mBasicTRentBeans.get(i - 1).getRowList());
            }
            String openNum = handicap.getOpenCode().replace(",", "");
            rowList.set(0, Integer.valueOf(openNum));  //开奖号码
            String[] strNumbers = handicap.getOpenCode().split(",");
            List<Integer> numbers = new ArrayList<>();
            numbers.add(Integer.valueOf(strNumbers[0]));
            numbers.add(Integer.valueOf(strNumbers[1]));
            numbers.add(Integer.valueOf(strNumbers[2]));
            rowList.set(1, numbers.get(0) + numbers.get(1) + numbers.get(2));  //和值
            Collections.sort(numbers);
            rowList.set(2, numbers.get(2) - numbers.get(0)); // 跨度
            for (int k = 3; k < 9; k++) {
                boolean sureAdd = true;
                for (int h = 0; h < numbers.size(); h++) {
                    int num = numbers.get(h);
                    if (k - 2 == num) {
                        if (sureAdd) {
                            occurrences.set(k - 3, occurrences.get(k - 3) + 1);  //记录出现次数
                            sureAdd = false;
                        }
                        if (rowList.get(k) < 1000) {
                            rowList.set(k, num + 1000);
                        } else {
                            if (h == 0) {
                                rowList.set(k, num + 1000);
                            } else {
                                rowList.set(k, rowList.get(k) + 1000);
                            }
                        }
                    } else {
                        if (rowList.get(k) >= 1000) {
                            if (h == 0) {
                                rowList.set(k, 0);
                                computeMaxContinuity(maxContinuity, mBasicTRentBeans, i, k);//记录最大连出
                            }
                        }
                    }
                }
                if (rowList.get(k) < 1000) {
                    rowList.set(k, rowList.get(k) + 1);
                    if (maxOmission.get(k - 3) < rowList.get(k)) {  //记录最大遗漏值
                        maxOmission.set(k - 3, rowList.get(k));
                    }
                }
            }
            chartDataBean.setRowList(rowList);
            mBasicTRentBeans.add(chartDataBean);
        }

        for (int j : occurrences) {          //根据出现次数计算平均遗漏
            if (j == 0) {
                averagOmission.add(120);
            } else
                averagOmission.add((120 - j) / j);
        }

        ChartDataBean chartDataBean = new ChartDataBean();
        chartDataBean.setExpect("出现次数");
        chartDataBean.setItemType(ChartDataBean.ITEM_TYPE_STATISTIC);
        chartDataBean.setRowList(occurrences);
        mBasicTRentBeans.add(chartDataBean);

        ChartDataBean chartDataBean2 = new ChartDataBean();
        chartDataBean2.setExpect("平均遗漏");
        chartDataBean2.setItemType(ChartDataBean.ITEM_TYPE_STATISTIC);
        chartDataBean2.setRowList(averagOmission);
        mBasicTRentBeans.add(chartDataBean2);

        ChartDataBean chartDataBean3 = new ChartDataBean();
        chartDataBean3.setExpect("最大遗漏");
        chartDataBean3.setItemType(ChartDataBean.ITEM_TYPE_STATISTIC);
        chartDataBean3.setRowList(maxOmission);
        mBasicTRentBeans.add(chartDataBean3);

        ChartDataBean chartDataBean4 = new ChartDataBean();
        chartDataBean4.setExpect("最大连击");
        chartDataBean4.setItemType(ChartDataBean.ITEM_TYPE_STATISTIC);
        chartDataBean4.setRowList(maxContinuity);
        mBasicTRentBeans.add(chartDataBean4);

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
        if (maxC.get(k - 3) < comput) {
            maxC.set(k - 3, comput);
        }
    }

    @Override
    public void onDestroy() {
        mRightFooterView = null;
        RxBus.get().unregister(this);
        super.onDestroy();
        mUnbinder.unbind();
    }

    class BasicTrentAdapter extends BaseMultiItemQuickAdapter {
        int mCount = 0;

        public BasicTrentAdapter(List data) {
            super(data);
            addItemType(ITEM_TYPE_CHART, R.layout.layout_k3_basic_trend);
            addItemType(ITEM_TYPE_STATISTIC, R.layout.layout_k3_basic_trend_statistics);
        }

        @Override
        protected void convert(BaseViewHolder helper, Object item) {
            if (item == null) {
                return;
            }
            ChartDataBean chartDataBean = (ChartDataBean) item;
            List<Integer> rowList = chartDataBean.getRowList();
            int[] ids = {R.id.tv_number_1, R.id.tv_number_2, R.id.tv_number_3, R.id.tv_number_4, R.id.tv_number_5, R.id.tv_number_6};
            int[] tagIds = {R.id.tv_number_1_tag, R.id.tv_number_2_tag, R.id.tv_number_3_tag, R.id.tv_number_4_tag, R.id.tv_number_5_tag, R.id.tv_number_6_tag};
            setItemBg(helper);

            switch (helper.getItemViewType()) {
                case ITEM_TYPE_CHART:
//                    helper.setText(R.id.tv_expect_number, getString(R.string.which_periods_, chartDataBean.getExpect()));
                    String expect = chartDataBean.getExpect();
                    if (TextUtils.isEmpty(expect)) {
                        return;
                    }
                    if (expect.length() - 3 > 0) {
                        String expect2 = getString(R.string.which_periods_,expect.substring(expect.length() - 3));
                        helper.setText(R.id.tv_expect_number, expect2);
                    } else {
                        String expect3 = getString(R.string.which_periods_,expect);
                        helper.setText(R.id.tv_expect_number, expect3);
                    }

                    helper.setText(R.id.tv_open_number, String.valueOf(rowList.get(0)));
                    helper.setTextColor(R.id.tv_open_number, ContextCompat.getColor(mContext, R.color.open_item_text2));
                    helper.setText(R.id.tv_sum_value, String.valueOf(rowList.get(1)));
                    helper.setTextColor(R.id.tv_sum_value, ContextCompat.getColor(mContext, R.color.open_item_text2));
                    helper.setText(R.id.tv_big_small, String.valueOf(rowList.get(2)));
                    helper.setTextColor(R.id.tv_big_small, ContextCompat.getColor(mContext, R.color.open_item_text2));
                    for (int i = 0; i < 6; i++) {
                        int num = rowList.get(i + 3);
                        if (num < 1000) {
                            helper.setText(ids[i], String.valueOf(num));
                            helper.setTextColor(ids[i], ContextCompat.getColor(mContext, R.color.open_item_text));
                            helper.setBackgroundRes(ids[i], R.color.transparent);
                            helper.setVisible(tagIds[i], false);
                        } else {
                            helper.setText(ids[i], String.valueOf(num % 1000));
                            helper.setTextColor(ids[i], ContextCompat.getColor(mContext, R.color.open_tab_layout_selecr));
                            helper.setBackgroundRes(ids[i], R.drawable.shape_k3_trent_open_text_bg3);
                            int tag = num / 1000;
                            if (tag > 1) {
                                helper.setVisible(tagIds[i], true);
                                helper.setText(tagIds[i], String.valueOf(tag));
                            } else {
                                helper.setVisible(tagIds[i], false);
                            }
                        }
                    }
                    break;

                case ITEM_TYPE_STATISTIC:
                    helper.setText(R.id.tv_expect_number, chartDataBean.getExpect());
                    if (mCount % 4 == 0) {
                        helper.setTextColor(R.id.tv_expect_number, ContextCompat.getColor(mContext, R.color.color_chart_statistics_1));
                    } else if (mCount % 4 == 1) {
                        helper.setTextColor(R.id.tv_expect_number, ContextCompat.getColor(mContext, R.color.color_chart_statistics_2));
                    } else if (mCount % 4 == 2) {
                        helper.setTextColor(R.id.tv_expect_number, ContextCompat.getColor(mContext, R.color.color_chart_statistics_3));
                    } else if (mCount % 4 == 3) {
                        helper.setTextColor(R.id.tv_expect_number, ContextCompat.getColor(mContext, R.color.color_chart_statistics_4));
                    }
                    List<Integer> statisticsList = chartDataBean.getRowList();
                    for (int i = 0; i < statisticsList.size(); i++) {
                        int num = statisticsList.get(i);
                        helper.setText(ids[i], String.valueOf(num));
                        if (mCount % 4 == 0) {
                            helper.setTextColor(ids[i], ContextCompat.getColor(mContext, R.color.color_chart_statistics_1));
                        } else if (mCount % 4 == 1) {
                            helper.setTextColor(ids[i], ContextCompat.getColor(mContext, R.color.color_chart_statistics_2));
                        } else if (mCount % 4 == 2) {
                            helper.setTextColor(ids[i], ContextCompat.getColor(mContext, R.color.color_chart_statistics_3));
                        } else if (mCount % 4 == 3) {
                            helper.setTextColor(ids[i], ContextCompat.getColor(mContext, R.color.color_chart_statistics_4));
                        }
                    }
                    mCount++;
                    break;
            }
        }
    }

    /**
     * 设置item背景颜色
     *
     * @param helper
     */
    void setItemBg(BaseViewHolder helper) {
        int position = helper.getAdapterPosition();
        if (position % 2 == 0) {
            helper.itemView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.open_item_bg2));
        } else {
            helper.itemView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.open_item_bg));
        }
    }


    /**
     * 控制开奖footer
     */
    @Subscribe(tags = {@Tag(ConstantValue.EVENT_TYPE_OPENING_LOTTERY)})
    public void toggleOpenFooter(String s) {
        if (mRencentList == null) {
            return;
        }
        if ("opening".equals(s)) {
            if (mRightFooterView == null) {
                mRightFooterView = View.inflate(mContext, R.layout.list_item_chart_right_footer_fix_view, null);
            }
            mRightAdapter.addFooterView(mRightFooterView);

            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (mRencentList != null) {
                        mRlvBasicTrent.scrollToPosition(mRencentList.size());
                    }
                }
            }, 200);
        } else {


            if (mRencentList != null) {
                mBasicTRentBeans.clear();
                convertData(mRencentList);
            }
            mRightAdapter.removeFooterView(mRightFooterView);
            mRightAdapter.notifyDataSetChanged();
        }
    }
}
