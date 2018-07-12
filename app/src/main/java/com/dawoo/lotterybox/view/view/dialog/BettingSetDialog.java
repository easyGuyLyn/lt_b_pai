package com.dawoo.lotterybox.view.view.dialog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.SwitchCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.bean.BetParamBean;
import com.dawoo.lotterybox.view.activity.lottery.BaseLotteryAActivity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 投注设置
 * Created by benson on 18-2-11.
 */

public class BettingSetDialog {

    @BindView(R.id.title_name_tv)
    TextView mTitleNameTv;
    @BindView(R.id.unite_tv)
    TextView mUniteTv;
    @BindView(R.id.rb_unite)
    RadioGroup mRadioGroupUnite;
    @BindView(R.id.unite_yuan_tv)
    RadioButton mUniteYuanTv;
    @BindView(R.id.unite_jiao_tv)
    RadioButton mUniteJiaoTv;
    @BindView(R.id.unite_fen_tv)
    RadioButton mUniteFenTv;
    @BindView(R.id.mode_tv)
    TextView mModeTv;
    @BindView(R.id.rg_bet_model)
    RadioGroup mRadioGroupModel;
    @BindView(R.id.mode_one_yuan_tv)
    RadioButton mModeOneYuanTv;
    @BindView(R.id.mode_two_yuan_tv)
    RadioButton mModeTwoYuanTv;
    @BindView(R.id.lable_times_tv)
    TextView mLableTimesTv;
    @BindView(R.id.value_times_tv)
    TextView mValueTimesTv;
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
    @BindView(R.id.switch_order_tip)
    SwitchCompat mSwitchOrderTip;
    @BindView(R.id.cancle_btn)
    Button mCancleBtn;
    @BindView(R.id.sure_btn)
    Button mSureBtn;
    @BindView(R.id.close_iv)
    ImageView mCloseIv;
    private Context mContext;
    private BaseDialog mBaseDialog;
    private Unbinder mUnBinder;
    private BetParamBean betParamBean;
    private String mBonusModel = "1";
    private double mRebate = -1;
    DecimalFormat df = new DecimalFormat("######0.0");
    private BottomNumDialog mBottomNumDialog;

    public BettingSetDialog(@NonNull Context context, BetParamBean betParamBean) {
        this.mContext = context;
        mBaseDialog = new BaseDialog(context, R.style.CustomDialogStyle);
        mBaseDialog.setContentView(R.layout.dialog_betting_set);
        mUnBinder = ButterKnife.bind(this, mBaseDialog);
        mBaseDialog.setCancelable(true);
        mBaseDialog.setCanceledOnTouchOutside(false);
        this.betParamBean = betParamBean;
        initData();
        initListener();
    }

    @SuppressLint("SetTextI18n")
    private void initData() {
        mBonusModel = betParamBean.getBonusModel();
        if ("1".equals(betParamBean.getBonusModel()))
            mUniteYuanTv.setChecked(true);
        else if ("10".equals(betParamBean.getBonusModel()))
            mUniteJiaoTv.setChecked(true);
        else if ("100".equals(betParamBean.getBonusModel()))
            mUniteFenTv.setChecked(true);
        if (1 == betParamBean.getModel())
            mModeOneYuanTv.setChecked(true);
        else {
            mModeTwoYuanTv.setChecked(true);
        }
        mValueTimesTv.setText(betParamBean.getMultiple());
        if (betParamBean.isBetHint())
            mSwitchOrderTip.setChecked(true);
        else mSwitchOrderTip.setChecked(false);
        if (betParamBean.getLotteryOddBeans() != null && betParamBean.getLotteryOddBeans().size() != 0 && betParamBean.getLotteryOddBeans().get(0).getRebate() != 0) {
            mSeekBar.setMax((int) (betParamBean.getLotteryOddBeans().get(0).getRebate() * 1000)); //既使是多赔率，rebate都一样。
            StringBuffer sbValueRate = new StringBuffer();
            for (int i = 0; i < betParamBean.getLotteryOddBeans().size(); i++) {
                if (betParamBean.getNowRebate() == -1) {
                    sbValueRate.append(df.format(betParamBean.getLotteryOddBeans().get(i).getOdd() - betParamBean.getLotteryOddBeans().get(i).getBaseNum() * betParamBean.getLotteryOddBeans().get(i).getRebate()));
                } else {
                    sbValueRate.append(df.format(betParamBean.getLotteryOddBeans().get(i).getOdd() - betParamBean.getLotteryOddBeans().get(i).getBaseNum() * betParamBean.getNowRebate()));
                }
                if (i < (betParamBean.getLotteryOddBeans().size() - 1)) {
                    sbValueRate.append("|");
                }
            }
            mValueRateTv.setText(sbValueRate);
            if (betParamBean.getNowRebate() == -1) {
                mValueRebateTv.setText(df.format(betParamBean.getLotteryOddBeans().get(0).getRebate() * 100) + "%");
                mSeekBar.setProgress((int) (betParamBean.getLotteryOddBeans().get(0).getRebate() * 1000));
            } else {
                mValueRebateTv.setText(df.format(betParamBean.getNowRebate() * 100) + "%");
                mSeekBar.setProgress((int) (betParamBean.getNowRebate() * 1000));
            }
        }

    }

