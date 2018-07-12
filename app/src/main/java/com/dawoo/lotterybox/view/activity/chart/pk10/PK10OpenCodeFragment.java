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
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dawoo.lotterybox.ConstantValue;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.bean.lottery.Handicap;
import com.dawoo.lotterybox.view.fragment.BaseFragment;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by b on 18-5-1.
 * 走势图  开奖
 */

public class PK10OpenCodeFragment extends BaseFragment {

    private static final String ARG_PARAM1 = "param1";
    @BindView(R.id.rlv_stage_number)
    RecyclerView mRlvStageNumber;
    @BindView(R.id.right_recycler_view)
    RecyclerView mRightRecyclerView;

    private ArrayList<Handicap> mChartDataBeans;
    private Unbinder mUnbinder;
    private LeftAdapter mLeftAdapter;
    private RightAdapter mRightAdapter;
    private View mLeftFooterView;
    private View mRightFooterView;
    private Handler mHandler;

    public static PK10OpenCodeFragment newInstance(ArrayList<Handicap> chartDataBeans) {
        PK10OpenCodeFragment fragment = new PK10OpenCodeFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_PARAM1, chartDataBeans);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mChartDataBeans = getArguments().getParcelableArrayList(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pk10_open_code, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        initViews();
        return view;
    }

    private void initViews() {

        mLeftAdapter = new LeftAdapter(R.layout.list_item_chart_trend_pk10_left_view);
        mRlvStageNumber.setLayoutManager(new LinearLayoutManager(mContext));
        mRlvStageNumber.setAdapter(mLeftAdapter);

        mRightAdapter = new RightAdapter(R.layout.list_item_open_code_pk10_right_title_view);
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
        mLeftAdapter.setNewData(mChartDataBeans);
        mRightAdapter.setNewData(mChartDataBeans);
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
    class RightAdapter extends BaseQuickAdapter {

        public RightAdapter(int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(BaseViewHolder helper, Object item) {
            if (item == null) {
                return;
            }
            Handicap chartDataBean = (Handicap) item;
            int[] ids = {R.id.tv_item_right_0, R.id.tv_item_right_1, R.id.tv_item_right_2,
                    R.id.tv_item_right_3, R.id.tv_item_right_4, R.id.tv_item_right_5,
                    R.id.tv_item_right_6, R.id.tv_item_right_7, R.id.tv_item_right_8, R.id.tv_item_right_9};
            setItemBg(helper);
            String opcode = chartDataBean.getOpenCode();
            String[] rowList = opcode.split(",");
            for (int i = 0; i < rowList.length; i++) {
                helper.setText(ids[i], rowList[i]);
            }
        }

    }

    class LeftAdapter extends BaseQuickAdapter {

        public LeftAdapter(int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(BaseViewHolder helper, Object item) {
            if (item == null) {
                return;
            }
            setItemBg(helper);
            Handicap bean = (Handicap) item;
//            helper.setText(R.id.item_left_tv, chartDataBean.getExpect());

            if (TextUtils.isEmpty(bean.getExpect())) {
                return;
            }
            if (bean.getExpect().length() - 3 > 0) {
                helper.setText(R.id.item_left_tv, bean.getExpect().substring(bean.getExpect().length() - 3) + "期");
            } else {
                helper.setText(R.id.item_left_tv, bean.getExpect() + "期");
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
            mLeftAdapter.removeFooterView(mLeftFooterView);
            mRightAdapter.removeFooterView(mRightFooterView);

            mLeftAdapter.notifyDataSetChanged();
            mRightAdapter.notifyDataSetChanged();
        }
    }

}
