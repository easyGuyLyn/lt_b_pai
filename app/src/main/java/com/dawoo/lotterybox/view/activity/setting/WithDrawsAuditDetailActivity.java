package com.dawoo.lotterybox.view.activity.setting;

import android.annotation.SuppressLint;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.dawoo.coretool.util.date.DateTool;
import com.dawoo.lotterybox.ConstantValue;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.view.activity.team.base.BindLayout;
import com.dawoo.lotterybox.view.activity.team.base.SuperBaseActivity;
import com.dawoo.lotterybox.bean.WithDrawsDetailBean;
import com.dawoo.lotterybox.mvp.presenter.WithDrawsPresenter;
import com.dawoo.lotterybox.mvp.view.IWithDrawsDetailView;
import com.dawoo.lotterybox.util.BalanceUtils;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;

import java.util.Date;

import butterknife.BindView;

@BindLayout(R.layout.activity_with_draws_audit_detail)
public class WithDrawsAuditDetailActivity extends SuperBaseActivity implements IWithDrawsDetailView {


    @BindView(R.id.left_btn)
    FrameLayout leftBtn;
    @BindView(R.id.title_name)
    TextView titleName;
    @BindView(R.id.fl_right_click)
    FrameLayout flRightClick;
    @BindView(R.id.tv_withdraws_time)
    TextView tvWithdrawsTime;
    @BindView(R.id.tv_withDraws_balance)
    TextView tvWithDrawsBalance;
    @BindView(R.id.tv_withdraws_audit_point)
    TextView tvWithdrawsAuditPoint;
    @BindView(R.id.tv_aduilt_cost)
    TextView tvAduiltCost;
    @BindView(R.id.tv_fee_amount)
    TextView tvFeeAmount;
    @BindView(R.id.tv_fee_audit_point)
    TextView tvFeeAuditPoint;
    @BindView(R.id.tv_fee_cost)
    TextView tvFeeCost;
    private WithDrawsPresenter<WithDrawsAuditDetailActivity> withDrawsPresenter;


    @Override
    protected void initViews() {
        titleName.setText("稽核详情");
        leftBtn.setOnClickListener(v -> finish());
        RxBus.get().register(this);
    }

    @Override
    protected void initData() {
        withDrawsPresenter = new WithDrawsPresenter<>(this, this);
        String id = getIntent().getStringExtra("id");
        withDrawsPresenter.withDrawsAuidDeatail(id);

    }


    @SuppressLint("SetTextI18n")
    @Override
    public void resultDetail(WithDrawsDetailBean drawsDetailBean) {
        tvWithdrawsTime.setText(DateTool.convert2String(new Date(drawsDetailBean.getOrderTime()),DateTool.FMT_DATE_TIME));
        tvWithDrawsBalance.setText(BalanceUtils.getScalsBalance(drawsDetailBean.getDepositAmount()));
        tvWithdrawsAuditPoint.setText(BalanceUtils.getScalsBalance(drawsDetailBean.getDepositCode())+"/"+BalanceUtils.getScalsBalance((drawsDetailBean.getTotalCode()+drawsDetailBean.getAvailableDepositCode())));
        tvAduiltCost.setText(BalanceUtils.getScalsBalance(drawsDetailBean.getAdminCost()));
        tvFeeAmount.setText(BalanceUtils.getScalsBalance(drawsDetailBean.getFavorableAmount()));
        tvFeeAuditPoint.setText(BalanceUtils.getScalsBalance(drawsDetailBean.getFavorableCode())+"/"+BalanceUtils.getScalsBalance((drawsDetailBean.getTotalCode()+drawsDetailBean.getAvailableFavorableCode())));
        tvFeeCost.setText(drawsDetailBean.getDeductFavorable()==0?"通过":BalanceUtils.getScalsBalance(drawsDetailBean.getDeductFavorable()));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.get().unregister(this);
        withDrawsPresenter.onDestory();
    }

    String msg = "";

    @Subscribe(tags = @Tag(ConstantValue.EVENT_TYPE_NETWORK_EXCEPTION))
    public void onReturnError(String message) {
        if (msg.equals(message)) {
        } else {
            ToastUtils.showShort(message);
            msg = message;
        }
    }
}