    private void initListener() {
        mRadioGroupUnite.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.unite_yuan_tv) {
                    mBonusModel = "1";
                } else if (checkedId == R.id.unite_jiao_tv) {
                    mBonusModel = "10";
                } else {
                    mBonusModel = "100";
                }
            }
        });

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mRebate = progress * 0.001;
                StringBuffer sbValueRate = new StringBuffer();
                for (int i = 0; i < betParamBean.getLotteryOddBeans().size(); i++) {
                    sbValueRate.append(df.format(betParamBean.getLotteryOddBeans().get(i).getOdd() - betParamBean.getLotteryOddBeans().get(i).getBaseNum() * mRebate));
                    if (i < (betParamBean.getLotteryOddBeans().size() - 1)) {
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
        });

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


    public void onDestory() {
        if (mBaseDialog != null) {
            mBaseDialog.dismiss();
            mBaseDialog = null;
        }

        if (mUnBinder != null) {
            mUnBinder.unbind();
        }

        if (mBottomNumDialog != null) {
            mBottomNumDialog.onDestory();
        }
    }

    @OnClick({R.id.cancle_btn, R.id.sure_btn, R.id.close_iv, R.id.value_times_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cancle_btn:
                doCancleBtn();
                break;
            case R.id.sure_btn:
                doSureBtn();
                break;
            case R.id.close_iv:
                dismiss();
                break;
            case R.id.value_times_tv:
                createBottomNum();
                break;
            default:
        }
    }

    void doCancleBtn() {
        mUniteYuanTv.setChecked(true);
        mModeTwoYuanTv.setChecked(true);
        mValueTimesTv.setText("1");
        StringBuffer sbValueRate = new StringBuffer();
        for (int i = 0; i < betParamBean.getLotteryOddBeans().size(); i++) {
            sbValueRate.append(betParamBean.getLotteryOddBeans().get(i).getOdd());
            if (i < (betParamBean.getLotteryOddBeans().size() - 1)) {
                sbValueRate.append("|");
            }
        }
        mValueRateTv.setText(sbValueRate);
        mValueRebateTv.setText(betParamBean.getLotteryOddBeans().get(0).getRebate() * 100 + "%");
        mSeekBar.setProgress(0);
        mSwitchOrderTip.setChecked(false);
    }

    void doSureBtn() {
        betParamBean.setBonusModel(mBonusModel);
        String mVa = mValueTimesTv.getText().toString().trim();
        if (!TextUtils.isEmpty(mVa))
            betParamBean.setMultiple(mVa);
        betParamBean.setBetHint(mSwitchOrderTip.isChecked());
        if (mRebate != -1) {
            betParamBean.setNowRebate(new BigDecimal(mRebate).setScale(3, RoundingMode.DOWN).doubleValue());
        }

        if (mContext instanceof BaseLotteryAActivity) {
            ((BaseLotteryAActivity) mContext).setShowBetInfo(betParamBean.getBetCount());
        }
        dismiss();
    }


    void createBottomNum() {
        if (mBottomNumDialog == null) {
            mBottomNumDialog = new BottomNumDialog(mContext, mValueTimesTv);
            mBottomNumDialog.show();
        } else {
            mBottomNumDialog.show();
        }
    }
}
