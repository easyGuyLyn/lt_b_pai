package com.dawoo.lotterybox.adapter.waypaper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.dawoo.lotterybox.R;

import java.util.List;

/**
 * 路纸图表格适配器
 * Created by b on 18-3-19.
 */

public class WayPaperFormAdapter extends RecyclerView.Adapter {

    private final Context mContext;
    private final List<List<String>> statusList;

    public WayPaperFormAdapter(Context context, List<List<String>> str) {
        this.mContext = context;
        this.statusList = str;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        FormItemHolder formItemHolder = new FormItemHolder(mContext,LayoutInflater.from(mContext).inflate(R.layout.item_ssc_way_paper_form, parent, false));
        return formItemHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((FormItemHolder)holder).onBindView(statusList.get(position));
    }

    @Override
    public int getItemCount() {
        return statusList.size();
    }
}
