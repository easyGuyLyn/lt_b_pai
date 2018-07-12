package com.dawoo.lotterybox.view.view.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.dawoo.lotterybox.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 提示
 * Created by benson on 18-2-12.
 */

public class CommonTipDialog {

    @BindView(R.id.title_name_tv)
    TextView mTitleNameTv;
    @BindView(R.id.lable_rate_tv)
    TextView mLableRateTv;
    @BindView(R.id.cancle_btn)
    TextView mCancleBtn;
    @BindView(R.id.sure_btn)
    TextView mSureBtn;
    private BaseDialog mBaseDialog;
    private Context mContext;
    private Unbinder mUnbinder;

    public CommonTipDialog(@NonNull Context context) {
        mContext = context;
        mBaseDialog = new BaseDialog(context, R.style.CustomDialogStyle);
        mBaseDialog.setContentView(R.layout.dialog_common_tip);
        mUnbinder = ButterKnife.bind(this, mBaseDialog);
        mBaseDialog.setCancelable(false);
        mBaseDialog.setCanceledOnTouchOutside(false);
    }


    public void show() {
        if (mBaseDialog != null && !mBaseDialog.isShowing()) {
            mBaseDialog.show();
        }
    }

    public void dismiss() {
        if (mBaseDialog != null && mBaseDialog.isShowing()) {
            mBaseDialog.dismiss();
        }
    }


    /**
     * 消除引用
     */
    public void onDestory() {

        if (mBaseDialog != null) {
            mBaseDialog.dismiss();
            mBaseDialog = null;
        }

        if (mUnbinder != null) {
            mContext = null;
            mUnbinder.unbind();
        }
    }

    @OnClick({R.id.cancle_btn, R.id.sure_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cancle_btn:
                dismiss();
                break;
            case R.id.sure_btn:
                dismiss();
                break;
        }
    }
}
