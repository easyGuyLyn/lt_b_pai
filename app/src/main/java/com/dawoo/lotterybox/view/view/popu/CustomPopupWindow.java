package com.dawoo.lotterybox.view.view.popu;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dawoo.coretool.util.activity.DensityUtil;
import com.dawoo.lotterybox.R;

/**
 * 快选和游戏类型
 * Created by benson on 18-1-15.
 */

public class CustomPopupWindow {

    private View mContentView;
    private PopupWindow mPopWindow = null;
    private RecyclerView mRecyclerView = null;
    private final Context mContext;

    public CustomPopupWindow(Context context, BaseQuickAdapter adapter) {
        mContext = context;
        if (mContentView == null) {
            mContentView = LayoutInflater.from(mContext).inflate(R.layout.custom_popuwindow_view, null);
            mRecyclerView = mContentView.findViewById(R.id.recycle_view);
        }
        mRecyclerView.setLayoutManager(new WrapContentLinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        mContentView.measure(0, 0);
        if (mPopWindow == null) {
            if (mContentView.getMeasuredHeight() < 400) {
                mPopWindow = new PopupWindow(mContentView, DensityUtil.dp2px(mContext, mContentView.getMeasuredWidth() * 3 / 4), DensityUtil.dp2px(mContext, mContentView.getMeasuredHeight() * 7 / 10), true);
            } else {
                mPopWindow = new PopupWindow(mContentView, DensityUtil.dp2px(mContext, mContentView.getMeasuredWidth() * 3 / 4), DensityUtil.dp2px(mContext, 300), true);
            }

            mPopWindow.setTouchable(true);
            mPopWindow.setOutsideTouchable(true);
            mPopWindow.setBackgroundDrawable(new BitmapDrawable(context.getResources(), (Bitmap) null));
        }
        mPopWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = ((Activity) mContext).getWindow().getAttributes();
                lp.alpha = 1f;
                ((Activity) mContext).getWindow().setAttributes(lp);
            }
        });
    }

    public void doTogglePopupWindow(View view) {
        if (mPopWindow == null) {
            return;
        }

        if (mPopWindow.isShowing()) {
            mPopWindow.dismiss();
        } else {
            // 设置背景颜色变暗
            WindowManager.LayoutParams lp = ((Activity) mContext).getWindow().getAttributes();
            lp.alpha = 0.7f;
            ((Activity) mContext).getWindow().setAttributes(lp);
            mPopWindow.showAsDropDown(view);
        }
    }

    public void dissMissPopWindow() {
        if (mPopWindow != null && mPopWindow.isShowing()) {
            mPopWindow.dismiss();
        }
    }
}
