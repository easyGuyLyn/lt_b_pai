package com.dawoo.lotterybox.view.activity.chart.k3;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dawoo.coretool.util.LogUtils;
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
 * Created by b on 18-4-24.
 * K3开奖
 */

public class K3OpenFragment extends BaseFragment {

    @BindView(R.id.rlv_open_result)
    RecyclerView mRlvOpenResult;
    private Unbinder mUnbinder;
    private static final String ARG_PARAM1 = "param1";
    private ArrayList<Handicap> mHandicaps;
    private OpenResultQuickAdapter mQuickAdapter;
    private View mRightFooterView;
    private Handler mHandler;


    public static K3OpenFragment newInstance(ArrayList<Handicap> handicaps) {
        K3OpenFragment fragment = new K3OpenFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_PARAM1, handicaps);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mHandicaps = getArguments().getParcelableArrayList(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_k3_open, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        initViews();
        return view;
    }

    private void initViews() {
        mQuickAdapter = new OpenResultQuickAdapter(R.layout.layout_k3_open);
        mQuickAdapter.setEmptyView(LayoutInflater.from(mContext).inflate(R.layout.empty_view, null));
        mRlvOpenResult.setLayoutManager(new LinearLayoutManager(mContext));
        mRlvOpenResult.setAdapter(mQuickAdapter);
        setScroll();
    }

    private void setScroll() {
        RxBus.get().register(this);
        mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mHandicaps != null) {
                    mRlvOpenResult.scrollToPosition(mHandicaps.size() - 1);
                }
            }
        }, 200);
    }

    @Override
    protected void loadData() {
        mQuickAdapter.setNewData(mHandicaps);
    }

    class OpenResultQuickAdapter extends BaseQuickAdapter {
        public OpenResultQuickAdapter(int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(BaseViewHolder helper, Object item) {
            Handicap k3ResultBean = (Handicap) item;
            setItemBg(helper);
            String openCode = k3ResultBean.getOpenCode().replace(",", " ");
            String expect = k3ResultBean.getExpect();
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
            helper.setText(R.id.tv_open_number, openCode);
            helper.setTextColor(R.id.tv_open_number,getResources().getColor(R.color.open_item_text2));

            String[] nums = k3ResultBean.getOpenCode().split(",");
            int addNums = Integer.valueOf(nums[0]) + Integer.valueOf(nums[1]) + Integer.valueOf(nums[2]);
            helper.setText(R.id.tv_sum_value, addNums + "");
            helper.setTextColor(R.id.tv_sum_value,getResources().getColor(R.color.open_item_text2));
            helper.setText(R.id.tv_big_small, getString(addNums < 11 ? R.string.small : R.string.big));
            helper.setTextColor(R.id.tv_big_small,getResources().getColor(R.color.open_item_text2));
            helper.setText(R.id.tv_single_double, getString((addNums % 2) == 0 ? R.string.double_ : R.string.single));
            helper.setTextColor(R.id.tv_single_double,getResources().getColor(R.color.open_item_text2));
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
        if (mHandicaps == null) {
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
                    if (mHandicaps != null) {
                        mRlvOpenResult.scrollToPosition(mHandicaps.size());
                    }
                }
            }, 200);
        } else {

            mQuickAdapter.removeFooterView(mRightFooterView);
            mQuickAdapter.notifyDataSetChanged();
        }
    }
}
