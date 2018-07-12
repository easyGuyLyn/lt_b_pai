package com.dawoo.lotterybox.view.view.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.dawoo.lotterybox.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by b on 18-2-15.
 * 走势图设置
 */

public class TrendChartSetDialog extends BaseDialog {

    @BindView(R.id.rb_lately_30)
    RadioButton mRbLately30;
    @BindView(R.id.rb_lately_100)
    RadioButton mRbLately100;
    @BindView(R.id.rb_lately_50)
    RadioButton mRbLately50;
    @BindView(R.id.rb_lately_120)
    RadioButton mRbLately120;
    @BindView(R.id.rg_lately)
    RadioGroup mRgLately;
    @BindView(R.id.rb_just_array)
    RadioButton mRbJustArray;
    @BindView(R.id.rb_reverse_array)
    RadioButton mRbReverseArray;
    @BindView(R.id.rg_array)
    RadioGroup mRgArray;
    @BindView(R.id.rb_show_line)
    RadioButton mRbShowLine;
    @BindView(R.id.rb_hide_line)
    RadioButton mRbHideLine;
    @BindView(R.id.rg_broken_line)
    RadioGroup mRgBrokenLine;
    @BindView(R.id.rb_show_omit)
    RadioButton mRbShowOmit;
    @BindView(R.id.rb_hide_omit)
    RadioButton mRbHideOmit;
    @BindView(R.id.rg_omit)
    RadioGroup mRgOmit;
    @BindView(R.id.rb_show_statistics)
    RadioButton mRbShowStatistics;
    @BindView(R.id.rb_hide_statistics)
    RadioButton mRbHideStatistics;
    @BindView(R.id.rg_statistics)
    RadioGroup mRgStatistics;
    @BindView(R.id.cancle_btn)
    Button mCancleBtn;
    @BindView(R.id.sure_btn)
    Button mSureBtn;
    private Unbinder mUnBinder;

    public TrendChartSetDialog(@NonNull Context context) {
        super(context, R.style.CustomDialogStyle);
        init();
    }

    private void init() {
        setContentView(R.layout.dialog_trend_chart_set);
        mUnBinder = ButterKnife.bind(this);
        setCancelable(true);
        setCanceledOnTouchOutside(false);
    }

    @OnClick({R.id.cancle_btn})
    public void setOnClick(View view){
        switch (view.getId()){
            case R.id.cancle_btn:
                dismiss();
                break;
        }
    }

    public void onDestory() {
        dismiss();
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
    }

}
