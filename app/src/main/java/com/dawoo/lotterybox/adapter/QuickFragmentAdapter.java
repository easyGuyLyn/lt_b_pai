package com.dawoo.lotterybox.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.dawoo.lotterybox.view.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 18-4-20.
 * @author alex
 */

public class QuickFragmentAdapter extends FragmentPagerAdapter {
    private List<BaseFragment> lists = new ArrayList<>();
    private String[] titles;

    public QuickFragmentAdapter(FragmentManager fm, List<BaseFragment> lists,String ... titles) {
        super(fm);
        this.lists = lists;
        this.titles=titles;
    }

    @Override
    public Fragment getItem(int position) {
        return lists.get(position);
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
