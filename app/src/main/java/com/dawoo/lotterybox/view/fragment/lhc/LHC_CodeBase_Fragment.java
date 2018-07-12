package com.dawoo.lotterybox.view.fragment.lhc;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dawoo.lotterybox.ConstantValue;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.adapter.lhc.LHCBaseAdapter;
import com.dawoo.lotterybox.adapter.lhc.LHCTopTypeAdapter;
import com.dawoo.lotterybox.bean.KeyMap;
import com.dawoo.lotterybox.bean.playtype.PlayChooseBean;
import com.dawoo.lotterybox.bean.playtype.PlayDetailBean;
import com.dawoo.lotterybox.util.lottery.LHCUtil;
import com.dawoo.lotterybox.util.lottery.initdata.LHCDataUtils;
import com.dawoo.lotterybox.view.activity.lottery.lhc.HKSMActivity;
import com.dawoo.lotterybox.view.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by rain on 18-4-23.
 */

public abstract class LHC_CodeBase_Fragment extends BaseFragment {
    View rootView;
    Unbinder unbinder;
    @BindView(R.id.top_type_rc)
    RecyclerView topTypeRecycleView;
    LHCTopTypeAdapter typeAdapter;
    @BindView(R.id.content_rv)
    RecyclerView palyRecycleView;
    List<PlayChooseBean> typeList = new ArrayList<>();
    List<PlayDetailBean> allDatas = new ArrayList<>();
    int index = 0;
    LHCBaseAdapter mAdapter;
    boolean needChange;
    ChangeReceiver mReceiver;
    SelectedReceiver selectedReceiver;
    int minSelected = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_tm_layout, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        initView();
        initData();
        return rootView;
    }

    @Override
    protected void loadData() {

    }

    void initView() {
        typeAdapter = new LHCTopTypeAdapter(R.layout.layout_top_type_item);
        typeAdapter.setNewData(typeList);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        topTypeRecycleView.setLayoutManager(manager);
        topTypeRecycleView.setAdapter(typeAdapter);
        palyRecycleView.setLayoutManager(new GridLayoutManager(getActivity(), 6));
        mAdapter = new LHCBaseAdapter();
        palyRecycleView.setAdapter(mAdapter);
        mAdapter.setmDatas(allDatas);
        setOnItemClick();
        mAdapter.setOnItemClickListener(new LHCBaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (mAdapter.getItem(position).isAskview()) {
                    KeyMap.PlayExplainBean a = LHCUtil.getExplianbyCode(mAdapter.getItem(position).getCode());
                    if (a == null) {
                        return;
                    }
                    ((HKSMActivity) getActivity()).showExplainDialog(a);
                } else {
                    allDatas.get(position).setSelected(!allDatas.get(position).isSelected());
                    mAdapter.notifyItemChanged(position);
                    ((HKSMActivity) getActivity()).setSelectedItem(allDatas.get(position), minSelected);
                }
            }
        });
    }

    /**
     * 设置typeView的点击事件
     */
    abstract void setOnItemClick();

    abstract void initData();

    private void clearSelected() {
        LHCDataUtils.clearSelected(allDatas);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden){
            clearSelected();
        }
    }

    abstract void randomSelected();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mReceiver = new ChangeReceiver();
        selectedReceiver = new SelectedReceiver();
        LocalBroadcastManager.getInstance(mContext).registerReceiver(selectedReceiver, new IntentFilter(ConstantValue.EVENT_CHANGE_SELECTED));
        LocalBroadcastManager.getInstance(mContext).registerReceiver(mReceiver, new IntentFilter(ConstantValue.EVENT_CHANGE_LR));
    }

    @Override
    public void onDestroyView() {
        LocalBroadcastManager.getInstance(mContext).unregisterReceiver(mReceiver);
        LocalBroadcastManager.getInstance(mContext).unregisterReceiver(selectedReceiver);
        super.onDestroyView();
        unbinder.unbind();
    }

    /**
     * 冷热
     */
    class ChangeReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            needChange = true;
            if (!isHidden()) {
                if (mAdapter.getItemCount() != 0) {
                    mAdapter.notifyDataSetChanged();
                }
            }
        }
    }

    class SelectedReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Boolean isRandom = intent.getBooleanExtra("isRandom", false);
            if (!isHidden()) {
                if (isRandom) {
                    randomSelected();
                } else {
                    clearSelected();
                }
            }
        }
    }

}
