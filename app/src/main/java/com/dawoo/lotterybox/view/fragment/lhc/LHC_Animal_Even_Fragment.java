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
 * 连肖
 */

public class LHC_Animal_Even_Fragment extends LHC_Com_Base_Fragment {
    String betCode = BoxApplication.getContext().getResources().getString(R.string.lhc_animal_con_code0);
    private int index = 0;

    @Override
    protected void loadData() {

    }


    @Override
    void initView() {
        super.initView();
        typeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            long clicktime = 0;

            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (typeList.get(position).isSelected()) {
                    return;
                }
                if (System.currentTimeMillis() - clicktime < 500) {
                    return;
                }
                clicktime = System.currentTimeMillis();
                index = position;
                LHCDataUtils.changeTypeSelected(typeList, position);
                adapter.notifyDataSetChanged();
                switch (position) {
                    case 0:
                        betCode = BoxApplication.getContext().getString(R.string.lhc_animal_con_code0);
                        break;
                    case 1:
                        betCode = BoxApplication.getContext().getString(R.string.lhc_animal_con_code1);
                        break;
                    case 2:
                        betCode = BoxApplication.getContext().getString(R.string.lhc_animal_con_code2);
                        break;
                    case 3:
                        betCode = BoxApplication.getContext().getString(R.string.lhc_animal_con_code3);
                        break;
                }
                initData();
                ((HKSMActivity) getActivity()).clearSelected();
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
        needChange = false;
        if (typeList.isEmpty()) {
            typeList.addAll(LHCDataUtils.getAnimaleEvenTypes());
            typeAdapter.notifyDataSetChanged();

        }
        playList.clear();
        playList.addAll(LHCDataUtils.getAnimalEvenDatasByCode(betCode));
        playAdapter.notifyDataSetChanged();
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
            if (playList.isEmpty()) {
                return;
            }
            LHCDataUtils.clearSelected(playList);
            int[] selected = LHCUtil.getRandomList(index + 2, 0, playList.size());
            for (int i : selected) {
                playList.get(i).setSelected(true);
                ((HKSMActivity) getActivity()).addSelected(playList.get(i), index + 2);
            }
            playAdapter.notifyDataSetChanged();
            playRecycle.scrollToPosition(selected[0]);
        } catch (Exception e) {

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
