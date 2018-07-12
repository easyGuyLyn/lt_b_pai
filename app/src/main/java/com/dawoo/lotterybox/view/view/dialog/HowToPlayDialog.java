package com.dawoo.lotterybox.view.view.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dawoo.lotterybox.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 玩法说明
 * Created by benson on 18-2-11.
 */

public class HowToPlayDialog {

    private BaseDialog mBaseDialog;
    private Unbinder mUnBinder;
    @BindView(R.id.title_name_tv)
    TextView mTitleNameTv;
    @BindView(R.id.lottery_palying_way_tv)
    TextView mLotteryPalyingWayTv;
    @BindView(R.id.sample_note_tv)
    TextView mSampleNoteTv;
    @BindView(R.id.samle_betting_program_tv)
    TextView mSamleBettingProgramTv;
    @BindView(R.id.sample_open_tv)
    TextView mSampleOpenTv;
    @BindView(R.id.lottery_note_number_tv)
    TextView mLotteryNoteNumberTv;
    @BindView(R.id.close_iv)
    ImageView mCloseIv;

    public HowToPlayDialog(@NonNull Context context) {
        mBaseDialog = new BaseDialog(context, R.style.CustomDialogStyle);
        mBaseDialog.setContentView(R.layout.dialog_how_to_play);
        mUnBinder = ButterKnife.bind(this, mBaseDialog);
        mBaseDialog.setCancelable(true);
        mBaseDialog.setCanceledOnTouchOutside(false);
    }

    public void setPlayWay(String str){
        mLotteryPalyingWayTv.setText(str);
    }

    public void setSamleBettingProgramTv(String str){
        mSamleBettingProgramTv.setText(str);
    }

    public void setLotteryNOteNumber(String str){
        mLotteryNoteNumberTv.setText(str);
    }
    public void setPlayProgram(String str){
        if(str!=null&&!str.isEmpty()){
            mSampleNoteTv.setVisibility(View.VISIBLE);
            mSamleBettingProgramTv.setVisibility(View.VISIBLE);
            mSamleBettingProgramTv.setText(str);
        }
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


    @OnClick({R.id.close_iv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.close_iv:
                dismiss();
                break;
        }
    }

    public void onDestory() {
        if (mBaseDialog != null) {
            mBaseDialog.dismiss();
            mBaseDialog = null;
        }

        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
    }

}
