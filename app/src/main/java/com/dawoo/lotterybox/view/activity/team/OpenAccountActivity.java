package com.dawoo.lotterybox.view.activity.team;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.adapter.QuickFragmentAdapter;
import com.dawoo.lotterybox.view.activity.team.base.BindLayout;
import com.dawoo.lotterybox.view.activity.team.base.SuperBaseActivity;
import com.dawoo.lotterybox.view.fragment.BaseFragment;
import com.dawoo.lotterybox.view.fragment.mc.LinkOpenAccountFragment;
import com.dawoo.lotterybox.view.fragment.mc.SimpleOpenAccountFragment;
import com.dawoo.lotterybox.view.view.HeaderView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author alex
 */
@BindLayout(R.layout.activity_open_account)
public class OpenAccountActivity extends SuperBaseActivity {
    @BindView(R.id.hv_titile)
    HeaderView headerView;
    @BindView(R.id.tl_head)
    TabLayout tlHead;
    @BindView(R.id.vp_content)
    ViewPager vpContent;


    @Override
    protected void initViews() {
        headerView.setHeader("开户中心",true);
        List<BaseFragment> list = new ArrayList<>();
        list.add(SimpleOpenAccountFragment.newInstance());
        list.add(LinkOpenAccountFragment.newInstance());
        QuickFragmentAdapter quickFragmentAdapter = new QuickFragmentAdapter(getSupportFragmentManager(), list, "普通开户", "链接开户");
        vpContent.setAdapter(quickFragmentAdapter);
        tlHead.setupWithViewPager(vpContent);

    }


    @Override
    protected void initData() {

    }



}
