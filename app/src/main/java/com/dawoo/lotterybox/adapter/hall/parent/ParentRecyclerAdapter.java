package com.dawoo.lotterybox.adapter.hall.parent;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.adapter.hall.child.BaseViewHolder;
import com.dawoo.lotterybox.adapter.hall.child.ChildViewHolder;
import com.dawoo.lotterybox.adapter.hall.child.ParentViewHolder;
import com.dawoo.lotterybox.adapter.hall.child.RecyclerAdapter;
import com.dawoo.lotterybox.bean.BannerBean;
import com.dawoo.lotterybox.bean.Bulletin;
import com.dawoo.lotterybox.bean.TypeAndLotteryBean;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by b on 18-4-12.
 */

public class ParentRecyclerAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private Context mContext;
    private List<BannerBean> bannerBeans = new ArrayList<>(); //轮播图数据
    private List<Bulletin> bulletins = new ArrayList<>();    //公告数据
    private List<TypeAndLotteryBean> lotteryTypes = new ArrayList<>(); //彩种类型数据

    public ParentRecyclerAdapter(Context context){
        mContext = context;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        switch (viewType){
            case 0:
                view = LayoutInflater.from(mContext).inflate(R.layout.item_hall_banner, parent, false);
                return new BannerViewHolder(mContext, view);
            case 1:
                view = LayoutInflater.from(mContext).inflate(R.layout.item_hall_marquee_text, parent, false);
                return new MarqueeTextViewHolder(mContext, view);
            case 2:
                view = LayoutInflater.from(mContext).inflate(R.layout.item_hall_shortcut_icon, parent, false);
                return new ShortcutViewHolder(mContext, view);
            default:
                view = LayoutInflater.from(mContext).inflate(R.layout.item_hall_recyclerview, parent, false);
                return new ChildRecyclerViewHolder(mContext, view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        if (holder instanceof BannerViewHolder)
            ((BannerViewHolder)holder).bindView(bannerBeans);
        else if (holder instanceof MarqueeTextViewHolder)
            ((MarqueeTextViewHolder)holder).bindView(bulletins);
        else if (holder instanceof ChildRecyclerViewHolder)
            ((ChildRecyclerViewHolder)holder).bindView(lotteryTypes,mOnScrollListener);
    }

    public void setBannerBeans(List<BannerBean> bannerBeans){
        this.bannerBeans = bannerBeans;
        notifyItemChanged(0);
    }

    public void setBulletins(List<Bulletin> bulletins){
        this.bulletins = bulletins;
        notifyItemChanged(1);
    }

    public void setLotteryTypes(List<TypeAndLotteryBean> lotteryTypes){
        this.lotteryTypes = lotteryTypes;
        notifyItemChanged(3);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    private RecyclerAdapter.OnScrollListener mOnScrollListener;
    public void setOnScrollListener(RecyclerAdapter.OnScrollListener onScrollListener){
        this.mOnScrollListener = onScrollListener;
    }
}
