package com.dawoo.lotterybox.view.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.dawoo.lotterybox.R;

/**
 * Created by rain on 18-5-4.
 */

public class ShowFeeDialog extends Dialog implements View.OnClickListener {
    TextView deposit_amount, fee_amount;
    TextView cancel_tv, sure_tv;
    View.OnClickListener sureClick;
    public ShowFeeDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
       initView();
    }

    void initView() {
        setContentView(R.layout.dialog_show_fee);
        Window win = getWindow();
        win.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        win.setAttributes(lp);
        deposit_amount = findViewById(R.id.deposit_amount);
        fee_amount = findViewById(R.id.fee_amount);
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
            }
        });
        sure_tv = findViewById(R.id.sure_tv);
        sure_tv.setOnClickListener(this);
    }

    public void setText(double depositAmount, double feeAmount) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder("金额：");
        SpannableString deposit = new SpannableString(""+depositAmount);
        deposit.setSpan(new ForegroundColorSpan(getContext().getResources().getColor(R.color.colorPrimary)), 0, deposit.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableStringBuilder.append(deposit);
        deposit_amount.setText(spannableStringBuilder);


        SpannableStringBuilder spannableStringBuilder2 = new SpannableStringBuilder("手续费：");
        SpannableString fee = new SpannableString(feeAmount==0?"免手续费":feeAmount+"");
        fee.setSpan(new ForegroundColorSpan(getContext().getResources().getColor(R.color.colorPrimary)), 0, fee.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableStringBuilder2.append(fee);
        fee_amount.setText(spannableStringBuilder2);
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