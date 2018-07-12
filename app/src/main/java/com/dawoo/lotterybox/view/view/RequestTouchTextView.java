package com.dawoo.lotterybox.view.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by archar on 18-3-18.
 */

public class RequestTouchTextView extends android.support.v7.widget.AppCompatTextView {

    public RequestTouchTextView(Context context) {
        super(context);
    }

    public RequestTouchTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RequestTouchTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }

}
