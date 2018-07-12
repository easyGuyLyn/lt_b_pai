package com.dawoo.lotterybox.view.activity.account;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.bumptech.glide.Glide;
import com.dawoo.coretool.ToastUtil;
import com.dawoo.coretool.util.packageref.PackageInfoUtil;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.bean.DataCenter;
import com.dawoo.lotterybox.view.activity.BaseActivity;
import com.dawoo.lotterybox.view.activity.account.bank.BandCardScuessActviity;
import com.dawoo.lotterybox.view.activity.account.bank.BindCardActivity;
import com.dawoo.lotterybox.view.activity.account.withdraw.WithdrawalsActivity;
import com.dawoo.lotterybox.view.activity.account.deposit.PayParentActivity;
import com.dawoo.lotterybox.view.fragment.bill_details.BillingDetailsActivity;
import com.dawoo.lotterybox.view.view.HeaderView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author jack
 * @date 18-2-21
 * 钱包管理
 */

public class WalletManagement extends BaseActivity {
    @BindView(R.id.head_view)
    HeaderView headView;
    @BindView(R.id.imageView6)
    ImageView imageView6;
    @BindView(R.id.tv_account)
    TextView account;
    @BindView(R.id.tv_nick_name)
    TextView name;
    @BindView(R.id.tv_id)
    TextView accountID;
    @BindView(R.id.imageView7)
    ImageView imageView7;
    @BindView(R.id.textView16)
    TextView textView16;
    @BindView(R.id.money)
    TextView money;
    @BindView(R.id.mine_recharge)
    RelativeLayout mineRecharge;
    @BindView(R.id.mine_withdraw)
    RelativeLayout mineWithdraw;
    @BindView(R.id.bank_card_managerment)
    RelativeLayout bankCardManagerment;
    @BindView(R.id.bill_details)
    RelativeLayout billDetails;
    private Context context;
    private Handler handler = new Handler();

    @Override
    protected void createLayoutView() {
        setContentView(R.layout.activity_wellet_mangerment);
    }

    @Override
    protected void initViews() {
        headView.setHeader(getResources().getString(R.string.money_managerment), true);
        context = this;
    }

    @Override
    protected void initData() {
        account.setText(DataCenter.getInstance().getUser().getUsername());
        accountID.setText(DataCenter.getInstance().getUser().getUserId() + "");
        name.setText(DataCenter.getInstance().getUser().getNickname());
        money.setText(DataCenter.getInstance().getUser().getBalance());
        Glide.with(this).load(PackageInfoUtil.getResource(this, DataCenter.getInstance().getUser().getAvatarUrl())).into(imageView6);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.mine_recharge, R.id.mine_withdraw, R.id.bank_card_managerment, R.id.bill_details})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //充值
            case R.id.mine_recharge:
                startActivity(new Intent(context, PayParentActivity.class));
                break;
            //体现
            case R.id.mine_withdraw:
                if (DataCenter.getInstance().getUser().isBindBankCard()) {
                    ActivityUtils.startActivity(WithdrawalsActivity.class);
                } else {
                    ActivityUtils.startActivity(BindCardActivity.class);
                }
                break;
            //银行卡管理
            case R.id.bank_card_managerment:
                if (DataCenter.getInstance().getUser().isBindBankCard()) {
                    ActivityUtils.startActivity(BandCardScuessActviity.class);
                } else {
                    ActivityUtils.startActivity(BindCardActivity.class);
                }
                break;
            //账单明细
            case R.id.bill_details:
                startActivity(new Intent(context, BillingDetailsActivity.class));
                break;
            default:
        }
    }

    private void ShowMsg(String msg) {
        ToastUtil.showToastShort(context, msg);
    }

}
