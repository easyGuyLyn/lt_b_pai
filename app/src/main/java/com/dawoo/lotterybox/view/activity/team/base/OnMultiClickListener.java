package com.dawoo.lotterybox.view.activity.team.base;

import android.view.View;

/**
 * Created by alex on 18-5-1.
 * @author alex
 */

public abstract class OnMultiClickListener implements View.OnClickListener{
    private static final int MIN_CLICK_DELAY_TIME = 500;
    private static long lastClickTime;

    public abstract void onMultiClick(View v);

    @Override
    public void onClick(View v) {
        long curClickTime = System.currentTimeMillis();
        if((curClickTime - lastClickTime) >= MIN_CLICK_DELAY_TIME) {
            // 超过点击间隔后再将lastClickTime重置为当前点击时间
            lastClickTime = curClickTime;
            onMultiClick(v);
        }
    }
}