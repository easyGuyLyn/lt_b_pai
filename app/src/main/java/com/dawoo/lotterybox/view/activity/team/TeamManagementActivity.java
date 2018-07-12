package com.dawoo.lotterybox.view.activity.team;

import android.support.design.widget.TabLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.adapter.QuickFragmentAdapter;
import com.dawoo.lotterybox.view.activity.team.base.BindLayout;
import com.dawoo.lotterybox.view.activity.team.base.SuperBaseActivity;
import com.dawoo.lotterybox.util.BottomDialogUtils;
import com.dawoo.lotterybox.util.ViewUtils;
import com.dawoo.lotterybox.view.fragment.BaseFragment;
import com.dawoo.lotterybox.view.fragment.mc.TeamMangerFragment;
import com.dawoo.lotterybox.view.view.CustomViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author alex
 */
@BindLayout(R.layout.activity_team_management)
public class TeamManagementActivity extends SuperBaseActivity {


    @BindView(R.id.service_iv)
    RelativeLayout serviceIv;
    @BindView(R.id.title_name)
    TextView titleName;
    @BindView(R.id.setting_iv)
    RelativeLayout settingIv;
    @BindView(R.id.tl_team_head)
    TabLayout tlTeamHead;
    @BindView(R.id.vp_content)
    CustomViewPager vpContent;
    private TeamMangerFragment todayFragment;
    private TeamMangerFragment threeDayFragment;
    private TeamMangerFragment weekFragment;
    private TeamMangerFragment mouthFragment;


    @Override
    protected void initViews() {

        vpContent.setOffscreenPageLimit(4);
        serviceIv.setOnClickListener(v -> finish());
        settingIv.setOnClickListener(v -> BottomDialogUtils.with(TeamManagementActivity.this)
                .showTimePickDialog((startDate, endDate) -> {
                    switch (vpContent.getCurrentItem()){
                        case 0:
                            todayFragment.searchDate(startDate,endDate);
                            break;
                        case 1:
                            threeDayFragment.searchDate(startDate,endDate);
                            break;
                        case 2:
                            weekFragment.searchDate(startDate,endDate);
                            break;
                        case 3:
                            mouthFragment.searchDate(startDate,endDate);
                            break;
                            default:
                    }
                }));
    }

    @Override
    protected void initData() {
        List<BaseFragment> list = new ArrayList<>();
        todayFragment = TeamMangerFragment.getInstance(TeamMangerFragment.TODAY);
        threeDayFragment = TeamMangerFragment.getInstance(TeamMangerFragment.THREEDAY);
        weekFragment = TeamMangerFragment.getInstance(TeamMangerFragment.WEEK);
        mouthFragment = TeamMangerFragment.getInstance(TeamMangerFragment.MOUTH);
        list.add(todayFragment);
        list.add(threeDayFragment);
        list.add(weekFragment);
        list.add(mouthFragment);
        String[] title = {"今日", "近三天", "近一周", "近一个月"};
        QuickFragmentAdapter teamManagerAdapter = new QuickFragmentAdapter(getSupportFragmentManager(), list, title);
        vpContent.setAdapter(teamManagerAdapter);
        tlTeamHead.setupWithViewPager(vpContent);
        ViewUtils.reflexTabLayout(tlTeamHead);
    }

    @Override
    protected void onPause() {
        super.onPause();
        BottomDialogUtils.with(this).destoryTimePick();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
       BottomDialogUtils.with(this).destoryTimePick();
    }
}
