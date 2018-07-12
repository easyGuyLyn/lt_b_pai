package com.dawoo.lotterybox.view.activity.team;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.adapter.QuickFragmentAdapter;
import com.dawoo.lotterybox.util.BottomDialogUtils;
import com.dawoo.lotterybox.util.ViewUtils;
import com.dawoo.lotterybox.view.activity.BaseActivity;
import com.dawoo.lotterybox.view.activity.team.base.BindLayout;
import com.dawoo.lotterybox.view.activity.team.base.SuperBaseActivity;
import com.dawoo.lotterybox.view.fragment.BaseFragment;
import com.dawoo.lotterybox.view.fragment.mc.TeamMemberFragment;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author alex
 */
@BindLayout(R.layout.activity_team_manager)
public class TeamMemberActivity extends SuperBaseActivity implements MaterialSearchView.OnQueryTextListener,MaterialSearchView.SearchViewListener {

    @BindView(R.id.title_name)
    TextView titleName;
    @BindView(R.id.tb_member)
    TabLayout tbMember;
    @BindView(R.id.vp_team_member_content)
    ViewPager vpTeamMemberContent;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.search_view)
    MaterialSearchView searchView;
    @BindView(R.id.left_btn)
    FrameLayout leftBtn;
    private TeamMemberFragment agentFragment;
    private TeamMemberFragment memberFragment;
    private TeamMemberFragment allFragment;


    @Override
    protected void initViews() {
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
//        toolbar.setNavigationIcon(getResources().getDrawable(R.mipmap.head_back_icon_unclick));
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
        vpTeamMemberContent.setOffscreenPageLimit(3);
        searchView.setOnQueryTextListener(this);
        searchView.setOnSearchViewListener(this);
        searchView.setHint("搜索");
        String startData = getIntent().getStringExtra("startData");
        String endData = getIntent().getStringExtra("endData");
        titleName.setText("团队管理");
        List<BaseFragment> datas = new ArrayList<>();
        allFragment = TeamMemberFragment.getInstance(TeamMemberFragment.ALL, startData, endData);
        agentFragment = TeamMemberFragment.getInstance(TeamMemberFragment.AGENT, startData, endData);
        memberFragment = TeamMemberFragment.getInstance(TeamMemberFragment.MEMBER, startData, endData);
        datas.add(allFragment);
        datas.add(agentFragment);
        datas.add(memberFragment);
        QuickFragmentAdapter quickFragmentAdapter = new QuickFragmentAdapter(getSupportFragmentManager(), datas, "所有", "代理", "会员");
        vpTeamMemberContent.setAdapter(quickFragmentAdapter);
        tbMember.setupWithViewPager(vpTeamMemberContent);
        ViewUtils.reflexTabLayout(tbMember);
        leftBtn.setOnClickListener(v -> finish());
//        flRightClick.setOnClickListener(v -> BottomDialogUtils.with(TeamMemberActivity.this)
//                .showTimePickDialog((start, end) -> {
//                    LogUtils.e("start",startData);
//                    LogUtils.e("end",endData);
//                    switch (vpTeamMemberContent.getCurrentItem()) {
//                        case 0:
//                            allFragment.searchDay(start,end,"");
//                            break;
//                        case 1:
//                            agentFragment.searchDay(start,end,"agent");
//                            break;
//                        case 2:
//                            memberFragment.searchDay(start,end,"member");
//                            break;
//                        default:
//                    }
//                }));
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onPause() {
        super.onPause();
        BottomDialogUtils.with(this)
                .destoryTimePick();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BottomDialogUtils.with(this)
                .destoryTimePick();
    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//
//        MenuItem item = menu.findItem(R.id.action_search);
//        searchView.setMenuItem(item);
//
//        return true;
//    }

    //搜索筛选

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public void onSearchViewShown() {

    }

    @Override
    public void onSearchViewClosed() {

    }
}
