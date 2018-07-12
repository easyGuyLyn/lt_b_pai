package com.dawoo.lotterybox.view.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.util.MSPropties;
import com.dawoo.lotterybox.view.activity.account.RechargeRecordActivity;

/**
 * Created by rain on 18-5-6.
 */

public class ShowSuccessDialog extends Dialog implements View.OnClickListener {
    TextView cancel_tv, sure_tv;
    View.OnClickListener sureClick;
    public ShowSuccessDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        initView();
    }
    void initView(){
        setContentView(R.layout.dialog_show_success);
        setCancelable(false);
        Window win = getWindow();
        win.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        win.setAttributes(lp);
        cancel_tv = findViewById(R.id.cancel_tv);
        findViewById(R.id.close_iv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        cancel_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                MSPropties.startActivity(RechargeRecordActivity.class);
            }
        });
        sure_tv = findViewById(R.id.sure_tv);
        sure_tv.setOnClickListener(this);
    }

    /**
     * 提交post信息
     * @param onClickListener
     */
    public void setSureClickListener(View.OnClickListener onClickListener) {
        sureClick = onClickListener;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sure_tv:
                if(sureClick==null){return;}
                sureClick.onClick(v);
                break;
        }
    }
}
