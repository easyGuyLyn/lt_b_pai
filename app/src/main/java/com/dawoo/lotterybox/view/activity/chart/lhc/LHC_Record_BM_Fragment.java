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
 * Created by rain on 18-3-19.
 */

public class LHC_Record_BM_Fragment extends LHC_Record_baseFragment {

    public static LHC_Record_BM_Fragment newInstace(List<Handicap> mDatas) {
        LHC_Record_BM_Fragment fragment = new LHC_Record_BM_Fragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ConstantValue.RENCT_DATA, (ArrayList<? extends Parcelable>) mDatas);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        countTopNums = 10;
        if (typeData.size() > 0) {
            return;
        }
        typeData.add("特码");
        typeData.add("单");
        typeData.add("双");
        typeData.add("合单");
        typeData.add("合双");
        typeData.add("大");
        typeData.add("小");
        typeData.add("红波");
        typeData.add("蓝波");
        typeData.add("绿波");
    }

    @Override
    void initData() {

        for (int i = 0; i < mDatas.size(); i++) {
            Handicap handicap = mDatas.get(i);
            List<LHCRecordBean> itemData = new ArrayList<>();
            String[] openCodes= handicap.getOpenCode().split(",");
            if (openCodes.length < 7) {
                continue;
            }
            int codes = Integer.parseInt(openCodes[6]);
            for (int j = 0; j < typeData.size(); j++) {
                LHCRecordBean bean = new LHCRecordBean();
                if (j == 0) {
                    bean.setCode(String.valueOf(codes));
                    bean.setAnimall(false);
                } else if (j == 1) {
                    if (codes % 2 == 1) {
                        bean.setCode("单");
                        bean.setLeaveOut(0);
                    }
                } else if (j == 2) {
                    if (codes % 2 == 0) {
                        bean.setCode("双");
                        bean.setLeaveOut(0);
                    }
                } else if (j == 3) {
                    int sum = LHCUtil.getAddNum(codes);
                    if (sum % 2 == 1) {
                        bean.setCode("合单");
                        bean.setLeaveOut(0);
                    }
                } else if (j == 4) {
                    int sum = LHCUtil.getAddNum(codes);
                    if (sum % 2 == 0) {
                        bean.setCode("合双");
                        bean.setLeaveOut(0);
                    }
                } else if (j == 5) {
                    if (typeData.get(j).equalsIgnoreCase(LHCUtil.getBigOrSmall(codes))) {
                        bean.setCode("大");
                        bean.setLeaveOut(0);
                    }
                } else if (j == 6) {
                    if (typeData.get(j).equalsIgnoreCase(LHCUtil.getBigOrSmall(codes))) {
                        bean.setCode("小");
                        bean.setLeaveOut(0);
                    }
                } else if (j == 7) {
                    if ("red".equalsIgnoreCase(LHCUtil.getBallColor(codes))) {
                        bean.setCode(String.valueOf(codes));
                        bean.setLeaveOut(0);
                    }
                } else if (j == 8) {
                    if ("blue".equalsIgnoreCase(LHCUtil.getBallColor(codes))) {
                        bean.setCode(String.valueOf(codes));
                        bean.setLeaveOut(0);
                    }
                } else {
                    if ("green".equalsIgnoreCase(LHCUtil.getBallColor(codes))) {
                        bean.setCode(String.valueOf(codes));
                        bean.setLeaveOut(0);
                    }
                }

                if (bean.getCode() == null && i != 0 && i - 1 < contentDatas.size()) {
                    bean.setLeaveOut(contentDatas.get(i - 1).get(j).getLeaveOut() + 1);
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
