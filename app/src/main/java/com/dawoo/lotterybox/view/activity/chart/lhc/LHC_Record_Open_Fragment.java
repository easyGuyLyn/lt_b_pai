package com.dawoo.lotterybox.view.activity.chart.lhc;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.dawoo.lotterybox.ConstantValue;
import com.dawoo.lotterybox.bean.LHCRecordBean;
import com.dawoo.lotterybox.bean.lottery.Handicap;
import com.dawoo.lotterybox.util.lottery.LHCUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 开奖
 * Created by rain on 18-3-18.
 */

public class LHC_Record_Open_Fragment extends LHC_Record_baseFragment {

    public static LHC_Record_Open_Fragment newInstace(List<Handicap> mDatas) {
        LHC_Record_Open_Fragment fragment = new LHC_Record_Open_Fragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ConstantValue.RENCT_DATA, (ArrayList<? extends Parcelable>) mDatas);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        countTopNums = 13;
        if (typeData.size() > 0) {
            return;
        }
        typeData.add("正一");
        typeData.add("正二");
        typeData.add("正三");
        typeData.add("正四");
        typeData.add("正五");
        typeData.add("正六");
        typeData.add("特码");
        typeData.add("生肖");
        typeData.add("波色");
        typeData.add("大小");
        typeData.add("单双");
        typeData.add("合数");
        typeData.add("尾数");

    }

    @Override
    void initData() {

        for (int i = 0; i < mDatas.size(); i++) {
            Handicap handicap = mDatas.get(i);
            List<LHCRecordBean> itemData = new ArrayList<>();
            String[] opcodes = handicap.getOpenCode().split(",");
            if (opcodes.length < 7) {
                continue;
            }
            for (int j = 0; j < typeData.size(); j++) {
                LHCRecordBean bean = new LHCRecordBean();
                if (j < 7) {
                    bean.setCode(opcodes[j]);
                } else if (j == 7) {
                    bean.setAnimall(true);
                    bean.setCode(LHCUtil.getShengXiao(opcodes[6]));
                } else if (j == 8) {
                    bean.setCode(LHCUtil.getBallColor(opcodes[6]));
                } else if (j == 9) {
                    bean.setCode(LHCUtil.getBigOrSmall(opcodes[6]));
                } else if (j == 10) {
                    bean.setCode(LHCUtil.getSingleOrDouble(opcodes[6]));
                } else if (j == 11) {
                    bean.setAnimall(true);
                    bean.setCode(LHCUtil.getAddNum(opcodes[6]));
                } else if (j == 12) {
                    bean.setAnimall(true);
                    bean.setCode(LHCUtil.getLastNum(opcodes[6]));
                }
                itemData.add(bean);
            }

            contentDatas.add(itemData);
        }
        leftAdapter.notifyDataSetChanged();
        contentRV.isDrawLine(false);
        mAdapter.notifyDataSetChanged();
    }


}
