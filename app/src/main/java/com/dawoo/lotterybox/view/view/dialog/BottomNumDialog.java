package com.dawoo.lotterybox.view.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dawoo.coretool.ToastUtil;
import com.dawoo.lotterybox.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 下面弹出框
 * 数字输入
 * Created by benson on 18-2-12.
 */

public class BottomNumDialog {

    private Context mContext;
    public Dialog mBaseDialog;
    private Unbinder mUnbinder;
    @BindView(R.id.times_five_btn)
    TextView mTimesFiveBtn;
    @BindView(R.id.times_ten_btn)
    TextView mTimesTenBtn;
    @BindView(R.id.times_twenty_btn)
    TextView mTimesTwentyBtn;
    @BindView(R.id.one_key_btn)
    TextView mOneKeyBtn;
    @BindView(R.id.two_key_btn)
    TextView mTwoKeyBtn;
    @BindView(R.id.three_key_btn)
    TextView mThreeKeyBtn;
    @BindView(R.id.four_key_btn)
    TextView mFourKeyBtn;
    @BindView(R.id.five_key_btn)
    TextView mFiveKeyBtn;
    @BindView(R.id.six_key_btn)
    TextView mSixKeyBtn;
    @BindView(R.id.seven_key_btn)
    TextView mSevenKeyBtn;
    @BindView(R.id.eight_key_btn)
    TextView mEightKeyBtn;
    @BindView(R.id.nine_key_btn)
    TextView mNineKeyBtn;
    @BindView(R.id.confirm_key_btn)
    TextView mConfirmKeyBtn;
    @BindView(R.id.zero_key_btn)
    TextView mZeroKeyBtn;
    @BindView(R.id.back_key_btn)
    FrameLayout mBackKeyBtn;
    @BindView(R.id.ll_bottom_num)
    public LinearLayout mLLBottomNum;
    TextView mValueTimesTv;
    StringBuilder mSb;
    private boolean mIsNeedClear = false;
    private BottomNumDialogShowOrHide bottomNumDialogShowOrHide;


    public BottomNumDialog(@NonNull Context context, TextView valueTimesTv) {
        mContext = context;
        mValueTimesTv = valueTimesTv;
        mBaseDialog = new Dialog(context, R.style.BottomDialogStyle);
        setParams();
        mBaseDialog.setContentView(R.layout.dialog_bottom_num);
        mUnbinder = ButterKnife.bind(this, mBaseDialog);
        // setOnTouchListener();
        mBaseDialog.setCancelable(false);
        mBaseDialog.setCanceledOnTouchOutside(false);
        setLLBottomNumDraw();
    }

    private void setLLBottomNumDraw() {
        ViewTreeObserver vto = mLLBottomNum.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mLLBottomNum.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                if (bottomNumDialogShowOrHide != null)
                    bottomNumDialogShowOrHide.show();

            }
        });
    }

    public void show() {
        if (mBaseDialog != null && !mBaseDialog.isShowing()) {
            mBaseDialog.show();
            mSb = new StringBuilder();
            mIsNeedClear = true;
            if (bottomNumDialogShowOrHide != null)
                bottomNumDialogShowOrHide.show();
        }
    }

    public void dismiss() {
        if (mBaseDialog != null && mBaseDialog.isShowing()) {
            mBaseDialog.dismiss();
            if (bottomNumDialogShowOrHide != null)
                bottomNumDialogShowOrHide.hide();
        }
    }

    void setParams() {
        Window win = mBaseDialog.getWindow();
        win.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        win.setGravity(Gravity.BOTTOM);
        win.setAttributes(lp);
    }

    @OnClick({R.id.times_five_btn,
            R.id.times_ten_btn,
            R.id.times_twenty_btn,
            R.id.one_key_btn,
            R.id.two_key_btn,
            R.id.three_key_btn,
            R.id.four_key_btn,
            R.id.five_key_btn,
            R.id.six_key_btn,
            R.id.seven_key_btn,
            R.id.eight_key_btn,
            R.id.nine_key_btn,
            R.id.confirm_key_btn,
            R.id.zero_key_btn,
            R.id.back_key_btn})
    public void onViewClicked(View view) {
        if (mValueTimesTv == null) {
            return;
        }
        doTextClear();

        switch (view.getId()) {
            case R.id.times_five_btn:
                mValueTimesTv.setText("5");
                break;
            case R.id.times_ten_btn:
                mValueTimesTv.setText("10");
                break;
            case R.id.times_twenty_btn:
                mValueTimesTv.setText("20");
                break;
            case R.id.one_key_btn:
                if (isMaxMultiple())
                    return;
                mSb.append("1");
                mValueTimesTv.setText(mSb.toString());
                break;
            case R.id.two_key_btn:
                if (isMaxMultiple())
                    return;
                mSb.append("2");
                mValueTimesTv.setText(mSb.toString());
                break;
            case R.id.three_key_btn:
                if (isMaxMultiple())
                    return;
                mSb.append("3");
                mValueTimesTv.setText(mSb.toString());
                break;
            case R.id.four_key_btn:
                if (isMaxMultiple())
                    return;
                mSb.append("4");
                mValueTimesTv.setText(mSb.toString());
                break;
            case R.id.five_key_btn:
                if (isMaxMultiple())
                    return;
                mSb.append("5");
                mValueTimesTv.setText(mSb.toString());
                break;
            case R.id.six_key_btn:
                if (isMaxMultiple())
                    return;
                mSb.append("6");
                mValueTimesTv.setText(mSb.toString());
                break;
            case R.id.seven_key_btn:
                if (isMaxMultiple())
                    return;
                mSb.append("7");
                mValueTimesTv.setText(mSb.toString());
                break;
            case R.id.eight_key_btn:
                if (isMaxMultiple())
                    return;
                mSb.append("8");
                mValueTimesTv.setText(mSb.toString());
                break;
            case R.id.nine_key_btn:
                if (isMaxMultiple())
                    return;
                mSb.append("9");
                mValueTimesTv.setText(mSb.toString());
                break;
            case R.id.confirm_key_btn:
                dismiss();
                break;
            case R.id.zero_key_btn:
                if (isMaxMultiple())
                    return;
                if (TextUtils.isEmpty(mSb)) {
                    mSb.append("1");
                    return;
                }
                mSb.append("0");
                mValueTimesTv.setText(mSb.toString());
                break;
            case R.id.back_key_btn:
                if (!TextUtils.isEmpty(mSb)) {
                    mSb.deleteCharAt(mSb.length() - 1);
                    mValueTimesTv.setText(mSb.toString());
                }
                break;
        }
    }

    boolean isMaxMultiple() {
        String multiple = mValueTimesTv.getText().toString().trim();
        if (!TextUtils.isEmpty(multiple)) {
            int length = multiple.length();
            if (length > 10) {
                System.out.println(length);
                return true;
            }
        }
        return false;
    }


    void doTextClear() {
        if (mIsNeedClear) {
            mValueTimesTv.setText("");
        }
        mIsNeedClear = false;
    }

    public void onDestory() {
        if (mBaseDialog != null) {
            mBaseDialog.dismiss();
            mBaseDialog = null;
        }

        if (mUnbinder != null) {
            mContext = null;
            mSb = null;
            mValueTimesTv = null;
            mUnbinder.unbind();
        }
    }

    public void setListener(BottomNumDialogShowOrHide bottomNumDialogShowOrHide) {
        this.bottomNumDialogShowOrHide = bottomNumDialogShowOrHide;
    }

    public interface BottomNumDialogShowOrHide {

        void show();

        void hide();
    }
}
