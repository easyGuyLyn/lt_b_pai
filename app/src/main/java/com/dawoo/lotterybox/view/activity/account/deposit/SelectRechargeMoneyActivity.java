package com.dawoo.lotterybox.view.activity.account.deposit;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.andrognito.patternlockview.utils.RandomUtils;
import com.dawoo.lotterybox.ConstantValue;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.adapter.SaleAdapter;
import com.dawoo.lotterybox.adapter.SelectPayWayAdapter;
import com.dawoo.lotterybox.adapter.SelectRechangeMoneyAdapter;
import com.dawoo.lotterybox.bean.DataCenter;
import com.dawoo.lotterybox.bean.DateResources;
import com.dawoo.lotterybox.bean.Deposit.CallBackUrlBean;
import com.dawoo.lotterybox.bean.Deposit.OnlineResulltBean;
import com.dawoo.lotterybox.bean.Deposit.ParentDepositBean;
import com.dawoo.lotterybox.bean.Deposit.PayDetailBean;
import com.dawoo.lotterybox.bean.Deposit.SaleBean;
import com.dawoo.lotterybox.mvp.presenter.DepositPresenter;
import com.dawoo.lotterybox.mvp.view.IDepositView;
import com.dawoo.lotterybox.net.BaseHttpResult;
import com.dawoo.lotterybox.util.ActivityUtil;
import com.dawoo.lotterybox.util.DepositUtil;
import com.dawoo.lotterybox.util.MSPropties;
import com.dawoo.lotterybox.util.RxUtils;
import com.dawoo.lotterybox.util.SingleToast;
import com.dawoo.lotterybox.view.activity.BaseActivity;
import com.dawoo.lotterybox.view.view.MeasureAllGridView;
import com.dawoo.lotterybox.view.view.NoMoveRecycleView;
import com.dawoo.lotterybox.view.view.dialog.ShowFeeDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jack
 * 选择金额
 */

public class SelectRechargeMoneyActivity extends BaseActivity implements IDepositView {
    final String COMPANY_PAY="company_income";
    @BindView(R.id.rechange_money)
    EditText rechangeMoney;
    @BindView(R.id.random_balance)
    TextView randomTv;
    @BindView(R.id.tv_balance)
    TextView balanceTV;
    @BindView(R.id.rechage_money_gridview)
    MeasureAllGridView rechageMoneyGridview;
    @BindView(R.id.way_gridView)
    MeasureAllGridView wayGridView;

    @BindView(R.id.sale_card)
    CardView saleCard;
    @BindView(R.id.sales_rv)
    NoMoveRecycleView saleView;
    @BindView(R.id.sale_tv)
    TextView saleTV;
    @BindView(R.id.note_tv)
    TextView noteTv;

    SelectPayWayAdapter mAdapter;
    private ParentDepositBean bean;
    private PopupWindow balancePop;
    TextView getBalanceTV;
    private List<PayDetailBean> mDatas = new ArrayList<>();
    private DepositPresenter mPresenter;
    private ShowFeeDialog mFeeDialog;
    protected double inputAmount = 0.00;
    private SaleAdapter mSaleAdapter;
    private Handler mHandle = new Handler();

    @Override
    protected void createLayoutView() {
        setContentView(R.layout.activity_select_money_recharge);
    }

