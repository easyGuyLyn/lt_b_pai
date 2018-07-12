package com.dawoo.lotterybox.view.view;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;


import com.dawoo.lotterybox.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by benson on 18-1-1.
 */

public class BankScuessDialog {
    private final Context mContext;
    @BindView(R.id.close_iv)
    ImageView mClose;
    private Dialog mDialog;
    @BindView(R.id.notice_title)
    TextView noticeTitle;



    public BankScuessDialog(@NonNull Context context) {
        mContext = context;
        mDialog = new Dialog(mContext, R.style.CustomDialogStyle);
        mDialog.setContentView(R.layout.tip_diaolog);
        mDialog.setCancelable(false);
        ButterKnife.bind(this, mDialog);
        setLayoutParams();
        mDialog.show();
    }

    private void setLayoutParams() {
        Window win = mDialog.getWindow();
        win.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        win.setAttributes(lp);
    }

    public void showDialog() {
        if (mDialog != null) {
            mDialog.show();
        }
    }


    @OnClick({R.id.close_iv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.close_iv:
                mDialog.dismiss();
                break;
            default:
        }
    }

}
