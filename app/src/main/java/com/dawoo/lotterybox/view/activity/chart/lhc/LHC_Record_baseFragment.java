package com.dawoo.lotterybox.view.activity.chart.lhc;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dawoo.lotterybox.ConstantValue;
import com.dawoo.lotterybox.adapter.lhc_rcd.HistoryTypeAdapter;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.adapter.lhc_rcd.ResultHistoryBaseAdapter;
import com.dawoo.lotterybox.bean.LHCRecordBean;
import com.dawoo.lotterybox.bean.lottery.Handicap;
import com.dawoo.lotterybox.view.activity.chart.LineRecyclerView;
import com.dawoo.lotterybox.view.activity.chart.lhc.SMRecordActivity;
import com.dawoo.lotterybox.view.fragment.BaseFragment;
import com.hwangjr.rxbus.RxBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by rain on 18-4-25.
 */

public abstract class LHC_Record_baseFragment extends BaseFragment {
    Unbinder unbinder;
    @BindView(R.id.left_recycler_view)
    RecyclerView leftRecycle;
    @BindView(R.id.top_type_rc)
    RecyclerView headerRV;
    @BindView(R.id.content_rv)
    LineRecyclerView contentRV;
    List<Handicap> mDatas = new ArrayList<>();
    List<String> typeData = new ArrayList<>();
    List<List<LHCRecordBean>> contentDatas = new ArrayList<>();
    List<Integer> integerList = new ArrayList<>();
    ResultHistoryBaseAdapter mAdapter;
    HistoryTypeAdapter typeAdapter;
    HistoryTypeAdapter leftAdapter;
    int countTopNums = 49;

    @Override
    protected void loadData() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.result_history_layout, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        RxBus.get().register(this);
        initView();
        initData();
        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDatas = getArguments().getParcelableArrayList(ConstantValue.RENCT_DATA);
        for (Handicap handicap : mDatas) {
            if (!handicap.getOpenCode().contains(",")) {
                mDatas.remove(handicap);
                break;
            }
        }
    }

    void initView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        contentRV.setLayoutManager(layoutManager);
        mAdapter = new ResultHistoryBaseAdapter(countTopNums);
        mAdapter.setData(contentDatas);
        contentRV.setAdapter(mAdapter);

        LinearLayoutManager layoutManager2 = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        headerRV.setLayoutManager(layoutManager2);
        typeAdapter = new HistoryTypeAdapter(R.layout.adapter_top_layout);
        headerRV.setAdapter(typeAdapter);
        typeAdapter.setNewData(typeData);


        LinearLayoutManager layoutManager0 = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        leftRecycle.setLayoutManager(layoutManager0);
        leftAdapter = new HistoryTypeAdapter(R.layout.adapter_issue_left_layout);
        leftRecycle.setAdapter(leftAdapter);
        leftAdapter.setNewData(mDatas);

        leftRecycle.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (recyclerView.getScrollState() != RecyclerView.SCROLL_STATE_IDLE) {
                    contentRV.scrollBy(dx, dy);
                }
            }
        });
        contentRV.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (recyclerView.getScrollState() != RecyclerView.SCROLL_STATE_IDLE) {
                    leftRecycle.scrollBy(dx, dy);
                }
            }
        });

    }

    abstract void initData();


    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        RxBus.get().unregister(this);
    }
}
