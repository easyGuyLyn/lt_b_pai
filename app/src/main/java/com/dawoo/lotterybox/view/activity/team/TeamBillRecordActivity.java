package com.dawoo.lotterybox.view.activity.team;


import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.dawoo.lotterybox.R;

import com.dawoo.lotterybox.adapter.QuickFragmentAdapter;
import com.dawoo.lotterybox.view.activity.team.base.BindLayout;
import com.dawoo.lotterybox.view.activity.team.base.SuperBaseActivity;
import com.dawoo.lotterybox.util.ViewUtils;
import com.dawoo.lotterybox.view.fragment.BaseFragment;
import com.dawoo.lotterybox.view.fragment.mc.TeamBillRecordFragment;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * @author alex
 */
@BindLayout(R.layout.activity_team_bill_recored)
public class TeamBillRecordActivity extends SuperBaseActivity {


    @BindView(R.id.left_btn)
    FrameLayout leftBtn;
    @BindView(R.id.title_name)
    TextView titleName;
    @BindView(R.id.fl_right_click)
    FrameLayout flRightClick;
    @BindView(R.id.tb_bill)
    TabLayout tbBill;
    @BindView(R.id.vp_team_bill_content)
    ViewPager vpTeamBillContent;
    private List<BaseFragment> data;


    @Override
    protected void initViews() {
        titleName.setText("账变明细");
        leftBtn.setOnClickListener(v -> finish());
        flRightClick.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initData() {
        String startData = getIntent().getStringExtra("startData");
        String endData = getIntent().getStringExtra("endData");
        TeamBillRecordFragment billAllFragment = TeamBillRecordFragment.instance(TeamBillRecordFragment.ALL, startData, endData);
        TeamBillRecordFragment billDepositFragment = TeamBillRecordFragment.instance(TeamBillRecordFragment.DEPOSIT, startData, endData);
        TeamBillRecordFragment billWithDrawsFragment = TeamBillRecordFragment.instance(TeamBillRecordFragment.WITHDRAWS, startData, endData);
        TeamBillRecordFragment billGameFragment = TeamBillRecordFragment.instance(TeamBillRecordFragment.GAME, startData, endData);
        TeamBillRecordFragment billOfferFragment = TeamBillRecordFragment.instance(TeamBillRecordFragment.OFFER, startData, endData);
        data = new ArrayList<>();
        data.add(billAllFragment);
        data.add(billDepositFragment);
        data.add(billWithDrawsFragment);
        data.add(billGameFragment);
        data.add(billOfferFragment);
        QuickFragmentAdapter quickFragmentAdapter = new QuickFragmentAdapter(getSupportFragmentManager(), data, "全部", "存款", "取款", "游戏", "优惠");
        vpTeamBillContent.setAdapter(quickFragmentAdapter);
        tbBill.setupWithViewPager(vpTeamBillContent);
        ViewUtils.reflexTabLayout(tbBill);
        vpTeamBillContent.setOffscreenPageLimit(data.size());
        //  flRightClick.setOnClickListener(v -> ActivityUtils.startActivity(TeamBillFilterActivity.class));
        flRightClick.setVisibility(View.GONE);
        vpTeamBillContent.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                billAllFragment.turnOffAll();
                billDepositFragment.turnOffAll();
                billWithDrawsFragment.turnOffAll();
                billGameFragment.turnOffAll();
                billOfferFragment.turnOffAll();
            }

            @Override
            public void onPageSelected(int position) {
                billAllFragment.turnOffAll();
                billDepositFragment.turnOffAll();
                billWithDrawsFragment.turnOffAll();
                billGameFragment.turnOffAll();
                billOfferFragment.turnOffAll();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                billAllFragment.turnOffAll();
                billDepositFragment.turnOffAll();
                billWithDrawsFragment.turnOffAll();
                billGameFragment.turnOffAll();
                billOfferFragment.turnOffAll();
            }
        });
    }


}
