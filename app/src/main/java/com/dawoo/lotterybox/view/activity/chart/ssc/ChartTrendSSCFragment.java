package com.dawoo.lotterybox.view.activity.chart.ssc;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dawoo.lotterybox.ConstantValue;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.bean.playtype.PlayTypeBean;
import com.dawoo.lotterybox.view.activity.chart.LineRecyclerView;
import com.dawoo.lotterybox.view.activity.chart.pk10.PK10TrendChartActivity;
import com.dawoo.lotterybox.view.activity.chart.pk10.Pk10EventOB;
import com.dawoo.lotterybox.view.fragment.BaseFragment;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class ChartTrendSSCFragment extends BaseFragment {
    private static final String ARG_PARAM0 = "param0";
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final int ITEM_TYPE_CHART = 1;
    public static final int ITEM_TYPE_STATISTIC = 2;

    Unbinder unbinder;
    @BindView(R.id.right_recycler_view)
    LineRecyclerView mRightRecyclerView;
    @BindView(R.id.left_title_tv)
    TextView mLeftTitleTv;
    @BindView(R.id.left_recycler_view)
    RecyclerView mLeftRecyclerView;
    @BindView(R.id.tv_item_right_0)
    TextView mTvItemRight0;
    @BindView(R.id.tv_item_right_1)
    TextView mTvItemRight1;
    @BindView(R.id.tv_item_right_2)
    TextView mTvItemRight2;
    @BindView(R.id.tv_item_right_3)
    TextView mTvItemRight3;
    @BindView(R.id.tv_item_right_4)
    TextView mTvItemRight4;
    @BindView(R.id.tv_item_right_5)
    TextView mTvItemRight5;
    @BindView(R.id.tv_item_right_6)
    TextView mTvItemRight6;
    @BindView(R.id.tv_item_right_7)
    TextView mTvItemRight7;
    @BindView(R.id.tv_item_right_8)
    TextView mTvItemRight8;
    @BindView(R.id.tv_item_right_9)
    TextView mTvItemRight9;
    private LeftAdapter mLeftAdapter;
    private RightAdapter mRightAdapter;
    ArrayList<SSCStatistics> mSscStatisticsList;
    PlayTypeBean.PlayBean mPlayBean;
    List<Integer> mIntegers;
    private View mLeftFooterView;
    private View mRightFooterView;
    private Handler mHandler;
    private int mIndex;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mIndex = getArguments().getInt(ARG_PARAM0);
            mSscStatisticsList = getArguments().getParcelableArrayList(ARG_PARAM1);
            mPlayBean = getArguments().getParcelable(ARG_PARAM2);
        }
    }

    @Override
    public void onDestroyView() {
        mLeftFooterView = null;
        mRightFooterView = null;
        RxBus.get().unregister(this);
        super.onDestroyView();
        unbinder.unbind();
    }

    public static ChartTrendSSCFragment newInstance(int index, ArrayList<SSCStatistics> sscStatisticsList, PlayTypeBean.PlayBean playBean) {
        ChartTrendSSCFragment fragment = new ChartTrendSSCFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM0, index);
        args.putParcelableArrayList(ARG_PARAM1, sscStatisticsList);
        args.putParcelable(ARG_PARAM2, playBean);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chart_trend_ssc, container, false);
        unbinder = ButterKnife.bind(this, view);
        initViews();
        initData();
        return view;
    }

    private void initViews() {
        mLeftTitleTv.setText("期号");

        mLeftAdapter = new LeftAdapter(new ArrayList());
        mLeftRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mLeftRecyclerView.setAdapter(mLeftAdapter);


        mRightAdapter = new RightAdapter(new ArrayList());
        mRightRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRightRecyclerView.setAdapter(mRightAdapter);

        syncScroll(mLeftRecyclerView, mRightRecyclerView);
        setScroll();
    }

    private void setScroll() {
        RxBus.get().register(this);
        mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mSscStatisticsList != null) {
                    mLeftRecyclerView.scrollToPosition(mSscStatisticsList.size() - 1);
                    mRightRecyclerView.scrollToPosition(mSscStatisticsList.size() - 1);
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

    private void initData() {
        if (mSscStatisticsList != null) {
            mLeftAdapter.setNewData(mSscStatisticsList);
            mRightAdapter.setNewData(mSscStatisticsList);

             mIntegers = new ArrayList<>();

            for (int i = 0; i < mSscStatisticsList.size() - 4; i++) {
                mIntegers.add(mSscStatisticsList.get(i).getRowList().get(10));
            }
            mRightRecyclerView.setList(mIntegers);
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    protected void loadData() {

    }


    class LeftAdapter extends BaseMultiItemQuickAdapter {
        int mCount = 0;

        public LeftAdapter(List data) {
            super(data);
            addItemType(ITEM_TYPE_CHART, R.layout.list_item_chart_trend_ssc_left_view);
            addItemType(ITEM_TYPE_STATISTIC, R.layout.list_item_chart_trend_ssc_left_view_statistics);
        }

        @Override
        protected void convert(BaseViewHolder helper, Object item) {
            if (item == null) {
                return;
            }
            setItemBg(helper);
            SSCStatistics sscStatistics = (SSCStatistics) item;
            switch (helper.getItemViewType()) {
                case ITEM_TYPE_CHART:
                    if (TextUtils.isEmpty(sscStatistics.getExpect())) {
                        return;
                    }
                    if (sscStatistics.getExpect().length() - 3 > 0) {
                        helper.setText(R.id.item_left_tv, sscStatistics.getExpect().substring(sscStatistics.getExpect().length() - 3) + "期");
                    } else {
                        helper.setText(R.id.item_left_tv, sscStatistics.getExpect() + "期");
                    }
                    break;

                case ITEM_TYPE_STATISTIC:
                    helper.setText(R.id.item_left_tv, sscStatistics.getExpect());
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
         * 列表
         * 统计
         */
        class RightAdapter extends BaseMultiItemQuickAdapter {
            int mCount = 0;

            public RightAdapter(List data) {
                super(data);
                addItemType(ITEM_TYPE_CHART, R.layout.list_item_chart_trend_ssc_right_view);
                addItemType(ITEM_TYPE_STATISTIC, R.layout.list_item_chart_trend_ssc_right_view_statistics);
            }

            @Override
            protected void convert(BaseViewHolder helper, Object item) {
                if (item == null) {
                    return;
                }
                SSCStatistics sSCStatistics = (SSCStatistics) item;
                setItemBg(helper);
                switch (helper.getItemViewType()) {
                    case ITEM_TYPE_CHART:

                        List<Integer> rowList = sSCStatistics.getRowList();
                        int col10 = rowList.get(10);
                        setCircleBg(helper, col10);
                        helper.setText(R.id.tv_item_right_0, String.valueOf(rowList.get(0)));
                        helper.setText(R.id.tv_item_right_1, String.valueOf(rowList.get(1)));
                        helper.setText(R.id.tv_item_right_2, String.valueOf(rowList.get(2)));
                        helper.setText(R.id.tv_item_right_3, String.valueOf(rowList.get(3)));
                        helper.setText(R.id.tv_item_right_4, String.valueOf(rowList.get(4)));
                        helper.setText(R.id.tv_item_right_5, String.valueOf(rowList.get(5)));
                        helper.setText(R.id.tv_item_right_6, String.valueOf(rowList.get(6)));
                        helper.setText(R.id.tv_item_right_7, String.valueOf(rowList.get(7)));
                        helper.setText(R.id.tv_item_right_8, String.valueOf(rowList.get(8)));
                        helper.setText(R.id.tv_item_right_9, String.valueOf(rowList.get(9)));
                        break;

                    case ITEM_TYPE_STATISTIC:
                        List<Integer> statisticsList = sSCStatistics.getStatistics();
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


        void setCircleBg(BaseViewHolder helper, int index) {
            switch (index) {
                case 0:
                    helper.setBackgroundRes(R.id.tv_item_right_0, R.drawable.shape_ball_yellow);
                    helper.setBackgroundRes(R.id.tv_item_right_1, R.color.transparent);
                    helper.setBackgroundRes(R.id.tv_item_right_2, R.color.transparent);
                    helper.setBackgroundRes(R.id.tv_item_right_3, R.color.transparent);
                    helper.setBackgroundRes(R.id.tv_item_right_4, R.color.transparent);
                    helper.setBackgroundRes(R.id.tv_item_right_5, R.color.transparent);
                    helper.setBackgroundRes(R.id.tv_item_right_6, R.color.transparent);
                    helper.setBackgroundRes(R.id.tv_item_right_7, R.color.transparent);
                    helper.setBackgroundRes(R.id.tv_item_right_8, R.color.transparent);
                    helper.setBackgroundRes(R.id.tv_item_right_9, R.color.transparent);
                    break;
                case 1:
                    helper.setBackgroundRes(R.id.tv_item_right_0, R.color.transparent);
                    helper.setBackgroundRes(R.id.tv_item_right_1, R.drawable.shape_ball_yellow);
                    helper.setBackgroundRes(R.id.tv_item_right_2, R.color.transparent);
                    helper.setBackgroundRes(R.id.tv_item_right_3, R.color.transparent);
                    helper.setBackgroundRes(R.id.tv_item_right_4, R.color.transparent);
                    helper.setBackgroundRes(R.id.tv_item_right_5, R.color.transparent);
                    helper.setBackgroundRes(R.id.tv_item_right_6, R.color.transparent);
                    helper.setBackgroundRes(R.id.tv_item_right_7, R.color.transparent);
                    helper.setBackgroundRes(R.id.tv_item_right_8, R.color.transparent);
                    helper.setBackgroundRes(R.id.tv_item_right_9, R.color.transparent);
                    break;
                case 2:
                    helper.setBackgroundRes(R.id.tv_item_right_0, R.color.transparent);
                    helper.setBackgroundRes(R.id.tv_item_right_1, R.color.transparent);
                    helper.setBackgroundRes(R.id.tv_item_right_2, R.drawable.shape_ball_yellow);
                    helper.setBackgroundRes(R.id.tv_item_right_3, R.color.transparent);
                    helper.setBackgroundRes(R.id.tv_item_right_4, R.color.transparent);
                    helper.setBackgroundRes(R.id.tv_item_right_5, R.color.transparent);
                    helper.setBackgroundRes(R.id.tv_item_right_6, R.color.transparent);
                    helper.setBackgroundRes(R.id.tv_item_right_7, R.color.transparent);
                    helper.setBackgroundRes(R.id.tv_item_right_8, R.color.transparent);
                    helper.setBackgroundRes(R.id.tv_item_right_9, R.color.transparent);
                    break;
                case 3:
                    helper.setBackgroundRes(R.id.tv_item_right_0, R.color.transparent);
                    helper.setBackgroundRes(R.id.tv_item_right_1, R.color.transparent);
                    helper.setBackgroundRes(R.id.tv_item_right_2, R.color.transparent);
                    helper.setBackgroundRes(R.id.tv_item_right_3, R.drawable.shape_ball_yellow);
                    helper.setBackgroundRes(R.id.tv_item_right_4, R.color.transparent);
                    helper.setBackgroundRes(R.id.tv_item_right_5, R.color.transparent);
                    helper.setBackgroundRes(R.id.tv_item_right_6, R.color.transparent);
                    helper.setBackgroundRes(R.id.tv_item_right_7, R.color.transparent);
                    helper.setBackgroundRes(R.id.tv_item_right_8, R.color.transparent);
                    helper.setBackgroundRes(R.id.tv_item_right_9, R.color.transparent);
                    break;
                case 4:
                    helper.setBackgroundRes(R.id.tv_item_right_0, R.color.transparent);
                    helper.setBackgroundRes(R.id.tv_item_right_1, R.color.transparent);
                    helper.setBackgroundRes(R.id.tv_item_right_2, R.color.transparent);
                    helper.setBackgroundRes(R.id.tv_item_right_3, R.color.transparent);
                    helper.setBackgroundRes(R.id.tv_item_right_4, R.drawable.shape_ball_yellow);
                    helper.setBackgroundRes(R.id.tv_item_right_5, R.color.transparent);
                    helper.setBackgroundRes(R.id.tv_item_right_6, R.color.transparent);
                    helper.setBackgroundRes(R.id.tv_item_right_7, R.color.transparent);
                    helper.setBackgroundRes(R.id.tv_item_right_8, R.color.transparent);
                    helper.setBackgroundRes(R.id.tv_item_right_9, R.color.transparent);
                    break;
                case 5:
                    helper.setBackgroundRes(R.id.tv_item_right_0, R.color.transparent);
                    helper.setBackgroundRes(R.id.tv_item_right_1, R.color.transparent);
                    helper.setBackgroundRes(R.id.tv_item_right_2, R.color.transparent);
                    helper.setBackgroundRes(R.id.tv_item_right_3, R.color.transparent);
                    helper.setBackgroundRes(R.id.tv_item_right_4, R.color.transparent);
                    helper.setBackgroundRes(R.id.tv_item_right_5, R.drawable.shape_ball_yellow);
                    helper.setBackgroundRes(R.id.tv_item_right_6, R.color.transparent);
                    helper.setBackgroundRes(R.id.tv_item_right_7, R.color.transparent);
                    helper.setBackgroundRes(R.id.tv_item_right_8, R.color.transparent);
                    helper.setBackgroundRes(R.id.tv_item_right_9, R.color.transparent);
                    break;
                case 6:
                    helper.setBackgroundRes(R.id.tv_item_right_0, R.color.transparent);
                    helper.setBackgroundRes(R.id.tv_item_right_1, R.color.transparent);
                    helper.setBackgroundRes(R.id.tv_item_right_2, R.color.transparent);
                    helper.setBackgroundRes(R.id.tv_item_right_3, R.color.transparent);
                    helper.setBackgroundRes(R.id.tv_item_right_4, R.color.transparent);
                    helper.setBackgroundRes(R.id.tv_item_right_5, R.color.transparent);
                    helper.setBackgroundRes(R.id.tv_item_right_6, R.drawable.shape_ball_yellow);
                    helper.setBackgroundRes(R.id.tv_item_right_7, R.color.transparent);
                    helper.setBackgroundRes(R.id.tv_item_right_8, R.color.transparent);
                    helper.setBackgroundRes(R.id.tv_item_right_9, R.color.transparent);
                    break;
                case 7:
                    helper.setBackgroundRes(R.id.tv_item_right_0, R.color.transparent);
                    helper.setBackgroundRes(R.id.tv_item_right_1, R.color.transparent);
                    helper.setBackgroundRes(R.id.tv_item_right_2, R.color.transparent);
                    helper.setBackgroundRes(R.id.tv_item_right_3, R.color.transparent);
                    helper.setBackgroundRes(R.id.tv_item_right_4, R.color.transparent);
                    helper.setBackgroundRes(R.id.tv_item_right_5, R.color.transparent);
                    helper.setBackgroundRes(R.id.tv_item_right_6, R.color.transparent);
                    helper.setBackgroundRes(R.id.tv_item_right_7, R.drawable.shape_ball_yellow);
                    helper.setBackgroundRes(R.id.tv_item_right_8, R.color.transparent);
                    helper.setBackgroundRes(R.id.tv_item_right_9, R.color.transparent);
                    break;
                case 8:
                    helper.setBackgroundRes(R.id.tv_item_right_0, R.color.transparent);
                    helper.setBackgroundRes(R.id.tv_item_right_1, R.color.transparent);
                    helper.setBackgroundRes(R.id.tv_item_right_2, R.color.transparent);
                    helper.setBackgroundRes(R.id.tv_item_right_3, R.color.transparent);
                    helper.setBackgroundRes(R.id.tv_item_right_4, R.color.transparent);
                    helper.setBackgroundRes(R.id.tv_item_right_5, R.color.transparent);
                    helper.setBackgroundRes(R.id.tv_item_right_6, R.color.transparent);
                    helper.setBackgroundRes(R.id.tv_item_right_7, R.color.transparent);
                    helper.setBackgroundRes(R.id.tv_item_right_8, R.drawable.shape_ball_yellow);
                    helper.setBackgroundRes(R.id.tv_item_right_9, R.color.transparent);
                    break;
                case 9:
                    helper.setBackgroundRes(R.id.tv_item_right_0, R.color.transparent);
                    helper.setBackgroundRes(R.id.tv_item_right_1, R.color.transparent);
                    helper.setBackgroundRes(R.id.tv_item_right_2, R.color.transparent);
                    helper.setBackgroundRes(R.id.tv_item_right_3, R.color.transparent);
                    helper.setBackgroundRes(R.id.tv_item_right_4, R.color.transparent);
                    helper.setBackgroundRes(R.id.tv_item_right_5, R.color.transparent);
                    helper.setBackgroundRes(R.id.tv_item_right_6, R.color.transparent);
                    helper.setBackgroundRes(R.id.tv_item_right_7, R.color.transparent);
                    helper.setBackgroundRes(R.id.tv_item_right_8, R.color.transparent);
                    helper.setBackgroundRes(R.id.tv_item_right_9, R.drawable.shape_ball_yellow);
                    break;
            }
        }

    /**
     * 控制开奖footer
     */
    @Subscribe(tags = {@Tag(ConstantValue.EVENT_TYPE_OPENING_LOTTERY)})
    public void toggleOpenFooter(String s) {
        if (mSscStatisticsList == null) {
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
                    if (mSscStatisticsList != null) {
                        mLeftRecyclerView.scrollToPosition(mSscStatisticsList.size());
                        mRightRecyclerView.scrollToPosition(mSscStatisticsList.size());
                    }
                }
            }, 200);
        } else {
            ArrayList<ArrayList<SSCStatistics>> eventOB = ((ChartSSCActivity) mContext).getmSscStatisticsListList();
            if (eventOB != null ) {
                mSscStatisticsList.clear();
                mSscStatisticsList.addAll(eventOB.get(mIndex));

                mIntegers.clear();
                for (int i = 0; i < mSscStatisticsList.size() - 4; i++) {
                    mIntegers.add(mSscStatisticsList.get(i).getRowList().get(10));
                }
            }


            mLeftAdapter.removeFooterView(mLeftFooterView);
            mRightAdapter.removeFooterView(mRightFooterView);
            mLeftAdapter.notifyDataSetChanged();
            mRightAdapter.notifyDataSetChanged();
        }
    }


    }
