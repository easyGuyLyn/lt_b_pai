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
public class TeamReportFromDetailActivity extends BaseActivity {


    @BindView(R.id.left_btn)
    FrameLayout leftBtn;
    @BindView(R.id.title_name)
    TextView titleName;
    @BindView(R.id.fl_right_click)
    FrameLayout flRightClick;
    @BindView(R.id.tv_desp)
    TextView tvDesp;
    @BindView(R.id.tv_aviable)
    TextView tvAviable;
    @BindView(R.id.tv_zhongjiang)
    TextView tvZhongjiang;
    @BindView(R.id.tv_withdraws)
    TextView tvWithdraws;
    @BindView(R.id.tv_fenghong)
    TextView tvFenghong;
    @BindView(R.id.tv_yingkui)
    TextView tvYingkui;
    @BindView(R.id.tv_username)
    TextView tvUserName;
    @Override
    protected void createLayoutView() {
        setContentView(R.layout.item_team_report);
    }

    @Override
    protected void initViews() {
        titleName.setText("报表详情");
        leftBtn.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                finish();
            }
        });
        String playerName = getIntent().getStringExtra("playerName");
        String deposittotal = getIntent().getStringExtra("deposittotal");
        String withdrawtotal = getIntent().getStringExtra("withdrawtotal");
        String effectivevolume = getIntent().getStringExtra("effectivevolume");
        String favorable = getIntent().getStringExtra("favorable");
        String payout = getIntent().getStringExtra("payout");
        String win = getIntent().getStringExtra("win");
        tvUserName.setText(playerName);
        tvAviable.setText(effectivevolume);
        tvDesp.setText(deposittotal);
        tvFenghong.setText(favorable);
        tvWithdraws.setText(withdrawtotal);
        tvYingkui.setText(win);
        tvZhongjiang.setText(payout);

    }

    @Override
    protected void initData() {

    }



}
