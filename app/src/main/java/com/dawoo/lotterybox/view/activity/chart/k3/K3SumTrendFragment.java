package com.dawoo.lotterybox.view.activity.chart.k3;

import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
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
import com.dawoo.lotterybox.view.activity.chart.LineRecyclerView;
import com.dawoo.lotterybox.view.activity.chart.pk10.PK10TrendChartActivity;
import com.dawoo.lotterybox.view.activity.chart.pk10.TrendChartPK10Fragment;
import com.dawoo.lotterybox.view.activity.chart.ssc.ChartSSCActivity;
import com.dawoo.lotterybox.view.activity.chart.ssc.SSCStatistics;
import com.dawoo.lotterybox.view.fragment.BaseFragment;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.dawoo.lotterybox.view.activity.chart.ChartDataBean.ITEM_TYPE_CHART;
import static com.dawoo.lotterybox.view.activity.chart.ChartDataBean.ITEM_TYPE_STATISTIC;

/**
 * Created by b on 18-4-25.
 * k3和值走势
 */

public class K3SumTrendFragment extends BaseFragment {

    private static final String ARG_PARAM1 = "param1";
    @BindView(R.id.rlv_stage_number)
    RecyclerView mRlvStageNumber;
    @BindView(R.id.right_recycler_view)
    LineRecyclerView mRightRecyclerView;
    private View mLeftFooterView;
    private View mRightFooterView;
    private Handler mHandler;

    private ArrayList<ChartDataBean> mBasicTRentBeans = new ArrayList<>();
    List<Integer> linesData = new ArrayList<>();   //折线数据
    private Unbinder mUnbinder;
    private LeftAdapter mLeftAdapter;
    private RightAdapter mRightAdapter;
    protected ArrayList<Handicap> mRencentList;

