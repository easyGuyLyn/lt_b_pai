package com.dawoo.lotterybox.view.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;

/**
 * Created by b on 18-5-3.
 */

public class ViewPagerHorizontalScrollView extends HorizontalScrollView {


    public ViewPagerHorizontalScrollView(Context context) {
        super(context);
    }

    public ViewPagerHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewPagerHorizontalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev){
        onTouchEvent(ev);
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        super.onTouchEvent(ev);
        return false;
    }
}
