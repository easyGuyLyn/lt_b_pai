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
 * Created by rain on 18-3-7.
 */

public class LHC_TM_Fragment extends LHC_CodeBase_Fragment {
    public LHC_ZM_Fragment getInstance() {
        LHC_ZM_Fragment fragment = new LHC_ZM_Fragment();
        return fragment;
    }

    @Override
    protected void loadData() {

    }

    @Override
    void setOnItemClick() {
        typeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            long clickTime=0;
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (typeList.get(position).isSelected()) {
                    return;
                }
                if(System.currentTimeMillis()-clickTime<500){
                    return;
                }
                clickTime = System.currentTimeMillis();
                minSelected = 1;
                index = position;
                LHCDataUtils.changeTypeSelected(typeList, index);
                adapter.notifyDataSetChanged();
                ((HKSMActivity) getActivity()).clearSelected();
                initData();
            }
        });
    }


    @Override
    void randomSelected() {
        try {
            LHCDataUtils.clearSelected(allDatas);
            int selected = RandomUtils.randInt(allDatas.size());
            if (selected == 0 || selected == 50) {
                selected = 15;
            }
            allDatas.get(selected).setSelected(true);
            mAdapter.notifyDataSetChanged();
            ((HKSMActivity) getActivity()).setSelectedItem(allDatas.get(selected), 1);
            palyRecycleView.scrollToPosition(selected);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    void initData() {
        needChange = false;
        if (typeList == null || typeList.isEmpty()) {
            typeList.addAll(LHCDataUtils.getTmTypes());
            typeAdapter.notifyDataSetChanged();
        }
        ThreadUtils.newThread(new Runnable() {
            @Override
            public void run() {
                Map<String, List<PlayDetailBean>> plays = LHCDataUtils.getZmPlays(typeList.get(index).getBerCode());

                PlayDetailBean ask0 = new PlayDetailBean();
                ask0.setAskview(true);
                ask0.setNum("特码");
                ask0.setCode("special");
                PlayDetailBean ask1 = new PlayDetailBean();
                ask1.setAskview(true);
                ask1.setNum("特码");
                ask1.setCode("special");
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

