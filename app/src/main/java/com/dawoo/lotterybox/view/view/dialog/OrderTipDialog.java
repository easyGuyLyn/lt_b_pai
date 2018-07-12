package com.dawoo.lotterybox.view.view.dialog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.bean.BetParamBean;
import com.dawoo.lotterybox.view.activity.lottery.BaseLotteryAActivity;
import com.dawoo.lotterybox.view.view.popu.MoneyUnitPopu;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 下单提示
 * Created by benson on 18-2-11.
 */

public class OrderTipDialog implements SeekBar.OnSeekBarChangeListener {

    private BaseDialog mBaseDialog;
    private Context mContext;
    private Unbinder mUnbinder;
    @BindView(R.id.title_name_tv)
    TextView mTitleNameTv;
    @BindView(R.id.lable_rate_tv)
    TextView mLableRateTv;
    @BindView(R.id.value_rate_tv)
    TextView mValueRateTv;
    @BindView(R.id.lable_rebate_tv)
    TextView mLableRebateTv;
    @BindView(R.id.value_rebate_tv)
    TextView mValueRebateTv;
    @BindView(R.id.seek_bar)
    SeekBar mSeekBar;
    @BindView(R.id.lable_times_tv)
    TextView mLableTimesTv;
    @BindView(R.id.value_times_tv)
    TextView mValueTimesTv;
    @BindView(R.id.arrw_down_iv)
    ImageView mArrwDownIv;
    @BindView(R.id.money_tv)
    TextView mMoneyTv;
    @BindView(R.id.money_ll)
    LinearLayout mMoneyLl;
    @BindView(R.id.num_tv)
    TextView mNumTv;
    @BindView(R.id.gold_coins_tv)
    TextView mGoldCoinsTv;
    @BindView(R.id.cancle_btn)
    Button mCancleBtn;
    @BindView(R.id.sure_btn)
    Button mSureBtn;
    @BindView(R.id.close_iv)
    ImageView mCloseIv;
    private MoneyUnitPopu mMoneyUnitPopu;
    private BottomNumDialog mBottomNumDialog;
    private BetParamBean mBetParamBean;
    private double mRebate = -1;

    public OrderTipDialog(@NonNull Context context, BetParamBean betParamBean) {
        mContext = context;
        mBaseDialog = new BaseDialog(context, R.style.CustomDialogStyle);
        mBaseDialog.setContentView(R.layout.dialog_order_tip);
        mUnbinder = ButterKnife.bind(this, mBaseDialog);
        mBaseDialog.setCancelable(false);
        mBaseDialog.setCanceledOnTouchOutside(false);
        mSeekBar.setOnSeekBarChangeListener(this);
        this.mBetParamBean = betParamBean;
        initData();
    }