    @Override
    protected void initViews() {
        rechageMoneyGridview.setAdapter(new SelectRechangeMoneyAdapter(this, DateResources.moneyType));
        rechageMoneyGridview.setOnItemClickListener((parent, view, position, id) -> {
            rechangeMoney.setText(MSPropties.getDoubleNumber(DateResources.moneyType[position]) + "0");
            rechangeMoney.setSelection(rechangeMoney.getText().length());

        });
        mAdapter = new SelectPayWayAdapter(mDatas);
        wayGridView.setAdapter(mAdapter);
        wayGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mAdapter.getSelectIndex() != position) {
                    mAdapter.setSelectIndex(position);
                    updateUI();
                }
            }
        });
        mSaleAdapter = new SaleAdapter();
        mSaleAdapter.setOnItemClickLInstener(new SaleAdapter.OnItemClickLinstener() {
            @Override
            public void onItemClick(int position) {
                mSaleAdapter.setSelectedIndex(position);
                if (rechangeMoney.getText().toString().trim().isEmpty()) {
                    return;
                }
                if (mSaleAdapter.getSaleId() == 0) {
                    saleTV.setText("不参与优惠");
                    return;
                }
                initInputMoney();
                PayDetailBean select = mDatas.get(mAdapter.getSelectIndex());
                if (inputAmount >= select.getSingleDepositMin() && inputAmount <= select.getSingleDepositMax()) {
                    mPresenter.getSaleMoney(inputAmount, mSaleAdapter.getSaleId());
                } else {
                    SingleToast.showMsg("请输入正确的存款金额");
                }
            }
        });
        saleView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        saleView.setAdapter(mSaleAdapter);

        rechangeMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (rechangeMoney.getText().toString().isEmpty()) {
                    saleTV.setText("优惠金额：0.0");
                    return;
                }

                if (bean.getType().equalsIgnoreCase(COMPANY_PAY)) {
                    if (delayRun != null || mSaleAdapter.getSaleId() == 0) {
                        //每次editText有变化的时候，则移除上次发出的延迟线程
                        mHandle.removeCallbacks(delayRun);
                    }
                    if (mSaleAdapter.getSaleId() == 0) {
                        saleTV.setText("不参与优惠");
                        return;
                    }
                    //延迟800ms，如果不再输入字符，则执行该线程的run方法
                    mHandle.postDelayed(delayRun, 800);
                }
            }
        });
    }

    private void processInput() {
        initInputMoney();
        if (inputAmount == 0) {
            return;
        }
        PayDetailBean select = mDatas.get(mAdapter.getSelectIndex());
        if (inputAmount >= select.getSingleDepositMin() && inputAmount <= select.getSingleDepositMax()) {
            mPresenter.getSaleMoney(inputAmount, mSaleAdapter.getSaleId());
        } else {
            SingleToast.showMsg("请输入正确的存款金额");
        }
    }

    @Override
    protected void initData() {
        bean = (ParentDepositBean) getIntent().getSerializableExtra("item");
        if (bean == null) {
            finish();
            return;
        }

        mPresenter = new DepositPresenter(this, this);
        mPresenter.getPayDetail(bean.getType(),bean.getItem());
        if (bean.getType().equalsIgnoreCase(COMPANY_PAY)) {
            mPresenter.getSales(bean.getItem());
            saleCard.setVisibility(View.VISIBLE);
        }

    }

    void updateUI() {

        PayDetailBean bean0 = mDatas.get(mAdapter.getSelectIndex());
        rechangeMoney.setText("");
        rechangeMoney.setHint("最小金额：" + bean0.getSingleDepositMin() + "~~最大金额：" + bean0.getSingleDepositMax());
        if (bean0.isRandomAmount()) {
            int randoms = RandomUtils.randInt(99);
            if (randoms == 0) {
                randoms = 52;
            }
            randomTv.setText("." + randoms);
            randomTv.setVisibility(View.VISIBLE);
        }

        String notice = DepositUtil.getNoteByCode(bean.getItem(),bean0.isRandomAmount(),bean0.getType(),true);
        SpannableStringBuilder noticeBuffer = new SpannableStringBuilder("温馨提示：\n"+notice.replaceAll("<br>", "\n"));
        SpannableString colorSpan = new SpannableString("点击联系在线客服");
        colorSpan.setSpan(new ClickableSpan() {
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(getResources().getColor(R.color.colorPrimary));
                ds.setUnderlineText(false);
                ds.clearShadowLayer();
            }

            @Override
            public void onClick(View widget) {
                ActivityUtil.gotoCustomerService();
            }
        }, 0, colorSpan.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        noticeBuffer.append(colorSpan);
        noteTv.setText(noticeBuffer);
        noteTv.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.back, R.id.submit, R.id.tv_balance})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.submit:
                initInputMoney();
                if (inputAmount == 0.00) {
                    return;
                }
                PayDetailBean select = mDatas.get(mAdapter.getSelectIndex());
                if (inputAmount >= select.getSingleDepositMin() && inputAmount <= select.getSingleDepositMax()) {
                    if (bean.getType().equalsIgnoreCase(COMPANY_PAY)) {
                        ActivityUtil.startWithdrawActivity(SelectRechargeMoneyActivity.this,mDatas.get(mAdapter.getSelectIndex()), inputAmount, mSaleAdapter.getSaleId());
                    } else {
                        mPresenter.getFee(select.getId(), inputAmount);
                    }
                } else {
                    SingleToast.showMsg("请输入最大最小范围内正确的存款金额");
                }

                break;

            case R.id.tv_balance:
                if (balancePop == null) {
                    initBalancePop();
                }
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 0.7f;
                getWindow().setAttributes(lp);
                getBalanceTV.setText(DataCenter.getInstance().getUser().getBalance() + "");
                balancePop.showAsDropDown(view, 0, 20);
                break;
            default:
        }
    }

    void initInputMoney() {
        inputAmount = 0.00;
        if (rechangeMoney.getText().toString().trim().isEmpty()) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    SingleToast.showMsg("请输入存款金额");
                }
            });
            return;
        }
        if (mDatas == null || mDatas.isEmpty()) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    SingleToast.showMsg("未获取到支付渠道，请退出当前页面后重试");
                }
            });
            return;
        }
        inputAmount = Double.parseDouble(rechangeMoney.getText().toString().trim());
        if (randomTv.getVisibility() == View.VISIBLE) {
            String randomPoint = "0" + randomTv.getText().toString();
            inputAmount += Double.parseDouble(randomPoint);
        }
    }

    void initBalancePop() {
        balancePop = new PopupWindow(getLayoutInflater().inflate(R.layout.pop_balance_layout, null));
        balancePop.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        balancePop.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        balancePop.setFocusable(true);
        balancePop.setTouchable(true);
        balancePop.setOutsideTouchable(true);
        balancePop.setBackgroundDrawable(new BitmapDrawable());
        getBalanceTV = balancePop.getContentView().findViewById(R.id.tv_balance);
        balancePop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
    }

    private Runnable delayRun = new Runnable() {

        @Override
        public void run() {
            //在这里调用服务器的接口，获取数据

            RxUtils.doOnIOThread(() -> processInput(), 0);
        }
    };

    @Override
    public void getDepositWay(List<ParentDepositBean> beans) {

    }

    @Override
    public void getBankDepositDetail(List<PayDetailBean> beans) {
        if (!beans.isEmpty()) {
            mDatas.clear();
            mDatas.addAll(beans);
            mAdapter.notifyDataSetChanged();
            updateUI();
        }
    }

    @Override
    public void getFee(Object o) {
        if (o == null) {
            return;
        }
        double feeAmount;
        try {
            feeAmount = Double.parseDouble(o.toString());
        } catch (Exception e) {
            feeAmount = 0.00;
        }
        initInputMoney();
        if (mFeeDialog == null) {
            mFeeDialog = new ShowFeeDialog(SelectRechargeMoneyActivity.this, R.style.CustomDialogStyle);
            mFeeDialog.setSureClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPresenter.postOnline(mDatas.get(mAdapter.getSelectIndex()).getId(), inputAmount);
                }
            });
        }
        mFeeDialog.setText(inputAmount, feeAmount);
        mFeeDialog.show();


    }

    @Override
    public void postResult(OnlineResulltBean bean) {
        if (bean == null) {
            return;
        }
        if (bean.isState()) {
            String billNO = bean.getBillNo();
            mPresenter.callBackOrder(billNO);
        }
    }

    @Override
    public void callBackOrder(CallBackUrlBean backUrlBean) {
        mFeeDialog.dismiss();
        if (backUrlBean != null && !backUrlBean.getPayUrl().isEmpty()) {
            ActivityUtil.startWebView(backUrlBean.getPayUrl(), ConstantValue.WEBVIEW_TYPE_PAY);
        }
        SingleToast.showMsg("存款信息提交成功");
    }

    @Override
    public void getSales(List<SaleBean> saleBeans) {
        mSaleAdapter.setmDatas(saleBeans);
    }

    @Override
    public void getSaleMoney(Object o) {
        Map<String, String> sales = (Map<String, String>) o;
        String favorableMoney = sales.get("favorableMoney");
        if (favorableMoney != null) {
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder("优惠金额：");
            SpannableString m = new SpannableString(favorableMoney);
            m.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_open_lottery_ball)), 0, m.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            spannableStringBuilder.append(m);
            saleTV.setText(spannableStringBuilder);
        }
    }

    @Override
    public void postCompany(BaseHttpResult baseHttpResult) {
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==ConstantValue.REQUEST_DEPOSIT&&resultCode==ConstantValue.RESULE_SUCCESS){
            rechangeMoney.setText("");
            mPresenter.getSales(bean.getItem());
        }
    }
}