    public static K3SumTrendFragment newInstance(ArrayList<Handicap> handicaps) {
        K3SumTrendFragment fragment = new K3SumTrendFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_PARAM1, handicaps);
        fragment.setArguments(args);
        return fragment;
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
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_k3_sum_trent, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        initViews();
        return view;
    }

    private void initViews() {
        mLeftAdapter = new LeftAdapter(new ArrayList());
        mRlvStageNumber.setLayoutManager(new LinearLayoutManager(mContext));
        mRlvStageNumber.setAdapter(mLeftAdapter);

        mRightAdapter = new RightAdapter(new ArrayList());
        mRightRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRightRecyclerView.setAdapter(mRightAdapter);

        syncScroll(mRlvStageNumber, mRightRecyclerView);
        setScroll();
    }

    private void setScroll() {
        RxBus.get().register(this);
        mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mRencentList != null) {
                    mRlvStageNumber.scrollToPosition(mRencentList.size() - 1);
                    mRightRecyclerView.scrollToPosition(mRencentList.size() - 1);
                }
            }
        }, 200);
    }


    @Override
    protected void loadData() {
        mLeftAdapter.setNewData(mBasicTRentBeans);
        mRightAdapter.setNewData(mBasicTRentBeans);
        mRightRecyclerView.setList(linesData);
        mRightRecyclerView.setLineColor(R.color.open_item_bg3);

    }

    private void syncScroll(RecyclerView leftList, RecyclerView rightList) {
        leftList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (recyclerView.getScrollState() != RecyclerView.SCROLL_STATE_IDLE) {
                    rightList.scrollBy(dx, dy);
                }
            }
        });

        rightList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (recyclerView.getScrollState() != RecyclerView.SCROLL_STATE_IDLE) {
                    leftList.scrollBy(dx, dy);
                }
            }
        });
    }

    private void convertData(ArrayList<Handicap> mHandicaps) {
        List<Integer> occurrences = new ArrayList<>();    //出现次数
        List<Integer> averagOmission = new ArrayList<>(); //平均遗漏
        List<Integer> maxOmission = new ArrayList<>();    //最大遗漏
        List<Integer> maxContinuity = new ArrayList<>();  //最大连出

        for (int i = 0; i < mHandicaps.size(); i++) {
            Handicap handicap = mHandicaps.get(i);
            String openCode = handicap.getOpenCode();
            String[] numbers = openCode.split(",");
            int sumNumber = Integer.valueOf(numbers[0]) + Integer.valueOf(numbers[1]) + Integer.valueOf(numbers[2]); //和值
            linesData.add(sumNumber - 3);
            ChartDataBean chartDataBean = new ChartDataBean();
            chartDataBean.setExpect(handicap.getExpect());
            chartDataBean.setItemType(ChartDataBean.ITEM_TYPE_CHART);
            List<Integer> rowList = new ArrayList<>();
            if (i == 0) {
                for (int k = 0; k < 16; k++) {
                    rowList.add(0);
                    occurrences.add(0);
                    maxOmission.add(0);
                    maxContinuity.add(0);
                }
            } else {
                rowList.addAll(mBasicTRentBeans.get(i - 1).getRowList());    //赋值为上一条数据参数，用于叠加计算
            }
            for (int k = 0; k < 16; k++) {
                if (k == sumNumber - 3) {
                    occurrences.set(k, occurrences.get(k) + 1); //统计出现次数
                    if (rowList.get(k) < 1000)
                        rowList.set(k, sumNumber + 1000);
                } else {
                    int rowNum = rowList.get(k);
                    if (rowNum < 1000) {
                        rowList.set(k, rowList.get(k) + 1);
                        if (maxOmission.get(k) < rowList.get(k)) {  //记录最大遗漏值
                            maxOmission.set(k, rowList.get(k));
                        }
                    } else {
                        rowList.set(k, 1);
                        computeMaxContinuity(maxContinuity, mBasicTRentBeans, i, k);//记录最大连出
                    }
                }


            }
            chartDataBean.setRowList(rowList);
            mBasicTRentBeans.add(chartDataBean);
        }

        for (int j : occurrences) {
            if (j == 0) {
                averagOmission.add(120);
            } else
                averagOmission.add((120 - j) / j);
        }

        //出现次数、平均遗漏、最大遗漏、最大连出
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
        if (maxC.get(k) < comput) {
            maxC.set(k, comput);
        }
    }

    @Override
    public void onDestroy() {
        mLeftFooterView = null;
        mRightFooterView = null;
        RxBus.get().unregister(this);
        super.onDestroy();
        mUnbinder.unbind();
    }


    /**
     * 列表
     * 统计
     */
    class RightAdapter extends BaseMultiItemQuickAdapter {
        int mCount = 0;

        public RightAdapter(List data) {
            super(data);
            addItemType(ITEM_TYPE_CHART, R.layout.layout_k3_sum_trend);
            addItemType(ITEM_TYPE_STATISTIC, R.layout.layout_k3_sum_trend);
        }

        @Override
        protected void convert(BaseViewHolder helper, Object item) {
            if (item == null) {
                return;
            }
            ChartDataBean chartDataBean = (ChartDataBean) item;
            int[] ids = {R.id.tv_item_right_3, R.id.tv_item_right_4, R.id.tv_item_right_5, R.id.tv_item_right_6, R.id.tv_item_right_7,
                    R.id.tv_item_right_8, R.id.tv_item_right_9, R.id.tv_item_right_10, R.id.tv_item_right_11, R.id.tv_item_right_12, R.id.tv_item_right_13,
                    R.id.tv_item_right_14, R.id.tv_item_right_15, R.id.tv_item_right_16, R.id.tv_item_right_17, R.id.tv_item_right_18};
            setItemBg(helper);
            switch (helper.getItemViewType()) {
                case ITEM_TYPE_CHART:

                    List<Integer> rowList = chartDataBean.getRowList();
                    for (int i = 0; i < rowList.size(); i++) {
                        int num = rowList.get(i);
                        if (num < 1000) {
                            helper.setText(ids[i], String.valueOf(rowList.get(i)));
                            helper.setTextColor(ids[i], ContextCompat.getColor(mContext, R.color.open_item_text));
                            helper.setBackgroundRes(ids[i], R.color.transparent);
                        } else {
                            helper.setText(ids[i], String.valueOf(rowList.get(i) - 1000));
                            helper.setTextColor(ids[i], ContextCompat.getColor(mContext, R.color.open_tab_layout_selecr));
                            helper.setBackgroundRes(ids[i], R.drawable.shape_sum_ball_green);
                        }
                    }

                    break;

                case ITEM_TYPE_STATISTIC:
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


    class LeftAdapter extends BaseMultiItemQuickAdapter {
        int mCount = 0;

        public LeftAdapter(List data) {
            super(data);
            addItemType(ITEM_TYPE_CHART, R.layout.list_item_chart_trend_k3_left_view);
            addItemType(ITEM_TYPE_STATISTIC, R.layout.list_item_chart_trend_k3_left_view);
        }

        @Override
        protected void convert(BaseViewHolder helper, Object item) {
            if (item == null) {
                return;
            }
            setItemBg(helper);
            ChartDataBean chartDataBean = (ChartDataBean) item;
            switch (helper.getItemViewType()) {
                case ITEM_TYPE_CHART:
//                    helper.setText(R.id.item_left_tv, chartDataBean.getExpect());

                    String expect = chartDataBean.getExpect();
                    if (TextUtils.isEmpty(expect)) {
                        return;
                    }
                    if (expect.length() - 3 > 0) {
                        String expect2 = getString(R.string.which_periods_,expect.substring(expect.length() - 3));
                        helper.setText(R.id.item_left_tv, expect2);
                    } else {
                        String expect3 = getString(R.string.which_periods_,expect);
                        helper.setText(R.id.item_left_tv, expect3);
                    }
                    break;

                case ITEM_TYPE_STATISTIC:
                    helper.setText(R.id.item_left_tv, chartDataBean.getExpect());
                    if (mCount % 4 == 0) {
                        helper.setTextColor(R.id.item_left_tv, ContextCompat.getColor(mContext, R.color.color_chart_statistics_1));
                    } else if (mCount % 4 == 1) {
                        helper.setTextColor(R.id.item_left_tv, ContextCompat.getColor(mContext, R.color.color_chart_statistics_2));
                    } else if (mCount % 4 == 2) {
                        helper.setTextColor(R.id.item_left_tv, ContextCompat.getColor(mContext, R.color.color_chart_statistics_3));
                    } else if (mCount % 4 == 3) {
                        helper.setTextColor(R.id.item_left_tv, ContextCompat.getColor(mContext, R.color.color_chart_statistics_4));
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
            if (mLeftFooterView == null) {
                mLeftFooterView = View.inflate(mContext, R.layout.list_item_chart_left_footer_view, null);
            }
            if (mRightFooterView == null) {
                mRightFooterView = View.inflate(mContext, R.layout.list_item_chart_right_footer_view, null);
            }
            mLeftAdapter.addFooterView(mLeftFooterView);
            mRightAdapter.addFooterView(mRightFooterView);

            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (mRencentList != null) {
                        mRlvStageNumber.scrollToPosition(mRencentList.size());
                        mRightRecyclerView.scrollToPosition(mRencentList.size());
                    }
                }
            }, 200);
        } else {
            mRencentList = ((K3TrendChartActivity) mContext).getmRencentList();
            if(mRencentList != null) {
                mBasicTRentBeans.clear();
                linesData.clear();
                convertData(mRencentList);
            }


            mLeftAdapter.removeFooterView(mLeftFooterView);
            mRightAdapter.removeFooterView(mRightFooterView);
            mLeftAdapter.notifyDataSetChanged();
            mRightAdapter.notifyDataSetChanged();
        }
    }
}
