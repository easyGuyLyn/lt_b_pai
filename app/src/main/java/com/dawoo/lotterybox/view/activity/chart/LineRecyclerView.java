package com.dawoo.lotterybox.view.activity.chart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;


import com.dawoo.coretool.util.activity.DensityUtil;
import com.dawoo.lotterybox.R;

import java.util.List;

/**
 * Created by b on 18-2-13.
 * 折线图 RecyclerView
 */

public class LineRecyclerView extends RecyclerView {

    private List<Integer> integers;
    private Paint mPaint;
    private Context mContext;
    private float lastX = 0;
    private float lastY = 0;
    private boolean isDrawLine = true;
    private float offset;

    public LineRecyclerView(Context context) {
        super(context);
        init(context);
    }

    public LineRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LineRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
//        mPaint.setXfermode(new PorterDuffXfermode(
//                PorterDuff.Mode.DST_OVER));
        mPaint.setStrokeWidth(10);
        mPaint.setColor(ContextCompat.getColor(mContext, R.color.line_bg));
        mPaint.setStyle(Paint.Style.STROKE);
        offset = DensityUtil.dp2px(mContext, 10);
    }

    public void setLineColor(int colorID){
        mPaint.setColor(ContextCompat.getColor(mContext, colorID));
    }

    public void setList(List<Integer> integers) {
        this.integers = integers;
    }

    public void isDrawLine(boolean isDraw) {
        this.isDrawLine = isDraw;
    }

    private void drawLine(Canvas c) {
        if (!isDrawLine || integers == null)
            return;
        int firstPosition = ((LinearLayoutManager) getLayoutManager()).findFirstVisibleItemPosition();
        int lastPosition = ((LinearLayoutManager) getLayoutManager()).findLastVisibleItemPosition();

//        if (lastPosition + 1 > integers.size()) {
//            return;
//        }

        for (int i = firstPosition; i < lastPosition + 1; i++) {
            Log.e("LineReyccle", "" + i);
            LinearLayout childView = (LinearLayout) getLayoutManager().findViewByPosition(i);
            if (childView != null) {
                float childGroupViewX = childView.getLeft();
                float childGroupViewY = childView.getTop();
                if(i > integers.size()-1) {
                    return;
                }
                View view = childView.getChildAt(integers.get(i));
                if (view != null) {
//                    int[] position = new int[2];
//                    view.getLocationInWindow(position);
                    float x = view.getX() + childGroupViewX + view.getWidth() / 2;
                    float y = view.getY() + childGroupViewY + view.getHeight() / 2;
                    if (lastX != 0 && y > lastY) {
                        Point point1 = getFirstPoint(lastX, lastY, x, y, offset);
                        Point point2 = getSecondPoint(lastX, lastY, x, y, offset);
                        c.drawLine(point1.getX(), point1.getY(), point2.getX(), point2.getY(), mPaint);
                    }
                    lastX = x;
                    lastY = y;
                }
            }
        }
        lastX = 0;
        lastY = 0;
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        drawLine(canvas);
    }

    private float getSlope(float x2, float y2, float x1, float y1) {
        float slope = (y2 - y1) / (x2 - x1);

        return slope;
    }

    private Point getFirstPoint(float x1, float y1, float x4, float y4, float r) {

        Point point = null;
        if (x4 - x1 > 0) {
            float slope = (x4 - x1) / (y4 - y1);
            float tempx = (float) (Math.sqrt(Math.pow(slope * r, 2) / (Math.pow(slope, 2) + 1)));
            float x2 = x1 + tempx;
            float tempy = (float) (Math.sqrt(r * r / (slope * slope + 1)));
            float y2 = y1 + tempy;
            point = new Point(x2, y2);
        } else if (x4 - x1 < 0) {
            float slope = (x4 - x1) / (y4 - y1);
            float tempx = (float) (Math.sqrt(Math.pow(slope * r, 2) / (Math.pow(slope, 2) + 1)));
            float x2 = x1 - tempx;
            float tempy = (float) (Math.sqrt(r * r / (slope * slope + 1)));
            float y2 = y1 + tempy;
            point = new Point(x2, y2);
        } else if (x4 == x1) {
            point = new Point(x1, y1 + r);
        }
        return point;

    }

    private Point getSecondPoint(float x1, float y1, float x4, float y4, float r) {
        Point point = null;
        if (x4 - x1 > 0) {
            float slope = (x4 - x1) / (y4 - y1);
            float tempx = (float) (Math.sqrt(Math.pow(slope * r, 2) / (Math.pow(slope, 2) + 1)));
            float x3 = x4 - tempx;
            float tempy = (float) (Math.sqrt(r * r / (slope * slope + 1)));
            float y3 = y4 - tempy;
            point = new Point(x3, y3);

        } else if (x4 - x1 < 0) {
            float slope = (x4 - x1) / (y4 - y1);
            float tempx = (float) (Math.sqrt(Math.pow(slope * r, 2) / (Math.pow(slope, 2) + 1)));
            float x3 = x4 + tempx;
            float tempy = (float) (Math.sqrt(r * r / (slope * slope + 1)));
            float y3 = y4 - tempy;
            point = new Point(x3, y3);

        } else if (x4 == x1) {
            point = new Point(x4, y4 - r);
        }

        return point;
    }


    public class Point {
        private float x;
        private float y;

        public Point(float x, float y) {
            this.x = x;
            this.y = y;
        }

        public float getX() {
            return x;
        }

        public void setX(float x) {
            this.x = x;
        }

        public float getY() {
            return y;
        }

        public void setY(float y) {
            this.y = y;
        }

    }

}
