package com.dawoo.lotterybox.view.activity.setting;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.util.MSPropties;
import com.dawoo.lotterybox.view.activity.BaseActivity;
import com.dawoo.lotterybox.view.view.HeaderView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jack
 * 推送和提醒
 */

public class PushAndremindActvity extends BaseActivity {
    @BindView(R.id.head_view)
    HeaderView headView;
    @BindView(R.id.Jackpot_push)
    TextView JackpotPush;
    @BindView(R.id.Lottery_push)
    TextView LotteryPush;

    @Override
    protected void createLayoutView() {
        setContentView(R.layout.activity_push_and_remind);
    }

    @Override
    protected void initViews() {
        headView.setHeader(getResources().getString(R.string.push), true);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.Jackpot_push, R.id.Lottery_push})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Jackpot_push:
                MSPropties.startActivity(JackpotPushActivity.class);
                break;
            case R.id.Lottery_push:
                MSPropties.startActivity(LotteryPushActivity.class);
                break;
            default:
        }
    }
}
