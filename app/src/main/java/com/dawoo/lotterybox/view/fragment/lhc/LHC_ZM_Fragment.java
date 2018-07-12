package com.dawoo.lotterybox.view.fragment.lhc;

import android.view.View;

import com.andrognito.patternlockview.utils.RandomUtils;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.bean.playtype.PlayDetailBean;
import com.dawoo.lotterybox.util.ThreadUtils;
import com.dawoo.lotterybox.util.lottery.initdata.LHCDataUtils;
import com.dawoo.lotterybox.view.activity.lottery.lhc.HKSMActivity;
import java.util.List;
import java.util.Map;


/**
 * Created by rain on 18-3-7.
 */

public class LHC_ZM_Fragment extends LHC_CodeBase_Fragment {

    public LHC_ZM_Fragment getInstance() {
        LHC_ZM_Fragment fragment = new LHC_ZM_Fragment();
        return fragment;
    }


    @Override
    void randomSelected() {
        try {
            LHCDataUtils.clearSelected(allDatas);
            int selected = RandomUtils.randInt(allDatas.size());
            if(selected==0||selected==50){
                selected=25;
            }
            allDatas.get(selected).setSelected(true);
            ((HKSMActivity) getActivity()).setSelectedItem(allDatas.get(selected), minSelected);
            mAdapter.notifyDataSetChanged();
            palyRecycleView.scrollToPosition(selected);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Override
    protected void loadData() {

    }

    @Override
    void setOnItemClick() {

    }

    @Override
    void initData() {
        topTypeRecycleView.setVisibility(View.GONE);
        ThreadUtils.newThread(new Runnable() {
            @Override
            public void run() {
                needChange = false;
                Map<String, List<PlayDetailBean>> plays = LHCDataUtils.getPositiveDatas();
                PlayDetailBean ask0 = new PlayDetailBean();
                ask0.setAskview(true);
                ask0.setNum("正码");
                ask0.setCode("positive");
                PlayDetailBean ask1 = new PlayDetailBean();
                ask1.setAskview(true);
                ask1.setNum("总分");
                ask1.setCode("positive_no_digital");
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        allDatas.clear();
                        allDatas.add(ask0);
                        allDatas.addAll(plays.get("boll"));
                        allDatas.add(ask1);
                        allDatas.addAll(plays.get("word"));
                        mAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        ((HKSMActivity) getActivity()).setParentType(getString(R.string.lhc_play_type0));
    }
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden){
            ((HKSMActivity) getActivity()).setParentType(getString(R.string.lhc_play_type0));

        }
    }
}

