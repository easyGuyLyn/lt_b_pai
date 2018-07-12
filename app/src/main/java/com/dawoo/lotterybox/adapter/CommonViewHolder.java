package com.dawoo.lotterybox.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;


/**
 * Created by archar on 2016/3/11.
 *
 * 可以避免重复 findViewById
 */
public class CommonViewHolder extends RecyclerView.ViewHolder {
    private View itemView;
    SparseArray<View> views = new SparseArray<View>();

    public CommonViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
    }

    public <T extends View> T getView(int viewId) {
        if (views.get(viewId) == null) {
            views.put(viewId, itemView.findViewById(viewId));
        }
        return (T) views.get(viewId);
    }

    public <T extends View> T getView(int viewId, Class<T> kclass) {
        return (T) getView(viewId);
    }

    public TextView getTv(int viewId) {
        return getView(viewId, TextView.class);
    }

    public ImageView getIv(int viewId) {
        return getView(viewId, ImageView.class);
    }

    public RelativeLayout getRl(int viewId) {
        return getView(viewId, RelativeLayout.class);
    }

    public RecyclerView getRv(int viewId) {
        return getView(viewId, RecyclerView.class);
    }

    public ProgressBar getPb(int viewId) {
        return getView(viewId, ProgressBar.class);
    }

    public LinearLayout getLl(int viewId) {
        return getView(viewId, LinearLayout.class);
    }

}
