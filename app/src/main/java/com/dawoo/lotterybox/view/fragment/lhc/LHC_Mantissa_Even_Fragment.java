package com.dawoo.lotterybox.view.fragment.lhc;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dawoo.lotterybox.BoxApplication;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.util.ThreadUtils;
import com.dawoo.lotterybox.util.lottery.LHCUtil;
import com.dawoo.lotterybox.util.lottery.initdata.LHCDataUtils;
import com.dawoo.lotterybox.view.activity.lottery.lhc.HKSMActivity;

/**
 * Created by rain on 18-3-12.
 * 尾数连
 */

public class LHC_Mantissa_Even_Fragment extends LHC_Com_Base_Fragment {
    String betCode = BoxApplication.getContext().getString(R.string.lhc_mantissa_even_code0);
    int index = 0;

    @Override
    protected void loadData() {

    }

    @Override
    void initView() {
        super.initView();
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
                index = position;
                LHCDataUtils.changeTypeSelected(typeList, position);
                adapter.notifyDataSetChanged();
                switch (position) {
                    case 0:
                        betCode = BoxApplication.getContext().getString(R.string.lhc_mantissa_even_code0);
                        break;
                    case 1:
                        betCode = BoxApplication.getContext().getString(R.string.lhc_mantissa_even_code1);
                        break;
                    case 2:
                        betCode = BoxApplication.getContext().getString(R.string.lhc_mantissa_even_code2);
                        break;
                    case 3:
                        betCode = BoxApplication.getContext().getString(R.string.lhc_mantissa_even_code3);
                        break;
                }
                ((HKSMActivity) getActivity()).clearSelected();
                initData();
            }
        });
        playAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                playList.get(position).setSelected(!playList.get(position).isSelected());
                adapter.notifyItemChanged(position);
                ((HKSMActivity) getActivity()).setSelectedItem(playList.get(position), index + 2);
            }
        });
    }

    @Override
    void initData() {
        if (typeList == null || typeList.isEmpty()) {
            typeList.addAll(LHCDataUtils.getMantissaEvenTypes());
            typeAdapter.notifyDataSetChanged();
        }
        ThreadUtils.newThread(new Runnable() {
            @Override
            public void run() {
                needChange = false;
                playList = LHCDataUtils.getMantissaEnenByBet(betCode);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        playAdapter.setNewData(playList);
                    }
                });
            }
        });
        askView.setAskTV(typeList.get(index).getBetName(), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((HKSMActivity) getActivity()).showExplainDialog(LHCUtil.getExplianbyCode(betCode));
            }
        });

    }

    @Override
    void randomSelected() {
        try {
            LHCDataUtils.clearSelected(playList);
            int[] selected = LHCUtil.getRandomList(index + 2, 0, playList.size());
            for (int i : selected) {
                playList.get(i).setSelected(true);
                ((HKSMActivity) getActivity()).addSelected(playList.get(i), index + 2);
            }
            playAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        ((HKSMActivity) getActivity()).setParentType(BoxApplication.getContext().getString(R.string.lhc_play_type2));
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            ((HKSMActivity) getActivity()).setParentType(getString(R.string.lhc_play_type2));
        }
    }
}
