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
 * 合肖
 * Created by rain on 18-3-12.
 */

public class LHC_Animal_Add_Fragment extends LHC_Com_Base_Fragment {
    String betCode = BoxApplication.getContext().getString(R.string.lhc_add_animal_code0);
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
                LHCDataUtils.changeTypeSelected(typeList, position);
                typeAdapter.notifyDataSetChanged();
                index = position;
                switch (position) {
                    case 0:
                        betCode = BoxApplication.getContext().getString(R.string.lhc_add_animal_code0);
                        break;
                    case 1:
                        betCode = BoxApplication.getContext().getString(R.string.lhc_add_animal_code1);
                        break;
                    case 2:
                        betCode = BoxApplication.getContext().getString(R.string.lhc_add_animal_code2);
                        break;
                    case 3:
                        betCode = BoxApplication.getContext().getString(R.string.lhc_add_animal_code3);
                        break;
                    case 4:
                        betCode = BoxApplication.getContext().getString(R.string.lhc_add_animal_code4);
                        break;
                    case 5:
                        betCode = BoxApplication.getContext().getString(R.string.lhc_add_animal_code5);
                        break;
                    case 6:
                        betCode = BoxApplication.getContext().getString(R.string.lhc_add_animal_code6);
                        break;
                    case 7:
                        betCode = BoxApplication.getContext().getString(R.string.lhc_add_animal_code7);
                        break;
                    case 8:
                        betCode = BoxApplication.getContext().getString(R.string.lhc_add_animal_code8);
                        break;
                    case 9:
                        betCode = BoxApplication.getContext().getString(R.string.lhc_add_animal_code9);
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
                playAdapter.notifyItemChanged(position);
                ((HKSMActivity) getActivity()).setSelectedItem(playList.get(position), index + 2);
            }
        });
    }

    @Override
    void initData() {
        if (typeList.isEmpty()) {
            typeList.addAll(LHCDataUtils.getAnimalAddTypes());
            typeAdapter.notifyDataSetChanged();

        }

        needChange = false;
        playList.clear();
        playList.addAll(LHCDataUtils.getAnimalDatasByCode(betCode));
        playAdapter.notifyDataSetChanged();
        askView.setAskTV(typeList.get(index).getBetName(), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((HKSMActivity) getActivity()).showExplainDialog(LHCUtil.getExplianbyCode("add_animal"));
            }
        });
    }

    @Override
    void randomSelected() {
        if (playList.isEmpty()) {
            return;
        }
        try {
            LHCDataUtils.clearSelected(playList);
            int[] selected = LHCUtil.getRandomList(index + 2, 0, playList.size());
            for (int index : selected) {
                playList.get(index).setSelected(true);
                ((HKSMActivity) getActivity()).addSelected(playList.get(index), this.index + 2);
            }
            playAdapter.notifyDataSetChanged();
            playRecycle.scrollToPosition(selected[0]);
        } catch (Exception e) {

        }

    }

    @Override
    public void onResume() {
        super.onResume();
        ((HKSMActivity) getActivity()).setParentType(BoxApplication.getContext().getString(R.string.lhc_play_type1));
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            ((HKSMActivity) getActivity()).setParentType(getString(R.string.lhc_play_type1));
        }
    }
}
