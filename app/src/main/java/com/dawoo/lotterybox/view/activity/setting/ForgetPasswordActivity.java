package com.dawoo.lotterybox.view.activity.setting;

import android.os.Bundle;
import android.widget.RelativeLayout;

import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.view.activity.BaseActivity;
import com.dawoo.lotterybox.view.view.HeaderView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alex on 18-4-6.
 * @author alex
 */

public class ForgetPasswordActivity extends BaseActivity {
    @BindView(R.id.head_view)
    HeaderView headView;
    @BindView(R.id.rl_phone_find)
    RelativeLayout rlPhoneFind;
    @BindView(R.id.rl_email_find)
    RelativeLayout rlEmailFind;
    @BindView(R.id.rl_kefu_find)
    RelativeLayout rlKefuFind;

    @Override
    protected void createLayoutView() {
        setContentView(R.layout.activity_forgetpassword);
    }

    @Override
    protected void initViews() {
        headView.setHeader("忘记密码", true);

    }

    @Override
    protected void initData() {

    }


}
