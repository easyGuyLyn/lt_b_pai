package com.dawoo.lotterybox.view.activity.account;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.view.activity.BaseActivity;
import com.dawoo.lotterybox.view.view.HeaderView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author alex
 *         稽核详情
 */
public class AuditActivity extends BaseActivity {


    @BindView(R.id.hv_top)
    HeaderView hvTop;
    @BindView(R.id.tl_adult_head)
    TabLayout tlAdultHead;
    @BindView(R.id.vp_adult_body)
    ViewPager vpAdultBody;

    @Override
    protected void createLayoutView() {
        setContentView(R.layout.activity_audit);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initData() {

    }


}
