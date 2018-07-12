package com.dawoo.lotterybox.view.activity;

import android.os.Bundle;

import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.view.view.HeaderView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jack
 * 订单详情
 * 没有数据　从这个页面的上上个页面开始获取数据的type
 */

public class OrderDetailsActivity extends BaseActivity {

    @BindView(R.id.head_view)
    HeaderView headView;

    @Override
    protected void createLayoutView() {
        setContentView(R.layout.activity_order_details);
    }

    @Override
    protected void initViews() {
        headView.setHeader(getResources().getString(R.string.bill_details_all), true);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
