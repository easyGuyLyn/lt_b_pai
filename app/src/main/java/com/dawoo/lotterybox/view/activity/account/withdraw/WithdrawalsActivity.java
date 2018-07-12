package com.dawoo.lotterybox.view.activity.account.withdraw;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.dawoo.lotterybox.ConstantValue;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.view.activity.team.base.BindLayout;
import com.dawoo.lotterybox.view.activity.team.base.OnMultiClickListener;
import com.dawoo.lotterybox.view.activity.team.base.SuperBaseActivity;
import com.dawoo.lotterybox.bean.WithDrawBean;
import com.dawoo.lotterybox.bean.WithDrawsFeeBean;
import com.dawoo.lotterybox.mvp.presenter.WithDrawsPresenter;
import com.dawoo.lotterybox.mvp.view.IWithDrawView;
import com.dawoo.lotterybox.net.rx.DefaultCallback;
import com.dawoo.lotterybox.util.ActivityUtil;
import com.dawoo.lotterybox.util.NumberFormaterUtils;
import com.dawoo.lotterybox.util.SingleToast;
import com.dawoo.lotterybox.view.activity.account.RechargeRecordActivity;
import com.dawoo.lotterybox.view.activity.account.bank.BandCardScuessActviity;
import com.dawoo.lotterybox.view.activity.account.WithDrawsAuditActivity;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;


import butterknife.BindView;

/**
 * Created by alex on 18-4-3.
 *
 * @author alex
 *         用户提现界面
 */
