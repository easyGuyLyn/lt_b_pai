package com.dawoo.lotterybox.view.activity.account.bank;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.dawoo.coretool.ToastUtil;
import com.dawoo.coretool.util.date.DateTool;
import com.dawoo.lotterybox.ConstantValue;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.bean.Bank;
import com.dawoo.lotterybox.bean.DataCenter;
import com.dawoo.lotterybox.mvp.presenter.BankCardPreserter;
import com.dawoo.lotterybox.mvp.presenter.UserPresenter;
import com.dawoo.lotterybox.mvp.view.IBankListView;
import com.dawoo.lotterybox.mvp.view.IBindBankCardView;
import com.dawoo.lotterybox.util.RexConstantValue;
import com.dawoo.lotterybox.util.RexUtils;
import com.dawoo.lotterybox.util.SingleToast;
import com.dawoo.lotterybox.util.ViewUtils;
import com.dawoo.lotterybox.view.activity.BaseActivity;
import com.dawoo.lotterybox.view.view.HeaderView;
import com.hwangjr.rxbus.RxBus;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author jack
 * @date 18-2-19
 * 绑定银行卡界面
 */

public class BandCardActivity extends BaseActivity implements IBankListView, IBindBankCardView {
    @BindView(R.id.head_view)
    HeaderView headView;
    @BindView(R.id.bank_img)
    ImageView bankimage;
    @BindView(R.id.bt_submit)
    Button btSubmit;
    @BindView(R.id.bank_ll)
    LinearLayout bankLl;
    @BindView(R.id.card_name)
    EditText cardName;
    @BindView(R.id.card_number)
    EditText cardNumber;
    private UserPresenter userPresenter;
    private Context context;
    @BindView(R.id.bank_name)
    TextView bank_name;
    private String bankCode;
    private BankCardPreserter<IBankListView> bankCardPreserter;
    private ArrayList<Bank> datas = new ArrayList<>();
    private String bankName;


    @Override
    protected void createLayoutView() {
        setContentView(R.layout.activity_bandcard);
        context = this;
    }

    @Override
    protected void initViews() {
        headView.setHeader(getResources().getString(R.string.band_card_binding_bank_card), true);
    }

    @Override
    protected void initData() {
        bankCardPreserter = new BankCardPreserter<>(this, this);
        bankCardPreserter.getBankList();
        if (DataCenter.getInstance().getUser().getRealname()!=null){
            cardName.setText(DataCenter.getInstance().getUser().getRealname());
            cardName.setFocusable(false);
            cardName.setClickable(false);
        }

    }

    @OnClick({R.id.bank_ll, R.id.bt_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bank_ll:
                startActivityForResult(new Intent(BandCardActivity.this, BankCardList.class).putParcelableArrayListExtra("datas", datas), 1);
                break;
            case R.id.bt_submit:

                if (TextUtils.isEmpty(cardName.getText().toString())) {
                    ToastUtil.showResLong(context, "用戶名为空,请重新输入");
                    return;
                }

                if (TextUtils.isEmpty(cardNumber.getText().toString())) {
                    ToastUtil.showResLong(context, "银行卡号为空,请重新输入");
                    return;
                }
                if (!RexUtils.isRealName(cardName.getText().toString())) {
                    ToastUtil.showResLong(context, "姓名错误");
                    return;
                }
                if (!isBank(cardNumber.getText().toString())) {
                    ToastUtil.showResLong(context, "卡号不正确");
                    return;
                }

                //绑定银行卡
                bankCardPreserter.bingBankCard(bankCode, bankName, cardNumber.getText().toString(), cardName.getText().toString());

                break;
            default:
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && data != null) {
            int position = data.getIntExtra("position", 0);
            bankName = datas.get(position).getBankShortName();
            bankCode = datas.get(position).getBankCode();
            bank_name.setText(bankName);
            ViewUtils.showBankImage(this, bankimage, bankName);
        }
    }


    @Override
    public void onResultDate(List<Bank> banks) {
        datas.clear();
        datas.addAll(banks);

        if (datas.size() > 0) {
            bank_name.setText(datas.get(0).getBankShortName());
            bankCode = datas.get(0).getBankCode();
            bankName = datas.get(0).getBankShortName();
            ViewUtils.showBankImage(this,bankimage,bankName);
        }

    }

    @Override
    public void onResult(Object o) {
        RxBus.get().post(ConstantValue.EVENT_BIND_CARD_SUCCEED, cardName.getText().toString().trim());
        SingleToast.showMsg("银行卡绑定成功");
        finish();
    }

    public static boolean isBank(final String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile(RexConstantValue.BANK); // 验证手机号
        m = p.matcher(str);
        b = m.matches();
        return b;
    }
}
