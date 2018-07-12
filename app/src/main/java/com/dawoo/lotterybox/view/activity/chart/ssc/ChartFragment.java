package com.dawoo.lotterybox.view.activity.chart.ssc;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.view.activity.chart.ChartBean;
import com.dawoo.lotterybox.view.activity.chart.LineRecyclerView;
import com.dawoo.lotterybox.view.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class ChartFragment extends BaseFragment {

    Unbinder unbinder;
    @BindView(R.id.left_title_tv)
    TextView mLeftTitleTv;
    @BindView(R.id.left_recycler_view)
    RecyclerView mLeftRecyclerView;
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
    @BindView(R.id.right_recycler_view)
    LineRecyclerView mRightRecyclerView;
    private LeftAdapter mLeftAdapter;
    private RightAdapter mRightAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chart, container, false);
        unbinder = ButterKnife.bind(this, view);
        initViews();
        initData();
        return view;
    }

    private void initViews() {
        mLeftTitleTv.setText("期号");
     //   mRightTitleTv.setText("         0        ,          1         ,         2          ,          3         ,        4        ,       5        ,       6      ,     7      ,      8      ,      9        ");

        mLeftAdapter = new LeftAdapter(R.layout.list_item_chart_left_view);
        mLeftRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mLeftRecyclerView.setAdapter(mLeftAdapter);


        mRightAdapter = new RightAdapter(R.layout.list_item_chart_right_view);
        mRightRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRightRecyclerView.setAdapter(mRightAdapter);

        syncScroll(mLeftRecyclerView, mRightRecyclerView);
    }

    private void syncScroll(RecyclerView leftList, RecyclerView rightList) {
        leftList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (recyclerView.getScrollState() != RecyclerView.SCROLL_STATE_IDLE) {
                    // note: scrollBy() not trigger OnScrollListener
                    // This is a known issue. It is caused by the fact that RecyclerView does not know how LayoutManager will handle the scroll or if it will handle it at all.
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
        List<ChartBean> data = getData();
        mLeftAdapter.setNewData(data);
        mRightAdapter.setNewData(data);

        // 画图
        List<Integer> integers = new ArrayList<>();
        integers.add(3);
        integers.add(2);
        integers.add(5);
        integers.add(8);
        integers.add(6);
        integers.add(2);
        integers.add(7);
        integers.add(5);
        integers.add(8);
        integers.add(3);

        integers.add(3);
        integers.add(2);
        integers.add(5);
        integers.add(8);
        integers.add(6);
        integers.add(2);
        integers.add(7);
        integers.add(5);
        integers.add(8);
        integers.add(3);

        integers.add(3);
        integers.add(2);
        integers.add(5);
        integers.add(8);
        integers.add(6);
        integers.add(2);
        integers.add(7);
        integers.add(5);
        integers.add(8);
        integers.add(3);


        mRightRecyclerView.setList(integers);

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

    List<ChartBean> getData() {
        List<ChartBean> chartBeans = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            chartBeans.add(new ChartBean((2000 + i) + "期", "", "2,5,6,7,3,4,3,4,3"));
        }
        return chartBeans;
    }


    class LeftAdapter extends BaseQuickAdapter {

        public LeftAdapter(int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(BaseViewHolder helper, Object item) {
            if (item != null && item instanceof ChartBean) {
                ChartBean chartBean = (ChartBean) item;
                int position = helper.getAdapterPosition();
                if (position % 2 == 0) {

                } else {

                }
                helper.setText(R.id.item_left_tv, chartBean.getExpect());

            }
        }
    }


    class RightAdapter extends BaseQuickAdapter {

        public RightAdapter(int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(BaseViewHolder helper, Object item) {
            if (item != null && item instanceof ChartBean) {
                ChartBean chartBean = (ChartBean) item;
                String[] arr = chartBean.getCodeNum().split(",");
                helper.setText(R.id.tv_item_right_1, arr[0]);
                helper.setText(R.id.tv_item_right_2, arr[1]);
                helper.setText(R.id.tv_item_right_3, arr[2]);
                helper.setText(R.id.tv_item_right_4, arr[3]);
                helper.setText(R.id.tv_item_right_5, arr[4]);
                helper.setText(R.id.tv_item_right_6, arr[5]);
                helper.setText(R.id.tv_item_right_7, arr[6]);
                helper.setText(R.id.tv_item_right_8, arr[7]);
                helper.setText(R.id.tv_item_right_9, arr[8]);
            }
        }
    }


}
