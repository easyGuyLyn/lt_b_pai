package com.dawoo.lotterybox.util;


import android.annotation.SuppressLint;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dawoo.lotterybox.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alex on 18-4-25.
 *
 * @author alex
 */

public class NoticeDialog {

    @BindView(R.id.tv_notice_title)
    TextView tvNoticeTitle;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.tv_cancle)
    TextView tvCancle;
    @BindView(R.id.tv_commit)
    TextView tvCommit;
    @BindView(R.id.iv_close)
    ImageView ivClose;
    AlertDialog alertDialog;
    private CommitCallback commitCallback;

    public NoticeDialog(FragmentActivity activity) {
        create(activity);
    }

    public void create(FragmentActivity activity) {
        @SuppressLint("InflateParams")
        View noticeDialog = activity.getLayoutInflater().inflate(R.layout.view_tip_bank_dialog, null);
        ButterKnife.bind(this, noticeDialog);
        tvCancle.setOnClickListener(v -> alertDialog.dismiss());

        ivClose.setOnClickListener(v -> alertDialog.dismiss());
        tvCommit.setOnClickListener(v -> commitCallback.commit(alertDialog));
        AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.CustomDialogStyle);
        builder.setView(noticeDialog);
        alertDialog = builder.create();

    }

    public void show(CommitCallback commitCallback) {
        this.commitCallback=commitCallback;
         alertDialog.show();
    }

    public void destory() {
        if (alertDialog!=null){
            alertDialog.dismiss();
            alertDialog = null;
        }

    }
    public NoticeDialog setTitle(String title){
        tvNoticeTitle.setText(title);
        return this;
    }
    public NoticeDialog setContentString(String contentString){
        tvContent.setText(contentString);
        return this;
    }

    public interface CommitCallback {
        void commit(AlertDialog alertDialog);
    }

}
