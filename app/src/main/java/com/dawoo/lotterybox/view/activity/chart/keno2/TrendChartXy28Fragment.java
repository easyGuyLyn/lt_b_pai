package com.dawoo.lotterybox.view.activity.chart.keno2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.view.activity.chart.LineRecyclerView;
import com.dawoo.lotterybox.view.activity.chart.pk10.LinesBean;
import com.dawoo.lotterybox.view.activity.chart.ChartDataBean;
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
 * Created by b on 18-4-5.
 * PK10折线
 */

public class TrendChartXy28Fragment extends BaseFragment {
    public static final String REFRSH = "refrsh";
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "tag";

    @BindView(R.id.rlv_stage_number)
    RecyclerView mRlvStageNumberRecyclerView;
    @BindView(R.id.right_recycler_view)
    LineRecyclerView mRightRecyclerView;
    @BindView(R.id.hsv_chart)
    HorizontalScrollView mHsvChart;
    private Unbinder mUnbinder;
    private LeftAdapter mLeftAdapter;
    private List<ChartDataBean> mChartDataBeans;
    private List<LinesBean> mLinesBeans;
    private RightAdapter mRightAdapter;
    private int mTag;

    public static TrendChartXy28Fragment newInstance(ArrayList<ChartDataBean> chartDataBeans, ArrayList<LinesBean> linesBeans, int tag) {
        TrendChartXy28Fragment fragment = new TrendChartXy28Fragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_PARAM1, chartDataBeans);
        args.putParcelableArrayList(ARG_PARAM2, linesBeans);
        args.putInt(ARG_PARAM3, tag);
        fragment.setArguments(args);
        return fragment;
    }

    public TrendChartXy28Fragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mChartDataBeans = getArguments().getParcelableArrayList(ARG_PARAM1);
            mLinesBeans = getArguments().getParcelableArrayList(ARG_PARAM2);
            mTag = getArguments().getInt(ARG_PARAM3);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_xy28_trend_chart, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        RxBus.get().register(this);
        initViews();
        initData();
        initLister();
        return view;
    }


    private void initViews() {
        mLeftAdapter = new LeftAdapter(new ArrayList());
        mRlvStageNumberRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRlvStageNumberRecyclerView.setAdapter(mLeftAdapter);

        mRightAdapter = new RightAdapter(new ArrayList());
        mRightRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRightRecyclerView.setAdapter(mRightAdapter);

        syncScroll(mRlvStageNumberRecyclerView, mRightRecyclerView);
    }

    @Subscribe(tags = {@Tag(REFRSH)})
    public void clearSelectedData(Xy28EventBean xy28EventBean) {
        if (xy28EventBean != null) {
            mChartDataBeans = xy28EventBean.getData().get(mTag);
            mLinesBeans = xy28EventBean.getLinesData().get(mTag);
            initData();
        }
    }

    private void initData() {
        mLeftAdapter.setNewData(mChartDataBeans);
        mRightAdapter.setNewData(mChartDataBeans);
        List<Integer> list = new ArrayList<>();
        for (LinesBean linesBean : mLinesBeans) {
            list.add(linesBean.getBallIndex());
        }
        mRightRecyclerView.setList(list);
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

    private void initLister() {

    }

    @Override
    protected void loadData() {

    }


    @Override
    public void onDestroyView() {
        RxBus.get().unregister(this);
        super.onDestroyView();
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
            addItemType(ITEM_TYPE_CHART, R.layout.list_item_chart_trent_xy28_right_title_view);
            addItemType(ITEM_TYPE_STATISTIC, R.layout.list_item_chart_trent_xy28_right_title_view);
        }

        @Override
        protected void convert(BaseViewHolder helper, Object item) {
            if (item == null) {
                return;
            }
            ChartDataBean chartDataBean = (ChartDataBean) item;
            int[] ids = {R.id.tv_item_right_0, R.id.tv_item_right_1, R.id.tv_item_right_2, R.id.tv_item_right_3, R.id.tv_item_right_4, R.id.tv_item_right_5,
                    R.id.tv_item_right_6, R.id.tv_item_right_7, R.id.tv_item_right_8, R.id.tv_item_right_9, R.id.tv_item_right_big, R.id.tv_item_right_small,
                    R.id.tv_item_right_single, R.id.tv_item_right_double};
            setItemBg(helper);
            switch (helper.getItemViewType()) {
                case ITEM_TYPE_CHART:
                    List<Integer> rowList = chartDataBean.getRowList();
                    for (int i = 0; i < rowList.size(); i++) {
                        int num = rowList.get(i);
                        if (i < 10) {
                            if (num < 1000) {
                                helper.setText(ids[i], String.valueOf(rowList.get(i)));
                                helper.setBackgroundRes(ids[i], R.color.transparent);
                            } else {
                                helper.setText(ids[i], String.valueOf(rowList.get(i) - 1000));
                                helper.setBackgroundRes(ids[i], R.drawable.shape_ball_yellow);
                            }
                        }
                    }
                    break;

                case ITEM_TYPE_STATISTIC:
                    List<Integer> statisticsList = chartDataBean.getRowList();
                    helper.setText(R.id.tv_item_right_0, String.valueOf(statisticsList.get(0)));
                    helper.setText(R.id.tv_item_right_1, String.valueOf(statisticsList.get(1)));
                    helper.setText(R.id.tv_item_right_2, String.valueOf(statisticsList.get(2)));
                    helper.setText(R.id.tv_item_right_3, String.valueOf(statisticsList.get(3)));
                    helper.setText(R.id.tv_item_right_4, String.valueOf(statisticsList.get(4)));
                    helper.setText(R.id.tv_item_right_5, String.valueOf(statisticsList.get(5)));
                    helper.setText(R.id.tv_item_right_6, String.valueOf(statisticsList.get(6)));
                    helper.setText(R.id.tv_item_right_7, String.valueOf(statisticsList.get(7)));
                    helper.setText(R.id.tv_item_right_8, String.valueOf(statisticsList.get(8)));
                    helper.setText(R.id.tv_item_right_9, String.valueOf(statisticsList.get(9)));

                    if (mCount % 4 == 0) {
                        helper.setTextColor(R.id.tv_item_right_0, ContextCompat.getColor(mContext, R.color.color_chart_statistics_1));
                        helper.setTextColor(R.id.tv_item_right_1, ContextCompat.getColor(mContext, R.color.color_chart_statistics_1));
                        helper.setTextColor(R.id.tv_item_right_2, ContextCompat.getColor(mContext, R.color.color_chart_statistics_1));
                        helper.setTextColor(R.id.tv_item_right_3, ContextCompat.getColor(mContext, R.color.color_chart_statistics_1));
                        helper.setTextColor(R.id.tv_item_right_4, ContextCompat.getColor(mContext, R.color.color_chart_statistics_1));
                        helper.setTextColor(R.id.tv_item_right_5, ContextCompat.getColor(mContext, R.color.color_chart_statistics_1));
                        helper.setTextColor(R.id.tv_item_right_6, ContextCompat.getColor(mContext, R.color.color_chart_statistics_1));
                        helper.setTextColor(R.id.tv_item_right_7, ContextCompat.getColor(mContext, R.color.color_chart_statistics_1));
                        helper.setTextColor(R.id.tv_item_right_8, ContextCompat.getColor(mContext, R.color.color_chart_statistics_1));
                        helper.setTextColor(R.id.tv_item_right_9, ContextCompat.getColor(mContext, R.color.color_chart_statistics_1));
                    } else if (mCount % 4 == 1) {
                        helper.setTextColor(R.id.tv_item_right_0, ContextCompat.getColor(mContext, R.color.color_chart_statistics_2));
                        helper.setTextColor(R.id.tv_item_right_1, ContextCompat.getColor(mContext, R.color.color_chart_statistics_2));
                        helper.setTextColor(R.id.tv_item_right_2, ContextCompat.getColor(mContext, R.color.color_chart_statistics_2));
                        helper.setTextColor(R.id.tv_item_right_3, ContextCompat.getColor(mContext, R.color.color_chart_statistics_2));
                        helper.setTextColor(R.id.tv_item_right_4, ContextCompat.getColor(mContext, R.color.color_chart_statistics_2));
                        helper.setTextColor(R.id.tv_item_right_5, ContextCompat.getColor(mContext, R.color.color_chart_statistics_2));
                        helper.setTextColor(R.id.tv_item_right_6, ContextCompat.getColor(mContext, R.color.color_chart_statistics_2));
                        helper.setTextColor(R.id.tv_item_right_7, ContextCompat.getColor(mContext, R.color.color_chart_statistics_2));
                        helper.setTextColor(R.id.tv_item_right_8, ContextCompat.getColor(mContext, R.color.color_chart_statistics_2));
                        helper.setTextColor(R.id.tv_item_right_9, ContextCompat.getColor(mContext, R.color.color_chart_statistics_2));
                    } else if (mCount % 4 == 2) {
                        helper.setTextColor(R.id.tv_item_right_0, ContextCompat.getColor(mContext, R.color.color_chart_statistics_3));
                        helper.setTextColor(R.id.tv_item_right_1, ContextCompat.getColor(mContext, R.color.color_chart_statistics_3));
                        helper.setTextColor(R.id.tv_item_right_2, ContextCompat.getColor(mContext, R.color.color_chart_statistics_3));
                        helper.setTextColor(R.id.tv_item_right_3, ContextCompat.getColor(mContext, R.color.color_chart_statistics_3));
                        helper.setTextColor(R.id.tv_item_right_4, ContextCompat.getColor(mContext, R.color.color_chart_statistics_3));
                        helper.setTextColor(R.id.tv_item_right_5, ContextCompat.getColor(mContext, R.color.color_chart_statistics_3));
                        helper.setTextColor(R.id.tv_item_right_6, ContextCompat.getColor(mContext, R.color.color_chart_statistics_3));
                        helper.setTextColor(R.id.tv_item_right_7, ContextCompat.getColor(mContext, R.color.color_chart_statistics_3));
                        helper.setTextColor(R.id.tv_item_right_8, ContextCompat.getColor(mContext, R.color.color_chart_statistics_3));
                        helper.setTextColor(R.id.tv_item_right_9, ContextCompat.getColor(mContext, R.color.color_chart_statistics_3));
                    } else if (mCount % 4 == 3) {
                        helper.setTextColor(R.id.tv_item_right_0, ContextCompat.getColor(mContext, R.color.color_chart_statistics_4));
                        helper.setTextColor(R.id.tv_item_right_1, ContextCompat.getColor(mContext, R.color.color_chart_statistics_4));
                        helper.setTextColor(R.id.tv_item_right_2, ContextCompat.getColor(mContext, R.color.color_chart_statistics_4));
                        helper.setTextColor(R.id.tv_item_right_3, ContextCompat.getColor(mContext, R.color.color_chart_statistics_4));
                        helper.setTextColor(R.id.tv_item_right_4, ContextCompat.getColor(mContext, R.color.color_chart_statistics_4));
                        helper.setTextColor(R.id.tv_item_right_5, ContextCompat.getColor(mContext, R.color.color_chart_statistics_4));
                        helper.setTextColor(R.id.tv_item_right_6, ContextCompat.getColor(mContext, R.color.color_chart_statistics_4));
                        helper.setTextColor(R.id.tv_item_right_7, ContextCompat.getColor(mContext, R.color.color_chart_statistics_4));
                        helper.setTextColor(R.id.tv_item_right_8, ContextCompat.getColor(mContext, R.color.color_chart_statistics_4));
                        helper.setTextColor(R.id.tv_item_right_9, ContextCompat.getColor(mContext, R.color.color_chart_statistics_4));
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
            addItemType(ITEM_TYPE_CHART, R.layout.list_item_chart_trend_pk10_left_view);
            addItemType(ITEM_TYPE_STATISTIC, R.layout.list_item_chart_trend_pk10_left_view);
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
                    helper.setText(R.id.item_left_tv, chartDataBean.getExpect());
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
            helper.itemView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
        } else {
            helper.itemView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.color_chart_item_bg));
        }
    }


}
