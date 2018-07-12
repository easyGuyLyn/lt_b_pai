package com.dawoo.lotterybox.view.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by archar on 18-2-11.
 */

public class NoMoveRecycleView extends RecyclerView {
    public NoMoveRecycleView(Context context) {
        super(context);
    }

    public NoMoveRecycleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public NoMoveRecycleView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        if (e.getAction() == MotionEvent.ACTION_MOVE) {
            return false;
        }
        return super.onInterceptTouchEvent(e);
    }


}
