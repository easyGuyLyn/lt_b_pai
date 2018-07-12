package com.dawoo.lotterybox.view.view.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.mvp.view.IBaseLotteryView;
import com.dawoo.lotterybox.mvp.view.ILotteryAView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 投注确认
 * Created by benson on 18-2-11.
 */

public class SureNoteDialog {

    private final IBaseLotteryView iBaseLotteryView;
    private final Context mContext;
    private  BaseDialog mBaseDialog;
    private  Unbinder mUnBinder;
    @BindView(R.id.title_name_tv)
    TextView mTitleNameTv;
    @BindView(R.id.lottery_palying_way_tv)
    TextView mLotteryPalyingWayTv;
    @BindView(R.id.lottery_expect_tv)
    TextView mLotteryExpectTv;
    @BindView(R.id.lottery_note_number_tv)
    TextView mLotteryNoteNumberTv;
    @BindView(R.id.lottery_note_amount_tv)
    TextView mLotteryNoteAmountTv;
    @BindView(R.id.sure_btn)
    Button mSureBtn;

    public SureNoteDialog(@NonNull Context context , IBaseLotteryView iBaseLotteryView, String playTypeName, String expect, String betCount, String totalMoney) {
        this.iBaseLotteryView = iBaseLotteryView;
        this.mContext = context;
        mBaseDialog = new BaseDialog(context, R.style.CustomDialogStyle);
        mBaseDialog.setContentView(R.layout.dialog_sure_note);
        mUnBinder = ButterKnife.bind(this, mBaseDialog);
        mBaseDialog.setCancelable(false);
        mBaseDialog.setCanceledOnTouchOutside(false);
        setData(playTypeName,expect,betCount,totalMoney);
    }

    public void setData( String playTypeName, String expect, String betCount, String totalMoney){
        mLotteryPalyingWayTv.setText(playTypeName);
        mLotteryExpectTv.setText(mContext.getString(R.string.which_periods,expect));
        mLotteryNoteNumberTv.setText(mContext.getString(R.string.all_bets,betCount));
        mLotteryNoteAmountTv.setText(mContext.getString(R.string.total_money,totalMoney));
        mBaseDialog.show();
    }

    public void dismiss() {
        if (mBaseDialog != null && mBaseDialog.isShowing()) {
            mBaseDialog.dismiss();
        }
    }

    public void setRightBtnClickListener(View.OnClickListener listener) {
        if (mBaseDialog != null && listener != null) {
            mSureBtn.setOnClickListener(listener);
        }
    }

    @OnClick({R.id.sure_btn, R.id.close_iv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.sure_btn:
                iBaseLotteryView.onSureBetOrder();
                dismiss();
                break;
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
