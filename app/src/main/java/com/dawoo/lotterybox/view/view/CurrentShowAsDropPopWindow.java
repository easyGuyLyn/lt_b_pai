package com.dawoo.lotterybox.view.view;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.PopupWindow;

/**
 * Created by archar on 18-2-21.
 */

public class CurrentShowAsDropPopWindow extends PopupWindow {
    public CurrentShowAsDropPopWindow(Context context) {
        super(context);
    }

    public CurrentShowAsDropPopWindow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CurrentShowAsDropPopWindow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CurrentShowAsDropPopWindow(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public CurrentShowAsDropPopWindow() {
    }

    public CurrentShowAsDropPopWindow(View contentView) {
        super(contentView);
    }

    public CurrentShowAsDropPopWindow(int width, int height) {
        super(width, height);
    }

    public CurrentShowAsDropPopWindow(View contentView, int width, int height) {
        super(contentView, width, height);
    }

    public CurrentShowAsDropPopWindow(View contentView, int width, int height, boolean focusable) {
        super(contentView, width, height, focusable);
    }

    @Override
    public void showAsDropDown(View anchor) {
        if (Build.VERSION.SDK_INT >= 24) {
            Rect visibleFrame = new Rect();
            anchor.getGlobalVisibleRect(visibleFrame);
            int height = anchor.getResources().getDisplayMetrics().heightPixels - visibleFrame.bottom;
            setHeight(height);
        }

        super.showAsDropDown(anchor);
    }
}
