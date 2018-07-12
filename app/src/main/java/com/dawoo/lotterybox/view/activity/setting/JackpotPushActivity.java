package com.dawoo.lotterybox.view.activity.setting;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.view.activity.BaseActivity;
import com.dawoo.lotterybox.view.view.HeaderView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jack
 * 中奖推送
 */

public class JackpotPushActivity extends BaseActivity {
    @BindView(R.id.head_view)
    HeaderView headView;
    @BindView(R.id.jackpot_push)
    TextView jackpotPush;
    @BindView(R.id.all_Species)
    TextView allSpecies;
    @BindView(R.id.high_Species)
    TextView highSpecies;

    @Override
    protected void createLayoutView() {
        setContentView(R.layout.activity_jackpot_push);
    }

    @Override
    protected void initViews() {
        headView.setHeader(getResources().getString(R.string.Jackpot_push), true);
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

    @OnClick({R.id.jackpot_push, R.id.all_Species, R.id.high_Species})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.jackpot_push:

                break;
            case R.id.all_Species:

                break;
            case R.id.high_Species:

                break;
        }
    }
}
