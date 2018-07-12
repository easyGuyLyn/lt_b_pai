package com.dawoo.lotterybox.view.fragment.lhc;

import android.view.View;

import com.andrognito.patternlockview.utils.RandomUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.bean.playtype.PlayDetailBean;
import com.dawoo.lotterybox.util.ThreadUtils;
import com.dawoo.lotterybox.util.lottery.initdata.LHCDataUtils;
import com.dawoo.lotterybox.view.activity.lottery.lhc.HKSMActivity;
import java.util.List;
import java.util.Map;

/**
 * Created by rain on 18-3-8.
 */

public class LHC_ZMT_Fragment extends LHC_CodeBase_Fragment {
    String positive = "positive_first";
    @Override
    protected void loadData() {

    }

    @Override
    void setOnItemClick() {
        typeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (typeList.get(position).isSelected()) {
                    return;
                }
                index = position;
                LHCDataUtils.changeTypeSelected(typeList, position);
                adapter.notifyDataSetChanged();
                switch (position) {
                    case 0:
                        positive = getString(R.string.lch_bcode_z1t);
                        break;
                    case 1:
                        positive = getString(R.string.lch_bcode_z2t);
                        break;
                    case 2:
                        positive = getString(R.string.lch_bcode_z3t);
                        break;
                    case 3:
                        positive = getString(R.string.lch_bcode_z4t);
                        break;
                    case 4:
                        positive = getString(R.string.lch_bcode_z5t);
                        break;
                    case 5:
                        positive = getString(R.string.lch_bcode_z6t);
                        break;

                }
                ((HKSMActivity) getActivity()).clearSelected();
                initData();
            }
        });
    }

    public LHC_ZMT_Fragment getInstance() {
        LHC_ZMT_Fragment fragment = new LHC_ZMT_Fragment();
        return fragment;
    }


    void initData() {
        if (typeList == null || typeList.isEmpty()) {
            typeList.addAll(LHCDataUtils.getTypes());
            typeAdapter.notifyDataSetChanged();
        }
        ThreadUtils.newThread(new Runnable() {
            @Override
            public void run() {
                needChange = false;
                Map<String, List<PlayDetailBean>> plays = LHCDataUtils.getDatasByPositive(positive);
                PlayDetailBean ask0 = new PlayDetailBean();
                ask0.setAskview(true);
                ask0.setNum(typeList.get(index).getBetName());
                ask0.setCode(positive);
                PlayDetailBean ask1 = new PlayDetailBean();
                ask1.setAskview(true);
                ask1.setNum(typeList.get(index).getBetName());
                switch (index) {
                    case 0:
                        ask1.setCode("positive_one_big_small");
                        break;
                    case 1:
                        ask1.setCode("positive_two_big_small");
                        break;
                    case 2:
                        ask1.setCode("positive_three_big_small");
                        break;
                    case 3:
                        ask1.setCode("positive_four_big_small");
                        break;
                    case 4:
                        ask1.setCode("positive_five_big_small");
                        break;
                    case 5:
                        ask1.setCode("positive_six_big_small");
                        break;
                }
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
    void randomSelected() {
        try {
            LHCDataUtils.clearSelected(allDatas);
            int selected = RandomUtils.randInt(allDatas.size());
            if(selected==0||selected==50){
                selected=35;
            }
            allDatas.get(selected).setSelected(true);
            mAdapter.notifyDataSetChanged();
            ((HKSMActivity) getActivity()).setSelectedItem(allDatas.get(selected), minSelected);
            palyRecycleView.scrollToPosition(selected);
        } catch (Exception e) {

        }
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
