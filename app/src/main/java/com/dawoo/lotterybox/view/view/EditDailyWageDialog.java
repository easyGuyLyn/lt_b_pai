package com.dawoo.lotterybox.view.view;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.dawoo.lotterybox.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alex on 18-4-26.
 */

public class EditDailyWageDialog {

    private final AlertDialog alertDialog;
    @BindView(R.id.tv_notice_title)
    TextView tvNoticeTitle;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.et_input_betting_s)
    EditText etInputBettingS;
    @BindView(R.id.tv_1)
    TextView tv1;
    @BindView(R.id.et_input_avaiable_s)
    EditText etInputAvaiableS;
    @BindView(R.id.tv_2)
    TextView tv2;
    @BindView(R.id.et_input_lose_s)
    EditText etInputLoseS;
    @BindView(R.id.tv_3)
    TextView tv3;
    @BindView(R.id.et_input_dailly_wages_s)
    EditText etInputDaillyWagesS;
    @BindView(R.id.tv_4)
    TextView tv4;
    @BindView(R.id.tv_commit)
    TextView tvCommit;
    @BindView(R.id.iv_close)
    ImageView ivClose;
    private CommitCallback commitCallback;

    public EditDailyWageDialog(FragmentActivity activity) {
        View DaillyWages =  activity.getLayoutInflater().inflate(R.layout.view_daily_wage, null);
        ButterKnife.bind(this, DaillyWages);
        tvCommit.setOnClickListener(v -> {
            String bet = etInputBettingS.getText().toString();
            String avaiavable = etInputAvaiableS.getText().toString();
            String wages = etInputDaillyWagesS.getText().toString();
            String lose = etInputLoseS.getText().toString();
            commitCallback.commit(bet,avaiavable,lose,wages);
        });
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.CustomDialogStyle);
        builder.setView(DaillyWages);
        alertDialog = builder.create();
    }

    public void show(CommitCallback commitCallback) {
        this.commitCallback = commitCallback;
        alertDialog.show();
    }

    public interface CommitCallback {
        void commit(String str1, String str2, String str3, String str4);
    }


}
