package com.dawoo.lotterybox.view.view.popu;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.dawoo.lotterybox.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by benson on 18-2-11.
 */

public class MoneyUnitPopu {
    @BindView(R.id.yuan_tv)
    TextView mYuanTv;
    @BindView(R.id.jiao_tv)
    TextView mJiaoTv;
    @BindView(R.id.fen_tv)
    TextView mFenTv;
    private View mRootView;
    private View mContentView;
    private PopupWindow mPopWindow;
    private TextView mMoneyTv;
    private Unbinder mUnBinder;
    private long mCurrentTime;
    private long mInterval = 1200;


    public MoneyUnitPopu(Context context, View rootView, TextView moneyTv) {
        createPopuWindow(context, rootView, moneyTv);
    }

    private void createPopuWindow(Context context, View rootView, TextView moneyTv) {
        if (mContentView == null) {
            mContentView = LayoutInflater.from(context).inflate(R.layout.dialog_order_tip_money_unit_set, null);
            mUnBinder = ButterKnife.bind(this, mContentView);//DensityUtil.dp2px(context, 189), DensityUtil.dp2px(context, 420)
            mPopWindow = new PopupWindow(mContentView);
            mPopWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
            mPopWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
            mPopWindow.setTouchable(true);
            mPopWindow.setOutsideTouchable(true);
            mPopWindow.setBackgroundDrawable(new BitmapDrawable(context.getResources(), (Bitmap) null));
            mRootView = rootView;
            mMoneyTv = moneyTv;
        }


        doTogglePopupWindow();
    }

    public void doTogglePopupWindow() {
        if (mPopWindow == null) {
            return;
        }

        if (System.currentTimeMillis() - mCurrentTime > mInterval) {
            mCurrentTime = System.currentTimeMillis();

            if (mPopWindow.isShowing()) {
                mPopWindow.dismiss();
            } else {
                mPopWindow.showAsDropDown(mRootView);
            }
        }
    }


    private void dismiss() {
        if (mPopWindow == null) {
            return;
        }
        mPopWindow.dismiss();
    }


    @OnClick({R.id.yuan_tv, R.id.jiao_tv, R.id.fen_tv})
    public void onViewClicked2(View view) {
        if (mMoneyTv == null) {
            return;
        }

        switch (view.getId()) {
            case R.id.yuan_tv:
                mMoneyTv.setText(mYuanTv.getText().toString().trim());
                if (mMoneyModeChange != null)
                    mMoneyModeChange.change();
                dismiss();
                break;
            case R.id.jiao_tv:
                mMoneyTv.setText(mJiaoTv.getText().toString().trim());
                if (mMoneyModeChange != null)
                    mMoneyModeChange.change();
                dismiss();
                break;
            case R.id.fen_tv:
                mMoneyTv.setText(mFenTv.getText().toString().trim());
                if (mMoneyModeChange != null)
                    mMoneyModeChange.change();
                dismiss();
                break;
        }
    }

    private MoneyModeChange mMoneyModeChange;

    public void setMoneyModeChange(MoneyModeChange mMoneyModeChange) {
        this.mMoneyModeChange = mMoneyModeChange;
    }

    public interface MoneyModeChange {

        void change();

    }

    public void onDestory() {
        if (mPopWindow != null) {
            mPopWindow.dismiss();
            mPopWindow = null;
        }

        if (mUnBinder != null) {
            mMoneyTv = null;
            mUnBinder.unbind();
        }
    }
}
