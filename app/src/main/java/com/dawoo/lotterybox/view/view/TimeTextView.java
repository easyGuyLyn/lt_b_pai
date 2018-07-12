package com.dawoo.lotterybox.view.view;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created by b on 18-2-12.
 */

public class TimeTextView extends android.support.v7.widget.AppCompatTextView {

    public TimeTextView(Context context) {
        super(context);
        init(context);
    }


    public TimeTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TimeTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        AssetManager assets = context.getAssets();
        final Typeface font = Typeface.createFromAsset(assets, "fonts/digitalism.ttf");
        setTypeface(font);
    }
}
