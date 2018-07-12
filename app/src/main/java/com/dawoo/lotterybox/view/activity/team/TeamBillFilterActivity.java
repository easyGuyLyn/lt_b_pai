package com.dawoo.lotterybox.view.activity.team;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.view.activity.team.base.OnMultiClickListener;
import com.dawoo.lotterybox.view.activity.BaseActivity;

import butterknife.BindView;

/**
 * @author alex
 */
public class TeamBillFilterActivity extends BaseActivity {

    @BindView(R.id.left_btn)
    FrameLayout leftBtn;
    @BindView(R.id.title_name)
    TextView titleName;
    @BindView(R.id.fl_right_click)
    FrameLayout flRightClick;

    @Override
    protected void createLayoutView() {
        setContentView(R.layout.activity_team_bill_filter);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initData() {
        leftBtn.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                finish();
            }
        });


    }


}
