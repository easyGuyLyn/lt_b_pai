package com.dawoo.lotterybox.view.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.EditText;

import com.dawoo.lotterybox.R;

/**
 * Created by jack on 18-2-12.
 */

public class PassWordEdit extends android.support.v7.widget.AppCompatEditText {
    //控件宽度与高度
    private int mWidth;
    private int mHeight;
    private Paint mPaint;
    //输入密码长度
    private int mPassWordLength;

    public PassWordEdit(Context context) {
        this(context, null);
    }

    public PassWordEdit(Context context, AttributeSet attrs) {
        super(context, attrs);
        //初始化
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(getResources().getColor(R.color.text_color_gray_999999));
        mPaint.setStrokeWidth(2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //为了绘制矩形边框设置画笔为空心的
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(new RectF(0 + mPaint.getStrokeWidth(), 0, mWidth - mPaint.getStrokeWidth(), mHeight), mPaint);
        mPaint.setStyle(Paint.Style.FILL);
        int width = (int) (mWidth / 6.0);
        //绘制中间竖线
        for (int i = 1; i <= 5; i++) {
            canvas.drawLine(width * i, 0, width * i, mHeight, mPaint);
        }
        //绘制中间圆点
        for (int j = 0; j < mPassWordLength; j++) {
            canvas.drawCircle(width / 2 + width * j, mHeight / 2, 5.0f, mPaint);
        }
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        //进行监听，文本改变的时候刷新界面
        if (text.length() != mPassWordLength) {
            mPassWordLength = text.length();
        }
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        mHeight = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
        setMeasuredDimension(mWidth, mHeight);
    }
}