    @SuppressLint("SetTextI18n")
    private void initData() {
        mNumTv.setText(mContext.getString(R.string.all_bets,mBetParamBean.getBetCount()+""));
        mGoldCoinsTv.setText(mContext.getString(R.string.total_money,mBetParamBean.getBetAmount()));
        mValueTimesTv.setText(mBetParamBean.getMultiple());
        if ("1".equals(mBetParamBean.getBonusModel()))
            mMoneyTv.setText("元");
        else if ("10".equals(mBetParamBean.getBonusModel()))
            mMoneyTv.setText("角");
        else if ("100".equals(mBetParamBean.getBonusModel()))
            mMoneyTv.setText("分");
        if (mBetParamBean.getLotteryOddBeans()!=null && mBetParamBean.getLotteryOddBeans().get(0).getRebate() != 0) {
            mSeekBar.setMax((int) (mBetParamBean.getLotteryOddBeans().get(0).getRebate() * 1000)); //既使是多赔率，rebate都一样。
            StringBuffer sbValueRate = new StringBuffer();
            for (int i = 0; i < mBetParamBean.getLotteryOddBeans().size(); i++) {
                if (mBetParamBean.getNowRebate() == -1) {
                    sbValueRate.append(mBetParamBean.getLotteryOddBeans().get(i).getOdd() - mBetParamBean.getLotteryOddBeans().get(i).getBaseNum() * mBetParamBean.getLotteryOddBeans().get(i).getRebate() + "");
                } else {
                    sbValueRate.append(df.format(mBetParamBean.getLotteryOddBeans().get(i).getOdd() - mBetParamBean.getLotteryOddBeans().get(i).getBaseNum() * mBetParamBean.getNowRebate()));
                }
                if (i < (mBetParamBean.getLotteryOddBeans().size() - 1)) {
                    sbValueRate.append("|");
                }
            }
            mValueRateTv.setText(sbValueRate);
            if (mBetParamBean.getNowRebate() == -1) {
                mValueRebateTv.setText(mBetParamBean.getLotteryOddBeans().get(0).getRebate()* 100 + "%");
                mSeekBar.setProgress((int) (mBetParamBean.getLotteryOddBeans().get(0).getRebate() * 1000));
            } else {
                mValueRebateTv.setText(df.format(mBetParamBean.getNowRebate() * 100) + "%");
                mSeekBar.setProgress((int) (mBetParamBean.getNowRebate() * 1000));
            }
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

    public void setRightBtnClickListener(View.OnClickListener listener) {
        if (mBaseDialog != null && listener != null) {
            mSureBtn.setOnClickListener(listener);
        }
    }

    @OnClick({R.id.value_times_tv, R.id.money_ll, R.id.cancle_btn, R.id.close_iv, R.id.sure_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.value_times_tv:
                createBottomNum();
                break;
            case R.id.money_ll:
                createPopuWindow();
                break;
            case R.id.cancle_btn:
                dismiss();
                break;
            case R.id.close_iv:
                dismiss();
                break;
            case R.id.sure_btn:
                dismiss();
                String money = mMoneyTv.getText().toString().trim();
                if ("元".equals(money))
                    mBetParamBean.setBonusModel("1");
                else if ("角".equals(money))
                    mBetParamBean.setBonusModel("10");
                else mBetParamBean.setBonusModel("100");
                if (mRebate != -1)
                    mBetParamBean.setNowRebate(new BigDecimal(mRebate).setScale(3, RoundingMode.DOWN).doubleValue());
                if (mContext instanceof BaseLotteryAActivity) {
                    ((BaseLotteryAActivity) mContext).setShowBetInfo(mBetParamBean.getBetCount());
                    ((BaseLotteryAActivity) mContext).goPlaceOrder();
                }
                break;
        }
    }

    DecimalFormat df = new DecimalFormat("######0.0");

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        mRebate = progress * 0.001;
        StringBuffer sbValueRate = new StringBuffer();
        for (int i = 0; i < mBetParamBean.getLotteryOddBeans().size(); i++) {
            sbValueRate.append(df.format(mBetParamBean.getLotteryOddBeans().get(i).getOdd() - mBetParamBean.getLotteryOddBeans().get(i).getBaseNum() * mRebate ));
            if (i < (mBetParamBean.getLotteryOddBeans().size() - 1)) {
                sbValueRate.append("|");
            }
        }
        mValueRateTv.setText(sbValueRate);
        mValueRebateTv.setText(df.format(mRebate * 100) + "%");

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    void createPopuWindow() {
        if (mMoneyUnitPopu == null) {
            mMoneyUnitPopu = new MoneyUnitPopu(mContext, mMoneyLl, mMoneyTv);
            mMoneyUnitPopu.setMoneyModeChange(mMoneyModeChange);
        } else {
            mMoneyUnitPopu.doTogglePopupWindow();
        }
    }


    /**
     * 消除引用
     */
    public void onDestory() {
        if (mMoneyUnitPopu != null) {
            mMoneyUnitPopu.onDestory();
        }

        if (mBottomNumDialog != null) {
            mBottomNumDialog.onDestory();
        }

        if (mBaseDialog != null) {
            mBaseDialog.dismiss();
            mBaseDialog = null;
        }

        if (mUnbinder != null) {
            mContext = null;
            mMoneyUnitPopu = null;
            mUnbinder.unbind();
        }
    }

    void createBottomNum() {
        if (mBottomNumDialog == null) {
            mBottomNumDialog = new BottomNumDialog(mContext, mValueTimesTv);
            mBottomNumDialog.show();
        } else {
            mBottomNumDialog.show();
        }
        mBottomNumDialog.setListener(mBottomNumDialogShowOrHide);
    }

    MoneyUnitPopu.MoneyModeChange mMoneyModeChange = new MoneyUnitPopu.MoneyModeChange() {
        @Override
        public void change() {
            String moneyMode = mMoneyTv.getText().toString().trim();
            if ("元".equals(moneyMode))
                mBetParamBean.setBonusModel("1");
            else if ("角".equals(moneyMode))
                mBetParamBean.setBonusModel("10");
            else if ("分".equals(moneyMode))
                mBetParamBean.setBonusModel("100");
            computeMoney();
            mGoldCoinsTv.setText(mContext.getString(R.string.total_money,mBetParamBean.getBetAmount()));
        }
    };

    BottomNumDialog.BottomNumDialogShowOrHide  mBottomNumDialogShowOrHide = new BottomNumDialog.BottomNumDialogShowOrHide() {
        @Override
        public void show() {

        }

        @Override
        public void hide() {
            String expect = mValueTimesTv.getText().toString().trim();
            if (!TextUtils.isEmpty(expect)){
                mBetParamBean.setMultiple(expect);
                computeMoney();
                mGoldCoinsTv.setText(mContext.getString(R.string.total_money,mBetParamBean.getBetAmount()));
            }
        }
    };

    private void computeMoney(){
        double allBetGold = 0;  //总投注金币
        if ("1".equals(mBetParamBean.getBonusModel())) {
            allBetGold = mBetParamBean.getBetCount() * 1;
        } else if ("10".equals(mBetParamBean.getBonusModel())) {
            allBetGold = mBetParamBean.getBetCount() * 0.1;
        } else if ("100".equals(mBetParamBean.getBonusModel())) {
            allBetGold = mBetParamBean.getBetCount() * 0.01;
        }
        allBetGold *= mBetParamBean.getModel();
        allBetGold *= Long.valueOf(mBetParamBean.getMultiple());
        mBetParamBean.setBetAmount(String.valueOf(allBetGold));
    }

}
