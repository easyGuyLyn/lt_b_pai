package com.dawoo.lotterybox.view.fragment.lhc;

import android.view.View;

import com.andrognito.patternlockview.utils.RandomUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.bean.playtype.PlayDetailBean;
import com.dawoo.lotterybox.util.ThreadUtils;
import com.dawoo.lotterybox.util.lottery.LHCUtil;
import com.dawoo.lotterybox.util.lottery.initdata.LHCDataUtils;
import com.dawoo.lotterybox.view.activity.lottery.lhc.HKSMActivity;

import java.util.List;
import java.util.Map;


/**
 * Created by rain on 18-3-9.
 */

public class LHC_Color_Fragment extends LHC_Com_Base_Fragment {
    Map<String, List<PlayDetailBean>> listMap;
    String selected = "red";
    int index = 0;

    @Override
    protected void loadData() {

    }

    @Override
    void initView() {
        super.initView();
        typeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (typeList.get(position).isSelected()) {
                    return;
                }
                index = position;
                LHCDataUtils.changeTypeSelected(typeList, position);
                typeAdapter.notifyDataSetChanged();
                LHCDataUtils.clearSelected(playList);
                if (position == 0) {
                    selected = "red";
                } else if (position == 1) {
                    selected = "blue";
                } else {
                    selected = "green";
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
                ((HKSMActivity) getActivity()).setSelectedItem(playList.get(position), 1);
            }
        });

    }

    @Override
    void initData() {

        if (typeList == null || typeList.isEmpty()) {
            typeList.addAll(LHCDataUtils.getColorTypes());
            typeList.get(0).setSelected(true);
            typeAdapter.notifyDataSetChanged();
            listMap = LHCDataUtils.getHalfColor();
        }

        needChange = false;
        playList.clear();
        playList.addAll(listMap.get(selected));
        playAdapter.notifyDataSetChanged();
        askView.setAskTV(typeList.get(index).getBetName(), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((HKSMActivity) getActivity()).showExplainDialog(LHCUtil.getExplianbyCode("half_color_" + selected));
            }
        });
    }

    @Override
    void randomSelected() {
        LHCDataUtils.clearSelected(playList);
        int selected = RandomUtils.randInt(playList.size() - 1);
        playList.get(selected).setSelected(true);
        ((HKSMActivity) getActivity()).setSelectedItem(playList.get(selected), 1);
        playAdapter.notifyDataSetChanged();
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
