package com.dawoo.lotterybox.adapter.play_style;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dawoo.coretool.util.activity.DensityUtil;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.adapter.CommonViewHolder;
import com.dawoo.lotterybox.bean.playtype.PlayTypeBean;
import com.dawoo.lotterybox.view.view.PlayTypePopupWindow;
import com.dawoo.lotterybox.view.view.SpaceItemDecoration;

import java.util.List;

/**
 * Created by archar on 18-2-11.
 */

public class PlayStyeParentAdapter extends RecyclerView.Adapter {

    private RecyclerView mRecyclerView;
    private Context mContext;
    private List<PlayTypeBean> mData;
    private PlayTypePopupWindow mPlayTypePopupWindow;

    public PlayStyeParentAdapter(Context context, List<PlayTypeBean> data, PlayTypePopupWindow playTypePopupWindow, RecyclerView recyclerView) {
        mContext = context;
        mData = data;
        mPlayTypePopupWindow = playTypePopupWindow;
        this.mRecyclerView = recyclerView;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CommonViewHolder(View.inflate(mContext, R.layout.layout_play_style_parent_item, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CommonViewHolder viewHolder = (CommonViewHolder) holder;
        PlayTypeBean playBean = mData.get(position);
        TextView tv_play_style_parent = viewHolder.getTv(R.id.tv_play_style_parent);
        TextView tv_play_style_parent1 = viewHolder.getTv(R.id.tv_play_style_parent1);

        RecyclerView rv_play_style_child = viewHolder.getRv(R.id.rv_play_style_child);

        if (position != 0) {
            String parent = mData.get(position - 1).getParentTitle();
            if (playBean.getParentTitle().equals(parent)) {
                tv_play_style_parent.setVisibility(View.GONE);
            } else {
                tv_play_style_parent.setVisibility(View.VISIBLE);
            }
        }
        tv_play_style_parent.setText(playBean.getParentTitle());
        tv_play_style_parent1.setText(playBean.getType());

        rv_play_style_child.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        if (rv_play_style_child.getItemDecorationCount() == 0) {
            rv_play_style_child.addItemDecoration(new SpaceItemDecoration(DensityUtil.dp2px(mContext, 8)));
        }
        PlayStyeChildAdapter childAdapter = new PlayStyeChildAdapter(mContext, playBean.getPlayBeans(), mPlayTypePopupWindow);
        rv_play_style_child.setAdapter(childAdapter);
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }
}
