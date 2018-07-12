package com.dawoo.lotterybox.view.view;

import android.content.Context;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;

/**
 * Created by archar on 18-3-14.
 */

public class NOMoveStaggeredGridLayoutManager extends StaggeredGridLayoutManager {

    public NOMoveStaggeredGridLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public NOMoveStaggeredGridLayoutManager(int spanCount, int orientation) {
        super(spanCount, orientation);
    }

    private boolean isScrollEnabled = false;



    public void setScrollEnabled(boolean flag) {
        this.isScrollEnabled = flag;
    }

    @Override
    public boolean canScrollVertically() {
        //Similarly you can customize "canScrollHorizontally()" for managing horizontal scroll
        return isScrollEnabled && super.canScrollVertically();
    }
}
