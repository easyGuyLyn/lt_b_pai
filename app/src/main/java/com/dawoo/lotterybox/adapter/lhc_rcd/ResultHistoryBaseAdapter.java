package com.dawoo.lotterybox.adapter.lhc_rcd;

import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.dawoo.coretool.util.activity.DensityUtil;
import com.dawoo.lotterybox.BoxApplication;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.bean.LHCRecordBean;
import com.dawoo.lotterybox.view.view.HistoryTextLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rain on 18-4-24.
 */

public class ResultHistoryBaseAdapter extends RecyclerView.Adapter<ResultHistoryBaseAdapter.BaseHolder> {
    List<List<LHCRecordBean>> mDatas = new ArrayList<>();
    int topNum=50;
    public ResultHistoryBaseAdapter(int topNum){
        this.topNum = topNum;
    }
    @NonNull
    @Override
    public BaseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout linearLayout = new LinearLayout(parent.getContext());
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DensityUtil.dp2px(parent.getContext(), 40)));
        for (int i = 0; i < topNum; i++) {
            HistoryTextLayout mTextView = new HistoryTextLayout(parent.getContext());
            linearLayout.addView(mTextView);
        }
        return new BaseHolder(linearLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseHolder holder, int position) {
        List<LHCRecordBean> beans = mDatas.get(position);
        for (int i = 0; i < beans.size(); i++) {
            ((HistoryTextLayout) holder.parent.getChildAt(i)).setData(beans.get(i), i);
        }
        if (position % 2 == 0) {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(BoxApplication.getContext(), R.color.white));
        } else {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(BoxApplication.getContext(), R.color.color_chart_item_bg));
        }
    }

    public void setData(List<List<LHCRecordBean>> mDatas) {
        this.mDatas=mDatas;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class BaseHolder extends RecyclerView.ViewHolder {
        LinearLayout parent;

        public BaseHolder(View itemView) {
            super(itemView);
            parent = (LinearLayout) itemView;
        }
    }
}
