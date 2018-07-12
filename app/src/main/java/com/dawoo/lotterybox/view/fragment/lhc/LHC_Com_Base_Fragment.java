package com.dawoo.lotterybox.view.fragment.lhc;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dawoo.lotterybox.ConstantValue;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.adapter.lhc.LHCColorAdapter;
import com.dawoo.lotterybox.adapter.lhc.LHCTopTypeAdapter;
import com.dawoo.lotterybox.bean.playtype.PlayChooseBean;
import com.dawoo.lotterybox.bean.playtype.PlayDetailBean;
import com.dawoo.lotterybox.util.lottery.initdata.LHCDataUtils;
import com.dawoo.lotterybox.view.fragment.BaseFragment;
import com.dawoo.lotterybox.view.view.QuestionAskView;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.support.v7.widget.LinearLayoutManager.HORIZONTAL;

/**
 * Created by rain on 18-3-9.
 */

public abstract class LHC_Com_Base_Fragment extends BaseFragment {
    Unbinder unbinder;
    @BindView(R.id.color_top_rv)
    RecyclerView typeRecycle;
    @BindView(R.id.color_content_rv)
    RecyclerView playRecycle;
    @BindView(R.id.color_ask)
    QuestionAskView askView;
    List<PlayChooseBean> typeList = new ArrayList<>();
    List<PlayDetailBean> playList = new ArrayList<>();
    LHCTopTypeAdapter typeAdapter;
    BaseQuickAdapter playAdapter;
    boolean needChange;
    ChangeReceiver mReceiver;
    SelectedReceiver selectedReceiver;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lhc_color_hlfe_layout, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        initData();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mReceiver = new ChangeReceiver();
        selectedReceiver = new SelectedReceiver();
        LocalBroadcastManager.getInstance(mContext).registerReceiver(selectedReceiver,new IntentFilter(ConstantValue.EVENT_CHANGE_SELECTED));
        LocalBroadcastManager.getInstance(mContext).registerReceiver(mReceiver,new IntentFilter(ConstantValue.EVENT_CHANGE_LR));
    }

    void initView() {
        playAdapter = new LHCColorAdapter<PlayDetailBean>(R.layout.layout_lhc_color_play_item);
        typeAdapter = new LHCTopTypeAdapter(R.layout.layout_top_type_item);
        typeAdapter.setNewData(typeList);
        playAdapter.setNewData(playList);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(HORIZONTAL);
        typeRecycle.setLayoutManager(manager);
        typeRecycle.setAdapter(typeAdapter);
        playRecycle.setLayoutManager(new LinearLayoutManager(getActivity()));
        playRecycle.setAdapter(playAdapter);
    }

    abstract void initData();

    @Override
    public void onDestroyView() {
        LocalBroadcastManager.getInstance(mContext).unregisterReceiver(selectedReceiver);
        LocalBroadcastManager.getInstance(mContext).unregisterReceiver(mReceiver);
        super.onDestroyView();
        unbinder.unbind();
    }
    abstract void randomSelected();
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            if (needChange) {
                initData();
            } else {
                LHCDataUtils.clearSelected(playList);
                if (playAdapter == null) {
                    return;
                }
                playAdapter.notifyDataSetChanged();
            }
        }
    }

    /**
     * 冷热
     */
    class ChangeReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            needChange = true;
            if (!isHidden()) {
                if (!playAdapter.getData().isEmpty()) {
                    playAdapter.notifyDataSetChanged();
                }
            }
        }
    }
    class SelectedReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            Boolean isRandom = intent.getBooleanExtra("isRandom",false);
            if(!isHidden()){
                if(isRandom){
                    randomSelected();
                }else{
                    LHCDataUtils.clearSelected(playList);
                    if (playAdapter == null) {
                        return;
                    }
                    playAdapter.notifyDataSetChanged();
                }
            }
        }
    }
}
