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
 * Created by rain on 18-3-12.
 */

public class LHC_Un_Match_Fragment extends LHC_CodeBase_Fragment {
    String betCode = BoxApplication.getContext().getString(R.string.lhc_un_match_code0);

    @Override
    protected void loadData() {

    }

    @Override
    void setOnItemClick() {
        typeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            long clickTime=0;
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (index == position) {
                    return;
                }
                if(System.currentTimeMillis()-clickTime<500){
                    return;
                }
                clickTime = System.currentTimeMillis();
                index = position;
                minSelected = index+5;
                LHCDataUtils.changeTypeSelected(typeList, position);
                typeAdapter.notifyDataSetChanged();
                if (position == 0) {
                    betCode = getString(R.string.lhc_un_match_code0);
                } else if (position == 1) {
                    betCode = getString(R.string.lhc_un_match_code1);
                } else if (position == 2) {
                    betCode = getString(R.string.lhc_un_match_code2);
                } else if (position == 3) {
                    betCode = getString(R.string.lhc_un_match_code3);
                } else if (position == 4) {
                    betCode = getString(R.string.lhc_un_match_code4);
                } else if (position == 5) {
                    betCode = getString(R.string.lhc_un_match_code5);
                } else if (position == 6) {
                    betCode = getString(R.string.lhc_un_match_code6);
                } else if (position == 7) {
                    betCode = getString(R.string.lhc_un_match_code7);
                }
                ((HKSMActivity) getActivity()).clearSelected();
                initData();
            }
        });
    }

    @Override
    void initData() {
        needChange = false;
        if (typeList == null || typeList.isEmpty()) {
            typeList.addAll(LHCDataUtils.getUnMatchTypes());
            typeAdapter.notifyDataSetChanged();
        }
        ThreadUtils.newThread(new Runnable() {
            @Override
            public void run() {
                PlayDetailBean ask0 = new PlayDetailBean();
                ask0.setAskview(true);
                ask0.setNum(typeList.get(index).getBetName());
                ask0.setCode(betCode);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        allDatas.clear();
                        allDatas.add(ask0);
                        allDatas.addAll(LHCDataUtils.getUnMatchDatasByCode(betCode));
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
            int[] selected = LHCUtil.getRandomList(minSelected, 1, allDatas.size());
            for (int i : selected) {
                allDatas.get(i).setSelected(true);
                ((HKSMActivity) getActivity()).addSelected(allDatas.get(i), minSelected);
            }
            mAdapter.notifyDataSetChanged();
            palyRecycleView.scrollToPosition(selected[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        ((HKSMActivity) getActivity()).setParentType(BoxApplication.getContext().getString(R.string.lhc_play_type1));
        minSelected = index+5;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden){
            ((HKSMActivity) getActivity()).setParentType(BoxApplication.getContext().getString(R.string.lhc_play_type1));
            minSelected = index+5;
        }
    }
}
