package com.dawoo.lotterybox.view.fragment.lhc;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dawoo.lotterybox.BoxApplication;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.bean.playtype.PlayDetailBean;
import com.dawoo.lotterybox.util.ThreadUtils;
import com.dawoo.lotterybox.util.lottery.LHCUtil;
import com.dawoo.lotterybox.util.lottery.initdata.LHCDataUtils;
import com.dawoo.lotterybox.view.activity.lottery.lhc.HKSMActivity;

/**
 * 连码
 * Created by rain on 18-3-9.
 */

public class LHC_Com_Match_Fragment extends LHC_CodeBase_Fragment {

    String bet = "lhc_three_all_in";
    int index=0;
    @Override
    public void onResume() {
        super.onResume();
        ((HKSMActivity)getActivity()).setParentType(BoxApplication.getContext().getString(R.string.lhc_play_type1));
    }
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden){
            ((HKSMActivity) getActivity()).setParentType(getString(R.string.lhc_play_type1));
        }
    }
    @Override
    void setOnItemClick() {
        typeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if(index==position){return;}
                LHCDataUtils.changeTypeSelected(typeList, position);
                typeAdapter.notifyDataSetChanged();
                index=position;
                if (position == 0) {
                    bet = getString(R.string.lhc_con_match_code0);
                    minSelected =3;
                } else if (position == 1) {
                    bet = getString(R.string.lhc_con_match_code1);
                    minSelected =3;
                } else if (position == 2) {
                    bet = getString(R.string.lhc_con_match_code2);
                    minSelected =2;
                } else if (position == 3) {
                    bet = getString(R.string.lhc_con_match_code3);
                    minSelected =2;
                } else if (position == 4) {
                    minSelected =2;
                    bet = getString(R.string.lhc_con_match_code4);
                } else if (position == 5) {
                    minSelected =4;
                    bet = getString(R.string.lhc_con_match_code5);
                }
                initData();
                ((HKSMActivity)getActivity()).clearSelected();
            }
        });
    }

    @Override
    void initData() {
        if (typeList == null || typeList.isEmpty()) {
            typeList.addAll(LHCDataUtils.getContinuTypes());
            typeAdapter.notifyDataSetChanged();
            minSelected=3;
        }
        ThreadUtils.newThread(new Runnable() {
            @Override
            public void run() {
                needChange =false;
                PlayDetailBean ask0 = new PlayDetailBean();
                ask0.setAskview(true);
                ask0.setNum(typeList.get(index).getBetName());
                ask0.setCode(bet);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        allDatas.clear();
                        allDatas.add(ask0);
                        allDatas.addAll(LHCDataUtils.getBollsByBet(bet));
                       mAdapter.notifyDataSetChanged();
                    }
                });
            }
        });

    }

    @Override
    void randomSelected() {
        try {
            if(allDatas.isEmpty()){return;}
            LHCDataUtils.clearSelected(allDatas);
            int[] selected;
                selected = LHCUtil.getRandomList(minSelected,1,allDatas.size());
            for(int i:selected){
                allDatas.get(i).setSelected(true);
                ((HKSMActivity)getActivity()).addSelected(allDatas.get(i),minSelected);
            }
            palyRecycleView.scrollToPosition(selected[0]);
            mAdapter.notifyDataSetChanged();
        }catch (Exception e){

        }

    }

    @Override
    protected void loadData() {

    }
}
