package com.dawoo.lotterybox.view.fragment.lhc;


import android.view.View;

import com.andrognito.patternlockview.utils.RandomUtils;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.bean.playtype.PlayDetailBean;
import com.dawoo.lotterybox.util.ThreadUtils;
import com.dawoo.lotterybox.util.lottery.initdata.LHCDataUtils;
import com.dawoo.lotterybox.view.activity.lottery.lhc.HKSMActivity;

import java.util.List;


/**
 * Created by rain on 18-3-8.
 */

public class LHC_ZM_1_6_Fragment extends LHC_CodeBase_Fragment {


    @Override
    void initView() {
        super.initView();
        topTypeRecycleView.setVisibility(View.GONE);
    }

    @Override
    void setOnItemClick() {

    }

    void initData() {
        ThreadUtils.newThread(new Runnable() {
            @Override
            public void run() {
                needChange = false;
                if (!allDatas.isEmpty()) {
                    allDatas.clear();
                }
                allDatas.addAll(LHCDataUtils.getOneToSixDatas());
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.notifyDataSetChanged();
                    }
                });
            }
        });


    }


    void randomSelected() {
        try {
            if (allDatas.isEmpty()) {
                return;
            }
            int selectedParent = RandomUtils.randInt(allDatas.size());{
                if(allDatas.get(selectedParent).isAskview()){
                    selectedParent=6;
                }
            }
            allDatas.get(selectedParent).setSelected(true);
            ((HKSMActivity) mContext).setSelectedItem(allDatas.get(selectedParent), 1);
            mAdapter.notifyDataSetChanged();
            palyRecycleView.scrollToPosition(selectedParent);
        } catch (Exception e) {

        }


    }

    void clearSelected() {
        if (!allDatas.isEmpty()) {

            mAdapter.notifyDataSetChanged();
        }
        ((HKSMActivity) mContext).clearSelected();
    }


    @Override
    public void onResume() {
        super.onResume();
        ((HKSMActivity) getActivity()).setParentType(getString(R.string.lhc_play_type0));
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            ((HKSMActivity) getActivity()).setParentType(getString(R.string.lhc_play_type0));
        }
    }
}
