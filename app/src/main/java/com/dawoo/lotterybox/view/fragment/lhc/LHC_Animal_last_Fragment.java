package com.dawoo.lotterybox.view.fragment.lhc;

import android.view.View;

import com.andrognito.patternlockview.utils.RandomUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.util.ThreadUtils;
import com.dawoo.lotterybox.util.lottery.LHCUtil;
import com.dawoo.lotterybox.util.lottery.initdata.LHCDataUtils;
import com.dawoo.lotterybox.view.activity.lottery.lhc.HKSMActivity;

/**
 * Created by rain on 18-3-9.
 */

public class LHC_Animal_last_Fragment extends LHC_Com_Base_Fragment {
    @Override
    protected void loadData() {

    }

    @Override
    void initView() {
        super.initView();
        typeRecycle.setVisibility(View.GONE);
        playAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                playList.get(position).setSelected(!playList.get(position).isSelected());
                adapter.notifyItemChanged(position);
                ((HKSMActivity) getActivity()).setSelectedItem(playList.get(position), 1);
            }
        });
    }

    @Override
    void initData() {
        ThreadUtils.newThread(new Runnable() {
            @Override
            public void run() {
                needChange = false;
                playList = LHCDataUtils.getAnimationDatasByPosition(getString(R.string.lhc_special_animal));
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        playAdapter.setNewData(playList);
                        askView.setAskTV("特肖", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ((HKSMActivity) getActivity()).showExplainDialog(LHCUtil.getExplianbyCode("special_animal"));
                            }
                        });
                    }
                });
            }
        });
    }

    @Override
    void randomSelected() {
        try {
            LHCDataUtils.clearSelected(playList);
            int selected = RandomUtils.randInt(playList.size() - 1);
            playList.get(selected).setSelected(true);
            ((HKSMActivity) getActivity()).setSelectedItem(playList.get(selected), 1);
            playRecycle.scrollToPosition(selected);
            playAdapter.notifyDataSetChanged();
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
        if (!hidden) {
            ((HKSMActivity) getActivity()).setParentType(getString(R.string.lhc_play_type0));
        }
    }
}