@BindLayout(R.layout.activity_withdrawals)
public class WithdrawalsActivity extends SuperBaseActivity implements IWithDrawView,DefaultCallback {
    @BindView(R.id.tv_account_title)
    TextView tvAccountTitle;
    @BindView(R.id.tv_account_name)
    TextView tvAccountName;
    @BindView(R.id.tv_yue_title)
    TextView tvYueTitle;
    @BindView(R.id.tv_yue_name)
    TextView tvYueName;
    @BindView(R.id.tv_edu_title)
    TextView tvEduTitle;
    @BindView(R.id.tv_edu_name)
    TextView tvEduName;
    @BindView(R.id.tv_can_not_tie)
    TextView tvCanNotTie;
    @BindView(R.id.tv_need_touzhu)
    TextView tvNeedTouzhu;
    @BindView(R.id.tv_less_tie_time)
    TextView tvLessTieTime;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.iv_bank_icon)
    ImageView ivBankIcon;
    @BindView(R.id.tv_bank_card_name)
    TextView tvBankCardName;
    @BindView(R.id.tv_bank_card_number)
    TextView tvBankCardNumber;
    @BindView(R.id.et_money_input)
    EditText etMoneyInput;
    @BindView(R.id.tv_go_next)
    TextView tvGoNext;
    @BindView(R.id.service_iv)
    RelativeLayout rlBack;
    @BindView(R.id.title_name)
    TextView titleName;
    @BindView(R.id.setting_iv)
    RelativeLayout rlService;
    @BindView(R.id.tv_get_all_balance)
    TextView tvGetAllBalance;
    @BindView(R.id.tv_get_all_adult)
    TextView tvGetAllAdult;
    @BindView(R.id.tv_lest_withdraws_time)
    TextView tvLestWithdrawsTime;
    @BindView(R.id.tv_fee_cost)
    TextView tvFeeCost;
    @BindView(R.id.tv_admi_cost)
    TextView tvAdmiCost;
    @BindView(R.id.tv_off_cost)
    TextView tvOffCost;
    @BindView(R.id.tv_finally_money)
    TextView tvFinallyMoney;
    @BindView(R.id.tv_show_message)
    TextView tvShowMessage;
    @BindView(R.id.btn_go)
    Button btnGo;
    @BindView(R.id.ll_nothing_root)
    LinearLayout llNothingRoot;
    @BindView(R.id.ll_content_body)
    LinearLayout llContentBody;
    @BindView(R.id.sv_body)
    ScrollView svBody;
    @BindView(R.id.ll_go_bank_list)
    RelativeLayout rlGoBankList;
    @BindView(R.id.tv_jihai)
    TextView tvJihai;
    @BindView(R.id.tv_lest_withdraws_time_detail)
    TextView tvLestWithdrawsTimeDetail;
    @BindView(R.id.tv_fee_cost_detail)
    TextView tvFeeCostDetail;
    @BindView(R.id.tv_admi_cost_detail)
    TextView tvAdmiCostDetail;
    @BindView(R.id.tv_off_cost_detail)
    TextView tvOffCostDetail;
    @BindView(R.id.tv_finally_money_detail)
    TextView tvFinallyMoneyDetail;
    private WithDrawsPresenter<WithdrawalsActivity> withDrawsPresenter;
    private String balance;
    private double withdrawMaxNum;
    private double withdrawMinNum;
    private int reminder;


    @Override
    protected void initViews() {
        RxBus.get().register(this);
    }

    @Override
    protected void initData() {
        withDrawsPresenter = new WithDrawsPresenter<>(this, this);
        withDrawsPresenter.withDrawInit();
        initEvent();
    }

    private void initEvent() {
        rlBack.setOnClickListener(v -> finish());
        rlService.setOnClickListener(v -> ActivityUtil.gotoCustomerService());
        goNextUnableClick();
        tvGoNext.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                String money = tvFinallyMoneyDetail.getText().toString();
                String inputMoney = etMoneyInput.getText().toString();
                if ("".equals(inputMoney)) {
                    ToastUtils.showShort("输入为空");
                    return;
                }
                if ("".equals(money)) {
                    return;
                }
                Double dMoney = NumberFormaterUtils.formaterS2D(money);
                Double dInputMoney = NumberFormaterUtils.formaterS2D(inputMoney);
                if (dInputMoney < withdrawMinNum || dInputMoney > withdrawMaxNum) {
                    ToastUtils.showShort("取款最小值为" + withdrawMinNum + "," + "最大值为" + withdrawMaxNum + ",");

                    return;
                }
                if (dMoney > 0) {
                    withDrawsPresenter.applyWithDraws(dInputMoney + "");
                } else {
                    ToastUtils.showShort("输入的取款金额有误");
                }
            }
        });

        tvGetAllBalance.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                etMoneyInput.setText(NumberFormaterUtils.formaterS2S(balance));
                etMoneyInput.setSelection(etMoneyInput.getText().length());
            }
        });
        tvJihai.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                Intent intent = new Intent(WithdrawalsActivity.this, WithDrawsAuditActivity.class);
                startActivity(intent);
            }
        });

        btnGo.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                if ("已存在未审核取款订单".equals(tvShowMessage.getText().toString())) {
                    Intent intent = new Intent(WithdrawalsActivity.this, RechargeRecordActivity.class);
                    intent.putExtra("isDeposit", false);
                    ActivityUtils.startActivity(intent);
                } else {
                    ActivityUtil.gotoCustomerService();
                }
            }
        });

        rlGoBankList.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                ActivityUtils.startActivity(BandCardScuessActviity.class);
            }
        });
        //监听是否完成输入
        etMoneyInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @SuppressLint("SetTextI18n")
            @Override
            public void afterTextChanged(Editable s) {
                goNextUnableClick();
                if (etMoneyInput.getText().length() == 0) {
                    tvAdmiCostDetail.setText("0.00");
                    tvOffCostDetail.setText("0.00");
                    tvFeeCostDetail.setText("0.00");
                    tvFinallyMoneyDetail.setText("0.00");
                } else {

                    new Handler().postDelayed(() -> processInput(),800);

                }

            }
        });
    }

    private void processInput() {
        String finallyAmout = etMoneyInput.getText().toString().trim();
        finallyAmout = finallyAmout.replaceAll(",", "");
        if ("".equals(finallyAmout)) {
            return;
        }
        Double finallyDouble = Double.valueOf(finallyAmout);
        if (finallyDouble < withdrawMinNum || finallyDouble > withdrawMaxNum) {
            ToastUtils.showShort("单笔最低可提" + NumberFormaterUtils.formaterD2S(withdrawMinNum) + "元," + "，最大值为" + NumberFormaterUtils.formaterD2S(withdrawMaxNum) + "元。");
            return;
        }
        withDrawsPresenter.getWithDrawsFee(finallyAmout,this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.get().unregister(this);
        withDrawsPresenter.onDestory();
    }


    @Override
    public void onFeeResult(WithDrawsFeeBean withDrawsFeeBean) {
        if (withDrawsFeeBean.isMoneyTooSmall()) {
            ToastUtils.showShort("可取款小于最低取款金额");
            return;
        }
        goNextCanClick();
        tvFeeCostDetail.setText(NumberFormaterUtils.formaterS2S(withDrawsFeeBean.getFee()));
        tvAdmiCostDetail.setText(NumberFormaterUtils.formaterS2S(withDrawsFeeBean.getAdminCost()));
        tvOffCostDetail.setText(NumberFormaterUtils.formaterS2S(withDrawsFeeBean.getDeductFavorable()));
        tvFinallyMoneyDetail.setText(NumberFormaterUtils.formaterS2S(withDrawsFeeBean.getActualWithdraw()));

    }

    private void goNextUnableClick() {
        tvGoNext.setClickable(false);
        tvGoNext.setBackground(getResources().getDrawable(R.drawable.un_accent_btn_background));
    }

    private void goNextCanClick() {
        tvGoNext.setClickable(true);
        tvGoNext.setBackground(getResources().getDrawable(R.drawable.accent_btn_background));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onInitResult(WithDrawBean data) {
        svBody.setVisibility(View.VISIBLE);
        llNothingRoot.setVisibility(View.GONE);
        tvAccountName.setText(data.getUsername());
        balance = data.getBalance();
        tvYueName.setText("¥" + NumberFormaterUtils.formaterS2S(balance));
        reminder = data.getReminder();
        tvLestWithdrawsTimeDetail.setText(reminder + "次");
        tvBankCardName.setText(data.getBankShortName());
        tvBankCardNumber.setText(data.getCardNumber());
        withdrawMaxNum = data.getWithdrawMaxNum();
        withdrawMinNum = data.getWithdrawMinNum();
        etMoneyInput.setHint("可取款范围(" + NumberFormaterUtils.formaterD2S(withdrawMinNum) + "～" + NumberFormaterUtils.formaterD2S(withdrawMaxNum)+")");
    }

    @Override
    public void onApplyResult(Object o) {
        tvGoNext.setClickable(true);
        SingleToast.showMsg("提现申请已提交");
        svBody.setVisibility(View.GONE);
        llNothingRoot.setVisibility(View.VISIBLE);
        tvShowMessage.setText("已存在未审核取款订单");
        btnGo.setText("查看取款记录");
    }

    @Subscribe(tags = @Tag(ConstantValue.EVENT_TYPE_NETWORK_RETURN))
    public void onReturnError(String message) {
        processdError(message);

    }

    private void processdError(String message) {
        if ("已存在未审核取款订单".equals(message)) {
            tvShowMessage.setText(message);
            btnGo.setText("查看取款记录");
            svBody.setVisibility(View.GONE);
            llNothingRoot.setVisibility(View.VISIBLE);
        } else if ("今日取款已达上限".equals(message)) {
            tvShowMessage.setText(message);
            btnGo.setText("联系工作人员");
            svBody.setVisibility(View.GONE);
            llNothingRoot.setVisibility(View.VISIBLE);
        }
    }

    @Subscribe(tags = {@Tag(ConstantValue.EVENT_TYPE_NETWORK_EXCEPTION)})
    public void shrinkRefreshView(String s) {
      goNextUnableClick();
      processdError(s);
    }

    @Override
    public void start() {

    }

    @Override
    public void complete() {

    }
}
