package com.dawoo.lotterybox.view.view;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.dawoo.lotterybox.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by benson on 17-12-27.
 */

public class HeaderView extends RelativeLayout {

    @BindView(R.id.left_btn)
    FrameLayout mLeftBtn;
    @BindView(R.id.title_name)
    TextView mTitleName;
    private Context mConText;

    public HeaderView(Context context) {
        super(context);
        initUI(context);
    }

    public HeaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initUI(context);
    }

    public HeaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initUI(context);
    }

    private void initUI(Context context) {
        mConText = context;
        View view = LayoutInflater.from(context).inflate(R.layout.layout_custom_header_view, this);
        ButterKnife.bind(this, view);
    }

    @OnClick(R.id.left_btn)
    public void onViewClicked() {
        if (mConText instanceof Activity) {
            ((Activity) mConText).finish();
        }
    }

    public void setHeader(String name, boolean hasLeftBtn) {
        mTitleName.setText(name);
        if (hasLeftBtn) {
            mLeftBtn.setVisibility(View.VISIBLE);
        } else {
            mLeftBtn.setVisibility(View.GONE);
        }
    }
}