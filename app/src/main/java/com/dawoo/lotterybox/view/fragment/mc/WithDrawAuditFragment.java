package com.dawoo.lotterybox.view.fragment.mc;

import android.annotation.SuppressLint;

import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.view.activity.team.base.BindLayout;
import com.dawoo.lotterybox.view.activity.team.base.SuperBaseFragment;

/**
 * Created by alex on 18-4-27.
 * @author alex
 */

@SuppressLint("ValidFragment")
@BindLayout(R.layout.fragment_withdraw_aduit)
public class WithDrawAuditFragment extends SuperBaseFragment {
    public static final int ALL=0;
    public static final int PSAA=1;
    public static final int UNPASS=2;
    private int type;

    public WithDrawAuditFragment(int type) {
        this.type = type;
    }



    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void superLoadData() {

    }
}
