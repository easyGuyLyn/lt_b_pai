package com.dawoo.lotterybox.view.activity.account.bank;

import android.content.Context;
import android.content.Intent;
import android.widget.Button;

import com.blankj.utilcode.util.ToastUtils;
import com.dawoo.lotterybox.ConstantValue;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.bean.DataCenter;
import com.dawoo.lotterybox.view.activity.BaseActivity;
import com.dawoo.lotterybox.view.view.HeaderView;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author jack
 * @date 18-2-19
 * 提现界面
 */

public class BindCardActivity extends BaseActivity {
    @BindView(R.id.band_card)
    Button bandCard;
    @BindView(R.id.head_view)
    HeaderView headView;
    private Context context;

    @Override
    protected void createLayoutView() {
        setContentView(R.layout.activity_depoist);
        context = this;
        RxBus.get().register(this);
    }

    @Override
    protected void initViews() {
        headView.setHeader(getResources().getString(R.string.band_card_prompt), true);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onDestroy() {
        RxBus.get().unregister(this);
        super.onDestroy();
    }



    @OnClick(R.id.band_card)
    public void onViewClicked() {
        startActivity(new Intent(context, BandCardActivity.class));
    }



    /**
     * 用户绑卡成功
     */
    @Subscribe(tags = {@Tag(ConstantValue.EVENT_BIND_CARD_SUCCEED)})
    public void bindCardSuccess(String s) {
      finish();
    }

}
