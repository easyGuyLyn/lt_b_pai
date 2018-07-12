package com.dawoo.lotterybox.view.activity.chart.pk10;

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

public class TrendChartPK10Fragment extends BaseFragment {
    private static final String ARG_PARAM0 = "param0";
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.rlv_stage_number)
    RecyclerView mRlvStageNumber;
    @BindView(R.id.right_recycler_view)
    ThreeLineRecyclerView mRightRecyclerView;
    private Unbinder mUnbinder;
    private LeftAdapter mLeftAdapter;
    private ArrayList<ChartDataBean> mChartDataBeans;
    private ArrayList<LinesBean> mLinesBeans;
    private RightAdapter mRightAdapter;
    private View mLeftFooterView;
    private View mRightFooterView;
    private Handler mHandler;
    private int mIndex;

    public static TrendChartPK10Fragment newInstance(int index, ArrayList<ChartDataBean> chartDataBeans, ArrayList<LinesBean> linesBeans) {
        TrendChartPK10Fragment fragment = new TrendChartPK10Fragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM0, index);
        args.putParcelableArrayList(ARG_PARAM1, chartDataBeans);
        args.putParcelableArrayList(ARG_PARAM2, linesBeans);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mIndex = getArguments().getInt(ARG_PARAM0);
            mChartDataBeans = getArguments().getParcelableArrayList(ARG_PARAM1);
            mLinesBeans = getArguments().getParcelableArrayList(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pk10_trend_chart, container, false);
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

    private void initData() {
        mLeftAdapter.setNewData(mChartDataBeans);
        mRightAdapter.setNewData(mChartDataBeans);
        mRightRecyclerView.setList(mLinesBeans);
    }

    private void setScroll() {
        RxBus.get().register(this);
        mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mChartDataBeans != null) {
                    mRlvStageNumber.scrollToPosition(mChartDataBeans.size() - 1);
                    mRightRecyclerView.scrollToPosition(mChartDataBeans.size() - 1);
                }
            }
        }, 200);
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


    @Override
    protected void loadData() {
        initData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mLeftFooterView = null;
        mRightFooterView = null;
        RxBus.get().unregister(this);
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
            addItemType(ITEM_TYPE_CHART, R.layout.list_item_chart_trent_pk10_right_title_view);
            addItemType(ITEM_TYPE_STATISTIC, R.layout.list_item_chart_trent_pk10_right_title_view);
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
                        } else {
                            if (i == 10) {
                                if (num < 1000) {
                                    helper.setText(ids[i], String.valueOf(rowList.get(i)));
                                    helper.setBackgroundRes(ids[i], R.color.transparent);
                                } else {
                                    helper.setText(ids[i], "大");
                                    helper.setBackgroundRes(ids[i], R.drawable.shape_big_small);
                                }
                            } else if (i == 11) {
                                if (num < 1000) {
                                    helper.setBackgroundRes(ids[i], R.color.transparent);
                                    helper.setText(ids[i], String.valueOf(rowList.get(i)));
                                } else {
                                    helper.setText(ids[i], "小");
                                    helper.setBackgroundRes(ids[i], R.drawable.shape_big_small);
                                }
                            } else if (i == 12) {
                                if (num < 1000) {
                                    helper.setBackgroundRes(ids[i], R.color.transparent);
                                    helper.setText(ids[i], String.valueOf(rowList.get(i)));
                                } else {
                                    helper.setText(ids[i], "单");
                                    helper.setBackgroundRes(ids[i], R.drawable.shape_big_small);
                                }
                            } else if (i == 13) {
                                if (num < 1000) {
                                    helper.setBackgroundRes(ids[i], R.color.transparent);
                                    helper.setText(ids[i], String.valueOf(rowList.get(i)));
                                } else {
                                    helper.setText(ids[i], "双");
                                    helper.setBackgroundRes(ids[i], R.drawable.shape_big_small);
                                }
                            }
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
            addItemType(ITEM_TYPE_CHART, R.layout.list_item_chart_trend_pk10_left_view);
            addItemType(ITEM_TYPE_STATISTIC, R.layout.list_item_chart_trend_pk10_left_view);
        }

        @Override
        protected void convert(BaseViewHolder helper, Object item) {
            if (item == null) {
                return;
            }
            setItemBg(helper);
            ChartDataBean bean = (ChartDataBean) item;
            switch (helper.getItemViewType()) {
                case ITEM_TYPE_CHART:
//                    helper.setText(R.id.item_left_tv, chartDataBean.getExpect());

                    if (TextUtils.isEmpty(bean.getExpect())) {
                        return;
                    }
                    if (bean.getExpect().length() - 3 > 0) {
                        helper.setText(R.id.item_left_tv, bean.getExpect().substring(bean.getExpect().length() - 3) + "期");
                    } else {
                        helper.setText(R.id.item_left_tv, bean.getExpect() + "期");
                    }
                    break;

                case ITEM_TYPE_STATISTIC:
                    helper.setText(R.id.item_left_tv, bean.getExpect());
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

    /**
     * 控制开奖footer
     */
    @Subscribe(tags = {@Tag(ConstantValue.EVENT_TYPE_OPENING_LOTTERY)})
    public void toggleOpenFooter(String s) {
        if (mChartDataBeans == null) {
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
                    if (mChartDataBeans != null) {
                        mRlvStageNumber.scrollToPosition(mChartDataBeans.size());
                        mRightRecyclerView.scrollToPosition(mChartDataBeans.size());
                    }
                }
            }, 200);
        } else {
            Pk10EventOB eventOB = ((PK10TrendChartActivity) mContext).getPK10EventOB();
            if (eventOB != null && eventOB.getData() != null && eventOB.getLinesData() != null) {
                mChartDataBeans.clear();
                mLinesBeans.clear();
                mChartDataBeans.addAll(eventOB.getData().get(mIndex));
                mLinesBeans.addAll(eventOB.getLinesData().get(mIndex));
            }


            mLeftAdapter.removeFooterView(mLeftFooterView);
            mRightAdapter.removeFooterView(mRightFooterView);
            mLeftAdapter.notifyDataSetChanged();
            mRightAdapter.notifyDataSetChanged();
        }
    }

}
