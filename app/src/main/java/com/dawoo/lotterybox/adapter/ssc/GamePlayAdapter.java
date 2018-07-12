package com.dawoo.lotterybox.adapter.ssc;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.bean.playtype.PlayTypeBean;
import com.dawoo.lotterybox.bean.playtype.PlayTypeBean.PlayBean.LayoutBean;

import java.util.List;

/**
 * Created by b on 18-2-11.
 */

public class GamePlayAdapter extends RecyclerView.Adapter {

    private PlayTypeBean.PlayBean mPlayTypeBean;
    private Context mContext;
    private List<LayoutBean> mLayoutBeans;
    public int type = -1; // 冷热和遗漏checkbox状态

    public GamePlayAdapter(Context Context, PlayTypeBean.PlayBean mPlayTypeBean) {
        this.mContext = Context;
        this.mPlayTypeBean = mPlayTypeBean;
        this.mLayoutBeans = mPlayTypeBean.getLayoutBeans();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == -1) {
            GamePlayHeadHolder gamePlayHeaderHolder = new GamePlayHeadHolder(mContext,LayoutInflater.from(mContext).inflate(R.layout.item_ssc_game_play_head, parent, false));
            return gamePlayHeaderHolder;
        } else {
            if (viewType==0){
                GamePlayHolder gamePlayHolder = new GamePlayHolder(mContext,LayoutInflater.from(mContext).inflate(R.layout.item_ssc_game_play, parent, false));
                return gamePlayHolder;
            }else if (viewType==2){
                GamePlayInputHolder gamePlayInputHolder = new GamePlayInputHolder(mContext,LayoutInflater.from(mContext).inflate(R.layout.item_ssc_game_play_input, parent, false));
                return gamePlayInputHolder;
            }else {
                GamePlayHolder gamePlayHolder = new GamePlayHolder(mContext,LayoutInflater.from(mContext).inflate(R.layout.item_ssc_game_play, parent, false));
                return gamePlayHolder;
            }

        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof GamePlayHeadHolder)
            ((GamePlayHeadHolder) holder).onBindView(mPlayTypeBean ,this);
        if (holder instanceof GamePlayHolder)
            ((GamePlayHolder) holder).onBindView(mPlayTypeBean,position -1,this);
        if (holder instanceof GamePlayInputHolder)
            ((GamePlayInputHolder)holder).onBindView(mPlayTypeBean,position -1);
    }

    public void setNewData(PlayTypeBean.PlayBean mPlayTypeBean){
        this.mPlayTypeBean = mPlayTypeBean;
        this.mLayoutBeans = mPlayTypeBean.getLayoutBeans();
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return -1;
        else {
           return mLayoutBeans.get(position-1).getLayoutType();
        }
    }

    @Override
    public int getItemCount() {
        return mLayoutBeans.size() + 1;
    }
}
