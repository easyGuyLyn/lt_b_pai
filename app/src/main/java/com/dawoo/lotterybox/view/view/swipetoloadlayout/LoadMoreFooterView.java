package com.dawoo.lotterybox.view.view.swipetoloadlayout;

import android.content.Context;
import android.util.AttributeSet;

import com.aspsine.swipetoloadlayout.SwipeLoadMoreTrigger;
import com.aspsine.swipetoloadlayout.SwipeTrigger;
import com.dawoo.lotterybox.R;

/**
 * Created by benson on 17-12-24.
 */

public class LoadMoreFooterView extends android.support.v7.widget.AppCompatTextView implements SwipeTrigger, SwipeLoadMoreTrigger {
    public LoadMoreFooterView(Context context) {
        super(context);
    }

    public LoadMoreFooterView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onLoadMore() {
        setText(getResources().getString(R.string.LOADING_MORE));
    }

    @Override
    public void onPrepare() {
        setText("");
    }

    @Override
    public void onMove(int yScrolled, boolean isComplete, boolean automatic) {
        if (!isComplete) {
            if (yScrolled <= -getHeight()) {
                setText(getResources().getString(R.string.RELEASE_TO_LOAD_MORE));
            } else {
                setText(getResources().getString(R.string.SWIPE_TO_LOAD_MORE));
            }
        } else {
            setText(getResources().getString(R.string.LOAD_MORE_RETURNING));
        }
    }

    @Override
    public void onRelease() {
        setText(getResources().getString(R.string.LOAD_MORE_RETURNING));
    }

    @Override
    public void onComplete() {
        setText(getResources().getString(R.string.LOAD_MORE_COMPLETE));
    }

    @Override
    public void onReset() {
        setText("");
    }
}
