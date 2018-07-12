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

public class WebHeaderView extends RelativeLayout {

    @BindView(R.id.left_back_btn)
    FrameLayout mLefBacktBtn;
    @BindView(R.id.right_close_btn)
    FrameLayout mLefCloseBtn;
    @BindView(R.id.title_name)
    TextView mTitleName;
    private Context mConText;

    public WebHeaderView(Context context) {
        super(context);
        initUI(context);
    }

    public WebHeaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initUI(context);
    }

    public WebHeaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initUI(context);
    }

    private void initUI(Context context) {
        mConText = context;
        View view = LayoutInflater.from(context).inflate(R.layout.layout_custom_web_header_view, this);
        ButterKnife.bind(this, view);
    }

    @OnClick({R.id.right_close_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.right_close_btn:
                if (mConText instanceof Activity) {
                    ((Activity) mConText).finish();
                }
                break;
        }
    }

    public void setHeader(String name) {
        mTitleName.setText(name);
    }

    public void setHeader(String name, boolean hasLeftBackBtn, boolean hasRightCloseBtn) {
        mTitleName.setText(name);
        if (hasLeftBackBtn) {
            mLefBacktBtn.setVisibility(View.VISIBLE);
        } else {
            mLefBacktBtn.setVisibility(View.GONE);
        }
        if (hasRightCloseBtn) {
            mLefCloseBtn.setVisibility(View.VISIBLE);
        } else {
            mLefCloseBtn.setVisibility(View.GONE);
        }
    }

    public void setLeftBackListener(OnClickListener listener) {
        mLefBacktBtn.setOnClickListener(listener);
    }
}