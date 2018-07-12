package com.dawoo.lotterybox.adapter.play_style;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.adapter.CommonViewHolder;
import com.dawoo.lotterybox.bean.playtype.PlayTypeBean;
import com.dawoo.lotterybox.view.view.PlayTypePopupWindow;

import java.util.List;

/**
 * Created by archar on 18-2-11.
 */

public class PlayStyeChildAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<PlayTypeBean.PlayBean> mData;
    private PlayTypePopupWindow mPlayTypePopupWindow;

    public PlayStyeChildAdapter(Context context, List<PlayTypeBean.PlayBean> data, PlayTypePopupWindow playTypePopupWindow) {
        mContext = context;
        mData = data;
        mPlayTypePopupWindow = playTypePopupWindow;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CommonViewHolder(View.inflate(mContext, R.layout.recyclerview_list_play_child_item, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CommonViewHolder viewHolder = (CommonViewHolder) holder;
        PlayTypeBean.PlayBean playTypeBean = mData.get(position);
        TextView tv_play_style_child = viewHolder.getTv(R.id.tv_play_style_child);
        if (!TextUtils.isEmpty(playTypeBean.getPlayTypeName())) {
            tv_play_style_child.setText(playTypeBean.getPlayTypeName());
        }
        if (playTypeBean.isSelected()) {
            tv_play_style_child.setTextColor(mContext.getResources().getColor(R.color.mine_lv_bg));
        } else {
            tv_play_style_child.setTextColor(mContext.getResources().getColor(R.color.color_gray_666666));
        }
        tv_play_style_child.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPlayTypePopupWindow.refeshSelectData(playTypeBean);
                mPlayTypePopupWindow.dissMissPopAndCallBack(playTypeBean);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

}
