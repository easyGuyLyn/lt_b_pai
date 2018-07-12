package com.dawoo.lotterybox.view.activity.account.bank;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.dawoo.lotterybox.ConstantValue;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.mvp.presenter.BankCardPreserter;
import com.dawoo.lotterybox.mvp.view.IBindBankCardView;
import com.dawoo.lotterybox.util.SingleToast;
import com.dawoo.lotterybox.view.activity.BaseActivity;
import com.dawoo.lotterybox.view.activity.account.bank.BandCardActivity;
import com.dawoo.lotterybox.view.activity.account.bank.BandCardScuessActviity;
import com.dawoo.lotterybox.view.activity.account.bank.BindCardActivity;
import com.dawoo.lotterybox.view.view.HeaderView;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author jack
 * @date 18-2-20
 * 验证银行卡
 */

public class CertifiedBankCardActivity extends BaseActivity implements IBindBankCardView {
    @BindView(R.id.head_view)
    HeaderView headView;
    @BindView(R.id.btn_confirm)
    Button btnConfirm;
    @BindView(R.id.btn_modify_information)
    Button btnModifyInformation;
    @BindView(R.id.imageView4)
    ImageView imageView4;
    @BindView(R.id.bankName)
    TextView bank_Name;
    @BindView(R.id.cardNumber)
    TextView band_Code;
    @BindView(R.id.user_name)
    TextView userName;
    @BindView(R.id.textView15)
    TextView textView15;
    private Context context;
    private BankCardPreserter bankCardPreserter;
    private String bankCode;
    private String bankName;
    private String cardNumber;
    private String masterName;

    @Override
    protected void createLayoutView() {
        setContentView(R.layout.activity_certifiedbankcard);
        context = this;
        RxBus.get().register(this);


    }

    @Override
    protected void initViews() {
        headView.setHeader(getResources().getString(R.string.certified_bank_card), true);
        bankCardPreserter = new BankCardPreserter<IBindBankCardView>(this, this);
    }

    @Override
    protected void onDestroy() {
        RxBus.get().unregister(this);
        bankCardPreserter.onDestory();
        super.onDestroy();
    }

    @Override
    protected void initData() {
        bankCode = getIntent().getStringExtra("bankCode");
        bankName = getIntent().getStringExtra("bankName");
        cardNumber = getIntent().getStringExtra("cardNumber");
        masterName = getIntent().getStringExtra("masterName");
        bank_Name.setText(bankName);
        band_Code.setText(cardNumber);
        userName.setText(masterName);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_confirm, R.id.btn_modify_information})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_confirm:
                bankCardPreserter.bingBankCard(bankCode, bankName, cardNumber, masterName);
                break;
            case R.id.btn_modify_information:
                finish();
                break;
            default:
        }
    }

    @Override
    public void onResult(Object o) {
        RxBus.get().post(ConstantValue.EVENT_BIND_CARD_SUCCEED,"绑卡成功");
        startActivity(new Intent(context, BandCardScuessActviity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        ActivityUtils.finishActivity(BindCardActivity.class);
        ActivityUtils.finishActivity(BandCardActivity.class);
        ActivityUtils.finishActivity(BandCardScuessActviity.class);
        finish();

    }

    @Subscribe(tags = @Tag(ConstantValue.EVENT_TYPE_NETWORK_RETURN))
    public void onReturnError(String message) {
        SingleToast.showMsg(message);
    }


}
