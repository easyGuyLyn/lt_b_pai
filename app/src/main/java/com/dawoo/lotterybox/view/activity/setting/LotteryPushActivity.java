package com.dawoo.lotterybox.view.activity.setting;

import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.view.activity.BaseActivity;
import com.dawoo.lotterybox.view.view.HeaderView;

/**
 * Created by jack on 18-3-1.
 */

public class LotteryPushActivity extends BaseActivity {

    private HeaderView head_view;

    @Override
    protected void createLayoutView() {
        setContentView(R.layout.activity_lottery_push);
    }

    @Override
    protected void initViews() {
        head_view = findViewById(R.id.head_view);
        head_view.setHeader(getResources().getString(R.string.Lottery_push), true);
    }

    @Override
    protected void initData() {

    }
}
