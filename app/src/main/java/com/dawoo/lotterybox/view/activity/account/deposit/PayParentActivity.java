package com.dawoo.lotterybox.view.activity.account.deposit;


import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.view.activity.BaseActivity;
import com.dawoo.lotterybox.view.fragment.BaseFragment;
import com.dawoo.lotterybox.view.fragment.mc.MutiPayFragment;
import com.dawoo.lotterybox.view.view.HeaderView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by jack on 18-2-8.
 * 充值
 * 这个页面数据也是后台返回的
 * @author alex
 */

public class PayParentActivity extends BaseActivity {
    @BindView(R.id.head_view)
    HeaderView headView;
    @BindView(R.id.tl_pay_top)
    TabLayout tlPayTop;
    @BindView(R.id.vp_pay_content)
    ViewPager vpPayContent;

    @Override
    protected void createLayoutView() {

        setContentView(R.layout.activity_pay);
    }


    @Override
    protected void initViews() {
        headView.setHeader(getResources().getString(R.string.pay_title), true);
        tlPayTop.setupWithViewPager(vpPayContent);

    }

    @Override
    protected void initData() {
        MutiPayFragment compayPayFragment = new MutiPayFragment(true);
        MutiPayFragment onlinePayFragment = new MutiPayFragment(false);
        List<BaseFragment> list = new ArrayList<>();
        list.add(compayPayFragment);
        list.add(onlinePayFragment);
        PayAdapter payAdapter = new PayAdapter(getSupportFragmentManager(), list);
        vpPayContent.setAdapter(payAdapter);
        tlPayTop.setupWithViewPager(vpPayContent);

    }

    class PayAdapter extends FragmentPagerAdapter {
        String[] titles = {"公司入款", "线上支付"};
        List<BaseFragment> list = new ArrayList<>();

        public PayAdapter(FragmentManager fm, List<BaseFragment> list) {
            super(fm);
            this.list = list;
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }


}
