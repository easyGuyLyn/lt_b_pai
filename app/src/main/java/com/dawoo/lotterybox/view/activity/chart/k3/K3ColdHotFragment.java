package com.dawoo.lotterybox.view.activity.chart.k3;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
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
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by b on 18-4-26.
 * k3冷热
 */

public class K3ColdHotFragment extends BaseFragment {

    @BindView(R.id.rlv_cold_hot_trent)
    RecyclerView mRlvColdHotTrent;

    private static final String ARG_PARAM1 = "param1";
    private ArrayList<ChartDataBean> mBasicTRentBeans = new ArrayList<>();
    private Unbinder mUnbinder;
    private ColdHotQuickAdapter mQuickAdapter;
    private View mRightFooterView;
    private Handler mHandler;
    protected ArrayList<Handicap> mRencentList;

    public static K3ColdHotFragment newInstance(ArrayList<Handicap> handicaps) {
        K3ColdHotFragment fragment = new K3ColdHotFragment();
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
        View view = inflater.inflate(R.layout.fragment_k3_cold_hot, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        initViews();
        return view;
    }

    private void initViews() {
        mQuickAdapter = new ColdHotQuickAdapter(R.layout.layout_k3_cold_hot_trend);
//        mQuickAdapter.setEmptyView(LayoutInflater.from(mContext).inflate(R.layout.empty_view, null));
        mRlvColdHotTrent.setLayoutManager(new LinearLayoutManager(mContext));
        mRlvColdHotTrent.setAdapter(mQuickAdapter);
        setScroll();
    }

    private void setScroll() {
        RxBus.get().register(this);
        mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mRencentList != null) {
                    mRlvColdHotTrent.scrollToPosition(mRencentList.size() - 1);
                }
            }
        }, 200);
    }


    @Override
    protected void loadData() {
        mQuickAdapter.setNewData(mBasicTRentBeans);
    }

    private void convertData(ArrayList<Handicap> mHandicaps) {
        for (int i = 0; i < 16; i++) {
            ChartDataBean chartDataBean = new ChartDataBean();
            chartDataBean.setExpect(String.valueOf(i + 3));
            List<Integer> rowList = new ArrayList<>();
            for (int j = 0; j < 4; j++) {
                rowList.add(0);
            }
            chartDataBean.setRowList(rowList);
            mBasicTRentBeans.add(chartDataBean);
        }
        if (mHandicaps.size() >= 30)
            computeByNo(30, mHandicaps);
        if (mHandicaps.size() >= 50)
            computeByNo(50, mHandicaps);
        if (mHandicaps.size() >= 100)
            computeByNo(100, mHandicaps);
    }

    private void computeByNo(int which, ArrayList<Handicap> mHandicaps) {
        for (int i = 0; i < which; i++) {
            String[] openCode = mHandicaps.get(i).getOpenCode().split(",");
            int sumNum = Integer.valueOf(openCode[0]) + Integer.valueOf(openCode[1]) + Integer.valueOf(openCode[2]);
            ChartDataBean chartDataBean = mBasicTRentBeans.get(sumNum - 3);
            if (which == 30)
                chartDataBean.getRowList().set(0, chartDataBean.getRowList().get(0) + 1);
            else if (which == 50) {
                chartDataBean.getRowList().set(1, chartDataBean.getRowList().get(1) + 1);
            } else if (which == 100) {
                chartDataBean.getRowList().set(2, chartDataBean.getRowList().get(2) + 1);
                if (chartDataBean.getRowList().get(3) < 1000) {
                    chartDataBean.getRowList().set(3, chartDataBean.getRowList().get(3) + 1000);
                }
                for (int j = 0; j < mBasicTRentBeans.size(); j++) {
                    if (j + 3 != sumNum) {
                        ChartDataBean chartDataBean2 = mBasicTRentBeans.get(j);
                        if (chartDataBean2.getRowList().get(3) < 1000) {
                            chartDataBean2.getRowList().set(3, chartDataBean2.getRowList().get(3) + 1);
                        }
                    }
                }
            }
        }
    }

    class ColdHotQuickAdapter extends BaseQuickAdapter {
        public ColdHotQuickAdapter(int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(BaseViewHolder helper, Object item) {
            ChartDataBean chartDataBean = (ChartDataBean) item;
            List<Integer> rowList = chartDataBean.getRowList();
            setItemBg(helper);
            helper.setText(R.id.tv_number, chartDataBean.getExpect());
            helper.setText(R.id.tv_which_30, String.valueOf(rowList.get(0)));
            helper.setTextColor(R.id.tv_which_30, getResources().getColor(R.color.open_item_text2));
            helper.setText(R.id.tv_which_50, String.valueOf(rowList.get(1)));
            helper.setTextColor(R.id.tv_which_50, getResources().getColor(R.color.open_item_text2));
            helper.setText(R.id.tv_which_100, String.valueOf(rowList.get(2)));
            helper.setTextColor(R.id.tv_which_100, getResources().getColor(R.color.open_item_text2));
            helper.setText(R.id.tv_cold, String.valueOf(rowList.get(3) % 1000));
            helper.setTextColor(R.id.tv_cold, getResources().getColor(R.color.open_item_text2));
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
    }


    @Override
    public void onDestroy() {
        mRightFooterView = null;
        RxBus.get().unregister(this);
        super.onDestroy();
        mUnbinder.unbind();
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
            mQuickAdapter.addFooterView(mRightFooterView);

            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (mRencentList != null) {
                        mRlvColdHotTrent.scrollToPosition(mRencentList.size());
                    }
                }
            }, 200);
        } else {
            mRencentList = ((K3TrendChartActivity) mContext).getmRencentList();
            if (mRencentList != null) {
                mBasicTRentBeans.clear();
                convertData(mRencentList);
            }

            mQuickAdapter.removeFooterView(mRightFooterView);
            mQuickAdapter.notifyDataSetChanged();
        }
    }
}
