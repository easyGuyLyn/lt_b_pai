package com.dawoo.lotterybox.view.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * 自定义可以拖动的view
 * 用于游戏中的退出
 * Created by benson on 18-2-21.
 */

public class DragViewLayout extends FrameLayout {

    private Context mContext;
    private ViewDragHelper mDragHelper;

    public DragViewLayout(Context context) {
        super(context);
        createView(context);
    }

    public DragViewLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        createView(context);
    }

    public DragViewLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        createView(context);
    }

    void createView(Context context) {
        mContext = context;
        mDragHelper = ViewDragHelper.create(this, 1.0f, new DragHelperCallBack());
        mDragHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_ALL);//这里锁定左边
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mDragHelper.shouldInterceptTouchEvent(ev);
    }


    class DragHelperCallBack extends ViewDragHelper.Callback {

        @Override
        public boolean tryCaptureView(@NonNull View child, int pointerId) {
            return true;
        }

        @Override
        public int clampViewPositionHorizontal(@NonNull View child, int left, int dx) {
            //控制左边的拖曳距离，不能越界。
            //当拖曳的距离超过左边的padding值，也意味着child view越界，复位
            //默认的padding值=0
            int paddingleft = getPaddingLeft();
            if (left < paddingleft) {
                return paddingleft;
            }

            //这里是控制右边的拖曳边缘极限位置。
            //假设pos的值刚好是子view child右边边缘与父view的右边重合的情况
            //pos值即为一个极限的最右边位置，超过也即意味着拖曳越界：越出右边的界限，复位。
            //可以再加一个paddingRight值，缺省的paddingRight=0，所以即便不加也在多数情况正常可以工作
            int pos = getWidth() - child.getWidth() - getPaddingRight();
            if (left > pos) {
                return pos;
            }

            //其他情况属于在范围内的拖曳，直接返回系统计算默认的left即可
            return left;
        }

        @Override
        public int clampViewPositionVertical(@NonNull View child, int top, int dy) {
            int paddingTop = getPaddingTop();
            if (top < paddingTop) {
                return paddingTop;
            }
            int pos = getHeight() - child.getHeight() - getPaddingBottom();

            if (top > pos) {
                return pos;
            }

            return top;
        }

        @Override
        public int getViewHorizontalDragRange(View child) {
            return getMeasuredWidth() - child.getMeasuredWidth();
        }

        @Override
        public int getViewVerticalDragRange(View child) {
            return getMeasuredHeight() - child.getMeasuredHeight();
        }

        @Override
        public boolean onEdgeLock(int edgeFlags) {
            return true;
        }
    }

}
